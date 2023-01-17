package com.checkers.game_creation;

import com.checkers.stage_manager.StageManager;
import com.checkers_core.VariantStartDescription;

public class SingleGameCreationController implements GameCreationController {

    GameCreationView view = new GameCreationView(this);
    StageManager manager;

    public SingleGameCreationController(StageManager manager) {
        this.manager = manager;
    }

    @Override
    public void activate() {
        view.setCurrent();
    }

    @Override
    public void game() {
        System.out.println("Variant: " + view.getVariant());
        System.out.println("Height: " + view.getHeight() + "Width: " + view.getWidth());
        String variant = switch (view.getVariant()) {
            case "Basic Variant" -> "BASIC";
            default -> null;
        };

        manager.switchToSingleGame(new VariantStartDescription(view.getWidth(), view.getHeight(), variant, view.getColor()));
    }

    @Override
    public void deactivate() {
        //
    }

}
