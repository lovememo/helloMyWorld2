package com.opm.acct.mdt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtDetTrdModel;

@Mapper
public interface MdtDetTrdMapper {
	/**
	 * ���л���ϸ����-�������л���Ƽ��˽��ױ�
	 * @param list
	 * @param trdNo
	 * @return
	 */
	public int insertMdtDetTrdForImp (@Param("list") List<MdtDetTrdModel> list, @Param("trdNo") String trdNo);
	
	/**
	 * ���л����������Ϣ��Ϣ
	 * @param trdNo
	 * @param opDate
	 * @param actId
	 * @return
	 */
	public int insertDrawForImp (@Param("trdNo") String trdNo, @Param("opDate") String opDate, @Param("actId") String actId);
	
	/**
	 * �����ϸ�ܶ�У��
	 * @return
	 */
	public String debitCreditAmtCheck(@Param("trdNo") String trdNo);
	
	/**
	 * ��Ǽ��˺��ʽУ��
	 * @param mdtSubjectClass
	 * @return
	 */
	public String debitTradeAmtCheck(@Param("trdNo") String trdNo);
	
	/**
	 * ���л���ϸ�޸�-�������л���Ƽ��˽��ױ�
	 * @param list
	 * @param trdNo
	 * @return
	 */
	public int insertMdtDetTrdForMod (@Param("list") List<MdtDetTrdModel> list, @Param("trdNo") String trdNo, @Param("actDate") String actDate);
	
	/**
	 * �ս������Ϣ��Ϣ
	 * @param trdNo
	 * @param opDate
	 * @param actId
	 * @param amt
	 * @return
	 */
	public int insertDrawForDayend (@Param("trdNo") String trdNo, @Param("opDate") String opDate, 
			@Param("actId") String actId, @Param("amt") String amt);
	/**
	 * �������л���Ƽ��˽��ױ�
	 * @param list
	 * @return
	 */
	public int insertMdtDetTrd (@Param("list") List<MdtDetTrdModel> list);
	/**
	 * ��صڶ������л����й���Ѻ��йܷѵ�������
	 * @param actId
	 * @param date
	 * @param trdNo
	 * @return
	 */
	public int reverseMdtAndTrustFee (@Param("actId") String actId,  @Param("date") String date,@Param("trdNo") String trdNo);
	/**
	 * �����ĩ�����ת
	 * @param actId
	 * @param trdNo
	 * @param date
	 * @param beginDateDraw
	 * @return
	 */
	public int reverseMdtMonAcct (@Param("actId") String actId,@Param("trdNo") String trdNo, 
			@Param("date") String date,  @Param("beginDateDraw") String beginDateDraw);
	
	/**
	 * ����-�������л���Ƽ��˽��ױ�
	 * @param trdNo
	 * @param actId
	 * @return
	 */
	public int insertMdtDetTrdForReverAct (@Param("trdNo") String trdNo, @Param("actId") String actId);
	
	/**
	 * ���л�����-�������л���Ƽ��˽��ױ�
	 * @param list
	 * @param trdNo
	 * @return
	 */
	public int insertMdtDetTrdForAcctRecord (@Param("list") List<MdtDetTrdModel> list, @Param("trdNo") String trdNo);
}
