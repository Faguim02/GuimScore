package com.app.guimscore.service;

import com.app.guimscore.dto.PlayerDto;
import com.app.guimscore.model.DataModel;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.Player;
import com.app.guimscore.model.exceptions.ForbiddenException;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.DataRepository;
import com.app.guimscore.repository.GameServerRepository;
import com.app.guimscore.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private DataRepository dataRepository;
    @Mock
    private GameServerRepository gameServerRepository;

    @Nested
    @DisplayName("signUp Tests")
    class SignUpTests {

        @DisplayName("Should throw NotFoundException when player name or password is null")
        @Test
        void shouldThrowNotFoundExceptionWhenPlayerNameIsNull() {

            PlayerDto playerDto = new PlayerDto();

            Assertions.assertThrows(NotFoundException.class, () -> {
                playerService.signUp(playerDto);
            });

        }

        @DisplayName("Should throw NotFoundException when player name or password is empty")
        @Test
        void shouldThrowNotFoundExceptionWhenPlayerNameIsEmpty() {
            PlayerDto playerDto = new PlayerDto();
            playerDto.setName("");
            playerDto.setPassword("");

            Assertions.assertThrows(NotFoundException.class, () -> {
                playerService.signUp(playerDto);
            });
        }

        @DisplayName("Should return PlayerDto when player name and password are valid")
        @Test
        void shouldReturnPlayerDtoWhenPlayerNameAndPasswordAreValid() {

            DataModel dataModel = new DataModel("Health", 100, 100, 0);

            GameServerModel gameServerModel = new GameServerModel("Test Server", "Test Description");
            dataModel.setGameServerModel(gameServerModel);
            List<DataModel> dataModelList = List.of(
                    dataModel
            );


            PlayerDto playerDto = new PlayerDto();
            playerDto.setName("validName");
            playerDto.setPassword("validPassword");
            playerDto.setGameServerId(gameServerModel.getUuid());

            Mockito.when(gameServerRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(gameServerModel));
            Mockito.when(dataRepository.findByGameServerModel(gameServerModel)).thenReturn(dataModelList);

            PlayerDto result = playerService.signUp(playerDto);

            Assertions.assertNotNull(result);
            Assertions.assertEquals(playerDto.getName(), result.getName());
            Assertions.assertEquals(playerDto.getGameServerId(), result.getGameServerId());
        }
    }

    @Nested
    @DisplayName("login Tests")
    class LoginTests {

        @DisplayName("Should throw NotFoundException when player not found")
        @Test
        void shouldThrowNotFoundExceptionWhenPlayerNotFound() {

            PlayerDto playerDto = new PlayerDto();
            playerDto.setName("nonExistentName");
            playerDto.setPassword("somePassword");

            Mockito.when(playerRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());

            Assertions.assertThrows(NotFoundException.class, () -> {
                playerService.signIn(playerDto);
            });

        }

        @DisplayName("Should throw ForbiddenException password is incorrect")
        @Test
        void shouldThrowNotFoundExceptionWhenPlayerNameIsEmpty() {
            PlayerDto playerDto = new PlayerDto();
            playerDto.setName("joao");
            playerDto.setPassword("12345678");

            Player player = new Player();
            player.setName("joao");
            //player.setPassword("$2a$10$7QJfFz3u5G8f1r9H8G6OeO5jFz3u5G8f1r9H8G6OeO"); // hashed version of "password
            player.setPassword("$2a$10$Dow1m3bFz3u5G8f1r9H8G6OeO5jFz3u5G8f1r9H8G6OeO5jFz3u5G8f1r9H8G6"); // hashed version of "password123"

            Mockito.when(playerRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(player));


            Assertions.assertThrows(ForbiddenException.class, () -> {
                playerService.signIn(playerDto);
            });
        }

        @DisplayName("Should return PlayerDto when player name and password are valid")
        @Test
        void shouldReturnPlayerDtoWhenPlayerNameAndPasswordAreValid() {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode("12345678");

            PlayerDto playerDto = new PlayerDto();
            playerDto.setName("joao");
            playerDto.setPassword("12345678");

            Player player = new Player();
            player.setName("joao");
            player.setPassword(hashedPassword);

            Mockito.when(playerRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(player));

            playerService.signIn(playerDto);

            PlayerDto playerDtoReturn = playerService.signIn(playerDto);
            Assertions.assertNotNull(playerDtoReturn);
            Assertions.assertEquals(playerDto.getName(), playerDtoReturn.getName());

    }
    }

}
