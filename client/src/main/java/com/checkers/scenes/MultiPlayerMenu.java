package com.checkers.scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public final class MultiPlayerMenu extends MenuScene{
    Button gameCreateButton;
    ListView<String> list;

    VBox vBox;
    
    private MultiPlayerMenu()
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

        Label lobbyLabel = new Label("Choose your lobby:");
        list = new ListView<String>();
        setLobbys(FXCollections.observableArrayList (
            "Lobby1", "Lobby2", "Lobby3", "Lobby4"));
        list.setId("lobby_list");
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {
        
                if (click.getClickCount() >= 2) {
                    System.out.println(list.getSelectionModel().getSelectedItem());
                }
            }
        });
        

        vBox.getChildren().addAll(lobbyLabel, list,gameCreateButton);
    }

    public void setLobbys(ObservableList<String> items)
    {
        list.setItems(items);
    }

    private void setButtons()
    {
        gameCreateButton = new Button("Create new game");
        gameCreateButton.setId("menu_button");

        styleComponent(gameCreateButton);

        gameCreateButton.setOnAction(onAction -> {
            GameCreationMenu gameCreationMenu = new GameCreationMenu();
            gameCreationMenu.setCurrent();
        });
    }

    private static MultiPlayerMenu instance;

    public static synchronized MultiPlayerMenu getInstance()
    {
        if (instance == null) {
            instance = new MultiPlayerMenu();
        }
        return instance;
    }

    @Override
    protected void onEnter()
    {
        
    }
}