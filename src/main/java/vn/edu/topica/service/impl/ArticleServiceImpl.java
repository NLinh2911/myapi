package vn.edu.topica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import vn.edu.topica.model.Article;
import vn.edu.topica.repository.ArticleRepository;
import vn.edu.topica.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

@Profile("cachedb")
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void delete(Long id) {
        articleRepository.delete(id);
    }

    @Override
    @CacheEvict(value="articles")
    public List<Article> getAll() {
        List<Article> articles = new ArrayList<>();
        for(Article article: articleRepository.findAll()){
            articles.add(article);
        }
        return articles;
    }
}
