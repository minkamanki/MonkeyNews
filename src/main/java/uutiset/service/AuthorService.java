package uutiset.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uutiset.domain.Author;
import uutiset.domain.Article;
import uutiset.repository.ArticleRepository;
import uutiset.repository.AuthorRepository;
//Kirjoittajien service.
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ArticleRepository articleRepository;

    //Listataan kaikki kirjoittajat.
    public List<Author> list() {
        return authorRepository.findAll();
    }
    
    //Lisätään uusi kirjoittaja. Ei saman nimistä, kuin on jo luotu.
    @Transactional
    public void add(String name) {
        for(Author author : list()){
            if(author.getName().equals(name)){
                return;
            }
        }
        Author author = new Author();
        author.setName(name);

        authorRepository.save(author);

    }

    //Postetaan kirjoittaja.
    @Transactional
    public void remove(Long authorId) {
        Author author = authorRepository.getOne(authorId);
        for (Article article : author.getArticles()) {
            article.getAuthors().remove(author);
        }

        authorRepository.deleteById(authorId);
    }

    //Liitetään artikkeli ja kirjoittaja yhteen.
    @Transactional
    public void addAuthorToArticle(Long authorId, Long actorId) {
        Author author = authorRepository.getOne(authorId);
        Article article = articleRepository.getOne(actorId);

        author.getArticles().add(article);
        article.getAuthors().add(author);
    }

    //Etsitään id perusteella kirjailija.
    @Transactional
    public Author findById(Long authorId) {
        return authorRepository.getOne(authorId);
    }

//    //Etsitään nimen perusteella
//    @Transactional
//    public Author findByUsername(String username) {
//        for (Author author : authorRepository.findAll()) {
//            if (author.getName().equals(username)) {
//                return author;
//            }
//        }
//        return null;
//    }

    //Etsitään kirjoittajat joita ei vielä ole lisätty artikkeliin.
    @Transactional(readOnly = true)
    public List<Author> findOtherAuthors(Long articleId) {
        List<Author> without = authorRepository.findAll();
        without.removeAll(articleRepository.getOne(articleId).getAuthors());
        return without;
    }
}

