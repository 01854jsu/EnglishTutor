/*
window.onbeforeunload = function(){

var inputs = document.getElementsByTagName("INPUT");
        for (var i in inputs) {
            if (inputs[i].type == "button" || inputs[i].type == "submit") {
                inputs[i].disabled = true;
            }
        }

var buttons = document.getElementsByTagName("BUTTON");
        for (var i in buttons) {
            if (buttons[i].type == "button" || buttons[i].type == "submit") {
                buttons[i].disabled = true;
            }
        }
};
*/





function login() {
	  var xhttp = new XMLHttpRequest();
	  var username = document.getElementById("un").value;
	  var password = document.getElementById("pw").value;
	  //alert("login ");
	  //var url="studycontent.jsp";
	  //var str = grades + "&weeks=" + weeks + "&contents=" + contents;
	  //var url="SetBooks.jsp?grades=" + str;
	  var url="main.jsp?username=" + username + "&password=" + password;
	  //var url="SetBooks.jsp?grades=" + grades;
	  //var url="";
	  //alert("url " + url);
	  
	  
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {	
	    	//alert("onreadystatechange ");
	    	var str = this.responseText;	    	
	    	//alert("str " + str);
	    	if(str.match(/gradeweek/i) != null)
	    	{	
		    	//alert("onreadystatechange 3" + str);
	    		//var n = str.search(":");
	    		//alert("onreadystatechange 4");
	    		//var m = str.search(",");
	    		//alert("onreadystatechange 5");
	    		var l = str.search(";");
	    		//alert("l " + l);
	    		var o = str.search("/");
	    		//alert("o " + o);
	    		var p = str.search(",");
	    		//alert("p " + p);
	    		//alert("onreadystatechange 7");
	    		var grade = str.substr(0, l);
	    		//alert("onreadystatechange 6");
	    		var week = str.substr(l+1,o-l-1);
	    		//alert("grade " + grade);
	    		//alert("week " + week);
	    		
	    		var userid = str.substr(o+1,p-o-1);
	    		//alert("userid " + userid);
	    		//url = "speechtextchrome.html?grade=" + grade + "&week=" + week;
	    		//alert("url12 " + url);
	    		//windows.location.href='/speechtextchrome.jsp';
	    		//top.location.href ='speechtextchrome.jsp';
	    		//top.location.href = "EnglishReading.html?grade=" + grade + "&week=" + week + "&userid=" + userid;
	    		//top.location.href = "EnglishReading.html";
				//alert("forward ");
	    		top.location.href = "Distributor.html?in=" + week + "," + "973" + "," + userid + "," + grade + "," + "07564";
				//top.location.href = "javascript:postwith('Distributor.html',{userid: userid, grade: grade, week: week})";
				//alert("top.location.href is " + top.location.href);
				//forward(userid,grade,week);
				//openWindowWithPost("worklist.jsp",'height=300px,width=300px,top=200px,left=500px,scrollbars=no,sizable=yes,toolbar=no,statusbar=no','title',param);
	    		//alert("url1 " + url);
				//alert("forward after");
	    	}  	
	    	else
	    	{	    		
	    		alert(str);
	    	} 
	    	
	    }
	  };
	  
	  xhttp.open("GET", url, true);
		 xhttp.send();
	}
	
	/*
	function postwith (to,p) {
	alert("to " + to);	
	alert("p " + p);
  var myForm = document.createElement("form");
  myForm.method="post" ;
  myForm.action = to ;
  for (var k in p) {
    var myInput = document.createElement("input") ;
    myInput.setAttribute("name", k) ;
    myInput.setAttribute("value", p[k]);
    myForm.appendChild(myInput) ;
  }
  document.body.appendChild(myForm) ;
  myForm.submit() ;
  document.body.removeChild(myForm) ;
alert("to after " + to);
}
	
	function forward(userid,grade,week){
		alert("userid " + userid);
		alert("grade " + grade);
		alert("week " + week);
		/*
		$.post("Distributor.html", {grade: grade,
			week: week}, function(result){
    $("span").html(result);
  });
	$.ajax({
    url : "Distributor.html",
    type: "POST",
    data: {
            userid: userid,
			grade: grade,
			week: week
          },
    success: function(data, textStatus, jqXHR)
    {
        //data - response from server
		alert("SUCCESS");
    },
    error: function (jqXHR, textStatus, errorThrown)
    {
 		alert("FAILURE");
    }
});
alert("forward(userid,grade,week");

}
	*/
	
	function user(){
		document.getElementById("createuser").style.display = "block";
		document.getElementById("usersubmit").style.display = "none";
		
		
	}
	
function createuser(){
	var xhttp = new XMLHttpRequest();
	  var username = document.getElementById("unin").value;
	  var email = document.getElementById("eain").value;
	  //var grade = document.getElementById("grade").value;

 var e = document.getElementById("grades");

	 //alert("chooseweek in 1");
	  var grade = e.options[e.selectedIndex].text;
	  //alert("contents " + contents);
	  var url="main.jsp?username=" + username + "&email=" + email + "&grade=" + grade;
	  //alert("url " + url);
	  
	  
	  xhttp.onreadystatechange = function() {
	    //if (this.readyState == 4 && this.status == 200) {	
			//alert("here");		
	    if (this.readyState == 4) {	
	    	//alert("onreadystatechange ");
	    	//alert("here1");
	    	var str = this.responseText;	    	
	    	
	    	if(this.status == 200){
				//alert("here1-2");
				if(str === "username has been used. Please choose another one"){
					document.getElementById("createuser").style.display = "block";	
					//alert("block");			
	    		}else if(str === "Emailaddress and Grade have been used, please choose another Emailaddress"){
					document.getElementById("createuser").style.display = "block";	
					//alert("block");			
	    		}else{
					document.getElementById("createuser").style.display = "none";	
					//("none");			
	    		}
				//alert(str);
	    	}
	    	else{
				document.getElementById("createuser").style.display = "block";
				//alert("here2");
            } 
	    	
	    	alert(str);
	    }
	  };
	  
	  xhttp.open("GET", url, true);
		 xhttp.send();
		
	}
	
	
function forgotpw(){
	document.getElementById("resetpw").style.display = "block";
	document.getElementById("forgotpwsubmit").style.display = "none";
	
}

function resetpw(){
var xhttp = new XMLHttpRequest();
  var email = document.getElementById("earp").value;

  var username = document.getElementById("unrp").value;
  //alert("username " + username);
  var url="main.jsp?email=" + email + "&username=" + username + "&resetpw=1";
  //alert("url " + url);
  
  
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {	
    	//alert("onreadystatechange ");
    	var str = this.responseText;	    	
    	//alert("str " + str);
    	/*
    	if(str.match(/gradeweek/i) != null)
    	{	
	    	//alert("onreadystatechange 3" + str);
    		//var n = str.search(":");
    		//alert("onreadystatechange 4");
    		//var m = str.search(",");
    		//alert("onreadystatechange 5");
    		var l = str.search(";");
    		//alert("l " + l);
    		var o = str.search("/");
    		//alert("o " + o);
    		var p = str.search(",");
    		//alert("p " + p);
    		//alert("onreadystatechange 7");
    		var grade = str.substr(0, l);
    		//alert("onreadystatechange 6");
    		var week = str.substr(2,1);
    		//alert("grade " + grade);
    		//alert("week " + week);
    		
    		var userid = str.substr(4,1);
    		//alert("userid " + userid);
    		//url = "speechtextchrome.html?grade=" + grade + "&week=" + week;
    		//alert("url12 " + url);
    		//windows.location.href='/speechtextchrome.jsp';
    		//top.location.href ='speechtextchrome.jsp';
    		document.getElementById("createuser").style.display = "none";
    		top.location.href = "speechtextchrome.html?grade=" + grade + "&week=" + week + "&userid=" + userid;
    		//alert("url1 " + url);
    	}  	
    	else
    	{
    		*/
    		document.getElementById("resetpw").style.display = "none";
    		//document.getElementById("forgotpwsubmit").style.display = "block";
    		//document.getElementById("forgotpwsubmit").style.display.left = "50%";
    		alert(str);
    	//} 
    	
    }
  };
  
  xhttp.open("GET", url, true);
	 xhttp.send();
	
}

function forgotun(){
	document.getElementById("getun").style.display = "block";
	document.getElementById("forgotunsubmit").style.display = "none";
	
}

function getun(){
var xhttp = new XMLHttpRequest();
  var email = document.getElementById("eagurp").value;

  //var grade = document.getElementById("gdrp").value;
   var gdrp = document.getElementById("gdrp");

	 //alert("chooseweek in 1");
	 var grade = gdrp.options[gdrp.selectedIndex].text;
  
  
  //alert("username " + username);
  var url="main.jsp?email=" + email + "&grade=" + grade + "&getun=1";
  //alert("url " + url);
  
  
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {	
    	//alert("onreadystatechange ");
    	var str = this.responseText;	    	
    	//alert("str " + str);
    	/*
    	if(str.match(/gradeweek/i) != null)
    	{	
	    	//alert("onreadystatechange 3" + str);
    		//var n = str.search(":");
    		//alert("onreadystatechange 4");
    		//var m = str.search(",");
    		//alert("onreadystatechange 5");
    		var l = str.search(";");
    		//alert("l " + l);
    		var o = str.search("/");
    		//alert("o " + o);
    		var p = str.search(",");
    		//alert("p " + p);
    		//alert("onreadystatechange 7");
    		var grade = str.substr(0, l);
    		//alert("onreadystatechange 6");
    		var week = str.substr(2,1);
    		//alert("grade " + grade);
    		//alert("week " + week);
    		
    		var userid = str.substr(4,1);
    		//alert("userid " + userid);
    		//url = "speechtextchrome.html?grade=" + grade + "&week=" + week;
    		//alert("url12 " + url);
    		//windows.location.href='/speechtextchrome.jsp';
    		//top.location.href ='speechtextchrome.jsp';
    		document.getElementById("createuser").style.display = "none";
    		top.location.href = "speechtextchrome.html?grade=" + grade + "&week=" + week + "&userid=" + userid;
    		//alert("url1 " + url);
    	}  	
    	else
    	{
    		*/
    		document.getElementById("getun").style.display = "none";
    		//document.getElementById("forgotunsubmit").style.display = "block";
    		//document.getElementById("forgotunsubmit").style.display.left = "50%";
    		alert(str);
    	//} 
    	
    }
  };
  
  xhttp.open("GET", url, true);
	 xhttp.send();
	
}



function menu(){	
	document.getElementById("menudiv").style.display = "block";	
	//document.getElementById("menusmtdiv").style.display = "none";	
}