package com.fiberhome.filink.demo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.fiberhome.filink.demo.pojo.Article;
import com.fiberhome.filink.demo.pojo.BookInfo;
import org.junit.Test;

import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 15:29 2020/5/14
 */
public class Demo {

    @Test
    public void test01() {
        Article build = Article.builder().id(1L).title("柯基").build();
        Article article = Article.builder().id(1L).title("柯基").build();
        if (article.equals(build)) {
            System.out.println("相等");
        }
    }

    @Test
    public void setTest() {
        long time = DateUtil.parse("2020-05-30").getTime();
        //System.out.println(time);

        Integer l = Math.toIntExact((time - System.currentTimeMillis()) / (1000 * 3600 * 24));
        int l1 = (int) ((time - System.currentTimeMillis()) / (1000 * 3600 * 24));
        int i = Math.toIntExact((long) 1.9);

        Map<String,String> stringMap = new HashMap<>();
        stringMap.put(null,null);
        if(stringMap.isEmpty()){
            System.out.println("为空");
        }

        if(CollUtil.isEmpty(stringMap)){
            System.out.println("为空");
        }
    }

    @Test
    public void test2(){

        List<BookInfo> bookInfoList = new ArrayList<>(7);

        bookInfoList.add(null);

        if(CollUtil.isEmpty(bookInfoList)){
            System.out.println("数组为空");
        }

        bookInfoList.forEach(s-> System.out.println("获取的数据："+s));

        if(7 > 7){
            System.out.println("大于");
        }

    }

    @Test
    public void test1(){

        BookInfo bookInfo = new BookInfo("1","柯基","看看看",0L,1L);

        BookInfo info = new BookInfo();
        info.setContent("啦啦啦");
        info.setTitle("哈士奇");

        BookInfo convert = this.convert(bookInfo, info, BookInfo.class);
        System.out.println("获取的数据："+convert);
    }


    private <T>T convert(Object oldOrder,Object updateOrder,Class<T> clazz){
        Map<String, Object> oldMap = BeanUtil.beanToMap(oldOrder);
        Map<String, Object> updateMap = BeanUtil.beanToMap(updateOrder);

        for (Map.Entry<String, Object> entry : updateMap.entrySet()) {
            if (Objects.isNull(entry.getValue()) && Objects.nonNull(oldMap.get(entry.getKey()))) {
                entry.setValue(oldMap.get(entry.getKey()));
            }
        }
        return BeanUtil.mapToBean(updateMap, clazz, true);
    }

}
