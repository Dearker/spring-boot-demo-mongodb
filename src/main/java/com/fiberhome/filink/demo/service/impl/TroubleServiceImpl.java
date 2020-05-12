package com.fiberhome.filink.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.fiberhome.filink.demo.common.constant.TroubleConstant;
import com.fiberhome.filink.demo.pojo.Trouble;
import com.fiberhome.filink.demo.repository.TroublesRepository;
import com.fiberhome.filink.demo.service.TroubleService;
import com.fiberhome.filink.demo.vo.QueryParam;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 09:28 2020/05/09
 */
@Service
public class TroubleServiceImpl implements TroubleService {

    @Autowired
    private TroublesRepository troublesRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Trouble> findByConditionAndSort(QueryParam queryParam) {

        Sort sort = new Sort(Sort.Direction.DESC, TroubleConstant.TROUBLE_LEVEL);

        Criteria criteria = Criteria.where(TroubleConstant.TROUBLE_TYPE).is("电力故障")
                .and(TroubleConstant.REPORT_USER_ID).is("abc123")
                .and(TroubleConstant.HANDLE_STATUS).is("已打回");
        Query query = new Query(criteria);

        return mongoTemplate.find(query.with(sort).limit(10), Trouble.class);
        //return mongoTemplate.find(query.with(sort), Trouble.class);
    }

    @Override
    public List<Trouble> findEntityByConditionAndSort(QueryParam queryParam) {

        DBObject query = new BasicDBObject();
        query.put(TroubleConstant.TROUBLE_TYPE, "电力故障");
        query.put(TroubleConstant.REPORT_USER_ID, "abc123");
        query.put(TroubleConstant.HANDLE_STATUS, "已打回");
        DBCursor dbCursor = mongoTemplate.getCollection("troubles").find(query)
                .sort(new BasicDBObject(TroubleConstant.TROUBLE_LEVEL, -1));

        List<Trouble> troubleList = new ArrayList<>(dbCursor.size());
        Trouble trouble = null;
        while (dbCursor.hasNext()){
            DBObject dbObject = dbCursor.next();
            trouble = BeanUtil.toBean(dbObject, Trouble.class);
            trouble.setId(dbObject.get("_id").toString());
            troubleList.add(trouble);
        }

        return troubleList;
    }

    @Override
    public List<Trouble> findByManyTroubleTypeAndSort(QueryParam queryParam) {

        Sort sort = new Sort(Sort.Direction.DESC, TroubleConstant.TROUBLE_LEVEL);

        Criteria criteria = Criteria.where(TroubleConstant.HANDLE_STATUS).is("已打回")
                .and(TroubleConstant.REPORT_USER_ID).is("abc123")
                .and(TroubleConstant.TROUBLE_TYPE).in("电力故障", "业务质量故障", "安全故障");
        Query query = new Query(criteria);

        return mongoTemplate.find(query.with(sort).limit(1000), Trouble.class);
    }

    @Override
    public long countByTroubleType(String... troubleTypes) {
        Criteria criteria = Criteria.where(TroubleConstant.TROUBLE_TYPE).is("电力故障");
        Query query = new Query(criteria);
        return mongoTemplate.count(query, Trouble.class);
    }

    @Override
    public List<Trouble> groupByTroubleType() {
        return null;
    }

}
