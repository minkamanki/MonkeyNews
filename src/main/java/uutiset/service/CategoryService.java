package uutiset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uutiset.domain.Article;
import uutiset.domain.Category;
import uutiset.repository.ArticleRepository;
import uutiset.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public Iterable<Category> list() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void add(String name) {
        Category category = new Category();
        category.setName(name);

        categoryRepository.save(category);

    }

    @Transactional
    public void remove(Long categoryId) {
        Category category = categoryRepository.getOne(categoryId);
        for (Article article : category.getArticles()) {
            article.getCategories().remove(category);
        }

        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    public void addCategoryToArticle(Long categoryId, Long actorId) {
        Category category = categoryRepository.getOne(categoryId);
        Article article = articleRepository.getOne(actorId);

        category.getArticles().add(article);
        article.getCategories().add(category);
    }
    
    @Transactional
    public Category findById(Long categoryId) {
        return categoryRepository.getOne(categoryId);
    }

}
