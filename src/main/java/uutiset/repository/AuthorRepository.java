package uutiset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uutiset.domain.Author;

public interface AuthorRepository  extends JpaRepository<Author, Long>{
    
}
