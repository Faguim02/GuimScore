package com.app.guimscore.view.controller;

import com.app.guimscore.dto.DataDto;
import com.app.guimscore.infra.security.JwtService;
import com.app.guimscore.service.DataService;
import com.app.guimscore.view.model.DataReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("data")
public class DataController {

    @Autowired
    private DataService dataService;
    @Autowired
    private JwtService jwtService;

    @PostMapping()
    ResponseEntity<String> createData(@RequestBody DataReqDto dataReqDto,
                                      Authentication authentication,
                                      @RequestParam("game-id")UUID gameId
    ) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        DataDto dataDto = new DataDto(dataReqDto.nameData(), dataReqDto.value(), dataReqDto.maxValue(), dataReqDto.minValue());

        this.dataService.createData(dataDto, userId, gameId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Um novo dado foi criado");

    }

}
