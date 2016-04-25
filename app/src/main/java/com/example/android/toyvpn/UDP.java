package com.example.android.toyvpn;

/**
 * Created by Lipi on 16. 4. 24..
 */
public class UDP {
    int sourcePort;
    int destinationPort;
    int length;
    int checksum;

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(sport: " + sourcePort + ", dport: " + destinationPort + ")");

        return sb.toString();
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }
}
