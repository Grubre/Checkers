package com.checkers;

import com.checkers.scenes.MainMenu;
import com.checkers_core.BasicBoard;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class App extends Application {
    private static Stage mStage;

    @Override
    public void start(Stage stage) {
        mStage = stage;

        mStage.centerOnScreen();

        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.setCurrent();

        mStage.setTitle("Checkers");
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