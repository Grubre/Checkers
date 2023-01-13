package com.checkers;

import com.checkers.stage_manager.StageManager;
import javafx.application.Application;
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

        StageManager manager = new StageManager();
        manager.switchToMainMenu();

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