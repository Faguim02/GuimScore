package com.app.guimscore.service;

import com.app.guimscore.dto.PlayerDto;
import com.app.guimscore.model.DataModel;
import com.app.guimscore.model.GameServerModel;
import com.app.guimscore.model.Player;
import com.app.guimscore.model.exceptions.ForbiddenException;
import com.app.guimscore.model.exceptions.NotFoundException;
import com.app.guimscore.repository.DataRepository;
import com.app.guimscore.repository.GameServerRepository;
import com.app.guimscore.repository.PlayerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private GameServerRepository gameServerRepository;

    public PlayerDto signUp(PlayerDto player) {

        try {

            if (player.getName() == null || player.getPassword() == null || player.getGameServerId() == null) {
                throw new NotFoundException("Nome ou senha nulos");
            }

            if (Objects.equals(player.getName(), "") || Objects.equals(player.getPassword(), "")) {
                throw new NotFoundException("Nome ou senha vazios");
            }

            //player.setGameServerId(player.getGameServerId());

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(player.getPassword());
            player.setPassword(hashedPassword);

            //
            GameServerModel gameServerModel = gameServerRepository.findById(player.getGameServerId()).orElseThrow(() -> new NotFoundException("Servidor de jogo não encontrado"));
            List<DataModel> dataModelList = dataRepository.findByGameServerModel(gameServerModel);

            Player playerModel = new Player();
            playerModel.setData(dataModelList);
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

    public PlayerDto signIn(PlayerDto player) {

        try {

            Optional<Player> playerModel = playerRepository.findByName(player.getName());
            if (playerModel.isEmpty()) {
                throw new NotFoundException("Jogador não encontrado");
            }

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(player.getPassword(), playerModel.get().getPassword())) {
                throw new ForbiddenException("Senha incorreta");
            }

            PlayerDto playerDto = new PlayerDto();
            playerDto.setId(playerModel.get().getId());
            BeanUtils.copyProperties(playerModel.get(), playerDto);

            return playerDto;

        } catch (NotFoundException notFoundException) {
            throw new NotFoundException(notFoundException.getMessage());
        } catch (ForbiddenException forbiddenException) {
            throw new ForbiddenException(forbiddenException.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar jogador");
        }

    }

}
