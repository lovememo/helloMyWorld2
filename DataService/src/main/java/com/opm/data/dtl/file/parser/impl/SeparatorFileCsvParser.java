package com.opm.data.dtl.file.parser.impl;

import com.opm.data.dtl.file.FileProcessingUtil;

/**
 * Created by kfzx-jinjf on 2016/12/19.
 */
public class SeparatorFileCsvParser extends SeparatorFileBaseParser {
    public SeparatorFileCsvParser(int batchSize) {
        super(batchSize, FileProcessingUtil.CSV_SEPARATOR);
    }
}
