package com.pipilong.controller;

import com.pipilong.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pipilong
 * @createTime 2022/12/15
 * @description
 */
@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{keyword}")
    String get(@PathVariable String keyword) throws Exception {
        itemService.search(keyword);
        return "爬取成功！";
    }

}


















