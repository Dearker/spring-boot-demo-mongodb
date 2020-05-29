package com.fiberhome.filink.demo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 11:42 2020/5/18
 */
@Data
public class BookBase implements Serializable {

    /*private String bookName;

    private Integer bookAge;*/

    /**
     * 点赞数量
     */
    private Long thumbUp;

    /**
     * 访客数量
     */
    private Long visits;

}
