package com.opm.data.dtl.file.parser.impl;

import ch.qos.logback.classic.Logger;
import com.opm.data.common.StringUtil;
import com.opm.data.dtl.file.parser.ParserLogger;
import com.opm.data.dtl.file.parser.action.ICleaningAction;
import com.opm.data.dtl.model.DtlData;
import com.opm.data.dtl.model.DtlItem;
import com.opm.data.dtl.model.DtlRule;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * �����ļ�����
 * Created by kfzx-jinjf on 2016/12/20.
 */
public class FixedSizeFileParser extends AbstractParser {
    private DtlItem dtlItem;
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SeparatorFileBaseParser.class);

    private String[] parseHeader(String header) throws Exception {
        return this.parserRowStr(header, this.dtlItem.getParseHeaderRuleList());
    }

    private String[] parseBody(String body) throws Exception {
        return this.parserRowStr(body, this.dtlItem.getParseBodyRuleList());
    }


    private String[] parserRowStr(String rowStr, List<DtlRule> dtlRuleList) throws Exception {
        if(dtlRuleList == null || dtlRuleList.size() < 1) {
            throw new Exception("δ���ֽ���������򼯣�����ϵ����Ա��");
        }
        String[] cols = new String[dtlRuleList.size()];
        byte[] rawData = rowStr.getBytes();
        int pos = 0;
        for(int i=0; i<dtlRuleList.size(); i++) {
            DtlRule rule = dtlRuleList.get(i);
            String[] formats = rule.getRuleExpression().split(":");
            if(formats.length != 2) {
                throw new Exception("�����������������ϵ����Ա��");
            }
            String fieldType = formats[0];
            int fieldLength = Integer.parseInt(formats[1]);
            byte[] tempBytes = new byte[fieldLength];
            System.arraycopy(rawData, pos, tempBytes, 0, fieldLength);
            String tmpStr = new String(tempBytes, this.charset);
            if("C".equals(fieldType.toUpperCase())) {//ԭ�����Ҳ��ո�
                tmpStr = StringUtil.trimRightSpace(tmpStr);
            } else if("N".equals(fieldType.toUpperCase())) {//ԭ��������
                tmpStr = StringUtil.trimLeftZero(tmpStr);
            }
            cols[i] = tmpStr;
            pos += fieldLength;
        }
        return cols;
    }

    /**
     * �����������������ļ�����
     * @param is
     * @param action
     * @throws Exception
     * @return int���飬int[0]Ϊ������ int[1]Ϊ���󱨸�����
     */
    @Override
    public int[] parse(InputStream is, ICleaningAction action) throws Exception {
        int[] retArr = new int[]{0, 0};
        if(null == is || null == action) {
            throw new Exception("InputStream is null or action is null");
        }
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String rowStr = "";
        String[] rowData = null;
        List<DtlData> resultList = new ArrayList<DtlData>();
        int rowNum = 0;
        long startTime = System.currentTimeMillis();
        ParserLogger logger = new ParserLogger(LOGGER);
        int batchSeq = 0;
        while((rowStr = br.readLine()) != null) {
            if(rowStr.replaceAll("\\s*","").equals("")) {
                continue;
            }
            rowNum ++;
            if(rowNum == Integer.MAX_VALUE) {
                throw new Exception("�����ɴ������������");
            }
            if(rowNum == 1 && this.dtlItem.getParseHeaderRuleList() != null && this.dtlItem.getParseHeaderRuleList().size() > 0) {
                rowData = this.parseHeader(rowStr);
            } else {
                rowData = this.parseBody(rowStr);
            }
            DtlData dtlData = new DtlData(action.getSerialNo(), rowNum, rowData);
            resultList.add(dtlData);
            if(rowNum % this.batchSize == 0) {
                action.doProcess(resultList);//��������
                retArr[0] += resultList.size();
                resultList.clear();//�����ύ�󻺴��������
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

    public FixedSizeFileParser(int batchSize, DtlItem dtlItem) {
        this.batchSize = batchSize;
        this.dtlItem = dtlItem;
    }

}
