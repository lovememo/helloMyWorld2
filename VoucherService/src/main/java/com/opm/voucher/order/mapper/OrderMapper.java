package com.opm.voucher.order.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.opm.voucher.order.entity.DictateInf;

/**
 * Created by kfzx-liuyz1 on 2016/10/28.
 */
public interface OrderMapper {
    public int insertOrder(@Param("planId")String planId,
                           @Param("orderType")String orderType,
                           @Param("appId")long appId,
                           @Param("coPlanFlag")String coPlanFlag,
                           @Param("updateTime")Date updateTime,
                           @Param("validFlag") int validFlag);
    
    /**
     * 接口<br> 指令生成
     * @param dictateInf
     * @return
     */
    public int insertDictate(DictateInf dictateInf);

    /**
     * 接口<br> 指令blob文件生成
     * @param dictateInf
     * @return
     */
    public int updateDictate(DictateInf dictateInf);
    
    /**
     * 获取指令列表
     * @param orderType
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<Map<String,String>> getOrderList(@Param("orderType")String orderType,
    											 @Param("beginDate")String beginDate,
    											 @Param("endDate")String endDate);
    
    /**
     * 导出指令报表
     * @param dictateNo
     * @return
     */
    public DictateInf exportOrderReport(@Param("dictateNo")String dictateNo);
 }
