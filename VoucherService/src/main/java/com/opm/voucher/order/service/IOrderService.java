package com.opm.voucher.order.service;

import java.util.List;
import java.util.Map;

import com.opm.voucher.order.entity.DictateInf;

/**
 * Created by kfzx-liuyz1 on 2016/10/24.
 */
public interface IOrderService {
    public boolean createOrder(String orderGsonStr);
    

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
    public List<Map<String,String>> getOrderList(String orderType, String beginDate, String endDate);
    
    /**
     * 导出指令报表
     * @param dictateNo
     * @return
     */
    public DictateInf exportOrderReport(String dictateNo);
}
