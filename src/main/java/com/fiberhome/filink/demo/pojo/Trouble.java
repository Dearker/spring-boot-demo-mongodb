package com.fiberhome.filink.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "troubles")
public class Trouble implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("updateTime")
    private String updateTime;

    @Field("createTime")
    private String createTime;

    @Field("trouble_code")
    private String troubleCode;

    @Field("handle_status")
    private String handleStatus;

    @Field("trouble_type")
    private String troubleType;

    @Field("trouble_level")
    private String troubleLevel;

    @Field("trouble_source")
    private String troubleSource;

    @Field("trouble_area")
    private String troubleArea;

    @Field("device_name")
    private String deviceName;

    @Field("device_id")
    private String deviceId;

    @Field("equipment_name")
    private String equipmentName;

    @Field("equipment_id")
    private String equipmentId;

    @Field("trouble_describe")
    private String troubleDescribe;

    @Field("report_user_id")
    private String reportUserId;

    @Field("report_user_name")
    private String reportUserName;

    @Field("happen_time")
    private String happenTime;

    @Field("duty_dept_id")
    private String dutyDeptId;

    @Field("duty_dept_name")
    private String dutyDeptName;

    @Field("handle_time")
    private String handleTime;

    @Field("current_handle_progress")
    private String currentHandleProgress;

    @Field("trouble_remark")
    private String troubleRemark;

    @Field("is_delete")
    private Integer deleteStatus;

    @Field("create_user")
    private String createUser;

    @Field("update_user")
    private String updateUser;

}
