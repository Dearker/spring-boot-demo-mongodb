package com.fiberhome.filink.demo.pojo.many.incloud;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 巡检模板详情实体类
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 11:58 2020/5/25
 */
@Data
public class InspectionItem implements Serializable {

    /**
     * 模板详情id
     */
    private String templateItemId;

    /**
     * 模板详情名称
     */
    private String templateItemName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer sort;
}
