package com.opm.filetemplate;

import java.io.OutputStream;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/20.
 */
public interface IFileGenerator {
    public void generate(Object data,OutputStream outputStream);
    public void generateByTemplate(String templateFilePath,String fileName, Map<String,Object> map,OutputStream outputStream);
}
