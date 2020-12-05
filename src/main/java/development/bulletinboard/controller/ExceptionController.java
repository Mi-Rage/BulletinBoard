package development.bulletinboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер для исключений и ошибочных ситуаций
 */
@Controller
public class ExceptionController {

    /**
     * Обработка события запрета доступа
     * @return страница с сообщением
     */
    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String accessDenied() {
        return "access-denied";
    }
}
