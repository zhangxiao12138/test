package com.firecontrol.common;

/**
 * Created by mariry on 2019/12/5.
 */

import com.alibaba.druid.util.StringUtils;
import com.firecontrol.domain.entity.Response;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


public class HttpRequestUtil {


    public static Response sendPostRequest(String url, Map<String, Object> params, String token){
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


//
//
//        // 发送文件数据
////        if (files != null)
////            for (Map.Entry<String, File> file : files.entrySet()) {
////                StringBuilder sb1 = new StringBuilder();
////                sb1.append(PREFIX);
////                sb1.append(BOUNDARY);
////                sb1.append(LINEND);
////                sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\""
////                        + file.getKey() + "\"" + LINEND);
////                sb1.append("Content-Type: application/octet-stream; charset="
////                        + CHARSET + LINEND);
////                sb1.append(LINEND);
////                outStream.write(sb1.toString().getBytes());
////
////                InputStream is = new FileInputStream(file.getValue());
////                byte[] buffer = new byte[1024];
////                int len = 0;
////                while ((len = is.read(buffer)) != -1) {
////                    outStream.write(buffer, 0, len);
////                    Log.i("HttpUtil", "写入中...");
////                }
////
////                is.close();
////                outStream.write(LINEND.getBytes());
////            }
//
//        // 请求结束标志
//        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
//        outStream.write(end_data);
//        outStream.flush();
//        Log.i("HttpUtil", "conn.getContentLength():" + conn.getContentLength());
//
//        // 得到响应码
//        int res = conn.getResponseCode();
//        InputStream in = conn.getInputStream();
//        if (res == 200) {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
//            StringBuffer buffer = new StringBuffer();
//            String line = "";
//            while ((line = bufferedReader.readLine()) != null) {
//                buffer.append(line);
//            }
//
////			int ch;
////			StringBuilder sb2 = new StringBuilder();
////			while ((ch = in.read()) != -1) {
////				sb2.append((char) ch);
////			}
//            return buffer.toString();
//        }
//        outStream.close();
//        conn.disconnect();
//        return in.toString();
//
//    }
//


//    private static CloseableHttpClient httpClient;
//
//    static {
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//        cm.setMaxTotal(100);
//        cm.setDefaultMaxPerRoute(20);
//        cm.setDefaultMaxPerRoute(50);
//        httpClient = HttpClients.custom().setConnectionManager(cm).build();
//    }
//
//    public static String get(String url) {
//        CloseableHttpResponse response = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//            HttpGet httpGet = new HttpGet(url);
//            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
//            httpGet.setConfig(requestConfig);
//            httpGet.setConfig(requestConfig);
//            httpGet.addHeader("Content-type", "application/json; charset=utf-8");
//            httpGet.setHeader("Accept", "application/json");
//            response = httpClient.execute(httpGet);
//            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            StringBuffer sb = new StringBuffer("");
//            String line = "";
//            String NL = System.getProperty("line.separator");
//            while ((line = in.readLine()) != null) {
//                sb.append(line + NL);
//            }
//            in.close();
//            result = sb.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != response) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    public static String post(String url, String jsonString) {
//        CloseableHttpResponse response = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//            HttpPost httpPost = new HttpPost(url);
//            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
//            httpPost.setConfig(requestConfig);
//            httpPost.setConfig(requestConfig);
//            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setEntity(new StringEntity(jsonString, Charset.forName("UTF-8")));
//            response = httpClient.execute(httpPost);
//            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            StringBuffer sb = new StringBuffer("");
//            String line = "";
//            String NL = System.getProperty("line.separator");
//            while ((line = in.readLine()) != null) {
//                sb.append(line + NL);
//            }
//            in.close();
//            result = sb.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != response) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//





//    *//**//**
//     * 向指定 URL 发送POST方法的请求
//     *
//     * @param url
//     *            发送请求的 URL
//     * @param param
//     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//     * @return 所代表远程资源的响应结果
//     *//**//*

}
