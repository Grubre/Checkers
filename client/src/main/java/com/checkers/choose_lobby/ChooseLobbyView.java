package com.checkers.choose_lobby;

import java.util.List;

import com.checkers.view.StageView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ChooseLobbyView extends StageView{

    ChooseLobbyController controller;

    Button gameCreateButton;
    ListView<Integer> list;

    VBox vBox;
    
    public ChooseLobbyView(ChooseLobbyController controller)
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

        Label lobbyLabel = new Label("Choose your lobby:");
        list = new ListView<Integer>();
        setLobbys(FXCollections.observableArrayList (2));
        list.setId("lobby_list");
        list.setOnMouseClicked(click -> {
            if (click.getClickCount() >= 2) {
                controller.joinGame();
            }
        });

        vBox.getChildren().addAll(lobbyLabel, list,gameCreateButton);
    }

    public void setLobbys(ObservableList<Integer> items)
    {
        list.setItems(items);
    }

    public void setLobbys(List<Integer> items) {
        list.getItems().setAll(items);
    }

    public int getSelectedLobby() {
        return list.getSelectionModel().getSelectedItem();
    }

    private void setButtons()
    {
        gameCreateButton = new Button("Create new game");
        gameCreateButton.setId("menu_button");

        styleComponent(gameCreateButton);

        gameCreateButton.setOnAction(action -> {
            controller.createGame();
        });
    }
}
