package development.bulletinboard.controller;

import development.bulletinboard.model.User;
import development.bulletinboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер регистрации новых пользователей
 */
@Controller
public class RegistrationController {

    private final UserService userService;
    @Autowired
    RegistrationController(UserService userService){
        this.userService = userService;
    }

    /**
     * Обработка запроса страницы для регистрации
     * @param model - новый объект пользователя User
     * @return - страница регистрации
     */
    @RequestMapping(value ="/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    /**
     * Обработка запроса на регистрацию пользователя
     * и добавление его в БД
     * @param user - объект пользователя из вэб-формы
     * @param model - сообщения об ошибках
     * @return если ошибка - пробуем снова. Если ок - редирект на главную
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerNewUser(@ModelAttribute User user, Model model) {

        if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }

        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }
}
