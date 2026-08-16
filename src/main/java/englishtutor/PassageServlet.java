package englishtutor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import java.io.InputStream;


public class PassageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
		String week =String.valueOf((int) request.getSession(false).getAttribute("week")).trim();
		  //String grade = request.getParameter("grade").trim();
		  int gradeint = Integer.parseInt(grade);
		  //String week = request.getParameter("week").trim();
		  int weekint = Integer.parseInt(week);
		  */
		  //String contents = request.getParameter("contents").trim();
		  String contents = request.getParameter("contents");
		  if(contents != null) 
			  contents = contents.trim();
		  
		  String contentExp = request.getParameter("contentExp");
		  if(contentExp != null) 
			  contentExp = contentExp.trim();
		  
		  
		  
		  //passage explanation
		  
		  String questionscount = request.getParameter("questionscount");
		  if(questionscount != null) 
			  questionscount = questionscount.trim();
		  String selpsgexplan = request.getParameter("selpsgexplan");
		  if(selpsgexplan != null) 
			  selpsgexplan = selpsgexplan.trim();
		  //System.out.println("selpsgexplan " + selpsgexplan);String selpsgexplan = request.getParameter("selpsgexplan");
		 /* 
		  String quesanswers = request.getParameter("quesanswers");
		  if(quesanswers != null) 
			  quesanswers = quesanswers.trim();
		  */
		  /*
		  String selqueans = request.getParameter("selqueans");
		  if(selqueans != null) 
			  selqueans = selqueans.trim();
			  */
		  //System.out.println("selqueans " + selqueans);
		  String needpsgexptot = request.getParameter("needpsgexptot");
		  if(needpsgexptot != null) 
			  needpsgexptot = needpsgexptot.trim();
		  String psgexpcontents = request.getParameter("psgexpcontents");
		  if(psgexpcontents != null) 
			  psgexpcontents = psgexpcontents.trim();
		  

		  String psgexplan = request.getParameter("psgexplan");
		  int psgexplanint =-1;
		  if(psgexplan != null) {
			  psgexplan = psgexplan.trim();
			  psgexplanint = Integer.parseInt(psgexplan);
		  }
		  
		  
		  
		  
		  
		  //
		  /*
		  System.out.println("gradeint " + gradeint);
		  System.out.println("weekint " + weekint);
		  System.out.println("contentExp " + contentExp);
		  //
		   */
		  //userName = "666";
		  String greetings = "";
		  ResultSet rs = null;
			Statement stmt = null;
			Connection con = null;
			//String result = "F";
			//System.out.println("greeting 2");
		  
		  try {
				//Class.forName("cubrid.jdbc.driver.CUBRIDDriver"); 
				//Connection con = DriverManager.getConnection("jdbc:cubrid:localhost:30000:cloudIN:dba:cloud:");
			  //System.out.println("greeting 3");
			    //Class.forName("com.mysql.cj.jdbc.Driver"); 
			    //Class.forName("com.mysql.jdbc.GoogleDriver"); 
			    //Class.forName("com.mysql.jdbc.Driver");
				//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/englishtutor?user=root&password=Jsu01854");  
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
			  //  con = DriverManager.getConnection("jdbc:mysql://172.30.176.3:3306/englishtutor?user=root&password=Jsu01854");
			  //String sql = "select * from customer";
				//String sql = "select * from customer where customer_id = " + Integer.parseInt(userName.trim());
				String sql = "";
				String sqlupd = "";
				stmt=con.createStatement();
				Statement stmtbooks=con.createStatement();
				if(selpsgexplan != null) {
					sql = "select * from teacherexplain where grade = " + gradeint + " and week = " + weekint;
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
						greetings = rs.getString("exp" + selpsgexplan);
						//System.out.println("greeting " + greetings);
					}
					
				}else if(needpsgexptot != null) {
					/*
					sql = "select * from questionanswer where grade = " + gradeint + " and week = " + weekint;
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
					/*
					int questol = 0;
					while (rssel.next()) {
						questol = rssel.getInt("quecount");
					}
					*/
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
					String sqltot = "select * from teacherexplain where grade = " + gradeint + " and week = " + weekint;
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
					
					String exp = exp1 + " " + exp2 + " " + exp3 + " " + exp4 + " " + exp5 + " " + exp6 + " " + exp7+ " " + exp8 + " " + exp9+ " " + exp10;
					//System.out.println("exp " + exp);
					/*
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
					
					*/

					sql = "UPDATE teacherexplain SET explaintext ='" + exp + "' WHERE grade = " + gradeint + " and week = " + weekint;
					
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
					greetings = "Insert passage explain is successsful";
					
				}else if(psgexpcontents != null) {
					sql = "select * from teacherexplain where grade = " + gradeint + " and week = " + weekint;
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
							
							if(psgexplanint == 1)
				 				sqlins = "insert into teacherexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','" + psgexpcontents + "','','','','','','','','','')";
							else if(psgexplanint == 2)
								sqlins = "insert into teacherexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','" + psgexpcontents + "','','','','','','','','')";
							else if(psgexplanint == 3)
								sqlins = "insert into teacherexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','" + psgexpcontents + "','','','','','','','')";
							else if(psgexplanint == 4)
								sqlins = "insert into teacherexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','" + psgexpcontents + "','','','','','','')";
							else if(psgexplanint == 5)
								sqlins = "insert into teacherexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','" + psgexpcontents + "','','','','','')";
							else if(psgexplanint == 6)
								sqlins = "insert into teacherexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','','" + psgexpcontents + "','','','','')";
							else if(psgexplanint == 7)
								sqlins = "insert into teacherexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','','','" + psgexpcontents + "','','','')";
							else if(psgexplanint == 8)
								sqlins = "insert into teacherexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','','','','" + psgexpcontents + "','','')";
							else if(psgexplanint == 9)
								sqlins = "insert into teacherexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','','','','','" + psgexpcontents + "','')";
							else
								sqlins = "insert into teacherexplain (grade,week,explaintext,exp1,exp2,exp3,exp4,exp5,exp6,exp7,exp8,exp9,exp10) values(" + gradeint + "," + weekint + ",'','','','','','','','','','','" + psgexpcontents + "')";
							
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
							String sqlins = "UPDATE teacherexplain SET exp" + psgexplanint + " ='" + psgexpcontents + "' WHERE grade = " + gradeint + " and week = " + weekint;
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
				}else if(contentExp != null) {
					
					sql = "select * from teacherexplain where grade = " + gradeint + " and week = " + weekint;
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
						sqlupd = "insert into teacherexplain (grade, week,explaintext) values (" + gradeint + ", " + weekint + ",'" +  contentExp + "')";
						greetings = "insert is successful";
					}else {
						sqlupd = "update teacherexplain set explaintext = '" +  contentExp + "' where grade = " + gradeint + " and week = " + weekint;  
						greetings = "update is successful";
					}
					stmtbooks.executeUpdate(sqlupd); 
				}else {
				
				
				sql = "select * from books where grade = " + gradeint + " and week = " + weekint;
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
					sqlupd = "insert into books (grade, week,content) values (" + gradeint + ", " + weekint + ",'" +  contents + "')";
					greetings = "insert is successful";
				}else {
					sqlupd = "update books set content = '" +  contents + "' where grade = " + gradeint + " and week = " + weekint;  
					greetings = "update is successful";
				}
					//System.out.println("greeting 4");
				//System.out.println("sql" + sql);
				//System.out.println("greeting 5");
				
				stmtbooks.executeUpdate(sqlupd); 
				
				}
		  		}
				catch(SQLException ex){
	            //logger.error("Cannot close connection");
					//System.out.println("SQLException");
					//System.out.println("greeting 6");
					greetings = "SQLException" + ex.getMessage();
					//System.out.println("greeting 61" + greetings);
					ex.printStackTrace();
					//result = "connection failed, try later";
				}
				catch (Exception e) 
				{

					//System.out.println("greeting 62");
					greetings = "SQLException" + e.getMessage();
					//System.out.println("greeting 62" + greetings);
				e.printStackTrace();
				}
				finally{
					try {
					con.close();
					}
					catch (Exception e) 
					{

						//System.out.println("greeting 63");
						greetings = "SQLException" + e.getMessage();
						//System.out.println("greeting 63" + greetings);
					e.printStackTrace();
					}
					 	//DBUtil.closeResultSet(rs);
						//DBUtil.closeStatement(stmt);
						//DBUtil.closeConnection(con);
					//return result;
					//return user.equalsIgnoreCase("in28Minutes") && password.equals("dummy");
				}
		 

		  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		  response.setHeader("Expires", "0"); // Proxies.
		response.setContentType("text/plain");
		response.getWriter().write(greetings);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
