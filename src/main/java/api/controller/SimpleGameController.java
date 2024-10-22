package api.controller;

import api.data.BoardRepository;
import game.boardChecker.BoardChecker;
import game.data.board.Board;
import game.data.board.factory.BoardFactory;
import game.data.board.factory.ListBoardFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class SimpleGameController implements GameController {
   @Override
    public Board getBoard(String gameName) throws NoSuchElementException {
        return boardRepository.getBoard(gameName);
    }

    @Override
    public boolean playInCell(List<Integer> cellCoordinate, int player, String gameName) {
        Board board = boardRepository.getBoard(gameName);
        board.setCellAt(cellCoordinate, player);
        boardRepository.saveBoard(board, gameName);
        return true;
    }

    @Override
    public int getWinner(String gameName) {
        Board board = boardRepository.getBoard(gameName);
        return boardChecker.winningPlayer(board);
    }

    @Override
    public boolean createBoard(List<Integer> sizes, int streakToWin, String gameName) {
        Board board = boardFactory.createBoard(sizes, streakToWin);
        boardRepository.saveBoard(board, gameName);
        return true;
    }

    public SimpleGameController(BoardChecker boardChecker, BoardRepository boardRepository) {
        this.boardChecker = boardChecker;
        this.boardRepository = boardRepository;
    }

    private final BoardRepository boardRepository;
    
    private final BoardChecker boardChecker;
    
    private final BoardFactory boardFactory = new ListBoardFactory();
}
