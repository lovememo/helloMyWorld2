package com.opm.filetemplate;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by kfzx-liuyz1 on 2016/12/7.
 */
@XStreamAlias("phoneNumber")
public class PhoneNumber {
    @XStreamAlias("code")
    private int code;
    @XStreamAlias("number")
    private String number;

    public PhoneNumber(int code, String number) {
        this.code = code;
        this.number = number;
    }
}
