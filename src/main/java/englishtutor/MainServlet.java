package englishtutor;

import java.io.IOException;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;

//import javax.mail.MessagingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;

import java.util.Properties;
import java.io.InputStream;


//import com.google.appengine.api.utils.SystemProperty;


//import com.mysql.jdbc.GoogleDriver;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (Exception e) {
            throw new ServletException("Failed to load database properties", e);
        }
    	
    	String username = "";
		if(request.getParameter("username") != null) 
			username = request.getParameter("username");
		/*
		else{
			while(request.getSession(false).getAttribute("username") == null) {
		        try {
		          Thread.sleep(1000);
		        } catch(InterruptedException e) {
		        	//greetings = "connection failed, try later";
					e.printStackTrace();
		        }
		      }
		
			username =(String) request.getSession(false).getAttribute("username");
		}
*/
	      //username =(String) request.getSession(false).getAttribute("username");
	  
		if(username != null) {
			  username = username.trim();
			  //request.getSession().setAttribute("username",username);
			  //usernameint = Integer.parseInt(username);
		  }
		//int usernameint = 0;
		  
		
		/*
		 
		if(request.getSession(false).getAttribute("username") != null) {
	      username =(String) request.getSession(false).getAttribute("username");
	      if(username != null) {
			  username = username.trim();
		  	  //usernameint = Integer.parseInt(username);
		   }
		
		}else {
		  username = request.getParameter("username");
	  //int usernameint = 0;
		  if(username != null) {
			  username = username.trim();
			  request.getSession().setAttribute("username",username);
			  //usernameint = Integer.parseInt(username);
		  }
		}
		*/
	
	  
	  String password = "";
		if(request.getParameter("password") != null)
			password = request.getParameter("password");
		/*
		else{
			while(request.getSession(false).getAttribute("password") == null) {
		        try {
		          Thread.sleep(1000);
		        } catch(InterruptedException e) {
		        	//greetings = "connection failed, try later";
					e.printStackTrace();
		        }
		      }
		
			password =(String)request.getSession(false).getAttribute("password");
		}
		*/
	      //password =(String)request.getSession(false).getAttribute("password");
	    
	    if(password != null) {
			  password = password.trim();
		  	  //passwordint = Integer.parseInt(password);
		   }
		
		
		
		
		/*
		
		if(request.getSession(false).getAttribute("password") != null) {
	      password =(String)request.getSession(false).getAttribute("password");
	      if(password != null) {
			  password = password.trim();
		  	  //passwordint = Integer.parseInt(password);
		   }
		
		}else {
		  password = request.getParameter("password");
	  //int passwordint = 0;
		  if(password != null) {
			  password = password.trim();
			  request.getSession().setAttribute("password",password);
			  //passwordint = Integer.parseInt(password);
		  }
		}
		*/
	    String oldpw = request.getParameter("oldpw");
		  //int oldpwint = 0;
		  if(oldpw != null)
			  oldpw = oldpw.trim();
		  
		  String newpw = request.getParameter("newpw");
		  //int newpwint = 0;
		  if(newpw != null)
			  newpw = newpw.trim();

		  String renewpw = request.getParameter("renewpw");
		  //int renewpwint = 0;
		  if(renewpw != null)
			  renewpw = renewpw.trim(); 
		  
		  String resetpw = request.getParameter("resetpw");
		  //int resetpwint = 0;
		  if(resetpw != null)
			  resetpw = resetpw.trim();
		  //System.out.println("resetpw " + resetpw);
		  
		  String getun = request.getParameter("getun");
		  if(getun != null) 
			  getun = getun.trim();

		  //System.out.println("getun " + getun);
		  String studyitem = request.getParameter("studyitem");
		  //int resetpwint = 0;
		  if(studyitem != null)
			  studyitem = studyitem.trim();
	    

		  String cusprofi = request.getParameter("cusprofi");
		  //int resetpwint = 0;
		  if(cusprofi != null)
			  cusprofi = cusprofi.trim();
		  
		  String useridstr = request.getParameter("userid");
		  //int resetpwint = 0;
		  int userid = 0;
		  if(useridstr != null) {
			  useridstr = useridstr.trim();
			  userid = Integer.parseInt(useridstr);
		  }
	    /*
		int userid = 0;
		if(request.getSession(false).getAttribute("userid") != null) {
	      userid =(int) request.getSession(false).getAttribute("userid");	      		
		}
		*/
		  
		String grade = "0";
		/*
		if(request.getSession(false).getAttribute("grade") != null)
	      grade =String.valueOf((int) request.getSession(false).getAttribute("grade"));
		else
		*/
		  grade = request.getParameter("grade");
	  int gradeint = 0;
	  if(grade != null) {
		  grade = grade.trim();
	  	  gradeint = Integer.parseInt(grade);
	  }
	  
	  String week = "0";
	  /*
		if(request.getSession(false).getAttribute("week") != null)
		      week =String.valueOf((int) request.getSession(false).getAttribute("week"));
		else
		*/
			  week = request.getParameter("week");
	  int weekint = 0;
	  //String week =String.valueOf((int) request.getSession(false).getAttribute("week"));
	  //String week = request.getParameter("week").trim();
	  if(week != null) {
	  week = week.trim();
  	  weekint = Integer.parseInt(week);
	  }
		/*
		int grade = 0;
		if(request.getSession(false).getAttribute("grade") != null) {
	      grade =(int) request.getSession(false).getAttribute("grade");	      		
		}
		
		int week = 0;
		if(request.getSession(false).getAttribute("week") != null) {
	      week =(int) request.getSession(false).getAttribute("week");	      		
		}
		*/
		  //String username = request.getParameter("username").trim();
		  //final String username = HttpSession.getAttribute("username").toString();
		  //String username1 =(String) request.getSession(false).getAttribute("username");
		  //String password1 =(String) request.getSession(false).getAttribute("password");
		  /*
	  	  String password = request.getParameter("password");
		  if(password != null) {
			  password = password.trim();
			  request.getSession().setAttribute("password",password);
		  }
		  request.getSession().setAttribute("username",username);
		  */
		  //request.getSession().setAttribute("grade",grade);
		  String chooseweek = request.getParameter("chooseweek");
		  int chooseweekint = 0;
		  if(chooseweek != null)
			  chooseweekint = Integer.parseInt(chooseweek.trim());

		  String displayweek = request.getParameter("displayweek");
		  //int displayweekint = 0;
		  if(displayweek != null)
			  displayweek = displayweek.trim();
		  
		  String unanswerques = request.getParameter("unanswerques");
		  //int unanswerquesint = 0;
		  if(unanswerques != null)
			  unanswerques = unanswerques.trim();
		  
		  String removeunansques = request.getParameter("removeunansques");
		  //int removeunansquesint = 0;
		  if(removeunansques != null)
			  removeunansques = removeunansques.trim();
		  //System.out.println("username " + username);
		  //System.out.println("password " + password);
			
		  //System.out.println("username1 " + username1);
		  //System.out.println("password1 " + password1);
		  String email = request.getParameter("email");
		  if(email != null)
			  email = email.trim();
		  //from user creation
		  /*
		  String gradein = request.getParameter("grade");
		  int gradeint = 0;
		  if(gradein != null)
			  gradeint = Integer.parseInt(gradein.trim());
			  */
		  //userName = "666";
		  String greetings="";
		  if(password != null)
			  greetings = "username and password are wrong,please correct it";
		  ResultSet rs = null;
			Statement stmt = null;
			Connection con = null;
			String result = "F";
			int forward = 0;
			//int grade = 0;
			//int week = 0;

			//int userid = 0;
			//System.out.println("greeting 2");
			
			int paydays;
			String paytypein = "t";
			if(paytypein == "y")
				paydays = 365;
			else if (paytypein == "m")
				paydays = 30;
			else if (paytypein == "t")
				paydays = 30;
			else
				paydays = 7;
		  
		  try {
			  
			  
				//Class.forName("cubrid.jdbc.driver.CUBRIDDriver"); 
				//Connection con = DriverManager.getConnection("jdbc:cubrid:localhost:30000:cloudIN:dba:cloud:");
			  //System.out.println("greeting 3");
			    //Class.forName("com.mysql.cj.jdbc.Driver"); 
			    //Class.forName("com.mysql.jdbc.GoogleDriver");
			    //Class.forName("com.mysql.jdbc.Driver");
				//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/texttospeech?user=root&password=Jsu01854"); 
				//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/englishtutor?user=root&password=Jsu01854");  
				//con = DriverManager.getConnection("jdbc:google:mysql://35.192.176.11:3306/englishtutor?user=root&password=Jsu01854"); 
				//con = DriverManager.getConnection("jdbc:mysql://35.192.176.11:3306/englishtutor?user=root&password=Jsu01854");
			    
			    
			    
			    //Class.forName("com.mysql.cj.jdbc.Driver"); 
			    //Class.forName("com.mysql.jdbc.GoogleDriver");
				
				
				//local
				//*
			  //Class.forName("com.mysql.cj.jdbc.Driver"); dbUrl,
			  //Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(dbUrl,dbun,dbpw);  
				
				
				 
				//con = DriverManager.getConnection("jdbc:mysql://englishtutor.clq26uw26wnu.us-east-2.rds.amazonaws.com:3306/englishtutor?user=root&password=Jsu01854");   
			    //*/
			  //google cloud
			  /*
			    Class.forName("com.mysql.cj.jdbc.Driver");
			    con = DriverManager.getConnection("jdbc:mysql://172.30.176.3:3306/englishtutor?user=root&password=Jsu01854");
			    //*/
			    //con = DriverManager.getConnection("jdbc:mysql://35.192.176.11:3306/englishtutor?user=root&password=Jsu01854");
				//con = DriverManager.getConnection("jdbc:mysql://35.192.176.11:3306/englishtutor?user=root&password=Jsu01854");
				//con = DriverManager.getConnection("jdbc:google:mysql://englishtutor:us-central1:Jsu01854/englishtutor?user=root&password=Jsu01854");
			    //con = DriverManager.getConnection("jdbc:google:mysql://35.192.176.11:3306/englishtutor?user=root&password=Jsu01854");

				//con = DriverManager.getConnection("jdbc:mysql://englishtutor:us-central1:Jsu01854/englishtutor?user=root&password=Jsu01854");
			    
			    
				//DriverManager.get
				stmt=con.createStatement();
				//String sql = "select * from customer";
				//String sql = "select * from customer where customer_id = " + Integer.parseInt(userName.trim());
				//String sql = "insert into books (grade, week,content) values (" + grades + ", " + weeks + ",'" +  contents + "')"; 
				
				
				if(getun != null) {
					//String sql = "update users set weeks= " +  chooseweekint + " where username = '" + username + "' and password = '" +  password + "'"; 
						//greetings="";
						//greetings= grade + "/" + week + ";";

					//System.out.println("greeting getun");
						String sql = "select * from users where grade = '" + gradeint + "' and emailaddress = '" +  email + "'";
						//String sql = "select * from users where userid = '" + userid + "' and emailaddress = '" +  email + "'";
					
					
					//= "select * from users where username = '" + username + "' and password = '" +  password + "'";
					//System.out.println("greeting 4");
					//System.out.println("sql" + sql);
					//System.out.println("greeting 5");
					//System.out.println("mainservlet ");
					//stmt=con.createStatement(); 
					//stmt.executeUpdate(sql); 
						ResultSet	rsresetpw = null;
						rsresetpw = stmt.executeQuery(sql);
					/*
					while(rsresetpw == null) {
				        try {
				          Thread.sleep(1000);
				        } catch(InterruptedException e) {
				        	greetings = "connection failed, try later";
							//System.out.println("greeting 61" + greetings);
							e.printStackTrace();
				        }
				      }
					*/
					//String item="";
					while (rsresetpw.next()) {
						
						String UserName = rsresetpw.getString("username");
						//greetings = "Dear Customer, Your English Tutor UserName is " + UserName;
						String eamilcontent = "Dear Customer, Your English Tutor UserName " + UserName  + " has been sent to you, please use it to log in.";
						String eamilsubject = "Your English Tutor UserName";
						//						/*
						try {
						SendEmail sendEmail = new SendEmail();
						sendEmail.send(email,eamilcontent,eamilsubject);
						greetings = "Your English Tutor UserName has been sent to your email, please use it to log in."; 
						}catch (RuntimeException mex) {
							greetings = "There is a problem to send the email, please check the email address."; 
							
					        mex.printStackTrace();
					    }
						//*/
					}	
					//System.out.println("greetings" + greetings);
		  		} else if(resetpw != null) {
					//String sql = "update users set weeks= " +  chooseweekint + " where username = '" + username + "' and password = '" +  password + "'"; 
						//greetings="";
						//greetings= grade + "/" + week + ";";

					//System.out.println("greeting resetpw");
						String sql = "select * from users where username = '" + username + "' and emailaddress = '" +  email + "'";
						//String sql = "select * from users where userid = '" + userid + "' and emailaddress = '" +  email + "'";
						//System.out.println("sql" + sql);
					
					//= "select * from users where username = '" + username + "' and password = '" +  password + "'";
					//System.out.println("greeting 4");
					//System.out.println("sql" + sql);
					//System.out.println("greeting 5");
					//System.out.println("mainservlet ");
					//stmt=con.createStatement(); 
					//stmt.executeUpdate(sql); 
						ResultSet	rsresetpw = null;
						rsresetpw = stmt.executeQuery(sql);
					/*
					while(rsresetpw == null) {
				        try {
				          Thread.sleep(1000);
				        } catch(InterruptedException e) {
				        	greetings = "connection failed, try later";
							//System.out.println("greeting 61" + greetings);
							e.printStackTrace();
				        }
				      }
					*/
					String item="";
					while (rsresetpw.next()) {
						Random r = new Random();
						int passwordint = r.nextInt((999999 - 111111) + 1) + 111111;
						//System.out.println("LocalDate.now().plusDays(14)" + LocalDate.now().plusDays(14));
						String passwordstr = String.valueOf(passwordint);
						
						String sqlupd = "update users set password= '" +  passwordstr + "' where username = '" + username + "' and emailaddress = '" +  email + "'"; 
						//System.out.println("sqlupd " + sqlupd);
						Statement stupd = con.createStatement();
						int rsint = -1;
						rsint = stupd.executeUpdate(sqlupd);
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
						//greetings = passwordstr;
						String eamilcontent = "Dear Customer, Your English Tutor Temporary password " + passwordstr  + " has been sent to you, please use this password to log in and change to your preferred password from customer profile.";
						String eamilsubject = "Your English Tutor Temparory password";
						//System.out.println("resetpw 1");
						///*
						try {
						SendEmail sendEmail = new SendEmail();
						sendEmail.send(email,eamilcontent,eamilsubject);
						//System.out.println("resetpw 2");
						greetings = "A temporary password has been sent to your email, please use it to log in and change to your password from customer profile."; 
						}catch (RuntimeException mex) {
							greetings = "There is a problem to send the email, please check the email address."; 
							
					        mex.printStackTrace();
					    }
						//*/
					}	
					//System.out.println("greetings" + greetings);
		  		} else if(cusprofi != null) {
					//String sql = "update users set weeks= " +  chooseweekint + " where username = '" + username + "' and password = '" +  password + "'"; 
						//greetings="";
						//greetings= grade + "/" + week + ";"; 
						//String sql = "select * from users where username = '" + username + "' and password = '" +  password + "'";
						String sql = "select * from users where userid = " + userid;
					
					
					//= "select * from users where username = '" + username + "' and password = '" +  password + "'";
					//System.out.println("greeting cusprofi");
					//System.out.println("sql" + sql);
					//System.out.println("greeting 5");
					//System.out.println("mainservlet ");
					//stmt=con.createStatement(); 
					//stmt.executeUpdate(sql); 
						ResultSet rscusprofi = null;
					rscusprofi = stmt.executeQuery(sql);
					/*
					while(rscusprofi == null) {
				        try {
				          Thread.sleep(1000);
				        } catch(InterruptedException e) {
				        	greetings = "connection failed, try later";
							e.printStackTrace();
				        }
				      }
					*/
					String item="";
					while (rscusprofi.next()) {
						String paytype = rscusprofi.getString("paytype");
						String weeks = rscusprofi.getString("weeks");
						String date =  rscusprofi.getString("date");
						String studyitemin = rscusprofi.getString("studyitem");
						int money = rscusprofi.getInt("money");
						greetings= paytype + ";" + weeks + ";" + date + ";" + studyitemin + ";" + String.valueOf(money) + ";";
					}	
					//System.out.println("greetings" + greetings);
		  		} else if(studyitem != null) {
					//String sql = "update users set weeks= " +  chooseweekint + " where username = '" + username + "' and password = '" +  password + "'"; 
					//greetings="";
					//greetings= grade + "/" + week + ";";
		  			//System.out.println("greeting studyitem");
		  			/*
		  			while(username == "") {
				        try {
				          Thread.sleep(1000);
				          System.out.println("greeting studyitem sleep");
				        } catch(InterruptedException e) {
				        	greetings = "connection failed, try later";
							e.printStackTrace();
				        }
				      }
		  			*/
		  			
					String sql = "select * from users where userid = " + userid;
				

		  			//System.out.println("greeting studyitem SQL" + sql);
				//= "select * from users where username = '" + username + "' and password = '" +  password + "'";
				//System.out.println("greeting 4");
				//System.out.println("sql" + sql);
				//System.out.println("greeting 5");
				//System.out.println("mainservlet ");
				//stmt=con.createStatement(); 
				//stmt.executeUpdate(sql); 
					ResultSet rsstudyitem = null;
				rsstudyitem = stmt.executeQuery(sql);
				//System.out.println("greeting studyitem 1");

	  			//System.out.println("greeting studyitem 1");
	  			/*
				while(rsstudyitem == null) {
			        try {
			          Thread.sleep(1000);
			          System.out.println("greeting studyitem sleep");
			        } catch(InterruptedException e) {
			        	greetings = "connection failed, try later";
						e.printStackTrace();
			        }
			      }
				*/
	  			//System.out.println("greeting studyitem 2");
				String item="";
				while (rsstudyitem.next()) {
					greetings= rsstudyitem.getString("studyitem");
				}	
				//System.out.println("greetings studyitem" + greetings);
	  		} else if(oldpw != null) {

	  			//System.out.println("greeting oldpw");
					//if(newpw.equalsIgnoreCase(renewpw)) {
					String sql = "update users set password= '" +  newpw + "' where password = '" + oldpw + "' and userid = " + userid; 	
					//System.out.println("oldpw " + sql);
					 int resint = stmt.executeUpdate(sql);
					 /*
					 while(resint < 0) {
					        try {
					          Thread.sleep(1000);
					        } catch(InterruptedException e) {
					        	greetings = "connection failed, try later";
								e.printStackTrace();
					        }
					      }
					 */
					 greetings = "Updating password has completed."; 
					//} else
					 //greetings = "Updating failed, new password and retype new password are different.";
					
		  	} else if(removeunansques != null) {
	  			//System.out.println("greeting removeunansques");
					//System.out.println("removeunansques greetings ");
					//String sql = "update users set weeks= " +  chooseweekint + " where username = '" + username + "' and password = '" +  password + "'"; 
					greetings="The unanswered questions have been answered.";
					String sql = "delete from unansques where userid = " + userid + " and grade = " + gradeint + " and week = " + weekint + " and ansflag = 'Y' and verified = 'Y'";
					//System.out.println("removeunansques greetings " + sql);
					boolean rsin = stmt.execute(sql);
					/*
					while(rsin) {
				        try {
				          Thread.sleep(1000);
				        } catch(InterruptedException e) {
				        	greetings = "connection failed, try later";
							e.printStackTrace();
				        }
				      }
				      */
					//System.out.println(" removeunansques greetings is " + greetings);
					
			}else if(unanswerques != null) {
					//System.out.println("unanswerques greetings ");
					//String sql = "update users set weeks= " +  chooseweekint + " where username = '" + username + "' and password = '" +  password + "'"; 
					/*
						while(grade == "0") {
					        try {
					          Thread.sleep(1000);
					        } catch(InterruptedException e) {
					        	greetings = "connection failed, try later";
								e.printStackTrace();
					        }
					      }
					      */
						//System.out.println(" unanswerques greetings grade " + grade);
					greetings="grade is " + grade + " and week is " + week + " for unanswered questions:/";
					String sql = "select * from unansques where userid = " + userid + " and grade = " + gradeint + " and week = " + weekint + " and ansflag = 'Y' and verified = 'Y'";
					//System.out.println("unanswerques greetings " + sql);

					ResultSet rsunanswerques = null;
					rsunanswerques = stmt.executeQuery(sql);
					/*
					while(rsunanswerques == null) {
				        try {
				          Thread.sleep(1000);
				        } catch(InterruptedException e) {
				        	greetings = "connection failed, try later";
							e.printStackTrace();
				        }
				      }
					
					*/
					while (rsunanswerques.next()) {
						
						String question = rsunanswerques.getString("question");
						String answer = rsunanswerques.getString("answer");
						greetings= greetings + " question is " + question + ", and answer is " + answer + ";";
					}
					//System.out.println(" unanswerques greetings is " + greetings);
					
			}else if(displayweek != null) {
					//String sql = "update users set weeks= " +  chooseweekint + " where username = '" + username + "' and password = '" +  password + "'"; 
						//greetings="";
						/*
						while(grade == "0") {
					        try {
					          Thread.sleep(1000);
					          System.out.println("greeting displayweek sleep 1");
					        } catch(InterruptedException e) {
					        	greetings = "connection failed, try later";
								e.printStackTrace();
					        }
					      }
						*/
						//System.out.println("greeting displayweek grade" + grade);
						greetings= grade + "/" + week + ";";
						
						
						String sql = "select * from users where userid=" + userid;
					
					
					//= "select * from users where username = '" + username + "' and password = '" +  password + "'";
					//System.out.println("greeting displayweek 1" + sql);
					//System.out.println("sql" + sql);
					//System.out.println("greeting 5");
					//System.out.println("mainservlet ");
					//stmt=con.createStatement(); 
					//stmt.executeUpdate(sql); 
						ResultSet	rsdisplayweek = null;
					rsdisplayweek = stmt.executeQuery(sql);
					/*
					while(rsdisplayweek == null) {
				        try {
				          Thread.sleep(1000);
				          System.out.println("greeting displayweek sleep");
				        } catch(InterruptedException e) {
				        	greetings = "connection failed, try later";
							e.printStackTrace();
				        }
				      }
				      */
					//System.out.println("greeting displayweek 2");
					String date="";
					String paytype="";
					String weeks="";
					while (rsdisplayweek.next()) {
						date = rsdisplayweek.getString("date");
						paytype = rsdisplayweek.getString("paytype");
			            weeks = rsdisplayweek.getString("weeks");
					}
					//System.out.println("date" + date);
					//System.out.println("paytype" + paytype);
					//System.out.println("weeks" + weeks);
					LocalDate date1 = LocalDate.now();
			        LocalDate date2 = LocalDate.parse(date);
					if(date1.isAfter(date2)) {//may need alert before two weeks usage expire
						greetings = greetings + "Thank you for using English Tutoring, your registered usage is expired.";
					}else 
						greetings = greetings + weeks;
					
					

					//System.out.println("greeting displayweek 3" + greetings);
					/*	
						if(paytype.equalsIgnoreCase("y")) {
						greetings = "1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/16/17/18/19/20/21/22/23/24/25/26/27/28/29/30/31/32/33/34/35/36/37/38/39/40/41/42/43/44/45/46/47/48/";
					}else if(paytype.equalsIgnoreCase("m")) {
						greetings = weeks;	
					}else {
						greetings = weeks;							
					}
					*/
					 //greetings = "Selected week is successsful";
			}else if(chooseweek != null) {
					greetings="";
					String sql = "update users set week= " +  chooseweekint + " where  userid=" + userid; 
					
					
					//= "select * from users where username = '" + username + "' and password = '" +  password + "'";
					//System.out.println("greeting chooseweek");
					//System.out.println("sql" + sql);
					//System.out.println("greeting 5");
					//System.out.println("mainservlet ");
					//stmt=con.createStatement(); 
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
					 //request.getSession().setAttribute("week",chooseweekint);
					 greetings = "changed to week " + chooseweek + " is successsful";
			}else if(password != null && !password.equalsIgnoreCase("")) {
				String sql = "select * from users where username = '" + username + "' and password = '" +  password + "'";
				//System.out.println("greeting password");
				//System.out.println("sql" + sql);
				//System.out.println("greeting 5");
				//System.out.println("mainservlet ");
				//stmt=con.createStatement(); 
				//stmt.executeUpdate(sql); 
				ResultSet rspassword = null;
				rspassword = stmt.executeQuery(sql); 
				
				
				//System.out.println("greeting password 1");
				//System.out.println("greeting password sleep");
				/*
				while(rspassword == null) {
			        try {
			          Thread.sleep(1000);
			        } catch(InterruptedException e) {
			        	greetings = "connection failed, try later";
						e.printStackTrace();
			        }
			      }
			      */
				//System.out.println("greeting password 2");
				
				int payedweeks = 0;
				String paytype = "";
				String dateString = "";
				//String phonenumber = "";
				while (rspassword.next()) {
		            //String coffeeName = rspassword.getString(2);
					gradeint = rspassword.getInt("grade");
		            weekint = rspassword.getInt("week");
		            userid = rspassword.getInt("userid");
		            payedweeks = rspassword.getInt("payedweeks");
		            paytype = rspassword.getString("paytype");
		            dateString = rspassword.getString("date");
		            //request.getSession(false).setAttribute("password");
		            //request.getSession().setAttribute("grade",gradeint);
		            //request.getSession().setAttribute("week",weekint);
		            //request.getSession().setAttribute("userid",userid);
		            //request.getSession().setAttribute("password",password);
		            //request.getSession().setAttribute("username",username);
		            //System.out.println("name " + coffeeName );
					greetings = rspassword.getString("grade") + ";"+ rspassword.getString("week") + "/"+ rspassword.getString("userid")+ ",gradeweek";
					
					
					//System.out.println("greetings " + greetings);
					//response.sendRedirect("redirect.html");
					//System.out.println("grade " + grade);
					//forward = 1;

					//System.out.println("greeting password 3" + greetings);
		  		}
			  if(!dateString.equalsIgnoreCase("")) {	
				LocalDate date = LocalDate.parse(dateString);
				LocalDate todayDate = LocalDate.now();
				
				String dateString1 = "2050-08-17";
		        String dateString2 = "2026-06-19";
				
				//System.out.println("mature weeks ");
				sql = "select * from maintenance";
				ResultSet matureweeks = null;
				matureweeks = stmt.executeQuery(sql);
				
								
				if(paytype.equalsIgnoreCase("t")) {
				

				//System.out.println("mature weeks 1");
				while (matureweeks.next()) {
		            //String coffeeName = rspassword.getString(2);
					int tryMtrW = matureweeks.getInt("tryMtrW");
					String phonenumber = matureweeks.getString("phonenumber");
		            //request.getSession(false).setAttribute("password");
		            //request.getSession().setAttribute("grade",gradeint);
		            //request.getSession().setAttribute("week",weekint);
		            //request.getSession().setAttribute("userid",userid);
		            //request.getSession().setAttribute("password",password);
		            //request.getSession().setAttribute("username",username);
		            //System.out.println("name " + coffeeName );
					
					if( tryMtrW <= payedweeks) {
						greetings = "Dear Customers, Your try period has been expired, please call " + phonenumber + " to renew your account";
					}
					
					//greetings = rspassword.getString("grade") + ";"+ rspassword.getString("week") + "/"+ rspassword.getString("userid")+ ",gradeweek";
					
					
					//System.out.println("greetings " + greetings);
					//response.sendRedirect("redirect.html");
					//System.out.println("grade " + grade);
					//forward = 1;

					//System.out.println("greeting password 3" + greetings);
		  		}//while (matureweeks.next())
				}else if(date.isBefore(todayDate)){
					while (matureweeks.next()) {
						String phonenumber = matureweeks.getString("phonenumber");
					greetings = "Dear Customers, Your payed month has been expired, please call " + phonenumber + " to renew your account";
					}
				}
			  }else//if(!dateString.equalsIgnoreCase(""))
			  {
				greetings = "Login failed: Incorrect username or password. Please try again.";
			  }
				
			}else {
				if(username != null && !username.equalsIgnoreCase("") && email != null && !email.equalsIgnoreCase(""))  {
					
					String sql = "select * from users where username = '" + username + "'";
					//System.out.println("greeting 4");
					//stmt=con.createStatement(); 
					//stmt.executeUpdate(sql); 
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
					int rsint = -1;
					if (!rs.next()) 
					{					
					Random r = new Random();
					int passwordint = r.nextInt((999999 - 111111) + 1) + 111111;
					//System.out.println("LocalDate.now().plusDays(14)" + LocalDate.now().plusDays(14));
					String passwordstr = String.valueOf(passwordint);
					/*
					String paytypein = "w";
					String weeks = "";
					if(paytypein.equalsIgnoreCase("y")) {
						weeks = "1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/16/17/18/19/20/21/22/23/24/25/26/27/28/29/30/31/32/33/34/35/36/37/38/39/40/41/42/43/44/45/46/47/48/";
					}else if(paytypein.equalsIgnoreCase("m")) {
						weeks = "1/2/3/4";	
					}else {
						weeks = "1/";							
					}
					*/

					String weeks = "";
					int payedweeks;
					
					if(paytypein.equalsIgnoreCase("y")) {
						weeks = "1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/16/17/18/19/20/21/22/23/24/25/26/27/28/29/30/31/32/33/34/35/36/37/38/39/40/41/42/43/44/45/46/47/48";
						payedweeks = 48;
					}else if(paytypein.equalsIgnoreCase("m")) {
						weeks = "1/2/3/4";
						payedweeks = 4;	
					}else if(paytypein.equalsIgnoreCase("t")){
						weeks = "1/2/3/4";
						payedweeks = 4;
					}else {
						weeks = "1/";
						payedweeks = 1;
					}
					
					
					sql = "insert into users(emailaddress,username,password,grade,week,money,paytype,weeks,date,studyitem,payedweeks) values('" + email + "','" + username + "','" + passwordstr + "'," + gradeint + ",1,0,'" + paytypein + "','" + weeks + "','" + LocalDate.now().plusDays(paydays) + "','grammar/reading/'," + payedweeks + ")";
					//sql = "insert into users(emailaddress,username,password,grade,week,money,paytype,weeks,date,studyitem) values('" + email + "','" + username + "','" + passwordstr + "'," + gradeint + ",1,0,'w','1/2/3/4/6/7/8/9/10/52/','" + LocalDate.now().plusDays(356) + "','reading/')";
					//System.out.println("greeting 4");
					//System.out.println("sql" + sql);
					//System.out.println("greeting 5");
					//System.out.println("mainservlet ");
					//stmt=con.createStatement(); 
					//stmt.executeUpdate(sql); 
					rsint = -1;
					rsint = stmt.executeUpdate(sql); 
					

					//System.out.println("mainservlet ");
					
					//greetings = passwordstr;
					
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
					String eamilsubject = "Your English Tutor Temparory password";
					String eamilcontent = "Dear Customer, Your English Tutor Temparory password " + passwordstr  + " has been sent to you, please use this password to log in and change to your preferred password from customer profile.";
					SendEmail sendEmail = new SendEmail();
					sendEmail.send(email,eamilcontent,eamilsubject);
					greetings = "A temporary password has been sent to your email, please use it to log in and change to your password from customer profile."; 
					
					}else {
						//String paytypein = "w";
						String weeks = "";
					
						if(paytypein.equalsIgnoreCase("y")) {
							weeks = "1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/16/17/18/19/20/21/22/23/24/25/26/27/28/29/30/31/32/33/34/35/36/37/38/39/40/41/42/43/44/45/46/47/48/49/50";
						}else if(paytypein.equalsIgnoreCase("m")) {
							weeks = "1/2/3/4";	
						}else if(paytypein.equalsIgnoreCase("t")){
							weeks = "1/2/3/4";							
						}else
							weeks = "1/";
						
						sql = "update users set paytype='" + paytypein + "', weeks = '" + weeks + "', date = '" + LocalDate.now().plusDays(365)  + "' where userid =" + userid;
						//System.out.println("greeting 4");
						//System.out.println("sql" + sql);
						//System.out.println("greeting 5");
						//System.out.println("mainservlet ");
						//stmt=con.createStatement(); 
						//stmt.executeUpdate(sql);  
						rsint = -1;
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
						greetings = "Updating pay type and learning weeks has completed."; 
					}
					
						if(rsint > 0)
						{
							sql = "select * from users where username = '" + username + "'";
							//System.out.println("greeting 4");
							//stmt=con.createStatement(); 
							//stmt.executeUpdate(sql);  
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
							//System.out.println("greeting 5");
							int useridint = 0;
							while (rs.next()) {
					            //String coffeeName = rs.getString(2);
								useridint = rs.getInt("userid");
								String weeksin = rs.getString("weeks");
								String[] weeksarray = weeksin.split("/");
								for(int l = 0; weeksarray.length > l; l++) {
								int weeksint = Integer.parseInt(weeksarray[l]);
								String sqlin = "select * from questionanswer where grade = " + gradeint + " and week = " + weeksint;
								//System.out.println("sqlin" + sqlin);
								Statement stmtin = con.createStatement();
								//stmt=con.createStatement();
								ResultSet rsin = null;
								rsin = stmtin.executeQuery(sqlin);
								/*
								while(rsin == null) {
							        try {
							          Thread.sleep(1000);
							        } catch(InterruptedException e) {
							        	greetings = "connection failed, try later";
										e.printStackTrace();
							        }
							      }
								*/
								//System.out.println("greeting 6");
								int quecount = 0;
								while (rsin.next()) {
									quecount = rsin.getInt("quecount");
								}
								//System.out.println("greeting 7");
								String anscount="";
								for(int i = 0;i < quecount; i++) {
									anscount = anscount + "0,0/";
								}

								//System.out.println("greeting 8");
								
								String sqlansct = "insert into questionanswercnt(userid,grade,week,anscount) values(" + useridint + "," + gradeint + "," + weeksint + ",'" + anscount + "')";
								Statement stmtansct = con.createStatement();
								rsint = stmtansct.executeUpdate(sqlansct);	
								//System.out.println("greeting 9");
								
								}//for(int l = 0; weeksarray.length > l; l++)
							}//while (rs.next())
						//}
					
					
					}
					else
					{
						greetings = "username has been used. Please choose another one";
					}//if(rsint > 0)
						
			      }else {
				
				     greetings = "Username and email address cannot be blank. Please enter a valid username and email address.";
			      }//if(username != null && username.equalsIgnoreCase("") && email != null && email.equalsIgnoreCase("")) 

				
				}
					
		  		}
				catch(SQLException ex){
	            //logger.error("Cannot close connection");
					//System.out.println("SQLException");
					//System.out.println("greeting 6");
					if(ex.getMessage().contains("users.unique_index"))
					{
						greetings = "Emailaddress and Grade have been used, please choose another Emailaddress";
				    }
					else
					   greetings = "SQLException " + ex.getMessage();
					
					
					
					//System.out.println("greeting 61" + greetings);
					ex.printStackTrace();
					result = "connection failed, try later";
				}
				catch (Exception e) 
				{

					//System.out.println("greeting 62");
					greetings = "SQLException" + e.getMessage();
					//System.out.println("greeting 62" + greetings);
				e.printStackTrace();
				}
		  /*
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
		  */
		  finally {
			    if (con != null) { 
			        try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
			    }
			}
		  //
		  /*
		 if(forward == 1)
		 {
			 System.out.println("speechtextchrome.html 1");
			 System.out.println("request.getContextPath() is " + request.getContextPath());
			 response.sendRedirect(request.getContextPath() + "/speechtextchrome.html?grade=" + grade + "&week=" + week);
			 //ServletContext sc = getServletContext();
	            //sc.getRequestDispatcher("/speechtextchrome.html?grade=" + grade + "&week=" + week).forward(request, response);
			 System.out.println("speechtextchrome.html 2");
			 //window.location.href = url;
		 }
		 else
		 {
		 //*/
			 //System.out.println("xajax");
		  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		  response.setHeader("Expires", "0"); // Proxies.
			 response.setContentType("text/plain");
			 response.getWriter().write(greetings);
		 //			 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
