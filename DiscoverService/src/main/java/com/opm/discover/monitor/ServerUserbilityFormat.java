package com.opm.discover.monitor;


import com.opm.filetemplate.HtmlFileFactory;
import com.opm.filetemplate.IFileFactory;

import java.io.OutputStream;

/**
 * Created by kfzx-liuyz1 on 2016/12/23.
 */
public class ServerUserbilityFormat implements IFormat {

    private final String usebilityMonitorTemplateFileName = "UsebilityMonitorTemplate.xml";
    private final String templateFilePath = "classpath:template/";

    @Override
    public void doFormat(Object data, OutputStream outputStream) {
        assert data instanceof MicroServiceInfo;
        MicroServiceInfo microServiceInfo = (MicroServiceInfo)data;
        IFileFactory fileFactory = new HtmlFileFactory();
        fileFactory.createFileCenerator().generateByTemplate(templateFilePath,usebilityMonitorTemplateFileName,microServiceInfo.toMap(),outputStream);
    }
}
