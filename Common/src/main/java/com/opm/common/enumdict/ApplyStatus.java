package com.opm.common.enumdict;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
public enum ApplyStatus {

	All("ȫ��", 0), Save("����", 1), ToReview("�����", 2), Reviewing("�����", 3), Reject("���", 4), Approve("ͬ��", 5), ApproveButNotValid("ͬ��δ��Ч", 6);

	public String getDescription() {
		return description;
	}

	public int getVal() {
		return val;
	}

	private String description;
	private int val;

	private ApplyStatus(String description, int val) {
		this.val = val;
		this.description = description;
	}

	public static ApplyStatus valueOfCode(String code) {
		ApplyStatus ret = null;
		switch (code) {
		case "2":
			ret = ApplyStatus.ToReview;
			break;
		default:
			break;
		}

		return ret;
	}

}
