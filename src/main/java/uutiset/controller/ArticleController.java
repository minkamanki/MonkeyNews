package uutiset.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import uutiset.domain.Article;
import uutiset.domain.Picture;
import uutiset.service.ArticleService;
import uutiset.service.AuthorService;
import uutiset.service.CategoryService;

@Controller
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("articles", articleService.list());
        model.addAttribute("athors", authorService.list());
        return "articles";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam String title, @RequestParam String lede, @RequestParam String text, @RequestParam("file") MultipartFile file) throws IOException { // 
        articleService.add(title, lede, text, file.getBytes()); //
        return "redirect:/articles";
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.DELETE)
    public String add(@PathVariable(value = "articleId") Long articleId) {
        articleService.remove(articleId);
        return "redirect:/articles";
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public String findOne(Model model, @PathVariable Long articleId) {
        articleService.findById(articleId).setReadCount(articleService.findById(articleId).getReadCount() + 1);
        model.addAttribute("authors", authorService.findOtherAuthors(articleId));
        model.addAttribute("categories", categoryService.findOtherCategories(articleId));
        model.addAttribute("article", articleService.findById(articleId));
        return "article";
    }

    @RequestMapping(value = "/{articleId}/picture", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> viewFile(@PathVariable Long articleId) {
        Article a = articleService.findById(articleId);
        Picture picture = a.getContent();
        return new ResponseEntity<>(picture.getContent(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{articleId}/category", method = RequestMethod.POST)
    public String addCategoryToArticle(@PathVariable(value = "articleId") Long articleId,
            @RequestParam(value = "categoryId") Long categoryId) {
        categoryService.addCategoryToArticle(categoryId, articleId);
        return "redirect:/articles/" + articleId;
    }

    @RequestMapping(value = "/{articleId}/author", method = RequestMethod.POST)
    public String addAuthorToArticle(@PathVariable(value = "articleId") Long articleId,
            @RequestParam(value = "authorId") Long authorId) {
        authorService.addAuthorToArticle(authorId, articleId);
        return "redirect:/articles/" + articleId;
    }

    @RequestMapping(value = "/{articleId}/edit", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable(value = "articleId") Long articleId) {
        model.addAttribute("article", articleService.findById(articleId));
        return "edit";
    }

    @RequestMapping(value = "/{articleId}/edit", method = RequestMethod.POST)
    public String editArticle(@PathVariable(value = "articleId") Long articleId, @RequestParam String title, @RequestParam String lede, @RequestParam String text, @RequestParam(value="file", required=false) MultipartFile file) throws IOException {
        articleService.editArticle(articleId, title, lede, text, file.getBytes());
        return "redirect:/articles/" + articleId;
    }

}
