package uutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uutiset.service.ArticleService;
import uutiset.service.CategoryService;
//Tässä kontrollerissa hoidetaan /home polussa tapahtuvat get, pot ja delete pyynnöt.
@Controller
@RequestMapping("home")
public class UserController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    
    //Artikkelien listaus etusivulle.
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("articles", articleService.listHomePage());
        model.addAttribute("categories", categoryService.listForNav());
        model.addAttribute("categoryName", "MonkeyNews");
        return "monkeynews";
    }

    //Artikkelien listaus tietyn kategorian sivulle.
    @RequestMapping(value = "/{category}", method = RequestMethod.GET)
    public String categories(Model model, @PathVariable String category) {
        articleService.findByCategory(category);
        model.addAttribute("articles", articleService.findByCategory(category));
        model.addAttribute("categories", categoryService.listForNav());
        model.addAttribute("categoryName", category);
        return "monkeynews";
    }

    //Artikkelien listaus eniten luettut sivulle.
    @RequestMapping(value = "/mostRead", method = RequestMethod.GET)
    public String mostRead(Model model) {
        model.addAttribute("articles", articleService.mostReadArticles());
        model.addAttribute("categories", categoryService.listForNav());
        model.addAttribute("categoryName", "Most read news this week!");
        return "monkeynews";
    }

    //Artikkelien listaus uusimmat sivulle.
    @RequestMapping(value = "/newest", method = RequestMethod.GET)
    public String newest(Model model) {
        model.addAttribute("articles", articleService.newestArticles());
        model.addAttribute("categories", categoryService.listForNav());
        model.addAttribute("categoryName", "Newest");
        return "monkeynews";
    }

}
