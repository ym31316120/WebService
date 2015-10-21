<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.io.OutputStream" %>
<%@ page import ="java.io.File" %>
<%@ page import ="java.io.FileInputStream" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
hello world!!!

<%

response.reset();//可以加也可以不加
response.setContentType("application/x-download");
String imgName = request.getParameter("img");
String path=application.getRealPath("/"); //当前你求请的JSP文件的物理路径
System.out.println("path="+path);
String dir=new File(path).getParent();//当前JSP文件所在目录的物理路径
System.out.println("dir="+dir);
String filedownload = path+"img\\"+imgName;
String filedisplay = imgName;
response.addHeader("Content-Disposition","attachment;filename=" + filedisplay);

OutputStream outp = null;
FileInputStream in = null;
try
{
    outp = response.getOutputStream();
    in = new FileInputStream(filedownload);

    byte[] b = new byte[1024];
    int i = 0;

    while((i = in.read(b)) > 0)
    {
        outp.write(b, 0, i);
    }
    outp.flush();
}
catch(Exception e)
{
    System.out.println("Error!");
    e.printStackTrace();
}
finally
{
    if(in != null)
    {
        in.close();
        in = null;
    }
    if(outp != null)
    {
        outp.close();
        outp = null;
    }
}

%>
</body>
</html>