package uutiset.service;

import java.util.ArrayList;
import java.util.Collections;
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
import uutiset.repository.PictureRepository;

//Artikkeli service.
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private AuthorRepository authorRepository;

    
    //Listataan artikkelit.
    public List<Article> list() {
        List<Article> articles = articleRepository.findAll();
        Collections.sort(articles,  (a,b) -> b.getDate().compareTo(a.getDate()));
        return articles;
    }

    //Lisätään uusi artikkeli, näillä tiedoilla.
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

    //Etsitään id:llä. 
    @Transactional
    public Article findById(Long id) {
        return articleRepository.findById(id).get();
    }

    //Poistetaan artikkeli, ja liitokset kategoriasta ja kirjoittajista.
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

    //Etsitään kategorian nimen perusteella artikkelit.
    public List<Article> findByCategory(String name) {
        List<Article> articles = list();
        List<Article> articlesWithCategory = new ArrayList<>();
        for (Article a : articles) {
            List<Category> gategories = a.getCategories();
            for (Category g : gategories) {
                if (g.getName().equals(name)) {
                    articlesWithCategory.add(a);
                }
            }
        }
        Collections.sort(articlesWithCategory, (a,b) -> b.getDate().compareTo(a.getDate()));
        return articlesWithCategory;
    }

    //Listaus etusivulle menevistä artikkeleista: viisi uusinta.
    public List<Article> listHomePage() {
        return articleRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, "date")).getContent();
    }

    //Listaus eniten luetuista artikkeleista.
    public List<Article> mostReadArticles() {
        return articleRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, "readCount")).getContent();
    }

    //Editoifaan artikkeli
    public void editArticle(Long articleId, String title, String lede, String text, byte[] bytes) {
        Article article = findById(articleId);
        article.setTitle(title);
        article.setLede(lede);
        article.setText(text);
        //Jos uutta kuvaa ei lisätty, säilyy vanha kuva.
        if (bytes.length > 0) {
            article.getContent().setContent(bytes);
        }
        articleRepository.save(article);
    }

    //Listataan 30 uusinta artikkelia.
    public List<Article> newestArticles() {
        return articleRepository.findAll(PageRequest.of(0, 30, Sort.Direction.DESC, "date")).getContent();
    }

    //Etsitään kirjoittajan id:llä artikkelit.
    public List<Article> findByAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).get();
        List< Article> articles = list();
        List<Article> articlesWhitAuthor = new ArrayList<>();
        for (Article article : articles) {
            if (article.getAuthors().contains(author)) {
                articlesWhitAuthor.add(article);
            }
        }
        return articlesWhitAuthor;
    }

}
