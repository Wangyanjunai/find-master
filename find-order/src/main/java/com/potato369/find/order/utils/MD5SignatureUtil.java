package com.potato369.find.order.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.LinkedMultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
public class MD5SignatureUtil {
    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_COO = "sign_code";
    public static final String SIGN_TYPE = "sign_type";

    public static Map<String, String> generateForMap(Map<String, String> data, String key) throws Exception {
        StringBuilder sb = mapToStr(data, key);
        String sign = MD5(sb.toString());
        data.put("sign", sign);
        return data;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static LinkedMultiValueMap<String, String> convertToMutiMap(Map<String, String> data) throws Exception {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        LinkedList list = null;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            list = new LinkedList<>();
            list.add(entry.getValue());
            map.put(entry.getKey(), list);
        }
        return map;
    }

    public static String generate(Map<String, String> data, String key) throws Exception {
        StringBuilder sb = mapToStr(data, key);
        String sign = MD5(sb.toString());
        return sb.append("&").append(FIELD_SIGN).append("=").append(sign).toString();
    }

    public static boolean valid(Map<String, String> data, String key) throws Exception {
        String sign = data.get(FIELD_SIGN);
        StringBuilder sb = mapToStr(data, key);
        return MD5(sb.toString()).equals(sign);
    }

    public static boolean validCooCaa(Map<String, String> data, String key) throws Exception {
        String sign = data.get(FIELD_SIGN_COO);
        StringBuilder sb = mapToStr(data, key);
        return MD5(sb.toString()).equalsIgnoreCase(sign);
    }

    public static boolean validLe(Map<String, String> data, String key) throws Exception {
        String sign = data.get(FIELD_SIGN);
        StringBuilder sb = mapToStr(data, key);
        return MD5(sb.toString()).equalsIgnoreCase(sign);
    }

    public static Map<String, String> requestToMap(HttpServletRequest request) throws Exception {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> data = request.getParameterMap();
        String v = null;
        for (String k : data.keySet()) {
            if (data.get(k) == null || data.get(k).length < 1) {
                continue;
            }
            v = data.get(k)[0];
            if (v != null && v.trim().length() > 0) {
                //参数值为空，则不参与签名
                params.put(k, data.get(k)[0].trim());
            }
        }
//      log.info("乐视回调结果返回params={}", params);
        return params;
    }

    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    private static StringBuilder mapToStr(Map<String, String> data, String key) {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(FIELD_SIGN)) {
                continue;
            }
            if (data != null && data.get(k) != null && data.get(k).trim().length() > 0) {
                // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
            }
        }
        StringBuilder signStringBuilder = sb.append("key=").append(key);
//      log.info("参与签名字符串signString={}", signStringBuilder.toString());
        return signStringBuilder;
    }

    public static String mapToStr(Map<String, String> data) {
        StringBuilder sbf = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (StringUtils.isNotEmpty(entry.getKey())) {
                // 参数值为空，则不参与签名
                sbf.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return sbf.toString();
    }

    public static String createLinkString(Map<String, String> params, String appkey) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            prestr = prestr + value;
        }
        return prestr + appkey;
    }

    public static String createSign(Map<String, String> params, String signKey) throws Exception {
        String str = createLinkString(params, signKey);
        StringBuilder toSign = new StringBuilder().append(str);
        return MD5SignatureUtil.MD5(toSign.toString());
    }

    public static String createSign(Map<String, String> params, String signKey, String[] ignoredParams) throws Exception {
        SortedMap<String, String> sortedMap = new TreeMap<>(params);
        StringBuilder toSign = new StringBuilder();
        for (String key : sortedMap.keySet()) {
            String value = params.get(key);
            boolean shouldSign = false;
            if (StringUtils.isNotEmpty(value) && !ArrayUtils.contains(ignoredParams, key) && !Lists.newArrayList("sign", "key", "xmlString", "xmlDoc", "couponList").contains(key)) {
                shouldSign = true;
            }
            if (shouldSign) {
                toSign.append(value);
            }
        }
        toSign.append(signKey);
        return MD5SignatureUtil.MD5(toSign.toString());
    }

    public static String createLeSign(Map<String, String> params, String signKey) throws Exception {
//    	log.info("params={}, signKey={}", params, signKey);
        StringBuilder signStringBuilder = mapToStr(params, signKey);
        return MD5SignatureUtil.MD5(signStringBuilder.toString()).toLowerCase();
    }

    /**
     * <pre>
     * 获取海信数据带签名字符串
     * @param params
     * @param enableEmpty
     * @param addQuot
     * @return
     * </pre>
     */
    public static String getHisenceSignData(Map<String, String> params, boolean enableEmpty, boolean addQuot, boolean hasSignType) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(params.keySet());
        //将key按照字母顺序进行排序
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if (hasSignType) {
                if (FIELD_SIGN.equals(key)) {
                    continue;
                }
            } else {
                if (FIELD_SIGN.equals(key) || SIGN_TYPE.equals(key)) {
                    continue;
                }
            }
            String value = params.get(key);
            if (value != null && !"".equals(value)) {
                if (addQuot) {
                    content.append((i == 0 ? "" : "&") + key + "=\"" + value + "\"");
                } else {
                    content.append((i == 0 ? "" : "&") + key + "=" + value + "");
                }
            } else if (enableEmpty) {
                content.append((i == 0 ? "" : "&") + key + "");
            }
        }
        String contentStr = content.toString();
        if (contentStr.startsWith("&")) {
            return contentStr.substring(1);
        }
        return contentStr;
    }

    /**
     * <pre>
     * 获取海信签名
     * @param content
     * @param key
     * @return
     * @throws Exception
     * </pre>
     */
    public static String hisenceSign(String content, String key) throws Exception {
        String toSign = (content == null ? "" : content) + key;
        log.info("待签名字符串={}", toSign);
        return MD5(toSign).toLowerCase();
    }

    /**
     * <pre>
     * 验证海信签名
     * @param data 待签名字符串数据
     * @param key 签名密钥
     * @param hasSignType signType参数是否参与签名
     * @return
     * @throws Exception
     * </pre>
     */
    public static boolean validHisence(Map<String, String> data, String key, boolean hasSignType) throws Exception {
        String sign = data.get(FIELD_SIGN);
        String toSignStr = getHisenceSignData(data, false, false, hasSignType);
        String validSign = hisenceSign(toSignStr, key);
        log.info("validSign={}, sign={}", validSign, sign);
        return validSign.equals(sign);
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
