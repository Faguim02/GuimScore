package com.app.guimscore.service;

import com.app.guimscore.dto.DataDto;
import com.app.guimscore.dto.GameServerDto;
import com.app.guimscore.model.DataModel;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.DataRepository;
import com.app.guimscore.repository.GameServerRepository;
import com.app.guimscore.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameServerRepository gameServerRepository;

    void createData(DataDto dataDto, UUID userId, UUID gameServerId) {

        try{

            DataModel dataModel = new DataModel();

            BeanUtils.copyProperties(dataDto, dataModel);

            Optional<UserModel> userModel = this.userRepository.findById(userId);
            Optional<GameServerModel> gameServerModel = this.gameServerRepository.findById(gameServerId);

            if (userModel.isEmpty() && gameServerModel.isEmpty()) {
                throw new NotFoundException("usuario ou gameServer inexistente");
            }

            dataModel.setGameServerModel(gameServerModel.get());
            dataModel.setPlayer(userModel.get());

        } catch (NotFoundException notFoundException) {
            throw new NotFoundException(notFoundException.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    List<DataDto> findAllDatas(UUID userId, UUID gameServerId) {

        try{

            Optional<GameServerModel> gameServerModel = this.gameServerRepository.findById(gameServerId);

            List<DataModel> dataModelList = this.dataRepository.findByGameServerModel(gameServerModel.get());

            return dataModelList.stream()
                    .map(DataService::convertDataModelToDto)
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static DataDto convertDataModelToDto(DataModel dataModel) {
        DataDto dataDto = new DataDto();
        BeanUtils.copyProperties(dataModel, dataDto);
        return dataDto;
    }

}
