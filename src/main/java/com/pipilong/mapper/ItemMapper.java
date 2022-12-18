package com.pipilong.mapper;

import com.pipilong.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @author pipilong
 * @createTime 2022/12/15
 * @description
 */
@Mapper
public interface ItemMapper {

    void save(Item item);

    @Transactional
    List<Item> findAll(Item item);

}
