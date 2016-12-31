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
        String timeUsed = ", �Ѵ���ʱ��Ϊ��" + (endTime - startTime)/1000.00 + "��";
        LOGGER.info("�ύ��" + batchSeq + "��������jdbcTemplate.batchUpdate..batchSizeΪ" + batchSizeProcessed + timeUsed);
    }


}
