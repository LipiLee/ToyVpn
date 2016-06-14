package com.example.android.toyvpn;

import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class DNS {
    private static final String TAG = "DNS";
    private Integer transactionID;
    private Boolean response;
    private Short opCode;
    private Boolean truncated;
    private Boolean recursionDesired;
    private Boolean nonAuthenticatedData;
    private Integer noOfQuestion;
    private Integer noOfAnswer;
    private Integer noOfAuthority;
    private Integer noOfAdditional;
    private List<Question> queries;
    private List<Answer> answers;

    private ByteBuffer packet;

    /**
     * Get the value in ByteBuffer deciding whether its position is moved or not
     */
    private boolean indexMode;
    private int indexPacket;

    class Question {
        String hostname;
        Integer type;
        Integer queryClass;
    }

    class Answer extends Question {
        Long ttl;
        Integer length;
        InetAddress address;
        String canonicalName;
    }

    public DNS(ByteBuffer packet) {
        this.packet = packet;

        transactionID = get16Bits();

        int flags = get16Bits();
        response = ((flags >> 15) & 1) == 1;
        opCode = (short) ((flags & 0x7800) >>> 11);
        truncated = ((flags >> 9) & 1) == 1;
        recursionDesired = ((flags >> 8) & 1) == 1;
        nonAuthenticatedData = ((flags >> 4) & 1) == 1;

        noOfQuestion = get16Bits();
        noOfAnswer = get16Bits();
        noOfAuthority = get16Bits();
        noOfAdditional = get16Bits();

        queries = new ArrayList<Question>();

        for (int i = 0; i < noOfQuestion; i++) {
            Question temp = new Question();
            temp.hostname = getHostname();
            temp.type = get16Bits();
            temp.queryClass = get16Bits();
            queries.add(temp);
        }

        answers = new ArrayList<Answer>();

        if (noOfAnswer > 0) {
            for (int i = 0; i < noOfQuestion; i++) {
                Answer temp = new Answer();
                temp.hostname = getHostname();
                temp.type = get16Bits();
                temp.queryClass = get16Bits();
                temp.ttl = get32Bits();
                temp.length = get16Bits();
                switch (temp.type) {
                    case 1: // Host Address
                        try {
                            temp.address = getIPAddress();
                        } catch (UnknownHostException e) {
                            Log.e(TAG, e.toString());
                        }
                        break;
                    case 5: // CNAME
                        temp.canonicalName = getHostname();
                        break;
                }
                answers.add(temp);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DNS{");

        for (Question query : queries) {
            sb.append(query.hostname);
        }
        sb.append(" ");

        for (Answer answer : answers) {
            if (answer.type == 1) {
                sb.append(answer.address.getHostAddress());
            }
        }
        sb.append("}");

        return sb.toString();
    }

    private short get8Bits() {
        return (short) (packet.get() & 0xFF);
    }

    private int get16Bits() {
        return packet.getShort() & 0xFFFF;
    }

    private long get32Bits() {
        return packet.getInt() & 0xFFFFFFFFL;
    }

    private String getHostname() {
        short c;
        int len = -1;

        indexMode = false;
        StringBuilder sb = new StringBuilder();

        do {
            c = get8BitsStream();
            if (len == -1 || len == 0) {
                if (c == 0) break;
                else {
                    if (isPointer(c)) {
                        short ch = get8BitsStream();
                        int pointer = ((c & 0x3F) << 8) + ch;
                        indexPacket = 20 + 8 + pointer;
                        if (!indexMode) indexMode = true;
                    } else {
                        if (len == 0) sb.append(".");
                        len = c;
                    }
                }
            } else {
                sb.append((char)c);
                len--;
            }
        } while (true);

        return sb.toString();
    }

    private short get8BitsStream() {
        short ch;

        if (indexMode) {
            ch = packet.get(indexPacket);
            indexPacket++;
        } else {
            ch = get8Bits();
        }

        return ch;
    }

    private boolean isPointer(short value) {
        return (((value & 0xFF) >> 6) & 3) == 3;
    }

    private InetAddress getIPAddress() throws UnknownHostException {
        byte[] fourBytes = new byte[4];
        for (int i = 0; i < fourBytes.length; i++)
            fourBytes[i] = (byte) (packet.get() & 0xFF);

        try {
            return InetAddress.getByAddress(fourBytes);
        } catch (UnknownHostException e) {
            throw new UnknownHostException("IP address is not valid.");
        }
    }
}
