package servicename.scenarios;

import io.gatling.javaapi.core.ScenarioBuilder;
import servicename.actions.authorization.Login;
import servicename.actions.navigation.MainPage;

import static io.gatling.javaapi.core.CoreDsl.scenario;

/**
 * <h2>Сценарий покупки телефона</h2>
 * <p>
 *     В данном примере демонстрируется, как может выгдялеть сложный сценарий, если его шаги вынесены в actions
 * Шаги сценария:
 * <ul>
 *     <li> Открытие главной страницы</li>
 *     <li> Авторизация</li>
 *     <li> Поиск</li>
 *     <li> Добавление в корзину</li>
 *     <li> Создание заказа</li>
 * </ul>
 *<p>
 * Требования SLA:
 * <ul>
 *     <li> Длительность работы сценария 60 секунд</li>
 *</ul>
 * @author  Roman Kislyy
 * @since 2023-08-11
 */
public class BuyPhoneScenario {

    public static ScenarioBuilder scn = scenario("Buy mobile phone scenario")
            .exec(new MainPage().open())
            .exec(new Login().byPassword())
//            .exec(new SearchProduct().searchMobilePhone())
//            .exec(new Basket().addProduct())
//            .exec(new CreateOrder().buyMobilePhone())
            ;

}

