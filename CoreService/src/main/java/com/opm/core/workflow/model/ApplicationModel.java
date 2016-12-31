package com.opm.core.workflow.model;

import java.util.Date;

import com.opm.common.enumdict.ApplyStatus;
import com.opm.common.enumdict.ApplyType;


/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
public class ApplicationModel {

    //申请编号
    private String applyId;
    //计划编码
    private String planId;
    //计划名称
    private String planName;
    //申请用户
    private String applyUser;
    //申请类型
    private ApplyType applyType;
    //申请状态
    private ApplyStatus applyStatus;
    //申请时间
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
