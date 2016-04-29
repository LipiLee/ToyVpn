package com.example.android.toyvpn;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lipis on 2016-04-27.
 */
public class DNS {
    private byte[] transactionID;
    private String hostName;
    private List<byte[]> ipAddresses;

    public DNS() {
        transactionID = new byte[2];
    }

    @Override
    public String toString() {
        return "DNS{" +
                "transactionID=" + Arrays.toString(transactionID) +
                ", hostName='" + hostName + '\'' +
                ", ipAddresses=" + ipAddresses +
                '}';
    }

    public byte[] getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(byte[] transactionID) {
        for (int i = 0; i < transactionID.length; i++) {
            this.transactionID[i] = transactionID[i];
        }
    }
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public List<byte[]> getIpAddresses() {
        return ipAddresses;
    }

    public void addIPAddress(byte[] ipAddress) {
        ipAddresses.add(ipAddress);
    }
}
