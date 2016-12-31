package com.opm.data.dtl.service.thread;

import ch.qos.logback.classic.Logger;
import com.opm.data.dtl.service.impl.DataCleaningWorker;
import org.slf4j.LoggerFactory;

/**
 * Created by kfzx-jinjf on 2016/12/27.
 */
public class DataCleaningThread extends Thread {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DataCleaningThread.class);

    private DataCleaningWorker worker;

    /**
     * 构造函数
     * @param worker
     */
    public DataCleaningThread(DataCleaningWorker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        try {
            this.worker.asynWork();
        } catch (Exception e) {
            LOGGER.info("数据清理worker异常！" + e.getMessage());
            e.printStackTrace();
        }
    }
}

