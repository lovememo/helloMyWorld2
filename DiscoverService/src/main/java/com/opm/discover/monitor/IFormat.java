package com.opm.discover.monitor;

import java.io.OutputStream;

/**
 * Created by kfzx-liuyz1 on 2016/12/23.
 */
public interface IFormat {
    public void doFormat(Object data, OutputStream outputStream);
}
