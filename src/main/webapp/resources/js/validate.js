(function controller() {
	this.error = false;
	var validate = function validate(evt) {
		var theEvent = evt || window.event,
			target = theEvent.target;
		if(target.name == "user_birthdate")
			validate_d(theEvent);
		else
			validate_n(theEvent);
	},
	validate_d = function validate_d(evt) {
		var key = evt.keyCode || evt.which,
			regex = /[0-9]|-/;
		key = String.fromCharCode(key);
		if(!regex.test(key)) {
			evt.returnValue = false;
			if(evt.perventDefault) evt.preventDefault();
		}
	},
	validate_n = function validate_n(evt) {
		var key = evt.keyCode || evt.which,
			regex = /[0-9]|\./;
		key = String.fromCharCode(key);
		if( !regex.test(key) ) {
			evt.returnValue = false;
			if(evt.preventDefault) evt.preventDefault();
		}
	},
	validate_afterfocus = function(evt) {
		var theEvent = evt || window.event,
			regex = /[0-9]{2}-[0-9]{2}-[0-9]{4}/;
		this.error = false;
		if(!regex.test(theEvent.target.value))
			this.error = true;
	},
	inputs = document.getElementsByClassName("validatable"),
	date = document.getElementsByClassName("date")[0];
	
	for(var i = 0, l = inputs.length; i < l; i++)
		inputs.item(i).addEventListener("onkeypress", validate, false);
	date.addEventListener("onfocusout", validate_afterfocus, false);
})();
