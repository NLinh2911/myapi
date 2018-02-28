package vn.edu.topica.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vn.edu.topica.model.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

}
