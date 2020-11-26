package development.bulletinboard.controller;

import development.bulletinboard.model.AdForm;
import development.bulletinboard.service.AdFormService;
import development.bulletinboard.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/details/{id}")
    public String detailsPage(Model model, @PathVariable("id") int id) {
        AdForm adForm = service.getAdFormById(id);
        model.addAttribute("selectedAd", adForm);
        model.addAttribute("dateSelectedAd", Util.getTimeFromStamp(adForm.getCreationTimestamp()));
        return "details";
    }

    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}
