package com.opm.core.investManager.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.investManager.dao.INetValueInfDao;
import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueEntity;
import com.opm.core.investManager.entity.NetValueInfEntity;
import com.opm.core.investManager.mapper.NetValueInfMapper;
import com.opm.core.investManager.model.NetValueAppFormModel;
import com.opm.core.investManager.model.NetValueAppFormModel2;

@Repository("NetValueInfDao")
public class NetValueInfDao implements INetValueInfDao {
	
	@Autowired
	private NetValueInfMapper netValueInfMapper;

	@Override
	public NetValueInfEntity qryInfByPK(NetValueInfEntity netValueInfModel) {
		if ( netValueInfModel.getPlanId() == null || netValueInfModel.getInvestId() == null || netValueInfModel.getEvaluateDate() == null ) {
			return null;
		}
		List<NetValueInfEntity> netValueInfEntityList = this.netValueInfMapper.qryInf(netValueInfModel);
		if ( netValueInfEntityList != null && netValueInfEntityList.size() == 1 ) {
			return netValueInfEntityList.get(0);
		} else {
			return null;
		}
		
	}

	@Override
	public void addInf(NetValueInfEntity netValueInfMode) {
		this.netValueInfMapper.addInf(netValueInfMode);
	}

	@Override
	public void updInf(NetValueInfEntity netValueInfModel) {
		if ( netValueInfModel.getPlanId() == null || netValueInfModel.getInvestId() == null || netValueInfModel.getEvaluateDate() == null ) {
			return ;
		}
		this.netValueInfMapper.updInf(netValueInfModel);
		
	}

	@Override
	public List<NetValueAppEntity> qryEvaluateDateList(String planId) {
		NetValueInfEntity netValueInfEntity = new NetValueInfEntity();
		netValueInfEntity.setPlanId(planId);
		return this.netValueInfMapper.qryEvaluateDate(netValueInfEntity);
	}

	@Override
	public NetValueAppFormModel2 qryInfDesc(NetValueInfEntity netValueInfEntity) {
		return this.netValueInfMapper.qryInfDesc(netValueInfEntity);
	}

	@Override
	public List<NetValueInfEntity> qryInf(NetValueInfEntity qryNetValueInfEntity) {
		return this.netValueInfMapper.qryInf(qryNetValueInfEntity);
	}

}
