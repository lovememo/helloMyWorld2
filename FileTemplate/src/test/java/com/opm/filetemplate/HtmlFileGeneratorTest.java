package com.opm.filetemplate;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/5.
 */
public class HtmlFileGeneratorTest {

    private Map map = new HashMap();

    @Before
    public void before(){
        map.put("user", "lavasoft");
        map.put("url", "http://www.baidu.com/");
        map.put("name", "°Ù¶È");
    }
    @Test
    public void testProcess(){

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("C:/Temp/test.html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            new HtmlFileFactory().createFileCenerator().generateByTemplate("c:/Temp/","test.ftl",map,out);

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
