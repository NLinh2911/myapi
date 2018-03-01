package vn.edu.topica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.topica.model.Article;
import vn.edu.topica.service.ArticleService;

import java.util.List;

@Profile("cachedb")
@RestController
public class MyCacheDBController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public List<Article> myArticles() {
        try {
            return articleService.getAll();
        } catch (Exception ex) {
            return null;
        }
    }
}
