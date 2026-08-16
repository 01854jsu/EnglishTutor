var speaktext;
var speaktext1;
var studycontent;

var quesexpcontentcnt;
var quesexpcontentcntmax;
var queryString = window.location.search;
var urlParams = new URLSearchParams(queryString);
//alert("urlParams " + urlParams);

var inparam = urlParams.get('in');
var grade;
var week;
var userid;

//var grade = urlParams.get('grade');
//var week = urlParams.get('week');
//var userid = urlParams.get('userid');
//alert("grade " + grade);
//alert("week " + week);
var teacheranswerwhole;
var wordarray;
var meaningarray;
//var loadinfocount=1;
//var meaningarrayin;

var teacherexplain = [];
var teacherquestion = [];
var questionans = [];
var answercount = new Array(8);
//var anscorrect = new Array(8);
var currentTX;
var currentTXmax;
var currentQS = -1;
var currentQSmax;
var currentQX;
var currentQXmax;
var questionon = 0;
var Qarray=new Array(8);
var QA = 0;
var readinganswer = 0;
var answeron = 0;
var queexpflag = 0;
//var synth = window.speechSynthesis;
//var synthans = window.speechSynthesis;
var synth;
var synthans;
var speakon = 0;
var speakonans = 0;
var voices = [];
var populateVoice;
var voiceSelectElement;
var utterance;
var utteranceans;
var voiceSelect;
var hltvalue;
var hlelement;
var hltvalueans;
var hlelementans;
var ansflag = 0;//flag for into answer question
var indextemp = 0;
var eventtemp;
var utterancetemp;
var ansflagres = 0;
var onewordflag = 0;//filter word only one word need explain
var questionexplainflag =0;

var chooseweek;

var voicerate = 1;

window.onbeforeunload = function(){
	synth.cancel();
	speakon = 0;
	  //return 'Are you sure you want to leave?';
/*
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
*/
	};


	
function getURLParameter(name) {
	return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]);
}
function hideURLParams() {
	//Parameters to hide (ie ?success=value, ?error=value, etc)
	//alert("Englishreading");
	
	var hide = ['success','error'];
	for(var h in hide) {
		if(getURLParameter(h)) {
			history.replaceState(null, document.getElementsByTagName("title")[0].innerHTML, window.location.pathname);
		}
	}
	//alert("Englishreading 1");
	var inarray = [];
	
	//alert("inparam is " + inparam);
	if(inparam != null){
	inarray = inparam.split(",");
	//alert("Englishreading 2");
	week = inarray[0];
	//alert("Englishreading 3");
	userid = inarray[2];
	grade = inarray[3];		
	}
	else{
		week = getCookie("weekcook");
		userid = getCookie("useridcook");
		grade = getCookie("gradecook")		
	}	
	
	//alert("Englishreading 4");
	setCookie("weekcook", week);
	//alert("Englishreading 5");
	setCookie("useridcook", userid);
	setCookie("gradecook", grade);
	
	//alert("week is " + week);
	//alert("userid is " + userid);
	//alert("grade is " + grade);
	
	
	unanswerques();
	displayweek();	
	synth = window.speechSynthesis;
	synthans = window.speechSynthesis;
	//resolve web page in browser first time, synth.pause() does not work.
	var mt = new SpeechSynthesisUtterance();
	mt.text = " ";
     //window.speechSynthesis.speak(mt);
	synth.speak(mt);
	var mtans = new SpeechSynthesisUtterance();
	mtans.text = " ";
	synthans.speak(mtans);
		speakon = 0;
		document.getElementById("explainQXcont").style.display = 'none';
		document.getElementById("TcherExpln1form").style.display = 'none';
}

window.onload = hideURLParams;

/*	
window.onload = function(){
	unanswerques();
	displayweek();	
	synth = window.speechSynthesis;
	synthans = window.speechSynthesis;
	//resolve web page in browser first time, synth.pause() does not work.
	var mt = new SpeechSynthesisUtterance();
	mt.text = " ";
     //window.speechSynthesis.speak(mt);
	synth.speak(mt);
	var mtans = new SpeechSynthesisUtterance();
	mtans.text = " ";
	synthans.speak(mtans);
		speakon = 0;
		  //return 'Are you sure you want to leave?';
		};
*/
//const utterance = new SpeechSynthesisUtterance();
//
/*
var x = document.getElementById("BooksQuestion");

x.style.display ="none";
//
*/
//
/*
$(document).ready(function() {
    $(".hideme").hide();
});
//
*/
/*
if (x.style.display === "none") {
  x.style.display = "block";
} else {
  x.style.display = "none";
}
*/

function setCookie(cname, cvalue) {
	//alert("Englishreading 41");
  document.cookie = cname + "=" + cvalue;
}



function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(';');
  for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function removeunansques() {
	 var xhttp = new XMLHttpRequest();
	 //chooseweekSelectElement = document.getElementById("chooseweekSelect");
	 //chooseweekSelectElement.style.display = 'block';
	  //document.getElementById("chooseweekSelect").style.display = 'none';
	 
	  var url="main.jsp?removeunansques=1&userid=" + userid + "&grade=" + grade + "&week=" + week;
	  //var url="";
	  //alert(url);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	if(this.responseText != "" && this.responseText != null)
	    		alert(this.responseText);
	    }
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
}

function unanswerques() {
	 var xhttp = new XMLHttpRequest();
	 //chooseweekSelectElement = document.getElementById("chooseweekSelect");
	 //chooseweekSelectElement.style.display = 'block';
	  //document.getElementById("chooseweekSelect").style.display = 'none';
	 
	  var url="main.jsp?unanswerques=1&userid=" + userid + "&grade=" + grade + "&week=" + week;
	  //var url="";
	  //alert(url);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	if(this.responseText != "" && this.responseText != null){
	    		
	    		var text = this.responseText;
	    		var c = text.search("/");
			 	//alert("c " + c);
			 	var header = text.substring(0, c);
			 	//alert("header " + header);
			 	var answer = text.substring(c+1);
			 	//alert("answer " + answer);
	    		if(answer != null && answer != ""){
	    			alert(header + answer);
	    			removeunansques();
	    		}
	    	}
	    		//alert(this.responseText);
	    }
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
}


function displayweek() {
	 var xhttp = new XMLHttpRequest();
	 chooseweekSelectElement = document.getElementById("chooseweekSelect");
	 chooseweekSelectElement.style.display = 'block';
	  //document.getElementById("chooseweekSelect").style.display = 'none';
	 
	  var url="main.jsp?displayweek=1&userid=" + userid + "&grade=" + grade + "&week=" + week;
	  //var url="";
	  //alert(url);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var weeks = this.responseText;
	    	
	    	//alert("weeks " + weeks);
		     //wordarray = teacheranswerwhole.split(";");
		     var c = weeks.search(";");
		 	//alert("c " + c);
		 	var gradeweek = weeks.substring(0, c);
		 	
		 	var d = gradeweek.search("/");
		 	//alert("c " + c);
		 	var grade = gradeweek.substring(0, d);
		 	var week = gradeweek.substring(d+1);
		 	
		 	document.getElementById("gradeweek").innerHTML = "Current Grade " + grade + ", Week " + week;
		 	
		 	//alert("header " + header);
		 	var weekstemp = weeks.substring(c+1);
	    	
	    	weeks = weekstemp;
	    	
	    	//alert("weeks 1 " + weeks);
		     //alert("wordarray " + wordarray[0]);
		     var weeksarray = [];
		     weeksarray = weeks.split("/");
		     //alert("weeksarray.length " + weeksarray.length);
		     //document.getElementById("moreanswer").innerHTML = meaningarray;
		     var i;
		     for(i = 0; i < weeksarray.length ; i++) {

			//alert("voice 5 " + weeksarray[i]);
	    var option = document.createElement('option');
	    //option.textContent = weeksarray[i].name + ' (' + weeksarray[i].lang + ')';
	    option.textContent = weeksarray[i];
	    //alert("weeksarray[i] " + weeksarray[i]);
	    /*
	    if(weeksarray[i].default) {
	      option.textContent += ' -- DEFAULT';
	    }
		*/
		//alert("voice 6");
	    //option.setAttribute('data-lang', weeksarray[i].lang);
	    //option.setAttribute('data-name', weeksarray[i].name);
	    option.setAttribute('value', i);

		//alert("voice 7");
	    chooseweekSelectElement.appendChild(option);
	    
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

///*
function chooseweek1() {
	 //alert("chooseweek 0");
	 var xhttp = new XMLHttpRequest();
	 //alert("chooseweek in");
	 var e = document.getElementById("chooseweekSelect");

	 //alert("chooseweek in 1");
	  chooseweek = e.options[e.selectedIndex].text;
		 //alert("chooseweek in 2");
	  //var url="studycontent.jsp";
	  //var url="speak.jsp?text=" + textvalue;
	  var url="main.jsp?chooseweek=" + chooseweek + "&userid=" + userid;
	  //var url="";
	  //alert(url);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	alert(this.responseText);
	    	document.getElementById("StartPR").style.display = 'block';
	    	document.getElementById("StartPE").style.display = 'none';
	    	document.getElementById("TcherExplnform").style.display = 'block';
	    	document.getElementById("TcherExpln1form").style.display = 'none';
	    	document.getElementById("demo").innerHTML = "";
	    	document.getElementById("wordexp").innerHTML = "";
	    	document.getElementById("explain").innerHTML = "";
	    	document.getElementById("copyspeech").innerHTML = "";
	    	document.getElementById("answer").innerHTML = "";
	    	document.getElementById("moreanswer").innerHTML = "";
	    	document.getElementById("moreword").innerHTML = "";
	    	document.getElementById("questionset").innerHTML = "";
	    	questionon = 0;
	    	QA = 0;
	    	readinganswer = 0;
	    	answeron = 0;
	    	queexpflag = 0;
	    	speakon = 0;
	    	speakonans = 0;
	    	ansflag = 0;//flag for into answer question
	    	indextemp = 0;
	    	ansflagres = 0;
	    	onewordflag = 0;//fi
			//hltvalue = 0;
	    	teacherexplain = [];
	    	teacherquestion = [];
	    	questionans = [];
	    	Qarray=new Array(8);
	    	answercount = new Array(8);
			synth.cancel();
			//top.location.href = "main.html";
			week = chooseweek;
			document.getElementById("gradeweek").innerHTML = "Current Grade " + grade + ", Week " + week;
	    }
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
}
//*/

function chooseSpeakvoice() {
	//synth.cancel();
	//speakon = 0;
	//alert("voice 3");
	  voices = synth.getVoices();
	  voiceSelectElement = document.getElementById("voiceSelect");
	  voiceSelectElement.style.display = 'block';
	  document.getElementById("choosevoice").style.display = 'none';
		//alert("voice 4 " + voices.length);
		//populateVoice = voices[4];
	//only first 3 voices are working properly	
	  //for(i = 0; i < voices.length ; i++) {	
	  for(i = 0; i < 3 ; i++) {

			//alert("voice 5 " + voices[i]);
	    var option = document.createElement('option');
	    option.textContent = voices[i].name + ' (' + voices[i].lang + ')';
	    
	    if(voices[i].default) {
	      option.textContent += ' -- DEFAULT';
	    }

		//alert("voice 6");
	    option.setAttribute('data-lang', voices[i].lang);
	    option.setAttribute('data-name', voices[i].name);
	    option.setAttribute('value', i);

		//alert("voice 7");
	    voiceSelectElement.appendChild(option);
	    
	    //const utterance = new SpeechSynthesisUtterance();
	    //utterance.voice = voices[4];  
	    //populateVoice = voices[4]; 
	   //return voices[4];

		//alert("voice 8");
	  }
	  //alert("voice 9");
	  //speakon = 1;
	  //loadDoc();
}
	
function chooseSpeakRate() {
	  document.getElementById("spkrtdiv").style.display = 'block';
	  document.getElementById("chooseSpkRate").style.display = 'none';
}
	
function speakRate(){
	
	//var ratein =  document.getElementById("spkrt").value;
	
	  var srElement = document.getElementById("spkrtSelect");
	  var spkrt = srElement.options[srElement.selectedIndex].text;

	  voicerate = spkrt;
	/*
	if(ratein < 0.5)		
		alert("Speak rate is out of range");
	else if( ratein > 1.5)
		alert("Speak rate is out of range");
	else{
		voicerate = ratein;
		alert("Speak rate has been changed to " + voicerate);
	}
	*/
	 document.getElementById("spkrtdiv").style.display = 'none';
	  document.getElementById("chooseSpkRate").style.display = 'block';
}
	
//function chooseSpeakvoice(){
	//alert("voice ");
	//populateVoiceList();

	//alert("voice 2");
	/*
	speechSynthesis.onvoiceschanged = function() {
		alert("voice 2");
		populateVoiceList();
		alert("voice 2-1");
	};
	*/	
		
		
		//populateVoiceList();
	//
	/*
	if (speechSynthesis.onvoiceschanged !== undefined) {
	  speechSynthesis.onvoiceschanged = populateVoiceList(){

		alert("voice 3");
		  voices = synth.getVoices();
		  var voiceSelect = document.getElementById("voiceSelect");
			//alert("voice 4 " + voices.length);
			populateVoiceList = voices[4];
		  for(i = 0; i < voices.length ; i++) {

				//alert("voice 5 " + voices[i]);
		    var option = document.createElement('option');
		    option.textContent = voices[i].name + ' (' + voices[i].lang + ')';
		    
		    if(voices[i].default) {
		      option.textContent += ' -- DEFAULT';
		    }

			//alert("voice 6");
		    option.setAttribute('data-lang', voices[i].lang);
		    option.setAttribute('data-name', voices[i].name);

			//alert("voice 7");
		    voiceSelect.appendChild(option);
		    
		    //const utterance = new SpeechSynthesisUtterance();
		    utterance.voice = voices[4];  

			//alert("voice 8");
		  };
	}
	//
	*/
//}
	
function onboundaryHandler(event){
    //var textarea = document.getElementById('textarea');
    //var value = textarea.value;
		//alert("hltvalue9 " + hltvalue);
		//alert("hlelement9 " + hlelement);
		eventtemp = event;
    var textarea = hlelement;
    var value = hltvalue;
    /*
    if(speakon == 0 && ansflag == 1)
    	event = eventtemp;
    */
    var index = event.charIndex;
    
    //*
    //var index;
    if(speakon == 0 && ansflag == 1){
        //alert(" ansflag index " + index);
        //event = eventtemp;
        ansflag = 0;
    }else if(ansflagres == 1){
    	if(index == indextemp){
    			ansflagres = 0;
    			//alert("index in")
    			synth.pause();
    			//var speaktexttemp = value.substring(indextemp);
    			//utterancetemp.volume = 1;
    			//utterancetemp.rate = 1;    			
				//hlelement.focus();
				//hlelement.style.color="#ffffff";
				alert("resume from here");
				//utterancetemp.volume = 1;
    			//utterancetemp.rate = 1;
    			//synth.speak(utterancetemp);
    			synth.resume();
    	}
    	//alert("index 1 " + index);
    	//var range = textarea.createTextRange();
    	//range.collapse(true);
        //range.moveEnd('character', activePositiontemp);
        //range.moveStart('character', anchorPositiontemp);
        //range.select();
    	//textarea.setSelectionRange = false;
    	//event.charIndex = index;
    }else{
    	//index = event.charIndex;
    	//alert("index 2 " + index);    	
    	//index = event.charIndex;
    	indextemp = index;
    }
    //*/
    var word = getWordAt(value, index);
    var anchorPosition = getWordStart(value, index);
    var activePosition = anchorPosition + word.length;
    //eventtemp = event;
    textarea.focus();
    //textarea.show().focus();
    if (textarea.setSelectionRange) {
       textarea.setSelectionRange(anchorPosition, activePosition);
    }
    else {
       var range = textarea.createTextRange();
       range.collapse(true);
       range.moveEnd('character', activePosition);
       range.moveStart('character', anchorPosition);
       range.select();
    }
}
	
function onboundaryHandlerans(event){
	    //var textarea = document.getElementById('textarea');
	    //var value = textarea.value;
			//alert("hltvalue9 " + hltvalue);
			//alert("hlelement9 " + hlelement);
	    var textarea = hlelementans;
	    var value = hltvalueans;
	    var index = event.charIndex;
	    var word = getWordAt(value, index);
	    var anchorPosition = getWordStart(value, index);
	    var activePosition = anchorPosition + word.length;
	    
	    textarea.focus();
	    
	    if (textarea.setSelectionRange) {
	       textarea.setSelectionRange(anchorPosition, activePosition);
	    }
	    else {
	       var range = textarea.createTextRange();
	       range.collapse(true);
	       range.moveEnd('character', activePosition);
	       range.moveStart('character', anchorPosition);
	       range.select();
	    }
	}
	

// Get the word of a string given the string and index
function getWordAt(str, pos) {
    // Perform type conversions.
    str = String(str);
    pos = Number(pos) >>> 0;

    // Search for the word's beginning and end.
    var left = str.slice(0, pos + 1).search(/\S+$/),
        right = str.slice(pos).search(/\s/);

    // The last word in the string is a special case.
    if (right < 0) {
        return str.slice(left);
    }
    
    // Return the word, using the located bounds to extract it from the string.
    return str.slice(left, right + pos);
}

// Get the position of the beginning of the word
function getWordStart(str, pos) {
    str = String(str);
    pos = Number(pos) >>> 0;

    // Search for the word's beginning
    var start = str.slice(0, pos + 1).search(/\S+$/);
    return start;
}

function setVoice(speaktext,element,defvoice) {	
	//alert("setVoice ");		
		utterance = new SpeechSynthesisUtterance(speaktext);
		//utterancetemp = utterance;
		utterance.rate = voicerate;
		hltvalue = speaktext;
		hlelement = element;
		//alert("hltvalue " + hltvalue);
		//alert("hlelement " + hlelement);

		//alert("defvoice " + defvoice);
	    utterance.onboundary = onboundaryHandler;

		//voiceSelect = voiceSelectElement.options[voiceSelectElement.selectedIndex].value;
		//element.focus();
		if(defvoice == 0){
			voiceSelect = voiceSelectElement.options[voiceSelectElement.selectedIndex].value;
			//alert("defvoice= 0");		
			utterance.voice = voices[voiceSelect];
			synth.speak(utterance);
		}
		else if(defvoice == 1){		
			//alert("defvoice= 1");		
			var defaultvoices = synth.getVoices();
			//alert(defaultvoices[1]);
			utterance.voice = defaultvoices[1];
			synth.speak(utterance);
		}else{
			var defaultvoices = synth.getVoices();
			//alert(defaultvoices[1]);
			utterance.voice = defaultvoices[1];
			synth.speak(utterance);
			element.show().focus();
			element.click();
		}
		
		//synth.speak(utterance);
		//element.click();
		document.getElementById("choosevoicediv").style.display = 'none';
		//document.getElementById("voiceSelect").style.display = 'none';
		//document.getElementById("choosevoice").style.display = 'none';	
	}
	
function setVoiceans(speaktext,element,defvoice) {		
	//alert("setVoiceans ");
	utteranceans = new SpeechSynthesisUtterance(speaktext);
	utteranceans.rate = 1;
	hltvalueans = speaktext;
	hlelementans = element;
	//alert("hltvalue " + hltvalue);
	//alert("hlelement " + hlelement);

	//alert("defvoice " + defvoice);
    utteranceans.onboundary = onboundaryHandlerans;

	//voiceSelect = voiceSelectElement.options[voiceSelectElement.selectedIndex].value;
	//element.focus();
	if(defvoice == 0){
		voiceSelect = voiceSelectElement.options[voiceSelectElement.selectedIndex].value;
		//alert("defvoice= 0");		
		utteranceans.voice = voices[voiceSelect];
		synthans.speak(utteranceans);
	}
	else if(defvoice == 1){		
		//alert("defvoice= 1");		
		var defaultvoices = synthans.getVoices();
		//alert(defaultvoices[1]);
		utteranceans.voice = defaultvoices[1];
		synthans.speak(utteranceans);
	}else{
		var defaultvoices = synthans.getVoices();
		//alert(defaultvoices[1]);
		utteranceans.voice = defaultvoices[1];
		synthans.speak(utteranceans);
		//element.show().focus();
		//element.click();
	}
	element.show().focus();
	element.click();
	
	//synth.speak(utteranceans);
	//element.click();
	document.getElementById("choosevoicediv").style.display = 'none';
	//document.getElementById("voiceSelect").style.display = 'none';
	//document.getElementById("choosevoice").style.display = 'none';	
}




function pauseSpeak() {
	//alert("speakon " + speakon);
	//alert("ansflag " + ansflag);
	
	if(speakon == 0){
			//synth.resume();
			synth.pause();
			speakon = 1;
	}else{
		//synth.resume();
		//speakon = 0;
		//alert("speakon != 0 ansflag " + ansflag);
		if(ansflag == 0){
			synth.resume();
			speakon = 0;
		}else{
		//var speaktexttemp = speaktext.substring(indextemp);
		document.getElementById("final_span").innerHTML = "";
		//document.getElementById("copyspeech").innerHTML = "";
		document.getElementById("copyspeech").value = "";
		document.getElementById("answer").innerHTML = "";
		document.getElementById("moreanswer").innerHTML = "";
		document.getElementById("moreword").innerHTML = "";
		//alert("speaktexttemp " + speaktexttemp);
		//hltvalue = speaktexttemp;
		//var utterancetemp = new SpeechSynthesisUtterance(speaktexttemp);
		ansflagres = 1;
		utterancetemp = new SpeechSynthesisUtterance(hltvalue);
		
		if(voices.length != 0){
			voiceSelect = voiceSelectElement.options[voiceSelectElement.selectedIndex].value;
			//alert("defvoice= 0");		
			utterancetemp.voice = voices[voiceSelect];
			//synth.speak(utterance);
		}
		/*
		else {		
			//alert("defvoice= 1");		
			var defaultvoices = synth.getVoices();
			//alert(defaultvoices[1]);
			utterancetemp.voice = defaultvoices[1];
			//synth.speak(utterance);
		}
		*/
		//ansflag = 0;
		//utterancetemp.onboundary = onboundaryHandler(eventtemp);
		utterancetemp.onboundary = onboundaryHandler;
		//utterancetemp.onresume = onboundaryHandler(eventtemp);
		//utterancetemp.onresume = onboundaryHandler;
		//utterancetemp.volume = 0;
		//utterancetemp.rate = 5;
		//hlelement.blur();
		//hlelement.style.color="#000000";
		synth.speak(utterancetemp);
		//hlelement.blur();
		//ansflagres = 1;
		//utterancetemp.onresume = onboundaryHandler;
		//synth.pause();
		//synth.resume();
		//synth.pause();
		//ansflag = 1;
		//utterancetemp.onboundary = onboundaryHandler;
		//synth.resume();
		//utterancetemp.onresume = onboundaryHandler;
		//utterancetemp.onresume = onboundaryHandler;
		//synth.speak(utterancetemp);
		//utterance.onresume = onboundaryHandler;
		//synth.resume();
		speakon = 0;
		}
	}
	///*
	//voiceSelect = voiceSelectElement.options[voiceSelectElement.selectedIndex].value;
	//utterance.voice = voices[voiceSelect];
	//synth.speak(utterance);
	//*/
}

function pauseSpeakans() {
	//alert("speakonans " + speakonans);
	//synth.cancel();
	if(speakonans == 0){
		synthans.pause();
		speakonans = 1;
	}else{
		synthans.resume();
		speakonans = 0;
	}
	///*
	//voiceSelect = voiceSelectElement.options[voiceSelectElement.selectedIndex].value;
	//utterance.voice = voices[voiceSelect];
	//synth.speak(utterance);
	//*/
}

function loadDoc() {
	//if(speakon == 0)
		//synth.cancel();
	//alert("loadDoc 1");
	//synth.cancel();
	questionexplainflag = 0;
	speakon = 0;
	ansflag = 0;
	/*
	 voices = synth.getVoices();
	 for(i = 0; i < voices.length ; i++) {
		 alert("voices " + voices)
	 }
	 */
	 
	  var xhttp = new XMLHttpRequest();
	  //var url="studycontent.jsp";
	  //var url="studycontent.jsp?para=1";
//alert("study in");
	  var url="studycontent.jsp?grade=" + grade + "&week=" + week;
	  //var url="studycontent.jsp";
	  var speaktext1;
	  //var url="";
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	speaktext = this.responseText;
	    	var st = speaktext.search(":");
	    	speaktext1 = speaktext.substring(0, st);
	    	speaktext = speaktext.replace(/:/g, "");
	    	var st = speaktext.search(":");
	    	speaktext = speaktext.substring(0, st);
	    	//alert("studycontent " + studycontent);
	    	studycontent = this.responseText;
	    	//alert("studycontent " + studycontent);
	     //document.getElementById("amit").innerHTML = this.responseText;	
	     //document.getElementById("demo").innerHTML = this.responseText;
	 	document.getElementById("demo").innerHTML = "";
	 	document.getElementById("wordexp").innerHTML = "";
	 	//var header = '<div class="row" style="margin-top: 20px;"><div class="col-lg-12"><div class="questionContainer 218318" style="overflow: hidden;"><div class="col-lgx-12 col-md-12 col-sm-12 col-xs-12"><div class="col-lg-6 col-md-6 col-sm-6 col-xs-12" style="border:1px solid #ccc; border-radius:3px; padding:5px; height:520px; overflow:auto;"><h4 style="text-align: center;">';
	    //var  studycont;
	    //	    /*
	    var studycontstrarray= [];
	    var finalwordexplan;
	    
	     
	 		var n = studycontent.search("!");
		 	var m = studycontent.search(":");
		 	 finalwordexplan = studycontent.substring(m+1, n);
		 			 
	    	//alert("finalwordexplan 1" + finalwordexplan);
	 	
	    //var m;
	    var j = 0;
	 	for (j; 0 < studycontent.search(":"); j++) {
			 
			 			 
	 		var n = studycontent.search(":");
		 	
		 	var studycontent1 = studycontent.substring(0, n);
		 	//alert("studycontent1 " + studycontent1);
	    
	    var studycontstr="";
	    //var m;
	    var i = 0;
	    //alert("m ");
	 	for (i; 0 < studycontent1.search(";"); i++) {
		    //alert("m 1");
		 		var m = studycontent1.search(";");
			 	//alert("m " + m);
			 	var studycont = studycontent1.substring(0, m);
			 	//alert("studycont " + studycont);
			 	var newLine = "\r\n";
			 	//alert("questionexplain[i]"+ questionexplain[i]);
			 	//studycontstr = studycontstr + "<div>"  + studycont + "</div><div>&nbsp;</div>";
			 	studycontstr = studycontstr + studycont + newLine;
			 	var studycontent1temp = studycontent1.substring(m+1);
			 	studycontent1 = studycontent1temp;
			 	//currentTXmax = i;
		 	}
	 	studycontstrarray[j] = studycontstr;
	 	//alert("studycontstr " + studycontstr);
	 	var studycontenttemp = studycontent.substring(n+1);
	 	studycontent = studycontenttemp;
	 	
	 	}//for (j; j < studycontent.search("*"); j++)
	 		//*/
	 	//alert("studycontstr " + studycontstr);
	 	var k = 0;
	 	//alert("m 1 " + studycontstrarray.length);
	 	var finalstucnt = "";
	 	//var sal = studycontstrarray.length;
	 	for(k; studycontstrarray.length > k; k++){
	 		finalstucnt = finalstucnt + studycontstrarray[k];
	 		//alert("finalstucnt " + finalstucnt);	 	
	 	}
	 /*
	 	for (k; 0 < sal; k++) {
	 		alert("m 2");
	 		
	 		finalstucnt = finalstucnt + studycontstrarray[k];
	 		alert("finalstucnt " + finalstucnt);
	 	}
	 */
	 		//document.getElementById("demo").innerHTML = studycontstrarray[0] + studycontstrarray[1] + studycontstrarray[2];

	 		//document.getElementById("demo").innerHTML = tt;
	 		

	 		//document.getElementById("demo").innerHTML = studycontent; 
	 		//alert("finalstucnt " + finalstucnt); 
	 		document.getElementById("demo").innerHTML = finalstucnt;
	 		
	 		//alert("finalwordexplan 2" + finalwordexplan);
	 		document.getElementById("wordexp").innerHTML = finalwordexplan;
	 		
//	 		document.getElementById("wordexp").innerHTML =  "<p>word explanation: <span class='tooltip'>collie<span class='tooltip-text'>This is a tooltip and that is a really long story and this is a reallly good story!</span></span>, <span class='tooltip'>yes<span class='tooltip-text'>This is a tooltip!</span></span>, <span class='tooltip'>test<span class='tooltip-text'>This is a tooltip!</span></span>"; 
	 	//document.getElementById("demo").innerHTML = speaktext;
	 	//document.getElementById("demo").innerHTML += "<div>	Read 'The Secret' and answer the questions that follow.</div><div>&nbsp;</div><div>	1. One morning Janie Rose woke up to a big crash in her room. She opened her eyes slowly, scared of what she might find.</div><div>	&nbsp;</div><div>	2. She saw her unpacked boxes from the move scattered on the floor. Janie screamed in her pillow, 'I wish I never had to move!'</div><div>	&nbsp;</div><div>	3. Janie’s mom got a new job which moved Janie from her school, soccer team, and friends.</div><div>	&nbsp;</div><div>	4. Janie got out of bed and went downstairs to fix a bowl of cereal for breakfast. Her mom was already down there and told Janie, good morning. Janie mumbled something that sounded like a good morning.</div><div>	&nbsp;</div><div>	5. Her mom sat down beside Janie and tried to make her feel better by telling her that she would meet new friends and to give this town a chance. Janie just rolled her eyes, and slurped the milk up from her cereal bowl.</div><div>	&nbsp;</div><div>	6. Janie then got up and went back to her room. She crawled back in bed and pulled her covers up to her chin. She stared at the window, and the old chest underneath it. The chest was there when they moved in.</div><div>	&nbsp;</div><div>	<div>		7. Janie got out of bed and walked over to see what was in it. She opened it up to find many neat treasures. She found dolls, hair bows, colors, and a rolled up piece of paper.</div>	<div>		&nbsp;</div>	<div>		8. She opened up the paper to find that it was a map. The map title read, The Secret of Blue Ridge.</div>	<div>		&nbsp;</div>	<div>		'Hmmm, that is the name of this town,' Janie said.</div>	<div>		&nbsp;</div>	<div>		9. As Janie studied the map she realized that this big secret was at Blue Ridge Library. Janie got herself ready and ran down the stairs with the map.</div>	<div>		&nbsp;</div>	<div>		10. She grabbed an apple and told her mom she was headed to the library as she was running out the door. Janie’s mom yelled, 'You don’t even know where the library is!'</div>	<div>		Janie yelled, 'I have a map!'</div>	<div>		&nbsp;</div>	<div>		11. Janie hopped on her bike and looked at the compass rose. The compass rose showed that the library was north of her house.</div>	<div>		&nbsp;</div>	<div>		12. Janie then compared the map symbols to the map key. The map symbols showed Janie that she would pass Bert’s Grocery, Amelia’s Flowers, and Carl’s Cars.</div>	<div>		&nbsp;</div>	<div>		13. As Janie followed the map she saw a soccer field with a group of girls playing on it. She stopped for a moment and just watched them.</div>	<div>		&nbsp;</div>	<div>		14. One of the girls ran up to Janie and asked her if she wanted to play. Janie gave a slight smile and said, 'Maybe, but I have to take care of something first.'</div>	<div>		&nbsp;</div>	<div>		15. She rode all the way to the library and parked her bike. She went into the library and read the clue on the back of the map.</div>	<div>		&nbsp;</div>	<div>		16. The clue read, look to the stars. 'Look to the stars,' Janie said while looking up.</div>	<div>	&nbsp;</div>	<div>		17. Then Janie saw a quote etched on the ceiling. 'Make today, better than yesterday, and tomorrow better than today.' Janie thought for a moment and realized that is the secret./div><div>&nbsp;</div><div>		18. She smiled and said, 'Now I think I have a soccer game to play.'</div>";

	     //speaktext = this.responseText;
	     //alert(speaktext);
	     //speaktext1 = speaktext;
	     document.getElementById("demo").style.display = "block";
	     document.getElementById("wordexp").style.display = "block";
	     document.getElementById("moreTE").style.display = "block";
	     document.getElementById("moreTE1").style.display = "none";
	     document.getElementById("previousTEInfo").style.display = "block";
	     document.getElementById("nextTEInfo").style.display = "block";
	     document.getElementById("BooksQuestion").style.display = "none";
	     document.getElementById("StartPE").style.display = "block";
	     document.getElementById("StartPR").style.display = "none";

		document.getElementById("explainQXcont").style.display = 'none';
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(speaktext));

		 //synth.speak(new SpeechSynthesisUtterance(speaktext));
		 //alert("loaddoc ");
		 var element = document.getElementById("demo");
		 //alert(voices.length);
		 //if (speechSynthesis.onvoiceschanged === undefined)
		 //if (voices != undefined)
			 
	     //*** using voices.length to determine whether voice is chosen or use default one		 
	     
//	     if (voices.length != 0)
//		 	setVoice(finalstucnt,element,0);
//		 else
//			setVoice(finalstucnt,element,1); 
//	     
	     
         if (voices.length != 0)
		 	setVoice(speaktext1,element,0);
		 else
			setVoice(speaktext1,element,1); 
		 /*
		utterance = new SpeechSynthesisUtterance(speaktext);
		voiceSelect = voiceSelectElement.options[voiceSelectElement.selectedIndex].value;
		utterance.voice = voices[voiceSelect];
		synth.speak(utterance);
		document.getElementById("voiceSelect").style.display = 'none';
		document.getElementById("choosevoice").style.display = 'block';
		   */  
		//var utterance = new SpeechSynthesisUtterance();
		//utterance.voice = voices[3]; 
		//alert("loaddoc 1" + voiceSelect);
		//var voiceSelectElement = document.getElementById("voiceSelect");
		 
		//alert("loaddoc 1" + voiceSelect);  
		//var voiceSelect = voiceSelectElement.options[voiceSelectElement.selectedIndex].value;
		//alert("loaddoc " + voiceSelect); 
		//utterance.voice = voices[voiceSelect];

		//alert("loaddoc 2" + utterance.voice);
	     //synth.speak(utterance);
	     //document.getElementById("voiceSelect").style.display = 'none';
		  //document.getElementById("choosevoice").style.display = 'block';
	     /*
	     const message = new SpeechSynthesisUtterance(text)
	     message.voice = await chooseVoice()
	     speechSynthesis.speak(message)
	     */
	     
	     document.getElementById("explain").innerHTML = "";
	     currentTX = 0;
//questionexplain();

//loadQuestion();

//startexplain();

	    }
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
}
	

	
function nextquestion(){
	if(ansflag == 0)
		synth.cancel();
	speakon = 0;
	ansflag = 0;
	var xhttp = new XMLHttpRequest();
	var url;
	QA = 0;
	if(currentQX == -1)
		currentQX = 0;
	if(currentQX == currentQXmax)
		currentQX = currentQXmax - 1;
	  //if(document.getElementById("").checked || document.getElementById("").checked )
//alert("in" + currentQX);
		  if(document.getElementById("Q1").checked)
			  QA = 1;
		  if(document.getElementById("Q2").checked)
			  QA = 2;
		  if(document.getElementById("Q3").checked)
			  QA = 3;
		  if(document.getElementById("Q4").checked)
			  QA = 4;
		  /*
		  if(QA == 0)
		  {
			  alert("Please select an answer");
			  return;
		  }
		  else
		  {
			  //alert("currentQX " + currentQX);
			  if(QA == questionans[currentQX])
			  { 
				  alert("answer correct");
				  //on();
			  }
			  else
			  {
				  alert("answer wrong");	
				  return;
			  }
		  }
	*/
	//alert("questionans[currentQX]" + questionans[currentQX]);
	if(QA == 0)
	  {
		  alert("Please select an answer");
		  //window.open(url, "Please select an answer"., 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
		  
			  document.getElementById("overlay").style.display = "none";
			  document.getElementById("mycanvas").style.visibility = "hidden";
			  document.getElementById("mycanvas").height = 10;
		  return;
	  }
	  else
	  {
		  //alert("currentQX " + currentQX);
		  if(QA == questionans[currentQX])
		  { 
			  alert("correct answer.");
			  //alert("bef answercount[currentQX][0]" + answercount[currentQX][0]);
			  if(answercount[currentQX][0] < 9)
			  	answercount[currentQX][0] = parseInt(answercount[currentQX][0]) + 1;
			  //alert("aft answercount[currentQX][0]" + answercount[currentQX][0]);
			  //alert("aft answercount[currentQX][1]" + answercount[currentQX][1]);
			  answeron =1;
			  //queexpflag = 1;
			  document.getElementById("overlay").style.display = "block";
			  document.getElementById("explain").style.display = "block";
  			  setTimeout(off, 3000);
			  var answertemp = answercount[currentQX][0];
			  var coin;
			  //on();
			  if( answercount[currentQX][1] == 0){
				  answercount[currentQX][1] = 1;
			  	if(answertemp == 1)	{			  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 10 coins!!!!!";
			  		coin = 10;
			  	}else if(answertemp == 2){			  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 5 coins!!!!!";
			  		coin = 5;
			  	}else if(answertemp == 3){				  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 3 coins!!!!!";
			  	coin = 3;
			  	}else if(answertemp > 3){			  
			  		document.getElementById("text").innerHTML = "Sorry!! You do not win any coin!!!!!";
			  		document.getElementById("mycanvas").style.visibility = "hidden";
			  		coin = 0;
		  		}
		  	   }
			  else{
				document.getElementById("text").innerHTML = "Sorry!! You already answered the question!!!!!";  
				document.getElementById("mycanvas").style.visibility = "hidden";
		  		coin = 0;
			  }
			  document.getElementById("explain").innerHTML = "";
			  //url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=1&coin=" + coin + "&userid=" + userid;
			  url="teacherexplain.jsp?questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=1&coin=" + coin + "&userid=" + userid + "&grade=" + grade + "&week=" + week;
			  //url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=" + answercount[currentQX][1];
			  xhttp.open("GET", url, true);
			  xhttp.send();
		  }
		  else
		  {
			  alert("wrong answers. Please select another answer.");
			  //alert("bef answercount[currentQX][0]" + answercount[currentQX][0]);
			  //answercount[currentQX][0] = answercount[currentQX][0] +1;
			  if(answercount[currentQX][0] < 9)
			  	answercount[currentQX][0] = parseInt(answercount[currentQX][0]) + 1;
			  //alert("aft answercount[currentQX][0]" + answercount[currentQX][0]);
			  answeron =1;
			  //queexpflag =1;
			  //document.getElementById("text").innerHTML = "Congratulation!! You win 5 conis!!!!!";
			  document.getElementById("overlay").style.display = "none";
			  document.getElementById("mycanvas").style.visibility = "hidden";
			  //document.getElementById("explain").style.display = "none";
			  document.getElementById("mycanvas").height = 10;
			  document.getElementById("explain").innerHTML = "";
			  //url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=0&coin=0&userid=" + userid;
			  url="teacherexplain.jsp?questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=0&coin=0&userid=" + userid + "&grade=" + grade + "&week=" + week;
			  xhttp.open("GET", url, true);
			  xhttp.send();
			  return;
		  }
	  }
	if(currentQX < currentQXmax-1) 
	{
		currentQX++;
		document.getElementById("questionset").innerHTML = "";
		document.getElementById("questionset").innerHTML += '<div class="test_question"><h2 style="font-size: 20px;font-weight: normal;"><div>' + Qarray[currentQX][0] + '</div></h2></div><div id="question" style="margin: 20px;"><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q1" type="radio" value="766502" attr-data="218318">&nbsp;&nbsp;A.&nbsp;&nbsp;' + Qarray[currentQX][1] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q2" type="radio" value="766503" attr-data="218318">&nbsp;&nbsp;B.&nbsp;&nbsp;' + Qarray[currentQX][2] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q3" type="radio" value="766504" attr-data="218318">&nbsp;&nbsp;C.&nbsp;&nbsp;' + Qarray[currentQX][3] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q4" type="radio" value="766505" attr-data="218318">&nbsp;&nbsp;D.&nbsp;&nbsp;' + Qarray[currentQX][4] + '</div></label></div></div>';
	     //document.getElementById("questionset").innerHTML += '<div class="test_question"><h2 style="font-size: 20px;font-weight: normal;"><div>' + Qarray[currentQX][0] + '</div></h2></div><div id="question" style="margin: 20px;"><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q1" type="radio" value="766502" attr-data="218318">&nbsp;&nbsp;A.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[currentQX][1] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q2" type="radio" value="766503" attr-data="218318">&nbsp;&nbsp;B.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[currentQX][2] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q3" type="radio" value="766504" attr-data="218318">&nbsp;&nbsp;C.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[currentQX][3] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q4" type="radio" value="766505" attr-data="218318">&nbsp;&nbsp;D.&nbsp;&nbsp;</div><div class="option_text">' + Qarray[currentQX][4] + '</div></label></div></div>';
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(teacherquestion[currentQX]));
	     
		 var element = document.getElementById("questionset");
		 //setVoice(speaktext,element);
	     //setVoice(teacherquestion[currentQX],element);
	     if (voices.length != 0)
			 	setVoice(teacherquestion[currentQX],element,0);
			 else
				setVoice(teacherquestion[currentQX],element,1);
	}
	else
		{
		document.getElementById("questionset").innerHTML = "";
		document.getElementById("questionset").innerHTML += '<div class="test_question"><h2 style="font-size: 20px;font-weight: normal;"><div>' + Qarray[currentQX][0] + '</div></h2></div><div id="question" style="margin: 20px;"><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q1" type="radio" value="766502" attr-data="218318">&nbsp;&nbsp;A.&nbsp;&nbsp;' + Qarray[currentQX][1] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q2" type="radio" value="766503" attr-data="218318">&nbsp;&nbsp;B.&nbsp;&nbsp;' + Qarray[currentQX][2] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q3" type="radio" value="766504" attr-data="218318">&nbsp;&nbsp;C.&nbsp;&nbsp;' + Qarray[currentQX][3] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q4" type="radio" value="766505" attr-data="218318">&nbsp;&nbsp;D.&nbsp;&nbsp;' + Qarray[currentQX][4] + '</div></label></div></div>';
	    alert("");
		//document.getElementById("questionset").innerHTML = "";
		//speechSynthesis.speak(new SpeechSynthesisUtterance(""));
		var element = document.getElementById("questionset");
		//setVoice("",element);
	     if (voices.length != 0)
			 	setVoice("",element,0);
			 else
				setVoice("",element,1);
		currentQX = currentQXmax;
		}
		
	
}

function previousquestion(){
	//synth.cancel();
	if(ansflag == 0)
		synth.cancel();
	speakon = 0;
	ansflag = 0;
	var xhttp = new XMLHttpRequest();
	var url;
	QA = 0;

	if(currentQX == -1)
		currentQX = 0;
	if(currentQX == currentQXmax )
		currentQX = currentQXmax-1;
	  //if(document.getElementById("").checked || document.getElementById("").checked )
//alert("previous " + currentQX);
		  if(document.getElementById("Q1").checked)
			  QA = 1;
		  if(document.getElementById("Q2").checked)
			  QA = 2;
		  if(document.getElementById("Q3").checked)
			  QA = 3;
		  if(document.getElementById("Q4").checked)
			  QA = 4;
		  /*
		  if(QA == 0)
		  {
			  //alert("Please select an answer");
			  return;
		  }
		  else
		  {
			  //alert("currentQX " + currentQX);
			  if(QA == questionans[currentQX])
			  { 
				  //alert("answer correct");
				  //on();
			  }
			  else
			  {
				  //alert("answer wrong");	
				  return;
			  }
		  }
	*/
	////alert("userid" + userid);
	if(QA == 0)
	  {
		  alert("Please select an answer");
		  //window.open(url, "Please select an answer"., 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
		  
			  document.getElementById("overlay").style.display = "none";
			  document.getElementById("mycanvas").style.visibility = "hidden";
			  document.getElementById("mycanvas").height = 10;
		  return;
	  }
	  else
	  {
		  //alert("currentQX " + currentQX);
		  if(QA == questionans[currentQX])
		  { 
			  alert("correct answer.");
			  //alert("bef answercount[currentQX][0]" + answercount[currentQX][0]);
			  if(answercount[currentQX][0] < 9)
			  	answercount[currentQX][0] = parseInt(answercount[currentQX][0]) + 1;
			  //alert("aft answercount[currentQX][0]" + answercount[currentQX][0]);
			  answeron =1;
			  //queexpflag = 1;
			  document.getElementById("overlay").style.display = "block";
			  document.getElementById("explain").style.display = "block";
  			  setTimeout(off, 3000);
			  var answertemp = answercount[currentQX][0];
			  //on();
			  	  var coin;
			  //on();
			  if( answercount[currentQX][1] == 0){
				  answercount[currentQX][1] = 1;
			  	if(answertemp == 1)	{			  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 10 coins!!!!!";
			  		coin = 10;
			  	}else if(answertemp == 2){			  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 5 coins!!!!!";
			  		coin = 5;
			  	}else if(answertemp == 3){				  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 3 coins!!!!!";
			  	coin = 3;
			  	}else if(answertemp > 3){			  
			  		document.getElementById("text").innerHTML = "Sorry!! You do not win any coin!!!!!";
			  		document.getElementById("mycanvas").style.visibility = "hidden";
			  		coin = 0;
		  		}
		  	   }
			  else{
				document.getElementById("text").innerHTML = "Sorry!! You already answered the question!!!!!";  
				document.getElementById("mycanvas").style.visibility = "hidden";
		  		coin = 0;
			  } 
			  document.getElementById("explain").innerHTML = "";
			  //url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=1&coin=" + coin + "&userid=" + userid;
			  url="teacherexplain.jsp?questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=1&coin=" + coin + "&userid=" + userid + "&grade=" + grade + "&week=" + week;
			  //url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=" + answercount[currentQX][1];
			  xhttp.open("GET", url, true);
			  xhttp.send();
		  }
		  else
		  {
			  alert("wrong answer. Please select another answer.");
			  //alert("bef answercount[currentQX][0]" + answercount[currentQX][0]);
			  //answercount[currentQX][0] = answercount[currentQX][0] +1;
			  if(answercount[currentQX][0] < 9)
			  	answercount[currentQX][0] = parseInt(answercount[currentQX][0]) + 1;
			  //alert("aft answercount[currentQX][0]" + answercount[currentQX][0]);
			  answeron =1;
			  //queexpflag =1;
			  //document.getElementById("text").innerHTML = "Congratulation!! You win 5 conis!!!!!";
			  document.getElementById("overlay").style.display = "none";
			  document.getElementById("mycanvas").style.visibility = "hidden";
			  //document.getElementById("explain").style.display = "none";
			  document.getElementById("mycanvas").height = 10;
			  document.getElementById("explain").innerHTML = "";
			  //url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=0&coin=0&userid=" + userid;
			  url="teacherexplain.jsp?questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=0&coin=0&userid=" + userid + "&grade=" + grade + "&week=" + week;
			  xhttp.open("GET", url, true);
			  xhttp.send();
			  return;
		  }
	  }
	
	if(currentQX > 0){
		currentQX--;
		document.getElementById("questionset").innerHTML = "";
		document.getElementById("questionset").innerHTML += '<div class="test_question"><h2 style="font-size: 20px;font-weight: normal;"><div>' + Qarray[currentQX][0] + '</div></h2></div><div id="question" style="margin: 20px;"><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q1" type="radio" value="766502" attr-data="218318">&nbsp;&nbsp;A.&nbsp;&nbsp;' + Qarray[currentQX][1] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q2" type="radio" value="766503" attr-data="218318">&nbsp;&nbsp;B.&nbsp;&nbsp;' + Qarray[currentQX][2] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q3" type="radio" value="766504" attr-data="218318">&nbsp;&nbsp;C.&nbsp;&nbsp;' + Qarray[currentQX][3] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q4" type="radio" value="766505" attr-data="218318">&nbsp;&nbsp;D.&nbsp;&nbsp;' + Qarray[currentQX][4] + '</div></label></div></div>';
	     //document.getElementById("questionset").innerHTML += '<div class="test_question"><h2 style="font-size: 20px;font-weight: normal;"><div>' + Qarray[currentQX][0] + '</div></h2></div><div id="question" style="margin: 20px;"><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q1" type="radio" value="766502" attr-data="218318">&nbsp;&nbsp;A.&nbsp;&nbsp;' + Qarray[currentQX][1] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q2" type="radio" value="766503" attr-data="218318">&nbsp;&nbsp;B.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[currentQX][2] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q3" type="radio" value="766504" attr-data="218318">&nbsp;&nbsp;C.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[currentQX][3] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q4" type="radio" value="766505" attr-data="218318">&nbsp;&nbsp;D.&nbsp;&nbsp;</div><div class="option_text">' + Qarray[currentQX][4] + '</div></label></div></div>';
	     //document.getElementById("questionset").innerHTML += '<div class="test_question"><h2 style="font-size: 20px;font-weight: normal;"><div>' + Qarray[currentQX][0] + '</div></h2></div><div id="question" style="margin: 20px;"><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q1" type="radio" value="766502" attr-data="218318">&nbsp;&nbsp;A.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[currentQX][1] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q2" type="radio" value="766503" attr-data="218318">&nbsp;&nbsp;B.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[currentQX][2] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q3" type="radio" value="766504" attr-data="218318">&nbsp;&nbsp;C.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[currentQX][3] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q4" type="radio" value="766505" attr-data="218318">&nbsp;&nbsp;D.&nbsp;&nbsp;</div><div class="option_text">' + Qarray[currentQX][4] + '</div></label></div></div>';
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(teacherquestion[currentQX]));
	     
		var element = document.getElementById("questionset");
		//setVoice("",element);
	     //setVoice(teacherquestion[currentQX],element);
	     if (voices.length != 0)
			 	setVoice(teacherquestion[currentQX],element,0);
			 else
				setVoice(teacherquestion[currentQX],element,1);
	}
	else
		{
		
		document.getElementById("questionset").innerHTML = "";
		document.getElementById("questionset").innerHTML += '<div class="test_question"><h2 style="font-size: 20px;font-weight: normal;"><div>' + Qarray[currentQX][0] + '</div></h2></div><div id="question" style="margin: 20px;"><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q1" type="radio" value="766502" attr-data="218318">&nbsp;&nbsp;A.&nbsp;&nbsp;' + Qarray[currentQX][1] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q2" type="radio" value="766503" attr-data="218318">&nbsp;&nbsp;B.&nbsp;&nbsp;' + Qarray[currentQX][2] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q3" type="radio" value="766504" attr-data="218318">&nbsp;&nbsp;C.&nbsp;&nbsp;' + Qarray[currentQX][3] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q4" type="radio" value="766505" attr-data="218318">&nbsp;&nbsp;D.&nbsp;&nbsp;' + Qarray[currentQX][4] + '</div></label></div></div>';
	    alert("it is the begining");
		//document.getElementById("questionset").innerHTML = "it is the begining";
		//speechSynthesis.speak(new SpeechSynthesisUtterance("it is the begining"));
		
		var element = document.getElementById("questionset");
		//setVoice("",element);
		//setVoice("it is the begining",element);
	     if (voices.length != 0)
			 	setVoice("it is the begining",element,0);
			 else
				setVoice("it is the begining",element,1);
		currentQX = -1;
		}
	
	//xhttp.open("GET", url, true);
	  //xhttp.send();
}
	
function loadQuestion() {
	synth.cancel();
	speakon = 0;
	ansflag = 0;
	questionexplainflag = 0;
	  var xhttp = new XMLHttpRequest();
	  var questiontext;
	  var questiontexttemp;
	  var questiontexti;
	  var questiontexttempi;
	  var questiontextj;
	  var questiontexttempj;
	  currentQX = 0;
	  //var url="studycontent.jsp";
	  //var url="studycontent.jsp?para=1";
//alert("ing " + grade);
//alert("inw " + week);
	  var url="question.jsp?grade=" + grade + "&week=" + week + "&userid=" + userid;
	  //var url="question.jsp";
	  //var url="";
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     //document.getElementById("amit").innerHTML = this.responseText;	
	     questiontext = this.responseText;
	     
	     //var loadinfovaluein = questiontext;
	 	//alert("questiontext " + questiontext);
	 	var i=0;
		 	var m;
		 	var n;
		 	var o;
		 	var p;
		 	
		 	var c = questiontext.search(";");
		 	//alert("c " + c);
		 	var anscount = questiontext.substring(0, c);
		 	questiontext = questiontext.substring(c+1);
		 	//alert("questiontext1 " + questiontext);	
		 	
	     var anscounttemp;
	     var anscontcor;
	     var anscontcortemp;
	     var d=0;
	     var e;
		 	for (d; 0 < anscount.search("/"); d++) {
		 		answercount[d]=new Array(4);
		 		e = anscount.search("/");
			 	//alert("e " + e);
			 	anscontcor = anscount.substring(0, e);
			 	var f = 0;
			 	var g;
			 	//for (f; 0 < anscontcor.search(","); f++) {
			 		g = anscontcor.search(",");
				 	//alert("f " + f);
				 	answercount[d][f] = anscontcor.substring(0, g);
				 	//alert("answercount[d][f] " + answercount[d][f]);
				 	//alert("et" + i + " = " + teacherexplain[i]);
				 	anscontcortemp = anscontcor.substring(g+1);
				 	answercount[d][f+1] = anscontcortemp;

				 	//alert("answercount[d][f+1] " + answercount[d][f+1]);
				 	anscontcor = anscontcortemp;
				 	//alert("anscontcor " + anscontcor);
				 	//currentTXmax = i;
			 	//}
			 	
			 	
			 	//alert("et" + i + " = " + teacherexglain[i]);
			 	anscounttemp = anscount.substring(e+1);
			 	anscount = anscounttemp;
			 	//alert("anscount " + anscount);	
			 	//currentTXmax = i;
		 	}
		 	
		 	
		 	
		 	
		 	
		 	
		 	
		 	p = questiontext.search(";");
		 	//alert("p " + p);
		 	questiontexti = questiontext.substring(0, p);
		 	//alert("questiontexti " + questiontexti);
		 	var k = 0;
		 	for (k; questiontexti.search("/") > 0; k++)
		 		{
		 		o = questiontexti.search("/");
			 	//alert("k " + k);
			 	questionans[k] = questiontexti.substring(0, o);

			 	//alert("questionans[k] " + questionans[k]);
			 	//alert("Qarray[i][j] " + Qarray[i][j]);
			 	questiontexttempi = questiontexti.substring(o+1);
			 	//alert("questiontexttempi " + questiontexttempi);
			 	questiontexti = questiontexttempi;

			 	//alert("questiontexti " + questiontexti);
			 	//Qarray[i][j] = teacherquestiontemp;
		 		}
		 	//questionans[k+1] = questiontexti.substring(2);
		 	
		 	questiontexttemp = questiontext.substring(p+1);
		 	questiontext = questiontexttemp;

		 	//alert("questiontext " + questiontext);
	 	//alert("m in");
	 	for (i; i < questiontext.search("/"); i++) {
	 		//alert("m1 in");
	 		Qarray[i]=new Array(5);
	 			 	
		 	//teacherquestion[i] = questiontexti;
	 		m = questiontext.search("/");
		 	//alert("m " + m);
		 	questiontexti = questiontext.substring(0, m);
		 	teacherquestion[i] = questiontexti;
		 	//alert("teacherquestion[i] " + teacherquestion[i]);
		 	var j = 0;
		 	for (j; j <4; j++)
		 		{
		 		n = questiontexti.search(";");
			 	//alert("m " + m);
			 	Qarray[i][j] = questiontexti.substring(0, n);
			 	//alert("Qarray[i][j] " + Qarray[i][j]);
			 	questiontexttempi = questiontexti.substring(n+3);
			 	questiontexti = questiontexttempi;

			 	//alert("questiontexti " + questiontexti);
			 	//Qarray[i][j] = teacherquestiontemp;
		 		}
		 	Qarray[i][4] = questiontexti;
		 	//alert("et" + i + " = " + teacherquestion[i]);
		 	questiontexttemp = questiontext.substring(m+2);
		 	questiontext = questiontexttemp;
		 	//alert("questiontext " + questiontext);
		 	//currentQXmax = i;
	 	}	     
	     
	 	currentQXmax = i;
	 	currentQX = 0;
	 	//alert("currentQXmax " + currentQXmax);
	     questionon = 1;
	     document.getElementById("BooksQuestion").style.display = "block";
	     //document.getElementById("questionset").value = this.responseText;
	     //document.getElementById("questionset").innerHTML += '<div class="test_question"><h2 style="font-size: 20px;font-weight: normal;"><div>What is the meaning of the secret of Blue Ridge that she found?</div></h2></div><div id="question" style="margin: 20px;"><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="radio12" type="radio" value="766502" attr-data="218318">&nbsp;&nbsp;A.&nbsp;&nbsp;</div><div class="option_text"><div>See the bright side of things.</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="radio12" type="radio" value="766503" attr-data="218318">&nbsp;&nbsp;B.&nbsp;&nbsp;</div><div class="option_text"><div>Live life to the fullest and always try to make your days good.</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="radio12" type="radio" value="766504" attr-data="218318">&nbsp;&nbsp;C.&nbsp;&nbsp;</div><div class="option_text"><div>When you are making things good today, the future will automatically be good too.</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="radio12" type="radio" value="766505" attr-data="218318">&nbsp;&nbsp;D.&nbsp;&nbsp;</div><div class="option_text">Celebrate everything in your life, even the small things.</div></label></div></div>';
	     document.getElementById("questionset").innerHTML = "";
	     document.getElementById("questionset").innerHTML += '<div class="test_question"><h2 style="font-size: 20px;font-weight: normal;"><div>' + Qarray[currentQX][0] + '</div></h2></div><div id="question" style="margin: 20px;"><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q1" type="radio" value="766502" attr-data="218318">&nbsp;&nbsp;A.&nbsp;&nbsp;' + Qarray[currentQX][1] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q2" type="radio" value="766503" attr-data="218318">&nbsp;&nbsp;B.&nbsp;&nbsp;' + Qarray[currentQX][2] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q3" type="radio" value="766504" attr-data="218318">&nbsp;&nbsp;C.&nbsp;&nbsp;' + Qarray[currentQX][3] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q4" type="radio" value="766505" attr-data="218318">&nbsp;&nbsp;D.&nbsp;&nbsp;' + Qarray[currentQX][4] + '</div></label></div></div>';
	     //document.getElementById("questionset").innerHTML += '<div class="test_question"><h2 style="font-size: 20px;font-weight: normal;"><div>' + Qarray[currentQX][0] + '</div></h2></div><div id="question" style="margin: 20px;"><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q1" type="radio" value="766502" attr-data="218318">&nbsp;&nbsp;A.&nbsp;&nbsp;' + Qarray[currentQX][1] + '</div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q2" type="radio" value="766503" attr-data="218318">&nbsp;&nbsp;B.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[currentQX][2] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q3" type="radio" value="766504" attr-data="218318">&nbsp;&nbsp;C.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[currentQX][3] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q4" type="radio" value="766505" attr-data="218318">&nbsp;&nbsp;D.&nbsp;&nbsp;</div><div class="option_text">' + Qarray[currentQX][4] + '</div></label></div></div>';
		 //document.getElementById("questionset").innerHTML += '<div class="test_question"><h2 style="font-size: 20px;font-weight: normal;"><div>' + Qarray[0][0] + '</div></h2></div><div id="question" style="margin: 20px;"><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q1" type="radio" value="766502" attr-data="218318">&nbsp;&nbsp;A.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[0][1] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q2" type="radio" value="766503" attr-data="218318">&nbsp;&nbsp;B.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[0][2] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q3" type="radio" value="766504" attr-data="218318">&nbsp;&nbsp;C.&nbsp;&nbsp;</div><div class="option_text"><div>' + Qarray[0][3] + '</div></div></label></div><div style="margin: 0 0 20px;"><label style="font-weight:normal;"><div class="option_btn"><input name="ans218318" id="Q4" type="radio" value="766505" attr-data="218318">&nbsp;&nbsp;D.&nbsp;&nbsp;</div><div class="option_text">' + Qarray[0][4] + '</div></label></div></div>';
	     document.getElementById("explain").innerHTML = "";
		 
		document.getElementById("explainQXcont").style.display = 'none';
		document.getElementById("explainQX").style.display = 'block';
	     //speaktext = this.responseText;
	     //alert(speaktext);
	     //speaktext1 = speaktext;
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(teacherquestion[0]));
		var element = document.getElementById("questionset");
		//setVoice("",element);
			//setVoice(teacherquestion[0],element);
			//alert(voices.length);
		     if (voices.length != 0)
				 	setVoice(teacherquestion[0],element,0);
				 else
					setVoice(teacherquestion[0],element,1);
			/*
			utterance = new SpeechSynthesisUtterance(teacherquestion[0]);
			voiceSelect = voiceSelectElement.options[voiceSelectElement.selectedIndex].value;
			utterance.voice = voices[voiceSelect];
			synth.speak(utterance);
			document.getElementById("voiceSelect").style.display = 'none';
			document.getElementById("choosevoice").style.display = 'block';  
	    	*/
	    }
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  //questionon = 1;
	  xhttp.open("GET", url, true);
	  xhttp.send();
	}
	
	
	
function nextexplain(){
	//synth.cancel();
	if(ansflag == 0)
		synth.cancel();
	speakon = 0;
	ansflag = 0;
	
	if(currentTX < currentTXmax) 
	{
	var teacherexplaintempn = teacherexplain[currentTX + 1];
	//alert(teacherexplaintempn);
	document.getElementById("explain").innerHTML = teacherexplaintempn;
	//speechSynthesis.speak(new SpeechSynthesisUtterance(teacherexplaintempn));
	var element = document.getElementById("explain");
		//setVoice("",element);
	//setVoice(teacherexplaintempn,element);
    if (voices.length != 0)
	 	setVoice(teacherexplaintempn,element,0);
	 else
		setVoice(teacherexplaintempn,element,1);
	currentTX++;
	}
	else
		{
			
		document.getElementById("explain").innerHTML = "Please click on Start button below for more explanation";
		document.getElementById("moreTE1").style.display = "block";
		document.getElementById("moreTE").style.display = "none";
		//speechSynthesis.speak(new SpeechSynthesisUtterance(""));
		var element = document.getElementById("explain");
		//setVoice("",element);
	    if (voices.length != 0)
		 	setVoice("Please click on Start button below for more explanation",element,0);
		 else
			setVoice("Please click on Start button below for more explanation",element,1);
		currentTX = currentTXmax + 1;
		   
		}
		
}

function previousexplain(){
	//synth.cancel();
	if(ansflag == 0)
		synth.cancel();
	speakon = 0;
	ansflag = 0;
	
	if(currentTX > 0){
	var teacherexplaintempp = teacherexplain[currentTX - 1];
	//alert(teacherexplaintempp);
	document.getElementById("explain").innerHTML = teacherexplaintempp;
	//speechSynthesis.speak(new SpeechSynthesisUtterance(teacherexplaintempp));
	var element = document.getElementById("explain");
		//setVoice("",element);
	//setVoice(teacherexplaintempp,element);
    if (voices.length != 0)
	 	setVoice(teacherexplaintempp,element,0);
	 else
		setVoice(teacherexplaintempp,element,1);
	currentTX--;
	}
	else
		{
		document.getElementById("explain").innerHTML = "it is the begining";
		//speechSynthesis.speak(new SpeechSynthesisUtterance("it is the begining"));
		var element = document.getElementById("explain");
		//setVoice("it is the begining",element);
	    if (voices.length != 0)
		 	setVoice("it is the begining",element,0);
		 else
			setVoice("it is the begining",element,1);
		currentTX = -1;
		}
}
	
function startexplain() {
	synth.cancel();
	speakon = 0;
	ansflag = 0;
	questionexplainflag = 0;
	  var xhttp = new XMLHttpRequest();
	  var explaintext;
	  var explaintexttemp;
	  currentTX = 0;
	  questionon = 0;
	  //var explaintext1;
	  //var url="studycontent.jsp";
	  //var url="studycontent.jsp?para=1";
//alert("explaintext in");

//alert("grade " + grade);

//alert("week " + week);

//alert("questionon " + questionon);

//alert("userid " + userid);


//alert("answercount[currentQX][0] " + answercount[currentQX][0]);
	  //var url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=0&questionnum=0&correctans=0&coin=0&userid=" + userid;
	  //var url="teacherexplain.jsp?questionon=" + questionon + "&answeron=0&questionnum=0&correctans=0&coin=0&userid=" + userid + "&grade=" + grade + "&week=" + week;
      var url="grammarexplain.jsp?grammar=Y&userid=" + userid + "&grade=" + grade + "&week=" + week;
	  //var url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=0&answeron=0&questionnum=0&correctans=0&coin=0&userid=" + userid;
		 
	  //var url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=0&questionnum=0&answercount=" + answercount[currentQX][0] + "&correctans=0&coin=0&userid=" + userid;
	 
	  //var url="";
	 //alert("url " + url);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     //document.getElementById("amit").innerHTML = this.responseText;	
	     //document.getElementById("explain").innerHTML = this.responseText;
	     explaintext = this.responseText;
	     
	     //var loadinfovaluein = explaintext;
	 	//alert("oo " + explaintext);
	 	var m = explaintext.search(";");
	 	//alert("m " + m);
	 	teacherexplain[0] = explaintext.substring(0, m);
	 	//alert("et0" + teacherexplain[currentTX]);
	 	explaintexttemp = explaintext.substring(m+2);
	 	/*
	 	alert(tee);
	 	var n = explaintexttemp.search(";");
	 	alert("n " + n);
	 	teacherexplain[currentTX + 1] = explaintexttemp.substring(0, n+1);
	 	alert("et1" + teacherexplain[currentTX + 1]);
	 	*/
	 	explaintext = explaintexttemp;
	 	
	 	//alert("explaintext " + explaintext);
	 	
	 	var i=1;
	 	for (i; i < explaintext.search(";"); i++) {
	 		m = explaintext.search(";");
		 	//alert("m " + m);
		 	teacherexplain[i] = explaintext.substring(0, m);
		 	//alert("et" + i + " = " + teacherexplain[i]);
		 	explaintexttemp = explaintext.substring(m+2);
		 	explaintext = explaintexttemp;
		 	currentTXmax = i;
	 	}
	 	
	 		//alert("currentTXmax " + currentTXmax);
	 	
	 	
	 	//var tt = explaintext.substring(0, m+1);
	     //alert(teacherexplain[1]);
	     document.getElementById("explain").style.display = "block";
	     document.getElementById("explain").innerHTML = teacherexplain[0];
	     //speaktext1 = explaintext;
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(teacherexplain[0]));
		 var element = document.getElementById("explain");
		 //setVoice("it is the begining",element);
	     //setVoice(teacherexplain[0],element);
		    if (voices.length != 0)
			 	setVoice(teacherexplain[0],element,0);
			 else
				setVoice(teacherexplain[0],element,1); 
		 document.getElementById("TcherExplnform").style.display = "block";
		 document.getElementById("explain").style.display = "block";
	     document.getElementById("previousTEInfo").style.display = "block";
	     document.getElementById("nextTEInfo").style.display = "block";
	     
		 document.getElementById("TcherExpln1form").style.display = "none";
		 document.getElementById("explain1").style.display = "none";
	     document.getElementById("previousTEInfo1").style.display = "none";
	     document.getElementById("nextTEInfo1").style.display = "none";
	     
	     document.getElementById("moreTE").style.display = "none";
	     document.getElementById("moreTE1").style.display = "none";
	     document.getElementById("BooksQuestion").style.display = "none";
		document.getElementById("explainQXcont").style.display = 'none';
	     
	     //document.getElementById("questionset").innerHTML = "";

	     //document.getElementById("explain").innerHTML = tt;
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(tt));
	    }
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
	}
	
		
function nextexplain1(){
	//synth.cancel();
	if(ansflag == 0)
		synth.cancel();
	speakon = 0;
	ansflag = 0;
	
	if(currentTX < currentTXmax) 
	{
	var teacherexplaintempn = teacherexplain[currentTX + 1];
	//alert(teacherexplaintempn);
	document.getElementById("explain1").innerHTML = teacherexplaintempn;
	//speechSynthesis.speak(new SpeechSynthesisUtterance(teacherexplaintempn));
	var element = document.getElementById("explain1");
		//setVoice("",element);
	//setVoice(teacherexplaintempn,element);
    if (voices.length != 0)
	 	setVoice(teacherexplaintempn,element,0);
	 else
		setVoice(teacherexplaintempn,element,1);
	currentTX++;
	}
	else
		{
		document.getElementById("explain1").innerHTML = "it is the end";
		//speechSynthesis.speak(new SpeechSynthesisUtterance(""));
		var element = document.getElementById("explain1");
		//setVoice("",element);
	    if (voices.length != 0)
		 	setVoice("it is the end",element,0);
		 else
			setVoice("it is the end",element,1);
		currentTX = currentTXmax + 1;
		}
			
}

function previousexplain1(){
	//synth.cancel();
	if(ansflag == 0)
		synth.cancel();
	speakon = 0;
	ansflag = 0;
	
	if(currentTX > 0){
	var teacherexplaintempp = teacherexplain[currentTX - 1];
	//alert(teacherexplaintempp);
	document.getElementById("explain1").innerHTML = teacherexplaintempp;
	//speechSynthesis.speak(new SpeechSynthesisUtterance(teacherexplaintempp));
	var element = document.getElementById("explain1");
		//setVoice("",element);
	//setVoice(teacherexplaintempp,element);
    if (voices.length != 0)
	 	setVoice(teacherexplaintempp,element,0);
	 else
		setVoice(teacherexplaintempp,element,1);
	currentTX--;
	}
	else
		{
		document.getElementById("explain1").innerHTML = "it is the begining";
		//speechSynthesis.speak(new SpeechSynthesisUtterance("it is the begining"));
		var element = document.getElementById("explain1");
		//setVoice("it is the begining",element);
	    if (voices.length != 0)
		 	setVoice("it is the begining",element,0);
		 else
			setVoice("it is the begining",element,1);
		currentTX = -1;
		}
}
	
	function startexplain1() {
	synth.cancel();
	speakon = 0;
	ansflag = 0;
	questionexplainflag = 0;
	  var xhttp = new XMLHttpRequest();
	  var explaintext;
	  var explaintexttemp;
	  currentTX = 0;
	  questionon = 0;
	  currentTXmax = 1;
	  teacherexplain = [];
	  //var explaintext1;
	  //var url="studycontent.jsp";
	  //var url="studycontent.jsp?para=1";
//alert("explaintext in");

//alert("grade " + grade);

//alert("week " + week);

//alert("questionon " + questionon);

//alert("userid " + userid);


//alert("answercount[currentQX][0] " + answercount[currentQX][0]);
	  //var url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=0&questionnum=0&correctans=0&coin=0&userid=" + userid;
	  //var url="teacherexplain.jsp?questionon=" + questionon + "&answeron=0&questionnum=0&correctans=0&coin=0&userid=" + userid + "&grade=" + grade + "&week=" + week;
      var url="grammarexplain.jsp?grammar1=Y&userid=" + userid + "&grade=" + grade + "&week=" + week;
	  //var url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=0&answeron=0&questionnum=0&correctans=0&coin=0&userid=" + userid;
		 
	  //var url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=0&questionnum=0&answercount=" + answercount[currentQX][0] + "&correctans=0&coin=0&userid=" + userid;
	 
	  //var url="";
	 //alert("url " + url);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     //document.getElementById("amit").innerHTML = this.responseText;	
	     //document.getElementById("explain").innerHTML = this.responseText;
	     explaintext = this.responseText;
	     //alert("explaintext " + explaintext);
	     
	     if(this.responseText == "")
	     {
			 document.getElementById("explain1").innerHTML = "it is the end";
	     //alert("TcherExpln1form 1");
	     //speaktext1 = explaintext;
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(teacherexplain[0]));
		 var element = document.getElementById("explain1");
		 //setVoice("it is the begining",element);
	     //setVoice(teacherexplain1[0],element);
		    if (voices.length != 0)		       
		 	setVoice("it is the end",element,0);
			 else    
		 	setVoice("it is the end",element,0);	
		 }
	     else
	     {
			 	     //var loadinfovaluein = explaintext;
	 	//alert("oo " + explaintext);
	 	//var mword = ";";
	 	var m = explaintext.search(";");
	 	//alert("m " + m);
	 	//var mn = explaintext.search(",");
	 	//alert("mn " + mn);
	 	teacherexplain[0] = explaintext.substring(0, m);
	 	//alert("et0" + teacherexplain[currentTX]);
	 	explaintexttemp = explaintext.substring(m+2);
	 	/*
	 	alert(tee);
	 	var n = explaintexttemp.search(";");
	 	alert("n " + n);
	 	teacherexplain[currentTX + 1] = explaintexttemp.substring(0, n+1);
	 	alert("et1" + teacherexplain[currentTX + 1]);
	 	*/
	 	explaintext = explaintexttemp;
	 	
	 	
	     //alert("explaintext " + explaintext);
	 	
	 	var i=1;
	 	for (i; i < explaintext.search(";"); i++) {
	 		m = explaintext.search(";");
		 	//alert("m " + m);
		 	teacherexplain[i] = explaintext.substring(0, m);
		 	//alert("et" + i + " = " + teacherexplain[i]);
		 	explaintexttemp = explaintext.substring(m+2);
		 	explaintext = explaintexttemp;
		 	currentTXmax = i;
	 	}
	 	
	 		
	 	
	 	
	 	//var tt = explaintext.substring(0, m+1);
	     //alert(teacherexplain[1]); 
	     //alert("TcherExpln1form ");
	     /*
	     document.getElementById("TcherExpln1form").style.display = "block";
	     document.getElementById("explain1").style.display = "block"; 
	     document.getElementById("TcherExplnform").style.display = "none";
	     */
		
	     document.getElementById("explain1").innerHTML = teacherexplain[0];
	     //alert("TcherExpln1form 1");
	     //speaktext1 = explaintext;
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(teacherexplain[0]));
		 var element = document.getElementById("explain1");
		 //setVoice("it is the begining",element);
	     //setVoice(teacherexplain1[0],element);
		    if (voices.length != 0)
			 	setVoice(teacherexplain[0],element,0);
			 else
				setVoice(teacherexplain[0],element,1);				
				
		 }
	     document.getElementById("TcherExpln1form").style.display = "block";
	     document.getElementById("explain1").style.display = "block";	 
	     document.getElementById("TcherExplnform").style.display = "none";
	     document.getElementById("moreTE1").style.display = "none";
	     document.getElementById("previousTEInfo1").style.display = "block";
	     document.getElementById("nextTEInfo1").style.display = "block";
	     document.getElementById("BooksQuestion").style.display = "none";
		document.getElementById("explainQXcont").style.display = 'none';
	     
	     //document.getElementById("questionset").innerHTML = "";

	     //document.getElementById("explain").innerHTML = tt;
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(tt));
	    }
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
	}




function loadInfo(){
	synth.cancel();
	//speakon = 0;
	//ansflag = 0;
	//alert("loadInfo ");
	var meaningarrayin = document.getElementById("moreanswer").innerHTML;
	var loadinfovalue = meaningarrayin;
	var n = meaningarrayin.search("/");

	//alert("loadInfo 1" + loadinfovalue);
	//alert("n is " + n);
	//if(loadinfovalue == "")
	if(loadinfovalue == "" || loadinfovalue == null)
		{
		document.getElementById("answer").innerHTML = "no more information";
	    //speechSynthesis.speak(new SpeechSynthesisUtterance("no more information"));
		 var element = document.getElementById("answer");
		 //setVoice("it is the begining",element);
	     //setVoice("no more information",element);
		    if (voices.length != 0)
			 	setVoiceans("no more information",element,0);
			 else
				setVoiceans("no more information",element,1);
		}
	else if(n == -1)
		{
		//var loadinfovalue = meaningarrayin;
		//alert("loadInfo-1 " + loadinfovalue);
		//meaningarray = wordarray[0].split("/");
    	//alert("meaningarray1 ");
    	document.getElementById("answer").innerHTML = loadinfovalue;
    	//speechSynthesis.speak(new SpeechSynthesisUtterance(loadinfovalue));    	
    	document.getElementById("moreanswer").innerHTML="";
		 var element = document.getElementById("answer");
	     //setVoice(loadinfovalue,element);
    	document.getElementById("moreanswer").innerHTML="";
		    if (voices.length != 0)
			 	setVoiceans(loadinfovalue,element,0);
			 else
				setVoiceans(loadinfovalue,element,1);
    	//document.getElementById("moreanswer").innerHTML="";
		}
	else
		{
		var loadinfovalue1 = loadinfovalue.substring(0, n);
		//alert("loadInfo1 " + loadinfovalue1);
		//meaningarray = wordarray[0].split("/");
    	//alert("meaningarray1 ");
    	document.getElementById("answer").innerHTML = loadinfovalue1;
    	//speechSynthesis.speak(new SpeechSynthesisUtterance(loadinfovalue1));
		 var element = document.getElementById("answer");
	     //setVoice(loadinfovalue,element);
    	//setVoice(loadinfovalue1,element);
    	document.getElementById("moreanswer").innerHTML = loadinfovalue.substring(n+1);
	    if (voices.length != 0)
		 	setVoiceans(loadinfovalue1,element,0);
		 else
			setVoiceans(loadinfovalue1,element,1);
    	//var tempmeaning = meaningarrayin.substring(n+1);
    	//document.getElementById("moreanswer").innerHTML = loadinfovalue.substring(n+1);
		}	
	
}


function loadword(){
	synth.cancel();
	//speakon = 0;
	//ansflag = 0;
	//alert("loadInfo ");
	var meaningarrayin = document.getElementById("moreword").innerHTML;
	//alert("loadInfo " + meaningarrayin);
	var loadinfovaluein = meaningarrayin;
	//alert("oo " + meaningarrayin);
	var m = meaningarrayin.search(";");
	//alert("m " + m);
	var loadinfovalueinfirst =  loadinfovaluein.substring(0,m);
	//alert("loadinfovalueinfirst " + loadinfovalueinfirst);
	var loadinfovaluein2 = loadinfovaluein.substring(m+1);
	//alert("loadinfovaluein2 " + loadinfovaluein2);
	var loadinfovalue = loadinfovaluein2;
	var n = loadinfovaluein2.search(";");
	//alert("loadword n " + n);
	//alert("loadword loadinfovalue " + loadinfovalue);
	//if(loadinfovalue == "")
	if(loadinfovalue == "" || loadinfovalue == null || onewordflag == 1)
		{
		//alert("first in  1");
		document.getElementById("answer").innerHTML = "no more word";
	    //speechSynthesis.speak(new SpeechSynthesisUtterance("no more word"));
		 var element = document.getElementById("answer");
	     //setVoice(loadinfovalue,element);
	    //setVoice("no more word",element);
	   	//onewordflag = 0;
	    document.getElementById("copyspeech").value = "";
	    if (voices.length != 0)
		 	setVoiceans("no more word",element,0);
		 else
			setVoiceans("no more word",element,1);
	    
	    //alert("no more word in ")
	    //onewordflag = 0;
	    //document.getElementById("copyspeech").value = "";
	    //document.getElementById("copyspeech").innerHTML = "";
		//document.getElementById('final_span').value = "";
		}
	else if(loadinfovalueinfirst != null && loadinfovalueinfirst != "")
		{
		//alert("first in  ");
		//document.getElementById("answer").innerHTML = loadinfovalueinfirst;
		document.getElementById("moreword").innerHTML = loadinfovalue;
		//document.getElementById("moreanswer").innerHTML = loadinfovalue1;
		var element = document.getElementById("answer");
		var l = loadinfovalueinfirst.search("/");
		if(l != -1){
			var loadinfovalueinfirst1 = loadinfovalueinfirst;
			loadinfovalueinfirst = loadinfovalueinfirst1.substring(0,l);//voice is on string var loadinfovalueinfirst
    		document.getElementById("moreanswer").innerHTML = loadinfovalueinfirst1.substring(l+1);
    		document.getElementById("answer").innerHTML = loadinfovalueinfirst;
    		//alert("first in  " + loadinfovalueinfirst);
    		//loadinfovalueinfirst = loadinfovalue1;
		}
		else 
			document.getElementById("answer").innerHTML = loadinfovalueinfirst;
	     //setVoice(loadinfovalue,element);
	    //setVoice("no more word",element);
	    //alert("first in  " + loadinfovalueinfirst);
	    if (voices.length != 0)
		 	setVoiceans(loadinfovalueinfirst,element,0);
		 else
			setVoiceans(loadinfovalueinfirst,element,1);	
	    //loadInfo();
	}else if(n == -1)
		{
		//alert("first in  3");
		//var loadinfovalue = meaningarrayin;
		//alert("loadInfo-1 " + loadinfovalue);
		//meaningarray = wordarray[0].split("/");
    	//alert("meaningarray1 ");
    	document.getElementById("moreanswer").innerHTML = loadinfovalue;
    	document.getElementById("moreword").innerHTML="";
    	loadInfo();
		//var loadinfovalue = meaningarrayin;
		/*
		alert("loadinfovaluein " + loadinfovaluein);
		//meaningarray = wordarray[0].split("/");
    	//alert("meaningarray1 ");
		var l = loadinfovaluein.search("/");
		//
		/*
		var loadinfovalueinfirst1 = loadinfovalueinfirst;
		loadinfovalueinfirst = loadinfovalueinfirst1.substring(0,k);
		var l = (loadinfovalueinfirst1.substring(0,k)).search("/");//voice is on string var loadinfovalueinfirst
		*/
		//
		//alert("first in  ");
		/*
		if(l != -1){
			//var loadinfovalueinfirst1 = loadinfovalueinfirst;
			//loadinfovalueinfirst = loadinfovalueinfirst1.substring(0,l);//voice is on string var loadinfovalueinfirst
    		//document.getElementById("moreanswer").innerHTML = loadinfovalueinfirst1.substring(l+1);
    		document.getElementById("moreanswer").innerHTML = loadinfovaluein.substring(l+1);
    		document.getElementById("answer").innerHTML = loadinfovaluein.substring(0,l);
    		//alert("first in  " + loadinfovalueinfirst);
    		//loadinfovalueinfirst = loadinfovalue1;
		}
		else {
			document.getElementById("answer").innerHTML = loadinfovaluein;
			document.getElementById("moreanswer").innerHTML = "";
		}
    	//document.getElementById("moreanswer").innerHTML = loadinfovalue;
    	//document.getElementById("moreword").innerHTML="";
    	loadInfo();
    	
    	*/
		}
	else
		{
		//alert("first in  4");
		var loadinfovalue1 = loadinfovalue.substring(0, n);
		//alert("loadInfo1 " + loadinfovalue);
		//meaningarray = wordarray[0].split("/");
    	//alert("meaningarray1 ");
    	document.getElementById("moreanswer").innerHTML = loadinfovalue1;
    	//speechSynthesis.speak(new SpeechSynthesisUtterance(loadinfovalue1));
    	//var tempmeaning = meaningarrayin.substring(n+1);
    	document.getElementById("moreword").innerHTML = loadinfovalue;
    	loadInfo();
		}

	//document.getElementById("copyspeech").innerHTML = "";
	//document.getElementById('final_span').innerHTML = "";
}

function speak() {
	synth.cancel();
	//speakon = 0;	
	ansflag = 1;
	var textarea = document.getElementById('final_span');
	//alert(textarea);
	var textvalue = textarea.innerHTML;
	//var textvalue = textarea.getAttribute('value');
		//alert(textvalue);
	//speechSynthesis.speak(new SpeechSynthesisUtterance(speaktext));
	//textvalue = textvalue1 + "&grade=3&week=1";
	 var xhttp = new XMLHttpRequest();
	  //
	  //var url="speak.jsp?text=test is here&grade=3&week=1";
	  //var url="speak.jsp?text=" + textvalue + "&grade=3&week=1";
	  var url="speak.jsp?text=" + textvalue + "&grade=" + grade + "&week=" + week + "&userid=" + userid;
	  //var url="";
	  //alert(url);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	teacheranswerwhole = this.responseText;
		     wordarray = teacheranswerwhole.split(";");
		     //alert("wordarray " + wordarray[0]);
		     meaningarray = wordarray[0].split("/");
		     //alert("meaningarray " + meaningarray[0]);
		     //document.getElementById("moreanswer").innerHTML = meaningarray;
		     document.getElementById("answer").innerHTML = meaningarray[0];
		     //speechSynthesis.speak(new SpeechSynthesisUtterance(meaningarray[0]));
		 	 var element = document.getElementById("answer");
	     	 //setVoice(loadinfovalue,element);
		     //setVoice(meaningarray[0],element);
			    
		     var n = wordarray[0].search("/");
		     if(n == -1)
		     	 document.getElementById("moreanswer").innerHTML = "";
		     else
			     document.getElementById("moreanswer").innerHTML = wordarray[0].substring(n+1);
		     var m = teacheranswerwhole.search(";");
		     document.getElementById("moreword").innerHTML = teacheranswerwhole.substring(m+1);
		     if(wordarray[1] == "" || wordarray[1] == null || wordarray[1] == undefined)
		    	 onewordflag = 1;
		     else
		    	 onewordflag = 0;
		     if (voices.length != 0)
			    	setVoiceans(meaningarray[0],element,0);
				 else
					setVoiceans(meaningarray[0],element,2);
		     //document.getElementById("answer").innerHTML = this.responseText;	     
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(this.responseText));
	     
	     //document.getElementById("email_button").style.display = "none";
	     
	     //speaktext = this.responseText;
	     //alert(speaktext);
	     //speaktext1 = speaktext;
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(this.responseText));
	    }
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
	//const utterance = new SpeechSynthesisUtterance(speaktext);
      //speechSynthesis.speak(utterance);
  //speechSynthesis.speak(new SpeechSynthesisUtterance(textvalue));

//document.getElementById("demo").innerHTML = this.responseText;
  //speechSynthesis.speak(new SpeechSynthesisUtterance(document.getElementById("amit").innerHTML));
}
//document.getElementById("demo").innerHTML = this.responseText;
//speak();

function changepw1(){
	//alert("changepw in");	
	document.getElementById("pwchangeddiv").style.display = "block";
	document.getElementById("changepw").style.display = "none";	
}


///*
function pwchanged1(){
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
    	document.getElementById("changepw").style.display = "block";
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
    	
    }
  };
  
  xhttp.open("GET", url, true);
	 xhttp.send();

 }//if(n != 0)
	
}

function on() {
	//alert("on is in");
  document.getElementById("overlay").style.display = "block";
  document.getElementById("mycanvas").style.visibility = "visible";
  setTimeout(off, 5000);

  //init();
}

function off() {
  document.getElementById("overlay").style.display = "none";
  document.getElementById("mycanvas").style.visibility = "hidden";
  document.getElementById("mycanvas").height = 10;
}


function speakcopy() {
	//var textarea = document.getElementById('final_span');
	//alert(textarea);
	//var textvalue = textarea.innerHTML;
	synth.cancel();
	//speakon = 0;
	ansflag = 1;
	var textvalue = document.getElementById("copyspeech").value;

	//var textvalue = document.getElementById("copyspeech").innerHTML;
	//var textvalue = textarea.getAttribute('value');
		//alert(textvalue);
	//speechSynthesis.speak(new SpeechSynthesisUtterance(speaktext));
	
	 var xhttp = new XMLHttpRequest();
	  //var url="studycontent.jsp";
	  //var url="speak.jsp?text=" + textvalue;
	  document.getElementById("answer").innerHTML = "";
	  document.getElementById("moreanswer").innerHTML = "";
	  document.getElementById("moreword").innerHTML = "";
	  var url="speak.jsp?text=" + textvalue + "&grade=" + grade + "&week=" + week + "&userid=" + userid;
	  //var url="";
	  //alert(url);
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	teacheranswerwhole = this.responseText;
	    	//alert("word " + teacheranswerwhole);
		     wordarray = teacheranswerwhole.split(";");
		     //alert("wordarray " + wordarray[0]);
		     meaningarray = wordarray[0].split("/");
		     //alert("meaningarray " + meaningarray[0]);
		     //document.getElementById("moreanswer").innerHTML = meaningarray;
		     document.getElementById("answer").innerHTML = meaningarray[0];
		     //speechSynthesis.speak(new SpeechSynthesisUtterance(meaningarray[0]));
		 	 var element = document.getElementById("answer");
	     	 //setVoice(loadinfovalue,element);
		     //setVoice(meaningarray[0],element);
		     //element.focus();
		     //element.click();
		     //alert("element " + element);
			    
		     var n = wordarray[0].search("/");
		     //alert("n " + n);
		     if(n == -1)
		     	 document.getElementById("moreanswer").innerHTML = "";
		     else
			     document.getElementById("moreanswer").innerHTML = wordarray[0].substring(n+1);
		     var m = teacheranswerwhole.search(";");
		     document.getElementById("moreword").innerHTML = teacheranswerwhole.substring(m+1);
		     //alert("moreword" + teacheranswerwhole.substring(m+1));
		     if(wordarray[1] == "" || wordarray[1] == null || wordarray[1] == undefined)
		    	 onewordflag = 1;
		     else
		    	 onewordflag = 0;
		     
		     if (voices.length != 0)
				 	setVoiceans(meaningarray[0],element,0);
				 else
					setVoiceans(meaningarray[0],element,2);
		     //document.getElementById("answer").style.display = "block";
	     //document.getElementById("moreanswer").innerHTML = this.responseText;	
	     //document.getElementById("answer").innerHTML = this.responseText;
	     //speaktext = this.responseText;
	     //alert(speaktext);
	     //speaktext1 = speaktext;
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(this.responseText));
	    }
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
	//const utterance = new SpeechSynthesisUtterance(speaktext);
      //speechSynthesis.speak(utterance);
  //speechSynthesis.speak(new SpeechSynthesisUtterance(textvalue));

//document.getElementById("demo").innerHTML = this.responseText;
  //speechSynthesis.speak(new SpeechSynthesisUtterance(document.getElementById("amit").innerHTML));
}


function questionexplain(){
	synth.cancel();
	speakon = 0;
	ansflag = 0;
	//synth.cancel();
	//speakon = 0;
	var xhttp = new XMLHttpRequest();
	var url;
	  //var url="studycontent.jsp";
	  //var url="studycontent.jsp?para=1";
	  QA = 0;
	  //if(document.getElementById("").checked || document.getElementById("").checked )
//alert("in" + currentQX);
		  if(document.getElementById("Q1").checked)
			  QA = 1;
		  if(document.getElementById("Q2").checked)
			  QA = 2;
		  if(document.getElementById("Q3").checked)
			  QA = 3;
		  if(document.getElementById("Q4").checked)
			  QA = 4;
		  /*
		  var left = (screen.width/2)-(w/2);
		  var top = (screen.height/2)-(h/2);
		 window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
*/
///*
		 //if(answeron == 0){
			 
		  if(QA == 0)
		  {
			  alert("Please select an answer");
			  //window.open(url, "Please select an answer"., 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
			  
				  document.getElementById("overlay").style.display = "none";
				  document.getElementById("mycanvas").style.visibility = "hidden";
				  document.getElementById("mycanvas").height = 10;
			  return;
		  }
		  else
		  {
			  //alert("currentQX " + currentQX);
			  if(QA == questionans[currentQX])
			  { 
				  alert("correct answer.");
				  //alert("bef answercount[currentQX][0]" + answercount[currentQX][0]);
				  if(answercount[currentQX][0] < 9)
				  	answercount[currentQX][0] = parseInt(answercount[currentQX][0]) + 1;
				  //alert("aft answercount[currentQX][0]" + answercount[currentQX][0]);
				  answeron =1;
				  //queexpflag = 1;
				  document.getElementById("overlay").style.display = "block";
				  document.getElementById("explain").style.display = "block";
				  var answertemp = answercount[currentQX][0];
				  //on();
				  var coin;
			  //on();
			  if( answercount[currentQX][1] == 0){
				  answercount[currentQX][1] = 1;
			  	if(answertemp == 1)	{			  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 10 coins!!!!!";
			  		coin = 10;
			  	}else if(answertemp == 2){			  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 5 coins!!!!!";
			  		coin = 5;
			  	}else if(answertemp == 3){				  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 3 coins!!!!!";
			  	coin = 3;
			  	}else if(answertemp > 3){			  
			  		document.getElementById("text").innerHTML = "Sorry!! You do not win any coin!!!!!";
   				    document.getElementById("mycanvas").style.visibility = "hidden";
			  		coin = 0;
		  		}
		  	   }
			  else{
				document.getElementById("mycanvas").style.visibility = "hidden";
			  	document.getElementById("text").innerHTML = "Sorry!! You already answered the question!!!!!";  
				coin = 0;
			  } 
				  //url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=1&coin=" + coin + "&userid=" + userid;
				  url="teacherexplain.jsp?questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=1&coin=" + coin + "&userid=" + userid+ "&grade=" + grade + "&week=" + week;
		//alert("url " + url)			  
		//url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=" + answercount[currentQX][1];
			  }
			  else
			  {
				  alert("wrong answer. Please select another answer.");
				  //alert("bef answercount[currentQX][0]" + answercount[currentQX][0]);
				  //answercount[currentQX][0] = answercount[currentQX][0] +1;
				  if(answercount[currentQX][0] < 9)
				  	answercount[currentQX][0] = parseInt(answercount[currentQX][0]) + 1;
				  //alert("aft answercount[currentQX][0]" + answercount[currentQX][0]);
				  answeron =1;
				  //queexpflag =1;
				  //document.getElementById("text").innerHTML = "Congratulation!! You win 5 conis!!!!!";
				  document.getElementById("overlay").style.display = "none";
				  document.getElementById("mycanvas").style.visibility = "hidden";
				  document.getElementById("explain").style.display = "none";
				  document.getElementById("mycanvas").height = 10;
				  //url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=0&coin=0&userid=" + userid;
				  url="teacherexplain.jsp?questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=0&coin=0&userid=" + userid+ "&grade=" + grade + "&week=" + week;
		//alert("url " + url)		  
		//return;
			  }
		  }
		 /* 
		 } else
			{
			    //answeron =1;
	  			url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=0";
			}
			*/
			//*/
	  //var url="";
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     //document.getElementById("amit").innerHTML = this.responseText;	
	     //document.getElementById("demo").innerHTML = this.responseText;
	 	
	     //document.getElementById("demo").innerHTML += "<div>	Read 'The Secret' and answer the questions that follow.</div><div>&nbsp;</div><div>	1. One morning Janie Rose woke up to a big crash in her room. She opened her eyes slowly, scared of what she might find.</div><div>	&nbsp;</div><div>	2. She saw her unpacked boxes from the move scattered on the floor. Janie screamed in her pillow, 'I wish I never had to move!'</div><div>	&nbsp;</div><div>	3. Janie’s mom got a new job which moved Janie from her school, soccer team, and friends.</div><div>	&nbsp;</div><div>	4. Janie got out of bed and went downstairs to fix a bowl of cereal for breakfast. Her mom was already down there and told Janie, good morning. Janie mumbled something that sounded like a good morning.</div><div>	&nbsp;</div><div>	5. Her mom sat down beside Janie and tried to make her feel better by telling her that she would meet new friends and to give this town a chance. Janie just rolled her eyes, and slurped the milk up from her cereal bowl.</div><div>	&nbsp;</div><div>	6. Janie then got up and went back to her room. She crawled back in bed and pulled her covers up to her chin. She stared at the window, and the old chest underneath it. The chest was there when they moved in.</div><div>	&nbsp;</div><div>	<div>		7. Janie got out of bed and walked over to see what was in it. She opened it up to find many neat treasures. She found dolls, hair bows, colors, and a rolled up piece of paper.</div>	<div>		&nbsp;</div>	<div>		8. She opened up the paper to find that it was a map. The map title read, The Secret of Blue Ridge.</div>	<div>		&nbsp;</div>	<div>		'Hmmm, that is the name of this town,' Janie said.</div>	<div>		&nbsp;</div>	<div>		9. As Janie studied the map she realized that this big secret was at Blue Ridge Library. Janie got herself ready and ran down the stairs with the map.</div>	<div>		&nbsp;</div>	<div>		10. She grabbed an apple and told her mom she was headed to the library as she was running out the door. Janie’s mom yelled, 'You don’t even know where the library is!'</div>	<div>		Janie yelled, 'I have a map!'</div>	<div>		&nbsp;</div>	<div>		11. Janie hopped on her bike and looked at the compass rose. The compass rose showed that the library was north of her house.</div>	<div>		&nbsp;</div>	<div>		12. Janie then compared the map symbols to the map key. The map symbols showed Janie that she would pass Bert’s Grocery, Amelia’s Flowers, and Carl’s Cars.</div>	<div>		&nbsp;</div>	<div>		13. As Janie followed the map she saw a soccer field with a group of girls playing on it. She stopped for a moment and just watched them.</div>	<div>		&nbsp;</div>	<div>		14. One of the girls ran up to Janie and asked her if she wanted to play. Janie gave a slight smile and said, 'Maybe, but I have to take care of something first.'</div>	<div>		&nbsp;</div>	<div>		15. She rode all the way to the library and parked her bike. She went into the library and read the clue on the back of the map.</div>	<div>		&nbsp;</div>	<div>		16. The clue read, look to the stars. 'Look to the stars,' Janie said while looking up.</div>	<div>	&nbsp;</div>	<div>		17. Then Janie saw a quote etched on the ceiling. 'Make today, better than yesterday, and tomorrow better than today.' Janie thought for a moment and realized that is the secret./div><div>&nbsp;</div><div>		18. She smiled and said, 'Now I think I have a soccer game to play.'</div>";

	     var questiontext = this.responseText;
	     
	     //alert("questiontext " + questiontext);
	     /*
	     var n = questiontext.search(";");
		 	//alert("n " + n);
		 	var anscount = questiontext.substring(0, n);
		 	questiontext = questiontext.substring(n+1);
		 	//alert("questiontext1 " + questiontext);	
		 	
	     var anscounttemp;
	     var anscontcor;
	     var anscontcortemp;
	     var j=0;
	     var l;
		 	for (j; 0 < anscount.search("/"); j++) {
		 		answercount[j]=new Array(4);
		 		l = anscount.search("/");
			 	//alert("m " + m);
			 	anscontcor = anscount.substring(0, l);
			 	var k = 0;
			 	var p;
			 	for (k; 0 < anscontcor.search(","); k++) {
			 		p = anscontcor.search(",");
				 	//alert("m " + m);
				 	answercount[j][k] = anscontcor.substring(0, p);
				 	//alert("answercount[j][k] " + answercount[j][k]);
				 	//alert("et" + i + " = " + teacherexplain[i]);
				 	anscontcortemp = anscontcor.substring(p+1);
				 	anscontcor = anscontcortemp;
				 	//alert("anscontcor " + anscontcor);
				 	//currentTXmax = i;
			 	}
			 	
			 	
			 	//alert("et" + i + " = " + teacherexplain[i]);
			 	anscounttemp = anscount.substring(l+1);
			 	anscount = anscounttemp;
			 	//alert("anscount " + anscount);	
			 	//currentTXmax = i;
		 	}
		 */	
	if(queexpflag == 0){
	     var questiontexttemp;
	     var i=0;
	     var m;
		 	for (i; i < questiontext.search("/"); i++) {
		 		m = questiontext.search("/");
			 	//alert("m " + m);
			 	questionexplain[i] = questiontext.substring(0, m);
			 	//alert("questionexplain[i]"+ questionexplain[i]);
			 	questiontexttemp = questiontext.substring(m+1);
			 	questiontext = questiontexttemp;
			 	//currentTXmax = i;
		 	}
		 	//alert("queexpflag " + queexpflag);
		 	queexpflag = 1;
	}
	     document.getElementById("moreTE").style.display = "none";
	     document.getElementById("moreTE1").style.display = "none";
	     document.getElementById("previousTEInfo").style.display = "none";
	     document.getElementById("nextTEInfo").style.display = "none";
	     //alert("questionexplain[currentQX]" + questionexplain[currentQX]);
	     //alert("questionans[currentQX]" + questionans[currentQX]);
	     if(QA == questionans[currentQX])
	     {
	    	 //alert("questionexplain[currentQX] " + questionexplain[currentQX]);
	    	 var quesexpcontent = questionexplain[currentQX];
	    	 //alert("quesexpcontent " + quesexpcontent);
	    	 var quesexpcontstr="";
	    	 //alert("quesexpcontent.search " + quesexpcontent.search(";"));
	 	    var m;
	 	   //* 
	 	    var l = 0;
	 	   //for(l; )
	 	 	for (l; 0 < quesexpcontent.search(";"); l++) {
	 		 		var o = quesexpcontent.search(";");
	 			 	//alert("m " + m);
	 			 	var quesexpcont = quesexpcontent.substring(0, o);
	 			 	var newLine = "\r\n";
	 			 	//alert("quesexpcont"+ quesexpcont);
	 			 	//quesexpcontentarray[i] = quesexpcont;
	 			 	//quesexpcontstr = quesexpcontstr + "<div>"  + quesexpcont + "</div><div>&nbsp;</div>";
	 			 	///*
	 			 	quesexpcontstr = quesexpcontstr + quesexpcont + newLine;
	 			 	var quesexpcontenttemp = quesexpcontent.substring(o+1);
	 			 	quesexpcontent = quesexpcontenttemp;
	 			 	quesexpcontentcntmax = l;
	 			 	//*/
	 			 	//currentTXmax = i;
	 		 	}//for (i; 0 < quesexpcontent.search("\"); i++)
	    	 
	    	// */
	    	 
	    	 
	 		 	//alert("quesexpcontstr " + quesexpcontstr);
	    	 
	     	document.getElementById("explain").innerHTML += quesexpcontstr;
	     //speaktext1 = speaktext;
		 	var element = document.getElementById("explain");
		 //setVoice(speaktext,element);
	     //setVoice(questionexplain[currentQX],element);
	     //alert("voices.length " + voices.length);
	     	if (voices.length != 0)
			 	setVoice(questionexplain[currentQX],element,0);
			 else
				setVoice(questionexplain[currentQX],element,1);
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(questionexplain[currentQX]));
	    	 }
			
	    }
			questionexplainflag = 1;
			document.getElementById("explainQX").style.display = 'none';
			document.getElementById("explainQXcont").style.display = 'block';
	  };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
	
	
}


function questionexplaincontinue(){
	synth.cancel();
	speakon = 0;
	ansflag = 0;
	//synth.cancel();
	//speakon = 0;
	/*
	var xhttp = new XMLHttpRequest();
	var url;
	*/
	  //var url="studycontent.jsp";
	  //var url="studycontent.jsp?para=1";
	  QA = 0;
	  //if(document.getElementById("").checked || document.getElementById("").checked )
//alert("in" + currentQX);
		  if(document.getElementById("Q1").checked)
			  QA = 1;
		  if(document.getElementById("Q2").checked)
			  QA = 2;
		  if(document.getElementById("Q3").checked)
			  QA = 3;
		  if(document.getElementById("Q4").checked)
			  QA = 4;
		  /*
		  var left = (screen.width/2)-(w/2);
		  var top = (screen.height/2)-(h/2);
		 window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
*/
///*
		 //if(answeron == 0){
			 
		  if(QA == 0)
		  {
			  alert("Please select an answer");
			  //window.open(url, "Please select an answer"., 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
			  
				  document.getElementById("overlay").style.display = "none";
				  document.getElementById("mycanvas").style.visibility = "hidden";
				  document.getElementById("mycanvas").height = 10;
			  return;
		  }
		  else
		  {
			  //alert("currentQX " + currentQX);
			  if(QA == questionans[currentQX])
			  { 
				  alert("correct answer.");
				  //alert("bef answercount[currentQX][0]" + answercount[currentQX][0]);
				  if(answercount[currentQX][0] < 9)
				  	answercount[currentQX][0] = parseInt(answercount[currentQX][0]) + 1;
				  //alert("aft answercount[currentQX][0]" + answercount[currentQX][0]);
				  answeron =1;
				  //queexpflag = 1;
				  document.getElementById("overlay").style.display = "block";
				  document.getElementById("explain").style.display = "block";
				  var answertemp = answercount[currentQX][0];
				  //on();
				  var coin;
			  //on();
			  if( answercount[currentQX][1] == 0){
				  answercount[currentQX][1] = 1;
			  	if(answertemp == 1)	{			  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 10 coins!!!!!";
			  		coin = 10;
			  	}else if(answertemp == 2){			  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 5 coins!!!!!";
			  		coin = 5;
			  	}else if(answertemp == 3){				  
			  		document.getElementById("text").innerHTML = "Congratulation!! You win 3 coins!!!!!";
			  	coin = 3;
			  	}else if(answertemp > 3){			  
			  		document.getElementById("text").innerHTML = "Sorry!! You do not win any coin!!!!!";
   				    document.getElementById("mycanvas").style.visibility = "hidden";
			  		coin = 0;
		  		}
		  	   }
			  else{
				document.getElementById("mycanvas").style.visibility = "hidden";
			  	document.getElementById("text").innerHTML = "Sorry!! You already answered the question!!!!!";  
				coin = 0;
			  } 
				  //url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=1&coin=" + coin + "&userid=" + userid;
				  //url="teacherexplain.jsp?questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=1&coin=" + coin + "&userid=" + userid+ "&grade=" + grade + "&week=" + week;
		//alert("url " + url)			  
		//url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=" + answercount[currentQX][1];
			  }
			  else
			  {
				  alert("wrong answer. Please select another answer.");
				  //alert("bef answercount[currentQX][0]" + answercount[currentQX][0]);
				  //answercount[currentQX][0] = answercount[currentQX][0] +1;
				  if(answercount[currentQX][0] < 9)
				  	answercount[currentQX][0] = parseInt(answercount[currentQX][0]) + 1;
				  //alert("aft answercount[currentQX][0]" + answercount[currentQX][0]);
				  answeron =1;
				  //queexpflag =1;
				  //document.getElementById("text").innerHTML = "Congratulation!! You win 5 conis!!!!!";
				  document.getElementById("overlay").style.display = "none";
				  document.getElementById("mycanvas").style.visibility = "hidden";
				  document.getElementById("explain").style.display = "none";
				  document.getElementById("mycanvas").height = 10;
				  //url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=0&coin=0&userid=" + userid;
				  //url="teacherexplain.jsp?questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=0&coin=0&userid=" + userid+ "&grade=" + grade + "&week=" + week;
		//alert("url " + url)		  
		//return;
			  }
		  }
		 /* 
		 } else
			{
			    //answeron =1;
	  			url="teacherexplain.jsp?grade=" + grade + "&week=" + week + "&questionon=" + questionon + "&answeron=" + answeron + "&questionnum=" + currentQX + "&answercount=" + answercount[currentQX][0] + "&correctans=0";
			}
			*/
			//*/
	  //var url="";
/*
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     //document.getElementById("amit").innerHTML = this.responseText;	
	     //document.getElementById("demo").innerHTML = this.responseText;
	 	
	     //document.getElementById("demo").innerHTML += "<div>	Read 'The Secret' and answer the questions that follow.</div><div>&nbsp;</div><div>	1. One morning Janie Rose woke up to a big crash in her room. She opened her eyes slowly, scared of what she might find.</div><div>	&nbsp;</div><div>	2. She saw her unpacked boxes from the move scattered on the floor. Janie screamed in her pillow, 'I wish I never had to move!'</div><div>	&nbsp;</div><div>	3. Janie’s mom got a new job which moved Janie from her school, soccer team, and friends.</div><div>	&nbsp;</div><div>	4. Janie got out of bed and went downstairs to fix a bowl of cereal for breakfast. Her mom was already down there and told Janie, good morning. Janie mumbled something that sounded like a good morning.</div><div>	&nbsp;</div><div>	5. Her mom sat down beside Janie and tried to make her feel better by telling her that she would meet new friends and to give this town a chance. Janie just rolled her eyes, and slurped the milk up from her cereal bowl.</div><div>	&nbsp;</div><div>	6. Janie then got up and went back to her room. She crawled back in bed and pulled her covers up to her chin. She stared at the window, and the old chest underneath it. The chest was there when they moved in.</div><div>	&nbsp;</div><div>	<div>		7. Janie got out of bed and walked over to see what was in it. She opened it up to find many neat treasures. She found dolls, hair bows, colors, and a rolled up piece of paper.</div>	<div>		&nbsp;</div>	<div>		8. She opened up the paper to find that it was a map. The map title read, The Secret of Blue Ridge.</div>	<div>		&nbsp;</div>	<div>		'Hmmm, that is the name of this town,' Janie said.</div>	<div>		&nbsp;</div>	<div>		9. As Janie studied the map she realized that this big secret was at Blue Ridge Library. Janie got herself ready and ran down the stairs with the map.</div>	<div>		&nbsp;</div>	<div>		10. She grabbed an apple and told her mom she was headed to the library as she was running out the door. Janie’s mom yelled, 'You don’t even know where the library is!'</div>	<div>		Janie yelled, 'I have a map!'</div>	<div>		&nbsp;</div>	<div>		11. Janie hopped on her bike and looked at the compass rose. The compass rose showed that the library was north of her house.</div>	<div>		&nbsp;</div>	<div>		12. Janie then compared the map symbols to the map key. The map symbols showed Janie that she would pass Bert’s Grocery, Amelia’s Flowers, and Carl’s Cars.</div>	<div>		&nbsp;</div>	<div>		13. As Janie followed the map she saw a soccer field with a group of girls playing on it. She stopped for a moment and just watched them.</div>	<div>		&nbsp;</div>	<div>		14. One of the girls ran up to Janie and asked her if she wanted to play. Janie gave a slight smile and said, 'Maybe, but I have to take care of something first.'</div>	<div>		&nbsp;</div>	<div>		15. She rode all the way to the library and parked her bike. She went into the library and read the clue on the back of the map.</div>	<div>		&nbsp;</div>	<div>		16. The clue read, look to the stars. 'Look to the stars,' Janie said while looking up.</div>	<div>	&nbsp;</div>	<div>		17. Then Janie saw a quote etched on the ceiling. 'Make today, better than yesterday, and tomorrow better than today.' Janie thought for a moment and realized that is the secret./div><div>&nbsp;</div><div>		18. She smiled and said, 'Now I think I have a soccer game to play.'</div>";

	     var questiontext = this.responseText;
	     
	     //alert("questiontext " + questiontext);
	     /*
	     var n = questiontext.search(";");
		 	//alert("n " + n);
		 	var anscount = questiontext.substring(0, n);
		 	questiontext = questiontext.substring(n+1);
		 	//alert("questiontext1 " + questiontext);	
		 	
	     var anscounttemp;
	     var anscontcor;
	     var anscontcortemp;
	     var j=0;
	     var l;
		 	for (j; 0 < anscount.search("/"); j++) {
		 		answercount[j]=new Array(4);
		 		l = anscount.search("/");
			 	//alert("m " + m);
			 	anscontcor = anscount.substring(0, l);
			 	var k = 0;
			 	var p;
			 	for (k; 0 < anscontcor.search(","); k++) {
			 		p = anscontcor.search(",");
				 	//alert("m " + m);
				 	answercount[j][k] = anscontcor.substring(0, p);
				 	//alert("answercount[j][k] " + answercount[j][k]);
				 	//alert("et" + i + " = " + teacherexplain[i]);
				 	anscontcortemp = anscontcor.substring(p+1);
				 	anscontcor = anscontcortemp;
				 	//alert("anscontcor " + anscontcor);
				 	//currentTXmax = i;
			 	}
			 	
			 	
			 	//alert("et" + i + " = " + teacherexplain[i]);
			 	anscounttemp = anscount.substring(l+1);
			 	anscount = anscounttemp;
			 	//alert("anscount " + anscount);	
			 	//currentTXmax = i;
		 	}
		 */	
	/*
	if(queexpflag == 0){
	     var questiontexttemp;
	     var i=0;
	     var m;
		 	for (i; i < questiontext.search("/"); i++) {
		 		m = questiontext.search("/");
			 	//alert("m " + m);
			 	questionexplain[i] = questiontext.substring(0, m);
			 	//alert("questionexplain[i]"+ questionexplain[i]);
			 	questiontexttemp = questiontext.substring(m+1);
			 	questiontext = questiontexttemp;
			 	//currentTXmax = i;
		 	}
		 	//alert("queexpflag " + queexpflag);
		 	queexpflag = 1;
	}
	     document.getElementById("moreTE").style.display = "none";
	     document.getElementById("previousTEInfo").style.display = "none";
	     document.getElementById("nextTEInfo").style.display = "none";



*/
	     //alert("questionexplain[currentQX]" + questionexplain[currentQX]);
	     //alert("questionans[currentQX]" + questionans[currentQX]);
	     if(QA == questionans[currentQX])
	     {
	    	 //alert("questionexplain[currentQX] " + questionexplain[currentQX]);
	    	 var quesexpcontent = questionexplain[currentQX];
	    	 //alert("quesexpcontent " + quesexpcontent);
	    	 var quesexpcontstr="";
	    	 //alert("quesexpcontent.search " + quesexpcontent.search(";"));
	 	    var m;
	 	   //* 
	 	    var l = 0;
	 	   //for(l; )
	 	 	for (l; 0 < quesexpcontent.search(";"); l++) {
	 		 		var o = quesexpcontent.search(";");
	 			 	//alert("m " + m);
	 			 	var quesexpcont = quesexpcontent.substring(0, o);
	 			 	var newLine = "\r\n";
	 			 	//alert("quesexpcont"+ quesexpcont);
	 			 	//quesexpcontentarray[i] = quesexpcont;
	 			 	//quesexpcontstr = quesexpcontstr + "<div>"  + quesexpcont + "</div><div>&nbsp;</div>";
	 			 	///*
	 			 	quesexpcontstr = quesexpcontstr + quesexpcont + newLine;
	 			 	var quesexpcontenttemp = quesexpcontent.substring(o+1);
	 			 	quesexpcontent = quesexpcontenttemp;
	 			 	quesexpcontentcntmax = l;
	 			 	//*/
	 			 	//currentTXmax = i;
	 		 	}//for (i; 0 < quesexpcontent.search("\"); i++)
	    	 
	    	// */
	    	 
	    	 
	 		 	//alert("quesexpcontstr " + quesexpcontstr);
	    	 
	     	document.getElementById("explain").innerHTML += quesexpcontstr;
	     //speaktext1 = speaktext;
		 	var element = document.getElementById("explain");
		 //setVoice(speaktext,element);
	     //setVoice(questionexplain[currentQX],element);
	     //alert("voices.length " + voices.length);
	     	if (voices.length != 0)
			 	setVoice(questionexplain[currentQX],element,0);
			 else
				setVoice(questionexplain[currentQX],element,1);
	     //speechSynthesis.speak(new SpeechSynthesisUtterance(questionexplain[currentQX]));
	    	 }
	
	 //   }
	 // };
	  //xhttp.open("GET", "ajax_info.txt", true);
	  xhttp.open("GET", url, true);
	  xhttp.send();
	
	
}