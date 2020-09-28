package com.study.rule.groovy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;

/**
 * 动态脚本，需要的就是动态执行，因此暂时不考虑groovy编译为字节码的情况，而直接使用java进行解析，并且运行
 *
 * @author pez1420@gmail.com
 * @version $Id: JavaParseGroovy.java v 0.1 2020/7/6 8:07 下午 pez1420 Exp $$
 */
public class GroovyScriptEngineTest {

    static String groovyFile = "com/study/rule/groovy/HelloWord.groovy";

    public static void main(String[] args) throws ScriptException, FileNotFoundException,
                                           IOException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("groovy");
        System.out.println(engine.eval("println 'HelloWord'\n'HelloWordReturn'"));

        //读取源Groovy源程序
        // /Users/luyb/code/study-dubbo-demo/target/src/main/com/study/groovy/HelloWord.groovy
        String fileFullPath = "/Users/luyb/code/study-dubbo-demo/src/main/java/" + groovyFile;
        String scriptContent = IOUtils.toString(new FileInputStream(fileFullPath));
        System.out.println("----------groovy-exec----------");
        engine.eval(scriptContent);
    }

    public static String rootDir() {
        String classDir = GroovyScriptEngineTest.class.getClassLoader().getResource("").getPath();
        int idx = classDir.lastIndexOf("/", classDir.length() - 2);
        return classDir.substring(0, idx);
    }

}
