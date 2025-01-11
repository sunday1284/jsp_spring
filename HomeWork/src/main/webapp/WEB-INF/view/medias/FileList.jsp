<%@page import="java.io.File"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>파일 리스트</title>
</head>
<body>
    <h1>파일 목록</h1>
    <%
        String currentPath = (String) request.getAttribute("currentPath");
        if (currentPath == null) {
            currentPath = "";
        }
        List<String> fileList = (List<String>) request.getAttribute("fileList");
    %>
    <h2>연결된 폴더: <%= currentPath.isEmpty() ? "00.medias" : currentPath %></h2>
    <ul>
        <%
            if (fileList == null || fileList.isEmpty()) {
        %>
            <li>No files available in this directory.</li>
        <%
            } else {
                for (int i = 0; i < fileList.size(); i++) {
                	//파일 리스트를 가져옴
                    String file = fileList.get(i);
                    boolean isDirectory = new File("E:/00.medias", file).isDirectory();
        %>
                    <li>
                        <a href="<%= request.getContextPath() %>/medias/files?<%= isDirectory ? "target=" + file : "file=" + file %>">
                            <%= isDirectory ? "[폴더] " : "" %><%= file %> <!-- 파일명 표시 -->
                        </a>
                    </li>
        <%
                }
            }
        %>
    </ul>
</body>
</html>
