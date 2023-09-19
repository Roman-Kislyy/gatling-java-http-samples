package helpers;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.Session;
import static io.gatling.javaapi.core.CoreDsl.exec;

/**
 * <h2>Функции помощники для работы с переменными</h2>
 * <p>
 *     Здесь методы работы с переменными в рамках session, которые позволяют не плодить код
 * @author  Roman Kislyy
 * @since 2023-08-11
 */
public class VarsHelper {
    /**
     * <p>Сохранить значение в переменную #{} в session контекст</p>
     * <p> Далее использовать будем так #{key}
     * @param key имя будущей переменной
     * @param value Значение переменной
     * @return объект с типом ChainBuilder
     * @author  Roman Kislyy
     * @since 2023-08-11
     */
    public static ChainBuilder set(String key, String value) {
        return exec(session -> {
            Session newSession = session.set(key, value);
            return newSession;
        });
    }
    public static ChainBuilder set(String key, int value) {
        return VarsHelper.set(key, String.valueOf(value));
    }
    public static ChainBuilder set(String key, Object value) {
        return VarsHelper.set(key, String.valueOf(value));
    }
}
