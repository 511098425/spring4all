package com.qzj.dqq.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qzj on 2018/3/8
 */
@Controller
@SessionAttributes({"authorizationRequest"})
public class OAuthController {

    @RequestMapping({ "/oauth/my_approval"})
    @ResponseBody
    public JSONObject getAccessConfirmation(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes"));
        List<String> scopeList = new ArrayList<>();
        for (String scope : scopes.keySet()) {
            scopeList.add(scope);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("scopeList",scopeList);
        return jsonObject;
    }

    @RequestMapping({ "/oauth/my_error" })
    public String handleError(Map<String, Object> model, HttpServletRequest request) {
        Object error = request.getAttribute("error");
        String errorSummary;
        if (error instanceof OAuth2Exception) {
            OAuth2Exception oauthError = (OAuth2Exception) error;
            errorSummary = HtmlUtils.htmlEscape(oauthError.getSummary());
        } else {
            errorSummary = "Unknown error";
        }
        model.put("errorSummary", errorSummary);
        return "oauth_error";
    }


}