package com.fiberhome.filink.demo.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.fiberhome.filink.demo.MongodbApplicationTests;
import com.fiberhome.filink.demo.common.constant.TroubleConstant;
import com.fiberhome.filink.demo.pojo.Trouble;
import com.fiberhome.filink.demo.service.TroubleService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TroublesRepositoryTest extends MongodbApplicationTests {

    @Autowired
    private TroublesRepository troublesRepository;

    @Autowired
    private TroubleService troubleService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testQuery() {

        TimeInterval timer = DateUtil.timer();
        for (int i = 0; i < 10; i++) {
            List<Trouble> troubleList = troubleService.findByConditionAndSort(null);
            //List<Trouble> troubleList = troubleService.findEntityByConditionAndSort(null);
            System.out.println(troubleList.size());
            System.out.println("消耗的时间(秒)：" + timer.intervalRestart());
        }

    }

    @Test
    public void testStartQuery() {
        TimeInterval timer = DateUtil.timer();
        DBObject query = new BasicDBObject();
        query.put(TroubleConstant.TROUBLE_TYPE, "电力故障");
        query.put(TroubleConstant.REPORT_USER_ID, "abc123");
        query.put(TroubleConstant.HANDLE_STATUS, "已打回");
        DBCursor dbCursor = null/*mongoTemplate.getCollection("troubles").find(query)
                .sort(new BasicDBObject(TroubleConstant.TROUBLE_LEVEL, -1)).limit(50000)*/;

        List<Trouble> troubleList = new ArrayList<>(50000);
        Trouble trouble = null;
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            trouble = BeanUtil.toBean(dbObject, Trouble.class);
            trouble.setId(dbObject.get("_id").toString());
            troubleList.add(trouble);
        }

        //System.out.println("获取的总数："+dbCursor.size());
        System.out.println("消耗的时间(秒)：" + timer.intervalRestart());
    }

    @Test
    public void testInQuery() {

        TimeInterval timer = DateUtil.timer();
        List<Trouble> troubleList = troubleService.findByManyTroubleTypeAndSort(null);
        System.out.println(troubleList.size());
        System.out.println("消耗的时间(秒)：" + timer.intervalRestart());

    }

    @Test
    public void testCount() {
        TimeInterval timer = DateUtil.timer();
        long count = troubleService.countByTroubleType("");
        System.out.println(count);
        System.out.println("消耗的时间(秒)：" + timer.intervalRestart());
    }

    @Test
    public void testCountByRepo() {
        TimeInterval timer = DateUtil.timer();
        long count = troublesRepository.countTroublesByTroubleType("电力故障");
        System.out.println(count);
        System.out.println("消耗的时间(秒)：" + timer.intervalRestart());
    }


}
