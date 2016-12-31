package com.opm.common.enumdict;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
public enum ApplyStatus {

	All("全部", 0), Save("保存", 1), ToReview("待审核", 2), Reviewing("审核中", 3), Reject("否决", 4), Approve("同意", 5), ApproveButNotValid("同意未生效", 6);

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
