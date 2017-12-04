package uutiset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uutiset.domain.Author;
import uutiset.domain.Article;
import uutiset.repository.ArticleRepository;
import uutiset.repository.AuthorRepository;

@Service
public class AuthorService {
      @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ArticleRepository articleRepository;
 
    public Iterable<Author> list() {
        return authorRepository.findAll();
    }
 
    @Transactional
    public void add(String name) {
        Author author = new Author();
        author.setName(name);
 
        authorRepository.save(author);
 
    }
 
    @Transactional
    public void remove(Long authorId) {
        Author author = authorRepository.getOne(authorId);
        for (Article article : author.getArticles()) {
            article.getAuthors().remove(author);
        }
 
        authorRepository.deleteById(authorId);
    }
 
    @Transactional
    public void addAuthorToArticle(Long authorId, Long actorId) {
        Author author = authorRepository.getOne(authorId);
        Article article = articleRepository.getOne(actorId);
 
        author.getArticles().add(article);
        article.getAuthors().add(author);
    }
 
    public Author findById(Long authorId) {
        return authorRepository.getOne(authorId);
    }
}
