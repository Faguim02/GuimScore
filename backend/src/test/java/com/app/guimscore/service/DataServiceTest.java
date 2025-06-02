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

import java.util.List;
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

    @Nested
    @DisplayName("function 'findAllData()'")
    class FindAllData {

        @Test
        @DisplayName("should return list from data")
        void shouldReturnListFromData() {

            GameServerModel gameServerModel = new GameServerModel("Mario", "plataform 2d");
            UserModel userModel = new UserModel("fagner", "aa@aa");
            gameServerModel.setUser(userModel);

            DataModel lifeData = new DataModel("life", 100, 100, 0);
            DataModel energy = new DataModel("energy", 0, 100, 0);
            lifeData.setGameServerModel(gameServerModel);
            energy.setGameServerModel(gameServerModel);
            List<DataModel> dataModelList = List.of(lifeData, energy);

            Mockito.when(gameServerRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(gameServerModel));
            Mockito.when(dataRepository.findByGameServerModel(Mockito.any(GameServerModel.class))).thenReturn(dataModelList);

            List<DataDto> dataDtoList = dataService.findAllDatas(userModel.getUuid(), gameServerModel.getUuid());

            Assertions.assertEquals(2, dataDtoList.size());


        }

        @Test
        @DisplayName("should return NotFoundException, is gameserver not found")
        void shouldReturnNotFound() {

            Mockito.when(gameServerRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

            Assertions.assertThrows(NotFoundException.class, () -> dataService.findAllDatas(UUID.randomUUID(), UUID.randomUUID()));
        }

        @Test
        @DisplayName("should retun ForbiddenException")
        void shouldReturnForbiddenException() {

            GameServerModel gameServerModel = new GameServerModel("Mario", "plataform 2d");
            UserModel userModel = new UserModel("fagner", "aa@aa");
            gameServerModel.setUser(userModel);

            Mockito.when(gameServerRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(gameServerModel));

            Assertions.assertThrows(ForbiddenException.class, () -> dataService.findAllDatas(UUID.randomUUID(), gameServerModel.getUuid()));

        }

    }

    @Nested
    @DisplayName("function 'findDataById()'")
    class FindDataById {

        @Test
        @DisplayName("should return Data")
        void shouldReturnData() {

            GameServerModel gameServerModel = new GameServerModel("Mario", "plataform 2d");
            UserModel userModel = new UserModel("fagner", "aa@aa");
            gameServerModel.setUser(userModel);

            DataModel dataModel = new DataModel("life", 100, 100, 0);
            dataModel.setPlayer(userModel);
            dataModel.setGameServerModel(gameServerModel);

            Mockito.when(dataRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(dataModel));

            DataDto dataDto = dataService.findDataById(UUID.randomUUID(), userModel.getUuid(), gameServerModel.getUuid());

            Assertions.assertEquals("life", dataDto.getNameData());
            Assertions.assertEquals(100, dataDto.getMaxValue());
        }

        @Test
        @DisplayName("should return NotFound Exception")
        void shouldReturnNotFoundException() {

            Mockito.when(dataRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

            Assertions.assertThrows(NotFoundException.class, () -> dataService.findDataById(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));

        }

        @Test
        @DisplayName("should return FobiddenException")
        void shouldReturnForbidden() {

            GameServerModel gameServerModel = new GameServerModel("Mario", "plataform 2d");
            UserModel userModel = new UserModel("fagner", "aa@aa");
            gameServerModel.setUser(userModel);

            DataModel dataModel = new DataModel("life", 100, 100, 0);
            dataModel.setPlayer(userModel);
            dataModel.setGameServerModel(gameServerModel);

            Mockito.when(dataRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(dataModel));

            Assertions.assertThrows(ForbiddenException.class, () -> dataService.findDataById(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));

        }

    }

}
