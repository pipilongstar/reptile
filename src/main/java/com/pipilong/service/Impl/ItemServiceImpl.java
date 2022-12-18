package com.pipilong.service.Impl;

import com.pipilong.service.ItemService;
import com.pipilong.task.ItemTaskInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pipilong
 * @createTime 2022/12/15
 * @description
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemTaskInterface task;

    public void search(String keyword) throws Exception {
        String url="http://search.dangdang.com/?key="+keyword+"&act=input&page_index=";
        task.itemTask(url);
    }

}


















