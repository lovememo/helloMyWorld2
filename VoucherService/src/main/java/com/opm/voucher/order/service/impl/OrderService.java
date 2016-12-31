package com.opm.voucher.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.common.enumdict.OrderType;
import com.opm.voucher.order.dao.IOrderDao;
import com.opm.voucher.order.entity.DictateInf;
import com.opm.voucher.order.entity.OrderEntity;
import com.opm.voucher.order.service.IOrderService;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-liuyz1 on 2016/10/24.
 */
@Service("OrderService")
public class OrderService implements IOrderService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private IOrderDao orderDao;

    @Override
    public boolean createOrder(String orderGsonStr) {
        LOGGER.debug("OrderService createOrder");
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setAppId(99);
        orderEntity.setPlanId("1212");
        orderEntity.setOrderType(OrderType.PAY);
        orderEntity.setCoPlanFlag("a");
        orderEntity.setUpdateTime(new Date());
        orderEntity.setValidFlag(true);
        int resCnt = this.orderDao.insertOrder(orderEntity);
        return resCnt == 1;
    }
    

    /**
     * 接口<br> 指令生成
     * @param dictateInf
     * @return
     */
    public int insertDictate(DictateInf dictateInf){
    	return this.orderDao.insertDictate(dictateInf);
    }

    /**
     * 接口<br> 指令blob文件生成
     * @param dictateInf
     * @return
     */
    public int updateDictate(DictateInf dictateInf){
    	return this.orderDao.updateDictate(dictateInf);
    }
    
    /**
     * 获取指令列表
     * @param orderType
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<Map<String,String>> getOrderList(String orderType, String beginDate, String endDate){
    	return this.orderDao.getOrderList(orderType, beginDate, endDate);
    }
    
    /**
     * 导出指令报表
     * @param dictateNo
     * @return
     */
    public DictateInf exportOrderReport(String dictateNo){
    	return this.orderDao.exportOrderReport(dictateNo);
    }
}
