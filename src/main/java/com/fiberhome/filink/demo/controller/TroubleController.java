package com.fiberhome.filink.demo.controller;

import com.fiberhome.filink.demo.pojo.Trouble;
import com.fiberhome.filink.demo.service.TroubleService;
import com.fiberhome.filink.demo.vo.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wenchangwei@wistronits.com
 * @since 09:44 2020/05/09
 */
@RestController
@RequestMapping("/trouble")
@Validated
public class TroubleController {

    @Autowired
    private TroubleService troubleService;

    /**
     * 根据多条件排序查询
     *
     * @param queryParam
     * @return
     */
    @PostMapping("/findByConditionAndSort")
    public List<Trouble> findByConditionAndSort(@RequestBody /*@Valid*/ QueryParam queryParam) {
        return troubleService.findByConditionAndSort(queryParam);
    }

    /**
     * 根据故障类型进行子查询
     *
     * @param queryParam
     * @return
     */
    @PostMapping("/findByManyTroubleTypeAndSort")
    public List<Trouble> findByManyTroubleTypeAndSort(@RequestBody QueryParam queryParam) {
        return troubleService.findByManyTroubleTypeAndSort(queryParam);
    }

    /**
     * 根据故障类型进行统计
     *
     * @param troubleType
     * @return
     */
    @GetMapping("/countByTroubleType")
    public long countByTroubleType(/*@NotBlank(message = "故障类型不能为空")*/ String troubleType) {
        return troubleService.countByTroubleType(troubleType);
    }

}
