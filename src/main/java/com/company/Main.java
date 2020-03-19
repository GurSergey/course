package com.company;

import com.company.servlets.PollEditServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class Main {

    public static void main(String[] args) throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8082);
        tomcat.setHostname("localhost");
        String appBase = ".";
        tomcat.getHost().setAppBase(appBase);
        Context ctx = tomcat.addContext("", new File("views/").getAbsolutePath());
        Tomcat.addServlet(ctx, "Poll",  PollEditServlet.class);
        ctx.addServletMapping("/polls", "Poll");
//        String docBase = "views/";
//        tomcat.addWebapp("/", new File(docBase).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
