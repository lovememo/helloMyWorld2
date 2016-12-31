package com.opm.voucher.order.dao.impl;

import com.opm.voucher.order.dao.IOrderDao;
import com.opm.voucher.order.entity.DictateInf;
import com.opm.voucher.order.entity.OrderEntity;
import com.opm.voucher.order.mapper.OrderMapper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kfzx-liuyz1 on 2016/10/31.
 */
@Repository
public class OrderDao implements IOrderDao {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int insertOrder(OrderEntity order) {
        return this.orderMapper.insertOrder(order.getPlanId(),
                order.getOrderType().toString(),
                order.getAppId(),
                order.getCoPlanFlag(),
                order.getUpdateTime(),
                order.isValidFlag() ? 1 : 0
        );
    }
    
    /**
     * 接口<br> 指令生成
     * @param dictateInf
     * @return
     */
    public int insertDictate(DictateInf dictateInf){
    	return this.orderMapper.insertDictate(dictateInf);
    }

    /**
     * 接口<br> 指令blob文件生成
     * @param dictateInf
     * @return
     */
    public int updateDictate(DictateInf dictateInf){
    	return this.orderMapper.updateDictate(dictateInf);
    }
    
    /**
     * 获取指令列表
     * @param orderType
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<Map<String,String>> getOrderList(String orderType, String beginDate, String endDate){
    	return this.orderMapper.getOrderList(orderType, beginDate, endDate);
    }
    
    /**
     * 导出指令报表
     * @param dictateNo
     * @return
     */
    public DictateInf exportOrderReport(String dictateNo){
    	return this.orderMapper.exportOrderReport(dictateNo);
    }
}
