package com.opm.filetemplate;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * Created by kfzx-liuyz1 on 2016/12/7.
 */
@XStreamAlias("person")
public class Person {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PhoneNumber getTel() {
        return tel;
    }

    public void setTel(PhoneNumber tel) {
        this.tel = tel;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Person(String title, String firstName, String lastName, PhoneNumber tel) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
    }

    @XStreamAsAttribute()
    private String title;
    private String firstName;
    private String lastName;
    @XStreamAlias("telphone")
    private PhoneNumber tel;
    private List<String> address;
    private List<Role> roles;


}
