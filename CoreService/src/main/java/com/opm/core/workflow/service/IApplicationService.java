package com.opm.core.workflow.service;

import com.opm.common.enumdict.ApplyStatus;
import com.opm.core.workflow.model.ApplicationModle;

/**
 * Created by kfzx-liuyz1 on 2016/10/24.
 */
public interface IApplicationService {
    public ApplicationModle getPlanApplicationList(String planId, int page, int rownum , ApplyStatus... applyStatus);
    public String applyReview(String applyId, String result);
}
