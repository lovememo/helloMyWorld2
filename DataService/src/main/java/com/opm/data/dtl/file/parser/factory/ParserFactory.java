package com.opm.data.dtl.file.parser.factory;

import com.opm.data.dtl.file.parser.IParser;
import com.opm.data.dtl.file.parser.impl.*;

/**
 * Created by kfzx-jinjf on 2016/12/26.
 */
public class ParserFactory {
    public static String CSV = new String("CSV");
    public static String TXT = new String("TXT");
    public static String XLS = new String("XLS");
    public static String ZIP = new String("ZIP");

    public static int SEPARATOR_FILE = 0;//�ָ����ļ�
    public static int FIXED_FILE = 1;//�����ļ�

    public int getDefaultBatchSize() {
        return defaultBatchSize;
    }

    public void setDefaultBatchSize(int defaultBatchSize) {
        this.defaultBatchSize = defaultBatchSize;
    }

    private int defaultBatchSize = 500;//Ĭ��������������

    public IParser getParser(String fileExt) {
        return getParser(fileExt, this.defaultBatchSize);
    }

    private IParser getParser(String fileExt, int batchSize) {
        return this.getParser(fileExt, batchSize, ParserFactory.SEPARATOR_FILE);//Ĭ�Ϸָ����ļ�
    }

    public IParser getParser(String fileExt, int batchSize, int fileFormat) {
        String fileExtUpper = fileExt.toUpperCase();
        if (fileExtUpper.equals(CSV)) {
            return new SeparatorFileCsvParser(batchSize);
        } else if (fileExtUpper.equals(TXT) && fileFormat == SEPARATOR_FILE) {//TXT�ָ���
            return new SeparatorFileTxtParser(batchSize);
        } else if (fileExtUpper.equals(TXT) && fileFormat == FIXED_FILE) {//�����ļ�
            return new FixedSizeFileParser(batchSize);
        } else if (fileExtUpper.equals(XLS)) {
            return new WpsFileExcelParser(batchSize);
        } else if (fileExtUpper.equals(ZIP)) {
            return new ZipFileParser(batchSize, fileFormat);
        }
        return null;
    }
}
