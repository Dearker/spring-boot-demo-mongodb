package com.fiberhome.filink.demo.service;

import com.fiberhome.filink.demo.pojo.Trouble;
import com.fiberhome.filink.demo.vo.QueryParam;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 09:28 2020/05/09
 */
public interface TroubleService {

    /**
     * 根据多条件排序查询
     *
     * @param queryParam
     * @return
     */
    List<Trouble> findByConditionAndSort(QueryParam queryParam);

    List<Trouble> findEntityByConditionAndSort(QueryParam queryParam);

    /**
     * 根据故障类型进行子查询
     *
     * @param queryParam
     * @return
     */
    List<Trouble> findByManyTroubleTypeAndSort(QueryParam queryParam);

    /**
     * 根据故障类型进行统计
     *
     * @param troubleTypes
     * @return
     */
    long countByTroubleType(String... troubleTypes);


    List<Trouble> groupByTroubleType();

}
