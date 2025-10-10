package com.app.guimscore.view.controller;

import com.app.guimscore.dto.ApiKeyDto;
import com.app.guimscore.infra.security.JwtService;
import com.app.guimscore.model.ApiKey;
import com.app.guimscore.view.model.*;
import com.app.guimscore.dto.UserDto;
import com.app.guimscore.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("signUp")
    ResponseEntity<SignUpResDto> signUp(@RequestBody SignUpReqDto signUpReq) {

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(signUpReq, userDto);

        SignUpResDto signUpRes = this.authService.signUp(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(signUpRes);
    }

    @PostMapping("signIn")
    ResponseEntity<SignInResDto> signIn(@RequestBody SignInReqDto signInReqDto) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(signInReqDto, userDto);

        SignInResDto signUpResDto = this.authService.signIn(userDto);

        return ResponseEntity.status(HttpStatus.OK).body(signUpResDto);
    }

    @PostMapping("apiKey")
    ResponseEntity<String> generateApiKey(Authentication authentication, @RequestBody ApiKeyReq apiKeyReq) {

        UUID userId = jwtService.getUserIdByToken(authentication);
        String apiKey = this.authService.createApiKey(userId, apiKeyReq.name());

        return ResponseEntity.status(HttpStatus.CREATED).body(apiKey);

    }

    @GetMapping("apiKey")
    ResponseEntity<List<ApiKeyRes>> getApiKeys(Authentication authentication) {

        UUID userId = jwtService.getUserIdByToken(authentication);
        List<ApiKeyDto> apiKeys = this.authService.findAllApiKeysByUserId(userId);
        List<ApiKeyRes> apiKeyResList = apiKeys.stream()
                .map(apiKey -> new ApiKeyRes(apiKey.getId(), apiKey.getName(), apiKey.getCreatedData()))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(apiKeyResList);
    }

    @DeleteMapping("apiKey/{apiKeyId}")
    ResponseEntity<Void> deleteApiKey(Authentication authentication, @PathVariable UUID apiKeyId) {
        UUID userId = jwtService.getUserIdByToken(authentication);
        this.authService.deleteApiKey(userId, apiKeyId);
        return ResponseEntity.ok().build();
    }

}
