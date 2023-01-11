package com.checkers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public abstract class MenuScene {
    protected String css;
    protected Scene scene;
    protected AnchorPane root;
    
    MenuScene()
    {
        css = getClass().getResource("menu.css").toExternalForm();
        root = new AnchorPane();
        root.setId("background");
        scene = new Scene(root);
    }

    void styleComponent(Parent obj)
    {
        if(css != null)
            obj.getStylesheets().add(css);
        else
            System.out.println("Error loading CSS!");
    }

    void setCurrent()
    {
        onEnter();
        App.getStage().setScene(scene);
    }

    protected abstract void onEnter();
}
