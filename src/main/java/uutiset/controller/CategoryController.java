package uutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uutiset.domain.Category;
import uutiset.service.ArticleService;
import uutiset.service.CategoryService;
//Polussa /categories tapahtuvat get, post ja deleta pyynnöt.
@Controller
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;
    
    //Listataan kategoriat sivulle categories.html.
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("categories", categoryService.list());
        return "categories";
    }

    //Tallennetaan uuden kategorian tiedot.
    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam String name, @RequestParam(required = false) String nav) {
        categoryService.add(name, nav);
        return "redirect:/categories";
    }

    //Esituys yhedelle kategorialla sivulla category.html.
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable(value = "categoryId") Long categoryId) {
        Category category = categoryService.findById(categoryId);
        model.addAttribute("category", category);
        model.addAttribute("articles", articleService.findByCategory(category.getName()));
        return "category";
    }
    
    //Poistetaan id:tä vastaava kategoria.
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    public String remove(@PathVariable(value = "categoryId") Long categoryId) {
        categoryService.remove(categoryId);
        return "redirect:/categories";
    }

}
