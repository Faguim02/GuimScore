package com.app.guimscore.view.controller;

import com.app.guimscore.dto.GameServerDto;
import com.app.guimscore.infra.security.JwtService;
import com.app.guimscore.service.GameServerService;
import com.app.guimscore.view.model.GameServerDetailsDto;
import com.app.guimscore.view.model.GameServerReqDto;
import com.app.guimscore.view.model.GameServerResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("game-server")
public class GameServerController {

    @Autowired
    private GameServerService gameServerService;
    @Autowired
    private JwtService jwtService;

    @PostMapping()
    ResponseEntity<String> createGameServer(@RequestBody GameServerReqDto gameServerReqDto, Authentication authentication) {

        GameServerDto gameServerDto = new GameServerDto(gameServerReqDto.nameServer(), gameServerReqDto.description());

        UUID userId = jwtService.getUserIdByToken(authentication);

        this.gameServerService.createGameServer(gameServerDto, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body("GameServer criado com sucesso");

    }

    @GetMapping()
    ResponseEntity<List<GameServerResDto>> findAllGameServer(Authentication authentication) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        List<GameServerDto> gameServerDtoList = this.gameServerService.findAllGameServers(userId);

        List<GameServerResDto> gameServerResDtoList = gameServerDtoList.stream()
                .map(game -> new GameServerResDto(game.getUuid(), game.getNameServer(), game.getDescription(), game.getDateAt()))
                .toList();

        return ResponseEntity.ok(gameServerResDtoList);

    }

    @GetMapping("{id}")
    ResponseEntity<GameServerDetailsDto> findGameServerDetails(Authentication authentication, @PathVariable("id") UUID gameId) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        GameServerDto gameServerDto = this.gameServerService.findGameServerById(userId, gameId);

        GameServerDetailsDto gameServerDetailsDto = new GameServerDetailsDto(gameServerDto.getUuid(), gameServerDto.getNameServer(), gameServerDto.getDescription(), gameServerDto.getDateAt(), null, null);

        return ResponseEntity.ok(gameServerDetailsDto);

    }

    @DeleteMapping("{id}")
    ResponseEntity<String> deleteGameServer(Authentication authentication, @PathVariable("id") UUID gameServerId) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        this.gameServerService.deleteGameServer(userId, gameServerId);

        return ResponseEntity.ok("GameServer deletado com sucesso");
    }

}
