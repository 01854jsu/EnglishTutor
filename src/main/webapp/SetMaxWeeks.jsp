<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "javax.servlet.RequestDispatcher" %>
<%  
//int n=Integer.parseInt(request.getParameter("val"));  
  
//for(int i=1;i<=10;i++)  
	
	javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("/word");
     //request.setAttribute("msg","HI Welcome");
     out.print(request);  
     rd.forward(request, response);

//out.print("That summer I found Winn-Dixie was also Lowell summer me");
out.print(response);  
  
%> 