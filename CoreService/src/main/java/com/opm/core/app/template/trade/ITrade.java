package com.opm.core.app.template.trade;

import com.opm.common.business.task.ITask;

/**
 * ���׵Ľӿ�
 * 
 * @author kfzx-zhengyy1
 *
 */
public interface ITrade {
//	enum ACTION_TYPE {
//		PREPARE, DO
//	}

//	ITrade setPrepareTask(Class<? extends ITask> task);
//
//	ITrade setDoTask(Class<? extends ITask> task);

	Class<? extends ITask> getPrepareTask();
	Class<? extends ITask> getDoTask();

	String getTradeName();
	
	void tradeCheck();

}
