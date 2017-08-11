package com.spanishinquisition.battleship.client.board.boardcontroller;

import com.spanishinquisition.battleship.client.board.GameBoard;
import com.spanishinquisition.battleship.client.board.GameBoardBuilder;
import com.spanishinquisition.battleship.client.game.Game;

public class OpponentBoardController extends BoardController{

    public OpponentBoardController(GameBoard gameBoard, Game game) {
        super(gameBoard, game);
    }

    @Override
    public void buildBoard() {
        GameBoardBuilder gameBoardBuilder = new GameBoardBuilder(this);
        gameBoardBuilder.fillTheBoardWithButtonsAndLabels();
    }

    public void setBoardDisabled(boolean disable) {
        gameBoard.getGridPane().setDisable(disable);
    }
}
