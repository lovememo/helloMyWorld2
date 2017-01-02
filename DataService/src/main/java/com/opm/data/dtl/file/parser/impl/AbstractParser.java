package com.opm.data.dtl.file.parser.impl;

import com.opm.data.dtl.file.parser.IParser;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件解析抽象类，定义了几个成员变量
 * Created by kfzx-jinjf on 2016/12/19.
 */
public abstract class AbstractParser implements IParser {
    /**
     * 解析后批量提交批次大小
     */
    protected int batchSize = 0;

    /*@Override
    public int[] parse(InputStream is, ICleaningAction action) throws Exception {
        if(!isZip) {
            return this.prototypeParse(is, action);
        }
        //压缩文件
        ZipInputStream zis = new ZipInputStream(is);
        int zipFileNum = 0;
        ZipEntry ze = null;
        while((ze = zis.getNextEntry()) != null && !ze.isDirectory()) {
            if(++ zipFileNum > 1) {
                break;
            }
            return this.prototypeParse(zis, action);
        }

        if(0 == zipFileNum) {
            throw new Exception("压缩文件内容为空或格式不正确！");
        }
        return new int[]{0, 0};
    }*/

    /**
     * 默认编码为GBK
     */
    protected String charset = "GBK";

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public String getCharset() {
        return charset;
    }

    @Override
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * 关闭输入流
     */
    protected static void closeInputStream(InputStream is) {
        if (is != null)
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
