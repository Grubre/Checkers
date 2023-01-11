package com.checkers;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public final class PlayMenu extends MenuScene{
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

        setButtons();

        styleComponent(vBox);

        vBox.getChildren().addAll(singlePlayerButton, multiPlayerButton, backButton);
    }

    private void setButtons()
    {
        singlePlayerButton = new Button("Play vs AI");
        singlePlayerButton.setId("menu_button");
        multiPlayerButton = new Button("Play vs another player");
        multiPlayerButton.setId("menu_button");
        backButton = new Button("Back");
        backButton.setId("menu_button");

        styleComponent(singlePlayerButton);
        styleComponent(multiPlayerButton);
        styleComponent(backButton);

        backButton.setOnAction(onAction -> {
            MainMenu.getInstance().setCurrent();
        });

        singlePlayerButton.setOnAction(onAction -> {
            GameCreationMenu gameCreationMenu = new GameCreationMenu();
            gameCreationMenu.setCurrent();
        });

        multiPlayerButton.setOnAction(onAction -> {
            GameCreationMenu gameCreationMenu = new GameCreationMenu();
            gameCreationMenu.setCurrent();
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