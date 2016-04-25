package com.example.android.toyvpn;

/**
 * Created by Lipi on 16. 4. 22..
 */
public class TCP {
    private int sourcePort;
    private int destinationPort;
    private int sequence;
    private int acknowledgement;
    private int dataOffset;
    private boolean NS;
    private boolean CWR;
    private boolean ECE;
    private boolean URG;
    private boolean ACK;
    private boolean PSH;
    private boolean RST;
    private boolean SYN;
    private boolean FIN;
    private int windowSize;
    private int checksum;
    private int urgentPointer;

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(sport: " + sourcePort + ", dport: " + destinationPort + " ");
        if (SYN) sb.append("S");
        if (ACK) sb.append("A");
        if (PSH) sb.append("P");
        if (FIN) sb.append("F");
        if (RST) sb.append("R");
        // int(signed) is 32bit, so extend it to long(64bit) for printing
        long unsignedFormat = sequence & 0xFFFF;
        sb.append(", seq: " + unsignedFormat);
        unsignedFormat = acknowledgement & 0xFFFF;
        sb.append(", ack: " + unsignedFormat);
        sb.append(")");
        return sb.toString();
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setAcknowledgement(int acknowledgement) {
        this.acknowledgement = acknowledgement;
    }

    public void setDataOffset(int dataOffset) {
        this.dataOffset = dataOffset;
    }

    public void setNS(boolean NS) {
        this.NS = NS;
    }

    public void setCWR(boolean CWR) {
        this.CWR = CWR;
    }

    public void setECE(boolean ECE) {
        this.ECE = ECE;
    }

    public void setURG(boolean URG) {
        this.URG = URG;
    }

    public void setACK(boolean ACK) {
        this.ACK = ACK;
    }

    public void setPSH(boolean PSH) {
        this.PSH = PSH;
    }

    public void setRST(boolean RST) {
        this.RST = RST;
    }

    public void setSYN(boolean SYN) {
        this.SYN = SYN;
    }

    public void setFIN(boolean FIN) {
        this.FIN = FIN;
    }

    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }

    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    public void setUrgentPointer(int urgentPointer) {
        this.urgentPointer = urgentPointer;
    }

}
