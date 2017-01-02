package com.opm.data.dtl.file.parser.impl;

import com.opm.data.dtl.file.parser.IParser;

import java.io.IOException;
import java.io.InputStream;

/**
 * �ļ����������࣬�����˼�����Ա����
 * Created by kfzx-jinjf on 2016/12/19.
 */
public abstract class AbstractParser implements IParser {
    /**
     * �����������ύ���δ�С
     */
    protected int batchSize = 0;

    /*@Override
    public int[] parse(InputStream is, ICleaningAction action) throws Exception {
        if(!isZip) {
            return this.prototypeParse(is, action);
        }
        //ѹ���ļ�
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
            throw new Exception("ѹ���ļ�����Ϊ�ջ��ʽ����ȷ��");
        }
        return new int[]{0, 0};
    }*/

    /**
     * Ĭ�ϱ���ΪGBK
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
     * �ر�������
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
