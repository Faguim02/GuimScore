package com.app.guimscore.service;

import com.app.guimscore.dto.GameServerDto;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.GameServerRepository;
import com.app.guimscore.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@DisplayName("game server service")
public class GameServerServiceTest {

    @InjectMocks
    GameServerService gameServerService;

    @Mock
    GameServerRepository gameServerRepository;
    @Mock
    UserRepository userRepository;

    @Nested
    @DisplayName("function 'createGameServer()'")
    class createGameServer {

        @Test
        @DisplayName("should executable normal")
        void shouldNotReturnException() {

            GameServerDto gameServerDto = new GameServerDto();
            UserModel userModel = new UserModel();
            UUID uuid = UUID.randomUUID();
            userModel.setUuid(uuid);

            Mockito.when(userRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(userModel));

            Assertions.assertDoesNotThrow(() -> gameServerService.createGameServer(gameServerDto, uuid), RuntimeException.class.getName());

        }

        @Test
        @DisplayName("should return notfound exception")
        void shouldReturnException() {

            GameServerDto gameServerDto = new GameServerDto();
            UUID uuid = UUID.randomUUID();

            Mockito.when(userRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

            Assertions.assertThrows(NotFoundException.class, () -> gameServerService.createGameServer(gameServerDto, uuid));
        }

    }

    @Nested
    @DisplayName("function 'findAllGameServer()'")
    class FindAllGameServer {

        @Test
        @DisplayName("should return list from game server")
        void shouldReturnListGameServer() {
            GameServerModel gameServerModel = new GameServerModel();
            gameServerModel.setNameServer("game1");
            List<GameServerModel> gameServerModelList = new ArrayList<>(List.of(gameServerModel));
            UserModel userModel = new UserModel();

            Mockito.when(userRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(userModel));
            Mockito.when(gameServerRepository.findByUser(Mockito.any(UserModel.class))).thenReturn(gameServerModelList);

            List<GameServerDto> gameServerDtos = gameServerService.findAllGameServers(UUID.randomUUID());

            Assertions.assertEquals(1, gameServerDtos.size());
            Assertions.assertEquals("game1", gameServerDtos.get(0).getNameServer());
            Assertions.assertNotNull(gameServerDtos.get(0).getUuid());
        }

        @Test
        @DisplayName("should return not found exception")
        void shoulReturnNotFoundException() {
            GameServerModel gameServerModel = new GameServerModel();
            gameServerModel.setNameServer("game1");
            UserModel userModel = new UserModel();

            Mockito.when(userRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

            Assertions.assertThrows(NotFoundException.class, () -> gameServerService.findAllGameServers(UUID.randomUUID()));

        }

    }

    @Nested
    @DisplayName("function 'findGameServerById()'")
    class FindGameServerById {

        @Test
        @DisplayName("should return gameServer")
        void shouldReturnGameServer() {

            GameServerModel gameServerModel = new GameServerModel();
            gameServerModel.setNameServer("game one");
            UserModel userModel = new UserModel();
            userModel.setName("Fulano");
            gameServerModel.setUser(userModel);

            Mockito.when(gameServerRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(gameServerModel));

            GameServerDto gameServerDto = gameServerService.findGameServerById(userModel.getUuid(), gameServerModel.getUuid());

            Assertions.assertEquals("game one", gameServerDto.getNameServer());
            Assertions.assertEquals("Fulano", gameServerDto.getUser().getName());

        }
    }

}
