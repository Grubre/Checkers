package com.checkers.choose_lobby;

import com.checkers.connection.ServerConnection;
import com.checkers.controller.StageController;
import com.checkers.stage_manager.StageManager;
import com.checkers_core.comm.command.JoinGameCommand;
import com.checkers_core.comm.command.ListLobbyCommand;
import com.checkers_core.resp.ResponseListener;
import com.checkers_core.resp.ResponseVisitor;
import com.checkers_core.resp.response.GameConnectionSuccessfulResponse;
import com.checkers_core.resp.response.GameConnectionUnsuccessfulResponse;
import com.checkers_core.resp.response.LobbyListResponse;
import com.checkers_core.resp.response.Response;

/*
 * Kontroler menu do wybierania lobby, do którego użytkownik chce dołączyć
 */
public class ChooseLobbyController implements StageController, ResponseListener, ResponseVisitor<Void>{

    /*
     * Stan menu. Jakiej odpowiedzi od serwera oczekujemy? 
     */
    private enum State {
        WAITING_FOR_CONNECT,
        WAITING_FOR_LIST,
        NOT_WAITING
    }

    private State state = State.NOT_WAITING;
    private ChooseLobbyView view = new ChooseLobbyView(this);
    private StageManager manager;
    private ServerConnection connection;

    /**
     * Konstruktor
     * @param manager Menadżer scen
     * @param connection Połączenie z serwerem
     */
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

    /*
     * Odświeżenie listy lobby z serwera
     */
    public void refreshList() {
        state = State.WAITING_FOR_LIST;
        connection.getListener().onCommand(new ListLobbyCommand());
    }

    /*
     * Wysłanie zapytania o stworzenie gry
     */
    public void createGame() {
        manager.switchToOnlineGameCreationMenu(connection);
    }


    /*
     * Wysłanie zapytania o dołączenie do gry
     */
    public void joinGame() {
        Integer lobbyId = view.getSelectedLobby();
        if(lobbyId == null) {
            return;
        }
        state = State.WAITING_FOR_CONNECT;
        connection.getListener().onCommand(new JoinGameCommand(lobbyId));
    }

    /*
     * W razie udanego połączenia z grą, przejdź do sceny gry
     */
    @Override
    public Void visitGameConnectionSuccessful(GameConnectionSuccessfulResponse response) {
        if (state == State.WAITING_FOR_CONNECT) {
            state = State.NOT_WAITING;
    
            manager.switchToOnlineGame(response.getDesc(), connection);
        }
        return null;
    }

    /*
     * W razie nieudanego połączenia nic nie rób
     */
    @Override
    public Void visitGameConnectionUnsuccessful(GameConnectionUnsuccessfulResponse response) {
        if (state == State.WAITING_FOR_CONNECT) {
            state = State.NOT_WAITING;
        }

        return null;
    }
    
    /*
     * W razie dostania listy lobby, wyświetl ją
     */
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
