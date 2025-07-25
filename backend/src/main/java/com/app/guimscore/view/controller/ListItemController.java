package com.app.guimscore.view.controller;

import com.app.guimscore.dto.ItemDto;
import com.app.guimscore.dto.ListItemDto;
import com.app.guimscore.infra.security.JwtService;
import com.app.guimscore.service.ListItem;
import com.app.guimscore.view.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/list")
public class ListItemController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private ListItem listItem;

    @PostMapping()
    ResponseEntity<String> createListItem(Authentication authentication,
                                          @RequestBody ListItemReqDto listItemReqDto,
                                          @RequestParam("game-id") UUID gameId
                                          ) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        ListItemDto listItemDto = new ListItemDto(listItemReqDto.listName());

        this.listItem.createListItem(listItemDto, userId, gameId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Lista de items criada");

    }

    @GetMapping()
    ResponseEntity<List<ListItemResDto>> findAllListItem(Authentication authentication, @RequestParam("game-id") UUID gameId) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        List<ListItemDto> listItemDto = this.listItem.findAllLists(gameId, userId);

        List<ListItemResDto> listItemResDto = listItemDto.stream()
                .map(list -> new ListItemResDto(list.getUuid(), list.getListName()))
                .toList();

        return ResponseEntity.ok(listItemResDto);
    }

    @GetMapping("details")
    ResponseEntity<ListItemResDto> findListItemById(Authentication authentication,
                                                          @RequestParam("game-id") UUID gameId,
                                                          @RequestParam("listId") UUID listId
    ) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        ListItemDto listItemDto = this.listItem.findListById(listId, gameId, userId);

        ListItemResDto listItemResDto = new ListItemResDto(listItemDto.getUuid(), listItemDto.getListName());

        return ResponseEntity.ok(listItemResDto);
    }

    @DeleteMapping()
    ResponseEntity<String> deleteList(Authentication authentication,
                                      @RequestParam("game-id") UUID gameId,
                                      @RequestParam("list-id") UUID listId
    ) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        this.listItem.deleteList(listId, gameId, userId);

        return ResponseEntity.ok("Lista deletada com sucesso!");

    }

    @PutMapping()
    ResponseEntity<String> updateList(Authentication authentication,
                                      @RequestBody ListItemReqDto listItemReqDto,
                                      @RequestParam("game-id") UUID gameId,
                                      @RequestParam("list-id") UUID listId
    ) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        ListItemDto listItemDto = new ListItemDto(listItemReqDto.listName());

        this.listItem.updateList(listItemDto, listId, gameId, userId);

        return ResponseEntity.ok("Lista Autalizada com sucesso");

    }

    // METÓDOS DESTINADOS PARA USUARIOS/PLAYERS
    @PostMapping("/items")
    public ResponseEntity<String> addItem(
            Authentication authentication,
            @RequestBody ItemReqDto itemReqDto,
            @RequestParam("list-id") UUID listItemId,
            @RequestParam("game-id") UUID gameServerId
    ) {
        UUID userId = this.jwtService.getUserIdByToken(authentication);

        ItemDto itemDto = new ItemDto(itemReqDto.itemName(), itemReqDto.itemDescription());

        this.listItem.addItem(itemDto, listItemId, userId, gameServerId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Item adicionado com sucesso");
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<String> removeItem(
            Authentication authentication,
            @PathVariable("itemId") UUID itemId,
            @RequestParam("list-id") UUID listItemId,
            @RequestParam("game-id") UUID gameServerId
    ) {
        UUID userId = this.jwtService.getUserIdByToken(authentication);

        this.listItem.removerItem(itemId, listItemId, userId, gameServerId);

        return ResponseEntity.ok("Item removido com sucesso");
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemResDto>> getAllItems(
            Authentication authentication,
            @RequestParam("list-id") UUID listItemId,
            @RequestParam("game-id") UUID gameServerId
    ) {
        UUID userId = this.jwtService.getUserIdByToken(authentication);

        List<ItemDto> items = this.listItem.findAllItems(listItemId, userId, gameServerId);

        List<ItemResDto> resDtos = items.stream()
                .map(i -> new ItemResDto(i.getUuid(), i.getItemName()))
                .toList();

        return ResponseEntity.ok(resDtos);
    }

    @GetMapping("/items/details/{itemId}")
    public ResponseEntity<ItemDetailsResDto> getItemById(
            Authentication authentication,
            @PathVariable("itemId") UUID itemId,
            @RequestParam("list-id") UUID listItemId,
            @RequestParam("game-id") UUID gameServerId
    ) {
        UUID userId = this.jwtService.getUserIdByToken(authentication);

        ItemDto itemDto = this.listItem.findItemById(itemId, listItemId, userId, gameServerId);

        ItemDetailsResDto resDto = new ItemDetailsResDto(
                itemDto.getUuid(),
                itemDto.getItemName(),
                itemDto.getItemDescription()
        );

        return ResponseEntity.ok(resDto);
    }

}
