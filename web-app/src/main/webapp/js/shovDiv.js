var isHide = false;
$(document).ready(function(){
	function showDiv();
	
});
function showDiv() {
	
	$(".js-show-some").click(function(e){
		if(!isHide){
		$("#aboutDiv").fadeIn();
		isHide = !isHide;
		}
		else{
			$("#aboutDiv").fadeOut();
			isHide = !isHide;
		}
	});
}
   //document.getElementById('aboutDiv').style.display = "block";
  // var e = document.getElementById(id);
//   if(document.getElementById('aboutDiv').style.display = "block")
//	   document.getElementById('aboutDiv').style.display = "none";
//   else
//	   document.getElementById('aboutDiv').style.display = "block"
