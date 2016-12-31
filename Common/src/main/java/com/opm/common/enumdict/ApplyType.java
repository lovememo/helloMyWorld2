package com.opm.common.enumdict;

/**
 * Created by kfzx-liuyz1 on 2016/10/21.
 */
// ��������Ϊ�������ֵ�ֵ��ȫ�����ƻ���Ϣ�Ǽǡ��ƻ���Ϣ�޸ġ��ƻ���Ϣɾ������ͬ�����ֶ��սᡢ�ս��־�л�����Ĭ�ϡ�ȫ������
public enum ApplyType {
	all("000000", "ȫ��"), PlanCreate("010101", "�ƻ���Ϣ�Ǽ�");

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
