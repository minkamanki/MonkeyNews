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
//Servise luokka categorioille.

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ArticleRepository articleRepository;

    //Listataan kaikki tietokannasta löytyvät kategoriat.

    public List<Category> list() {
        return categoryRepository.findAll();
    }

    //Listataan kategoriat jotka on merkitty kuulumaan navikaatio palkkiin.
    public List<Category> listForNav() {

        List<Category> navCategories = new ArrayList<>();
        for (Category c : categoryRepository.findAll()) {
            if (c.isNav()) {
                navCategories.add(c);
            }
        }

        return navCategories;
    }

    //Lisätään uusi kategoria. Ei saman nimistä, kuin on jo luotu.
    @Transactional
    public void add(String name, String checkbox) {
        for (Category category : list()) {
            if (category.getName().equals(name)) {
                return;
            }
        }
        Category category = new Category();
        category.setName(name);
        //Jon checkbox on rastitettu lisätään nav true = kuuluu nav palkkiin.
        if (checkbox != null) {
            category.setNav(true);
        } else {
            category.setNav(false);
        }

        categoryRepository.save(category);

    }

    //Poistetaan id:tä vastaava kategoria.
    @Transactional
    public void remove(Long categoryId) {
        Category category = categoryRepository.getOne(categoryId);
        for (Article article : category.getArticles()) {
            article.getCategories().remove(category);
        }

        categoryRepository.deleteById(categoryId);
    }

    //Lisätään yhteys artikkelin ja kategorian välille.
    @Transactional
    public void addCategoryToArticle(Long categoryId, Long articleId) {
        Category category = categoryRepository.getOne(categoryId);
        Article article = articleRepository.getOne(articleId);

        category.getArticles().add(article);
        article.getCategories().add(category);
    }

    //Etsitään kategoria id:n perusteella
    @Transactional
    public Category findById(Long categoryId) {
        return categoryRepository.getOne(categoryId);
    }

    //Etsitään vain ne kategoriat joita ei ole artikkelilla.
    @Transactional(readOnly = true)
    public List<Category> findOtherCategories(Long articleId) {
        List<Category> without = categoryRepository.findAll();
        without.removeAll(articleRepository.getOne(articleId).getCategories());
        return without;
    }

}
