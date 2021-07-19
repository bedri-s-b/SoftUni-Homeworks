package com.example.dtoexercise.service;

import com.example.dtoexercise.model.dto.GameAddDto;

import java.util.List;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(String[] command);

    void deleteGame(long gameId);

    List<String> allGames();
}
