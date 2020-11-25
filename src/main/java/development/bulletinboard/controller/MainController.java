package development.bulletinboard.controller;

import development.bulletinboard.model.AdForm;
import development.bulletinboard.service.AdFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private final AdFormService service;

    @Autowired
    public MainController(AdFormService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("messages", service.getLastAdForms());
        return "main";
    }

    @RequestMapping(value = "/addnew")
    public String addNewForm(Model model) {
        model.addAttribute("adform", new AdForm());
        return "addnew";
    }

    @RequestMapping(value = "/addnew/submit", method = RequestMethod.POST)
    public String submitArticle(@ModelAttribute AdForm adForm) {
        service.saveForm(adForm);
        return "redirect:../";
    }

    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}
