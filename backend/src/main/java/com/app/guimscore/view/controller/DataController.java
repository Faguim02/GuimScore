package com.app.guimscore.view.controller;

import com.app.guimscore.dto.DataDto;
import com.app.guimscore.infra.security.JwtService;
import com.app.guimscore.service.DataService;
import com.app.guimscore.view.model.DataDetailsDto;
import com.app.guimscore.view.model.DataReqDto;
import com.app.guimscore.view.model.DataResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/data")
public class DataController {

    @Autowired
    private DataService dataService;
    @Autowired
    private JwtService jwtService;

    @PostMapping()
    ResponseEntity<String> createData(@RequestBody DataReqDto dataReqDto,
                                      Authentication authentication,
                                      @RequestParam("game-id") UUID gameId
    ) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        DataDto dataDto = new DataDto(dataReqDto.nameData(), dataReqDto.value(), dataReqDto.maxValue(), dataReqDto.minValue());

        this.dataService.createData(dataDto, userId, gameId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Um novo dado foi criado");

    }

    @GetMapping()
    ResponseEntity<List<DataResDto>> findAllData(Authentication authentication, @RequestParam("game-id") UUID gameId) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        List<DataDto> dataDtoList = this.dataService.findAllData(userId, gameId);

        List<DataResDto> dataResDtoList = dataDtoList.stream()
                .map(data -> new DataResDto(data.getUuid(), data.getNameData()))
                .toList();

        return ResponseEntity.ok(dataResDtoList);
    }

    @GetMapping("details")
    ResponseEntity<DataDetailsDto> findDataById(Authentication authentication, @RequestParam("game-id") UUID gameId, @RequestParam("data-id") UUID dataId) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        DataDto dataDto = this.dataService.findDataById(dataId, userId, gameId);

        DataDetailsDto dataDetailsDto = new DataDetailsDto(dataDto.getUuid(), dataDto.getNameData(), dataDto.getValue(), dataDto.getMaxValue(), dataDto.getMinValue());

        return ResponseEntity.ok(dataDetailsDto);

    }

    @DeleteMapping()
    ResponseEntity<String> deleteData(Authentication authentication, @RequestParam("game-id") UUID gameId, @RequestParam("data-id") UUID dataId) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        this.dataService.deleteDataById(dataId, userId, gameId);

        return ResponseEntity.ok("Um dado foi deletado");

    }

    @PutMapping()
    ResponseEntity<String> updateData(Authentication authentication,
                                      @RequestBody DataReqDto dataReqDto,
                                      @RequestParam("game-id") UUID gameId,
                                      @RequestParam("data-id") UUID dataId
    ) {

        UUID userId = this.jwtService.getUserIdByToken(authentication);

        DataDto dataDto = new DataDto(dataReqDto.nameData(), dataReqDto.value(), dataReqDto.maxValue(), dataReqDto.minValue());

        this.dataService.updateData(dataId, userId, gameId, dataDto);

        return ResponseEntity.ok("Um dado foi atualizado");

    }

}
