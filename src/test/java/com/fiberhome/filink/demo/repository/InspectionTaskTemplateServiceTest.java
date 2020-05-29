package com.fiberhome.filink.demo.repository;

import com.fiberhome.filink.demo.MongodbApplicationTests;
import com.fiberhome.filink.demo.pojo.many.InspectionTaskTemplate;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 9:25 2020/5/27
 */
public class InspectionTaskTemplateServiceTest extends MongodbApplicationTests {


    @Resource
    private MongoTemplate mongoTemplate;


    /**
     * mongo 4.2
     */
    @Test
    public void queryTest() {

        Query query = new Query(Criteria.where("inspectionTaskId").is("Jaliwqd3wrhhDHp2K7X"));

        InspectionTaskTemplate inspectionTaskTemplate = mongoTemplate.findOne(query, InspectionTaskTemplate.class);
        System.out.println("获取的数据：" + inspectionTaskTemplate);
    }

    /**
     * mongo 3.2
     */
    @Test
    public void queryByTest(){

        Query query = new Query(Criteria.where("inspectionTaskId").is("u1NuhL2EPgpxadPzdXZ"));

        InspectionTaskTemplate inspectionTaskTemplate = mongoTemplate.findOne(query, InspectionTaskTemplate.class);
        System.out.println("获取的数据：" + inspectionTaskTemplate);

    }

    @Test
    public void batchUpdateTest(){



    }


    //private

}
