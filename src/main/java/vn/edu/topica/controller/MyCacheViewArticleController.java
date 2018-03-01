package vn.edu.topica.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import vn.edu.topica.model.Article;
import vn.edu.topica.service.ArticleService;
import vn.edu.topica.service.CacheService;

import java.util.List;

@Profile("cacheview")
@RestController
@Slf4j
public class MyCacheViewArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/my/articles", method = RequestMethod.GET)
    public List<Article> get() {
        try {
            return cacheService.get("TOP_ARTICLE");
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Type: TOP3, HOT5, ...
     * @param type
     * @return
     */
    @RequestMapping(value = "/my/articlesByType", method = RequestMethod.GET)
    public List<Article> getByType(@RequestParam String type) {
        try {
            return cacheService.get(type);
        } catch (Exception ex) {
            return null;
        }
    }
}
