import axios from 'axios'



var submit=document.getElementById('submit');
submit.addEventListener("click",getpath);

  
function getpath()
  {
   document.getElementById("result").innerHTML="Getting result from server ...";

  
  console.log("button clicked");
  var start=document.getElementById("start").value;
  var end=document.getElementById("end").value;
  if(start=="" || end=="") {
    document.getElementById("result").innerHTML="Input can not be empty!";
    return;
  }
  
 var url=window.location.href+'findPath/'+start+'/'+end; 
 console.log(url);
  
axios.get(url).then(

function(response){
  
  var data=response.data;
  
  
  document.getElementById("result").innerHTML=data;
  
  
}



);
  
  
  }
