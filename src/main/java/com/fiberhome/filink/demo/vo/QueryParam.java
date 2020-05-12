package com.fiberhome.filink.demo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 10:09 2020/05/09
 */
@Data
public class QueryParam implements Serializable {

    @NotBlank(message = "处理状态不能为空")
    private String handleStatus;

    private String reportUserId;

    private List<String> troubleTypeList;

    private String troubleLevel;

}
