package com.opm.data.dtl.file.parser;

/**
 * Created by kfzx-jinjf on 2016/12/20.
 */
public class ParserLogger {
    private ch.qos.logback.classic.Logger LOGGER;
    private long startTime = 0L;

    public ParserLogger(ch.qos.logback.classic.Logger LOGGER) {
        this.LOGGER = LOGGER;
        this.startTime = System.currentTimeMillis();
    }

    public void resetTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void info(String log) {
        this.LOGGER.info(log);
    }

    public void batchInfo(int batchSeq, int batchSizeProcessed) {
        long endTime = System.currentTimeMillis();
        String timeUsed = ", 已处理时间为：" + (endTime - startTime)/1000.00 + "秒";
        LOGGER.info("提交第" + batchSeq + "个批次至jdbcTemplate.batchUpdate..batchSize为" + batchSizeProcessed + timeUsed);
    }


}
