package com.coding.oj.controller.blog;

import com.coding.oj.pojo.entity.LikeArticle;
import com.coding.oj.service.LikeArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class LikeArticleController {
    @Autowired
    private LikeArticleService likeArticleService;

    // 增加博文点赞
    @PostMapping(value = "/addBlogLike")
    public Map<String, Object> addArticleLike(@RequestBody LikeArticle likeArticle)
            throws JsonParseException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", likeArticleService.addLikeArticle(likeArticle));
        return modelMap;
    }

    // 取消博文点赞
    @PostMapping(value = "/dropBlogLike")
    public Map<String, Object> dropArticleLike(@RequestBody LikeArticle likeArticle)
            throws JsonParseException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", likeArticleService.dropArticleLike(likeArticle));
        return modelMap;
    }

}
