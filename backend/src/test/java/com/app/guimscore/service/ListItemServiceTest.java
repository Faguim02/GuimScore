package com.app.guimscore.service;

import com.app.guimscore.dto.ItemDto;
import com.app.guimscore.dto.ListItemDto;
import com.app.guimscore.model.*;
import com.app.guimscore.model.exceptions.ForbiddenException;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("List Item Service Tests")
public class ListItemServiceTest {

    @InjectMocks
    private ListItem listItem;

    @Mock
    private ItemsRepository itemsRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private GameServerRepository gameServerRepository;

    @Mock
    private UserRepository userRepository;

    // =============== createListItem ===============
    @Nested
    @DisplayName("Function 'createListItem'")
    class CreateListItemTests {

        @Test
        @DisplayName("Should create list item successfully")
        void shouldCreateListItemSuccessfully() {
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(userModel);

            ListItemDto dto = new ListItemDto();
            dto.setUuid(UUID.randomUUID());

            when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));
            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));

            listItem.createListItem(dto, userId, gameServerId);

            verify(itemsRepository, times(1)).save(any(ItemsModel.class));
        }

        @Test
        @DisplayName("Should throw NotFoundException when user or game server not found")
        void shouldThrowNotFoundException_WhenUserOrGameServerNotFound() {
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            when(userRepository.findById(userId)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> listItem.createListItem(new ListItemDto(), userId, gameServerId))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("Nenhum usuario ou GameServer encontrado");
        }
    }

    // =============== findAllLists ===============
    @Nested
    @DisplayName("Function 'findAllLists'")
    class FindAllListsTests {

        @Test
        @DisplayName("Should return all lists for game server")
        void shouldReturnAllListsForGameServer() {
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(userModel);

            ItemsModel itemsModel = new ItemsModel(UUID.randomUUID(), "Inventory List");

            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));
            when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));
            when(itemsRepository.findByGameServerModel(gameServerModel)).thenReturn(Collections.singletonList(itemsModel));

            List<ListItemDto> result = listItem.findAllLists(gameServerId, userId);

            assertThat(result).isNotEmpty();
        }

        @Test
        @DisplayName("Should throw ForbiddenException when user is not admin")
        void shouldThrowForbiddenException_WhenUserIsNotAdmin() {
            UUID userId = UUID.randomUUID();
            UUID otherUserId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            UserModel otherUser = new UserModel("Other", "other@email.com");
            otherUser.setUuid(otherUserId);

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(otherUser);

            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));
            when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));

            assertThatThrownBy(() -> listItem.findAllLists(gameServerId, userId))
                    .isInstanceOf(ForbiddenException.class)
                    .hasMessage("Acesso negado");
        }
    }

    // =============== findListById ===============
    @Nested
    @DisplayName("Function 'findListById'")
    class FindListByIdTests {

        @Test
        @DisplayName("Should return list item by ID")
        void shouldReturnListItemById() {
            UUID listItemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(userModel);

            ItemsModel itemsModel = new ItemsModel(listItemId, "Inventory List");
            itemsModel.setPlayer(userModel);
            itemsModel.setGameServerModel(gameServerModel);

            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));
            when(itemsRepository.findById(listItemId)).thenReturn(Optional.of(itemsModel));

            ListItemDto result = listItem.findListById(listItemId, gameServerId, userId);

            verify(itemsRepository, times(1)).findById(listItemId);
        }

        @Test
        @DisplayName("Should throw NotFoundException when list does not exist")
        void shouldThrowNotFoundException_WhenListDoesNotExist() {
            UUID listItemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> listItem.findListById(listItemId, gameServerId, userId))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("GameServer inexistente");
        }
    }

    // =============== updateList ===============
    @Nested
    @DisplayName("Function 'updateList'")
    class UpdateListTests {

        @Test
        @DisplayName("Should update list item successfully")
        void shouldUpdateListItemSuccessfully() {
            UUID listItemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(userModel);

            ItemsModel itemsModel = new ItemsModel(listItemId, "Inventory List");
            itemsModel.setPlayer(userModel);
            itemsModel.setGameServerModel(gameServerModel);

            ListItemDto dto = new ListItemDto();
            dto.setUuid(listItemId);
            dto.setListName("Updated Inventory");

            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));
            when(itemsRepository.findById(listItemId)).thenReturn(Optional.of(itemsModel));

            listItem.updateList(dto, listItemId, gameServerId, userId);

            verify(itemsRepository, times(1)).save(itemsModel);
        }

        @Test
        @DisplayName("Should throw NotFoundException when list does not exist")
        void shouldThrowNotFoundException_WhenListDoesNotExist() {
            UUID listItemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            lenient().when(itemsRepository.findById(listItemId)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> listItem.updateList(new ListItemDto(), listItemId, gameServerId, userId))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("GameServer inexistente");
        }
    }

    // =============== deleteList ===============
    @Nested
    @DisplayName("Function 'deleteList'")
    class DeleteListTests {

        @Test
        @DisplayName("Should delete list item successfully")
        void shouldDeleteListItemSuccessfully() {
            UUID listItemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(userModel); // Garante que o usuário é dono do servidor

            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));

            listItem.deleteList(listItemId, gameServerId, userId);

            verify(itemsRepository, times(1)).deleteById(listItemId);
        }

        @Test
        @DisplayName("Should throw NotFoundException when list does not exist")
        void shouldThrowNotFoundException_WhenListDoesNotExist() {
            UUID listItemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(new UserModel("User", "user@email.com"));

            // Garantir que validateUserAdmin() funcione
            lenient().when(gameServerRepository.findById(gameServerId))
                    .thenReturn(Optional.of(gameServerModel));

            // Este é o mock importante para o teste
            lenient().when(itemsRepository.findById(listItemId))
                    .thenReturn(Optional.empty());

            // Executar e validar exceção
            Assertions.assertThrows(NotFoundException.class, () ->
                    listItem.deleteList(listItemId, userId, gameServerId)
            );
        }
    }

    // =============== addItem ===============
    @Nested
    @DisplayName("Function 'addItem'")
    class AddItemTests {

        @Test
        @DisplayName("Should add item to list successfully")
        void shouldAddItemToListSuccessfully() {
            UUID listItemId = UUID.randomUUID();
            UUID itemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(userModel);

            ItemsModel itemsModel = new ItemsModel(listItemId, "Inventory List");
            itemsModel.setPlayer(userModel);
            itemsModel.setGameServerModel(gameServerModel);

            ItemDto dto = new ItemDto();
            dto.setItemName("Espada");
            dto.setItemDescription("Arma afiada");

            when(itemsRepository.findById(listItemId)).thenReturn(Optional.of(itemsModel));
            when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));
            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));

            listItem.addItem(dto, listItemId, userId, gameServerId);

            verify(itemRepository, times(1)).save(any(ItemModel.class));
        }

        @Test
        @DisplayName("Should throw ForbiddenException when adding without permission")
        void shouldThrowForbiddenException_WhenAddingWithoutPermission() {
            UUID listItemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            UserModel otherUser = new UserModel("Other", "other@email.com");
            otherUser.setUuid(UUID.randomUUID());

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(otherUser);

            ItemsModel itemsModel = new ItemsModel(listItemId, "Inventory List");
            itemsModel.setPlayer(otherUser);
            itemsModel.setGameServerModel(gameServerModel);

            ItemDto dto = new ItemDto();
            dto.setItemName("Armadura");

            when(itemsRepository.findById(listItemId)).thenReturn(Optional.of(itemsModel));
            when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));
            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));

            assertThatThrownBy(() -> listItem.addItem(dto, listItemId, userId, gameServerId))
                    .isInstanceOf(ForbiddenException.class)
                    .hasMessage("Permição negada");
        }
    }

    // =============== removerItem ===============
    @Nested
    @DisplayName("Function 'removerItem'")
    class RemoveItemTests {

        @Test
        @DisplayName("Should remove item successfully")
        void shouldRemoveItemSuccessfully() {
            UUID listItemId = UUID.randomUUID();
            UUID itemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(userModel);

            ItemsModel itemsModel = new ItemsModel(listItemId, "Inventory List");
            itemsModel.setPlayer(userModel);
            itemsModel.setGameServerModel(gameServerModel);

            when(itemsRepository.findById(listItemId)).thenReturn(Optional.of(itemsModel));
            when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));
            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));

            listItem.removerItem(itemId, listItemId, userId, gameServerId);

            verify(itemRepository, times(1)).deleteById(itemId);
        }
    }

    // =============== findAllItems ===============
    @Nested
    @DisplayName("Function 'findAllItems'")
    class FindAllItemsTests {

        @Test
        @DisplayName("Should return all items from list")
        void shouldReturnAllItemsFromList() {
            UUID listItemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(userModel);

            ItemsModel itemsModel = new ItemsModel(listItemId, "Inventory List");
            itemsModel.setPlayer(userModel);
            itemsModel.setGameServerModel(gameServerModel);

            ItemModel itemModel = new ItemModel("Espada", "Arma afiada");
            itemModel.setUuid(UUID.randomUUID());
            itemModel.setListItem(itemsModel);

            when(itemsRepository.findById(listItemId)).thenReturn(Optional.of(itemsModel));
            when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));
            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));
            when(itemRepository.findByListItem(itemsModel)).thenReturn(Collections.singletonList(itemModel));

            List<ItemDto> result = listItem.findAllItems(listItemId, userId, gameServerId);

            assertThat(result).isNotEmpty();
        }
    }

    // =============== findItemById ===============
    @Nested
    @DisplayName("Function 'findItemById'")
    class FindItemByIdTests {

        @Test
        @DisplayName("Should return item by ID")
        void shouldReturnItemById() {
            UUID listItemId = UUID.randomUUID();
            UUID itemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(userModel);

            ItemsModel itemsModel = new ItemsModel(listItemId, "Inventory List");
            itemsModel.setPlayer(userModel);
            itemsModel.setGameServerModel(gameServerModel);

            ItemModel itemModel = new ItemModel("Espada", "Arma afiada");
            itemModel.setUuid(itemId);
            itemModel.setListItem(itemsModel);

            when(itemsRepository.findById(listItemId)).thenReturn(Optional.of(itemsModel));
            when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));
            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));
            when(itemRepository.findById(itemId)).thenReturn(Optional.of(itemModel));

            ItemDto result = listItem.findItemById(itemId, listItemId, userId, gameServerId);

            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("Should throw NotFoundException when item does not exist")
        void shouldThrowNotFoundException_WhenItemDoesNotExist() {
            UUID listItemId = UUID.randomUUID();
            UUID itemId = UUID.randomUUID();
            UUID userId = UUID.randomUUID();
            UUID gameServerId = UUID.randomUUID();

            UserModel userModel = new UserModel("User", "user@email.com");
            userModel.setUuid(userId);

            GameServerModel gameServerModel = new GameServerModel("server1", "desc");
            gameServerModel.setUuid(gameServerId);
            gameServerModel.setUser(userModel);

            ItemsModel itemsModel = new ItemsModel(listItemId, "Inventory List");
            itemsModel.setPlayer(userModel);
            itemsModel.setGameServerModel(gameServerModel);

            when(itemsRepository.findById(listItemId)).thenReturn(Optional.of(itemsModel));
            when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));
            when(gameServerRepository.findById(gameServerId)).thenReturn(Optional.of(gameServerModel));
            when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> listItem.findItemById(itemId, listItemId, userId, gameServerId))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("Item inexistente");
        }
    }
}