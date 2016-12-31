package com.opm.filetemplate;

import ch.qos.logback.classic.Logger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/5.
 */
class HtmlFileGenerator implements IFileGenerator{

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(HtmlFileGenerator.class);

    private Configuration cfg = new Configuration();

    private void process(String tmplateFileName, Map<String, Object> dataMap, BufferedWriter out) throws Exception {
        if(null != out){
            Template t = this.cfg.getTemplate(tmplateFileName);
            t.process(dataMap, out);
        }

        //t.process(dataMap, new OutputStreamWriter(System.out));
    }

    @Override
    public void generate(Object data, OutputStream outputStream) {

    }

    @Override
    public void generateByTemplate(String templateFilePath, String fileName, Map<String, Object> map, OutputStream outputStream) {
        try {
            File templateFileDir = ResourceUtils.getFile(templateFilePath);
            assert templateFileDir != null;
            this.cfg.setDirectoryForTemplateLoading(templateFileDir);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            BufferedWriter bw = new BufferedWriter(osw);
            this.process(fileName,map,bw);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

}
