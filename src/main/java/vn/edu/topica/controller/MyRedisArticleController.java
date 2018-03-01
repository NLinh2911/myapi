package vn.edu.topica.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import vn.edu.topica.model.Article;
import vn.edu.topica.service.ArticleService;

import java.util.List;

@Profile("redis")
@RestController
@Slf4j
public class MyRedisArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public List<Article> get() {
        try {
            return articleService.getAll();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    public void save(@RequestBody Article article) {
        try {
            articleService.save(article);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.POST)
    public void delete(@PathVariable Long id) {
        try {
            articleService.delete(id);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
