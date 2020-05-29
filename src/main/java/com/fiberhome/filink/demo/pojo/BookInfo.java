package com.fiberhome.filink.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 11:43 2020/5/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo /*extends BookBase*/ implements Serializable {

    @Id
    private String bookId;

    private String title;

    private String content;

    /**
     * 点赞数量
     */
    private Long thumbUps;

    /**
     * 访客数量
     */
    private Long visit;
}
