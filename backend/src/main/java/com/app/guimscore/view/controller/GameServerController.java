package com.app.guimscore.view.controller;

import com.app.guimscore.dto.GameServerDto;
import com.app.guimscore.infra.security.JwtService;
import com.app.guimscore.service.GameServerService;
import com.app.guimscore.view.model.GameServerReqDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseEntity.ok("GameServer criado com sucesso");

    }


}
