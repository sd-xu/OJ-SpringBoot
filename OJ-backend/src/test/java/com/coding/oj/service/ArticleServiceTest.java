package com.coding.oj.service;

import com.coding.oj.pojo.entity.Article;
import org.springframework.data.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Test
    public void getArticleList() {
    }

    @Test
    public void getArticleSort() {
        List<String> list = articleService.getArticleSort();
        System.out.println(list);
        assertEquals(3,list.size());
    }

    @Test
    public void getArticleSortPercent(){
        List<Pair<String,String>> list = articleService.getArticleSortPercent();
        System.out.println(list);
        assertEquals(3,list.size());
    }
    @Test
    public void addArticle(){
        Article article = new Article();
        article.setUid(1);
        article.setTitle("ghl傻逼");
        article.setContent("xsd也是");
        article.setLikeCount(new Long(1));
        article.setCommentCount(new Long(1));
        boolean effectNum = articleService.addArticle(article);
        assertEquals(true,effectNum);
    }
    @Test
    public void getArticleById(){
        Article article =  articleService.getArticleById(4L);
        assertEquals(new Long(4),article.getId());
    }
    @Test
    public void deleteArticle(){
        boolean effectNum = articleService.deleteArticle(1L);
        assertEquals(true,effectNum);
    }
    @Test
    public void modifyArticle(){
        Article article = articleService.getArticleById(4L);
        System.out.println("----"+article);
        article.setContent("xch最帅了");
        System.out.println("----"+article);
        boolean effectNum = articleService.modifyArticle(article);
        assertEquals(true,effectNum);
    }

    @Test
    public void getRecommendation() {
        List<Article> list = articleService.getRecommendation();
        list.forEach(System.out::println);
    }
}