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

    public void createData(DataDto dataDto, UUID userId, UUID gameServerId) {

        try{

            if (dataDto == null) {
                throw new NotFoundException("Data inexistente");
            }

            DataModel dataModel = new DataModel();

            BeanUtils.copyProperties(dataDto, dataModel);

            Optional<UserModel> userModel = this.userRepository.findById(userId);
            Optional<GameServerModel> gameServerModel = this.gameServerRepository.findById(gameServerId);

            if (userModel.isEmpty() && gameServerModel.isEmpty()) {
                throw new NotFoundException("usuario ou gameServer inexistente");
            }

            if (!gameServerModel.get().getUser().getUuid().equals(userModel.get().getUuid())) {
                throw new ForbiddenException("Acesso negado");
            }

            dataModel.setGameServerModel(gameServerModel.get());
            dataModel.setPlayer(userModel.get());

            this.dataRepository.save(dataModel);

        } catch (NotFoundException notFoundException) {
            throw new NotFoundException(notFoundException.getMessage());
        } catch (ForbiddenException forbiddenException) {
            throw new ForbiddenException(forbiddenException.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public List<DataDto> findAllData(UUID userId, UUID gameServerId) {

        try{

            Optional<GameServerModel> gameServerModel = this.gameServerRepository.findById(gameServerId);

            if (gameServerModel.isEmpty()) {
                throw new NotFoundException("GameServer inexistente");
            }

            if (!gameServerModel.get().getUser().getUuid().equals(userId)) {
                throw new ForbiddenException("Não authorizado");
            }

            List<DataModel> dataModelList = this.dataRepository.findByGameServerModel(gameServerModel.get());

            return dataModelList.stream()
                    .map(DataService::convertDataModelToDto)
                    .toList();

        } catch (NotFoundException notFoundException) {
            throw new NotFoundException(notFoundException.getMessage());
        } catch (ForbiddenException forbiddenException) {
            throw new ForbiddenException(forbiddenException.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public DataDto findDataById(UUID dataId, UUID userId, UUID gameServerId) {

        try {

            DataModel dataModel = this.validateAccessToData(dataId, userId, gameServerId);

            DataDto dataDto = new DataDto();

            BeanUtils.copyProperties(dataModel, dataDto);

            return dataDto;

        } catch (NotFoundException notFoundException) {
            throw new NotFoundException(notFoundException.getMessage());
        } catch (ForbiddenException forbiddenException) {
            throw new ForbiddenException(forbiddenException.getMessage());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteDataById(UUID dataId, UUID userId, UUID gameServerId) {
        try {

            DataModel dataModel = this.validateAccessToData(dataId, userId, gameServerId);

            this.dataRepository.delete(dataModel);

        } catch (NotFoundException notFoundException) {
            throw new NotFoundException(notFoundException.getMessage());
        } catch (ForbiddenException forbiddenException) {
            throw new ForbiddenException(forbiddenException.getMessage());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateData(UUID dataId, UUID userId, UUID gameServerId, DataDto dataDto) {
        try {

            DataModel dataModel = this.validateAccessToData(dataId, userId, gameServerId);

            dataModel.setValue(dataDto.getValue() != null ? dataDto.getValue() : dataModel.getValue());
            dataModel.setMaxValue(dataDto.getMaxValue() != null ? dataDto.getMaxValue(): dataModel.getMaxValue());
            dataModel.setMinValue(dataDto.getValue() != null ? dataDto.getMinValue() : dataModel.getMinValue());
            dataModel.setNameData(dataDto.getNameData().isEmpty() ? dataModel.getNameData() : dataDto.getNameData());

            this.dataRepository.save(dataModel);

        } catch (NotFoundException notFoundException) {
            throw new NotFoundException(notFoundException.getMessage());
        } catch (ForbiddenException forbiddenException) {
            throw new ForbiddenException(forbiddenException.getMessage());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DataModel validateAccessToData(UUID dataId, UUID userId, UUID gameServerId) {
        Optional<DataModel> dataModel = this.dataRepository.findById(dataId);

        if (dataModel.isEmpty()) {
            throw new NotFoundException("Data inexistente");
        }

        if (!dataModel.get().getPlayer().getUuid().equals(userId) || !dataModel.get().getGameServerModel().getUuid().equals(gameServerId)) {
            throw new ForbiddenException("Acesso negado");
        }

        return dataModel.get();
    }

    private static DataDto convertDataModelToDto(DataModel dataModel) {
        DataDto dataDto = new DataDto();
        BeanUtils.copyProperties(dataModel, dataDto);
        return dataDto;
    }

}
