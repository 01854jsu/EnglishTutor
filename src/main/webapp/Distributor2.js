var queryString = window.location.search;
var urlParams = new URLSearchParams(queryString);
//alert("urlParams " + urlParams);
//var grade = urlParams.get('grade');
//var week = urlParams.get('week');
//var userid = urlParams.get('userid');
var inparam = urlParams.get('in');
var grade;
var week;
var userid;


function getURLParameter(name) {
	return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]);
}
function hideURLParams() {
	//Parameters to hide (ie ?success=value, ?error=value, etc)
	var hide = ['success','error'];
	for(var h in hide) {
		if(getURLParameter(h)) {
			history.replaceState(null, document.getElementsByTagName("title")[0].innerHTML, window.location.pathname);
		}
	}
	
	var inarray = [];
	inarray = inparam.split(",");
	week = inarray[0];
	userid = inarray[2];
	grade = inarray[3];
			
	getstudyitem();
}

window.onload = hideURLParams;

function parsein(){
	
	
	
	
}

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


/*
window.onload = function(){
	//alert("getstudyitem");
	getstudyitem();
	};
	*/
	
function grammer() {
	 			//top.location.href = "EnglishGrammar.html?userid=" + userid + "&grade=" + grade + "&week=" + week;
	    		top.location.href = "EnglishGrammar.html?in=" + week + "," + "3064" + "," + userid + "," + grade + "," + "0074";
}

function reading() {	 
	    		top.location.href = "EnglishReading.html?userid=" + userid + "&grade=" + grade + "&week=" + week;
	    		top.location.href = "EnglishReading.html?in=" + week + "," + "3064" + "," + userid + "," + grade + "," + "0074";
}

function writing() {
	    		//top.location.href = "EnglishWriting.html?userid=" + userid + "&grade=" + grade + "&week=" + week;	 
	    		top.location.href = "EnglishWriting.html?in=" + week + "," + "3064" + "," + userid + "," + grade + "," + "0074";
}

function changepw(){
	//alert("changepw in");	
	document.getElementById("pwchangeddiv").style.display = "block";
	document.getElementById("changepw").style.display = "none";	
}


///*
function pwchanged(){
var xhttp = new XMLHttpRequest();
  var oldpw = document.getElementById("oldpw").value;
  var newpw = document.getElementById("newpw").value;
  var renewpw = document.getElementById("renewpw").value;
  //var newpwstr = new String(newpw);
 // var renewpwstr = new String(renewpw);
 // /*
 var n = newpw.localeCompare(renewpw);
 //alert("n " + n);
 
 if(n != 0){
	  alert("the new password does not match the retype new password");
	  return;
 }
 else
 {
  var url="main.jsp?oldpw=" + oldpw + "&newpw=" + newpw + "&renewpw=" + renewpw + "&userid=" + userid;
  //alert("url " + url);
  ///*
  
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {	
    	alert(this.responseText);
    	document.getElementById("changepwdiv").style.display = "block";
    	document.getElementById("pwchangeddiv").style.display = "none";	
    	//var str = this.responseText;
    	/*
    	var n = str.match("different");
    	if(n != null){
    		alert("str " + str);    	
    	}else{
    		alert("str " + str);
    		break;
    	
    	}
    	*/
    	//
		top.location.href = "main.html";
    	
    }
  };
  
  xhttp.open("GET", url, true);
	 xhttp.send();

 }//if(n != 0)
	
}

	

function cusprofi() {
	 var xhttp = new XMLHttpRequest();
		 
	  var url="main.jsp?cusprofi=1&userid=" + userid;
	  //var url="";
	  //alert(url);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var profi = this.responseText;
			//document.getElementById("readsubmit").style.display = "none";
			//document.getElementById("writsubmit").style.display = "none";
			//document.getElementById("gramsubmit").style.display = "none";
		    
		     var profiarray = [];
		     profiarray = profi.split(";");
		     //alert("profiarray.length " + profiarray.length);
		     //document.getElementById("moreanswer").innerHTML = meaningarray;
		     var i;
		     for(i = 0; i < profiarray.length ; i++) {
						
				if(i == 0)	{	
					document.getElementById("paytypediv").innerHTML = "paytype: " + profiarray[i];
					document.getElementById("paytypediv").style.display = "block";
				}
				else if(i == 1)	{	
					document.getElementById("weeksdiv").innerHTML = "learning weeks: " + profiarray[i];
					document.getElementById("weeksdiv").style.display = "block";
				}
				else if(i == 2)	{	
					document.getElementById("datediv").innerHTML = "expired date: " + profiarray[i];
					document.getElementById("datediv").style.display = "block";
				}
				else if(i == 3)	{	
					document.getElementById("itemdiv").innerHTML = "study item: " + profiarray[i];
					document.getElementById("itemdiv").style.display = "block";
				}
				else if(i == 4)	{	
					document.getElementById("coindiv").innerHTML = "earn coin: " + profiarray[i];
					document.getElementById("coindiv").style.display = "block";
				}
				
				/*
				<div id="paytypediv" style="display: none;"></div>
 <div id="weeksdiv" style="display: none;"></div>
 <div id="datediv" style="display: none;"></div>
 <div id="itemdiv" style="display: none;"></div>
 <div id="coindiv" style="display: none;"></div>
				document.getElementById("gradeweek").innerHTML = "paytype: " + profiarray[i];
				document.getElementById("gradeweek").innerHTML = "Current Grade " + grade + ", Week " + week;
				document.getElementById("gradeweek").innerHTML = "Current Grade " + grade + ", Week " + week;
				document.getElementById("gradeweek").innerHTML = "Current Grade " + grade + ", Week " + week;
				document.getElementById("gradeweek").innerHTML = "Current Grade " + grade + ", Week " + week;
				
				
				
				
				paytype + "/" + weeks + "/" + date + "/" + studyitemin + "/" + String.valueOf(money) + "/";
				*/
			//alert("voice 5 " + itemarray[i]);
	    //var option = document.createElement('option');
	    //option.textContent = itemarray[i].name + ' (' + itemarray[i].lang + ')';
	    //option.textContent = itemarray[i];
	    //alert("itemarray[i] " + itemarray[i]);
	    /*
	    if(itemarray[i].default) {
	      option.textContent += ' -- DEFAULT';
	    }
		*/
		//alert("voice 6");
	    //option.setAttribute('data-lang', itemarray[i].lang);
	    //option.setAttribute('data-name', itemarray[i].name);
	    //option.setAttribute('value', i);

		//alert("voice 7");
	    //chooseweekSelectElement.appendChild(option);
	    
	    //const utterance = new SpeechSynthesisUtterance();
	    //utterance.voice = weeksarray[4];  
	    //populateVoice = weeksarray[4]; 
	   //return weeksarray[4];

		//alert("voice 8");
	  }//for(i = 0;
		
					document.getElementById("changepwdiv").style.display = "block";
					document.getElementById("cusprofi").style.display = "none";
	    }
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
}


function getstudyitem() {
	 //alert("getstudyitem in");
	 var xhttp = new XMLHttpRequest();
	  var url="main.jsp?studyitem=1&userid=" + userid;
	  //var url="";
	  //alert(url);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var item = this.responseText;
			//document.getElementById("readsubmit").style.display = "none";
			//document.getElementById("writsubmit").style.display = "none";
			//document.getElementById("gramsubmit").style.display = "none";
		    
		     var itemarray = [];
		     itemarray = item.split("/");
		     //alert("itemarray.length " + itemarray.length);
		     //document.getElementById("moreanswer").innerHTML = meaningarray;
		     var i;
		     for(i = 0; i < itemarray.length ; i++) {
						
				if(itemarray[i] == "grammar")	{					
			  			document.getElementById("GrammerDiv").style.display = "block";
				}
				else if(itemarray[i] == "writing"){				
			  			document.getElementById("WriteDiv").style.display = "block";
				}
				else if(itemarray[i] == "reading"){					
			  			document.getElementById("ReadDiv").style.display = "block";
				}
				

			//alert("voice 5 " + itemarray[i]);
	    //var option = document.createElement('option');
	    //option.textContent = itemarray[i].name + ' (' + itemarray[i].lang + ')';
	    //option.textContent = itemarray[i];
	    //alert("itemarray[i] " + itemarray[i]);
	    /*
	    if(itemarray[i].default) {
	      option.textContent += ' -- DEFAULT';
	    }
		*/
		//alert("voice 6");
	    //option.setAttribute('data-lang', itemarray[i].lang);
	    //option.setAttribute('data-name', itemarray[i].name);
	    //option.setAttribute('value', i);

		//alert("voice 7");
	    //chooseweekSelectElement.appendChild(option);
	    
	    //const utterance = new SpeechSynthesisUtterance();
	    //utterance.voice = weeksarray[4];  
	    //populateVoice = weeksarray[4]; 
	   //return weeksarray[4];

		//alert("voice 8");
	  }//for(i = 0;
	    }
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
}