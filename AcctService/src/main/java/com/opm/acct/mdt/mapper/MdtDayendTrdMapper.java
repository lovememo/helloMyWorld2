package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtDayendTrdModel;


@Mapper
public interface MdtDayendTrdMapper {
	/**
	 * ���뽻����Ϣ
	 * @param mdtDayendTrdModel
	 * @return
	 */
	public int insertMdtDayendTrd (@Param("mdtDayendTrdModel") MdtDayendTrdModel mdtDayendTrdModel);
	
	/**
	 * ��ȡ�ս�(ȡ���ս�)������Ϣ
	 * @param trdNo
	 * @return
	 */
	public MdtDayendTrdModel getMdtDayendTrd (@Param("trdNo") String trdNo);
	
}
