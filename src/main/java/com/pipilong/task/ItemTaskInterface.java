package com.pipilong.task;

/**
 * @author pipilong
 * @createTime 2022/12/17
 * @description
 */
public interface ItemTaskInterface {

    void itemTask(String url) throws Exception;

    void parse(String html) throws Exception;
}
