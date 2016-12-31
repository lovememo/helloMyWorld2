package com.opm.data.dtl.file.parser.impl;

import ch.qos.logback.classic.Logger;
import com.opm.data.dtl.file.parser.ParserLogger;
import com.opm.data.dtl.file.parser.action.ICleaningAction;
import com.opm.data.dtl.model.DtlData;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 分隔符文件解析
 * Created by kfzx-jinjf on 2016/12/19.
 */
public class SeparatorFileBaseParser extends AbstractParser {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SeparatorFileBaseParser.class);

    /**
     * 分隔符
     */
    private String separativeSign;
    public SeparatorFileBaseParser(int batchSize, String separativeSign) {
        this.batchSize = batchSize;
        this.separativeSign = separativeSign;
    }

    /**
     * 解析并批量处理定长文件数据
     * @param is
     * @param action
     * @throws Exception
     * @return int数组，int[0]为总行数 int[1]为错误报告行数
     */
    @Override
    public int[] parse(InputStream is, ICleaningAction action) throws Exception {
        int[] retArr = new int[]{0, 0};
        if(null == is || null == action) {
            throw new Exception("InputStream is null or action is null");
        }
        InputStreamReader isr = new InputStreamReader(is, this.charset);
        BufferedReader br = new BufferedReader(isr);
        String rowStr = "";
        String[] rowData = null;
        List<DtlData> resultList = new ArrayList<DtlData>();
        int rowNum = 0;
        long startTime = System.currentTimeMillis();
        ParserLogger logger = new ParserLogger(LOGGER);
        int batchSeq = 0;
        while((rowStr = br.readLine()) != null) {
            if(rowStr.replaceAll(this.separativeSign, "").replaceAll("\\s*","").equals("")) {
                continue;
            }
            rowNum ++;
            if(rowNum == Integer.MAX_VALUE) {
                throw new Exception("超过可处理最大行数！");
            }
            rowData = rowStr.split(this.separativeSign);
            DtlData dtlData = new DtlData(action.getSerialNo(), rowNum, rowData);
            resultList.add(dtlData);
            if(rowNum % this.batchSize == 0) {
                action.doProcess(resultList);//批量处理
                retArr[0] += resultList.size();
                resultList.clear();//批量提交后缓存数据清空
                logger.batchInfo(batchSeq++, batchSize);
            }
        }
        if(resultList.size() != 0) {
            action.doProcess(resultList);
            retArr[0] += resultList.size();
            logger.batchInfo(batchSeq++, resultList.size());
        }
        return retArr;
    }

    /*@Override
    public List<String[]> parse(InputStream is) throws Exception{
        if(null == is) {
            throw new Exception("InputStream is null");
        }
        InputStreamReader isr = new InputStreamReader(is, this.charset);
        BufferedReader br = new BufferedReader(isr);
        String rowStr = "";
        String[] rowData = null;
        List<String[]> resultList = new ArrayList<String[]>();
        while((rowStr = br.readLine()) != null) {
            rowData = rowStr.split(this.separativeSign);
            resultList.add(rowData);
        }
        return resultList;
    }*/
    /*public static void main(String[] args) {
        String rowStr = "       ,           ";
        System.out.println(rowStr.replaceAll(FileProcessingUtil.CSV_SEPARATOR, "").replaceAll("\\s*","").equals(""));
    }*/
}
