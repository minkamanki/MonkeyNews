package uutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uutiset.service.ArticleService;
import uutiset.service.CategoryService;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;
 
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("categories", categoryService.list());
        return "categories";
    }
 
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String add(@RequestParam String name) {
        categoryService.add(name);
        return "redirect:/categories";
    }
 
    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable(value = "categoryId") Long categoryId) {
        model.addAttribute("category", categoryService.findById(categoryId));
        model.addAttribute("articles", articleService.listArticlesWithoutThisCategory(categoryId));
        return "category";
    }
 
    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.DELETE)
    public String remove(@PathVariable(value = "categoryId") Long categoryId) {
        categoryService.remove(categoryId);
        return "redirect:/categories";
    }
 
    @RequestMapping(value = "/categories/{categoryId}/articles", method = RequestMethod.POST)
    public String addCategoryToArticle(@PathVariable(value = "categoryId") Long categoryId,
            @RequestParam(value = "articleId") Long articleId) {
        categoryService.addCategoryToArticle(categoryId, articleId);
        return "redirect:/categories";
    }

}
