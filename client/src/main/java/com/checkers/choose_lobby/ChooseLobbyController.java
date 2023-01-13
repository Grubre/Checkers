package com.checkers.choose_lobby;

import com.checkers.connection.ServerConnection;
import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;
import com.checkers_core.comm.command.JoinGameCommand;
import com.checkers_core.comm.command.ListLobbyCommand;
import com.checkers_core.comm.command.NewGameCommand;
import com.checkers_core.resp.ResponseListener;
import com.checkers_core.resp.ResponseVisitor;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers_core.resp.response.LobbyListResponse;
import com.checkers_core.resp.response.Response;

public class ChooseLobbyController implements StageController, ResponseListener, ResponseVisitor<Void>{

    private enum State {
        WAITING_FOR_CONNECT,
        WAITING_FOR_LIST,
        NOT_WAITING
    }

    State state;

    ChooseLobbyView view = new ChooseLobbyView(this);
    StageManager manager;
    ServerConnection connection;

    public ChooseLobbyController(StageManager manager, ServerConnection connection) {
        this.manager = manager;
        this.connection = connection;
    }

    @Override
    public void activate() {
        view.setCurrent();    
        connection.getSender().addListener(this);

        refreshList();
    }

    @Override
    public void deactivate() {
        connection.getSender().removeListener(this);
    }

    public void refreshList() {
        state = State.WAITING_FOR_LIST;
        connection.getListener().onCommand(new ListLobbyCommand());
    }

    public void createGame() {
        state = State.WAITING_FOR_CONNECT;
        connection.getListener().onCommand(new NewGameCommand());
    }
    
    public void joinGame() {
        int lobbyId = view.getSelectedLobby();
        state = State.WAITING_FOR_CONNECT;
        connection.getListener().onCommand(new JoinGameCommand(lobbyId));
    }

    @Override
    public Void visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response) {
        if (state == State.WAITING_FOR_CONNECT) {
            state = State.NOT_WAITING;
    
            manager.switchToGameCreationMenu();
        }
        return null;
    }

    @Override
    public Void visitGameConnectionUnsuccessful(GameConnectionUnsuccessfulResponse response) {
        if (state == State.WAITING_FOR_CONNECT) {
            state = State.NOT_WAITING;
        }

        return null;
    }

    @Override
    public Void visitLobbyList(LobbyListResponse response) {
        if (state == State.WAITING_FOR_LIST) {
            state = State.NOT_WAITING;
            view.setLobbys(response.getLobbyIds());
        }

        return null;
    }
    
    @Override
    public void onResponse(Response response) {
        response.accept(this);
    }
    
    @Override
    public Void onUnimplemented(Response response) {
        return null;
    }
}
