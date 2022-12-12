package com.coding.oj.controller.blog;

import com.coding.oj.pojo.entity.Star;
import com.coding.oj.dao.StarArticleService;

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
public class StarController {
    @Autowired
    private StarArticleService starArticleService;

    // 增加收藏
    @PostMapping(value = "/addSubscribe")
    public Map<String, Object> addStar(@RequestBody Star star)
            throws JsonParseException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", starArticleService.addStar(star));
        return modelMap;
    }

    // 取消收藏
    @PostMapping(value = "/dropSubscribe")
    public Map<String, Object> dropStar(@RequestBody Star star)
            throws JsonParseException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", starArticleService.dropStar(star));
        return modelMap;
    }

}
