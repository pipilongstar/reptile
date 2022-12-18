package com.pipilong.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author pipilong
 * @createTime 2022/12/15
 * @description
 */
@Data
@Component
public class Item {
    private Long id;
    private String title;
    private Double price;
    private String pic;
    private String url;
    private String created;
    private String updated;
    private Long spu;
    private Long sku;
}
