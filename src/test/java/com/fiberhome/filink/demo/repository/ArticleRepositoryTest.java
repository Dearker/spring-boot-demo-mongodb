package com.fiberhome.filink.demo.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.fiberhome.filink.demo.MongodbApplicationTests;
import com.fiberhome.filink.demo.pojo.Article;
import com.fiberhome.filink.demo.pojo.BookBase;
import com.fiberhome.filink.demo.pojo.BookInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 11:17 2020/05/09
 */
@Slf4j
public class ArticleRepositoryTest extends MongodbApplicationTests {

    @Autowired
    private ArticleRepository articleRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Snowflake snowflake;

    /**
     * 测试新增
     */
    @Test
    public void testSave() {
        Article article = new Article(1L, RandomUtil.randomString(20), RandomUtil.randomString(150), DateUtil.date(), DateUtil
                .date(), 0L, 0L);
        articleRepo.save(article);
        log.info("【article】= {}", JSONUtil.toJsonStr(article));
    }

    /**
     * 测试新增列表
     */
    @Test
    public void testSaveList() {
        List<Article> articles = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            articles.add(new Article(snowflake.nextId(), RandomUtil.randomString(5), RandomUtil.randomString(10), DateUtil
                    .date(), DateUtil.date(), (long) i, (long) i));
        }
        articleRepo.save(articles);

        log.info("【articles】= {}", JSONUtil.toJsonStr(articles.stream()
                .map(Article::getId)
                .collect(Collectors.toList())));
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        Article article = articleRepo.findOne(1L);

        if (Objects.nonNull(article)) {
            article.setTitle(article.getTitle() + "更新之后的标题");
            article.setUpdateTime(DateUtil.date());
            articleRepo.save(article);
            log.info("更新成功 【article】= {}", JSONUtil.toJsonStr(article));
        } else {
            log.error("更新失败，数据已存在");
        }
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        // 根据主键删除
        articleRepo.delete(1L);

        // 全部删除
        //articleRepo.deleteAll();
    }

    /**
     * 测试点赞数、访客数，使用save方式更新点赞、访客
     */
    @Test
    public void testThumbUp() {

        Article article = articleRepo.findOne(1L);

        if (Objects.nonNull(article)) {
            article.setThumbUp(article.getThumbUp() + 1);
            article.setVisits(article.getVisits() + 1);
            articleRepo.save(article);
            log.info("【标题】= {}【点赞数】= {}【访客数】= {}", article.getTitle(), article.getThumbUp(), article.getVisits());
        } else {
            log.error("更新失败，数据已存在");
        }
    }

    /**
     * 测试点赞数、访客数，使用更优雅/高效的方式更新点赞、访客
     */
    @Test
    public void testThumbUp2() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(1L));
        Update update = new Update();
        update.inc("thumbUp", 1L);
        update.inc("visits", 1L);
        mongoTemplate.updateFirst(query, update, "article");

        Article article = articleRepo.findOne(1L);
        if (Objects.nonNull(article)) {
            log.info("【标题】= {}【点赞数】= {}【访客数】= {}", article.getTitle(), article.getThumbUp(), article
                    .getVisits());
        }
    }

    /**
     * 测试分页排序查询
     */
    @Test
    public void testQuery() {
        Sort sort = new Sort(Sort.Direction.DESC, "thumbUp").and(new Sort(Sort.Direction.DESC, "updateTime"));
        PageRequest pageRequest = new PageRequest(0, 5, sort);
        Page<Article> all = articleRepo.findAll(pageRequest);
        log.info("【总页数】= {}", all.getTotalPages());
        log.info("【总条数】= {}", all.getTotalElements());
        log.info("【当前页数据】= {}", JSONUtil.toJsonStr(all.getContent()
                .stream()
                .map(article -> "文章标题：" + article.getTitle() + "点赞数：" + article.getThumbUp() + "更新时间：" + article.getUpdateTime())
                .collect(Collectors.toList())));
    }

    /**
     * 测试根据标题模糊查询
     */
    @Test
    public void testFindByTitleLike() {
        List<Article> articles = articleRepo.findByTitleLike("更新");
        log.info("【articles】= {}", JSONUtil.toJsonStr(articles));
    }

    @Test
    public void updateTest() {
        Article build = Article.builder().id(2L).title("柯基111").visits(50L).build();
        articleRepo.save(build);
    }

    @Test
    public void batchUpdateTest() {

        Article build = Article.builder().id(3L).title("柯基222").visits(50L).build();
        //mongoTemplate.insert(build);
        //articleRepo.save(build);
        mongoTemplate.save(build);

        boolean exists = mongoTemplate.exists(new Query(Criteria.where("_id").is(2L)), Article.class);
        System.out.println(exists);
    }

    @Test
    public void copyTest() {

        List<Article> articleList = mongoTemplate.findAll(Article.class);

        BookInfo bookInfo = null;
        List<BookInfo> bookInfoList = new ArrayList<>(articleList.size());
        for (Article article : articleList) {
            bookInfo = new BookInfo();
            BeanUtils.copyProperties(article, bookInfo);
            this.setBase(article, bookInfo);
            bookInfoList.add(bookInfo);
        }

        bookInfoList.forEach(s -> System.out.println("获取的数据：" + s));
    }

    private void setBase(Article article, BookInfo bookBase) {
        bookBase.setThumbUps(article.getThumbUp());
        bookBase.setVisit(article.getVisits());
    }

    @Test
    public void idTest(){
        //Query query = new Query(Criteria.where("visits").is(0L).and("id").is(1262231645461811200L));
        Query query = new Query(Criteria.where("bookId").is("T5qMCuMzO0Wu8gXpkdX"));
        BookInfo one = mongoTemplate.findOne(query, BookInfo.class);
        System.out.println("获取的数据："+one);
    }

    @Test
    public void bookInfoTest(){

        mongoTemplate.save(BookInfo.builder().bookId("T5qMCuMzO0Wu8gXpkdX").content("哈哈哈").title("1212").build());

    }

}
