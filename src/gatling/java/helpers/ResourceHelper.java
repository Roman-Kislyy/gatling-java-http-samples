package helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;

/**
 * <h2>Полезные функции для работы с ресурсами</h2>
 * @author  Roman Kislyy
 * @since 2023-08-11
 */
public  class ResourceHelper {
    private static Logger log = LoggerFactory.getLogger(ResourceHelper.class);
    private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    /**
     * <p> Возвращает абсолютный путь до ресурса из модуля gatling
     * @param shortPath Относительный путь в папке gatling/resources или абсолютный путь.
     * @author  Roman Kislyy
     * @since 2023-08-11
     */
    public static  String gatlingResourcePath(String shortPath) throws IOException {
        File file = new File(shortPath);
        String path = "";

        if (!file.isAbsolute()){
            log.debug("Short resource path: {}", shortPath);
//            path = this.getClass().getClassLoader().getResource(shortPath).getPath();
            path = classLoader.getResource(shortPath).getPath();
            log.debug("Absolutely resource path: {}", path);
        }else {
            path = file.getCanonicalPath();
            log.debug("Absolutely path presented: {}", path);
        }
        return path;
    }
}
