package com.checkers.connecting_menu;

import com.checkers.view.StageView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/*
 * View do menu, podczas łączenie użytkownika z serwerem
 */
public class ConnectingMenuView extends StageView {
    private Button abortButton;

    private ConnectingMenuController controller;
    
    /**
     * Konstruktor
     * @param controller
     */
    public ConnectingMenuView(ConnectingMenuController controller)
    {
        this.controller = controller;

        ProgressBar progressBar = new ProgressBar();

        Text loadingText = new Text("Connecting...");
        loadingText.setId("loading_text");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setId("background");
        AnchorPane.setTopAnchor(vBox, 0.0);
        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setBottomAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);
        
        root.getChildren().add(vBox);

        setButtons();

        styleComponent(vBox);

        vBox.getChildren().addAll(loadingText, progressBar, abortButton);
    }

    private void setButtons()
    {
        abortButton = new Button("Abort");
        abortButton.setId("menu_button");

        styleComponent(abortButton);

        abortButton.setOnAction(onAction -> {
            controller.backToMain();
        });
    }
}
