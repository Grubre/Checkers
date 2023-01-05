package com.checkers;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConnectingToServerMenu extends MenuScene{
    Text loadingText;
    ProgressBar progressBar;
    Button backButton;

    VBox vBox;
    
    private ConnectingToServerMenu()
    {
        super();

        progressBar = new ProgressBar();

        loadingText = new Text("Connecting...");
        loadingText.setId("loading_text");
        //style_component(loadingText);

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setId("background");
        AnchorPane.setTopAnchor(vBox, 0.0);
        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setBottomAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);
        
        root.getChildren().add(vBox);

        set_buttons();

        style_component(vBox);

        vBox.getChildren().addAll(loadingText, progressBar, backButton);
    }

    private void set_buttons()
    {
        backButton = new Button("Back");
        backButton.setId("menu_button");

        style_component(backButton);

        backButton.setOnAction(onAction -> {
            MainMenu.getInstance().set_current();
        });
    }

    private static ConnectingToServerMenu instance;

    public static synchronized ConnectingToServerMenu getInstance()
    {
        if (instance == null) {
            instance = new ConnectingToServerMenu();
        }
        return instance;
    }
}

