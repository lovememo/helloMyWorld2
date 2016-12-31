package com.opm.acct.mdt.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.acct.mdt.dao.ITrdDao;
import com.opm.acct.mdt.mapper.TrdMapper;
import com.opm.acct.mdt.model.TrdModel;

@Repository("TrdDao")
public class TrdDao implements ITrdDao {
	
	@Autowired
	private TrdMapper trdMapper;

	@Override
	public void addTrd(TrdModel trdModel) {
		this.trdMapper.addTrd(trdModel);
	}

	@Override
	public int qryTrdCount(TrdModel trdModel) {
		return this.trdMapper.qryTrd(trdModel).size();
	}

	@Override
	public void updTrd(TrdModel trdModel) {
		this.trdMapper.updTrd(trdModel);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List qryTrd(TrdModel trdModel) {
		return this.trdMapper.qryTrd(trdModel);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public TrdModel qryTrdByTrdNo(String trdNo) {
		TrdModel resTrdModel = null;
		TrdModel qryTrdModel = new TrdModel();
		qryTrdModel.setTrdNo(trdNo);
		List trdList = this.trdMapper.qryTrd(qryTrdModel);
		if ( trdList != null && trdList.size() == 1 ) {
			resTrdModel = (TrdModel) trdList.get(0);
		}
		return resTrdModel;
	}

	public TrdMapper getTrdMappping() {
		return trdMapper;
	}

	public void setTrdMappping(TrdMapper trdMappping) {
		this.trdMapper = trdMappping;
	}

}
