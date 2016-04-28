package com.example.android.toyvpn;

import java.util.Vector;

/**
 * Created by lipis on 2016-04-27.
 */
public class DNS {
    private String hostName;
    private Vector <byte[]> ipAddresses;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Vector<byte[]> getIpAddresses() {
        return ipAddresses;
    }

    public void addIPAddress(byte[] ipAddress) {
        ipAddresses.add(ipAddress);
    }
}
