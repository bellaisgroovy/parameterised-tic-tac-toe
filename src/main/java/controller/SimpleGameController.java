package controller;

import data.BoardRepository;
import game.boardChecker.BoardChecker;
import game.data.Board;
import game.data.SimpleBoard;

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
    public boolean createBoard(List<Integer> sizes, String gameName) {
        Board board = new SimpleBoard(sizes);
        boardRepository.saveBoard(board, gameName);
        return true;
    }

    public SimpleGameController(BoardChecker boardChecker, BoardRepository boardRepository) {
        this.boardChecker = boardChecker;
        this.boardRepository = boardRepository;
    }

    private BoardRepository boardRepository;
    private BoardChecker boardChecker;
}
