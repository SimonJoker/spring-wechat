package com.meiben.wechat.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joker on 2016/6/30.
 *
 */
public class XmlUtil {

    /**
     * 将xml格式内容转换为map结构
     * @param request xml内容
     * @return map结构
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(String request) throws DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Document doc = DocumentHelper.parseText(request);

            Element root = doc.getRootElement();

            List<Element> list = root.elements();
            for(Element e : list){
                map.put(e.getName(), e.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }
}
