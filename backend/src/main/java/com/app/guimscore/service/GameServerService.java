package com.app.guimscore.service;

import com.app.guimscore.dto.GameServerDto;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.UserModel;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.GameServerRepository;
import com.app.guimscore.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

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

}
