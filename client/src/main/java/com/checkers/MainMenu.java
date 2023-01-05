package com.checkers;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public final class MainMenu extends MenuScene{
    Button multiPlayerButton;
    Button singlePlayerButton;
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

        set_buttons();

        style_component(vBox);

        vBox.getChildren().addAll(singlePlayerButton, multiPlayerButton, exitButton);
    }

    private void set_buttons()
    {
        singlePlayerButton = new Button("Singleplayer");
        singlePlayerButton.setId("menu_button");
        multiPlayerButton = new Button("Multiplayer");
        multiPlayerButton.setId("menu_button");
        exitButton = new Button("Exit");
        exitButton.setId("menu_button");

        style_component(singlePlayerButton);
        style_component(multiPlayerButton);
        style_component(exitButton);

        exitButton.setOnAction(onAction -> {
            Platform.exit();
        });

        singlePlayerButton.setOnAction(onAction -> {
            GameCreationMenu gameCreationMenu = new GameCreationMenu(new SinglePlayerGame(null));
            gameCreationMenu.set_current();
        });

        multiPlayerButton.setOnAction(onAction -> {
            ConnectingToServerMenu.getInstance().set_current();
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
}
