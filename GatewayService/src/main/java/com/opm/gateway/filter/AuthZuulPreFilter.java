package com.opm.gateway.filter;

import ch.qos.logback.classic.Logger;
import com.opm.gateway.enumtype.SessionAttribute;
import com.opm.gateway.session.DseSession;
import com.opm.gateway.session.DseSessionContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import java.util.Arrays;

/**
 * Created by kfzx-liuyz1 on 2016/11/18.
 */

@Component
public class AuthZuulPreFilter extends ZuulFilter {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AuthZuulPreFilter.class);

    private final String[] routeUrlList = {"/login",};
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        String url = context.getRequest().getRequestURI();
        return !Arrays.asList(this.routeUrlList).contains(url);
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        DseSession dseSession = DseSessionContext.getDseSessionContext().getSession(context);
        if(null == dseSession){
            this.setFailedRequest(FilterStatus.SESSION_TIMEOUT);
        }
        else{
            Object userToken = dseSession.getMap().get(SessionAttribute.USER_TOKEN);
            if(null == userToken || StringUtils.isEmpty(userToken.toString())){
                this.setFailedRequest(FilterStatus.SESSION_TIMEOUT);
            }
        }

        return null;
    }

    private void setFailedRequest(FilterStatus filterStatus) {
        LOGGER.error("Not login userId is null");
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(filterStatus.getCode());
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(filterStatus.geBodyMsg());
            ctx.setSendZuulResponse(true);
            ctx.sendZuulResponse();
            throw new RuntimeException("Code: " + filterStatus.getCode() + ", " + filterStatus.geBodyMsg()); //optional
        }
    }

}
