package com.checkers.game_creation;

import com.checkers.connection.ServerConnection;
import com.checkers.stage_manager.StageManager;
import com.checkers_core.VariantStartDescription;
import com.checkers_core.comm.command.NewGameCommand;
import com.checkers_core.resp.ResponseListener;
import com.checkers_core.resp.ResponseVisitor;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers_core.resp.response.Response;

/*
 * Kontroler menu tworzenia gry dla wersji online
 */
public class OnlineGameCreationController implements GameCreationController, ResponseListener, ResponseVisitor<Void> {
    
    /*
     * Stan menu. Czy czekamy na odpowiedź serwera czy nie?
     */
    private enum State {
        WAITING_FOR_CONFIRMATION,
        NOT_WAITING
    }

    private State state = State.NOT_WAITING;
    private ServerConnection connection;
    private StageManager manager;
    private GameCreationView view = new GameCreationView(this);


    /**
     * Konstruktor
     * @param manager Menadźer scen
     * @param connection Połączenie z serwerem
     */
    public OnlineGameCreationController(StageManager manager, ServerConnection connection) {
        this.manager = manager;
        this.connection = connection;
    }

    /*
     * Wysłanie na serwer prośby o rozpoczęcie gry na podstawie opcji wybranych w widoku
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

        VariantStartDescription desc = new VariantStartDescription(view.getWidth(), view.getHeight(), variant, view.getColor());

        state = State.WAITING_FOR_CONFIRMATION;
        connection.getListener().onCommand(new NewGameCommand(desc));
    }

    
    /** 
     * Po uzyskaniu odpowiedzi o połączeniu przechodzimy do gry
     * @param response
     * @return Void
     */
    @Override
    public Void visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response) {
        if(state == State.WAITING_FOR_CONFIRMATION) {
            state = State.NOT_WAITING;
            manager.switchToOnlineGame(response.getDesc(), connection);
        }

        return null;
    }

    
    /** 
     * Po uzyskaniu odpowiedzi o nieudanym połączeniu nie robimi nic
     * @param response
     * @return Void
     */
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

    
    /** 
     * @param response
     * @return Void
     */
    @Override
    public Void onUnimplemented(Response response) {
        System.out.println("unimplemented");
        return null;
    }

    
    /** 
     * @param response
     */
    @Override
    public void onResponse(Response response) {
        response.accept(this);
    }


}
