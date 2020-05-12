package com.fiberhome.filink.demo.repository;

import com.fiberhome.filink.demo.pojo.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 11:16 2020/05/09
 */
public interface ArticleRepository extends MongoRepository<Article, Long> {

    /**
     * 根据标题模糊查询
     *
     * @param title 标题
     * @return 满足条件的文章列表
     */
    List<Article> findByTitleLike(String title);

}
