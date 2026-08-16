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

import java.util.Properties;
import java.io.InputStream;
//import com.mysql.jdbc.*;

//import com.mysql.jdbc.Driver;


public class TeacherExplainServlet  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//*grade=" + grade + "&week=" + week;

		//System.out.println("greeting 1");
		//System.out.println("greeting 1");
		
		
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
		
		
		
		String grade = "";
		if(request.getParameter("grade") != null)
			grade = request.getParameter("grade");			
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
	    
		int gradeint = 0;
	    if(grade != null) {
			  grade = grade.trim();
		  	  gradeint = Integer.parseInt(grade);
		   }
		
	    String week = "";
		if(request.getParameter("week") != null)
			week = request.getParameter("week");	
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
	      //week =String.valueOf((int) request.getSession(false).getAttribute("week"));
	    
		int weekint = 0;
	    if(week != null) {
			  week = week.trim();
		  	  weekint = Integer.parseInt(week);
		   }
	    /*
		  String grade =String.valueOf((int) request.getSession(false).getAttribute("grade")).trim();
		  //String grade = request.getParameter("grade").trim();
		  String week =String.valueOf((int) request.getSession(false).getAttribute("week")).trim();
		  */
		  //String week = request.getParameter("week").trim();
		  String questionon = request.getParameter("questionon");
		  if(questionon != null) 
			  questionon = questionon.trim();
		  String correctans = request.getParameter("correctans");
		  if(correctans != null) 
			  correctans = correctans.trim();
		  String answeron = request.getParameter("answeron");
		  if(answeron != null) 
			  answeron = answeron.trim();
		  String questionnum = request.getParameter("questionnum");
		  if(questionnum != null) 
			  questionnum = questionnum.trim();
		  String answercount = request.getParameter("answercount");
		  if(answercount != null) 
			  answercount = answercount.trim();
		  String coin = request.getParameter("coin");
		  int coinint = 0;
		  if(coin != null) {
			  coin = coin.trim();
			  coinint = Integer.parseInt(coin.trim());
		  }
		  String grammar = request.getParameter("grammar");
		  if(grammar != null) 
			  grammar = grammar.trim();
		  
		 /*
		while(request.getSession(false).getAttribute("userid") == null) {
			        try {
			          Thread.sleep(1000);
			        } catch(InterruptedException e) {
			        	//greetings = "connection failed, try later";
						e.printStackTrace();
			        }
			      }	
	
		     // week =String.valueOf((int) request.getSession(false).getAttribute("week"));		

		  String userid =String.valueOf((int) request.getSession(false).getAttribute("userid"));
		  */
		  String userid = "";
			if(request.getParameter("userid") != null)
				userid = request.getParameter("userid");	
		  
		  //String userid = request.getParameter("userid");
		  int useridint = 0;
		  if(userid != null) {
			  userid = userid.trim();
			  useridint = Integer.parseInt(userid.trim());
		  }
		  //int gradeint = Integer.parseInt(grade.trim());
		  //int weekint = Integer.parseInt(week.trim());
		  //int coinint = Integer.parseInt(coin.trim());
		  //int useridint = Integer.parseInt(userid.trim());
		  //System.out.println("questionnum " + questionnum);
		  //System.out.println("correctans " + correctans);
		  //System.out.println("answercount " + answercount);
		  //System.out.println("coinint " + coinint);
		  //System.out.println("useridint " + useridint);
		  
		  //System.out.println("grade " + grade);

		  //System.out.println("questionon " + questionon);
		  //userName = "666";
		  String greetings = "";
		  ResultSet rs = null;
			Statement stmt = null;
			///*
			String greetingsans = "";	  
			ResultSet rsans = null;
			Statement stmtans = null;
			ResultSet rscoins = null;
			Statement stmtcoins = null;
			ResultSet rscoinsbf = null;
			Statement stmtcoinsbf = null;
				//*/
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
				String sql;
				String sqlans = "";
				//System.out.println("grade " + gradeint);
				//System.out.println("week " + weekint);
				if(grammar != null)	{			
					   sql = "select * from grammarexplain where grade = " + gradeint + " and week = " + weekint;
					   //sql = "select * from teacherexplain where grade = " + Integer.parseInt(grade.trim()) + " and week = " + Integer.parseInt(week.trim());
					   System.out.println("grammar in" + sql);
					   stmtans=con.createStatement(); 
						//rsans = stmtans.executeQuery(sqlans); 
						
						rsans = null;
						rsans = stmtans.executeQuery(sql);
						while (rsans.next()) {
				            //String coffeeName = rs.getString(2);
				            //System.out.println("name " + coffeeName );
							greetings = rsans.getString("explaintext");
							//System.out.println("greetingsans " + greetingsans);
						}
				}
				else if(questionon.equalsIgnoreCase("0"))	{			
				   sql = "select * from teacherexplain where grade = " + gradeint + " and week = " + weekint;
				   //sql = "select * from teacherexplain where grade = " + Integer.parseInt(grade.trim()) + " and week = " + Integer.parseInt(week.trim());
				   //System.out.println("sqlteacher " + sql);
				}
				   else
				{					
					//if(questionnum.equalsIgnoreCase("-1"))
					//{
					   
						sql = "select * from questionexplain where grade = " + gradeint + " and week = " + weekint;
						//sql = "select * from questionexplain where grade = " + Integer.parseInt(grade.trim()) + " and week = " + Integer.parseInt(week.trim());
						//System.out.println("sql " + sql);
						//System.out.println("gradeint " + gradeint);
						//System.out.println("weekint " + weekint);
						sqlans = "select * from questionanswercnt where userid = " + useridint + " and grade = " + gradeint + " and week = " + weekint;
						//sqlans = "select * from questionanswer where grade = " + Integer.parseInt(grade.trim()) + " and week = " + Integer.parseInt(week.trim());
						stmtans=con.createStatement(); 
						//rsans = stmtans.executeQuery(sqlans); 
						
						rsans = null;
						rsans = stmtans.executeQuery(sqlans);
						/*
						while(rsans == null) {
					        try {
					          Thread.sleep(1000);
					        } catch(InterruptedException e) {
					        	greetings = "connection failed, try later";
								e.printStackTrace();
					        }
					      }
						*/
						while (rsans.next()) {
				            //String coffeeName = rs.getString(2);
				            //System.out.println("name " + coffeeName );
							greetingsans = rsans.getString("anscount");
							//System.out.println("greetingsans " + greetingsans);
						}
						
						//if(questionnum.equalsIgnoreCase("0"))
						//{
						
						
							int index = Integer.parseInt(questionnum.trim()) * 4;
							//System.out.println("index " + index);
							String tempstring = replaceChar(greetingsans,answercount, index);
							//System.out.println("tempstring " + tempstring);
							index = Integer.parseInt(questionnum.trim()) * 4 + 2;
							//System.out.println("index 2 " + index);
							 String greetingsins = replaceChar(tempstring,correctans, index);
						
							//System.out.println("greetingsans " + greetingsans);
							//System.out.println("greetingsins " + greetingsins);
						String sqlins = "UPDATE questionanswercnt SET anscount ='" + greetingsins + "' WHERE userid =" +  useridint+ " and grade = " + gradeint + " and week = " + weekint;
						//System.out.println("sqlins " + sqlins);
						Statement stmtins=con.createStatement();  
						//int dbres = stmtins.executeUpdate(sqlins);
						
						int dbres = -1;
						dbres = stmtins.executeUpdate(sqlins);
						 /*
						 while(dbres < 0) {
						        try {
						          Thread.sleep(1000);
						        } catch(InterruptedException e) {
						        	greetings = "connection failed, try later";
									e.printStackTrace();
						        }
						      }
						
						*/
						if(coinint > 0) {
							int coinsbf=0;
							String sqlcoinsbf = "select * from users where userid= " + useridint;
							//System.out.println("sqlcoinsbf " + sqlcoinsbf);
							stmtcoinsbf=con.createStatement(); 
							//rscoinsbf = stmtcoinsbf.executeQuery(sqlcoinsbf); 
							
							rscoinsbf = null;
							rscoinsbf = stmtcoinsbf.executeQuery(sqlcoinsbf); 
							/*
							while(rscoinsbf == null) {
						        try {
						          Thread.sleep(1000);
						        } catch(InterruptedException e) {
						        	greetings = "connection failed, try later";
									e.printStackTrace();
						        }
						      }
							*/
							while (rscoinsbf.next()) {
					            //String coffeeName = rs.getString(2);
					            //System.out.println("name " + coffeeName );
								coinsbf = rscoinsbf.getInt("money");
								//System.out.println("greeting " + greetings);
							}
							coinint = coinint + coinsbf;
							String sqlcoins = "UPDATE users SET money ='" + coinint + "' WHERE userid= " + useridint;
							//String sqlcoins = "UPDATE users SET money ='" + coinint + "' WHERE grade =" +  gradeint + " and week= " + weekint + " and userid= " + useridint;
							//System.out.println("sqlcoins " + sqlcoins);
							stmtcoins=con.createStatement();  
							//int dbcoinsres = stmtcoins.executeUpdate(sqlcoins);
							
							int dbcoinsres = -1;
							dbcoinsres = stmtcoins.executeUpdate(sqlcoins);
							 /*
							 while(dbcoinsres < 0) {
							        try {
							          Thread.sleep(1000);
							        } catch(InterruptedException e) {
							        	greetings = "connection failed, try later";
										e.printStackTrace();
							        }
							      }
							*/
						}
					}
				
				/*
				{
					sql = "select * from questionexplain where grade = " + Integer.parseInt(grade.trim()) + " and week = " + Integer.parseInt(week.trim());
					sqlans = "select * from questionanswer where grade = " + Integer.parseInt(grade.trim()) + " and week = " + Integer.parseInt(week.trim())  + " and question = " + Integer.parseInt(questionon.trim());
					
				}
				*/
					//System.out.println("greeting 4");
				//System.out.println("sql" + sql);
				//System.out.println("greeting 5");
				///*
				stmt=con.createStatement(); 
				//rs = stmt.executeQuery(sql);
				
				rs = null;
				rs = stmt.executeQuery(sql);
				/*
				while(rs == null) {
			        try {
			          Thread.sleep(1000);
			        } catch(InterruptedException e) {
			        	greetings = "connection failed, try later";
						e.printStackTrace();
			        }
			      }
				*/
				while (rs.next()) {
		            //String coffeeName = rs.getString(2);
		            //System.out.println("name " + coffeeName );
					greetings = rs.getString("explaintext");
					//System.out.println("greeting " + greetings);
				}
				//
				/*
				if(!questionon.equalsIgnoreCase("0")) 
				{
				stmtans=con.createStatement(); 
				rsans = stmtans.executeQuery(sqlans); 
				while (rsans.next()) {
		            //String coffeeName = rs.getString(2);
		            //System.out.println("name " + coffeeName );
					greetingsans = rsans.getString("anscount");
					//System.out.println("greeting " + greetings);
				}
				}
				//
				 */
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
				finally{
					try {
						if(rs != null)
							rs.close();
						if(stmt != null)
							stmt.close();
						if(rsans != null)
							rsans.close();
						if(stmtans != null)
							stmtans.close();	
						if(stmtcoins != null)
							stmtcoins.close();
						if(rscoinsbf != null)
							rscoinsbf.close();
						if(stmtcoinsbf != null)
							stmtcoinsbf.close();
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
		  //greetings = greetingsans + ";" + greetings;
		  //System.out.println("finalgreeting " + greetings);
		  

		  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		  response.setHeader("Expires", "0"); // Proxies.
		response.setContentType("text/plain");
		response.getWriter().write(greetings);
		/*
		if(questionon.equalsIgnoreCase("0")) 
			response.getWriter().write(greetings);
		else
			response.getWriter().write(greetingsans + "/" + greetings);
		*/
	}
	
	public String replaceChar(String str, String ch, int index) {
		//if(index == 0)
			return str.substring(0, index) + ch + str.substring(index+1);
		//else
			//return str.substring(0, index-1) + ch + str.substring(index+1);
	}

}
