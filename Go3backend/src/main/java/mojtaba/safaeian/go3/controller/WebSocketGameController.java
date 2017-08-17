package mojtaba.safaeian.go3.controller;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.dto.Answer;
import mojtaba.safaeian.go3.api.dto.GameDto;
import mojtaba.safaeian.go3.api.dto.NewGameRequest;
import mojtaba.safaeian.go3.api.service.GameService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(WebSocketGameController.class);

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
    public GameDto startNewGame(NewGameRequest newGameRequest) throws Exception {
        logger.debug("new websocket request for starting a new game with remote player at {}", newGameRequest.getRemotePlayerDescriptor().getHttpUrl());
        Game game = gameService.startNewGame(new Answer(newGameRequest.getFirstNumber()),
                newGameRequest.getRemotePlayerDescriptor(), newGameRequest.isAutomatic());
        return gameToGameDto(game);
    }

    @MessageMapping("/game/requestAnswer")
    @SendTo("/topic/requestAnswer")
    public String requestAnswer() throws Exception {
        gameService.requestAnswerByUser();
        return "ok";
    }

    private GameDto gameToGameDto(Game game){
        return modelMapper.map(game, GameDto.class);
    }
}
