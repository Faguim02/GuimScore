package com.app.guimscore.service;

import com.app.guimscore.dto.PlayerDto;
import com.app.guimscore.model.Player;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.PlayerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public PlayerDto signUp(PlayerDto player, UUID gameServerId) {

        try {

            if (player.getName() == null || player.getPassword() == null) {
                throw new NotFoundException("Nome ou senha nulos");
            }

            if (Objects.equals(player.getName(), "") || Objects.equals(player.getPassword(), "")) {
                throw new NotFoundException("Nome ou senha vazios");
            }

            player.setGameServerId(gameServerId);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(player.getPassword());
            player.setPassword(hashedPassword);

            Player playerModel = new Player();

            BeanUtils.copyProperties(player, playerModel);

            playerRepository.save(playerModel);

            return player;

        } catch (NotFoundException notFoundException) {
            throw new NotFoundException(notFoundException.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar jogador");
        }

    }

    public PlayerDto findByName(PlayerDto player) {

        try {

            Optional<Player> playerModel = Optional.ofNullable(playerRepository.findByName(player.getName()));
            if (playerModel.isEmpty()) {
                throw new NotFoundException("Jogador não encontrado");
            }

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(player.getPassword(), playerModel.get().getPassword())) {
                throw new NotFoundException("Senha incorreta");
            }

            PlayerDto playerDto = new PlayerDto();
            playerDto.setId(playerModel.get().getId());
            BeanUtils.copyProperties(playerModel.get(), playerDto);

            return playerDto;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar jogador");
        }

    }

}
