package com.rare.wifivendor.models;

public class WifiInfo {
    private final String bssid;
    private final String vendor;
    
    public WifiInfo(String bssid, String vendor) {
        this.bssid = bssid;
        this.vendor = vendor;
    }
    
    public String getBssid() { return bssid; }
    public String getVendor() { return vendor; }
}
