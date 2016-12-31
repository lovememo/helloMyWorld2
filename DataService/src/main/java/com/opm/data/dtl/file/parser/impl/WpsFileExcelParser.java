package com.opm.data.dtl.file.parser.impl;

import ch.qos.logback.classic.Logger;
import com.opm.data.dtl.file.parser.ParserLogger;
import com.opm.data.dtl.file.parser.action.ICleaningAction;
import com.opm.data.dtl.model.DtlData;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import com.icbc.larvaframework.ext.excelimportor.ExcelImportorExt.EventListener;
import org.slf4j.LoggerFactory;


import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * excel文件解析
 * Created by kfzx-jinjf on 2016/12/20.
 */
public class WpsFileExcelParser extends AbstractParser{
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(WpsFileExcelParser.class);

    public WpsFileExcelParser(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public int[] parse(InputStream is, ICleaningAction action) throws Exception {
        int[] retArr = new int[]{0, 0};
        BufferedInputStream bis = new BufferedInputStream(is);
        List[] excels = doExcelImport(bis);
        excels[1].add(0, excels[0]);//表头加上
        List data = excels[1];
        int rowNum = 0;
        int batchSeq = 0;
        List<DtlData> resultList = new ArrayList<DtlData>();
        ParserLogger logger = new ParserLogger(LOGGER);
        while(data.size() > rowNum) {
            List<?> colList = (List<?>)data.get(rowNum);
            if(colList.size() < 1) {
                continue;
            }
            rowNum ++;
            if(rowNum == Integer.MAX_VALUE) {
                throw new Exception("超过可处理最大行数！");
            }
            DtlData dtlData = new DtlData(action.getSerialNo(), rowNum, colList);
            resultList.add(dtlData);
            if(resultList.size() % this.batchSize == 0) {
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

   /* @Override
    public List<String[]> parse(InputStream is) throws Exception {
        return null;
    }*/

    /**
     * method copy from PIM UploadService.java
     * @param bis
     * @return
     * @throws Exception
     */
    private static List<?>[] doExcelImport(BufferedInputStream bis) throws Exception {
        List<?> data = new ArrayList<List<?>>();
        List<?> title = null;
        InputStream fin = null;
        InputStream din = null;
        try {
            fin = bis;
            din = new POIFSFileSystem(fin)
                    .createDocumentInputStream("Workbook");
            HSSFRequest hreq = new HSSFRequest();
            EventListener listener = new EventListener();
            hreq.addListenerForAllRecords(listener);
            HSSFEventFactory factory = new HSSFEventFactory();
            factory.processEvents(hreq, din);
            if (listener.result.isEmpty())
                throw new Exception("导入excel文件为空，请导入数据文件");
            title = (List<?>) listener.result.get(0);
            data = (List<?>) listener.result.clone();
            data.remove(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("导入文件作为excel文件打开失败，确实是excel文件？");
        }
        return new List[] { title, data };
    }


}
