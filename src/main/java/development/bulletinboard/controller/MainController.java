package development.bulletinboard.controller;

import development.bulletinboard.model.AdForm;
import development.bulletinboard.model.Category;
import development.bulletinboard.model.User;
import development.bulletinboard.service.AdFormService;
import development.bulletinboard.service.CategoryService;
import development.bulletinboard.service.UserService;
import development.bulletinboard.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Основной контроллер обработки запросов
 */
@Controller
public class MainController {

    private final AdFormService adFormService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public MainController(AdFormService service, UserService userService, CategoryService categoryService) {
        this.adFormService = service;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    /**
     * Запрос главной страницы
     * @param model - список всех объявлений
     * @return главная страница
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Model model, Principal principal) {
        String userName = "гость";
        if (principal != null) {
            userName = principal.getName();
        }
        model.addAttribute("userName", userName);
        model.addAttribute("messages", adFormService.getLastAdForms());
        return "main";
    }

    /**
     * Запрос добавления нового объявления
     * @param model - новый объект объявления AdForm
     * @return страница создания объявления
     */
    @RequestMapping(value = "/addnew", method = RequestMethod.GET)
    public String addNewForm(Model model, Principal principal) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("adform", new AdForm());
        model.addAttribute("userName", principal.getName());
        return "addnew";
    }

    /**
     * Подтверждение создания нового объявления
     * @param adForm - полученный из веб объект объявления
     * @return редирект на главную
     */
    @RequestMapping(value = "/addnew/submit", method = RequestMethod.POST)
    public String submitArticle(@ModelAttribute AdForm adForm, Principal principal) {
        String userName = principal.getName();
        User user = userService.getUserById(userName);
        adForm.setUser(user);
        adFormService.saveForm(adForm);
        return "redirect:../";
    }

    /**
     * Запрос просмотра выбранного объявления
     * @param model - объект объявления из базы, дата создания и имя создавшего в нормальном виде
     * @param id - идентификатор выбранного объявления
     * @return страница детализации объявдения
     */
    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String detailsPage(Model model, @PathVariable("id") int id) {
        AdForm adForm = adFormService.getAdFormById(id);

        model.addAttribute("title", adForm.getTitle());
        model.addAttribute("category", adForm.getCategoryName());
        model.addAttribute("content", adForm.getContent());
        model.addAttribute("id", adForm.getId());
        model.addAttribute("dateSelectedAd", adForm.getNormalDate());
        model.addAttribute("userName", adForm.getUser().getUserName());

        return "details";
    }

    /**
     * Запрос авторизации
     * @return страница авторизации
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /**
     * Запрос поиска объявлений по ключевому слову
     * @param model - текст из веб-формы
     * @param text - текст из веб-формы
     * @return страница с результатами поиска
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchOnSite(Model model, @RequestParam("searchingText") String text) {
        model.addAttribute("searchingText", text);
        List<AdForm> adFormList = adFormService.getAdFormBySearch(text);
        model.addAttribute("adFormList", adFormList);
        return "searching";
    }

    /**
     * Запрос страницы с данными пользователя.
     * В странице отображаются все объявления этого пользователя.
     * @param model передаем имя пользователя и полученный список его объявлений
     * @param userName имя пользователя, по которому будем искать объявления
     * @return страница с данными о пользователе
     */
    @RequestMapping(value = "/user-details/{id}", method = RequestMethod.GET)
    public String detailsPage(Model model, @PathVariable("id") String userName) {
        List<AdForm> adFormList = adFormService.getAllAdsFromUser(userName);
        model.addAttribute("userName", userName);
        model.addAttribute("regDate", Util.getTimeFromStamp(userService
                .getUserById(userName)
                .getRegisterTimestamp()));
        model.addAttribute("adFormList", adFormList);
        return "user-details";
    }
}
