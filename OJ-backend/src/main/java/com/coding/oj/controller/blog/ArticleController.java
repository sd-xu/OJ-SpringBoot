package com.coding.oj.controller.blog;

import com.coding.oj.pojo.entity.Article;
import com.coding.oj.dao.ArticleService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    // 获取所有博文的信息
    @GetMapping(value = "/listArticle")
    public Map<String, Object> listArticle() {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Article> list = articleService.getArticleList();
        modelMap.put("articleList", list);
        return modelMap;
    }

    // 根据id获取单个博文的信息
    @GetMapping(value = "/getArticleById")
    public Map<String, Object> getArticleById(Long id,Integer uid) {
        Map<String, Object> modelMap = articleService.getArticleById(id,uid);
        // 获取区域列表
        return modelMap;
    }

    // 获取所有不重复技术选型
    @GetMapping(value = "/getArticleSort")
    public Map<String, Object> getArticleSort() {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<String> list = articleService.getArticleSort();
        modelMap.put("articleList", list);
        return modelMap;
    }

    // 获取所有不重复的技术选型所占的比例
    @GetMapping(value = "/getArticleSortPercent")
    public Map<String, Object> getArticleSortPercent() {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Pair<String, String>>  list = articleService.getArticleSortPercent();
        modelMap.put("articleList", list);
        return modelMap;
    }

    // 上传添加博文
    @PostMapping(value = "/addArticle")
    public Map<String, Object> addArticle(@RequestBody Article article)
            throws JsonParseException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", articleService.addArticle(article));
        return modelMap;
    }

    // 删除博文
    @GetMapping(value = "/deleteArticle")
    public Map<String, Object> deleteArticle(Long id) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        modelMap.put("success", articleService.deleteArticle(id));
        return modelMap;
    }

    // 修改博文信息
    @PostMapping(value = "/modifyArticle")
    public Map<String, Object> modifyArticle(Article article) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        modelMap.put("success", articleService.modifyArticle(article));
        return modelMap;
    }

    @GetMapping(value = "/getRecommendation")
    public Map<String, Object> getRecommendation() {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        modelMap.put("recommendationList", articleService.getRecommendation());
        return modelMap;
    }
}
