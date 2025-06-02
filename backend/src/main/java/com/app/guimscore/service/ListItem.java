package com.app.guimscore.service;

import com.app.guimscore.dto.ItemDto;
import com.app.guimscore.dto.ListItemDto;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.ItemModel;
import com.app.guimscore.model.ItemsModel;
import com.app.guimscore.model.UserModel;
import com.app.guimscore.model.exceptions.ForbiddenException;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.GameServerRepository;
import com.app.guimscore.repository.ItemRepository;
import com.app.guimscore.repository.ItemsRepository;
import com.app.guimscore.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListItem {

    @Autowired
    ItemsRepository itemsRepository;
    @Autowired
    ItemRepository itemRepository;
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

    List<ListItemDto> findAllLists(UUID gameServerId, UUID userId) {

        Optional<GameServerModel> gameServerModelOptional = this.gameServerRepository.findById(gameServerId);
        Optional<UserModel> userModelOptional = this.userRepository.findById(userId);

        if (gameServerModelOptional.isEmpty() || userModelOptional.isEmpty()) {
            throw new NotFoundException("GameServer ou usuario não indentificado");
        }

        if (!gameServerModelOptional.get().getUser().equals(userModelOptional.get())) {
            throw new ForbiddenException("Acesso negado");
        }

        List<ItemsModel> itemsModelList = this.itemsRepository.findByGameServerModel(gameServerModelOptional.get());

        return itemsModelList.stream()
                .map(ListItem::convertListModelInDto)
                .toList();

    }

    ListItemDto findListById(UUID listId, UUID gameServerId, UUID userId) {

        this.validateUserAdmin(gameServerId, userId);

        Optional<ItemsModel> itemsModelOptional = this.itemsRepository.findById(listId);

        if (itemsModelOptional.isEmpty()) {
            throw new NotFoundException("Lista inexistente");
        }

        ListItemDto listItemDto = new ListItemDto();

        BeanUtils.copyProperties(itemsModelOptional.get(), listItemDto);

        return listItemDto;

    }

    void updateList(ListItemDto listItemDto, UUID listId, UUID gameServerId, UUID userId) {

        this.validateUserAdmin(gameServerId, userId);

        ItemsModel itemsModel = itemsRepository.findById(listId).get();

        BeanUtils.copyProperties(listItemDto, itemsModel);

        this.itemsRepository.save(itemsModel);

    }

    void deleteList(UUID listId, UUID gameServerId, UUID userId) {

        this.validateUserAdmin(gameServerId, userId);

        this.itemsRepository.deleteById(listId);

    }

    // Funções referentes a unidade dos itemns (Interação do player)
    void addItem(ItemDto itemDto, UUID listItemId, UUID userId, UUID gameServerId) {

        Optional<ItemsModel> itemsModelOptional = this.itemsRepository.findById(listItemId);
        Optional<UserModel> userModel = this.userRepository.findById(userId);
        Optional<GameServerModel> gameServerModelOptional = this.gameServerRepository.findById(gameServerId);

        if (userModel.isEmpty() || gameServerModelOptional.isEmpty() || itemsModelOptional.isEmpty()) {
            throw new NotFoundException("Usuario, GameServer ou Lista de items não indentificada");
        }

        if (!itemsModelOptional.get().getPlayer().equals(userModel.get()) || !itemsModelOptional.get().getGameServerModel().equals(gameServerModelOptional.get())) {
            throw new ForbiddenException("Permição negada");
        }

        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDto, itemModel);

        itemModel.setListItem(itemsModelOptional.get());

        this.itemRepository.save(itemModel);
    }

    void removerItem(UUID itemId, UUID listItemId, UUID userId, UUID gameServerId) {

        Optional<ItemsModel> itemsModelOptional = this.itemsRepository.findById(listItemId);
        Optional<UserModel> userModel = this.userRepository.findById(userId);
        Optional<GameServerModel> gameServerModelOptional = this.gameServerRepository.findById(gameServerId);

        if (userModel.isEmpty() || gameServerModelOptional.isEmpty() || itemsModelOptional.isEmpty()) {
            throw new NotFoundException("Usuario, GameServer ou Lista de items não indentificada");
        }

        if (!itemsModelOptional.get().getPlayer().equals(userModel.get()) || !itemsModelOptional.get().getGameServerModel().equals(gameServerModelOptional.get())) {
            throw new ForbiddenException("Permição negada");
        }

        this.itemRepository.deleteById(itemId);

    }

    List<ItemDto> findAllItems(UUID listItemId, UUID userId, UUID gameServerId) {

        Optional<ItemsModel> itemsModelOptional = this.itemsRepository.findById(listItemId);
        Optional<UserModel> userModel = this.userRepository.findById(userId);
        Optional<GameServerModel> gameServerModelOptional = this.gameServerRepository.findById(gameServerId);

        if (userModel.isEmpty() || gameServerModelOptional.isEmpty() || itemsModelOptional.isEmpty()) {
            throw new NotFoundException("Usuario, GameServer ou Lista de items não indentificada");
        }

        if (!itemsModelOptional.get().getPlayer().equals(userModel.get()) || !itemsModelOptional.get().getGameServerModel().equals(gameServerModelOptional.get())) {
            throw new ForbiddenException("Permição negada");
        }

        List<ItemModel> itemModelList = this.itemRepository.findByListItem(itemsModelOptional.get());

        return itemModelList.stream()
                .map(ListItem::convertItemModelInDto)
                .toList();

    }

    ItemDto findItemById(UUID itemId, UUID listItemId, UUID userId, UUID gameServerId) {
        Optional<ItemsModel> itemsModelOptional = this.itemsRepository.findById(listItemId);
        Optional<UserModel> userModel = this.userRepository.findById(userId);
        Optional<GameServerModel> gameServerModelOptional = this.gameServerRepository.findById(gameServerId);

        if (userModel.isEmpty() || gameServerModelOptional.isEmpty() || itemsModelOptional.isEmpty()) {
            throw new NotFoundException("Usuario, GameServer ou Lista de items não indentificada");
        }

        if (!itemsModelOptional.get().getPlayer().equals(userModel.get()) || !itemsModelOptional.get().getGameServerModel().equals(gameServerModelOptional.get())) {
            throw new ForbiddenException("Permição negada");
        }

        Optional<ItemModel> itemModel = this.itemRepository.findById(itemId);

        if (itemModel.isEmpty()) {
            throw new NotFoundException("Item inexistente");
        }

        ItemDto itemDto = new ItemDto();

        BeanUtils.copyProperties(itemModel, itemDto);

        return itemDto;
    }

    private static ListItemDto convertListModelInDto(ItemsModel itemsModel) {
        ListItemDto listItemDto = new ListItemDto();
        BeanUtils.copyProperties(itemsModel, listItemDto);
        return listItemDto;
    }

    private static ItemDto convertItemModelInDto(ItemModel itemModel) {
        ItemDto itemDto = new ItemDto();
        BeanUtils.copyProperties(itemModel, itemDto);
        return itemDto;
    }

    private void validateUserAdmin(UUID gameServerId, UUID userId) {
        Optional<GameServerModel> gameServerModelOptional = this.gameServerRepository.findById(gameServerId);

        if (gameServerModelOptional.isEmpty()) {
            throw new NotFoundException("GameServer inexistente");
        }

        if (!gameServerModelOptional.get().getUser().getUuid().equals(userId)) {
            throw new ForbiddenException("Acesso negado");
        }
    }

}
