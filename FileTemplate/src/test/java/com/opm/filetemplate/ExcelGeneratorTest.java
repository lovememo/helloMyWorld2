package com.opm.filetemplate;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/12/21.
 */
public class ExcelGeneratorTest {

    private Map<String, Object> map = new HashMap<String, Object>();


    @Before
    public void before(){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        for (int i = 0; i < 8; i++) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", "id" + "1");
            m.put("uname", "name" + "1");
            m.put("amount", i + "");
            list.add(m);
        }
        map.put("list", list);
        map.put("name","大头");
        map.put("role","领导");
    }

    @Test
    public void test2() throws Exception {

        //Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("E:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("E:/excel/merge_test.xls");
        new ExcelFileFactory().createFileCenerator().generateByTemplate("c://Temp/","merge_test.xls",map,fos);
        fos.close();
    }

}
