package mojtaba.safaeian.go3.domain;

import feign.Feign;
import feign.Logger;
import feign.RetryableException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import mojtaba.safaeian.go3.api.domain.Player;
import mojtaba.safaeian.go3.api.dto.*;
import mojtaba.safaeian.go3.api.service.RemoteServiceException;
import mojtaba.safaeian.go3.api.service.client.GameServiceClient;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/15/2017.
 */
public class RemotePlayerImpl implements Player {

    private RemotePlayerDescriptor localPlayer;
    private GameServiceClient gameServiceClient;
    private boolean isStarted = false;

    public RemotePlayerImpl(RemotePlayerDescriptor remotePlayerDescriptor, RemotePlayerDescriptor localPlayer , boolean isStarted) {
        gameServiceClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(GameServiceClient.class))
                .logLevel(Logger.Level.FULL)
                .target(GameServiceClient.class, remotePlayerDescriptor.getHttpUrl());
        this.localPlayer = localPlayer;
        this.isStarted = isStarted;
    }

    @Override
    public Integer generateFirstAnswer() {
        throw new UnsupportedOperationException("only local player can generate first answer");
    }

    @Override
    public void receiveAnswer(Integer answer) {
        try {
            if (isStarted) {
                gameServiceClient.answer(new AnswerRequest(answer, localPlayer));
            } else {
                gameServiceClient.startNewGame(new NewGameRequest(answer, localPlayer, true));
                this.isStarted = true;
            }
        } catch (RetryableException re) {
            throw new RemoteServiceException(re);
        }
    }

    @Override
    public int answer() {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    @Override
    public boolean isWin() {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    @Override
    public boolean isLose() {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    @Override
    public boolean isMyTurn() {
        return false;
    }
}
