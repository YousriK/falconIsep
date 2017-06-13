/**
 * 
 * Core.js 1.0
 * 
 * Définition des objets javaScript qui interviennent dans la création d'un formulaire
 * 
 */

var Core = {}

Core.form = function(title, questions) {
	this.title = title;
	this.questions = questions;
}

Core.question = function(type, title) {
	this.type = type;
	this.title = title;
}


Core.text = function(title) {
	this.base = Core.question;
	this.base("text", title);
}


Core.yn = function(title) {
	this.base = Core.question;
	this.base("yn", title);
}


Core.radio = function(title, options) {
	this.base = Core.question;
	this.base("radio", title);
	this.options = options;
}

Core.roti = function(title) {
	this.base = Core.question;
	this.base("roti", title);
}
Core.checkbox = function(title, options) {
	this.base = Core.question;
	this.base("checkbox", title);
	this.options = options;
}