package com.example.android.toyvpn;

import java.nio.ByteBuffer;

public class UDP {
    private Integer sourcePort;
    private Integer destinationPort;
    private Integer length;
    private Integer checksum;

    private DNS dns;

    public UDP(ByteBuffer packet) {
        sourcePort = packet.getShort() & 0xFFFF;
        destinationPort = packet.getShort() & 0xFFFF;
        length = packet.getShort() & 0xFFFF;
        checksum = packet.getShort() & 0xFFFF;

        if (sourcePort.intValue() == 53 || destinationPort.intValue() == 53) {
            dns = new DNS(packet);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(sport: " + sourcePort.toString());
        sb.append(", dport: " + destinationPort.toString() + ")");

        return sb.toString();
    }
}
