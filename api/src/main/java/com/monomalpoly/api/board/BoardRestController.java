package com.monomalpoly.api.board;

import com.monomalpoly.api.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import com.monomalpoly.api.chance.Chance;
import com.monomalpoly.api.chance.ChanceRepository;
import com.monomalpoly.api.property.Property;
import com.monomalpoly.api.property.PropertyRepository;
import com.monomalpoly.api.game.Game;

@RestController
public class BoardRestController extends BaseController{
    @Autowired
    private BoardRepository boardRepository;

	@RequestMapping("board")
	public Board board() {
		Iterator<Board> boards = boardRepository.findAll().iterator();

        if (boards.hasNext()) {
            return boards.next();
        } else {
        	Board b = new Board();
        	return b;
        }
	}

}
