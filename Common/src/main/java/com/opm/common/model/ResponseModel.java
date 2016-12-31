package com.opm.common.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseModel implements Serializable {

    /**
	 * UID
	 */
	private static final long serialVersionUID = 6986638882904489798L;

	/**
     * 内置的枚举类型
     * <p>
     * succ 表示成功，前台返回 0；
     * fail 标识失败，前台返回 1；
     * warn 标识警告，前台返回 2
     */
    public enum State {
        SUCC("0"), FAIL("1"), WARN("2");

        /**
         * 状态对应的值
         */
        private String val;

        /**
         * 默认构造函数
         *
         * @param val 对应的值
         */
        State(String val) {
            this.val = val;
        }

        /**
         * 获取状态值
         *
         * @return 状态的值[参考类型描述]
         */
        public String getVal() {
            return this.val;
        }
    }

    /**
     * 返回的标识
     */
    private State flag;

    /**
     * 返回的信息
     */
    private String msg;


    /**
     * 指向返回的内容
     */
    @JsonProperty("ctx")
    private Object obj;

    /**
     * 默认构造函数
     */
    public ResponseModel() {
        this(State.SUCC, "", null);
    }

    /**
     * 构造函数
     *
     * @param state 状态
     * @param msg   消息
     * @param obj   内容
     */
    public ResponseModel(State state, String msg, Object obj) {
        this.flag = state;
        this.msg = msg;
        this.obj = obj;
    }

    /**
     * 获取标识
     *
     * @return 标识的值
     */
    public String getFlag() {
        return flag.getVal();
    }

    /**
     * 设置对应的状态值
     *
     * @param flag
     */
    public void setFlag(State flag) {
        this.flag = flag;
    }

    /**
     * 获取返回的消息
     *
     * @return 消息内容
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置返回的消息
     *
     * @param msg 待返回的消息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取对应的对象
     *
     * @return 引用的对象
     */
    public Object getObj() {
        return obj;
    }

    /**
     * 设置引用的对象
     *
     * @param obj 引用的对象
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
