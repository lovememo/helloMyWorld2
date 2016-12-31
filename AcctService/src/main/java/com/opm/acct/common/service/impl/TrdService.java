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
			//�жϽ����Ƿ����
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
		// ��ѯ����
		TrdModel resTrdModel = this.trdDao.qryTrdByTrdNo(trdModel.getTrdNo());

		if (!(resTrdModel.getTrdState().equals(TrdState.FAILED.toString())
				|| resTrdModel.getTrdState().equals(TrdState.CANCELED.toString())
				|| resTrdModel.getTrdState().equals(TrdState.COMPENSATED.toString()))) {

			// ����״̬
			TrdModel updTrdModel = new TrdModel();
			updTrdModel.setTrdNo(trdModel.getTrdNo());
			updTrdModel.setTrdState(trdModel.getTrdState());
			updTrdModel.setUpdateTime("1"); // ���½���ʱ��
			this.trdDao.updTrd(trdModel);

		} else {
			// TODO �����ѱ�����
		}

	}
	
	public ITrdDao getTrdDao() {
		return trdDao;
	}

	public void setTrdDao(ITrdDao trdDao) {
		this.trdDao = trdDao;
	}
	
}
