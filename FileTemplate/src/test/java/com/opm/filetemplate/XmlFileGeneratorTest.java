package com.opm.filetemplate;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kfzx-liuyz1 on 2016/12/7.
 */
public class XmlFileGeneratorTest {

    private Person person = new Person("Favorate","¿Õ¿Õ","²Ö",new PhoneNumber(2,"38579804"));

    public void before(){
        List<String> address = new ArrayList<String>();
        address.add("ÔÆÇÅÂ·875");
        address.add("ÈÕ±¾");
        person.setAddress(address);
        Role role1 = new Role("Teacher");
        Role role2 = new Role("Actress");
        List<Role> roleList = new ArrayList<Role>();
        roleList.add(role1);
        roleList.add(role2);
        person.setRoles(roleList);
    }

    @Test
    public void testToXMLFileTest(){

        String xmlStr = XmlFileUtil.toXml(person);
        System.out.println(xmlStr);

        File savefile = new File("E:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream("C:/Temp/person.xml");
            new XmlFileFactory().createFileCenerator().generate(person,fos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testToBeanFromFile(){
        try{
            Person person = XmlFileUtil.toBeanFromFile("C:\\Temp\\","person.xml",Person.class);
            System.out.println(person.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
