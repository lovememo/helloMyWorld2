package com.opm.data.dtl.file.parser.factory;

import com.opm.common.enumdict.DtlMedium;
import com.opm.data.dtl.file.parser.IParser;
import com.opm.data.dtl.file.parser.impl.*;
import com.opm.data.dtl.model.DtlItem;

/**
 * Created by kfzx-jinjf on 2016/12/26.
 */
public class ParserFactory {
    public static String CSV = new String("CSV");
    public static String TXT = new String("TXT");
    public static String XLS = new String("XLS");
    public static String ZIP = new String("ZIP");


    public int getDefaultBatchSize() {
        return defaultBatchSize;
    }

    public void setDefaultBatchSize(int defaultBatchSize) {
        this.defaultBatchSize = defaultBatchSize;
    }

    private int defaultBatchSize = 500;//默认批处理数据量

    public IParser getParser(String fileExt, int batchSize, DtlItem dtlItem) {
        String fileExtUpper = fileExt.toUpperCase();
        IParser parser = null;
        if (fileExtUpper.equals(CSV)) {
            parser = new SeparatorFileCsvParser(batchSize);
        } else if (fileExtUpper.equals(TXT) && DtlMedium.valueOfCode(dtlItem.getMedium()) == DtlMedium.TXT_SEPARATOR) {//TXT分隔符
            parser = new SeparatorFileTxtParser(batchSize);
        } else if (fileExtUpper.equals(TXT) && DtlMedium.valueOfCode(dtlItem.getMedium()) == DtlMedium.TXT_FIXED_SIZE) {//定长文件
            parser = new FixedSizeFileParser(batchSize, dtlItem);
        } else if (fileExtUpper.equals(XLS)) {
            parser = new WpsFileExcelParser(batchSize);
        } else if (fileExtUpper.equals(ZIP)) {
            parser = new ZipFileParser(batchSize, dtlItem);
        }
        if(null != dtlItem.getCharset() && !"".equals(dtlItem.getCharset())) {
            parser.setCharset(dtlItem.getCharset());
        }
        return parser;
    }
}
