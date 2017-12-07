package uutiset.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uutiset.domain.Article;
import uutiset.domain.Author;
import uutiset.repository.ArticleRepository;
import uutiset.repository.AuthorRepository;
import uutiset.repository.CategoryRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Article> list() {
        return articleRepository.findAll();
    }

    @Transactional
    public void add(String title, String lede, String text, byte[] content) {
        Article article = new Article();
        article.setTitle(title);
        article.setLede(lede);
        article.setText(text);
        article.setContent(content);
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

        articleRepository.delete(article);
    }

    @Transactional(readOnly = true)
    public List<Article> listArticlesWithoutThisAuthor(Long authorId) {
        List<Article> without = articleRepository.findAll();
        without.removeAll(authorRepository.getOne(authorId).getArticles());

        return without;
    }

    public Object listArticlesWithoutThisCategory(Long categoryId) {
        List<Article> without = articleRepository.findAll();
        without.removeAll(categoryRepository.getOne(categoryId).getArticles());
        return without;
    }
}
