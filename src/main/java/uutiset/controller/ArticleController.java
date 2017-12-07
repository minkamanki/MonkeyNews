package uutiset.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uutiset.service.ArticleService;

@Controller
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("articles", articleService.list());
        return "articles";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam String title, @RequestParam String lede, @RequestParam String text, @RequestParam("file") MultipartFile file) throws IOException {
        articleService.add(title, lede, text, file.getBytes());
        return "redirect:/articles";
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.DELETE)
    public String add(@PathVariable(value = "articleId") Long articleId) {
        articleService.remove(articleId);
        return "redirect:/articles";
    }

//    @GetMapping("/articles/{articleId}")
    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public String findOne(Model model, @PathVariable Long articleId) {        
        model.addAttribute("article", articleService.findById(articleId));
        return "article";
    }
}
