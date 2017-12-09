package uutiset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uutiset.service.ArticleService;
import uutiset.service.AuthorService;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("authors", authorService.list());
        return "authors";
    }

    @RequestMapping(value = "/authors", method = RequestMethod.POST)
    public String add(@RequestParam String name) {
        authorService.add(name);
        return "redirect:/authors";
    }

    @RequestMapping(value = "/authors/{authorId}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable(value = "authorId") Long authorId) {
        model.addAttribute("author", authorService.findById(authorId));
        return "author";
    }

    @RequestMapping(value = "/authors/{authorId}", method = RequestMethod.DELETE)
    public String remove(@PathVariable(value = "authorId") Long authorId) {
        authorService.remove(authorId);
        return "redirect:/authors";
    }



}
