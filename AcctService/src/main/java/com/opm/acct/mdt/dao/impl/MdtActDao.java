package com.opm.acct.mdt.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.mdt.dao.IMdtActDao;
import com.opm.acct.mdt.mapper.MdtActMapper;

@Repository("MdtActDao")
public class MdtActDao implements IMdtActDao {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtDetDao.class);
	@Autowired
	private MdtActMapper mdtActMapper;

	@Override
	public String insertMdtAct(String actId) {
		try {
			mdtActMapper.insertMdtAct(actId);
			return CommonConstant.RETMSG_SUCCESS;
		} catch (Exception e) {
			LOGGER.error("入受托户会计余额账表异常", e);
			return CommonConstant.RETMSG_ERROR;
		}
	}


}
