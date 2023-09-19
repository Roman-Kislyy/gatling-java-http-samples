package servicename.simulations;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static ru.tinkoff.gatling.javaapi.SimulationConfig.getStringParam;

/**
 * <h2>В данной симуляции приводится простой пример симуляции</h2>
 * <p>
 * В тестах с большим количеством вызовов нужно декомпозировать логику на сценарии и действия, и разложить в scenarios и actions соответственно.
 * Тут можно рассказать, как собирать профиль нагрузки. В чем особенность теста.
 *
 * Кстати, в качестве сайта-примера используется этот проект https://github.com/Roman-Kislyy/web-tutorial-shopping-center/blob/master/README.md#quick-start
 * @author  Roman Kislyy
 * @since 2023-08-11
 */
public class SimpleSimulation extends Simulation {
    FeederBuilder<String> feeder = csv("pools/search.csv").random();
    ChainBuilder search =
            exec(http("Home page").get("/"))
                    .pause(1)
                    .feed(feeder)
                    .exec(
                            http("Search")
                                    .get("/index.jsp?search=#{productType}")
                    );
    HttpProtocolBuilder httpProtocol =
            http.baseUrl(getStringParam("http.baseUrl"))
                    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .acceptLanguageHeader("en-US,en;q=0.5")
                    .acceptEncodingHeader("gzip, deflate")
                    .userAgentHeader(
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
                    );
    ScenarioBuilder scn = scenario("SimpleSimulation scenario").exec(search);
    {
        setUp(
                // Разгон с 0 до 5 rps в течение 20 секунд, затем постоянная нагрузка 5 rps в течение 60 секунд
                // Будет 5 выполнений сценариев в секунду.
                // В нашем сценарие два http обращения.
                // В html отчете две вкладки (Global и Details) вы сможете увидеть сумму (10 rps) на одной из них и 5 rps по каждому методу на другой вкладке

                scn.injectOpen(
                        rampUsersPerSec(0).to(5).during(20),
                        constantUsersPerSec(5).during(60))
        ).protocols(httpProtocol);
    }
}
