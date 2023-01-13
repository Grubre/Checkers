package com.checkers.game_creation;

import com.checkers.connection.ServerConnection;
import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;
import com.checkers_core.VariantStartDescription;
import com.checkers_core.comm.command.NewGameCommand;
import com.checkers_core.resp.ResponseListener;
import com.checkers_core.resp.ResponseVisitor;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers_core.resp.response.Response;

public class MultiGameCreationController implements GameCreationController, StageController, ResponseListener, ResponseVisitor<Void> {
    private enum State {
        WAITING_FOR_CONFIRMATION,
        NOT_WAITING
    }

    State state = State.NOT_WAITING;
    ServerConnection connection;
    StageManager manager;
    GameCreationView view = new GameCreationView(this);

    public MultiGameCreationController(StageManager manager, ServerConnection connection) {
        this.manager = manager;
        this.connection = connection;
    }

    @Override
    public void game() {
        System.out.println("Variant: " + view.getVariant());
        System.out.println("Height: " + view.getHeight() + "Width: " + view.getWidth());
        String variant = switch (view.getVariant()) {
            case "Basic Variant" -> "BASIC";
            default -> null;
        };

        VariantStartDescription desc = new VariantStartDescription(view.getWidth(), view.getHeight(), variant, view.getColor());

        state = State.WAITING_FOR_CONFIRMATION;
        connection.getListener().onCommand(new NewGameCommand(desc));
    }

    @Override
    public Void visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response) {
        if(state == State.WAITING_FOR_CONFIRMATION) {
            state = State.NOT_WAITING;
            manager.switchToGame(response.getDesc());
        }

        return null;
    }

    @Override
    public Void visitGameConnectionUnsuccessful(GameConnectionUnsuccessfulResponse response) {
        if (state == State.WAITING_FOR_CONFIRMATION) {
            state = State.NOT_WAITING;
        }
        return null;
    }

    @Override
    public void activate() {
        view.setCurrent();
        connection.getSender().addListener(this);
    }

    @Override
    public void deactivate() {
        connection.getSender().removeListener(this);
    }

    @Override
    public Void onUnimplemented(Response response) {
        System.out.println("unimplemented");
        return null;
    }

    @Override
    public void onResponse(Response response) {
        response.accept(this);
    }


}
