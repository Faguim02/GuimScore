package com.app.guimscore.controller;

import com.app.guimscore.controller.models.SignInReqDto;
import com.app.guimscore.controller.models.SignInResDto;
import com.app.guimscore.controller.models.SignUpReqDto;
import com.app.guimscore.controller.models.SignUpResDto;
import com.app.guimscore.dto.UserDto;
import com.app.guimscore.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

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
}
