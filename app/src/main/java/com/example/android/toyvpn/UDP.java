package com.example.android.toyvpn;

import java.nio.ByteBuffer;

public class UDP {
    private static final String TAG = "UDP";

    private final Integer sourcePort;
    private final Integer destinationPort;
    private final Integer length;
    private final Integer checksum;

    private DNS dns;
    private final ByteBuffer packet;

    public UDP(ByteBuffer packet) {
        this.packet = packet;

        sourcePort = get16Bits();
        destinationPort = get16Bits();
        length = get16Bits();
        checksum = get16Bits();

        if (isDNS()) {
            dns = new DNS(packet);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(sport: ").append(sourcePort.toString());
        sb.append(", dport: ").append(destinationPort.toString()).append(")");
        if (sourcePort == 53 || destinationPort == 53)
            sb.append(dns.toString());

        return sb.toString();
    }

    private int get16Bits() {
        return packet.getShort() & 0xFFFF;
    }

    boolean isDNS() {
        return sourcePort == 53 || destinationPort == 53;
    }

    public DNS getDns() {
        return dns;
    }
}
