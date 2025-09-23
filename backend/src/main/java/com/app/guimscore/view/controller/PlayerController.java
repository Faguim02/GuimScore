package com.app.guimscore.view.controller;

import com.app.guimscore.dto.PlayerDto;
import com.app.guimscore.service.PlayerService;
import com.app.guimscore.view.model.PlayerReqDto;
import com.app.guimscore.view.model.PlayerResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/signup")
    public void signUp(@RequestBody PlayerReqDto playerReqDto) {
        // Implementation for player sign-up

        PlayerDto playerDto = new PlayerDto(playerReqDto.name(), playerReqDto.password(), playerReqDto.gameServerId());
        if (playerReqDto.dateOfBirth() != null) {
            // Convert String to Date (assuming format is correct)
            try {
                SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(playerReqDto.dateOfBirth());
                playerDto.setDateOfBirth(date);
            } catch (java.text.ParseException e) {
                // Handle parse exception
                e.printStackTrace();
            }
        }

        this.playerService.signUp(playerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<PlayerResDto> login(@RequestBody PlayerReqDto playerReqDto) {

        PlayerDto playerDto = new PlayerDto(playerReqDto.name(), playerReqDto.password(), playerReqDto.gameServerId());

        PlayerDto playerDtoRes = this.playerService.signIn(playerDto);
        PlayerResDto playerResDto = new PlayerResDto(playerDtoRes.getId(), playerDtoRes.getName(), playerDtoRes.getDateOfBirth(), playerDtoRes.getGameServerId());

        return ResponseEntity.ok(playerResDto);
    }

}
