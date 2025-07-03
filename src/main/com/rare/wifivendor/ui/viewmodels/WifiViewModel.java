package com.rare.wifivendor.ui.viewmodels;

import com.rare.wifivendor.models.WifiInfo;
import com.rare.wifivendor.services.WifiService;

import java.util.concurrent.CompletableFuture;

public class WifiViewModel {
    private final WifiService wifiService;

    public WifiViewModel() {
        this.wifiService = new WifiService();
    }

    public CompletableFuture<WifiInfo> fetchWifiInfo() {
        return wifiService.fetchWifiInfo();
    }
}
