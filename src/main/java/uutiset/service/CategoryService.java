package uutiset.service;

import java.util.ArrayList;
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

    public List<Category> listForNav() {

        List<Category> navCategories = new ArrayList<>();
        for (Category c : categoryRepository.findAll()) {
            if (c.isNav()) {
                navCategories.add(c);
            }
        }

        return navCategories;
    }

    @Transactional
    public void add(String name, String checkbox) {
        Category category = new Category();
        category.setName(name);
        if (checkbox != null) {
            category.setNav(true);
        } else {
            category.setNav(false);
        }

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
