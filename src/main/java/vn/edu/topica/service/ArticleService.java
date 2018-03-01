package vn.edu.topica.service;

import vn.edu.topica.model.Article;

import java.util.List;

public interface ArticleService {

    /**
     * Save & update function
     * @param article
     * @return
     */
    Article save(Article article);

    void delete(Long id);

    List<Article> getAll();
}
