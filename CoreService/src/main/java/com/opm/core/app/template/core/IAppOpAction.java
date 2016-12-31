package com.opm.core.app.template.core;

import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;

public interface IAppOpAction {
	
	public enum SUMBIT_ACTION {
		SAVE, SUBMIT,ASYNC_SUBMIT,ASYNC_CALLBACK
	}

	public enum AUD_ACTION{
		AGREE,DENY
	}

	/**
	 * ��ʼ�����ף�������˵��ֻӦ�ö��徲̬�Ľ���˳��
	 */
 void initTrades();
	
	/**
	 * �����������ͣ�����AppType��������
	 * @return �������ͱ���
	 */
//	String getAppTypeNo();
	/**
	 * �ύ����controller�е���<br>
	 * 
	 * @param action
	 * @param params ���������ֱ�Ӵ�����һ��trade��
	 */
	ResultMessage submit(SUMBIT_ACTION action,AppParam params);

	/**
	 * ����
	 */
	ResultMessage aud(AUD_ACTION audAction,AppParam params);

	/**
	 * ��Ч
	 * @param params
	 */
	ResultMessage effect(AppParam params);
}
