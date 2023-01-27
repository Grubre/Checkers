package com.checkers.view;

import com.checkers.App;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Abstrakcyjny widok sceny
 */
public abstract class StageView {
    protected String css;
    protected Scene scene;
    protected AnchorPane root;
    
    public StageView()
    {
        css = "menu.css";
        root = new AnchorPane();
        root.setId("background");
        scene = new Scene(root);
    }

    
    /** 
     * @param obj
     */
    public void styleComponent(Parent obj)
    {
        obj.getStylesheets().add(css);
    }

    public void setCurrent()
    {
        App.getStage().setScene(scene);
    }
}
