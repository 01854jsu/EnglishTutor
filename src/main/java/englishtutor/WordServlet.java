package englishtutor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import java.util.Properties;
import java.io.InputStream;


public class WordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("WordServlet greeting 1");
		
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
    	
    	
		
		
		String grade = "0";
		
			if(request.getParameter("grade")!= null)
				grade = request.getParameter("grade");
			/*
			else {
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
			  //grade = request.getParameter("grade");
		/*
			if(request.getSession(false).getAttribute("grade") != null)
		      grade =String.valueOf((int) request.getSession(false).getAttribute("grade"));
			else
			  grade = request.getParameter("grade");
			  */
			 
		  int gradeint = 0;
		  if(grade != null) {
			  grade = grade.trim();
		  	  gradeint = Integer.parseInt(grade);
		  }
		  
		  String week = "0";
		  
		  if(request.getParameter("week")!= null)
				week = request.getParameter("week");
		  /*
			else {
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
		  /*
			if(request.getSession(false).getAttribute("week") != null)
			      week =String.valueOf((int) request.getSession(false).getAttribute("week"));
			else
				  week = request.getParameter("week");			
			*/
		  int weekint = 0;
		  //String week =String.valueOf((int) request.getSession(false).getAttribute("week"));
		  //String week = request.getParameter("week").trim();
		  if(week != null) {
		  week = week.trim();
	  	  weekint = Integer.parseInt(week);
		  }
		  //int weekint = Integer.parseInt(week);
		  //String contents = request.getParameter("contents").trim();
		  String contents = request.getParameter("contents");
		  if(contents != null) 
			  contents = contents.trim();
		  String interrogwrd = request.getParameter("interrogwrd");
		  if(interrogwrd != null) 
			  interrogwrd = interrogwrd.trim();
		  String interroglist = request.getParameter("interroglist");
		  if(interroglist != null) 
			  interroglist = interroglist.trim();
		  String ignrdwrd = request.getParameter("ignrdwrd");
		  if(ignrdwrd != null) 
			  ignrdwrd = ignrdwrd.trim();
		  String ignrdwrdlist = request.getParameter("ignrdwrdlist");
		  if(ignrdwrdlist != null) 
			  ignrdwrdlist = ignrdwrdlist.trim();
		  String display = request.getParameter("display");
		  if(display != null) 
			  display = display.trim();
		  else
			  display = "0";
		  String meaning = request.getParameter("meaning");
		  int meaningint = 0;
		  if(meaning != null) {
			  meaning = meaning.trim();
			  meaningint = Integer.parseInt(meaning);
		  }
		  
		  String interrog = request.getParameter("interrog");
		  if(interrog != null) 
			  interrog = interrog.trim();
		  
		  String word = request.getParameter("word");
		  if(word != null) 
			  word = word.trim();
		  

		  String highlightedword = request.getParameter("highlightedword");
		  if(highlightedword != null) 
			  highlightedword = highlightedword.trim();
		  
		  System.out.println("highlightedword " + highlightedword);
		  
		  /*
		  System.out.println("display " + display);
		  System.out.println("word " + word);

		  System.out.println("meaning " + meaning);
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
			  ////System.out.println("greeting 3");
			    //Class.forName("com.mysql.cj.jdbc.Driver"); 
			    //Class.forName("com.mysql.jdbc.GoogleDriver");
			    //Class.forName("com.mysql.jdbc.Driver");
				//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/englishtutor?user=root&password=Jsu01854");  
				//con = DriverManager.getConnection("jdbc:mysql://35.192.176.11:3306/englishtutor?user=root&password=Jsu01854");  
				//con = DriverManager.getConnection("jdbc:google:mysql://35.192.176.11:3306/englishtutor?user=root&password=Jsu01854"); 
			  //Class.forName("com.mysql.jdbc.Driver");
              
	            //System.setProperty("javax.net.ssl.trustStore", TRUST_STORE_FILE_PATH);
	            //System.setProperty("javax.net.ssl.trustStorePassword", TRUST_STORE_PASS);
	            
	            /*
	              Properties properties = new Properties();
	             
	            properties.setProperty("sslMode", "VERIFY_IDENTITY");
	            properties.put("user", "root");
	            properties.put("password", "Jsu01854");
			  */
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
				String sql = "";
				String sqlword = "";
				String sqlwordmean = "";
				stmt=con.createStatement();
				Statement stmtword=con.createStatement();
				Statement stmtwordmean=con.createStatement();

				//System.out.println("interrogwrd in ");
				if(interrogwrd != null) {
					//sql = "select * from words where grade = " + gradeint + " and week = " + weekint + " and word = '" + word + "' and interrogative = '" + interrog + "'";
					sql = "select * from interrogative";
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
					String interrolist = "";
					//if(!rs.next()) {
					//if(rs.) {
						
						while (rs.next()) {
			            //String coffeeName = rs.getString(2);
			            //System.out.println("name " + coffeeName );
							interrolist = rs.getString("interrogative");
						//System.out.println("interrolist " + interrolist);
						}
					if(interrolist.equalsIgnoreCase("")) {
							//System.out.println("insert ");
							//String sqlins = "insert into interrogative (interrogative) values ('" +  contents + ",') where id =1";
							String sqlins = "insert into interrogative (interrogative) values ('" +  contents + ",')";
							//stmt.executeUpdate(sqlins);
							
							int rsint = -1;
							rsint = stmt.executeUpdate(sqlins);
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
							greetings = "insert is successful";
					} else {
						//System.out.println("update ");
						if(!interrolist.matches(contents.toLowerCase())) {
							//System.out.println("interrolist " + interrolist);
							interrolist = interrolist + contents.toLowerCase() + ",";
							//System.out.println("interrolist " + interrolist);
							String sqlins = "update interrogative set interrogative='" +  interrolist + "' where id =1";
							//stmt.executeUpdate(sqlins); 
							
							int rsint = -1;
							rsint = stmt.executeUpdate(sqlins);
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
							greetings = "update is successful";
						}else
							greetings = "Duplicated. Word is already in ignored word list";
					
					}
                }else if(highlightedword != null) {
						//sql = "select * from words where grade = " + gradeint + " and week = " + weekint + " and word = '" + word + "' and interrogative = '" + interrog + "'";
						sql = "select * from wordexplain where grade = " + gradeint + " and week = " + weekint;
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
						String wordexplanation = "";
						//if(!rs.next()) {
						//if(rs.) {
							
							while (rs.next()) {
				            //String coffeeName = rs.getString(2);
				            //System.out.println("name " + coffeeName );
								wordexplanation = rs.getString("content");
							//System.out.println("interrolist " + interrolist);
							}
													
							
                            int wordhiliet = 10;
							
							String[] wordhilietindx = {};
							//System.out.println("wordhilietindx 0");
							List<String> wordhilietList = new ArrayList<>(Arrays.asList(wordhilietindx));
							//System.out.println("wordhilietindx 1");
					        // Add the new element dynamically
					        
							
							for (int j = 2; j < wordhiliet+1; j++)
							{
								String newElement =  "" + j + ".";
								//System.out.println("newElement " + newElement);
								wordhilietList.add(newElement);
							}
							
							wordhilietindx = wordhilietList.toArray(new String[0]);
							
							String a = wordhilietindx[0];
							
							//System.out.println("wordhilietindx " + a);
							
String sqlstr = "";
							
							int startindes = 0;
							
							for(int i=0; i < wordhilietindx.length + 1 ; i++)
							{
								if(i == 0)
								{

									int wmindex = highlightedword.indexOf(wordhilietindx[i]);
									System.out.println("wmindex " + wmindex);
									startindes = wmindex;
									String wordmeaning = highlightedword.substring(3, wmindex);
									System.out.println("wordmeaning " + wordmeaning);
									int wordleft = wordmeaning.indexOf("(");
									String wordself = wordmeaning.substring(0, wordleft-1);
									System.out.println("wordself " + wordself);
									int wordright = wordmeaning.indexOf(")");
									String wordtype = wordmeaning.substring(0, wordright+1);
									System.out.println("wordtype " + wordtype);
									int wmmean = wordmeaning.indexOf("Synonym:");
									String wmmeanstr = wordmeaning.substring(wordright+1, wmmean);
									System.out.println("wmmeanstr1 " + wmmeanstr);
									wmmeanstr = wordself + " " + wmmeanstr;		
									System.out.println("wmmeanstr2 " + wmmeanstr);				

									int wmsyn = wordmeaning.indexOf("Example:");
									String wmsynstr = wordmeaning.substring(wmmean, wmsyn);
									System.out.println("wmsynstr " + wmsynstr);
									
									

									//int wmsyn = wordmeaning.indexOf("Synonym:");
									String wmexastr = wordmeaning.substring(wmsyn);
									System.out.println("wmexastr " + wmexastr);
									
									String finalstr = wmmeanstr + ", " + wmsynstr + ", " + wmexastr;

									System.out.println("finalstr " + finalstr);
									
									String firstr = "<p>word explanation: <span class='tooltip'>" + wordtype + "<span class='tooltip-text'>" + finalstr + "</span></span>,";

									System.out.println("firstr " + firstr);
									
									sqlstr = firstr;
									
								}else if(i == wordhilietindx.length)
								{

									int wmindex = highlightedword.indexOf(wordhilietindx[i-1]);
									System.out.println("wmindex " + wmindex);
									String wordmeaning = highlightedword.substring(wmindex + 4);
									System.out.println("wordmeaning " + wordmeaning);
									int wordleft = wordmeaning.indexOf("(");
									String wordself = wordmeaning.substring(0, wordleft-1);
									System.out.println("wordself " + wordself);
									int wordright = wordmeaning.indexOf(")");
									String wordtype = wordmeaning.substring(0, wordright+1);
									System.out.println("wordtype " + wordtype);
									int wmmean = wordmeaning.indexOf("Synonym:");
									String wmmeanstr = wordmeaning.substring(wordright+1, wmmean);
									System.out.println("wmmeanstr1 " + wmmeanstr);
									wmmeanstr = wordself + " " + wmmeanstr;		
									System.out.println("wmmeanstr2 " + wmmeanstr);				

									int wmsyn = wordmeaning.indexOf("Example:");
									String wmsynstr = wordmeaning.substring(wmmean, wmsyn);
									System.out.println("wmsynstr " + wmsynstr);
									
									

									//int wmsyn = wordmeaning.indexOf("Synonym:");
									String wmexastr = wordmeaning.substring(wmsyn);
									System.out.println("wmexastr " + wmexastr);
									
									String finalstr = wmmeanstr + ", " + wmsynstr + ", " + wmexastr;

									System.out.println("finalstr " + finalstr);
									
									String laststr = "<span class='tooltip'>" + wordtype + "<span class='tooltip-text'>" + finalstr + "</span></span>!";

									System.out.println("laststr " + laststr);
									
									sqlstr = sqlstr + laststr;
								}else {

									int wmindex = highlightedword.indexOf(wordhilietindx[i]);
									System.out.println("wmindex " + wmindex);
									String wordmeaning = highlightedword.substring(startindes + 3, wmindex);
									startindes = wmindex;
									System.out.println("wordmeaning " + wordmeaning);
									int wordleft = wordmeaning.indexOf("(");
									String wordself = wordmeaning.substring(0, wordleft-1);
									System.out.println("wordself " + wordself);
									int wordright = wordmeaning.indexOf(")");
									String wordtype = wordmeaning.substring(0, wordright+1);
									System.out.println("wordtype " + wordtype);
									int wmmean = wordmeaning.indexOf("Synonym:");
									String wmmeanstr = wordmeaning.substring(wordright+1, wmmean);
									System.out.println("wmmeanstr1 " + wmmeanstr);
									wmmeanstr = wordself + " " + wmmeanstr;		
									System.out.println("wmmeanstr2 " + wmmeanstr);				

									int wmsyn = wordmeaning.indexOf("Example:");
									String wmsynstr = wordmeaning.substring(wmmean, wmsyn);
									System.out.println("wmsynstr " + wmsynstr);
									
									

									//int wmsyn = wordmeaning.indexOf("Synonym:");
									String wmexastr = wordmeaning.substring(wmsyn);
									System.out.println("wmexastr " + wmexastr);
									
									String finalstr = wmmeanstr + ", " + wmsynstr + ", " + wmexastr;

									System.out.println("finalstr " + finalstr);
									
									String midstr = "<span class='tooltip'>" + wordtype + "<span class='tooltip-text'>" + finalstr + "</span></span>,";

									System.out.println("midstr " + midstr);
									
									sqlstr = sqlstr + midstr;
								}
								
							}
							
							sqlstr = sqlstr.replace("'", "''");
							
						if(wordexplanation.equalsIgnoreCase("")) {
								//System.out.println("insert ");
							
							//int wordhiliet = 10;
							//String[] wordhilietindx = {"2.","3.","4.","5.","6.","7.","8.","9.","10."};
							
													
							
							
							
							System.out.println("sqlstr " + sqlstr);
								//String sqlins = "insert into wordexplain (wordexplain) values ('" +  contents + ",') where id =1";
							String sqlins = "INSERT INTO englishtutor.wordexplain (grade, week, content) VALUES (" + gradeint + ", " +  weekint + " , '" + sqlstr + "')";
							//stmt.executeUpdate(sqlins);
								
								int rsint = -1;
								rsint = stmt.executeUpdate(sqlins);
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
								greetings = "insert is successful";
						} else {
							//System.out.println("update ");
							//System.out.println("wordexplanation " + wordexplanation);
								String sqlins = "UPDATE englishtutor.wordexplain SET content = '" + sqlstr + "' where grade = " + gradeint + " and week = " + weekint;
                                 //stmt.executeUpdate(sqlins); 
								
								int rsint = -1;
								rsint = stmt.executeUpdate(sqlins);
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
								greetings = "update is successful";
								
						
						}						
					}else if(interroglist != null) {
					sql = "select * from interrogative";
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
						greetings = rs.getString("interrogative");
						//System.out.println("greeting " + greetings);
					}
				}else if(ignrdwrd != null) {
					//sql = "select * from words where grade = " + gradeint + " and week = " + weekint + " and word = '" + word + "' and interrogative = '" + interrog + "'";
					sql = "select * from ignoredwords";
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

					String ignrlist = "";
					while (rs.next()) {
			            //String coffeeName = rs.getString(2);
			            //System.out.println("name " + coffeeName );
						ignrlist = rs.getString("word");
						//System.out.println("ignrlist " + ignrlist);
					}
					if(ignrlist.equalsIgnoreCase("")) {
						//System.out.println("insert ");
						String sqlins = "insert into ignoredwords (word) values ('" +  contents + ",')";
						//stmt.executeUpdate(sqlins);

						int rsint = -1;
						rsint = stmt.executeUpdate(sqlins);
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
						greetings = "insert is successful";
					} else {
						if(!ignrlist.matches(contents.toLowerCase())) {
							ignrlist = ignrlist + contents.toLowerCase() + ",";
							String sqlins = "update ignoredwords set word='" +  ignrlist + "' where id =1";
							//stmt.executeUpdate(sqlins); 							

							int rsint = -1;
							rsint = stmt.executeUpdate(sqlins);
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
							greetings = "update is successful";
						}else
							greetings = "Duplicated. Word is already in ignored word list";
					
					
					}
				}else if(ignrdwrdlist != null) {
					sql = "select * from ignoredwords";
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
						greetings = rs.getString("word");
						//System.out.println("greeting " + greetings);
					}
				}else if(interrog != null && display.equalsIgnoreCase("1")) {
					sql = "select * from words where grade = " + gradeint + " and week = " + weekint + " and word = '" + word + "' and interrogative = '" + interrog + "'";
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
						greetings = rs.getString("meaning" + meaning);
						//System.out.println("greeting " + greetings);
					}
				}else if(interrog != null) {
					
					sql = "select * from words where grade = " + gradeint + " and week = " + weekint + " and word = '" + word + "' and interrogative = '" + interrog+ "'";
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

					/*
					if (!rs.next()) {
						if(meaningint ==1) {
							sqlword = "insert into words (word,grade, week,meaning1,meaning2,meaning3,meaning4,interrogative) values ('" + word + "', " + gradeint + "," + weekint + ",'" + contents + "','','','','" + interrog + "')";
							sqlwordmean = "insert into wordmeaning (word,grade, week,meaning1,meaning2,meaning3,meaning4,interrogative) values ('" + word + "', " + gradeint + "," + weekint + ",'" + contents + "','','','','" + interrog + "')";
						}else if(meaningint ==2) {
								sqlword = "insert into words (word,grade, week,meaning1,meaning2,meaning3,meaning4,interrogative) values ('" + word + "', " + gradeint + "," + weekint + ",'" + contents + "','','','','" + interrog + "')";
								sqlwordmean = "insert into wordmeaning (word,grade, week,meaning1,meaning2,meaning3,meaning4,interrogative) values ('" + word + "', " + gradeint + "," + weekint + ",'','" + contents + "','','','" + interrog + "')";
						}else if(meaningint ==3) {
							sqlword = "insert into words (word,grade, week,meaning1,meaning2,meaning3,meaning4,interrogative) values ('" + word + "', " + gradeint + "," + weekint + ",'" + contents + "','','','','" + interrog + "')";
							sqlwordmean = "insert into wordmeaning (word,grade, week,meaning1,meaning2,meaning3,meaning4,interrogative) values ('" + word + "', " + gradeint + "," + weekint + ",'','','" + contents + "','','" + interrog + "')";
						}else if(meaningint ==4) {
							sqlword = "insert into words (word,grade, week,meaning1,meaning2,meaning3,meaning4,interrogative) values ('" + word + "', " + gradeint + "," + weekint + ",'','','','" + contents + "','" + interrog + "')";
							sqlwordmean = "insert into wordmeaning (word,grade, week,meaning1,meaning2,meaning3,meaning4,interrogative) values ('" + word + "', " + gradeint + "," + weekint + ",'" + contents + "','','','','" + interrog + "')";
						}
						greetings = "insert is successful";
					}else {
						sqlword = "update words set meaning" + meaning +"  = '" +  contents + "' where grade = " + gradeint + " and week = " + weekint + " and interrogative = '" + interrog + "' and word = '" + word + "'";  
						sqlwordmean = "update wordmeaning set meaning" + meaning +"  = '" +  contents + "' where grade = " + gradeint + " and week = " + weekint + " and interrogative = '" + interrog + "' and word = '" + word + "'";  
						greetings = "update is successful";
					}
					stmtword.executeUpdate(sqlword); 
					stmtwordmean.executeUpdate(sqlwordmean);
					*/
					PreparedStatement prepped = con.prepareStatement("insert into words (word,grade, week,meaning1,meaning2,meaning3,meaning4,interrogative) values (?,?,?,?,?,?,?,?)");
					PreparedStatement preppedmean = con.prepareStatement("insert into wordmeaning (word,grade, week,meaning1,meaning2,meaning3,meaning4,interrogative) values (?,?,?,?,?,?,?,?)");
					PreparedStatement preppedup = con.prepareStatement("update words set meaning" + meaning +"  =? where grade =? and week =? and interrogative =? and word =?");
					PreparedStatement preppedmeanup = con.prepareStatement("update wordmeaning set meaning" + meaning +"  =? where grade =? and week =? and interrogative =? and word =?");
					//System.out.println("prepped " + prepped);
					if (!rs.next()) {
						if(meaningint ==1) {
							prepped.setString(1, word);
prepped.setInt(2, gradeint);
prepped.setInt(3, weekint);
prepped.setString(4, contents);
prepped.setString(5, "");
prepped.setString(6, "");
prepped.setString(7, "");
prepped.setString(8, interrog);

preppedmean.setString(1, word);
preppedmean.setInt(2, gradeint);
preppedmean.setInt(3, weekint);
preppedmean.setString(4, contents);
preppedmean.setString(5, "");
preppedmean.setString(6, "");
preppedmean.setString(7, "");
preppedmean.setString(8, interrog);
}else if(meaningint ==2) {
								prepped.setString(1, word);
prepped.setInt(2, gradeint);
prepped.setInt(3, weekint);
prepped.setString(5, contents);
prepped.setString(4, "");
prepped.setString(6, "");
prepped.setString(7, "");
prepped.setString(8, interrog);

preppedmean.setString(1, word);
preppedmean.setInt(2, gradeint);
preppedmean.setInt(3, weekint);
preppedmean.setString(5, contents);
preppedmean.setString(4, "");
preppedmean.setString(6, "");
preppedmean.setString(7, "");
preppedmean.setString(8, interrog);
}else if(meaningint ==3) {
							prepped.setString(1, word);
prepped.setInt(2, gradeint);
prepped.setInt(3, weekint);
prepped.setString(6, contents);
prepped.setString(5, "");
prepped.setString(4, "");
prepped.setString(7, "");
prepped.setString(8, interrog);

preppedmean.setString(1, word);
preppedmean.setInt(2, gradeint);
preppedmean.setInt(3, weekint);
preppedmean.setString(6, contents);
preppedmean.setString(5, "");
preppedmean.setString(4, "");
preppedmean.setString(7, "");
preppedmean.setString(8, interrog);
}else if(meaningint ==4) {
							prepped.setString(1, word);
prepped.setInt(2, gradeint);
prepped.setInt(3, weekint);
prepped.setString(7, contents);
prepped.setString(5, "");
prepped.setString(6, "");
prepped.setString(4, "");
prepped.setString(8, interrog);

preppedmean.setString(1, word);
preppedmean.setInt(2, gradeint);
preppedmean.setInt(3, weekint);
preppedmean.setString(7, contents);
preppedmean.setString(5, "");
preppedmean.setString(6, "");
preppedmean.setString(4, "");
preppedmean.setString(8, interrog);
}
						//prepped.executeUpdate();
						

						int rsint = -1;
						rsint = prepped.executeUpdate();
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
						//preppedmean.executeUpdate();
						

						int rsint2 = -1;
						rsint2 = preppedmean.executeUpdate();
						 /*
						 while(rsint2 < 0) {
						        try {
						          Thread.sleep(1000);
						        } catch(InterruptedException e) {
						        	greetings = "connection failed, try later";
									e.printStackTrace();
						        }
						      }
						 */
						greetings = "insert is successful";
					}else {
						preppedup.setString(1, contents);
						preppedup.setInt(2, gradeint);
						preppedup.setInt(3, weekint);
						preppedup.setString(4, interrog);
						preppedup.setString(5, word);

						preppedmeanup.setString(1, contents);
						preppedmeanup.setInt(2, gradeint);
						preppedmeanup.setInt(3, weekint);
						preppedmeanup.setString(4, interrog);
						preppedmeanup.setString(5, word);
						//preppedup.executeUpdate();
						//preppedmeanup.executeUpdate();
						
						int rsint = -1;
						rsint = preppedup.executeUpdate();
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
						//preppedmean.executeUpdate();
						

						int rsint2 = -1;
						rsint2 = preppedmeanup.executeUpdate();
						 /*
						 while(rsint2 < 0) {
						        try {
						          Thread.sleep(1000);
						        } catch(InterruptedException e) {
						        	greetings = "connection failed, try later";
									e.printStackTrace();
						        }
						      }
						     */
						greetings = "update is successful";
					}
					
					//stmtbooks.executeUpdate(sqlupd); 
				}else {	
				sql = "select * from interrogative";
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
					//sqlupd = "insert into books (grade, week,content) values (" + gradeint + ", " + weekint + ",'" +  contents + "')";
					//greetings = greetings + rs.getString("interrogative") + "/";
					greetings = rs.getString("interrogative");
					//System.out.println("greetings interog in " + greetings);
				}
				//greetings = greetings + "/";
					//System.out.println("greeting 4");
				//System.out.println("greetings" + greetings);
				//System.out.println("greeting 5");
				
				//stmtbooks.executeUpdate(sqlupd); 
				
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
