package com.pipilong.task.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pipilong.domain.Item;
import com.pipilong.mapper.ItemMapper;
import com.pipilong.task.ItemTaskInterface;
import com.pipilong.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author pipilong
 * @createTime 2022/12/15
 * @description
 */
@Component
@Slf4j
public class ItemTask implements ItemTaskInterface {

    @Autowired
    private HttpUtils httpUtils;

    @Autowired
    private ItemMapper mapper;


    private static final ObjectMapper MAPPER=new ObjectMapper();

    public void itemTask(String url) throws Exception {

        log.info(url);

        for(int i=1;i<=5;i++){
            String html = httpUtils.doGetHtml(url + i);
            //解析页面，获取商品数据并存储
            this.parse(html);
        }

    }

    /**
     * 解析html
     * @param html
     */
    public void parse(String html) throws Exception {

        //解析html获取Document
        Document doc = Jsoup.parse(html);

        Elements dataProducts = doc.select("div#search_nature_rg > ul >li");

//        log.info(String.valueOf(dataProducts.size()));
//        log.info(String.valueOf(dataProducts.size()));

        for(Element dataProduct:dataProducts){
            //获取sku
            long sku = Long.parseLong(dataProduct.attr("id").substring(1));
            log.info("sku: "+String.valueOf(sku));

            Item item = new Item();
            //根据sku查询商品数据
            item.setSku(sku);
            //如果商品存在，则进行下一个循环，该商品跳过
            List<Item> list = this.mapper.findAll(item);
            if(list.size()>0) continue;

            //获取商品详情url
            String itemUrl="http://product.dangdang.com/"+sku+".html";
            item.setUrl(itemUrl);

            Elements itemInformation = dataProduct.select("a.pic");
            //获取商品标题
            String title = itemInformation.attr("title");
            item.setTitle(title);
            log.info("title: "+title);

            //获取商品图片
            Elements img = itemInformation.select("img");
            String picSrc = img.attr("data-original");
            if(picSrc.equals("")) picSrc=img.attr("src");
            String replace = picSrc.replace('b', 'w');
            String src="https:"+replace;
            String picName = this.httpUtils.doGetImage(src);
            item.setPic(picName);
            log.info("picSrc: "+src);
            log.info("picName: "+picName);

            //获取商品价格
            Element priceItem = dataProduct.select("p.price > span").first();
            Double price = Double.parseDouble(priceItem.text().substring(1));
            item.setPrice(price);
            log.info("price: "+price);

            item.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            item.setUpdated(item.getCreated());

            //保存到数据库
            this.mapper.save(item);
        }

    }


}































