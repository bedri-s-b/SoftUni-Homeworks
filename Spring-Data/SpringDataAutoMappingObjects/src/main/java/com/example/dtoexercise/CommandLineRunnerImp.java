package com.example.dtoexercise;

import com.example.dtoexercise.model.dto.GameAddDto;
import com.example.dtoexercise.model.dto.UserLoginDto;
import com.example.dtoexercise.model.dto.UserRegisterDto;
import com.example.dtoexercise.service.GameService;
import com.example.dtoexercise.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class CommandLineRunnerImp implements CommandLineRunner {
    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunnerImp(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        while (true){
            System.out.println("Enter your command:");
            String[] command = bufferedReader.readLine().split("\\|");

            switch (command[0]){
                case "RegisterUser" -> userService
                        .registerUser(new UserRegisterDto(command[1],command[2],command[3],command[4]));

                case "LoginUser" -> userService
                        .loginUser(new UserLoginDto(command[1],command[2]));

                case "Logout" -> userService.logout();

                case "AddGame" -> gameService.addGame(new GameAddDto(command[1],new BigDecimal(command[2])
                        ,Double.parseDouble(command[3]),command[4],command[5]
                        ,command[6],command[7]));

                case "EditGame" -> gameService.editGame(command);

                case "DeleteGame" -> gameService.deleteGame(Long.parseLong(command[1]));

                case "AllGames" -> System.out.println(String.join("",gameService.allGames()));
            }
        }
    }
}
