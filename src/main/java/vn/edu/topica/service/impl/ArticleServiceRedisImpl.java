package vn.edu.topica.service.impl;

import vn.edu.topica.model.Article;
import vn.edu.topica.service.ArticleService;

import java.util.List;

public class ArticleServiceRedisImpl implements ArticleService {

    //TODO: Use Redis to cache
    @Override
    public List<Article> getAll() {
        return null;
    }
}
