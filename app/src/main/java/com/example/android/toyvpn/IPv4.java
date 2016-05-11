package com.example.android.toyvpn;

import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class IPv4 {
    private static final String TAG = "IPv4";

    private Byte version;
    private Byte iHL;
    private Byte dscp;
    private Byte ecn;
    private Integer totalLength;
    private Integer id;
    private Boolean reserved;
    private Boolean df;
    private Boolean mf;
    private Integer fragmentOffset;
    private Short ttl;
    private Short protocol;
    private Integer headerChecksum;
    private InetAddress source;
    private InetAddress destination;
    private TCP tcp;
    private UDP udp;
    private ByteBuffer packet;

    public IPv4(ByteBuffer packet) {
        this.packet = packet;
        byte aByte = (byte) (packet.get() & 0xFF);
        version = (byte) ((aByte & 0xF0) >>> 4);
        // iHL unit is number of 32bit words
        iHL = (byte) ((aByte & 0x0F) * 4);
        aByte = (byte) (packet.get() & 0xFF);
        dscp = (byte) ((aByte & 0xFC) >>> 2);
        ecn = (byte) (aByte & 0x03);
        totalLength = packet.getShort() & 0xFFFF;

        id = packet.getShort() & 0xFFFF;
        short aShort = (short) (packet.getShort() & 0xFFFF);
        reserved = (aShort >> 15) == 1;
        df = ((aShort >> 14) & 1) == 1;
        mf = ((aShort >> 13) & 1) == 1;
        fragmentOffset = (aShort & 0x1FFF) * 8;

        ttl = (short) (packet.get() & 0xFF);
        protocol = (short) (packet.get() & 0xFF);
        headerChecksum = packet.getShort() & 0xFFFF;

        byte[] fourBytes = new byte[4];
        for (int i = 0; i < fourBytes.length; i++) {
            fourBytes[i] = packet.get();
        }
        try {
            source = InetAddress.getByAddress(fourBytes);

            for (int i = 0; i < fourBytes.length; i++) {
                fourBytes[i] = packet.get();
            }
            destination = InetAddress.getByAddress(fourBytes);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        if (protocol == 6) {
            tcp = new TCP(packet);
        } else if (protocol == 17) {
            udp = new UDP(packet);
        } else {
            Log.e(TAG, "Not support Transport Layer.");
        }
    }

    public String toString() {

        StringBuilder string = new StringBuilder("S: " + source.toString());

        string.append(", D: " + destination.toString());

        string.append(", ");
        if (protocol == 6) string.append("TCP");
        else if (protocol == 17) string.append("UDP");
        else string.append("Unknown");

        string.append(", hdr: " + iHL);

        return string.toString();
    }
}
