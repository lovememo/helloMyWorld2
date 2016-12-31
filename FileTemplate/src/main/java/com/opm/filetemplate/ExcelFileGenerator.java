package com.opm.filetemplate;

import ch.qos.logback.classic.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/21.
 */
class ExcelFileGenerator implements IFileGenerator {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ExcelFileGenerator.class);

    @Override
    public void generate(Object data, OutputStream outputStream) {
        //TO DO:
    }

    @Override
    public void generateByTemplate(String templateFilePath,String fileName,Map<String, Object> map,OutputStream outputStream) {
        try{
            String templateFile = templateFilePath + fileName;
            TemplateExportParams params = new TemplateExportParams(templateFile);
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);
            workbook.write(outputStream);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
        }
    }
}
