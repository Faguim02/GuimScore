package com.app.guimscore.service;

import com.app.guimscore.dto.GameServerDto;
import com.app.guimscore.model.DataModel;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.ItemsModel;
import com.app.guimscore.model.UserModel;
import com.app.guimscore.model.exceptions.ConflictException;
import com.app.guimscore.model.exceptions.ForbiddenException;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.DataRepository;
import com.app.guimscore.repository.GameServerRepository;
import com.app.guimscore.repository.ItemsRepository;
import com.app.guimscore.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameServerService {

    @Autowired
    private GameServerRepository gameServerRepository;
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private UserRepository userRepository;

    public void createGameServer(GameServerDto gameServerDto, UUID userId) {

        try {
            Optional<UserModel> userModel = this.userRepository.findById(userId);

            if (userModel.isEmpty()) {
                throw new NotFoundException("Usuario inexistente");
            }

            GameServerModel gameServerModel = new GameServerModel();

            BeanUtils.copyProperties(gameServerDto, gameServerModel);

            gameServerModel.setUser(userModel.get());

            gameServerRepository.save(gameServerModel);

        } catch (NotFoundException notFoundException) {
            throw new NotFoundException(notFoundException.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<GameServerDto> findAllGameServers(UUID userId) {
        try{
            Optional<UserModel> userModel = this.userRepository.findById(userId);

            if (userModel.isEmpty()) {
                throw new NotFoundException("Usuario inexistente");
            }

            List<GameServerModel> gameServerModels = this.gameServerRepository.findByUser(userModel.get());

            return gameServerModels.stream()
                    .map(GameServerService::convertGameServerModelToDto)
                    .toList();

        } catch (NotFoundException notFoundException) {
            throw new NotFoundException(notFoundException.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GameServerDto findGameServerById(UUID userId, UUID gameServerId) {
        try {

            GameServerModel gameServerModel = this.isGameServerExistAndAuthorized(userId, gameServerId);

            return convertGameServerModelToDto(gameServerModel);

        } catch (ForbiddenException forbiddenException) {
          throw new ForbiddenException(forbiddenException.getMessage());
        } catch (NotFoundException notFoundException) {
            throw new NotFoundException(notFoundException.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteGameServer(UUID userId, UUID gameServerId) {
        GameServerModel gameServerModel = this.isGameServerExistAndAuthorized(userId, gameServerId);

        List<DataModel> dataModelList = this.dataRepository.findByGameServerModel(gameServerModel);
        List<ItemsModel> itemsModelList = this.itemsRepository.findByGameServerModel(gameServerModel);

        if (!dataModelList.isEmpty() || !itemsModelList.isEmpty()) {
            throw  new ConflictException("Deseja deletar o GameServer com todos os dados dentro?");
        }

        gameServerRepository.delete(gameServerModel);

    }

    public void deleteGameServerFull(UUID userId, UUID gameServerId) {
        GameServerModel gameServerModel = this.isGameServerExistAndAuthorized(userId, gameServerId);

        this.itemsRepository.deleteByGameServerModel(gameServerModel);
        this.dataRepository.deleteByGameServerModel(gameServerModel);
    }

    public void updateGameServer(UUID userId, UUID gameServerId, GameServerDto gameServerDto) {
        GameServerModel gameServerModel = this.isGameServerExistAndAuthorized(userId, gameServerId);

        gameServerModel.setNameServer(gameServerDto.getNameServer().isEmpty() ? gameServerModel.getNameServer() : gameServerDto.getNameServer());
        gameServerModel.setDescription(gameServerDto.getDescription().isEmpty() ? gameServerModel.getDescription() : gameServerDto.getDescription());

        this.gameServerRepository.save(gameServerModel);
    }

    private GameServerModel isGameServerExistAndAuthorized(UUID userId, UUID gameServerId) {
        Optional<GameServerModel> gameServerModel = this.gameServerRepository.findById(gameServerId);

        if (gameServerModel.isEmpty()) {
            throw new NotFoundException("GameServer não encontrado");
        }

        if (!gameServerModel.get().getUser().getUuid().equals(userId)) {
            throw new ForbiddenException("Não authorizado!");
        }

        return gameServerModel.get();
    }

    private static GameServerDto convertGameServerModelToDto(GameServerModel gameServerModel) {
        GameServerDto gameServerDto = new GameServerDto();
        BeanUtils.copyProperties(gameServerModel, gameServerDto);
        return gameServerDto;
    }
}