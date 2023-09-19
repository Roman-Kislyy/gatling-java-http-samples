package servicename.actions.authorization;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

/**
 * <h2>Класс для авторизации на сайте</h2>
 * <p>
 *     Можно создать несколько методов, например, по паролю, или разные методы под разных пользователей
 * @author  Roman Kislyy
 * @since 2023-08-11
 */
public class Login {
    /**
     * <p>Авторизация паролю</p>
     * <p>
     *     Пулы клиентов pools/users.csv
     * <p>
     *     Требование к времени отклика: 5 секунд
     * @return объект с типом ChainBuilder
     * @author  Roman Kislyy
     * @since 2023-08-11
     */
    public ChainBuilder byPassword(){
        FeederBuilder<String> feederUser = csv("pools/users.csv").random();
        ChainBuilder chain =
              feed(feederUser)
                      // Переход на страницу логина
                      .exec(
                              http("Open login page")
                                      .get("/login.jsp")
                                      .headers(Map.ofEntries(
                                              Map.entry("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
                                              Map.entry("Proxy-Connection", "keep-alive"),
                                              Map.entry("Upgrade-Insecure-Requests", "1")
                                      ))
                      )
                      // Ввод логина и пароля
                      .exec(
                              http("Login by password")
                                      .post("/LoginSrv")
                                      .headers(Map.ofEntries(
                                              Map.entry("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
                                              Map.entry("Cache-Control", "max-age=0"),
                                              Map.entry("Origin", "http://shopping-center:9350"),
                                              Map.entry("Proxy-Connection", "keep-alive"),
                                              Map.entry("Upgrade-Insecure-Requests", "1")
                                      ))
                                      .formParam("username", "#{userLogin}")
                                      .formParam("password", "#{userPass}")
                                      .formParam("usertype", "customer")
                                      // Делаем проверку успешности регистрации.
                                      // После регистрации, пользователю доступен раздел Profile
                                      // Чтобы скопировать рег. выражение удобно в Chrome: F12 - Elements - Поиск Profile -
                                      // F12 - Elements - Поиск Profile - Правой кнопкой на тексте Profile - Copy - Copy element
                                      .check(regex("<a href=\"userProfile.jsp\">Profile</a>"))
                                      // Также можно через css по селектору, но его название не информативно в нашем примере (child(5)):
                                      //.check(css("#myNavbar > ul > li:nth-child(5) > a"))
                                      // Проверяем, что вход по паролю длится не более 5 секунд
                                      .check(responseTimeInMillis().lte(5000))
                      );

        return chain;
    }

}
