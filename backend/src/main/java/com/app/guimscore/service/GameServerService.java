package com.app.guimscore.service;

import com.app.guimscore.dto.GameServerDto;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;
import com.app.guimscore.model.exceptions.ForbiddenException;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.GameServerRepository;
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

    void deleteGameServer(UUID userId, UUID gameServerId) {
        GameServerModel gameServerModel = this.isGameServerExistAndAuthorized(userId, gameServerId);

        gameServerRepository.delete(gameServerModel);

    }

    void updateGameServer(UUID userId, UUID gameServerId, GameServerDto gameServerDto) {
        GameServerModel gameServerModel = this.isGameServerExistAndAuthorized(userId, gameServerId);

        GameServerModel gameServerModelSave = new GameServerModel();

        BeanUtils.copyProperties(gameServerDto, gameServerModel);

        this.gameServerRepository.save(gameServerModelSave);
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