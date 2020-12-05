package development.bulletinboard.controller;

import development.bulletinboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер администратора сервиса
 */
@Controller
public class AdminController {
    private final UserService userService;

    @Autowired
    AdminController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Обработка запроса к странице админки.
     * @param model передаем список всех пользователей, список ролей пользователей
     * @return страница админки
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allRoles", userService.getAllRoles());
        return "admin";
    }
}
