<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "javax.servlet.RequestDispatcher" %>
<%  
//int n=Integer.parseInt(request.getParameter("val"));  
  
//for(int i=1;i<=10;i++)  
	//String username = request.getParameter("username");
	//String password = request.getParameter("password");
	//request.setAttribute("username",username);
	//request.setAttribute("password",password);
	//request.getSession().setAttribute("username",username);
	//request.getSession().setAttribute("password",password);
	RequestDispatcher rd = request.getRequestDispatcher("/main");
     //request.setAttribute("msg","HI Welcome");
     rd.forward(request, response);

//out.print("That summer I found Winn-Dixie was also Lowell summer me");
out.print(response);  
  
%> 