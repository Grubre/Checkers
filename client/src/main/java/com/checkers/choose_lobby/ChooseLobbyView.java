package com.checkers.choose_lobby;

import java.util.List;

import com.checkers.view.StageView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/*
 * View menu do wybierania lobby, do którego użytkownik chce dołączyć
 */
public class ChooseLobbyView extends StageView{

    private ChooseLobbyController controller;
    private Button gameCreateButton;
    private Button refreshButton;
    private ListView<Integer> list;
    private static Image img = new Image("refresh-icon.png");
    
    /**
     * Konstruktor
     * @param controller Kontroler tego view
     */
    public ChooseLobbyView(ChooseLobbyController controller)
    {
        this.controller = controller;

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

        Label lobbyLabel = new Label("Choose your lobby:");
        list = new ListView<Integer>();
        list.setId("lobby_list");
        list.setOnMouseClicked(click -> {
            if (click.getClickCount() >= 2) {
                controller.joinGame();
            }
        });

        HBox hBox = new HBox();
        styleComponent(hBox);
        AnchorPane.setTopAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        hBox.getChildren().addAll(gameCreateButton, refreshButton);
        vBox.getChildren().addAll(lobbyLabel, list, hBox);
    }
    
    /** 
     * Ustawienie listy lobby do wyświetlenia
     * @param items lista lobby
     */
    public void setLobbys(List<Integer> items) {
        list.getItems().setAll(items);
    }

    
    /** 
     * Lobby które jest wybrane
     * @return Integer
     */
    public Integer getSelectedLobby() {
        return list.getSelectionModel().getSelectedItem();
    }

    private void setButtons()
    {
        gameCreateButton = new Button("Create new lobby");
        gameCreateButton.setId("menu_button");

        ImageView view = new ImageView(img);
        view.setFitHeight(30);
        view.setPreserveRatio(true);

        refreshButton = new Button();
        refreshButton.setPrefSize(30, 30);
        refreshButton.setGraphic(view);
        refreshButton.setId("menu_button");

        styleComponent(gameCreateButton);
        styleComponent(refreshButton);

        gameCreateButton.setOnAction(action -> {
            controller.createGame();
        });

        refreshButton.setOnAction(action -> {
            controller.refreshList();
        });
    }
}
