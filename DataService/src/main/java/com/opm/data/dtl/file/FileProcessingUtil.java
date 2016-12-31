package com.opm.data.dtl.file;

import com.opm.data.dtl.dao.IDtlDataDao;
import com.opm.data.dtl.model.DtlFile;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kfzx-jinjf on 2016/12/14.
 */
public class FileProcessingUtil {
    public static final String TXT_SEPARATOR = new String("\\t");
    public static final String CSV_SEPARATOR = new String(",");

    private static FileProcessingUtil fpu = new FileProcessingUtil();
    private FileProcessingUtil() {
    }

    public static FileProcessingUtil getInstance() {
        return fpu;
    }
    /**
     * 解析文件并批量插入数据库
     * @param fis
     * @param batchSize
     * @return
     */
    public boolean InsertDbBatch(InputStream fis, int batchSize) {
        return true;
    }

    /**
     * 保存文件
     * @param is
     * @param fileName
     * @param serialNo
     * @return
     * @throws IOException
     */
    public DtlFile saveFileAsBlob(InputStream is, String fileName, String serialNo, IDtlDataDao dataInfoDao) throws IOException {
        byte[] fileContent = FileCopyUtils.copyToByteArray(is);//code copy from liuyz, may raise oom exception
        DtlFile dtlFile = new DtlFile(serialNo, fileName, fileContent);
        dataInfoDao.saveFile(dtlFile);
        return dtlFile;
    }

}
