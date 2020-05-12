package com.fiberhome.filink.demo.repository;

import com.fiberhome.filink.demo.pojo.Trouble;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 16:24 2020/05/08
 */
public interface TroublesRepository extends MongoRepository<Trouble, String> {

    /**
     * 条件查询
     * @param handleStatus
     * @param reportUserId
     * @param troubleType
     * @return
     */
    List<Trouble> findByHandleStatusAndReportUserIdAndTroubleType(String handleStatus,String reportUserId,String troubleType);

    /**
     * 统计对应故障类型的总数
     *
     * @param troubleType
     * @return
     */
    long countTroublesByTroubleType(String troubleType);

}
