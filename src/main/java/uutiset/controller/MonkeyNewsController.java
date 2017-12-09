package uutiset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonkeyNewsController {

//
//
//    @Autowired
//    private AuthorService authorService;
//
//    @PostConstruct
//    public void init() {
//        if (authorService.findByUsername("M. Mankinen") != null) {
//            return;
//        }
//        Author user = new Author();
//        user.setName("M. Mankinen");
//        user.setPassword("salasana");
//        authorService.save(user);
//    }

    @GetMapping("*")
    public String menu() {
        return "news";
    }

//    @Autowired
//    private ArticleRepository aRepo;
//
//    @GetMapping("/")
//    public String list(Model model) {
//        model.addAttribute("news", aRepo.findAll());
//        return "news";
//    }
//
//    @GetMapping("/{id}")
//    public String list(Model model, @PathVariable Long id) {
//        model.addAttribute("article", aRepo.findById(id).get());
//        return "article";
//    }
//
//    @PostMapping("/")
//    public String add(@RequestParam String title, @RequestParam String lede, @RequestParam String text, @RequestParam("file") MultipartFile file) throws IOException {
//
////        if(!file.getContentType().contentEquals("image/png") || !file.getContentType().contentEquals("image/jpeg")){
////            return "redirect:/";
////        }
//        Article article = new Article();
//        article.setTitle(title);
//        article.setLede(lede);
//        article.setText(text);
//        article.setContent(file.getBytes());
//
//        aRepo.save(article);
//        return "redirect:/";
//    }
//
//    @GetMapping(path = "/{id}/content", produces = "image/png")
//    @ResponseBody
//    public byte[] getContent(@PathVariable Long id) {
//        return aRepo.getOne(id).getContent();
//    }
}
