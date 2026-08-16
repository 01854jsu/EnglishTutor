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


public class QuestionServlet  extends HttpServlet{
	
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
	    
	    String userid = "";
		if(request.getParameter("userid") != null)
			userid = request.getParameter("userid");
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
		int useridint = -1;
	    if(userid != null && !userid.equalsIgnoreCase("")) {
			  userid = userid.trim();
		  	  useridint = Integer.parseInt(userid);
		   }
		
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
					
				if(selqueans != null) {
					String sql = "select * from questionanswer where grade = " + gradeint + " and week = " + weekint;
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
					//String exp= "";
					while (rs.next()) {
						greetings = rs.getString("answer") + "," + rs.getString("quecount") ;
						//System.out.println("greeting " + greetings);
					}
					
				}else if(questionscount != null) {
					String sql = "select * from questionanswer where grade = " + gradeint + " and week = " + weekint;
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
					String exp= "";
					if (rs.next()) {
						sql = "UPDATE questionanswer SET answer ='" + contents + "', quecount=" + Integer.parseInt(questionscount) + " WHERE grade = " + gradeint + " and week = " + weekint;
						//stmt.executeUpdate(sql);
						
						int rsint = -1;
						rsint = stmt.executeUpdate(sql);
						 /*
						 while(rsint < 0) {
						        try {
						          Thread.sleep(1000);
						        } catch(InterruptedException e) {
						        	greetings = "connection failed, try later";
									e.printStackTrace();
						        }
						      }
						 */
						greetings = "Update question answer and count is successsful";
						//System.out.println("greeting " + greetings);
					}else {
						sql = "insert into questionanswer (grade,week,answer,quecount) values(" + gradeint + "," + weekint + ",'" + contents + "'," + Integer.parseInt(questionscount) + ")";
						//stmt.executeUpdate(sql);
						
						int rsint = -1;
						rsint = stmt.executeUpdate(sql);
						 /*
						 while(rsint < 0) {
						        try {
						          Thread.sleep(1000);
						        } catch(InterruptedException e) {
						        	greetings = "connection failed, try later";
									e.printStackTrace();
						        }
						      }
						 */
						greetings = "Insert question answer and count is successsful";
					}
					
				}else if(selque != null) {
					String sql = "select * from questionexplain where grade = " + gradeint + " and week = " + weekint;
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
					String exp= "";
					while (rs.next()) {
						greetings = rs.getString("exp" + selque);
						//System.out.println("greeting " + greetings);
					}
					
				}else if(needquetot != null) {
					String sql = "select * from questionanswer where grade = " + gradeint + " and week = " + weekint;
					Statement stmtsel=con.createStatement(); 
					ResultSet rssel = null;
					
					rssel = stmtsel.executeQuery(sql);
					
					/*
					while(rssel == null) {
				        try {
				          Thread.sleep(1000);
				        } catch(InterruptedException e) {
				        	greetings = "connection failed, try later";
							e.printStackTrace();
				        }
				      }
				      */
					//*
					int questol = 0;
					while (rssel.next()) {
						questol = rssel.getInt("quecount");
					}
					//*/
					String exp1= "";
					String exp2= "";
					String exp3= "";
					String exp4= "";
					String exp5= "";
					String exp6= "";
					String exp7= "";
					String exp8= "";
					String exp9= "";
					String exp10= "";
					String sqltot = "select * from questionexplain where grade = " + gradeint + " and week = " + weekint;
					Statement stmttot=con.createStatement(); 
					//ResultSet rstot = stmttot.executeQuery(sqltot);
					
					ResultSet rstot  = null;
					rstot = stmttot.executeQuery(sqltot);
					/*
					while(rstot == null) {
				        try {
				          Thread.sleep(1000);
				        } catch(InterruptedException e) {
				        	greetings = "connection failed, try later";
							e.printStackTrace();
				        }
				      }
					*/
					while (rstot.next()) {
			            exp1 = rstot.getString("exp1");
			            exp2 = rstot.getString("exp2");
			            exp3 = rstot.getString("exp3");
			            exp4 = rstot.getString("exp4");
			            exp5 = rstot.getString("exp5");
			            exp6 = rstot.getString("exp6");
			            exp7 = rstot.getString("exp7");
			            exp8 = rstot.getString("exp8");
			            exp9 = rstot.getString("exp9");
			            exp10 = rstot.getString("exp10");
			            //System.out.println("name " + coffeeName );
			            //String exp = 
						//System.out.println("greeting " + greetings);
					}
					System.out.println("exp1 " + exp1 );
					System.out.println("exp2 " + exp2 );
					System.out.println("exp3 " + exp3 );
					String exp= "";
					if(questol==1)
						exp = exp1;
					else if(questol==2)
						exp = exp1 + "/" + exp2 + "/";
					else if(questol==3)
						exp = exp1 + "/" + exp2 + "/" + exp3 + "/";
					else if(questol==4)
						exp = exp1 + "/" + exp2 + "/" + exp3 + "/" + exp4 + "/";
					else if(questol==5)
						exp = exp1 + "/" + exp2 + "/" + exp3 + "/" + exp4 + "/" + exp5 + "/";
					else if(questol==6)
						exp = exp1 + "/" + exp2 + "/" + exp3 + "/" + exp4 + "/" + exp5 + "/" + exp6 + "/";
					else if(questol==7)
						exp = exp1 + "/" + exp2 + "/" + exp3 + "/" + exp4 + "/" + exp5 + "/" + exp6 + "/" + exp7 + "/";
					else if(questol==8)
						exp = exp1 + "/" + exp2 + "/" + exp3 + "/" + exp4 + "/" + exp5 + "/" + exp6 + "/" + exp7 + "/" + exp8 + "/";
					else if(questol==9)
						exp = exp1 + "/" + exp2 + "/" + exp3 + "/" + exp4 + "/" + exp5 + "/" + exp6 + "/" + exp7 + "/" + exp8 + "/" + exp9 + "/";
					else if(questol==10)
						exp = exp1 + "/" + exp2 + "/" + exp3 + "/" + exp4 + "/" + exp5 + "/" + exp6 + "/" + exp7 + "/" + exp8 + "/" + exp9 + "/" + exp10 + "/";
					
					

					sql = "UPDATE questionexplain SET explaintext ='" + exp + "' WHERE grade = " + gradeint + " and week = " + weekint;
					
					System.out.println("total sql " + sql);
					
					stmt=con.createStatement(); 
					//stmt.executeUpdate(sql);

					int rsint = -1;
					rsint = stmt.executeUpdate(sql);
					 /*
					 while(rsint < 0) {
					        try {
					          Thread.sleep(1000);
					        } catch(InterruptedException e) {
					        	greetings = "connection failed, try later";
								e.printStackTrace();
					        }
					      }
					 */
					greetings = "Insert question explain is successsful";
					
				}else if(exquscontents != null) {
					String sql = "select * from questionexplain where grade = " + gradeint + " and week = " + weekint;
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
						if (!rs.next()) {	
							String sqlins = "";
							
							if(questionsint == 1)
				 				sqlins = "insert into questionexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','" + exquscontents + "','','','','','','','','','')";
							else if(questionsint == 2)
								sqlins = "insert into questionexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','" + exquscontents + "','','','','','','','','')";
							else if(questionsint == 3)
								sqlins = "insert into questionexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','" + exquscontents + "','','','','','','','')";
							else if(questionsint == 4)
								sqlins = "insert into questionexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','" + exquscontents + "','','','','','','')";
							else if(questionsint == 5)
								sqlins = "insert into questionexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','" + exquscontents + "','','','','','')";
							else if(questionsint == 6)
								sqlins = "insert into questionexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','','" + exquscontents + "','','','','')";
							else if(questionsint == 7)
								sqlins = "insert into questionexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','','','" + exquscontents + "','','','')";
							else if(questionsint == 8)
								sqlins = "insert into questionexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','','','','" + exquscontents + "','','')";
							else if(questionsint == 9)
								sqlins = "insert into questionexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','','','','','" + exquscontents + "','')";
							else
								sqlins = "insert into questionexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','','','','','','" + exquscontents + "')";
							
							//System.out.println("sqlins " + sqlins);
							Statement stmtins=con.createStatement(); 
							//stmtins.executeUpdate(sqlins); 
							
							int rsint = -1;
							rsint = stmtins.executeUpdate(sqlins);
							 /*
							 while(rsint < 0) {
							        try {
							          Thread.sleep(1000);
							        } catch(InterruptedException e) {
							        	greetings = "connection failed, try later";
										e.printStackTrace();
							        }
							      }
							 */
							greetings = "Insert is successsful";
						}else {
							String sqlins = "UPDATE questionexplain SET exp" + questionsint + " ='" + exquscontents + "' WHERE grade = " + gradeint + " and week = " + weekint;
							//System.out.println("sqlins " + sqlins);
							Statement stmtins=con.createStatement();  
							//stmtins.executeUpdate(sqlins);
							
							int rsint = -1;
							rsint = stmtins.executeUpdate(sqlins);
							 /*
							 while(rsint < 0) {
							        try {
							          Thread.sleep(1000);
							        } catch(InterruptedException e) {
							        	greetings = "connection failed, try later";
										e.printStackTrace();
							        }
							      }
							 */
							greetings = "Update is successsful";
						}					
				}else if(contents != null) {
				
				String sql = "select * from question where grade = " + gradeint + " and week = " + weekint;
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
					if (!rs.next()) {		
						String sqlins = "insert into question (grade,week,questiontext) values(" + gradeint + "," + weekint + ",'" + contents + "')";
						//System.out.println("sqlins " + sqlins);
						Statement stmtins=con.createStatement(); 
						//stmtins.executeUpdate(sqlins); 
						
						int rsint = -1;
						rsint = stmtins.executeUpdate(sqlins);
						 /*
						 while(rsint < 0) {
						        try {
						          Thread.sleep(1000);
						        } catch(InterruptedException e) {
						        	greetings = "connection failed, try later";
									e.printStackTrace();
						        }
						      }
						 */
						greetings = "Insert is successsful";
					}else {
						String sqlins = "UPDATE question SET questiontext ='" + contents + "' WHERE grade = " + gradeint + " and week = " + weekint;
						//System.out.println("sqlins " + sqlins);
						Statement stmtins=con.createStatement();  
						//stmtins.executeUpdate(sqlins);
						int rsint = -1;
						rsint = stmtins.executeUpdate(sqlins);
						 /*
						 while(rsint < 0) {
						        try {
						          Thread.sleep(1000);
						        } catch(InterruptedException e) {
						        	greetings = "connection failed, try later";
									e.printStackTrace();
						        }
						      }
						 */
						greetings = "Update is successsful";
					}
				
				}else {
				String sql = "select * from question where grade = " + gradeint + " and week = " + weekint;
				String sqlans = "select * from questionanswer where grade = " + gradeint + " and week = " + weekint;
				String sqlansct = "select * from questionanswercnt where userid = " + useridint+ " and grade = " + gradeint + " and week = " + weekint;
				//System.out.println("gradeint" + gradeint);
				//System.out.println("weekint" + weekint);
				//System.out.println("useridint" + useridint);
			
				//System.out.println("greeting 4");
				//System.out.println("sqlansct " + sqlansct);
				//System.out.println("greeting 5");
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
						greetings = rs.getString("questiontext");
					//System.out.println("greeting " + greetings);
					}
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
						greetingsans = rsans.getString("answer");
					//greetingsanscount = rsans.getString("anscount");    
					//System.out.println("greetingsans " + greetingsans);
					}
				Statement stmtansct=con.createStatement(); 
				//ResultSet rsansct = stmtansct.executeQuery(sqlansct); 
				
				ResultSet rsansct = null;
				rsansct = stmtansct.executeQuery(sqlansct);
				/*
				while(rsansct == null) {
			        try {
			          Thread.sleep(1000);
			        } catch(InterruptedException e) {
			        	greetings = "connection failed, try later";
						e.printStackTrace();
			        }
			      }
				*/
					while (rsansct.next()) {
		            //String coffeeName = rs.getString(2);
		            //System.out.println("name " + coffeeName );
						greetingsanscount = rsansct.getString("anscount");    
					//System.out.println("greetingsanscount " + greetingsanscount);
					}
				
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
		
		if(exquscontents != null || selque != null || needquetot != null || contents != null || selqueans != null || questionscount != null) {
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
