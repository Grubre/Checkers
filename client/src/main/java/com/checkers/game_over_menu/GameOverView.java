package com.checkers.game_over_menu;

import com.checkers.view.StageView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class GameOverView extends StageView{
    GameOverController controller;

    private Button leaveButton;
    private VBox vBox;
    private boolean youWon;

    public GameOverView(GameOverController gameOverController, boolean youWon)
    {
        this.controller = controller;

        this.youWon = youWon;

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setId("background");
        AnchorPane.setTopAnchor(vBox, 0.0);
        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setBottomAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);

        setComponents();

        styleComponent(vBox);

        root.getChildren().add(vBox);
    }

    private void setComponents() {
        Label label = new Label();
        if(youWon) {
            label.setText("You WON!");
        }
        else {
            label.setText("You LOST!");
        }

        leaveButton = new Button("Go back");
        leaveButton.setId("menu_button");
        leaveButton.setOnAction(onAction -> {
            controller.goBackToPlayMenu();
        });
        styleComponent(leaveButton);

        vBox.getChildren().addAll(label, leaveButton);
    }
}
