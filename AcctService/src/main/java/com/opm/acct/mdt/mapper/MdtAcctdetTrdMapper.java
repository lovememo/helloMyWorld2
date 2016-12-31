package com.opm.acct.mdt.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtAcctdetTrdModel;

@Mapper
public interface MdtAcctdetTrdMapper {
	
	/**
	 * ���л���ϸ�������������ļ�У��,ÿ����ϸ�ĳ��˽������˽��ֻ��һ�Ϊ��
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck1 (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ�������������ļ�У��,���˽������˽���Ϊ0
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck2 (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ�������������ļ�У��,�Է���������Ϊ-1
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck3 (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ�������������ļ�У��,�Է��˺Ų��ܰ�������
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck4 (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ�������������ļ�У��,���ڲ��ܴ��ڵ�ǰϵͳʱ��
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck5 (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ�������������ļ�У��,ȷ��״̬����Ϊ��
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck6 (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ�������������ļ�У��,��ע����Ϊ��
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck7 (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ�������������ļ�У��,��������ֻ��Ϊ�ʲ���ȡ����Ϣ���
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck8 (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ�������������ļ�У��,ÿ�����һ����ϸ���������
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck9 (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ�������������ļ�У��,������ϸ���ܿ���
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck10 (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ�������������ļ�У��,���ε����������ϸ�Ľ������ںͽ���ʱ�䣨���У����������ϴε�������һ����ϸ�Ľ������ںͽ���ʱ�䣨���У�
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck11 (@Param("trdNo") String trdNo,@Param("actId") String actId);
	/**
	 * ���л���ϸ�������������ļ�У��,�������ڲ������ڼƻ���Ϣ�е����л�������ʼ����
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck12 (@Param("trdNo") String trdNo,@Param("actId") String actId);
	
	/**
	 * ����trdNo,checkFlag��ѯ���л���ϸ���ױ��¼
	 * @param trdNo
	 * @param checkFlagMdtAcctdetTmpModel
	 * @return
	 */
	public List<MdtAcctdetTrdModel> qryMdtAcctdetTrdByTrdNoAndCheckFlag (@Param("trdNo") String trdNo,@Param("checkFlag") String checkFlag);
	
	/**
	 * �������л���ϸ���ױ�ÿ����¼У����
	 * @param list
	 * @return
	 */
	public int updCheckResultPerRecord (@Param("list")  List<MdtAcctdetTrdModel> list);
	
	/**
	 * ����trdNo�����������л���ϸ���ױ�У����
	 * @param trdNo
	 * @param checkMemo
	 * @return
	 */
	public int updCheckResultByTrdNo (@Param("trdNo") String trdNo, @Param("checkMemo") String checkMemo);
	
	/**
	 * ���л���ϸ����� ���һ����ϸ�Ľ�������
	 * @param trdNo
	 * @return
	 */
	public String getMaxOpDateByTrdNo (@Param("trdNo") String trdNo);
	
	/**
	 * ��ȡ���л���Ϣ�����еĽ�������
	 * @param trdNo
	 * @return
	 */
	public String getMdtDailyIntDrawEndDate (@Param("trdNo") String trdNo);
	
	/**
	 * ��ȡ���ε�����С��������
	 * @param trdNo
	 * @return
	 */
	public String getMinOpDate (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ������У���и��½��
	 * @param trdNo
	 * @return
	 */
	public int updateAmtImpCheck (@Param("trdNo") String trdNo, @Param("actId") String actId);
	
	/**
	 * ���л���ϸ����ǰ���У��,��������ȷ
	 * @param trdNo
	 * @return
	 */
	public int checkAmtBefImp1 (@Param("trdNo") String trdNo);
	/**
	 * ���л���ϸ����ǰ���У��,����Ϊ��
	 * @param trdNo
	 * @return
	 */
	public int checkAmtBefImp2 (@Param("trdNo") String trdNo);
	
	/**
	 * ����trdNo��ѯ���л�������ϸ��Ϣ
	 * @param trdNo
	 * @return
	 */
	public List<MdtAcctdetTrdModel> qryMdtAcctdetTrdListByTrdNo (@Param("trdNo") String trdNo);
	
	
	/**
	 * ���뽻����Ϣ
	 * @param mdtAcctdetTrdModel
	 * @return
	 */
	public int insertMdtAcctdetTrd (@Param("mdtAcctdetTrdModel") MdtAcctdetTrdModel mdtAcctdetTrdModel);
	
	/**
	 * ���л��޸� ���²����ֶ�
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public int updMdtAcctdetTrdForMod (@Param("trdNo") String trdNo, @Param("seqId") String seqId);
	
	/**
	 * ������ˮ�Ų�ѯ������Ϣ
	 * @param seqId
	 * @return
	 */
	public MdtAcctdetTrdModel getMdtAcctdetTrdBySeqId (@Param("seqId") String seqId);
	
	/**
	 * ��ȡ������ˮ��
	 * @return
	 */
	public String getTrdNo();
	
	/**
	 * �������л����ױ�
	 * @param list
	 * @return
	 */
	public int insertMdtAcctdetTrdList (@Param("serialNo") String serialNo, @Param("actId") String actId,
			@Param("trdNo") String trdNo);
	
	/**
	 * ��ȡУ�鲻�ɹ���¼��
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public int getCountsByCheckFlag (@Param("trdNo") String trdNo, @Param("checkFlag") String checkFlag);
}
