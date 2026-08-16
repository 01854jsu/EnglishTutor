package englishtutor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;

import java.util.Properties;
import java.io.InputStream;
//import com.mysql.jdbc.*;

//import com.mysql.jdbc.Driver;


public class UserServlet  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//*grade=" + grade + "&week=" + week;

		//System.out.println("greeting 1");
		//System.out.println("QuestionServlet greeting 1");
		
	String dbUrl = "";
    	
    	String dbun = "";
    	
    	String dbpw = "";
    	
    	Properties props = new Properties();
    	
    	try (InputStream input = getServletContext().getResourceAsStream("/WEB-INF/classes/application.properties")) {
            if (input == null) {
                // Fallback for standard Maven project structure
                try (InputStream mavenInput = getClass().getClassLoader().getResourceAsStream("application.properties")) {
                    if (mavenInput != null) props.load(mavenInput);
                }
            } else {
                props.load(input);
            }
            
            // Assign the filtered property value to your variable
            dbUrl = props.getProperty("datasource.url");
            dbun = props.getProperty("datasource.username");
            dbpw = props.getProperty("datasource.password");
            
            // Force load the driver class name (good practice for traditional servlets)
            
              //Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (Exception e) {
            throw new ServletException("Failed to load database properties", e);
        }
		
		String paytype = "";
		if(request.getParameter("paytype") != null)
			paytype = request.getParameter("paytype"); 
		
		String reviewprof = "";
		if(request.getParameter("reviewprof") != null)
			reviewprof = request.getParameter("reviewprof");
		
		
		String username = "";
		if(request.getParameter("username") != null)
			username = request.getParameter("username");
		

		String email = "";
		if(request.getParameter("email") != null)
			email = request.getParameter("email");
		
		email = email.toLowerCase();
		//System.out.println("email  is " + email);
		
		
		int gradeint = 0;
		String grade = "";
		if(request.getParameter("grade") != null)
		{
			grade = request.getParameter("grade").trim();
		  	gradeint = Integer.parseInt(grade);
		 }
	    
	    String userid = "";
	    int useridint = 0;
		if(request.getParameter("userid") != null) 
		{
			userid = request.getParameter("userid").trim();
			System.out.println("userid " + userid);
		  	useridint = Integer.parseInt(userid);
	    }
		

		
		
		String month = "";
		int monthint = 0;
		if(request.getParameter("month") != null) 
		{
			month = request.getParameter("month").trim();
		  	monthint = Integer.parseInt(month);
		}
	    
		/*
		else{
			while(request.getSession(false).getAttribute("grade") == null) {
		        try {
		          Thread.sleep(1000);
		        } catch(InterruptedException e) {
		        	//greetings = "connection failed, try later";
					e.printStackTrace();
		        }
		      }
			grade =String.valueOf((int) request.getSession(false).getAttribute("grade"));
			
		}
		*/
	      //grade =String.valueOf((int) request.getSession(false).getAttribute("grade"));
	 		
		/*
		else{
			while(request.getSession(false).getAttribute("week") == null) {
		        try {
		          Thread.sleep(1000);
		        } catch(InterruptedException e) {
		        	//greetings = "connection failed, try later";
					e.printStackTrace();
		        }
		      }
		
	      week =String.valueOf((int) request.getSession(false).getAttribute("week"));
		}
*/
		
		///*
		//String  week =String.valueOf((int) request.getSession(false).getAttribute("week"));
		//String anscount = request.getParameter("anscount");
		int weekint = 0;
		String week = "";
	    if(request.getParameter("anscount") != null) {
			  week = week.trim();
		  	  weekint = Integer.parseInt(week);
		   }
	    //*/
		/*
		else {
			while(request.getSession(false).getAttribute("userid") == null) {
		        try {
		          Thread.sleep(1000);
		        } catch(InterruptedException e) {
		        	//greetings = "connection failed, try later";
					e.printStackTrace();
		        }
		      }
			userid =String.valueOf((int) request.getSession(false).getAttribute("userid"));
		}
*/
			
			
			/*
			if(request.getSession(false).getAttribute("userid") != null)
			userid =String.valueOf((int) request.getSession(false).getAttribute("userid"));
	    */
		
		
		/*
		String grade =String.valueOf((int) request.getSession(false).getAttribute("grade")).trim();
		String week =String.valueOf((int) request.getSession(false).getAttribute("week")).trim();
		  //String grade = request.getParameter("grade").trim();
		  int gradeint = Integer.parseInt(grade);
		  //String week = request.getParameter("week").trim();
		  int weekint = Integer.parseInt(week);
		  
		  */
		  String anscount = request.getParameter("anscount");
		  if(anscount != null) 
			  anscount = anscount.trim();
	    
	      String questionscount = request.getParameter("questionscount");
		  if(questionscount != null) 
			  questionscount = questionscount.trim();
		  String selque = request.getParameter("selque");
		  if(selque != null) 
			  selque = selque.trim();
		  //System.out.println("selque " + selque);String selque = request.getParameter("selque");
		 /* 
		  String quesanswers = request.getParameter("quesanswers");
		  if(quesanswers != null) 
			  quesanswers = quesanswers.trim();
		  */
		  String selqueans = request.getParameter("selqueans");
		  if(selqueans != null) 
			  selqueans = selqueans.trim();
		  //System.out.println("selqueans " + selqueans);
		  String needquetot = request.getParameter("needquetot");
		  if(needquetot != null) 
			  needquetot = needquetot.trim();
		  String contents = request.getParameter("contents");
		  if(contents != null) 
			  contents = contents.trim();
		  String exquscontents = request.getParameter("exquscontents");
		  if(exquscontents != null) 
			  exquscontents = exquscontents.trim();
		  /*
		  String userid = "";
		  if(request.getSession(false).getAttribute("userid") != null)
			  userid =  String.valueOf((int) request.getSession(false).getAttribute("userid"));
		  
		  //String userid =(String) request.getSession(false).getAttribute("userid");
		  //String userid = request.getParameter("userid");
		  int useridint =-1;
		  if(userid != null && !userid.equalsIgnoreCase("")) {
			  userid = userid.trim();
			  useridint = Integer.parseInt(userid);
		  }
		  */
		  String questions = request.getParameter("questions");
		  int questionsint =-1;
		  if(questions != null) {
			  questions = questions.trim();
			  questionsint = Integer.parseInt(questions);
		  }
		  
		  //System.out.println("contents " + contents);
		  //userName = "666";
		  String greetings = "";
		  ResultSet rs = null;
			Statement stmt = null;
			String greetingsans = "";
			String greetingsanscount = "";
			  ResultSet rsans = null;
				Statement stmtans = null;
			Connection con = null;
			String result = "F";
			//System.out.println("grade " + grade);
		  
		  try {
				//Class.forName("cubrid.jdbc.driver.CUBRIDDriver"); 
				//Connection con = DriverManager.getConnection("jdbc:cubrid:localhost:30000:cloudIN:dba:cloud:");
			  //System.out.println("greeting 3");
			    //Class.forName("com.mysql.cj.jdbc.Driver"); 
			    //Class.forName("com.mysql.jdbc.GoogleDriver"); 
			    //Class.forName("com.mysql.jdbc.Driver");
				//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/englishtutor?user=root&password=Jsu01854");
			    //con = DriverManager.getConnection("jdbc:mysql://35.192.176.11:3306/englishtutor?user=root&password=Jsu01854");  
				//con = DriverManager.getConnection("jdbc:google:mysql://35.192.176.11:3306/englishtutor?user=root&password=Jsu01854");
			//local
			  //*
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			  //Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection(dbUrl,dbun,dbpw);
				//con = DriverManager.getConnection("jdbc:mysql://englishtutor.clq26uw26wnu.us-east-2.rds.amazonaws.com:3306/englishtutor?user=root&password=Jsu01854");  
			//*/
			//Google cloud
			  /*
			  Class.forName("com.mysql.cj.jdbc.Driver");
			    con = DriverManager.getConnection("jdbc:mysql://172.30.176.3:3306/englishtutor?user=root&password=Jsu01854");
			    //*/
			  
				
				//Class.forName("com.mysql.cj.jdbc.Driver");
			    //con = DriverManager.getConnection("jdbc:mysql://172.30.176.3:3306/englishtutor?user=root&password=Jsu01854");
			  
			  //String sql = "select * from customer";
				//String sql = "select * from customer where customer_id = " + Integer.parseInt(userName.trim());
				/*
				if(quesanswers != null) {
					String sql = "select * from questionanswer where grade = " + gradeint + " and week = " + weekint;
					stmt=con.createStatement(); 
					rs = stmt.executeQuery(sql);
					String exp= "";
					if (rs.next()) {
						sql = "UPDATE questionanswer SET answer ='" + contents + "', quecount=" +  ",'" + contents + "'," + Integer.parseInt(quesanswers) + " WHERE grade = " + gradeint + " and week = " + weekint;
						stmt.executeUpdate(sql);
						greetings = "Update question answer and count is successsful";
						//System.out.println("greeting " + greetings);
					}else {
						sql = "insert into questionanswer (grade,week,answer,quecount) values(" + gradeint + "," + weekint + ",'" + contents + "'," + Integer.parseInt(quesanswers) + ")";
						stmt.executeUpdate(sql);
						greetings = "Insert question answer and count is successsful";
					}
					
				}else 
				*/	
				//String sql = "";
				
				if(reviewprof != "") {

					System.out.println("reviewprof in");
										
					String sql = "select * from users where userid = '" + useridint + "'";
					
					stmt=con.createStatement(); 
					//rs = stmt.executeQuery(sql);
					
					rs = null;
					rs = stmt.executeQuery(sql);
					//*
					if(!rs.first()) {
						greetings = "No user found";
				      }
					else
					{
					//*/
					//String exp= "";
						rs.beforeFirst();
					     while (rs.next()) {
					    	 //String payedweeks = rs.getString("payedweeks");
					    	 //= Integer.parseInt(rs.getString("payedweeks"));
					    	 
						     greetings = rs.getString("userid") + ";" + rs.getString("emailaddress") + ";" + rs.getString("username") + ";" + rs.getString("grade") + ";" + rs.getString("paytype") + ";" + rs.getString("date") + ";" + (Integer.parseInt(rs.getString("payedweeks"))/4) + ";" + rs.getString("studyitem") + ";";
						 //System.out.println("greeting " + greetings);
					     }
					}

					//System.out.println("greeting " + greetings);
				}else if(username != "" || email != "") {

					//System.out.println("username in");
					String sql = "";
					if (username != "" && email != "")
					{
					    sql = "select * from users where username = '" + username + "' and emailaddress = '" + email + "'";
					}
					else if (username != "")
					{
						sql = "select * from users where username = '" + username + "'";
					}
					else
					{
						sql = "select * from users where emailaddress = '" + email + "' and grade = " + gradeint;
					}
					
					stmt=con.createStatement(); 
					//rs = stmt.executeQuery(sql);
					
					rs = null;
					rs = stmt.executeQuery(sql);
					
					//*
					if(!rs.first()) {
						greetings = "No user found";
				      }
					else
					{
					//*/
					//String exp= "";
						//System.out.println("greeting rs.next()");
						rs.beforeFirst();
						while (rs.next()) {
					    	 //String payedweeks = rs.getString("payedweeks");
					    	 //= Integer.parseInt(rs.getString("payedweeks"));
					    	 
						     greetings = rs.getString("userid") + "/" + (Integer.parseInt(rs.getString("payedweeks")))/4;
						 //System.out.println("greeting " + greetings);
					     }
					}

					//System.out.println("greeting " + greetings);
				}else if(monthint != 0) {
					//System.out.println("monthint in");
					
					String sql = "select * from users where userid = " + useridint;
					
					stmt=con.createStatement(); 
					//rs = stmt.executeQuery(sql);
					
					rs = null;
					rs = stmt.executeQuery(sql);
					
					int payedweeks = 0;					
					while (rs.next()) {
						payedweeks = rs.getInt("payedweeks");
					//System.out.println("greeting " + greetings);
				     }
					
					String weeksordered = "";
					
					//int months = 5;
					int maxweek = 4 * monthint;
					
					for (int k = 1; k < maxweek+1; k++)
					{
						if(k ==1) 
						{
							weeksordered = "1/";
						}
						else
						     weeksordered = weeksordered + k + "/";
					}

					//System.out.println("weeksordered " + weeksordered);
					
					sql = "UPDATE users SET paytype ='" + paytype + "', date ='" + LocalDate.now().plusDays((maxweek - payedweeks) * 7) + "',  weeks ='" + weeksordered + "', payedweeks=" + maxweek + " WHERE userid = " + useridint;
					
					//System.out.println("sql " + sql);
					stmt.executeUpdate(sql);
					
					int rsint = -1;
					rsint = stmt.executeUpdate(sql);
					
					//System.out.println("weeksordered 0");
					
					//System.out.println("payedweeks " + payedweeks);
					
					//System.out.println("maxweek " + maxweek);
					
					for (int i = payedweeks+1; i < maxweek+1; i++)
					{

						//System.out.println("weeksordered 1");
						//System.out.println("i is " + i);
//						sql = "select * from users where userid = " + useridint;
//						
//						stmt=con.createStatement(); 
//						
//						rs = stmt.executeQuery(sql);
//						
//						
//						while (rs.next()) {
//							payedweeks = rs.getInt("payedweeks");
//					     }

						//System.out.println("weeksordered 2");
						sql = "select * from questionanswer where grade = " + gradeint + " and week = " + i;
						stmt=con.createStatement(); 
						//rs = stmt.executeQuery(sql);
						//System.out.println("weeksordered sql " + sql);
						rs = null;
						rs = stmt.executeQuery(sql);
						
						int quecount = 0;
						while (rs.next()) {
							quecount = rs.getInt("quecount");
						//System.out.println("greeting " + greetings);
					     }
						

						//System.out.println("quecount is " + quecount);
						String quecounted = "";
						for (int k = 0; k < quecount; k++)
						{							
								quecounted = quecounted + "0,0/";
						}
						

						//System.out.println("weeksordered 4");
						
						sql = "select * from questionanswercnt where userid = " + useridint + " and grade = " + gradeint + " and week = " + i;
						stmt=con.createStatement(); 
						//rs = stmt.executeQuery(sql);
						
						rs = null;
						rs = stmt.executeQuery(sql);
						
						int answercnt = 0;
						if (rs.next()) {
							
					     }
						else
						{
						   sql = "insert into questionanswercnt (userid,grade,week,anscount) values(" + useridint + "," + gradeint + "," + i + ",'" + quecounted + "')";
						   //System.out.println("sql " + sql);
						   rsint = -1;
						   rsint = stmt.executeUpdate(sql);
						}

						//System.out.println("weeksordered 5");
					}
					
						greetings = "update user weeks, payedweeks; and Insert questionanswercnt anscount are successsful";
						//System.out.println("greeting " + greetings);	
				}								
				
		  		}
				catch(SQLException ex){
	            //logger.error("Cannot close connection");
					//System.out.println("SQLException");
					ex.printStackTrace();
					result = "connection failed, try later";
	            throw new RuntimeException(ex);
				}
				catch (Exception e) 
				{
				e.printStackTrace();
				throw new RuntimeException(e);
				}
				finally
				{
					try {
						//rs.close();
						//stmt.close();
						//rsans.close();
						//stmtans.close();
						con.close();
						
						}catch (Exception e) 
						{
							e.printStackTrace();
							throw new RuntimeException(e);
						}
						
						 	//DBUtil.closeResultSet(rs);
							//DBUtil.closeStatement(stmt);
							//DBUtil.closeConnection(con);
						//return result;
						//return user.equalsIgnoreCase("in28Minutes") && password.equals("dummy");
				

				}
		 
		  /*		
		if(userName.equalsIgnoreCase("1") || "".equals(userName)){
			greetings = "That summer I found Winn-Dixie was also Lowell summer me and Treacher moved to Naomi Flo Rida so he could be the new preacher at the Open Arms Baptist Church of Naomi my daddy is a good preacher and a nice man but sometime is hard for me to think about him as my daddy because he spend so much time trying to know thinking about preaching of gay getting ready to preach and so in my mind I think of him as a preacher before I was born he was a missionary in India and that is how I got my first name but he calls me by my second name because that was his mother's name and he loved her a lot";
		} else {
			greetings = "I like you very much";
		}
		//
		 * */
		
		//greetings = "Hello this is a test";

		  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		  response.setHeader("Expires", "0"); // Proxies.
		response.setContentType("text/plain");
		
		if(username != null || email != null || String.valueOf(questionsint) != null) 
		{
			response.getWriter().write(greetings);
			//System.out.println("greetingsfinal " + greetings);
		}
		else 
			response.getWriter().write(greetingsanscount + ";" + greetingsans + ";" + greetings);
		
		//System.out.println("greeting question cnt" + greetingsanscount + ";" + greetingsans + ";" + greetings);
		//exquscontents = null;
		//contents= null;
	}

}
