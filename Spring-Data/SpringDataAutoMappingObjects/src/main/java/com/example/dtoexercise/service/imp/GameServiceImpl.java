package com.example.dtoexercise.service.imp;

import com.example.dtoexercise.CommandLineRunnerImp;
import com.example.dtoexercise.model.dto.GameAddDto;
import com.example.dtoexercise.model.entity.Game;
import com.example.dtoexercise.repository.GameRepository;
import com.example.dtoexercise.service.GameService;
import com.example.dtoexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserServiceImp userServiceImp;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;


    public GameServiceImpl(GameRepository gameRepository, UserServiceImp userServiceImp, ModelMapper mapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.userServiceImp = userServiceImp;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {

        Set<ConstraintViolation<GameAddDto>> violation = validationUtil.getViolation(gameAddDto);

        if (!violation.isEmpty()) {
            violation
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }

        Game game = mapper.map(gameAddDto, Game.class);


        gameRepository.save(game);

        System.out.println("Added game " + gameAddDto.getTitle());
    }

    @Override
    public void editGame(String[] command) {

        long gameId = Long.parseLong(command[1]);

        Optional<Game> findGame = gameRepository.findById(gameId);
        if (findGame.isEmpty()) {
            System.out.println("Game don't exist in db!");
            return;
        }

        Game game = findGame.get();

        for (int i = 2; i < command.length; i++) {
            String[] current = command[i].split("=");
            switch (current[0]) {
                case "title" -> gameRepository.editTitle(gameId, current[1]);
                case "price" -> gameRepository.editPrice(gameId, new BigDecimal(current[1]));
                case "size" -> gameRepository.editSize(gameId, Double.parseDouble(current[1]));
                case "trailer" -> gameRepository.editTrailer(gameId, current[1]);
                case "Thumbnail URL" -> gameRepository.editThumbnailURL(gameId, current[1]);
            }
            System.out.println("you have succeeded edit " + current[0]);
        }


    }

    @Override
    public void deleteGame(long gameId) {
        try {
            String gameName = gameRepository.findById(gameId).get().getTitle();
            gameRepository.deleteById(gameId);
            System.out.println("Deleted " + gameName);
        } catch (Exception e) {
            System.out.println("Game don't exist");
        }

    }

    @Override
    public List<String> allGames() {
        return gameRepository.findAll()
                .stream()
                .map(g -> String.format("%s %s%n",g.getTitle(),g.getPrice()))
                .collect(Collectors.toList());


    }


}
