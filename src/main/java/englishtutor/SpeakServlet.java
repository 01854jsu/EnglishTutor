package englishtutor;

import java.io.IOException;
import java.sql.DriverManager;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.util.Properties;
import java.io.InputStream;


public class SpeakServlet  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "null", "null", "null", "null", "null", "null" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//*
		
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
		
		Connection con = null;
		  String greetings = "";
			String result = "F";
			String[] rsarray = null;
			//String[] intergarray = new String[1];
			List<String> intergarray = new ArrayList<String>();
			List<String> meaningarray1 = new ArrayList<String>();
			List<String> meaningarray2 = new ArrayList<String>();
			List<String> finalwords = new ArrayList<String>();
			List<String> compoundword = new ArrayList<String>();//put all compound word in a arraylist compoundword
			//String[] meaningarray1 = new String[1];  
			//String[] meaningarray2 = new String[1];  
			
		
			
		  String text = request.getParameter("text").trim();
		  String textin = text;
		  String textcompound = text;//textcompound is used for add compound word to compoundword arraylist
		  
		  
		  //System.out.println("text before is " + text);
		  ///*
		 /*
				while(request.getSession(false).getAttribute("grade") == null) {
			        try {
			          Thread.sleep(1000);
			        } catch(InterruptedException e) {
			        	//greetings = "connection failed, try later";
						e.printStackTrace();
			        }
			      }
			      */
				//grade =String.valueOf((int) request.getSession(false).getAttribute("grade"));
		  String grade = "";
			if(request.getParameter("grade") != null)
				grade = request.getParameter("grade");	
			int gradeint = 0;
		    if(grade != null) {
				  grade = grade.trim();
			  	  gradeint = Integer.parseInt(grade);
			   }
			
		    String week = "";
			if(request.getParameter("week") != null)
				week = request.getParameter("week");
			int weekint = 0;
		    if(week != null) {
				  week = week.trim();
			  	  weekint = Integer.parseInt(week);
			   }
		    
		    String userid = "";
			if(request.getParameter("userid") != null)
				userid = request.getParameter("userid");
			
			int useridint = -1;
		    if(userid != null && !userid.equalsIgnoreCase("")) {
				  userid = userid.trim();
			  	  useridint = Integer.parseInt(userid);
			   }
			
		  //String grade =String.valueOf((int) request.getSession(false).getAttribute("grade")).trim();
		  
/*
			while(request.getSession(false).getAttribute("week") == null) {
		        try {
		          Thread.sleep(1000);
		        } catch(InterruptedException e) {
		        	//greetings = "connection failed, try later";
					e.printStackTrace();
		        }
		      }
			*/
		  //String week =String.valueOf((int) request.getSession(false).getAttribute("week")).trim();
		  
/*
			while(request.getSession(false).getAttribute("userid") == null) {
		        try {
		          Thread.sleep(1000);
		        } catch(InterruptedException e) {
		        	//greetings = "connection failed, try later";
					e.printStackTrace();
		        }
		      }
		      */
		  //int userid = (int) request.getSession(false).getAttribute("userid");
		  //String grade = request.getParameter("grade");
		  //System.out.println("grade before is " + grade);
		  //String week = request.getParameter("week");
		  //System.out.println("week before is " + week);
		  //*/
		  //String[] removewords = {"it","this","is","you","he","an","that","i","are","does","a","do","did"};
		  
		  //String[] compoundword;
		  //String[] removewords1 = {"it ","this ","is ","you ","he ","an ","that ","i ","are ","does ","a ","do ","did "};
		  int wccount = 0;
		  while(textcompound != "" && textcompound.indexOf('"') > 0) {

			  //System.out.println("textcompound length is " + textcompound.length());
			  int indexstart = textcompound.indexOf('"');
			  //System.out.println("indexstart is " + indexstart);
			  String tempstr = textcompound.substring(indexstart+1);
			  //System.out.println("tempstr is " + tempstr);
			  int indexend = tempstr.indexOf('"');
			  //int indexend = textcompound.substring(indexstart+1).indexOf("'");
			  //System.out.println("textcompound.substring(indexstart+indexend+1) is " + textcompound.substring(indexstart+indexend+2));
			  //System.out.println("textcompound.substring(0, indexstart-1) is " + textcompound.substring(0, indexstart-1));
			  compoundword.add(tempstr.substring(0,indexend));
			  //textcompound = textcompound.substring(indexstart+indexend+2);
			  //System.out.println("textcompound.substring(0, indexstart-1) is " + textcompound.substring(0, indexstart-1));
			  //System.out.println("compoundword.add is " + tempstr.substring(0,indexend));
			  textcompound = textcompound.substring(0, indexstart-1) + textcompound.substring(indexstart+indexend+2);
			  //System.out.println("textcompound is " + textcompound);
			  wccount++;		  
		  }
		  text = textcompound;//text 
		  //System.out.println("textcompound text is " + text);
		  
		  String[] textarray = text.split(" ");
		  /*
		  for(int i = 0; compoundword.size() > i; i++)
		  {
			  textarray[textarray.length + i + 1] = compoundword.get(i);
		  }
		  ///*
		  for(int i = 0; textarray.length > i; i++) 
		  {
			  System.out.println("textarray is " + textarray[i]);
		  }
		  */
		  //*/
		  try {
			  ResultSet rs1 = null;
				Statement stmt1 = null;
				String sql1 = "";

				ResultSet rs2 = null;
				Statement stmt2 = null;
				String sql2 = "";
				
				ResultSet rsiw = null;
				Statement stmtiw = null;
				String sqliw = "";
				
				//String[] intergword = new String[1];
				//List<String> intergword = new ArrayList<String>();

				//List<String> ignword = new ArrayList<String>();

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
		  //String[] removewords = {"it","this","is","you","he","an","that","i","are","does","a","do","did","she","go","him","her","to","want"};
		  sqliw = "select * from ignoredwords";
			//System.out.println("sqliw " + sqliw);
			stmtiw=con.createStatement(); 
			//rsiw = stmtiw.executeQuery(sqliw);
			
			rsiw = null;
			rsiw = stmtiw.executeQuery(sqliw);
			/*
			while(rsiw == null) {
		        try {
		          Thread.sleep(1000);
		        } catch(InterruptedException e) {
		        	greetings = "connection failed, try later";
					e.printStackTrace();
		        }
		      }
			*/
			//int intinterg = 0;
			String ignw = "";
			while (rsiw.next()) {
	            //String coffeeName = rs.getString(2);
	            //System.out.println("name " + coffeeName );
				ignw = rsiw.getString("word");
				//ignword.add(ignw);
			}
		  
			String[] ignwarray = ignw.split(",");
		  
		  //		  /*
		  List<String> list = Arrays.asList(textarray);
		  List<String> newList=new ArrayList<>(list);
		  for(int i = 0; compoundword.size() > i; i++)
		  {
			  newList.add(compoundword.get(i));
		  }
		  //System.out.println("newList size is " + newList.size());
		  ///*
		  //int hh = list.size();
		  //		  /*
		  for(int i = newList.size() - 1; i >= 0; --i) 
		  {
			  //for(int j = 0; removewords.length > j; j++) {
			  //for(int j = 0; ignword.size() > j; j++) {
			  for(int j = 0; ignwarray.length > j; j++) {
				  //System.out.println("i is " + i);  
				  //System.out.println("newList.get(i)" + newList.get(i));  
				  //System.out.println("removewords[j] is " + removewords[j]);  
		          //if(newList.get(i).contains(removewords[j]))   
			      //if(newList.get(i).equalsIgnoreCase(removewords[j]))
			      //if(newList.get(i).equalsIgnoreCase(ignword.get(j)))
				  if(newList.get(i).equalsIgnoreCase(ignwarray[j]))
		          {
		        	  //System.out.println("ignwarray[j] " + ignwarray[j]); 
		        	  newList.remove(i);
		          }
		  	  }
		  }
		  //		  */
		  /*
		  for(int i = list.size() - 1; i >= 0; --i) 
		  {
			  for(int j = 0; removewords.length > j; j++) {
		          if(list.get(i).contains(removewords[j]))
		          {
		        	  list.remove(i);
		          }
		  	  }
		  }
	*/
		//int hh1 = list.size();
		  /*
	String[] strArray = new String[list.size()];
	strArray = list.toArray(strArray);
	*/
		  /*
	String[] words = new String[list.size()];
	words = list.toArray(words);
	*/

		  //System.out.println("newList22222222 size is " + newList.size());
	String[] words = new String[newList.size()];
	words = newList.toArray(words);
	
	/*
	for(int i=0; i < words.length; i++)
	  {
		  //String text1 = removeWord(text, removewords[i]);
		  System.out.println("words9999999999999999999 is " + words[i]);
	  }
		//
		 */  
	//String[] words = 
		  
		 /* 
		  
		  
		  for(int i=0; i < removewords.length; i++)
		  {
			  String text1 = removeWord(text, removewords[i]);
			  //System.out.println("text1 is " + text1);
			  text = text1;
			  //System.out.println("text in is " + text);
		  }
		  /*
		  String[] removewords = {" it"," this"," is"," you"," he"," an"," that"," i"," are"," does"," a"," do "," did "};
		  for(int i=0; i < removewords.length; i++)
		  {
			  String text1 = removeWord(text, removewords[i]);
			  //System.out.println("text1 is " + text1);
			  text = text1;
			  //System.out.println("text in is " + text);
		  }
		 */

		  //system.out.println("text after is " + text);
		  
		  /*
		  String[] words = text.split("\\s+");
		  for (int i = 0; i < words.length; i++) {
		      // You may want to check for a non-word character before blindly
		      // performing a replacement
		      // It may also be necessary to adjust the character class
		      words[i] = words[i].replaceAll("[^\\w]", "");
			  System.out.println("words" + i + " is " + words[i]);
		  }
		  
		  */
	//System.out.println("words is " + words[0] + "//" + words[1]);
		  //System.out.println("text before2 is " + text);
		  //try {
			/*
				ResultSet rs1 = null;
				Statement stmt1 = null;
				String sql1 = "";

				ResultSet rs2 = null;
				Statement stmt2 = null;
				String sql2 = "";
				
				//String[] intergword = new String[1];
				List<String> intergword = new ArrayList<String>();
				
		  Class.forName("com.mysql.cj.jdbc.Driver"); 
		    //Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/texttospeech?user=root&password=Jsu01854"); 
			*/
			//String sql = "select * from customer";
			//String sql = "select * from customer where customer_id = " + Integer.parseInt(userName.trim());
			sql1 = "select * from interrogative";
			//System.out.println("sql1 " + sql1);
			stmt1=con.createStatement(); 
			//rs1 = stmt1.executeQuery(sql1);
			
			rs1 = null;
			rs1 = stmt1.executeQuery(sql1);
			/*
			while(rs1 == null) {
		        try {
		          Thread.sleep(1000);
		        } catch(InterruptedException e) {
		        	greetings = "connection failed, try later";
					e.printStackTrace();
		        }
		      }
			*/
			//int intinterg = 0;
			String interg = "";
			while (rs1.next()) {
	            //String coffeeName = rs.getString(2);
	            //System.out.println("name " + coffeeName );
				interg = rs1.getString("interrogative");
				//String[] interglist = interg.split(",");
				/*
				String interg = rs1.getString("interrogative");
				intergword.add(interg);
				//System.out.println("sql11");
				
			  for (int i = 0; i < words.length; i++) {
				  //String interg = rs1.getString("interrogative");
					//System.out.println("sql12 " + interg);
					if(words[i].equalsIgnoreCase(interg)) {
				//greetings = rs.getString("meaning1");
					//System.out.println("sql13 " + interg);
				  intergarray.add(interg);
				//System.out.println("interg " + intergarray.get(i));
					}
			  }
			  */
			  //intinterg++;
			}
			
			
			String[] interglist = interg.split(",");
			
			///*
			for (int i = 0; i < words.length; i++) {
				  //String interg = rs1.getString("interrogative");
					//System.out.println("sql12 " + interg);
				for(int j = 0; j < interglist.length; j++) {
					if(words[i].equalsIgnoreCase(interglist[j])) {
				//greetings = rs.getString("meaning1");
					//System.out.println("interglist[j] " + interglist[j]);
				  intergarray.add(interglist[j]);
				//System.out.println("interg " + intergarray.get(i));
					}
				}
			}
			//*/
			/*
			for(int i=0; i < intergarray.length; i++)
			  {
				  String text1 = removeWord(text, intergarray[i]);
				  System.out.println("text1 is " + text1);
				  text = text1;
				  System.out.println("text in is " + text);
			  }
			*/
			
			
			//int k = 0;
			for (int n = 0; n < words.length; n++) {
				//System.out.println("n " + n);
				boolean breakflag = false;
				//String intergstr ="";

				for (int l = 0; l < interglist.length; l++) {
					//System.out.println("k 1");
					if(interglist[l].equalsIgnoreCase(words[n])) {
						//System.out.println("interglist[l]" + interglist[l]);
						//intergstr = interglist[l];
						breakflag = true;
						break;
					}
					//if(breakflag)
						//break;
				}
				//
				/*
				for (int l = 0; l < intergword.size(); l++) {
					//System.out.println("k 1");
					if(intergword.get(l).equalsIgnoreCase(words[i])) {
						//System.out.println("k 2");
						breakflag = true;
						break;
					}
					//if(breakflag)
						//break;
				}
				*/
				
				//System.out.println("k 3");
				if(breakflag)
					continue;
				//stmt2.close();
				
				for(int o = 0; intergarray.size() > o; o++)
				{
				//System.out.println("words[n].toLowerCase()" + words[n].toLowerCase());
				//sql2 = "select * from words where word= '" + words[i].toLowerCase() + "'";
				sql2 = "select * from words where word= '" + words[n].toLowerCase() + "' and grade=" + grade + " and week=" + week + " and interrogative='" + intergarray.get(o) + "'";
				//finalwords.add(words[i].toLowerCase());
				//System.out.println("sql2 " + sql2);
				stmt2=con.createStatement(); 
				//rs2 = stmt2.executeQuery(sql2); 
				
				rs2 = null;
				rs2 = stmt2.executeQuery(sql2); 
				/*
				while(rs2 == null) {
			        try {
			          Thread.sleep(1000);
			        } catch(InterruptedException e) {
			        	greetings = "connection failed, try later";
						e.printStackTrace();
			        }
			      }
				*/
				//System.out.println("gg words[i] " + words[i]);
				//*
				if(rs2.next() == false) {
					//System.out.println("words[i] " + words[i]);
					greetings = greetings + words[n] + ":this is a good question on " + words[n] + " and we will answer you next time.;";
					//System.out.println("greetings words[i] " + greetings);
					//ResultSet rsuaw = null;
					Statement stmtuaw = con.createStatement(); ;
					//String sqluaw = "";
					String question = words[n] + ":" + textin;
					//System.out.println("question " + question);
					
					String sqluaw = "insert into unansques(userid, grade, week, question, answer, ansflag, verified) values(" + userid + "," + grade + "," + week + ",'" + question + "','','N','N')";
					//System.out.println("sqluaw " + sqluaw);
					//stmtuaw.executeUpdate(sqluaw);
					
					int rsint = -1;
					rsint = stmtuaw.executeUpdate(sqluaw);
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
					continue;	
				}else {
				//*/
					finalwords.add(words[n].toLowerCase());
					//System.out.println("gg words[i] 1");
				 do {
						//System.out.println("gg words[i] 2");
			            //String coffeeName = rs.getString(2);
			            //System.out.println("name " + coffeeName );
						//System.out.println("sql21 intergarray.length " + intergarray.size());
					  for (int j = 0; j < intergarray.size(); j++) {
							//System.out.println("gg words[i] 3");
							//System.out.println("intergarray.get(j) " + intergarray.get(j));
							//System.out.println("rs2.getString  " + rs2.getString("interrogative").toLowerCase());
							//if(intergarray.get(j).equalsIgnoreCase(rs2.getString("interrogative").toLowerCase())) {
							if(intergarray.get(j).equalsIgnoreCase(rs2.getString("interrogative").toLowerCase())) {
						//greetings = rs.getString("meaning1");
							//System.out.println("sql23");
						  meaningarray1.add(rs2.getString("meaning1"));
						  meaningarray2.add(rs2.getString("meaning2"));
					      //meaningarray2[n] = rs2.getString("meaning2");
						//System.out.println("meaningarray1[n] " + meaningarray1.get(n));
						//System.out.println("meaningarray2[n] " + meaningarray2.get(n));
						//
						  //n++;
							}
					  }
				 	}while (rs2.next());//while (rs2.next())	
				}//if(!rs2.next())
				
				}//for(int o = 0; intergarray.size() > o; o++)
				//rs2.close();
			  }
			 //for (int j = 0; j < intergarray.size(); j++) {
				 int meaningarraylength = meaningarray1.size();
				 /*
				 for(int i=0; i < finalwords.size(); i++)
				  {
					  //String text1 = removeWord(text, removewords[i]);
					  System.out.println("finalwords is " + finalwords.get(i));
				  }
				  */
				 //System.out.println("meaningarraylength " + meaningarraylength);
				 //String greetings = "";
			for (int m = 0; m < meaningarraylength; m++) {
				String greetings1 = "";
				//System.out.println("meaningarray1.get(m) " + meaningarray1.get(m));
				if(meaningarray2.get(m) != null && !"".equals(meaningarray2.get(m))){
					greetings1 = finalwords.get(m) + ":" + meaningarray1.get(m) + "/" + meaningarray2.get(m);
					//System.out.println("greetings " + greetings1);
				} else {
					greetings1 = finalwords.get(m) + ":" + meaningarray1.get(m);
					//System.out.println("greetings1 " + greetings1);
				}
				
				if(meaningarraylength > 1 && (meaningarraylength-m)>1 ) {
				//System.out.println("meaningarray11 " + greetings);
				String greeting2 = greetings;
				greetings = greeting2 + greetings1 + ";";

				//greetings = greetings + greetings1 + "|";
				//System.out.println("meaningarray12 " + greetings);
				}
				else
				{
					String greeting2 = greetings;
					greetings = greeting2 + greetings1;
				}
			  //}//for (int m = 0; m < meaningarraylength; m++) 
			

			//System.out.println("greeting " + greetings);
		  }//for (n = 0; n < words.length; n++) {
			
			 //}//j < intergarray.size()
			
			/*
			
			
			sql = "select * from words";
			
			System.out.println("greeting 4");
			System.out.println("sql" + sql);
			System.out.println("greeting 5");
			stmt=con.createStatement(); 
			rs = stmt.executeQuery(sql);
			*/ 
			/*
			while (rs.next()) {
	            //String coffeeName = rs.getString(2);
	            //System.out.println("name " + coffeeName );
				greetings = rs.getString("content");
				System.out.println("greeting " + greetings);
			}
			*/
		  
		 // for (int i = 0; i < words.length; i++) {
			  
			
			  
			  
		  //}
		  
		  //greetings = "";
		 
		 /* 		
		if(userName.equalsIgnoreCase("1") || "".equals(userName)){
			greetings = "Hello this is a test";
		} else {
			greetings = "I like you very much";
		}
		//
		 */

			//System.out.println("greeting 1 " + greetings); 
		if("".equals(greetings))
			greetings = "this is a good question and we will answer you next time. ";
		
		
		
		  }catch(SQLException ex){
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
	
	public static String removeWord(String string, String word) 
    { 
  
        // Check if the word is present in string 
        // If found, remove it using removeAll() 
		
		
        if (string.contains(word)) { 
  
            // To cover the case 
            // if the word is at the 
            // beginning of the string 
            // or anywhere in the middle 
            String tempWord = word + " "; 
            string = string.replaceAll(tempWord, ""); 
  
            // To cover the edge case 
            // if the word is at the 
            // end of the string 
            tempWord = " " + word; 
            string = string.replaceAll(tempWord, ""); 
        } 
  
        // Return the resultant string 
        return string; 
    } 

}
