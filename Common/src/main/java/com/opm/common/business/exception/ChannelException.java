package com.opm.common.business.exception;

/**
 * Created by kfzx-liuyz1 on 2016/12/14.
 */
public class ChannelException extends RuntimeException {

    /**
	 * UID
	 */
	private static final long serialVersionUID = -7586684697564475433L;

	public ChannelException(String message) {
        super(message);
    }

    public ChannelException(Throwable e) {
        super(e);
    }

    public ChannelException(String message, Throwable e) {
        super(message, e);
    }
}
