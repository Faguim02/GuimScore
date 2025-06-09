package com.app.guimscore.view.controller;

import com.app.guimscore.dto.ListItemDto;
import com.app.guimscore.infra.security.JwtService;
import com.app.guimscore.service.ListItem;
import com.app.guimscore.view.model.ListItemReqDto;
import com.app.guimscore.view.model.ListItemResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("list")
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

    @GetMapping()
    ResponseEntity<ListItemResDto> findListItemById(Authentication authentication,
                                                          @RequestParam("game-id") UUID gameId,
                                                          @RequestParam("listId") UUID listId
    ) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        ListItemDto listItemDto = this.listItem.findListById(listId, gameId, userId);

        ListItemResDto listItemResDto = new ListItemResDto(listItemDto.getUuid(), listItemDto.getListName());

        return ResponseEntity.ok(listItemResDto);
    }

}
