package uutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uutiset.domain.Category;
import uutiset.service.ArticleService;
import uutiset.service.CategoryService;

@Controller
@RequestMapping("home")
public class UserController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("articles", articleService.list());
        model.addAttribute("categories", categoryService.list());
        model.addAttribute("categoryName", "News");
        return "monkeynews";
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public String categories(Model model, @PathVariable Long categoryId) {
        Category c = categoryService.findById(categoryId);
        model.addAttribute("articles", c.getArticles());
        model.addAttribute("categories", categoryService.list());
        model.addAttribute("categoryName", c.getName());
        return "monkeynews";
    }

}
