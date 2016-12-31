package com.opm.acct.mdt.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.mdt.dao.IMdtDetTrdDao;
import com.opm.acct.mdt.mapper.MdtDetTrdMapper;
import com.opm.acct.mdt.model.MdtDetTrdModel;

@Repository("MdtDetTrdDao")
public class MdtDetTrdDao implements IMdtDetTrdDao {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtDetTrdDao.class);
	
	@Autowired
	private MdtDetTrdMapper mdtDetTrdMapper;

	@Override
	public int insertMdtDetTrdForImp(List<MdtDetTrdModel> list, String trdNo) {
		return mdtDetTrdMapper.insertMdtDetTrdForImp(list,trdNo);
	}

	@Override
	public String debitCreditAmtCheck(String trdNo) {
		try {
			String ret = mdtDetTrdMapper.debitCreditAmtCheck(trdNo);
			if ("1".equals(ret)) {
				return CommonConstant.RETMSG_ERROR;
			}
			return CommonConstant.RETMSG_SUCCESS;
		} catch (Exception e) {
			LOGGER.error("借贷明细总额校验异常", e);
			return CommonConstant.RETMSG_ERROR;
		}
		
	}

	@Override
	public String debitTradeAmtCheck(String trdNo) {
		try {
			String ret = mdtDetTrdMapper.debitTradeAmtCheck(trdNo);
			if (!"0".equals(ret)) {
				return CommonConstant.RETMSG_ERROR;
			}
			return CommonConstant.RETMSG_SUCCESS;
		} catch (Exception e) {
			LOGGER.error("记账会计恒等式校验异常", e);
			return CommonConstant.RETMSG_ERROR;
		}
	}

	@Override
	public int insertDrawForImp(String trdNo, String opDate, String actId) {
		return mdtDetTrdMapper.insertDrawForImp(trdNo, opDate, actId);
	}

	@Override
	public int insertMdtDetTrdForMod(List<MdtDetTrdModel> list, String trdNo, String actDate) {
		return mdtDetTrdMapper.insertMdtDetTrdForMod(list, trdNo, actDate);
	}

	@Override
	public int insertDrawForDayend(String trdNo, String opDate, String actId, String amt) {
		return mdtDetTrdMapper.insertDrawForDayend(trdNo, opDate, actId, amt);
	}

	@Override
	public int insertMdtDetTrd(List<MdtDetTrdModel> list) {
		return mdtDetTrdMapper.insertMdtDetTrd(list);
	}

	@Override
	public int reverseMdtAndTrustFee(String actId, String date, String trdNo) {
		return mdtDetTrdMapper.reverseMdtAndTrustFee(actId, date, trdNo);
	}

	@Override
	public int reverseMdtMonAcct(String actId, String trdNo, String date, String beginDateDraw) {
		return mdtDetTrdMapper.reverseMdtMonAcct(actId, trdNo, date, beginDateDraw);
	}

	@Override
	public int insertMdtDetTrdForReverAct(String trdNo, String actId) {
		return mdtDetTrdMapper.insertMdtDetTrdForReverAct(trdNo, actId);
	}

	@Override
	public String insertMdtDetTrdForAcctRecord(List<MdtDetTrdModel> list, String trdNo) {
		try {
			mdtDetTrdMapper.insertMdtDetTrdForAcctRecord(list, trdNo);
			return CommonConstant.RETMSG_SUCCESS;
		} catch (Exception e) {
			LOGGER.error("受托户记账入交易表异常", e);
			return CommonConstant.RETMSG_ERROR;
		}
	}


}
