package com.opm.filetemplate;

/**
 * Created by kfzx-liuyz1 on 2016/12/21.
 */
public class XmlFileFactory implements IFileFactory{
    @Override
    public IFileGenerator createFileCenerator() {
        return new XmlFileGenerator();
    }
}
