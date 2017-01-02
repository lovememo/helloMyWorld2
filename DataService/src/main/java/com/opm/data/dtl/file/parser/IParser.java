package com.opm.data.dtl.file.parser;

import com.opm.data.dtl.file.parser.action.ICleaningAction;

import java.io.InputStream;

/**
 * Created by kfzx-jinjf on 2016/12/19.
 */
public interface IParser {
    /**
     * �����ļ�����
     * @param is �ļ�������
     * @param action �ص�����
     * @return int���飬int[0]Ϊ������ int[1]Ϊ���󱨸�����
     * @throws Exception
     */
    public int[] parse(InputStream is, ICleaningAction action) throws Exception;

    /**
     * ���ý����������ַ���
     * @param charset
     */
    public void setCharset(String charset);
//    public int[] prototypeParse(InputStream is, ICleaningAction action) throws Exception;
    //public List<String[]> parse(InputStream is) throws Exception;
}
