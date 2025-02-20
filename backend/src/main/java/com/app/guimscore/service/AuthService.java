package com.app.guimscore.service;

import com.app.guimscore.controller.models.SignInResDto;
import com.app.guimscore.controller.models.SignUpResDto;
import com.app.guimscore.dto.UserDto;
import com.app.guimscore.model.UserModel;
import com.app.guimscore.model.roles.UserRole;
import com.app.guimscore.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public SignUpResDto signUp(UserDto userDto) {

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);

        UserRole userRole = UserRole.ADMIN;
        String passwordEncode = new BCryptPasswordEncoder().encode(userDto.getPassword());
        userModel.setUserRole(userRole);
        userModel.setUuid(UUID.randomUUID());
        userModel.setPassword(passwordEncode);

        if (this.userRepository.existsByName(userDto.getName())) {
            throw new RuntimeException("puta que pario, já existe");
        }

        this.userRepository.save(userModel);

        return new SignUpResDto("Usuario criado com sucesso!", userDto.getName());

    }

    public SignInResDto signIn(UserDto userDto) {
        try {
            var userNamePassword = new UsernamePasswordAuthenticationToken(userDto.getName(), userDto.getPassword());
            this.authenticationManager.authenticate(userNamePassword);
        } catch (AuthenticationException exception) {
            throw new RuntimeException("Erro ao acessar a conta");
        }

        return new SignInResDto("");
    }

}
