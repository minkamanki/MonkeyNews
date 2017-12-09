package uutiset.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uutiset.domain.Article;
import uutiset.domain.Author;
import uutiset.domain.Category;
import uutiset.domain.Picture;
import uutiset.repository.ArticleRepository;
import uutiset.repository.AuthorRepository;
import uutiset.repository.CategoryRepository;
import uutiset.repository.PictureRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
//    @Autowired
//    private AuthorRepository authorRepository;
//    @Autowired
//    private CategoryRepository categoryRepository;
    @Autowired
    private PictureRepository pictureRepository;

    public List<Article> list() {
        return articleRepository.findAll();
    }

    @Transactional
    public void add(String title, String lede, String text, byte[] content) {
        Picture p = new Picture();
        p.setContent(content);
        
        Article article = new Article();
        article.setTitle(title);
        article.setLede(lede);
        article.setText(text);
        article.setContent(p); 
        article.setDate(new Date());
        article.setReadCount(0); 
        pictureRepository.save(p);
        articleRepository.save(article);        
    }

    @Transactional
    public Article findById(Long id) {
        return articleRepository.findById(id).get();
    }

    @Transactional
    public void remove(Long articleId) {
        Article article = articleRepository.getOne(articleId);
        for (Author author : article.getAuthors()) {
            author.getArticles().remove(article);
        }
        for (Category categoty : article.getCategories()) {
            categoty.getArticles().remove(article);
        }
        articleRepository.delete(article);
    }

    public  List<Article>  findByCategory(String name) {
        List<Article> articles = list();
        List<Article> articlesWhitCategory = new ArrayList<>(); 
        for(Article a : articles){
            List<Category> gategories = a.getCategories();
            for(Category g : gategories){
                if (g.getName().equals(name)){
                    articlesWhitCategory.add(a);
                }
            }
        }
        return articlesWhitCategory;
    }

    public List<Article> listHomePage() {
        return articleRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, "date")).getContent();
    }

    public List<Article> mostReadArticles() {
        return articleRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, "readCount")).getContent();
    }

}