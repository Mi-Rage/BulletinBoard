package development.bulletinboard.controller;

import development.bulletinboard.model.AdForm;
import development.bulletinboard.service.AdFormService;
import development.bulletinboard.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    private final AdFormService service;

    @Autowired
    public MainController(AdFormService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Model model) {
        model.addAttribute("messages", service.getLastAdForms());
        return "main";
    }

    @RequestMapping(value = "/addnew", method = RequestMethod.GET)
    public String addNewForm(Model model) {
        model.addAttribute("adform", new AdForm());
        return "addnew";
    }

    @RequestMapping(value = "/addnew/submit", method = RequestMethod.POST)
    public String submitArticle(@ModelAttribute AdForm adForm) {
        service.saveForm(adForm);
        return "redirect:../";
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String detailsPage(Model model, @PathVariable("id") int id) {
        AdForm adForm = service.getAdFormById(id);
        model.addAttribute("selectedAd", adForm);
        model.addAttribute("dateSelectedAd", Util.getTimeFromStamp(adForm.getCreationTimestamp()));
        return "details";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchOnSite(Model model, @RequestParam("searchingText") String text) {
        model.addAttribute("searchingText", text);
        List<AdForm> adFormList = service.geiAdFormBySearch(text);
        System.out.println(Arrays.toString(adFormList.toArray()));
        model.addAttribute("adFormList", adFormList);
        return "searching";
    }
}
