package com.lucky.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lucky.common.exception.CommonException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class HttpRequest {

    private static Logger logger = LoggerFactory.getLogger(HttpRequest.class);

    /**
     * POST请求
     *
     * @param url
     *            URL
     * @param parameterMap
     *            请求参数
     * @return 返回结果
     */
    public static String post(String url, Map<String, Object> parameterMap) {
        Assert.hasText(url,"url不能为空");
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null) {
                for (Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    if (StringUtils.isNotEmpty(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity,"UTF-8");
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            throw new CommonException(e);
        } catch (IOException e) {
            throw new CommonException(e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }

    /**
     * POST请求
     *
     * @param url
     *            URL
     * @param parameterMap
     *            请求参数
     * @return 返回结果
     * @throws Exception
     */
    public static String sslpost(String url, Map<String, Object> parameterMap) throws Exception {
        Assert.hasText(url,"url不能为空");
        String result = null;
        HttpClient httpClient = new SSLClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null) {
                for (Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    nameValuePairs.add(new BasicNameValuePair(name, value));

                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity,"UTF-8");
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            throw new CommonException(e);
        } catch (IOException e) {
            throw new CommonException(e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }

    public static String post(String url, String content) {
        Assert.hasText(url,"url不能为空");
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            HttpEntity entity = new StringEntity(content, "UTF-8");
            httpPost.setEntity(entity);
            logger.debug("Post Request start time: " + new Date(0));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            logger.debug("Post Request end time: " + new Date(0));
            HttpEntity httpEntity = httpResponse.getEntity();
            logger.debug("httpEntity: " + httpEntity);
            result = EntityUtils.toString(httpEntity,"UTF-8");
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            logger.error("http post:" + e.getMessage());
            throw new CommonException(e);
        } catch (IOException e) {
            logger.error("http post:" + e.getMessage());
            throw new CommonException(e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }

    /**
     * GET请求
     *
     * @param url
     *            URL
     * @param parameterMap
     *            请求参数
     * @return 返回结果
     */
    public static String get(String url, Map<String, Object> parameterMap) {
        Assert.hasText(url,"url不能为空");
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null) {
                for (Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    if (StringUtils.isNotEmpty(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            HttpGet httpGet = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?") + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity,"UTF-8");
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            throw new CommonException(e);
        } catch (IOException e) {
            throw new CommonException(e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }

    /**
     * GET请求
     *
     * @param url
     *            URL
     * @param parameterMap
     *            请求参数
     * @return 返回结果
     * @throws Exception
     */
    public static String sslget(String url, Map<String, Object> parameterMap) throws Exception {
        Assert.hasText(url,"url不能为空");
        String result = null;
        HttpClient httpClient = new SSLClient();
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null) {
                for (Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    if (StringUtils.isNotEmpty(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            HttpGet httpGet = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?") + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity,"UTF-8");
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            throw new CommonException(e);
        } catch (IOException e) {
            throw new CommonException(e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }

}
