package com.app.guimscore.view.controller;

import com.app.guimscore.dto.PlayerDto;
import com.app.guimscore.model.DataModel;
import com.app.guimscore.model.ItemsModel;
import com.app.guimscore.service.DataService;
import com.app.guimscore.service.PlayerService;
import com.app.guimscore.view.model.PlayerDetailsRes;
import com.app.guimscore.view.model.PlayerReqDto;
import com.app.guimscore.view.model.PlayerResDto;
import com.app.guimscore.view.model.ValueToDataReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private DataService dataService;

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody PlayerReqDto playerReqDto) {
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

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<PlayerResDto> login(@RequestBody PlayerReqDto playerReqDto) {

        PlayerDto playerDto = new PlayerDto(playerReqDto.name(), playerReqDto.password(), playerReqDto.gameServerId());

        PlayerDto playerDtoRes = this.playerService.signIn(playerDto);
        PlayerResDto playerResDto = new PlayerResDto(playerDtoRes.getId(), playerDtoRes.getName(), playerDtoRes.getDateOfBirth(), playerDtoRes.getGameServerId());

        return ResponseEntity.ok(playerResDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDetailsRes> findPlayerById(@PathVariable("id") UUID id) {
        PlayerDto playerDto = this.playerService.findById(id);
        PlayerDetailsRes playerDetailsRes = new PlayerDetailsRes(playerDto.getId(), playerDto.getName(), playerDto.getDateOfBirth(), playerDto.getGameServerId(), playerDto.getItems(), playerDto.getData());
        //PlayerDetailsRes playerDetailsRes = new PlayerDetailsRes(UUID.randomUUID(), "playerDto.getName()", new Date(), UUID.randomUUID(), List.of(new ItemsModel()), List.of(new DataModel()));
        return ResponseEntity.ok(playerDetailsRes);
    }

    @PutMapping("/addValue")
    ResponseEntity<String> incrementData(@RequestBody ValueToDataReqDto valueToData) {

        this.dataService.addValueToData(valueToData.value(), valueToData.dataId(), valueToData.gameId(), valueToData.playerId());

        return ResponseEntity.ok("Um dado foi incrementado");

    }

    @PutMapping("/subtractValue")
    ResponseEntity<String> decrementData(@RequestBody ValueToDataReqDto valueToData) {
        this.dataService.subtractValueToData(valueToData.value(), valueToData.dataId(), valueToData.gameId(), valueToData.playerId());

        return ResponseEntity.ok("Um dado foi decrementado");
    }

}
