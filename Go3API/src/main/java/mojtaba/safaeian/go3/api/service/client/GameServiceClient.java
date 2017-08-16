package mojtaba.safaeian.go3.api.service.client;

import feign.Headers;
import feign.RequestLine;
import mojtaba.safaeian.go3.api.dto.Answer;
import mojtaba.safaeian.go3.api.dto.AnswerRequest;
import mojtaba.safaeian.go3.api.dto.GameDto;
import mojtaba.safaeian.go3.api.dto.NewGameRequest;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/15/2017.
 */


public interface GameServiceClient {

    @RequestLine("POST /game")
    @Headers("Content-Type: application/json")
    GameDto startNewGame(NewGameRequest newGameRequest);

    @RequestLine("POST /game/answers")
    @Headers("Content-Type: application/json")
    String answer(AnswerRequest answerRequest);


}
