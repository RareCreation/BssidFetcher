package com.rare.wifivendor;

import com.rare.wifivendor.ui.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        new MainView().start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
