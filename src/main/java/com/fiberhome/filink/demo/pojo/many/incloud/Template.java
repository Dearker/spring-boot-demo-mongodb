package com.fiberhome.filink.demo.pojo.many.incloud;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * 模板详情实体类
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 11:54 2020/5/25
 */
@Data
public class Template {

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 巡检模板详情
     */
    private List<InspectionItem> inspectionItemList;

}
