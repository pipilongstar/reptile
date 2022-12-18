package com.pipilong.service;

import com.pipilong.domain.Item;

import java.util.List;

/**
 * @author pipilong
 * @createTime 2022/12/15
 * @description
 */
public interface ItemService {

    /**
     * 根据关键字爬取数据
     * @param keyword
     */
    void search(String keyword) throws Exception;


}
