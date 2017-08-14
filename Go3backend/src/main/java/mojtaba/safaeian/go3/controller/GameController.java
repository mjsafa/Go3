package mojtaba.safaeian.go3.controller;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.dto.Answer;
import mojtaba.safaeian.go3.api.dto.GameDto;
import mojtaba.safaeian.go3.api.dto.NewGameRequest;
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
@RequestMapping(
        value = "game",
        produces = "application/json", consumes =
        "application/json")
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
            @RequestBody NewGameRequest newGameRequest) {
        Game game = gameService.startNewGame(new Answer(newGameRequest.getFirstNumber()),
                newGameRequest.getRemotePlayerDescriptor());

        GameDto gameDto = modelMapper.map(game, GameDto.class);
        return ResponseEntity.ok(gameDto);
    }

    @PostMapping("answers")
    public ResponseEntity addAnswer(Answer answer) {
        gameService.addAnswer(answer);
        return null;
    }
}
