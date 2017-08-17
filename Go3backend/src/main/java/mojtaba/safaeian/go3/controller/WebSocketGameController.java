package mojtaba.safaeian.go3.controller;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.dto.Answer;
import mojtaba.safaeian.go3.api.dto.GameDto;
import mojtaba.safaeian.go3.api.dto.NewGameRequest;
import mojtaba.safaeian.go3.api.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/17/2017.
 */
@Controller
public class WebSocketGameController {

    private final SimpMessagingTemplate template;
    private final GameService gameService;
    private final ModelMapper modelMapper;

    @Autowired
    public WebSocketGameController(SimpMessagingTemplate template, GameService gameService, ModelMapper modelMapper) {
        this.template = template;
        this.gameService = gameService;
        this.modelMapper = modelMapper;
    }

    @MessageMapping("/game/start")
    @SendTo("/topic/start")
    public GameDto greeting(NewGameRequest newGameRequest) throws Exception {
        System.out.println("GAME START ++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Game game = gameService.startNewGame(new Answer(newGameRequest.getFirstNumber()),
                newGameRequest.getRemotePlayerDescriptor());
        return gameToGameDto(game);
    }

    private GameDto gameToGameDto(Game game){
        return modelMapper.map(game, GameDto.class);
    }
}
