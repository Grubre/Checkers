package com.checkers.game_creation;

import com.checkers.view.StageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class GameCreationView extends StageView{
    GameCreationController controller;

    private Button startGameButton;
    private VBox vBox;

    private Slider widthSlider;
    private Slider heightSlider;
    private TextArea textArea;
    private ComboBox<String> variantComboBox;
    private ComboBox<String> colorComboBox;

    public GameCreationView(GameCreationController controller)
    {
        this.controller = controller;

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

    private void setComponents()
    {
        Label mainLabel = new Label("Choose your game settings:");
        mainLabel.setId("gamecreate_mainlabel");


        widthSlider = new Slider(2,18,8);
        Label widthLabel = new Label("Width:");
        widthSlider.setShowTickMarks(true);
        widthSlider.setMajorTickUnit(2.0);
        widthSlider.setMinorTickCount(0);
        widthSlider.setShowTickLabels(true);
        widthSlider.setBlockIncrement(2.0f);
        widthSlider.setSnapToTicks(true);
        widthSlider.setId("slider");


        heightSlider = new Slider(2,18,8);
        Label heightLabel = new Label("Height:");
        heightSlider.setShowTickMarks(true);
        heightSlider.setMajorTickUnit(2.0);
        heightSlider.setMinorTickCount(0);
        heightSlider.setShowTickLabels(true);
        heightSlider.setBlockIncrement(2.0f);
        heightSlider.setSnapToTicks(true);
        heightSlider.setId("slider");


        textArea = new TextArea("abc");
        textArea.setId("variant_textarea");
        textArea.setEditable(false);


        ObservableList<String> variants = FXCollections.observableArrayList (
            "Basic Variant", "Anti Checkers");
        variantComboBox = new ComboBox<String>(variants);
        variantComboBox.setOnAction(actionEvent -> { setDescription(); });
        variantComboBox.getSelectionModel().selectFirst();
        variantComboBox.getSelectionModel().getSelectedItem();
        setDescription();

        ObservableList<String> colors = FXCollections.observableArrayList (
            "Black", "White");
        colorComboBox = new ComboBox<String>(colors);
        colorComboBox.getSelectionModel().selectFirst();
        colorComboBox.getSelectionModel().getSelectedItem();

        startGameButton = new Button("Start");
        startGameButton.setOnAction(onAction -> {
            controller.game();
        });

        startGameButton.setId("menu_button");

        
        styleComponent(startGameButton);
        styleComponent(colorComboBox);
        styleComponent(textArea);
        vBox.getChildren().addAll( mainLabel,
                                textArea,
                                widthLabel, widthSlider,
                                heightLabel, heightSlider,
                                variantComboBox, colorComboBox,
                                startGameButton);
    }

    private void setDescription()
    {
        String descString = new String();
        switch(variantComboBox.getValue()) {
            case "Basic Variant" -> {
                descString =
                "Basic checkers variant,\nyou can capture backwards,\n"
                .concat("whoever captures all enemy pawns wins");
            }
            case "Anti Checkers" -> {
                descString =
                "Anti Checkers follows standard rules\nwith one exception,\n"
                .concat("the winner is whoever\nhad all his pieces captured,");
            }
            default -> {descString = "error"; }
        }
        textArea.setText(descString);
    }

    public int getWidth() {
        return (int)widthSlider.getValue();
    }
    
    public int getHeight() {
        return (int)heightSlider.getValue();
    }

    public String getVariant() {
        return variantComboBox.getSelectionModel().getSelectedItem();
    }

    public String getColor() {
        return colorComboBox.getSelectionModel().getSelectedItem();
    }
}
