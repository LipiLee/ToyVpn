package com.example.android.toyvpn;

/**
 * Created by Lipi on 16. 4. 22..
 */
public class IPv4 {
    private int version;
    private int internetHeaderLength;
    private int differentiatedServicesCodePoint;
    private int explicitCongestionNotification;
    private int totalLength;
    private int identification;
    private int flags;
    private int fragmentOffset;
    private int timeToLive;
    private int protocol;
    private int headerChecksum;
    byte[] sourceIPAddress;
    byte[] destinationIPAddress;

    public IPv4() {
        sourceIPAddress = new byte[4];
        destinationIPAddress = new byte[4];
    }

    public String toString() {
        int a = sourceIPAddress[0] & 0xFF;
        int b = sourceIPAddress[1] & 0xFF;
        int c = sourceIPAddress[2] & 0xFF;
        int d = sourceIPAddress[3] & 0xFF;

        StringBuilder string = new StringBuilder("Source IP: " +
                Integer.toString(a) + "." + Integer.toString(b) + "." +
                Integer.toString(c) + "." + Integer.toString(d));
        a = destinationIPAddress[0] & 0xFF;
        b = destinationIPAddress[1] & 0xFF;
        c = destinationIPAddress[2] & 0xFF;
        d = destinationIPAddress[3] & 0xFF;
        string.append(", Destination IP: " + Integer.toString(a) + "." +
                Integer.toString(b) + "." + Integer.toString(c) + "." +
                Integer.toString(d));

        string.append(", Type: ");
        if (protocol == 6) string.append("TCP");
        else if (protocol == 17) string.append("UDP");
        else string.append("Unknown");

        string.append(", header: " + internetHeaderLength);

        return string.toString();
    }

    public int getProtocol() {
        return protocol;
    }

    public int getInternetHeaderLength() {
        return internetHeaderLength;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setInternetHeaderLength(int internetHeaderLength) {
        this.internetHeaderLength = internetHeaderLength;
    }

    public void setDifferentiatedServicesCodePoint(int differentiatedServicesCodePoint) {
        this.differentiatedServicesCodePoint = differentiatedServicesCodePoint;
    }

    public void setExplicitCongestionNotification(int explicitCongestionNotification) {
        this.explicitCongestionNotification = explicitCongestionNotification;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public void setFragmentOffset(int fragmentOffset) {
        this.fragmentOffset = fragmentOffset;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public void setHeaderChecksum(int headerChecksum) {
        this.headerChecksum = headerChecksum;
    }

    public void setSourceIPAddress(byte[] sourceIPAddress) {
        for (int i = 0; i < sourceIPAddress.length; i++)
            this.sourceIPAddress[i] = sourceIPAddress[i];
    }

    public void setDestinationIPAddress(byte[] destinationIPAddress) {
        for (int i = 0; i < destinationIPAddress.length; i++)
            this.destinationIPAddress[i] = destinationIPAddress[i];
    }
}
