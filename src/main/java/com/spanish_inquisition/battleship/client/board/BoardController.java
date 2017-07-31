package com.spanish_inquisition.battleship.client.board;

import com.spanish_inquisition.battleship.client.game.FleetInitializer;
import com.spanish_inquisition.battleship.common.Styles;
import javafx.scene.layout.GridPane;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author Michal_Partacz
 * The method has a GameBoard object and mainly has methods which will operate on it.
 */
public class BoardController {
    private GameBoard gameBoard;

    public BoardController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void buildPlayersBoard() {
        GameBoardBuilder gameBoardBuilder = new GameBoardBuilder(this);
        gameBoardBuilder.buildGameBoard();
    }

    GridPane getBoardGridPane() {
        return this.gameBoard.getGridPane();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Map<Integer, BoardTile> getBoardsIndexTiles() {
        return gameBoard.getIndexTiles();
    }

    public void placeShips() {
        FleetInitializer fleetInitializer = new FleetInitializer(this);
        Map<Integer, BoardTile> indexTiles = gameBoard.getIndexTiles();
        indexTiles.forEach((integer, boardTile) -> boardTile.setTileStyle(Styles.DEFAULT_TILE_COLOR, Styles.TEXT_BLACK));
        fleetInitializer.setUpShips();
    }
}