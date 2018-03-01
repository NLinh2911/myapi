package vn.edu.topica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import vn.edu.topica.model.Article;
import vn.edu.topica.service.ArticleService;
import vn.edu.topica.service.CacheService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    private static final String KEY_PREFIX = "cache";

    ObjectMapper objectMapper =  new ObjectMapper();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private SetOperations<String, Object> valueOperations;

    @Autowired
    private ArticleService articleService;

    @PostConstruct
    private void init() {
        valueOperations = redisTemplate.opsForSet();
    }

    @Override
    public List<Article> get(String key) {
        List<Article> articles;
        try{
            Object object = valueOperations.randomMember(KEY_PREFIX + key);
            //check if cache exist
            if(object != null){
                //if true return cache
                String json = (String)object;
                articles = objectMapper.readValue(json, List.class);
                return articles;
            }else {
                //if false get from database, insert to cache then return
                articles = articleService.getAll();
                this.set(key, articles);
                return articles;
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public void set(String key, List<Article> articles) {
        try {
            String json = objectMapper.writeValueAsString(articles);
            valueOperations.add(KEY_PREFIX + key, json);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }
}
