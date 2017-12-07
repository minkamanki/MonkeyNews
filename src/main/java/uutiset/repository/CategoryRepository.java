package uutiset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uutiset.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
