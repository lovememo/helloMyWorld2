package com.opm.core.workflow.model;

import java.util.Date;

import com.opm.common.enumdict.ApplyStatus;
import com.opm.common.enumdict.ApplyType;


/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
public class ApplicationModel {

    //������
    private String applyId;
    //�ƻ�����
    private String planId;
    //�ƻ�����
    private String planName;
    //�����û�
    private String applyUser;
    //��������
    private ApplyType applyType;
    //����״̬
    private ApplyStatus applyStatus;
    //����ʱ��
    private Date applyTime;

    public ApplicationModel(){}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public ApplyType getApplyType() {
		return applyType;
	}

	public void setApplyType(ApplyType applyType) {
		this.applyType = applyType;
	}

	public ApplyStatus getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(ApplyStatus applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
    
    
}
