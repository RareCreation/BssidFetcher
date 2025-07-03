package com.rare.wifivendor.ui;

import com.rare.wifivendor.models.WifiInfo;
import com.rare.wifivendor.ui.components.WifiCardView;
import com.rare.wifivendor.ui.viewmodels.WifiViewModel;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class MainView {
    private final WifiViewModel viewModel = new WifiViewModel();
    private WifiCardView cardView;

    public void start(Stage stage) {
        VBox root = createMain();
        Scene scene = new Scene(root, 500, 350);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/styles.css")).toExternalForm());

        stage.setTitle("Wi-Fi Vendor Finder");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        loadData();
    }

    private VBox createMain() {
        Label title = new Label("Wi-Fi Vendor Finder");
        title.getStyleClass().add("title");

        cardView = new WifiCardView();
        cardView.refreshButton.setOnAction(e -> loadData());

        VBox root = new VBox(20, title, cardView);
        root.setPadding(new Insets(30));
        root.getStyleClass().add("background");
        return root;
    }

    private void loadData() {
        cardView.progressIndicator.setVisible(true);

        viewModel.fetchWifiInfo().thenAccept(wifiInfo -> Platform.runLater(() -> {
            updateView(wifiInfo);
            cardView.progressIndicator.setVisible(false);
        }));
    }

    private void updateView(WifiInfo wifiInfo) {
        if (wifiInfo.getBssid() == null) {
            cardView.bssidLabel.setText("No BSSID found or not connected to Wi-Fi");
            cardView.vendorLabel.setText("");
        } else {
            cardView.bssidLabel.setText(wifiInfo.getBssid());
            cardView.vendorLabel.setText(
                    wifiInfo.getVendor() != null ? wifiInfo.getVendor() : "Vendor not found"
            );
        }
    }
}
