package com.opm.discover.monitor;

import com.opm.discover.support.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/23.
 */
public class MicroServiceInfo {
    private String appName;
    private String ipAddr;
    private String occuredAppSName;
    private String moduleCode;
    private String status;

    public MicroServiceInfo(String appName, String ipAddr, String moduleCode) {
        this.appName = appName;
        this.ipAddr = ipAddr;
        this.occuredAppSName = appName;
        this.moduleCode = moduleCode;
        this.status = "0";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getOccuredAppSName() {
        return occuredAppSName;
    }

    public void setOccuredAppSName(String occuredAppSName) {
        this.occuredAppSName = occuredAppSName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public Map<String,Object> toMap(){
        try {
            return MapUtils.objectToMap(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new HashMap<String,Object>();
    }

    @Override
    public int hashCode(){
        return (this.appName + this.occuredAppSName + this.moduleCode + this.ipAddr).hashCode();
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof MicroServiceInfo){
            MicroServiceInfo obj = (MicroServiceInfo)object;
            if(this.appName.equals(obj.appName)
                    && this.occuredAppSName.equals(obj.occuredAppSName)
                    && this.moduleCode.equals(obj.moduleCode)
                && this.ipAddr.equals(obj.ipAddr)){
                return true;
            }
        }

        return false;
    }
}
