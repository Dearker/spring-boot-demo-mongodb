package com.fiberhome.filink.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 14:39 2020/05/09
 */
@Data
@AllArgsConstructor
public class ResponseResult implements Serializable {

    private Integer code;

    private String message;

    private Object data;

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
