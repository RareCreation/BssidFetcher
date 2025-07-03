package com.rare.wifivendor.services;

import com.rare.wifivendor.models.WifiInfo;
import com.rare.wifivendor.utils.BssidFetcher;
import com.rare.wifivendor.utils.VendorLookup;

import java.util.concurrent.CompletableFuture;

public class WifiService {
    public CompletableFuture<WifiInfo> fetchWifiInfo() {
        return CompletableFuture.supplyAsync(() -> {
            String bssid = BssidFetcher.getBssid();
            String vendor = null;
            if (bssid != null) {
                try {
                    vendor = VendorLookup.getVendor(bssid);
                } catch (Exception ignored) {}
            }
            return new WifiInfo(bssid, vendor);
        });
    }
}
