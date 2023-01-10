package com.checkers;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PlayMenu extends MenuScene{
    Button multiPlayerButton;
    Button singlePlayerButton;
    Button backButton;

    VBox vBox;
    
    private PlayMenu()
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

        vBox.getChildren().addAll(singlePlayerButton, multiPlayerButton, backButton);
    }

    private void set_buttons()
    {
        singlePlayerButton = new Button("Play vs AI");
        singlePlayerButton.setId("menu_button");
        multiPlayerButton = new Button("Play vs another player");
        multiPlayerButton.setId("menu_button");
        backButton = new Button("Back");
        backButton.setId("menu_button");

        style_component(singlePlayerButton);
        style_component(multiPlayerButton);
        style_component(backButton);

        backButton.setOnAction(onAction -> {
            MainMenu.getInstance().set_current();
        });

        singlePlayerButton.setOnAction(onAction -> {
            GameCreationMenu gameCreationMenu = new GameCreationMenu();
            gameCreationMenu.set_current();
        });

        multiPlayerButton.setOnAction(onAction -> {
            GameCreationMenu gameCreationMenu = new GameCreationMenu();
            gameCreationMenu.set_current();
        });
    }

    private static PlayMenu instance;

    public static synchronized PlayMenu getInstance()
    {
        if (instance == null) {
            instance = new PlayMenu();
        }
        return instance;
    }

    @Override
    protected void onEnter()
    {
        
    }
}