package vn.edu.topica.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.topica.model.Article;
import vn.edu.topica.service.ArticleService;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    //TODO
    @RequestMapping(value = "/my/articles", method = RequestMethod.GET)
    public List<Article> myArticles() {
        try {
            return articleService.getAll();
        } catch (Exception ex) {
            return null;
        }
    }
}
