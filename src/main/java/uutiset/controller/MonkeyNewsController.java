package uutiset.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import uutiset.domain.Article;
import uutiset.repository.ArticleRepository;

@Controller
public class MonkeyNewsController {

    @Autowired
    private ArticleRepository aRepo;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("news", aRepo.findAll());
        return "news";
    }

    @GetMapping("/{id}")
    public String list(Model model, @PathVariable Long id) {
        model.addAttribute("article", aRepo.findById(id).get());
        return "article";
    }

    @PostMapping("/")
    public String add(@RequestParam String title, @RequestParam String text, @RequestParam("file") MultipartFile file) {

//        if(!file.getContentType().contentEquals("image/png") || !file.getContentType().contentEquals("image/jpeg")){
//            return "redirect:/";
//        }
        Article article = new Article();
        article.setTitle(title);
        article.setText(text);
        article.setContent(file.getBytes());

        aRepo.save(article);
        return "redirect:/";
    }

    @GetMapping(path = "/{id}/content", produces = "image/png")
    @ResponseBody
    public byte[] getContent(@PathVariable Long id) {
        return aRepo.getOne(id).getContent();
    }
}
