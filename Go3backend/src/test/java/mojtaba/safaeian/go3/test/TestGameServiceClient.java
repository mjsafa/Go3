package mojtaba.safaeian.go3.test;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import mojtaba.safaeian.go3.api.domain.HistoryRecord;
import mojtaba.safaeian.go3.api.dto.AnswerRequest;
import mojtaba.safaeian.go3.api.dto.GameDto;
import mojtaba.safaeian.go3.api.dto.NewGameRequest;
import mojtaba.safaeian.go3.api.dto.RemotePlayerDescriptor;
import mojtaba.safaeian.go3.api.service.client.GameServiceClient;
import mojtaba.safaeian.go3.domain.PlayerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/16/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestGameServiceClient {

    @Autowired
    private PlayerFactory playerFactory;

    private GameServiceClient gameServiceClient;

    @Value("${server.port}")
    private int serverPort;

    @Before
    public void initializeGameServiceClient() {
        gameServiceClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(GameServiceClient.class))
                .logLevel(Logger.Level.FULL)
                .target(GameServiceClient.class, new RemotePlayerDescriptor("localhost", String.valueOf(serverPort)).getHttpUrl());
    }

    @Test
    public void testStartNewGame() throws InterruptedException {
        GameDto gameDto = gameServiceClient.startNewGame(new NewGameRequest(13, new RemotePlayerDescriptor("", "")));

        assertThat(gameDto.getHistories().get(0), hasProperty("number", is(equalTo(13))));
        assertThat(gameDto.getHistories().get(0),
                hasProperty("historyRecordType", is(equalTo(HistoryRecord.HistoryRecordType.RECEIVED))));

        Thread.sleep(1000);

        String answer = gameServiceClient.answer(new AnswerRequest(10, new RemotePlayerDescriptor("", "")));

        assertThat(answer.toLowerCase(), is("ok"));
    }
}
