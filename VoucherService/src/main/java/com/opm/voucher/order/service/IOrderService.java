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
