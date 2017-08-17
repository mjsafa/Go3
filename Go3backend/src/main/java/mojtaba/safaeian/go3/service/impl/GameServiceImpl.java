package mojtaba.safaeian.go3.service.impl;

import mojtaba.safaeian.go3.api.domain.Game;
import mojtaba.safaeian.go3.api.domain.GameRunnerStatus;
import mojtaba.safaeian.go3.api.domain.Player;
import mojtaba.safaeian.go3.api.dto.Answer;
import mojtaba.safaeian.go3.api.dto.AnswerRequest;
import mojtaba.safaeian.go3.api.dto.RemotePlayerDescriptor;
import mojtaba.safaeian.go3.api.service.GameService;
import mojtaba.safaeian.go3.domain.GameFactory;
import mojtaba.safaeian.go3.domain.LocalPlayerImpl;
import mojtaba.safaeian.go3.domain.PlayerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/14/2017.
 */

@Service
public class GameServiceImpl implements GameService{

    @Value("go3.local.host")
    private String localHost;

    @Value("server.port")
    private String localPort;

    private final GameFactory gameFactory;
    private final PlayerFactory playerFactory;
    private final ModelMapper modelMapper;
    @Autowired
    private final SimpMessagingTemplate template;

    private Game runningGame = null;
    private GameRunner gameRunner;

    @Autowired
    public GameServiceImpl(GameFactory gameFactory, PlayerFactory playerFactory, ModelMapper modelMapper, SimpMessagingTemplate template) {
        this.gameFactory = gameFactory;
        this.playerFactory = playerFactory;
        this.modelMapper = modelMapper;
        this.template = template;
    }

    @Override
    public Game startNewGame(Answer answer, RemotePlayerDescriptor remotePlayerDescriptor){
        RemotePlayerDescriptor localPlayer = new RemotePlayerDescriptor(localHost, localPort);

        Player myPlayer = new LocalPlayerImpl();
        Player remotePlayer = playerFactory.createRemotePlayer(remotePlayerDescriptor, localPlayer);
        this.runningGame = gameFactory.createGame(myPlayer, remotePlayer);
        if(answer != null && answer.getNumber() > 0){
            this.runningGame.receiveAnswer(answer.getNumber());
        }

        //Start running game
        this.gameRunner = new GameRunner(this.runningGame, template);
        new Thread(gameRunner).start();
        return this.runningGame;
    }

    @Override
    public void addAnswer(AnswerRequest answerRequest){
        this.runningGame.receiveAnswer(answerRequest.getNumber());
    }

    @Override
    public GameRunnerStatus getStatus(){
        return this.gameRunner.getStatus();
    }

    @Override
    public Game getRunningGame(){
        return this.runningGame;
    }
}
