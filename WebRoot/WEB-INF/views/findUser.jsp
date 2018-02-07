<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>  
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<title>Insert title here</title>  
</head>  
<body>  
    <center>  
        通过controller访问<br/>  
        欢迎<br/>  
      <c:out value = "${user.userName}"/>
      <br />   
    </center>  
</body>  
</html>  