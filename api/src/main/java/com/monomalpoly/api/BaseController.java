package com.monomalpoly.api;

import com.monomalpoly.api.game.Game;
import com.monomalpoly.api.game.GameRepository;

import com.monomalpoly.api.board.Board;
import com.monomalpoly.api.board.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class BaseController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private BoardRepository boardRepository;

    public Game getLastGame() {
        List<Game> games = gameRepository.findFirst(PageRequest.of(0, 1));
        return games.get(0);
    }

    public Board getLastBoard() {
    	List<Board> boards = boardRepository.findFirst(new PageRequest(0, 1));
    	return boards.get(0);
    }

}


