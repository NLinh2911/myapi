package vn.edu.topica.service;

import vn.edu.topica.model.Article;

import java.util.List;

public interface CacheService {

    List<Article> get(String key);

    void set(String key, List<Article> articles);
}
