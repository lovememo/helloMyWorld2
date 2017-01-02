package com.opm.data.dtl.file.parser;

import com.opm.data.dtl.file.parser.action.ICleaningAction;

import java.io.InputStream;

/**
 * Created by kfzx-jinjf on 2016/12/19.
 */
public interface IParser {
    /**
     * 解析文件方法
     * @param is 文件输入流
     * @param action 回调函数
     * @return int数组，int[0]为总行数 int[1]为错误报告行数
     * @throws Exception
     */
    public int[] parse(InputStream is, ICleaningAction action) throws Exception;

    /**
     * 设置解析是所用字符集
     * @param charset
     */
    public void setCharset(String charset);
//    public int[] prototypeParse(InputStream is, ICleaningAction action) throws Exception;
    //public List<String[]> parse(InputStream is) throws Exception;
}
