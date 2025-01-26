package kr.or.ddit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.module.ModuleDescriptor.Version;
import java.util.Locale;
import java.util.TimeZone;


@WebServlet("/homework01/server-info.do")
public class ServerInfoServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out = response.getWriter();
      
      String jvmVersion = System.getProperty("java.vm.version");
      String javaVendor = System.getProperty("java.vendor");
      String osName = System.getProperty("os.name");
      String osVersion = System.getProperty("os.version");
      String country = Locale.getDefault().getCountry();
      String language = Locale.getDefault().getLanguage();
      String timeZone = TimeZone.getDefault().getID();
      
      out.println("<table border='1'>");
      out.println("<tr><td><b>Infomation Name</b></th><th>Information Value</th></tr>");
      out.println("<tr><td>JVM version</td><td>" + jvmVersion + "</td><tr>");
      out.println("<tr><td>Java Vendor</td><td>" + javaVendor + "</td><tr>");
      out.println("<tr><td>OS Name</td><td>" + osName + "</td><tr>");
      out.println("<tr><td>Os Version</td><td>" + osVersion + "</td><tr>");
      out.println("<tr><td>Server Country</td><td>" + country + "</td><tr>");
      out.println("<tr><td>Server Language</td><td>" + language + "</td><tr>");
      out.println("<tr><td>Server TimeZone</td><td>" + timeZone + "</td><tr>");
      
      
      
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   }

}
