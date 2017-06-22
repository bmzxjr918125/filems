<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String remotePath ="http://localhost:8080/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>index.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   <!--http://192.168.0.188:8080/filems/testUpload.jsp-->
   <form action="filemsApiUploadImageList?fileType=2&&account=10000" 
              enctype="multipart/form-data" method="post">
            文件1:        <input type="file" name="imageList"><br/>
            文件2:        <input type="file" name="imageList"><br/>
            文件3:        <input type="file" name="imageList"><br/>
                 <input type="submit" value="上传" />
        </form>
        
   
  </body>
</html>
