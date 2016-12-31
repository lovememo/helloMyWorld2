package com.opm.discover.monitor;

import java.util.*;

/**
 * Created by kfzx-liuyz1 on 2016/12/23.
 */
public class MicroServiceContainer{

    private static final MicroServiceContainer instance = new MicroServiceContainer();

    private Set<MicroServiceInfo> microServiceInfoSet = Collections.synchronizedSet(new HashSet<MicroServiceInfo>());

    private MicroServiceContainer(){

    }

    public static MicroServiceContainer getInstance(){
        return MicroServiceContainer.instance;
    }

    public void addAll(Set<MicroServiceInfo> microServiceInfoSet){
        this.microServiceInfoSet.addAll(microServiceInfoSet);
    }

    public Set<MicroServiceInfo> getMicroServiceInfoSet() {
        return microServiceInfoSet;
    }



}
