package com.example.android.toyvpn;

import java.nio.ByteBuffer;

public class TCP {
    private Integer sourcePort;
    private Integer destinationPort;
    private Long seq;
    private Long ack;
    private Byte dataOffset;
    private Boolean NS;
    private Boolean CWR;
    private Boolean ECE;
    private Boolean URG;
    private Boolean ACK;
    private Boolean PSH;
    private Boolean RST;
    private Boolean SYN;
    private Boolean FIN;
    private Integer windowSize;
    private Integer checksum;
    private Integer urgentPointer;

    public TCP(ByteBuffer packet) {
        sourcePort = packet.getShort() & 0xFFFF;
        destinationPort = packet.getShort() & 0xFFFF;
        seq = (long) (packet.getInt() & 0xFFFFFFFFL);
        ack = (long) (packet.getInt() & 0xFFFFFFFFL);
        byte aByte = (byte) (packet.get() & 0xFF);
        dataOffset = (byte) (((aByte & 0xF0) >>> 4) * 4);
        NS = (aByte & 0x01) == 1;
        aByte = (byte) (packet.get() & 0xFF);
        CWR = ((aByte >> 7) & 1) == 1;
        ECE = ((aByte >> 6) & 1) == 1;
        URG = ((aByte >> 5) & 1) == 1;
        ACK = ((aByte >> 4) & 1) == 1;
        PSH = ((aByte >> 3) & 1) == 1;
        RST = ((aByte >> 2) & 1) == 1;
        SYN = ((aByte >> 1) & 1) == 1;
        FIN = (aByte & 1) == 1;
        windowSize = (int) (packet.getShort() & 0xFFFF);
        checksum = (int) (packet.getShort() & 0xFFFF);
        urgentPointer = (int) (packet.getShort() & 0xFFFF);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(sport: " + sourcePort + ", dport: " + destinationPort + " ");
        if (SYN) sb.append("S");
        if (ACK) sb.append("A");
        if (PSH) sb.append("P");
        if (FIN) sb.append("F");
        if (RST) sb.append("R");
        // int(signed) is 32bit, so extend it to long(64bit) for printing
        long unsignedFormat = seq & 0xFFFF;
        sb.append(", seq: " + unsignedFormat);
        unsignedFormat = ack & 0xFFFF;
        sb.append(", ack: " + unsignedFormat);
        sb.append(")");
        return sb.toString();
    }
}
