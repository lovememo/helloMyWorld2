package com.opm.core.investManager.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.investManager.dao.INetValueTrdDao;
import com.opm.core.investManager.entity.NetValueTrdEntity;
import com.opm.core.investManager.mapper.NetValueTrdMapper;

@Repository("NetValueTrdDao")
public class NetValueTrdDao implements INetValueTrdDao {
	
	@Autowired
	private NetValueTrdMapper netValueTrdMapper;

	@Override
	public List<NetValueTrdEntity> qryTrdByTrdNo(String trdNo) {
		NetValueTrdEntity qryNetValueTreMdoel = new NetValueTrdEntity();
		qryNetValueTreMdoel.setTrdNo(trdNo);
		return this.netValueTrdMapper.qryTrd(qryNetValueTreMdoel);
	}

	@Override
	public void delTrd(NetValueTrdEntity netValueTrdModel) {
		if ( netValueTrdModel.getTrdNo() == null) {
			return ;
		}
		this.netValueTrdMapper.delTrd(netValueTrdModel);
		
	}

	@Override
	public void addTrd(NetValueTrdEntity netValueTrdModel) {
		this.netValueTrdMapper.addTrd(netValueTrdModel);
		
	}
}
