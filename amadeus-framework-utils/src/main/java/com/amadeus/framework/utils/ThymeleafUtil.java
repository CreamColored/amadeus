package com.amadeus.framework.utils;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;

public class ThymeleafUtil {

    public String staticThymeleaf(String fileName) {
        //构造模板引擎
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文(Model)
        Context context = new Context();
        context.setVariable("name","wxp");
        //渲染模板
        try {
            FileWriter fileWriter = new FileWriter(fileName+"test.html");
            templateEngine.process("test", context, fileWriter);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
