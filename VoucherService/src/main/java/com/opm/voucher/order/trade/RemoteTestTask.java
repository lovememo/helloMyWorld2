package com.opm.voucher.order.trade;

import com.opm.common.business.api.CoreBusinessApiFactory;
import com.opm.common.business.api.IBusinessApiFactory;
import com.opm.common.business.task.TaskAdapter;
import com.opm.common.conf.AppServiceEnum;
import org.springframework.stereotype.Service;

/**
 * Created by kfzx-liuyz1 on 2016/11/30.
 */
@Service("RemoteTestTask")
public class RemoteTestTask extends TaskAdapter {
    @Override
    public IBusinessApiFactory getBusinessApiFactory() {
        return new CoreBusinessApiFactory();
    }
    @Override
    public String getRemoteTradeClassName() {
        return "TestTask";
    }

}
