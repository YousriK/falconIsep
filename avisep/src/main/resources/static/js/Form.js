/**
 * 
 * Form.js
 * 
 */

$(document).ready(
	function() {
		$("#create-form").click(function() {
			var url = window.location.href.split("/").pop();
			createForm(url);
		});
		$("#form-title").change(function() {
			var newTitle = $(this).val();
			if (newTitle !== "") {
				$("#form-title-preview").attr("class", "form-title-preview").html(newTitle);
			} else {
				$("#form-title-preview").attr("class", "form-title-preview form-hint").html("Form title");
			}
		});
});
function addOption(item, type) {
	var id = checkIndex(0, "option"),
		questionId = $(item).attr("data-id"),
		html =
			'<div class="row option" id="' + id + '">' +
				'<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10"> ' +
					'<textarea class="form-control option-title" data-id="' + id + '" placeholder="Option" rows="1"></textarea>' +
				'</div> ' +
				'<div class="delete-button-parent col-xs-1 col-sm-1 col-md-1 col-lg-1"> ' +
					'<span class="delete-button delete-option-button glyphicon glyphicon-remove-sign" data-id="' + id + '" title="Supprimer l\'option" onclick="deleteOption(this)"></span>' +
				'</div>' +
			'</div>';
	$("#" + questionId + "_options").append(html);
	addOptionPreview(type, id, questionId);
	callQuestionChange();
}

function deleteOption(item) {
	var id = $(item).attr("data-id");
	$("#" + id + ", #" + id + "_option_preview").remove();
}

function addOptionPreview(type, id, questionId) {
	$("#" + questionId + "_options_preview_hint").hide();
	var html = "";
	switch (type) {
	case "checkbox":
		html = '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 option-preview" id="' + id + '_option_preview" >' +
					'<input type="checkbox"/>' +
					'<label class="question-options-preview form-hint" id="' + id + '_title_preview">Ajoutez un titre à votre option</label>' +
				'</div>'
		break;
	case "radio":
		html = '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 option-preview" id="' + id + '_option_preview" >' +
					'<input name="radio_' + questionId + '" type="radio"/>' +
					'<label class="question-options-preview form-hint" id="' + id + '_title_preview">Ajoutez un titre à votre option</label>' +
				'</div>'
		break;
	}
	$("#" + questionId + "_options_preview").append(html);
}

function checkIndex(n, type) {
	var newIndex = ($("#" + type + "_" + n).length == 0) ? type + "_" + n : checkIndex(n + 1, type);
	return newIndex;
}

function addQuestion(type) {
	var id = checkIndex(0, "question");
	$('#form-tab a[href="#form"]').tab('show');
	var html;
	$(".question-hint").hide();
	switch (type) {
	case "text":
		html = '<div class="well question text-question" data-type="text" id="'	+ id + '">' +
					'<div class="form-group row question-form">' +
						'<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">' +
							'<button class="btn btn-danger btn-delete-question" type="button" data-id="' + id	+ '" onclick="deleteQuestion(this)"><span class="glyphicon glyphicon-minus"></span></button>' +
						'</div>' +
						'<div class="col-xs-9 col-sm-3 col-md-3 col-lg-3">' +
							'<label class="question-type">Text Question :</label>' +
						'</div>' +
						'<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">' +
							'<textarea class="form-control question-title" data-id="' + id	+ '" placeholder="Type the Question" rows="1"></textarea>' +
						'</div>' +
					'</div>' +
				'</div>'
		break;
	case "checkbox":
		html = '<div class="well question checkbox-question" data-type="checkbox" id="' + id + '">' +
					'<div class="form-group row question-form">' +
						'<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">' +
							'<button class="btn btn-danger btn-delete-question" type="button" data-id="' + id + '" onclick="deleteQuestion(this)"><span class="glyphicon glyphicon-minus"></span></button>' +
						'</div>' +
						'<div class="col-xs-9 col-sm-3 col-md-3 col-lg-3">' +
							'<label class="question-type">Checkbox Question :</label>' +
						'</div>' +
						'<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">' +
							'<textarea class="form-control question-title" data-id="' + id	+ '" placeholder="Type the Question" rows="1"></textarea>' +
						'</div>' +
					'</div>' +
					'<div class="row question-form question-options">' +
						'<div class="col-xs-0 col-sm-1 col-md-1 col-lg-1"></div>' +
						'<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">' +
							'<label class="question-type">Options :</label>' +
						'</div>' +
						'<div class="col-xs-6 col-sm-1 col-md-1 col-lg-1">' +
							'<button class="btn btn-success btn-add-option" type="button" data-id="' + id	+ '" onclick="addOption(this, \'checkbox\')"><span class="glyphicon glyphicon-plus"></span></button>' +
						'</div>' +
						'<div class="col-xs-12 col-sm-7 col-md-7 col-lg-7" id="' + id	+ '_options">' +
						'<!-- Les options sont ajoutées en cliquant sur le bouton ci-dessus @Form.js -->' +
						'</div>' +
					'</div>' +
				'</div>'
		break;
	case "radio":
		html = '<div class="well question radio-question" data-type="radio" id="' + id + '">' +
					'<div class="form-group row question-form">' +
						'<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">' +
							'<button class="btn btn-danger btn-delete-question" type="button" data-id="' + id	+ '" onclick="deleteQuestion(this)"><span class="glyphicon glyphicon-minus"></span></button>' +
						'</div>' +
						'<div class="col-xs-9 col-sm-3 col-md-3 col-lg-3">' +
							'<label class="question-type">Radio Question :</label>' +
						'</div>' +
						'<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">' +
							'<textarea class="form-control question-title" data-id="' + id	+ '" placeholder="Type the Question" rows="1"></textarea>' +
						'</div>' +
					'</div>' +
					'<div class="row question-form question-options">' +
						'<div class="col-xs-0 col-sm-1 col-md-1 col-lg-1"></div>' +
						'<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">' +
							'<label class="question-type">Options :</label>' +
						'</div>' +
						'<div class="col-xs-6 col-sm-1 col-md-1 col-lg-1">' +
							'<button class="btn btn-success btn-add-option" type="button" data-id="' + id	+ '" onclick="addOption(this, \'radio\')"><span class="glyphicon glyphicon-plus"></span></button>' +
						'</div>' +
						'<div class="col-xs-12 col-sm-7 col-md-7 col-lg-7" id="' + id	+ '_options">' +
						'<!-- Les options sont ajoutées en cliquant sur le bouton ci-dessus @Form.js -->' +
						'</div>' +
					'</div>' +
				'</div>'
		break;
	case "yn":
		html = '<div class="well question yn-question" data-type="yn" id="' + id + '">' +
					'<div class="form-group row question-form">' +
						'<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">' +
							'<button class="btn btn-danger btn-delete-question" type="button" data-id="' + id + '" onclick="deleteQuestion(this)"><span class="glyphicon glyphicon-minus"></span></button>' +
						'</div>' +
						'<div class="col-xs-9 col-sm-3 col-md-3 col-lg-3">' +
							'<label class="question-type">Yes/No Question :</label>' +
						'</div>' +
						'<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">' +
							'<textarea class="form-control question-title" data-id="' + id	+ '" placeholder="Type the Question" rows="1"></textarea>' +
						'</div>' +
					'</div>' +
				'</div>'
		break;
	case "roti":
		html = '<div class="well question roti-question" data-type="roti" id="' + id + '">' +
					'<div class="form-group row question-form">' +
						'<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">' +
							'<button class="btn btn-danger btn-delete-question" type="button" data-id="' + id + '" onclick="deleteQuestion(this)"><span class="glyphicon glyphicon-minus"></span></button>' +
						'</div>' +
						'<div class="col-xs-9 col-sm-3 col-md-3 col-lg-3">' +
							'<label class="question-type">ROTI Question :</label>' +
						'</div>' +
						'<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">' +
							'<textarea class="form-control question-title" data-id="' + id	+ '" placeholder="Type the Question" rows="1"></textarea>' +
						'</div>' +
					'</div>' +
				'</div>'
		break;
	}
	$("#create-form").parent().before(html);
	addQuestionPreview(type, id);
	callQuestionChange();
}

//Supprime également le preview correspondant
function deleteQuestion(item) {
	var id = $(item).attr("data-id");
	$("#" + id + ", #" + id + "_preview").remove();
	if ($(".question").length == 0) {
		$(".question-hint").show();
	}
}

function callQuestionChange() {
	var promise = new Promise(function(resolve) {
		resolve(removeEventHandlers());
	});
	promise.then(function(result) {
		$(".question-title").change(function() {
			var id = $(this).attr("data-id"),
				title = $(this).val();
			if (title !== "") {
				$("#" + id + "_title_preview").html(title).removeClass("form-hint");
			} else {
				$("#" + id + "_title_preview").html("Ajoutez un titre à votre question").addClass("form-hint");
			}
		});
		$(".option-title").change(function() {
			var id = $(this).attr("data-id"),
				title = $(this).val();
			if (title !== "") {
				$("#" + id + "_title_preview").html(title).removeClass("form-hint");
			} else {
				$("#" + id + "_title_preview").html("Ajoutez un titre à votre option").addClass("form-hint");
			}
		});
		$(".roti-preview").click(function() {
			$(this).parent().find(".roti-preview-selected").removeClass("roti-preview-selected").addClass("roti-preview");
			$(this).removeClass("roti-preview").addClass("roti-preview-selected");
		});
	});
}

function removeEventHandlers() {
	$(".question-title").off();
	$(".option-title").off();
	$(".roti-preview").off();
}

function addQuestionPreview(type, id) {
	var html = "";
	switch (type) {
	case "text":
		html = '<div class="form-group row question-preview" id="' + id + '_preview" data-type="text">' +
					'<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
						'<label class="question-title-preview form-hint" id="' + id + '_title_preview">Ajoutez un titre à votre question</label>' +
					'</div>' +
					'<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
						'<textarea class="form-control question-answer-preview" placeholder="Type your answer" rows="1"></textarea>' +
					'</div>' +
				'</div>'
		break;
	case "checkbox":
		html = '<div class="form-group row question-preview" id="' + id + '_preview" data-type="checkbox">' +
					'<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
						'<label class="question-title-preview form-hint" id="' + id + '_title_preview">Ajoutez un titre à votre question</label>' +
					'</div>' +
					'<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="' + id + '_options_preview">' +
						'<label class="question-options-preview form-hint" id="' + id + '_options_preview_hint">Ajoutez des options à votre question</label>' +
					'</div>' +
				'</div>'
		break;
	case "radio":
		html = '<div class="form-group row question-preview" id="' + id + '_preview" data-type="radio">' +
					'<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
						'<label class="question-title-preview form-hint" id="' + id + '_title_preview">Ajoutez un titre à votre question</label>' +
					'</div>' +
					'<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="' + id + '_options_preview">' +
						'<label class="question-options-preview form-hint" id="' + id + '_options_preview_hint">Ajoutez des options à votre question</label>' +
					'</div>' +
				'</div>'
		break;
	case "yn":
		html = '<div class="form-group row question-preview" id="' + id + '_preview" data-type="yn">' +
					'<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
						'<label class="question-title-preview form-hint" id="' + id + '_title_preview">Ajoutez un titre à votre question</label>' +
					'</div>' +
					'<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
						'<div class="yn_switch_label">Non</div>' +
						'<div class="yn_switch_label" id="yn_switch_oui">Oui</div>' +
						'<div class="yn_switch">' +
							'<input id="cmn-toggle-1-' + id + '" class="cmn-toggle cmn-toggle-round" type="checkbox"/>' +
							'<label for="cmn-toggle-1-' + id + '"></label>' +
						'</div>' +
					'</div>' +
				'</div>'
		break;
	case "roti":
		html = '<div class="form-group row question-preview" id="' + id + '_preview" data-type="roti">' +
					'<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
						'<label class="question-title-preview form-hint" id="' + id + '_title_preview">Ajoutez un titre à votre question</label>' +
					'</div>' +
						'<div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 roti-preview" id="roti-preview-5"></div>' +
						'<div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 roti-preview" id="roti-preview-4"></div>' +
						'<div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 roti-preview" id="roti-preview-3"></div>' +
						'<div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 roti-preview" id="roti-preview-2"></div>' +
						'<div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 roti-preview" id="roti-preview-1"></div>' +
				'</div>'
		break;
	}
	$("#form-preview-group").append(html);
}

function createForm(url) {
	var form,
		form_title = $("#form-title").val(),
		questions = [],
		n = $(".question").length;
	if (n == 0) {
		var windowWidth = $(window).width();
		var placement = (windowWidth > 420) ? "left" : "top";
		$("#create-form")
				.attr({
					"data-toggle" : "tooltip",
					"data-placement" : placement || "top",
					"title" : "Vous devez ajouter au moins une question à votre formulaire"
				});
		$("#create-form").tooltip("show");
		setTimeout(function() {
			$("#create-form")
				.tooltip("destroy")
				.attr({
					"title" : "Create your form !"
				});
		}, 5000);
		return;
	} else {
		$(".question")
				.each(function() {
					var this_qst = $(this), type = this_qst
							.attr("data-type"), title = this_qst.find(
							".question-title").val();
					if (type == "checkbox" || type == "radio") {
						var options = [];
						this_qst.find(".option-title").each(
								function() {
									options.push($(this).val());
								})
						questions.push(new Core[type](title, options));
					} else if (type == "text" || type == "yn") {
						questions.push(new Core[type](title, options));
					}
				});
	}
	form = new Core.form(form_title, questions);
	$.ajax({
		url : '/createF/' + url,
		data : JSON.stringify(form),
		type : "POST",
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(data) {
			alert(data);
		}
	});
	return form;
}







//	function getClickClicked(){
// var clickedElement=null;
// var classes = null;
// ELEMENT.on("click",function(e){
//     clickedElement = $(e.target);
//     classes = clickedElement.attr("class").split(" ");
//     for(var i = 0; i < classes.length;i++)
//     {
//     	console.log(classes[i]);
//     }
//     e.preventDefault();
// });
//}	
