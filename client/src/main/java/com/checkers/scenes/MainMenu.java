package com.checkers.scenes;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import com.checkers_core.Board;

public final class MainMenu extends MenuScene{
    Button playButton;
    Button exitButton;

    VBox vBox;
    
    private MainMenu()
    {
        super();

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setId("background");
        AnchorPane.setTopAnchor(vBox, 0.0);
        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setBottomAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);
        
        root.getChildren().add(vBox);

        setButtons();

        styleComponent(vBox);

        vBox.getChildren().addAll(playButton, exitButton);
    }

    private void setButtons()
    {
        playButton = new Button("Play");
        playButton.setId("menu_button");
        exitButton = new Button("Exit");
        exitButton.setId("menu_button");

        styleComponent(playButton);
        styleComponent(exitButton);

        exitButton.setOnAction(onAction -> {
            Platform.exit();
        });

        playButton.setOnAction(onAction -> {
            ConnectingToServerMenu.getInstance().setCurrent();
        });
    }

    private static MainMenu instance;

    public static synchronized MainMenu getInstance()
    {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    @Override
    protected void onEnter()
    {
        
    }
}
