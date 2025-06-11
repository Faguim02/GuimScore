package com.app.guimscore.service;

import com.app.guimscore.model.exceptions.UnprocessableEntityException;
import com.app.guimscore.view.model.SignInResDto;
import com.app.guimscore.view.model.SignUpResDto;
import com.app.guimscore.dto.UserDto;
import com.app.guimscore.infra.security.JwtService;
import com.app.guimscore.model.UserModel;
import com.app.guimscore.model.exceptions.ConflictException;
import com.app.guimscore.model.exceptions.ForbiddenException;
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
    @Autowired
    private JwtService jwtService;

    public SignUpResDto signUp(UserDto userDto) {

        long amountUsers = this.userRepository.count();

        if (amountUsers >= 10) {
            throw new UnprocessableEntityException("Limite de usuarios atingidos");
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);

        UserRole userRole = UserRole.ADMIN;
        String passwordEncode = new BCryptPasswordEncoder().encode(userDto.getPassword());
        userModel.setUserRole(userRole);
        userModel.setUuid(UUID.randomUUID());
        userModel.setPassword(passwordEncode);

        if (this.userRepository.existsByName(userDto.getName())) {
            throw new ConflictException("Já existe um usuario com esse nome");
        }

        this.userRepository.save(userModel);

        return new SignUpResDto("Usuario criado com sucesso!", userDto.getName());

    }

    public SignInResDto signIn(UserDto userDto) {
        try {
            var userNamePassword = new UsernamePasswordAuthenticationToken(userDto.getName(), userDto.getPassword());
            var auth = this.authenticationManager.authenticate(userNamePassword);

            return new SignInResDto(this.jwtService.generateToken((UserModel) auth.getPrincipal()));
        } catch (AuthenticationException exception) {
            throw new ForbiddenException("username ou senha incorreta");
        }
    }

}
