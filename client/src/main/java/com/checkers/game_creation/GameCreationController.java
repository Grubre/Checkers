package com.checkers.game_creation;

import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;
import com.checkers_core.VariantStartDescription;

public class GameCreationController implements StageController {

    GameCreationView view = new GameCreationView(this);
    StageManager manager;

    public GameCreationController(StageManager manager) {
        this.manager = manager;
    }

    @Override
    public void activate() {
        view.setCurrent();
    }

    public void game() {
        System.out.println("Variant: " + view.getVariant());
        System.out.println("Height: " + view.getHeight() + "Width: " + view.getWidth());
        VariantStartDescription desc = switch (view.getVariant()) {
            case "Basic Variant" -> new VariantStartDescription(view.getWidth(), view.getHeight(), view.getVariant(), view.getColor());
            default -> null;
        };

        manager.switchToGame(desc);
    }

    @Override
    public void deactivate() {
    }

}
