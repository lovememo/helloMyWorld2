package com.opm.data.dtl.file.parser.impl;

import com.icbc.pim.zip.ZipEntry;
import com.icbc.pim.zip.ZipInputStream;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
import com.opm.data.common.FileUtil;
import com.opm.data.dtl.file.parser.IParser;
import com.opm.data.dtl.file.parser.action.ICleaningAction;
import com.opm.data.dtl.file.parser.factory.ParserFactory;

import java.io.InputStream;

/**
 * Created by kfzx-jinjf on 2016/12/26.
 */
public class ZipFileParser extends AbstractParser {
    private int fileFormat = -1;

    public int getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(int fileFormat) {
        this.fileFormat = fileFormat;
    }

    public ZipFileParser(int batchSize, int fileFormat) {
        this.batchSize = batchSize;
        this.fileFormat = fileFormat;
    }

    @Override
    public int[] parse(InputStream is, ICleaningAction action) throws Exception {
        //压缩文件
        ZipInputStream zis = new ZipInputStream(is);
        int zipFileNum = 0;
        ZipEntry ze = null;
        while((ze = zis.getNextEntry()) != null && !ze.isDirectory()) {
            if(++ zipFileNum > 1) {//只解析一个文件
                break;
            }
            String fileExt = FileUtil.getFileExtension(ze.getName());
            ParserFactory pf = new ParserFactory();
            IParser parser = pf.getParser(fileExt, this.batchSize, fileFormat);
            if(null == parser) {
                throw new Exception("解析器创建失败！fileExt:" + fileExt + ", fileFormat:" + fileFormat);
            }
            return parser.parse(zis, action);
        }

        if(0 == zipFileNum) {
            throw new Exception("压缩文件内容为空或格式不正确！");
        }
        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        ZipFileParser zfp = new ZipFileParser(123,1);
        System.out.println(FileUtil.getFileExtension("123txt"));
        System.out.println(FileUtil.getFileExtension("123.txt.zip"));
    }
}
