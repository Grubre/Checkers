package com.checkers;

import com.checkers_core.BasicPawnFactory;
import com.checkers_core.BasicVariant;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    private static Stage mStage;

    @Override
    public void start(Stage stage) {
        mStage = stage;

        Group group = new Group();
        
        Scene scene = new Scene(group, 500, 500, Color.WHITE);

        Grid grid = new Grid(8,8, new BasicVariant(8,8, new VisualCheckerFactory()));

        group.getChildren().addAll(grid);
 
        mStage.setTitle("Checkers");
        mStage.setScene(scene);
        mStage.show();
    }

    /**
     * @return the main stage, needed for calculating size of gui components
     */
    public static Stage getStage()
    {
        return mStage;
    }

    public static void main(String[] args) {
        launch();
    }

}