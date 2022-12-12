package com.coding.oj.controller.blog;


import com.coding.oj.pojo.entity.LikeComment;
import com.coding.oj.service.LikeCommentService;

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
public class LikeCommentController {
    @Autowired
    private LikeCommentService likeCommentService;

    // 增加评论点赞
    @PostMapping(value = "/addCommentLike")
    public Map<String, Object> addCommentLike(@RequestBody LikeComment likeComment)
            throws JsonParseException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", likeCommentService.addLikeComment(likeComment));
        return modelMap;
    }

    // 取消评论点赞
    @PostMapping(value = "/dropCommentLike")
    public Map<String, Object> dropCommentLike(@RequestBody LikeComment likeComment)
            throws JsonParseException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", likeCommentService.dropCommentLike(likeComment));
        return modelMap;
    }

}
