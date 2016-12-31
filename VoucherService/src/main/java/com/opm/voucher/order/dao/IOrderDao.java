package com.opm.voucher.order.dao;

import java.util.List;
import java.util.Map;

import com.opm.voucher.order.entity.DictateInf;
import com.opm.voucher.order.entity.OrderEntity;

/**
 * Created by kfzx-liuyz1 on 2016/10/31.
 */
public interface IOrderDao {
    public int insertOrder(OrderEntity order);
    
    /**
     * �ӿ�<br> ָ������
     * @param dictateInf
     * @return
     */
    public int insertDictate(DictateInf dictateInf);

    /**
     * �ӿ�<br> ָ��blob�ļ�����
     * @param dictateInf
     * @return
     */
    public int updateDictate(DictateInf dictateInf);
    
    /**
     * ��ȡָ���б�
     * @param orderType
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<Map<String,String>> getOrderList(String orderType, String beginDate, String endDate);
    
    /**
     * ����ָ���
     * @param dictateNo
     * @return
     */
    public DictateInf exportOrderReport(String dictateNo);
}
