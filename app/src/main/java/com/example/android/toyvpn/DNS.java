package com.example.android.toyvpn;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lipis on 2016-04-27.
 */
public class DNS {
    private byte[] transactionID;
    private boolean response;
    private int noOfQuestion;
    private int noOfAnswer;
    private int noOfAuthority;
    private int noOfAdditional;
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

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public int getNoOfQuestion() {
        return noOfQuestion;
    }

    public void setNoOfQuestion(int noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    public int getNoOfAnswer() {
        return noOfAnswer;
    }

    public void setNoOfAnswer(int noOfAnswer) {
        this.noOfAnswer = noOfAnswer;
    }

    public int getNoOfAuthority() {
        return noOfAuthority;
    }

    public void setNoOfAuthority(int noOfAuthority) {
        this.noOfAuthority = noOfAuthority;
    }

    public int getNoOfAdditional() {
        return noOfAdditional;
    }

    public void setNoOfAdditional(int noOfAdditional) {
        this.noOfAdditional = noOfAdditional;
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
