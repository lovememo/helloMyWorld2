package com.opm.core.investManager.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.investManager.dao.INetValueAppDao;
import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueMAppEntity;
import com.opm.core.investManager.mapper.NetValueAppMapper;

@Repository("NetValueAppDao")
public class NetValueAppDao implements INetValueAppDao {
	
	@Autowired
	private NetValueAppMapper netValueAppMapper;

	@Override
	public void delApp(NetValueAppEntity netValueAppModel) {
		this.netValueAppMapper.delApp(netValueAppModel);
		
	}

	@Override
	public void addApp(NetValueAppEntity netValueAppModel) {
		this.netValueAppMapper.addApp(netValueAppModel);
		
	}

	@Override
	public NetValueAppEntity qryAppByUniq(NetValueAppEntity netValueAppModel) {
		if ( netValueAppModel.getAppNo() == null || netValueAppModel.getPlanId() == null || netValueAppModel.getInvestId() == null ) {
			return null;
		}
		
		List<NetValueAppEntity> netValueAppModelList = this.netValueAppMapper.qryApp(netValueAppModel);
		if ( netValueAppModelList.size() == 1) {
			return netValueAppModelList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<NetValueAppEntity> qryApp(NetValueAppEntity netValueAppEntity) {
		return this.netValueAppMapper.qryApp(netValueAppEntity);
	}

	@Override
	public NetValueMAppEntity qryMAppByPK(NetValueMAppEntity netValueMAppEntity) {
		if ( netValueMAppEntity.getAppNo() == null 
				|| netValueMAppEntity.getPlanId() == null 
				|| netValueMAppEntity.getEvaluateDate() == null) {
			return null;
		}
		List<NetValueMAppEntity> netValueMAppEntityList = this.netValueAppMapper.qryMApp(netValueMAppEntity);
		if ( netValueMAppEntityList != null && netValueMAppEntityList.size() == 1) {
			return netValueMAppEntityList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addMApp(NetValueMAppEntity netValueMAppEntity) {
		this.netValueAppMapper.addMApp(netValueMAppEntity);
		
	}

	@Override
	public void delMApp(NetValueMAppEntity netValueMAppEntity) {
		this.netValueAppMapper.delMApp(netValueMAppEntity);
	}

}
