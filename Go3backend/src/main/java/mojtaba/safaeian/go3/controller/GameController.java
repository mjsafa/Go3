package mojtaba.safaeian.go3.controller;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.dto.GameDto;
import mojtaba.safaeian.go3.api.dto.RemotePlayerDescriptor;
import mojtaba.safaeian.go3.api.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/14/2017.
 */

@Controller
@CrossOrigin
@RequestMapping(value = "game", produces = "application/json")
public class GameController {

    private final GameService gameService;
    private final ModelMapper modelMapper;

    @Autowired
    public GameController(GameService gameService, ModelMapper modelMapper) {
        this.gameService = gameService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<GameDto> startNewGame(
            @RequestBody RemotePlayerDescriptor remotePlayerDescriptor){
        Game game = gameService.startNewGame(remotePlayerDescriptor);

        GameDto gameDto = modelMapper.map(game, GameDto.class);
        return ResponseEntity.ok(gameDto);
    }

}
