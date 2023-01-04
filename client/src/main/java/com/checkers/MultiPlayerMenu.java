package com.checkers;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MultiPlayerMenu extends MenuScene{
    Text loadingText;
    ProgressBar progressBar;
    Button exitButton;

    VBox vBox;
    
    public MultiPlayerMenu()
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

        vBox.getChildren().addAll(loadingText, progressBar, exitButton);
    }

    private void set_buttons()
    {
        exitButton = new Button("Exit");
        exitButton.setId("menu_button");

        style_component(exitButton);

        exitButton.setOnAction(onAction -> {
            Platform.exit();
        });
    }
}

