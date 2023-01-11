package com.checkers.scenes;

import java.util.Timer;

import com.checkers.conn.ConnectionManager;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public final class ConnectingToServerMenu extends MenuScene{
    private Text loadingText;
    private ProgressBar progressBar;
    private Button abortButton;

    private Timer timer = new Timer();

    private VBox vBox;
    
    private ConnectingToServerMenu()
    {
        super();

        progressBar = new ProgressBar();

        loadingText = new Text("Connecting...");
        loadingText.setId("loading_text");

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

        vBox.getChildren().addAll(loadingText, progressBar, abortButton);
    }

    private void setButtons()
    {
        abortButton = new Button("Abort");
        abortButton.setId("menu_button");

        styleComponent(abortButton);

        abortButton.setOnAction(onAction -> {
            //timer.cancel();
            MainMenu.getInstance().setCurrent();
        });
    }

    private static ConnectingToServerMenu instance;

    public static synchronized ConnectingToServerMenu getInstance()
    {
        if (instance == null) {
            instance = new ConnectingToServerMenu();
        }
        return instance;
    }

    @Override
    protected void onEnter()
    {
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        if(connectionManager.connect())
        {
            PlayMenu.getInstance().setCurrent();
        }
        else
        {
            MainMenu.getInstance().setCurrent();
        }
        // timer = new Timer();
        // TimerTask timerTask = new TimerTask() {
        //     @Override
        //     public void run() {
        //         Platform.runLater(new Runnable() {
        //             public void run() {
        //                 PlayMenu.getInstance().setCurrent();
        //             }
        //         });
        //     }  
        // };
        // timer.schedule(timerTask, 500);


    }
}

