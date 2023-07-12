package shopping;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static ru.tinkoff.gatling.javaapi.SimulationConfig.getStringParam;

/*
    В данной симуляции приводится пример использования нескольких вариантов генерации нагрузки для разных типов тестов
*/
public class OpenInjecjionsSimulation extends Simulation {
    FeederBuilder<String> feeder = csv("pools/search.csv").random();
    ChainBuilder home =
            exec(http("Home page").get("/"));
    HttpProtocolBuilder httpProtocol =
            http.baseUrl(getStringParam("http.baseUrl"))
                    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .acceptLanguageHeader("en-US,en;q=0.5")
                    .acceptEncodingHeader("gzip, deflate")
                    .userAgentHeader(
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
                    );
    ScenarioBuilder scn = scenario(getClass().getSimpleName() + " scenario").exec(home);

    int FIRST_STEP_INTENCITY = 50;
    int EACH_STEP_INTENCITY = 10;
    {
        setUp(
                /* #1 - Плавный разгон и одна полка #
                    Разгон с 0 до 5 rps в течение 20 секунд, затем постоянная нагрузка 5 rps в течение 60 секунд
                    Будет 5 выполнений сценариев в секунду.
                    В нашем сценарие два http обращения.
                    В html отчете две вкладки (Global и Details) вы сможете увидеть сумму (10 rps) на одной из них и 5 rps по каждому методу на другой вкладке
                 */
//                scn.injectOpen(
//                        rampUsersPerSec(0).to(5).during(20),
//                        constantUsersPerSec(5).during(60)
//                )

                /* #2 - 5 Одинаковых ступеней #
                    Каждая ступень увеличивается на 3 операции в секунду
                    Всего 5 ступеней
                    Длительность полки 20 секунд
                    Выход на каждую ступень длится 10 секунд
                    Начинается симуляция с 0 rps
                */
//                scn.injectOpen(
//                        incrementUsersPerSec(3)
//                        .times(5)
//                        .eachLevelLasting(20)
//                        .separatedByRampsLasting(10)
//                        .startingFrom(0)
//                )

                 /* #3 - Большая первая ступень #
                    Для удобства нам потребуется минимум пара переменных:
                        - FIRST_STEP_INTENCITY = 50 - уровень нагрузки на первой ступени
                        - EACH_STEP_INTENCITY = 10 - на сколько добавляется нагрузка на последующих ступенях
                    Сначала выход на большую ступень с 0 до 50 rps в течение 5 секунд
                    Затем еще 2 ступени по 10 rps каждая
                */
//                scn.injectOpen(
//                        rampUsersPerSec(0)
//                                .to(FIRST_STEP_INTENCITY)
//                                .during(5),
//                        constantUsersPerSec(FIRST_STEP_INTENCITY)
//                                .during(10),
//                        incrementUsersPerSec(EACH_STEP_INTENCITY)
//                                .times(2)
//                                .eachLevelLasting(20)
//                                .separatedByRampsLasting(0)
//                                .startingFrom(FIRST_STEP_INTENCITY + EACH_STEP_INTENCITY)
//                )

                /* #4 - Симуляция пиковой нагрузки, тест на восстановление работы системы #
                    Разгон от 0 до 15 rps
                    Затем полка 15 rps
                    Затем в течение 15 секунд входит 1000 пользователей (выполняют по одной итерации)
                    Затем снова 15 rps
                */
//                scn.injectOpen(
//                        rampUsersPerSec(0)
//                                .to(15)
//                                .during(5),
//                        constantUsersPerSec(15)
//                                .during(30),
//                        stressPeakUsers(1000)
//                                .during(20),
//                        constantUsersPerSec(15)
//                                .during(30)
//                )

                /* #5 - Однократный вызов (отладка) #
                    Запуск одного пользователя на 1 итерацию
                */
                scn.injectOpen(
                        atOnceUsers(1)
                )
        ).protocols(httpProtocol);
    }
}
