package development.bulletinboard.controller;

import development.bulletinboard.model.AdForm;
import development.bulletinboard.service.AdFormService;
import development.bulletinboard.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Основной контроллер обработки запросов
 */
@Controller
public class MainController {

    private final AdFormService service;

    @Autowired
    public MainController(AdFormService service) {
        this.service = service;
    }

    /**
     * Запрос главной страницы
     * @param model - список всех объявлений
     * @return главная страница
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Model model) {
        model.addAttribute("messages", service.getLastAdForms());
        return "main";
    }

    /**
     * Запрос добавления нового объявления
     * @param model - новый объект объявления AdForm
     * @return страница создания объявления
     */
    @RequestMapping(value = "/addnew", method = RequestMethod.GET)
    public String addNewForm(Model model) {
        model.addAttribute("adform", new AdForm());
        return "addnew";
    }

    /**
     * Подтверждение создания нового объявления
     * @param adForm - полученный из веб объект объявления
     * @return редирект на главную
     */
    @RequestMapping(value = "/addnew/submit", method = RequestMethod.POST)
    public String submitArticle(@ModelAttribute AdForm adForm) {
        service.saveForm(adForm);
        return "redirect:../";
    }

    /**
     * Запрос просмотра выбранного объявления
     * @param model - объект объявления из базы, дата создания в нормальном виде
     * @param id - идентификатор выбранного объявления
     * @return страница детализации объявдения
     */
    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String detailsPage(Model model, @PathVariable("id") int id) {
        AdForm adForm = service.getAdFormById(id);
        model.addAttribute("selectedAd", adForm);
        model.addAttribute("dateSelectedAd", Util.getTimeFromStamp(adForm.getCreationTimestamp()));
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
     * Запрос поиска объявлений
     * @param model - текст из веб-формы
     * @param text - текст из веб-формы
     * @return страница с результатами поиска
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchOnSite(Model model, @RequestParam("searchingText") String text) {
        model.addAttribute("searchingText", text);
        List<AdForm> adFormList = service.geiAdFormBySearch(text);
        model.addAttribute("adFormList", adFormList);
        return "searching";
    }
}
