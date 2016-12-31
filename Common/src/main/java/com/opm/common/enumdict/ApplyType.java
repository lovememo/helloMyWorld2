package com.opm.common.enumdict;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
// 申请类型为下拉框，字典值“全部、计划信息登记、计划信息修改、计划信息删除、合同管理、手动日结、日结标志切换”，默认“全部”。
public enum ApplyType {
	all("000000", "全部"), PlanCreate("010101", "计划信息登记");

	private String description;
	private String code;

	private ApplyType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public String getCode() {
		return this.code;
	}

	public static ApplyType valueOfCode(String code) {
		ApplyType ret = null;
		switch (code) {
		case "010101":
			ret = ApplyType.PlanCreate;
			break;
		default:
			break;
		}

		return ret;
	}
}
