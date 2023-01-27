package com.checkers.game_creation;

import com.checkers.stage_manager.StageManager;
import com.checkers_core.VariantStartDescription;

/*
 * Kontroler ekranu do tworzenia gry dla wariantu lokalnego 
 */
public class LocalGameCreationController implements GameCreationController {

    private GameCreationView view = new GameCreationView(this);
    private StageManager manager;

    /**
     * Kontroler
     * @param manager Menadżer scen
     */
    public LocalGameCreationController(StageManager manager) {
        this.manager = manager;
    }

    @Override
    public void activate() {
        view.setCurrent();
    }

    /*
     * Rozpoczęcie gry
     */
    @Override
    public void game() {
        System.out.println("Variant: " + view.getVariant());
        System.out.println("Height: " + view.getHeight() + "Width: " + view.getWidth());
        String variant = switch (view.getVariant()) {
            case "Basic Variant" -> "BASIC";
            case "Anti Checkers" -> "ANTI";
            default -> null;
        };

        manager.switchToLocalGame(new VariantStartDescription(view.getWidth(), view.getHeight(), variant, view.getColor()));
    }

    @Override
    public void deactivate() {
        //
    }

}
