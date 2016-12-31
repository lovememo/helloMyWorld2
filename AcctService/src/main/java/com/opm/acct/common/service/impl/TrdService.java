package com.opm.acct.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.acct.common.service.ITrdService;
import com.opm.acct.mdt.dao.ITrdDao;
import com.opm.acct.mdt.enums.TrdState;
import com.opm.acct.mdt.model.TrdModel;

@Service("TrdService")
public class TrdService implements ITrdService {

	@Autowired
	private ITrdDao trdDao;

	@Override
	public void saveTrd(TrdModel trdModel) {
		
		if ( trdModel.getTrdNo() == null ) {
			this.trdDao.addTrd(trdModel);
		} else {
			//判断交易是否存在
			TrdModel resTrdModel = this.trdDao.qryTrdByTrdNo(trdModel.getTrdNo());
			
			if ( resTrdModel != null ) {
				trdModel.setTrdState(trdModel.getTrdState());
				this.trdDao.addTrd(trdModel);
			} else {
				TrdModel updTrdModel = new TrdModel();
				updTrdModel.setTrdNo(trdModel.getTrdNo());
				updTrdModel.setUpdateTime("1");
				this.trdDao.addTrd(trdModel);
			}
		}
	}
	
	@Override
	public void modTrd(TrdModel trdModel) {
		// 查询交易
		TrdModel resTrdModel = this.trdDao.qryTrdByTrdNo(trdModel.getTrdNo());

		if (!(resTrdModel.getTrdState().equals(TrdState.FAILED.toString())
				|| resTrdModel.getTrdState().equals(TrdState.CANCELED.toString())
				|| resTrdModel.getTrdState().equals(TrdState.COMPENSATED.toString()))) {

			// 更新状态
			TrdModel updTrdModel = new TrdModel();
			updTrdModel.setTrdNo(trdModel.getTrdNo());
			updTrdModel.setTrdState(trdModel.getTrdState());
			updTrdModel.setUpdateTime("1"); // 更新交易时间
			this.trdDao.updTrd(trdModel);

		} else {
			// TODO 交易已被更新
		}

	}
	
	public ITrdDao getTrdDao() {
		return trdDao;
	}

	public void setTrdDao(ITrdDao trdDao) {
		this.trdDao = trdDao;
	}
	
}
