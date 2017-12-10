package uutiset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uutiset.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
//Liittää Long tyyppisen id:n artikkelliin.
}
