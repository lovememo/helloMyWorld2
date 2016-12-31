package com.opm.gateway.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opm.gateway.enumtype.LoginResultEnum;
import com.opm.gateway.enumtype.SessionAttribute;
import com.opm.gateway.session.DseSession;
import com.opm.gateway.session.DseSessionContext;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-liuyz1 on 2016/12/20.
 */
@Controller
public class LoginController {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpSession httpSession, HttpServletRequest request){
        LOGGER.info("user login...");

        DseSession dseSession = DseSessionContext.getDseSessionContext().getSession(request);
        if(null == dseSession){
            return LoginResultEnum.FAILED.toString();
        }
        else{
            dseSession.getMap().put(SessionAttribute.USER_TOKEN,request.getParameter(SessionAttribute.DSE_SESSIONID));
            return "{ \"ret\":\"" + LoginResultEnum.SUCCESS.toString() + "\"}";
        }
    }
}
