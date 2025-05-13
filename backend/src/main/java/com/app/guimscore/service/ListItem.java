package com.app.guimscore.service;

import com.app.guimscore.dto.ListItemDto;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.ItemsModel;
import com.app.guimscore.model.UserModel;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.GameServerRepository;
import com.app.guimscore.repository.ItemsRepository;
import com.app.guimscore.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ListItem {

    @Autowired
    ItemsRepository itemsRepository;
    @Autowired
    private GameServerRepository gameServerRepository;
    @Autowired
    private UserRepository userRepository;

    void createListItem(ListItemDto listItemDto, UUID userId, UUID gameServerId) {

        Optional<UserModel> userModelOptional = this.userRepository.findById(userId);
        Optional<GameServerModel> gameServerModelOptional = this.gameServerRepository.findById(gameServerId);

        if (userModelOptional.isEmpty() || gameServerModelOptional.isEmpty()) {
            throw new NotFoundException("Nenhum usuario ou GameServer encontrado");
        }

        ItemsModel itemsModel = new ItemsModel();

        BeanUtils.copyProperties(listItemDto, itemsModel);

        this.itemsRepository.save(itemsModel);
    }

}
