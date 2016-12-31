package com.opm.data.dtl.controller;

import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.data.common.model.DataErrorMsg;
import com.opm.data.dtl.model.DtlControl;
import com.opm.data.dtl.model.DtlFile;
import com.opm.data.dtl.service.IDataProcessService;
import com.opm.data.dtl.service.impl.DataCleaningWorker;
import com.opm.data.dtl.service.thread.DataCleaningThread;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/9.
 */
@RestController
//@RequestMapping("/file")
public class FileController {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(FileController.class);

    @Autowired
    private IDataProcessService dataProcessService;


    /**
     * ���ļ��ϴ�
     * @param request
     * @param response
     * @param dtlItemId
     * @param isRealTimeStr 1-ʵʱ 0-�첽 Ĭ��Ϊʵʱ
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/file/upload", method = {RequestMethod.GET, RequestMethod.POST})
    public void upload(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestParam(value = "dtlItemId", required = false) String dtlItemId,
                       @RequestParam(value = "isRealTime", required = false) String isRealTimeStr) throws UnsupportedEncodingException {
        List<DtlFile> dtlFileList = new ArrayList();
        List<DtlControl> dtlControlList = new ArrayList();
        boolean isRealTime = !"0".equals(isRealTimeStr);/*case:null -> ʵʱ case: 2 ->ʵʱ case: 1 -> ʵʱ case: 0 ->�첽 ��Ĭ��Ϊʵʱ*/
        try {
            DataCleaningWorker worker = new DataCleaningWorker(request, this.dataProcessService, dtlItemId);
            //step1 �����ļ�������Ʊ�����ȡ�Ǽ��ļ���
            LOGGER.info("step0 �����ļ�������Ʊ�����ȡ�Ǽ��ļ���");
            dtlControlList = worker.beforeWork();
            if(isRealTime) {
                dtlFileList = worker.syncWork();
                returnJsonStr(response, dtlFileList);
            } else {
                worker.syncSaveWork();
                //new DataCleaningThread(worker).start();//�첽�߳�
                returnJsonStr(response, dtlControlList);
            }
        } catch (Exception e) {
            LOGGER.info("�ļ��ϴ�����ʧ�ܣ�" + e.getMessage());
            returnJsonStr(response, new DataErrorMsg("�ϴ�ʧ�ܣ�����ϵ����Ա��ʧ����Ϣ��" + e.getMessage()));
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "dtl/cleaning", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseModel clean(RequestModel resqModel) {
        ResponseModel respModel = new ResponseModel(ResponseModel.State.FAIL, "clean ʧ��", null);
        try {
            String serialNo = (String) resqModel.getPrivateCtx().get("serialNo");
            if (null == serialNo || "".equals(serialNo)) {
                respModel.setMsg("δ�ҵ�serialNo�ļ���");
            }
            DataCleaningWorker woker = new DataCleaningWorker(serialNo, this.dataProcessService);
            new DataCleaningThread(woker).start();
            respModel.setMsg("���������첽������...serialNoΪ��" + serialNo);
            respModel.setObj(resqModel);
        }
        catch(Exception e) {
            LOGGER.info("��������ʧ�ܣ�" + e.getMessage());
            e.printStackTrace();
        }
        return respModel;
    }

    /**
     *��ҳ��ѯ�ϴ����ļ��б�
     * @param serialNo �ļ����к�
     * @param beginPos ��ʼλ��
     * @param fetchNum ��ȡ��
     * @return
     */
    @RequestMapping(value = "file/list", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Object> queryFileUploadedList(
            @RequestParam(value = "serialNo") String serialNo,
            @RequestParam(value = "begNum") String beginPos,
            @RequestParam(value = "fetchNum") String fetchNum) {
        return this.dataProcessService.turnPageFileUploadedList(serialNo, beginPos, fetchNum);
    }

    /**
     * ���ļ�����
     *
     * @param serialNo
     * @return
     */
    @RequestMapping(value = "file/download", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<byte[]> download(@RequestParam(value = "serialNo") String serialNo) {
        DtlFile fi;

        try {
            fi = this.dataProcessService.readFile(serialNo);
            String fileName = new String(fi.getFileName().getBytes("GBK"),"iso8859-1");
//            String fileName = fi.getFileName();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);
            return new ResponseEntity<>(fi.getFileContent(), headers, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("�ļ����ش���ʧ�ܣ�" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "dtl/count")
    public ResponseModel getDataProcessedCount(@RequestParam(value = "serialNo") String serialNo) {
        int count = this.dataProcessService.getDataProcessedCount(serialNo);
        HashMap mp = new HashMap<String, Integer>();
        mp.put("count", count);
        ResponseModel responseModel = new ResponseModel(ResponseModel.State.SUCC, "getDataProcessedCount3�ɹ�", mp);
        return responseModel;
    }


    private Gson gson = new Gson();

    private void returnJsonStr(HttpServletResponse response, Object obj) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String retJson = gson.toJson(obj);
            response.getWriter().write(retJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
