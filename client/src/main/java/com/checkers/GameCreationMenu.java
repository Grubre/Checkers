package com.checkers;

import com.checkers_core.BasicPawnFactory;
import com.checkers_core.BasicVariant;
import com.checkers_core.Board;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class GameCreationMenu extends MenuScene {
    private Button startGameButton;
    private VBox vBox;

    private Board board;

    private Game game;

    public GameCreationMenu(Game game)
    {
        super();

        this.game = game;

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setId("background");
        AnchorPane.setTopAnchor(vBox, 0.0);
        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setBottomAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);

        set_components();

        style_component(vBox);

        root.getChildren().add(vBox);
    }

    private void set_components()
    {
        Label mainLabel = new Label("Choose your game settings:");
        mainLabel.setId("gamecreate_mainlabel");


        Slider widthSlider = new Slider(2,18,8);
        Label widthLabel = new Label("Width:");
        widthSlider.setShowTickMarks(true);
        widthSlider.setMajorTickUnit(2.0);
        widthSlider.setMinorTickCount(0);
        widthSlider.setShowTickLabels(true);
        widthSlider.setBlockIncrement(2.0f);
        widthSlider.setSnapToTicks(true);
        widthSlider.setId("slider");


        Slider heightSlider = new Slider(2,18,8);
        Label heightLabel = new Label("Height:");
        heightSlider.setShowTickMarks(true);
        heightSlider.setMajorTickUnit(2.0);
        heightSlider.setMinorTickCount(0);
        heightSlider.setShowTickLabels(true);
        heightSlider.setBlockIncrement(2.0f);
        heightSlider.setSnapToTicks(true);
        heightSlider.setId("slider");


        TextArea textArea = new TextArea("abc");
        textArea.setId("variant_textarea");
        textArea.setEditable(false);


        ObservableList<String> variants = FXCollections.observableArrayList (
            "Basic Variant", "Another Variant");
        ComboBox<String> comboBox = new ComboBox<String>(variants);
        comboBox.setOnAction(actionEvent -> {
            textArea.setText("Opis: \n"+comboBox.getValue());
        });
        comboBox.getSelectionModel().selectFirst();
        comboBox.getSelectionModel().getSelectedItem();
        textArea.setText("Opis: \n"+comboBox.getValue());
        

        startGameButton = new Button("Start");
        startGameButton.setOnAction(onAction -> {
            switch(comboBox.getValue())
            {
                case "Basic Variant":
                    board = new BasicVariant(
                            (int)widthSlider.getValue(),
                            (int)heightSlider.getValue(),
                            new BasicPawnFactory());
                break;
            }
            game.set_board(board);
            game.set_current();
        });
        startGameButton.setId("menu_button");

        
        style_component(startGameButton);
        style_component(comboBox);
        style_component(textArea);
        vBox.getChildren().addAll( mainLabel,
                                comboBox, textArea,
                                widthLabel, widthSlider,
                                heightLabel, heightSlider,
                                startGameButton);
    }
}
