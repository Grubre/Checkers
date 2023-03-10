package com.checkers.main_menu;

import com.checkers.view.StageView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public final class MainMenuView extends StageView {
    MainMenuController controller;

    Button playButton;
    Button exitButton;
    VBox vBox;

    public MainMenuView(MainMenuController controller)
    {
        this.controller = controller;

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
            controller.exitGame();
        });

        playButton.setOnAction(onAction -> {
            controller.playGame();    
        });
    }
}
