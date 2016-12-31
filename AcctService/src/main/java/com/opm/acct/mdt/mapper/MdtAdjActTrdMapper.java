package com.opm.acct.mdt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtAdjActTrdModel;


@Mapper
public interface MdtAdjActTrdMapper {
	/**
	 * �������л����˽��ױ�
	 * @param list
	 * @return
	 */
	public int insertMdtAdjActTrd (@Param("list") List<MdtAdjActTrdModel> list);
	
	
	/**
	 * ��ѯ����������
	 * @param trdNo
	 * @return
	 */
	public String getMaxOpDate (@Param("trdNo") String trdNo);
	
	/**
	 * ��ȡָ���ֶε����ֵ
	 * @param trdNo
	 * @param fieldName
	 * @return
	 */
	public String getMaxValueByFieldName (@Param("trdNo") String trdNo,@Param("fieldName") String fieldName);
	
	/**
	 * ��ѯ����������Ϣ
	 * @param trdNo
	 * @return
	 */
	public List<MdtAdjActTrdModel> getMdtAdjActTrd (@Param("trdNo") String trdNo);
//	/**
//	 * ��ѯ������ߴ����
//	 * @param trdNo
//	 * @param debitCreditFlag
//	 * @return
//	 */
//	public String getDebitOrCreditAmt (@Param("trdNo") String trdNo,@Param("debitCreditFlag") String debitCreditFlag);
//	
//	/**
//	 * ��ѯ������Ȩ��
//	 * @param trdNo
//	 * @return
//	 */
//	public String getCreditAmt (@Param("trdNo") String trdNo);
//	
//	/**
//	 * ��ѯ���˽�����Ϣ  �����ʲ� ,��ծ����
//	 * @param trdNo
//	 * @return
//	 */
//	public List<MdtAdjActTrdModel> getMdtAdjActTrdForCheck (@Param("trdNo") String trdNo);
	
}
