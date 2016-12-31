package com.opm.filetemplate;

import java.io.OutputStream;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/21.
 */
class XmlFileGenerator implements IFileGenerator {

    @Override
    public void generate(Object data, OutputStream outputStream) {
        XmlFileUtil.toXMLFile(data,outputStream);
    }

    @Override
    public void generateByTemplate(String templateFilePath, String fileName, Map<String, Object> map, OutputStream outputStream) {
        //Not implement
    }


}
