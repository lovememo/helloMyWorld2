package com.opm.core.app.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.app.dao.ITrdDao;
import com.opm.core.app.mapper.TrdMapper;
import com.opm.core.app.model.TrdModel;

@Repository("TrdDao")
public class TrdDao implements ITrdDao {
	
	@Autowired
	private TrdMapper trdMappping;

	@Override
	public void addTrd(TrdModel trdModel) {
		this.trdMappping.addTrd(trdModel);
	}

	@Override
	public int qryTrdCount(TrdModel trdModel) {
		return this.trdMappping.qryTrd(trdModel).size();
	}

	@Override
	public void updTrd(TrdModel trdModel) {
		this.trdMappping.updTrd(trdModel);
	}

	@Override
	public List qryTrd(TrdModel trdModel) {
		return this.trdMappping.qryTrd(trdModel);
	}

	@Override
	public TrdModel qryTrdByTrdNo(String trdNo) {
		TrdModel resTrdModel = null;
		TrdModel qryTrdModel = new TrdModel();
		qryTrdModel.setTrdNo(trdNo);
		List trdList = this.trdMappping.qryTrd(qryTrdModel);
		if ( trdList != null && trdList.size() == 1 ) {
			resTrdModel = (TrdModel) trdList.get(0);
		}
		return resTrdModel;
	}

	public TrdMapper getTrdMappping() {
		return trdMappping;
	}

	public void setTrdMappping(TrdMapper trdMappping) {
		this.trdMappping = trdMappping;
	}

}
