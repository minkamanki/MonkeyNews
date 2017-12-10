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
//Polussa /authors tapahtuvat get, post ja deleta pyynnöt.
@Controller
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private ArticleService articleService;

    //Listataan authors.html sivulle kaikki kirjoittajat.
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("authors", authorService.list());
        return "authors";
    }

    //Otetaan vastaan uuden kirjottajan tiedot.
    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam String name) {
        authorService.add(name);
        return "redirect:/authors";
    }

    //Tiedot yhden kirjoittajan sivulle, author.html:sivulle.
    @RequestMapping(value = "/{authorId}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable(value = "authorId") Long authorId) {
        model.addAttribute("author", authorService.findById(authorId));
        model.addAttribute("articles", articleService.findByAuthor(authorId));
        return "author";
    }

    //Poistetaan kirjoittaja id:n perusteella.
    @RequestMapping(value = "/{authorId}", method = RequestMethod.DELETE)
    public String remove(@PathVariable(value = "authorId") Long authorId) {
        authorService.remove(authorId);
        return "redirect:/authors";
    }

}
