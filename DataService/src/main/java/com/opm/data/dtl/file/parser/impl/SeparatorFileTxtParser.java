package com.opm.data.dtl.file.parser.impl;

import com.opm.data.dtl.file.FileProcessingUtil;

/**
 * txt·Ö¸ô·û½âÎö
 * Created by kfzx-jinjf on 2016/12/19.
 */
public class SeparatorFileTxtParser extends SeparatorFileBaseParser {
    public SeparatorFileTxtParser(int batchSize) {
        super(batchSize, FileProcessingUtil.TXT_SEPARATOR);
    }
}
