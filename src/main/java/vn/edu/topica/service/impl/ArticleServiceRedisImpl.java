package vn.edu.topica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.edu.topica.model.Article;
import vn.edu.topica.repository.ArticleRepository;
import vn.edu.topica.service.ArticleService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Profile("redis")
@Service
public class ArticleServiceRedisImpl implements ArticleService {

    private static final String KEY = "articles";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Long, Article> hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public List<Article> getAll() {
        Map<Long, Article> articleMap = hashOperations.entries(KEY);
        List<Article> articles = new ArrayList<>();
        for(Article article: articleMap.values()){
            articles.add(article);
        }
        return articles;
    }

    @Override
    public Article save(Article article) {
        if(article.getId() == null){
            article.setId(System.currentTimeMillis());
        }
        hashOperations.put(KEY, article.getId(), article);
        return article;
    }

    @Override
    public void delete(Long id) {
        hashOperations.delete(KEY, id);
    }
}
