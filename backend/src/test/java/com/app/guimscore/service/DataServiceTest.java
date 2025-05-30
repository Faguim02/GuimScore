package com.app.guimscore.service;

import com.app.guimscore.dto.DataDto;
import com.app.guimscore.model.DataModel;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;
import com.app.guimscore.model.exceptions.ForbiddenException;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.DataRepository;
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
@DisplayName("data service")
public class DataServiceTest {

    @InjectMocks
    private DataService dataService;
    @Mock
    private DataRepository dataRepository;
    @Mock
    private GameServerRepository gameServerRepository;
    @Mock
    private UserRepository userRepository;

    @Nested
    @DisplayName("function 'createData()'")
    class CreateData {

        @Test
        @DisplayName("should create data")
        void shouldCreateData() {

            UserModel userModel = new UserModel("Fagner", "f@");
            GameServerModel gameServerModel = new GameServerModel("CatStolen", "Jogo de aventura");
            DataDto dataDto = new DataDto("Life", 100, 100, 0);
            gameServerModel.setUser(userModel);

            Mockito.when(gameServerRepository.findById(gameServerModel.getUuid())).thenReturn(Optional.of(gameServerModel));
            Mockito.when(userRepository.findById(userModel.getUuid())).thenReturn(Optional.of(userModel));

            dataService.createData(dataDto, userModel.getUuid(), gameServerModel.getUuid());

            Mockito.verify(dataRepository, Mockito.times(1)).save(Mockito.any(DataModel.class));
        }

        @Test
        @DisplayName("should return Not Found Exception when Data equals a null")
        void shouldReturnNotFoundExceptionWhenDataEqualsANull() {

            UserModel userModel = new UserModel("Fagner", "f@");
            GameServerModel gameServerModel = new GameServerModel("CatStolen", "Jogo de aventura");

            Assertions.assertThrows(NotFoundException.class, () -> dataService.createData(null, userModel.getUuid(), gameServerModel.getUuid()));

        }

        @Test
        @DisplayName("should return Not Found Exception when 'userModel' is empty")
        void shouldReturnNotFoundExceptionUserModel() {

            UserModel userModel = new UserModel("Fagner", "f@");
            GameServerModel gameServerModel = new GameServerModel("CatStolen", "Jogo de aventura");
            DataDto dataDto = new DataDto("Life", 100, 100, 0);

            Mockito.when(userRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

            Assertions.assertThrows(NotFoundException.class, () -> dataService.createData(dataDto, userModel.getUuid(), gameServerModel.getUuid()));
        }

        @Test
        @DisplayName("should return Not Found Exception when 'gameServerModel' is empty")
        void shouldReturnNotFoundExceptionGameServerModel() {

            UserModel userModel = new UserModel("Fagner", "f@");
            GameServerModel gameServerModel = new GameServerModel("CatStolen", "Jogo de aventura");
            DataDto dataDto = new DataDto("Life", 100, 100, 0);

            Mockito.when(gameServerRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

            Assertions.assertThrows(NotFoundException.class, () -> dataService.createData(dataDto, userModel.getUuid(), gameServerModel.getUuid()));
        }

        @Test
        @DisplayName("should return forbidden")
        void shouldReturnForbidden() {
            UserModel userModel = new UserModel("Fagner", "f@");
            UserModel userModel2 = new UserModel();
            GameServerModel gameServerModel = new GameServerModel("CatStolen", "Jogo de aventura");
            DataDto dataDto = new DataDto("Life", 100, 100, 0);
            gameServerModel.setUser(userModel2);

            Mockito.when(gameServerRepository.findById(gameServerModel.getUuid())).thenReturn(Optional.of(gameServerModel));
            Mockito.when(userRepository.findById(userModel.getUuid())).thenReturn(Optional.of(userModel));

            Assertions.assertThrows(ForbiddenException.class, () -> dataService.createData(dataDto, userModel.getUuid(), gameServerModel.getUuid()));
        }

    }

}
