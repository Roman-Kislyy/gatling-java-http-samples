Gatling Java HTTP tests
=============================================

Примеры тестов.

Создано на основе проекта
[on the Gatling website](https://gatling.io/docs/current/extensions/gradle_plugin/) for usage.


# Web shopping

Для создания и демонстрации HTTP скриптов использовалось [это веб-приложение](https://github.com/Roman-Kislyy/web-tutorial-shopping-center)


# Небольшие инструкции по работе с Gatling и Gradle:

- [Как запустить тест при помощи Gradle](docs/how-to-slides/Gtg-How-to-start-test.pptx)
- [Какие бывают переменные в Gatling](docs/how-to-slides/Gtg-Variables.pptx)
- [Про разные сценарии нагрузки. Максимум, стресс и другое](docs/how-to-slides/Gtg-Scenario-injections-load-users.pptx)
- [О структуре скриптов на Gatling. Термины для начинающих](docs/how-to-slides/Gtg-Termins-script-structure.pptx)
- [Как воспользоваться HTTP recorder для записи скрипта](docs/how-to-slides/Gtg-HTTP-Recording.pptx)
 
# Быстрый старт

Как запустить тест при помощи Gradle plugin.

`./gradlew.bat gatlingRun-servicename.simulations.OpenInjecjionsSimulation`

или 

`gradlew.bat gatlingRun-servicename.simulations.OpenInjecjionsSimulation`

В зависимости от окружения.

![](docs/img/GatlingRun.gif)
