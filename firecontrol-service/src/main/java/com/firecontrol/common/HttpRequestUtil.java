package com.firecontrol.common;

/**
 * Created by mariry on 2019/12/5.
 */

import com.alibaba.druid.util.StringUtils;
import com.firecontrol.domain.entity.Response;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class HttpRequestUtil {

    public  Response sendPostRequest(String url, Map<String, Object> params, String token){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        HttpMethod method = HttpMethod.POST;
        // 以什么方式提交，自行选择，一般使用json，或者表单
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(!StringUtils.isEmpty(token)) {
            headers.add("Authorization", token);
        }

//        //body
//        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
//        requestBody.add("roundid", "1");
//        //HttpEntity
//        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestHeaders);

        //将请求头部和参数合成一个请求
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, headers);

        //执行HTTP请求，将返回的结构使用Response类格式化
        ResponseEntity<Response> response = client.exchange(url, method, requestEntity, Response.class);

        return response.getBody();

    }

    public String getTpsonToken(String url, String company) {
        String token = "";
        //1. 获取token
        String secret = StringMD5Util.stringMD5(company + "#" + company);
        Map param = new HashMap();
        param.put("company", company);
        param.put("secret", secret);

        Response t = this.sendPostRequest(url, param, "");
        Map map = (Map)t.getData();

        token = "Bearer " + (String)map.get("token") ;

        return token;
    }



}
