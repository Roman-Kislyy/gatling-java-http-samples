package servicename.actions;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;

/**
 * <h2>Пример класса с действиями</h2>
 * <p>
 *     Например, реализует методы доступные на главной странице сайта

 * <p>
 * @author  Roman Kislyy
 * @since 2023-08-11
 */
public class ActionClassExample {

    public ChainBuilder open(){
        Map<CharSequence, String> headers_0 = Map.ofEntries(
                        Map.entry("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
                        Map.entry("Cache-Control", "max-age=0"),
                        Map.entry("Proxy-Connection", "keep-alive"),
                        Map.entry("Upgrade-Insecure-Requests", "1"));

        ChainBuilder chain =
                exec(
                    http("Main page")
                        .get("/")
                        .headers(headers_0)
                        );
        return chain;
    }
}
