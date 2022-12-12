package com.coding.oj.controller.blog;

import com.coding.oj.pojo.entity.Comment;
import com.coding.oj.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 添加博文评论
    @PostMapping(value = "/addComment")
    public Map<String, Object> addComment(@RequestBody Comment comment)
            throws JsonParseException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", commentService.addComment(comment));
        return modelMap;
    }

    // 删除博文评论
    @GetMapping(value = "/deleteComment")
    public Map<String, Object> deleteComment(Long id) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        modelMap.put("success",commentService.deleteComment(id));
        return modelMap;
    }

    // 修改博文评论
    @PostMapping(value = "/modifyComment")
    public Map<String, Object> modifyComment(Comment comment) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        modelMap.put("success",commentService.modifyComment(comment));
        return modelMap;
    }

    // 通过aid获取article对应的评论
    @GetMapping(value = "/getArticleComment")
    public Map<String, Object> getArticleComment(Long aid, Integer pageNum, Integer pageSize) {
        Map<String, Object> modelMap = new HashMap<>();
        List<Comment> list = commentService.getCommentByAid(aid, pageNum, pageSize);
        // 获取区域列表
        modelMap.put("commentList", list);
        return modelMap;
    }

    // 获取当前评论的所有子评论
    @GetMapping(value = "/getChildComment")
    public Map<String,Object> getChildComment(Long parentId){
        Map<String, Object> modelMap = new HashMap<>();
        List<Comment> list = commentService.getChildComment(parentId);
        // 获取区域列表
        modelMap.put("commentList",list);
        return modelMap;
    }
}
