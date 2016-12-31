package com.opm.filetemplate;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by kfzx-liuyz1 on 2016/12/7.
 */
@XStreamAlias("Role")
public class Role {
    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;
}
