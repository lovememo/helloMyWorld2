package com.opm.data.dtl.file.parser.impl;

import com.opm.data.dtl.file.parser.action.ICleaningAction;

import java.io.InputStream;

/**
 * 鼎城文件解析
 * Created by kfzx-jinjf on 2016/12/20.
 */
public class FixedSizeFileParser extends AbstractParser {
    @Override
    public int[] parse(InputStream is, ICleaningAction action) throws Exception {
        return new int[]{0, 0};
    }

    public FixedSizeFileParser(int batchSize) {
        this.batchSize = batchSize;
    }

    /*@Override
    public List<String[]> parse(String serialNo, InputStream is) throws Exception {
        return null;
    }*/


}
