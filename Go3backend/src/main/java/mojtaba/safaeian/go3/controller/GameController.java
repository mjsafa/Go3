package mojtaba.safaeian.go3.controller;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.domain.GameRunnerStatus;
import mojtaba.safaeian.go3.api.dto.Answer;
import mojtaba.safaeian.go3.api.dto.AnswerRequest;
import mojtaba.safaeian.go3.api.dto.GameDto;
import mojtaba.safaeian.go3.api.dto.NewGameRequest;
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
                newGameRequest.getRemotePlayerDescriptor(), true);

        return ResponseEntity.ok(gameToGameDto(game));
    }

    @PostMapping("answers")
    public ResponseEntity addAnswer(@RequestBody AnswerRequest answerRequest) {
        gameService.addAnswer(answerRequest);
        return ResponseEntity.ok("OK");
    }

    @GetMapping
    public @ResponseBody GameDto getGameDto(){
        return gameToGameDto(gameService.getRunningGame());
    }

    @GetMapping("status")
    public @ResponseBody GameRunnerStatus getStatus(){
        return gameService.getStatus();
    }

    /**
     * converts a game domain object to a DTO to send over http
     * @param game
     * @return
     */
    private GameDto gameToGameDto(Game game){
        return modelMapper.map(game, GameDto.class);
    }
}
