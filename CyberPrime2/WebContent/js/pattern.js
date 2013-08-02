
var pattern = 1;
var finalPattern = "";
function doSomething(obj){
	
	var div = document.getElementById(obj);
	div.innerHTML = pattern;
	pattern += 1;
	var input = document.getElementsByName('pattern')[0];
	finalPattern +=obj;
	input.value = finalPattern;
	div.style.zIndex = -1;

}

function resetPattern(){
	
	for(var i=0; i<8;i++){
		
		for(var j=0; j<6;j++){

			var string = i.toString()+j.toString();
			var div = document.getElementById(string);
			div.innerHTML = "";
			div.style.zIndex = 2;
			
		}
	}
	
	finalPattern = "";
	pattern = 1;
	var input = document.getElementsByName('pattern')[0];
	input.value = "";
}