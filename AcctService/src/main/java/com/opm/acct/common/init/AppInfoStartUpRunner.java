package com.opm.acct.common.init;

import ch.qos.logback.classic.Logger;
import com.opm.common.conf.AppInfo;
import com.opm.common.conf.AppServiceEnum;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by kfzx-liuyz1 on 2016/10/28.
 */
@Component
public class AppInfoStartUpRunner implements CommandLineRunner {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AppInfoStartUpRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info("SET APPINFO" + AppServiceEnum.acct.toString());
        AppInfo.getInstance().setAppServiceEnum(AppServiceEnum.data);
    }
}
