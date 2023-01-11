package com.checkers.scenes;

import com.checkers.App;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public abstract class MenuScene {
    protected String css;
    protected Scene scene;
    protected AnchorPane root;
    
    MenuScene()
    {
        css = "menu.css";
        root = new AnchorPane();
        root.setId("background");
        scene = new Scene(root);
    }

    public void styleComponent(Parent obj)
    {
        if(css != null)
            obj.getStylesheets().add(css);
        else
            System.out.println("Error loading CSS!");
    }

    public void setCurrent()
    {
        App.getStage().setScene(scene);
        onEnter();
    }

    protected abstract void onEnter();
}
