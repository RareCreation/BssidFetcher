package com.rare.wifivendor.ui.components;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class WifiCardView extends VBox {
    public Label bssidLabel;
    public Label vendorLabel;
    public ProgressIndicator progressIndicator;
    public Button refreshButton;

    public WifiCardView() {
        Label currentWifiLabel = new Label("Current Wi-Fi:");
        currentWifiLabel.getStyleClass().add("section-label");

        bssidLabel = new Label("Loading...");
        bssidLabel.getStyleClass().add("info-label");

        Label vendorInfoLabel = new Label("Vendor Information:");
        vendorInfoLabel.getStyleClass().add("section-label");

        vendorLabel = new Label("");
        vendorLabel.getStyleClass().add("vendor-label");

        progressIndicator = new ProgressIndicator();
        progressIndicator.setVisible(false);

        refreshButton = new Button("Refresh");
        refreshButton.getStyleClass().add("primary-button");

        this.setSpacing(10);
        this.setPadding(new Insets(20));
        this.getStyleClass().add("card");
        this.getChildren().addAll(
                currentWifiLabel,
                bssidLabel,
                vendorInfoLabel,
                vendorLabel,
                progressIndicator,
                refreshButton
        );
    }
}
