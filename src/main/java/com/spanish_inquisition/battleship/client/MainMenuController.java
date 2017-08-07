package com.spanish_inquisition.battleship.client;

import com.spanish_inquisition.battleship.client.board.GameBoard;
import com.spanish_inquisition.battleship.client.board.boardcontroller.OpponentBoardController;
import com.spanish_inquisition.battleship.client.board.boardcontroller.PlayerBoardController;
import com.spanish_inquisition.battleship.client.game.Game;
import com.spanish_inquisition.battleship.client.network.SocketClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.io.IOException;
import java.util.logging.Level;

import static com.spanish_inquisition.battleship.common.AppLogger.DEFAULT_LEVEL;
import static com.spanish_inquisition.battleship.common.AppLogger.logger;

/**
 * @author Michal_Partacz
 * A controller for a fxml file placed in resources folder.
 * The methods annotated with @FXML react to events specified
 * in the file's UI elements.
 */
public class MainMenuController {

    @FXML
    VBox centralVBox;
    @FXML
    TextField nameTextField;
    @FXML
    Button featureButton;
    @FXML
    HBox gameHBox;
    @FXML
    VBox playersVBox;
    @FXML
    Label playersLabel;
    @FXML
    GridPane playersGridPane;
    @FXML
    VBox opponentsVBox;
    @FXML
    VBox playerNameVBox;
    @FXML
    Label opponentsLabel;
    @FXML
    GridPane opponentsGridPane;
    @FXML
    Label gameStatusLabel;
    @FXML
    VBox fleetSetupVBox;
    @FXML
    Button sendToServerButton;
    @FXML
    Button fleetSetupButton;
    @FXML
    BorderPane mainBorderPane;

    SocketClient socketClient;
    Game game;

    /**
     * This method is run automatically right after the fxml file's loaded
     */
    @FXML
    public void initialize() {
        this.game = new Game();
        game.setStatusController(new StatusController(playersLabel));
        try {
            socketClient = SocketClient.createSocketClientWithSocket();
            game.setSocketClient(socketClient);
        } catch (IOException e) {
            logger.log(Level.WARNING,
                    "The client could not connect to the server", e);
            gameStatusLabel.setText("I couldn't connect to the server");
        }
        initializeElementsAfterServerConnection();
    }

    private void initializeElementsAfterServerConnection() {
        this.centralVBox.setVisible(true);
    }

    /**
     * After the button is clicked the information entered in the textField is sent to the server
     */
    @FXML
    public void onFeatureButtonClicked() {
        this.acceptANameAndStartANewGame(this.nameTextField.getText());
    }

    @FXML
    public void onNameTextFieldEntered() {
        this.acceptANameAndStartANewGame(this.nameTextField.getText());
    }

    private void acceptANameAndStartANewGame(String text) {
        setUpOnCloseRequest();
        game.acceptPlayersName(text);
        playerNameVBox.setVisible(false);
        new Thread(this::buildPlayerBoard).start();
    }

    private void setUpOnCloseRequest() {
        Scene scene = mainBorderPane.getScene();
        if (scene == null) {
            return;
        }
        Window window = scene.getWindow();
        window.setOnCloseRequest(event -> {
            if (game != null) {
                game.stopGameRunning();
                game.closeSocketConnection();
            }
            Platform.exit();
        });
    }

    private void buildPlayerBoard() {
        Platform.runLater(() -> playersLabel.setText("Set up your ships"));
        game.buildPlayersBoard(new PlayerBoardController(
                new GameBoard(this.playersGridPane), game));
        fleetSetupButton.setVisible(true);
    }

    @FXML
    public void onFleetSetupButtonClicked() {
        game.placePlayersShips();
        sendToServerButton.setVisible(true);
    }

    @FXML
    void onSendToServerButtonClicked() {
        fleetSetupButton.setVisible(false);
        sendToServerButton.setVisible(false);
        playersGridPane.setDisable(true);
        opponentsGridPane.setDisable(true);
        Platform.runLater(() -> playersLabel.setText("Wait for opponent's ships placement"));

        game.buildOpponentsBoard(new OpponentBoardController(
                new GameBoard(opponentsGridPane), game));
        game.sendTheFleetToServer();
        new Thread(() -> {
            try {
                game.runTheGame();
            } catch (InterruptedException e) {
                logger.log(DEFAULT_LEVEL, "Game interrupted");
            }
        }).start();
    }
}
