package com.app.guimscore.service;

import com.app.guimscore.dto.GameServerDto;
import com.app.guimscore.model.UserModel;
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
import org.springframework.beans.factory.annotation.Autowired;

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
        void shouldNotReturnException() {

            GameServerDto gameServerDto = new GameServerDto();
            UserModel userModel = new UserModel();
            UUID uuid = UUID.randomUUID();
            userModel.setUuid(uuid);

            Mockito.when(userRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(userModel));

            Assertions.assertDoesNotThrow(() -> gameServerService.createGameServer(gameServerDto, uuid), RuntimeException.class.getName());

        }

    }

}
