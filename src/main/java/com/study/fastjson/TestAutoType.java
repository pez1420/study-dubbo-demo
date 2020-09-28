package com.study.fastjson;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author pez1420@gmail.com
 * @version $Id: TestSerialize.java v 0.1 2020/6/1 10:02 上午 pez1420 Exp $$
 */
public class TestAutoType {

    public static void main(String[] args) {
        // 具体实现类解析为JSON字符串时，我们可以为其指定SerializerFeature
        B b = new B();
        b.setParamA("a");
        b.setParamB("b");
        // 将携带一个@type的属性，指定了序列化的类。
        // {"@type":"com.study.fastjson.B","paramA":"a","paramB":"b"}
        //当我们将B或者C解析为JSON时，我们添加SerializerFeature：
        String str = JSONObject.toJSONString(b, SerializerFeature.WriteClassName);
        // 可以直接使用抽象类A，通过反序列化解析字符串，得到具体的实现类了
        System.out.println(str);

        // 根据@type，得到结果是 b
        B bb = (B) JSONObject.parseObject(str, A.class);
        System.out.println(JSON.toJSON(bb));
        ParserConfig.getGlobalInstance().setSafeMode(true);

    }

    @Test
    public void testTypeReference() {
        // 当我们用 fastjson 如下 API 转成 List<T> 这种类型时，会遇到类型丢失的问题
        // com.alibaba.fastjson.JSON#parseObject(java.lang.String, java.lang.Class<T>)

        ParserConfig.getGlobalInstance().setSafeMode(true);
        ParserConfig.getGlobalInstance().setAutoTypeSupport(false);

        String testJSON = "[{\"type\":\"a\"},{\"type\":\"b\"}]";
        List<Button> buttons = JSON.parseObject(testJSON, new TypeReference<ArrayList<Button>>() {
        });

        System.out.println(JSON.toJSONString(buttons));
    }
}
