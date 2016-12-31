package com.opm.common.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseModel implements Serializable {

    /**
	 * UID
	 */
	private static final long serialVersionUID = 6986638882904489798L;

	/**
     * ���õ�ö������
     * <p>
     * succ ��ʾ�ɹ���ǰ̨���� 0��
     * fail ��ʶʧ�ܣ�ǰ̨���� 1��
     * warn ��ʶ���棬ǰ̨���� 2
     */
    public enum State {
        SUCC("0"), FAIL("1"), WARN("2");

        /**
         * ״̬��Ӧ��ֵ
         */
        private String val;

        /**
         * Ĭ�Ϲ��캯��
         *
         * @param val ��Ӧ��ֵ
         */
        State(String val) {
            this.val = val;
        }

        /**
         * ��ȡ״ֵ̬
         *
         * @return ״̬��ֵ[�ο���������]
         */
        public String getVal() {
            return this.val;
        }
    }

    /**
     * ���صı�ʶ
     */
    private State flag;

    /**
     * ���ص���Ϣ
     */
    private String msg;


    /**
     * ָ�򷵻ص�����
     */
    @JsonProperty("ctx")
    private Object obj;

    /**
     * Ĭ�Ϲ��캯��
     */
    public ResponseModel() {
        this(State.SUCC, "", null);
    }

    /**
     * ���캯��
     *
     * @param state ״̬
     * @param msg   ��Ϣ
     * @param obj   ����
     */
    public ResponseModel(State state, String msg, Object obj) {
        this.flag = state;
        this.msg = msg;
        this.obj = obj;
    }

    /**
     * ��ȡ��ʶ
     *
     * @return ��ʶ��ֵ
     */
    public String getFlag() {
        return flag.getVal();
    }

    /**
     * ���ö�Ӧ��״ֵ̬
     *
     * @param flag
     */
    public void setFlag(State flag) {
        this.flag = flag;
    }

    /**
     * ��ȡ���ص���Ϣ
     *
     * @return ��Ϣ����
     */
    public String getMsg() {
        return msg;
    }

    /**
     * ���÷��ص���Ϣ
     *
     * @param msg �����ص���Ϣ
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * ��ȡ��Ӧ�Ķ���
     *
     * @return ���õĶ���
     */
    public Object getObj() {
        return obj;
    }

    /**
     * �������õĶ���
     *
     * @param obj ���õĶ���
     */
    public void setObj(Object obj) {
        this.obj = obj;
    }

//    public static void main(String[] args) {
//		List<String> ls = Arrays.asList("abc", "bcd");
//
//		RequestModel req = new RequestModel();
//		req.setPrivateData("{'a':'b'}");
//		ResponseModel model = new ResponseModel(ResponseModel.State.SUCC, "OK", req);
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			String str = mapper.writeValueAsString(model);
//			System.out.println(str);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}

//		RequestModel req = new RequestModel();
//		req.setPrivateData("{'a':'b'}");
//		ObjectMapper mapper = new ObjectMapper();
//		
//		try {
//		String str = mapper.writeValueAsString(req);
//		System.out.println(str);
//	} catch (JsonProcessingException e) {
//		e.printStackTrace();
//	}
//    }
}
