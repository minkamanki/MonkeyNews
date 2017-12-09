package uutiset.service;

import java.util.List;
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

    public List<Category> list() {
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
    public void addCategoryToArticle(Long categoryId, Long articleId) {
        Category category = categoryRepository.getOne(categoryId);
        Article article = articleRepository.getOne(articleId);

        category.getArticles().add(article);
        article.getCategories().add(category);
    }
    
    @Transactional
    public Category findById(Long categoryId) {
        return categoryRepository.getOne(categoryId);
    }

    @Transactional(readOnly = true)
    public List<Category> findOtherCategories(Long articleId) {
        List<Category> without = categoryRepository.findAll();
        without.removeAll(articleRepository.getOne(articleId).getCategories());
        return without;
    }
}
