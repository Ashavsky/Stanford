/*var i = 1; // used to track how many extra questions were added

function increment() {
	i+=1; // Increments the counter when "add a question" is pressed
	var numQuestions = document.getElementById("numquestions");
	numQuestions.value = i; 
}
} */

// TODO: Consider validateData method 

function addQuestion(questionType) {
	//console.log(questionType);
	if (questionType == "qresponse") {
		addQResponse();
	} else if(questionType == "fillblank") {
		addFillBlank();
	} else if(questionType == "multiplechoice") {
		addMultipleChoice();
	} else if(questionType == "pictureresponse"){
		// Please select a question type
		addPictureResponse();
	}
	return false; 
}

function addQResponse() {
	var question = document.getElementById("dynamic");
	question.appendChild(document.createElement("br"));
	//question.appendChild(document.createTextNode(i+". Question Text: "));
	question.appendChild(document.createTextNode("Question Text: "));
	var input = document.createElement("input");
	input.type = "text";
	//input.name = "question" + i; // How to reference the field in the servlet
	input.name = "question";
	question.appendChild(input); 
	question.appendChild(document.createElement("br"));
	question.appendChild(document.createTextNode("Answer Text (delimit multiple answers with ';'): "));
	var answerInput = document.createElement("input");
	answerInput.type = "text";
	//answerInput.name = "answer" + i;
	answerInput.name = 'answer';
	question.appendChild(answerInput);
	question.appendChild(document.createElement("br"));
	//increment();
	return false; 
}

function addFillBlank(){
	var question = document.getElementById("dynamic");
	question.appendChild(document.createElement("br"));
	question.appendChild(document.createTextNode("For the question text, use a | as the blank in your question."));
	question.appendChild(document.createElement("br"));
	question.appendChild(document.createTextNode("Question Text: "));
	var input = document.createElement("input");
	input.type = "text";
	input.name = "question";
	question.appendChild(input);
	question.appendChild(document.createElement("br"));
	question.appendChild(document.createTextNode("Answer Text (delimit multiple answers with ';'): "));
	var answerInput = document.createElement("input");
	answerInput.type = "text";
	answerInput.name = 'answer';
	question.appendChild(answerInput);
	question.appendChild(document.createElement("br"));
	return false;
}

function addMultipleChoice() {
	var question = document.getElementById("dynamic");
	question.appendChild(document.createElement("br"));
	question.appendChild(document.createTextNode("Question Text: "));
	var input = document.createElement("input");
	input.type = "text";
	input.name = "question";
	question.appendChild(input);
	question.appendChild(document.createElement("br"));
	question.appendChild(document.createTextNode("Answer Text: "));
	var answerInput = document.createElement("input");
	answerInput.type = "text";
	answerInput.name = 'answer';
	question.appendChild(answerInput);
	question.appendChild(document.createElement("br"));
	var wrongChoices = [];
	for(i = 0; i < 3; i++) {
		question.appendChild(document.createTextNode("Wrong Choice Text "+i+": "));
		wrongChoices[i] = document.createElement("input");
		wrongChoices[i].type = "text";
		wrongChoices[i].name = 'wrong'+i;
		question.appendChild(wrongChoices[i]);
		question.appendChild(document.createElement("br"));
	}
	return false;
}

function addPictureResponse() {
	var question = document.getElementById("dynamic");
	question.appendChild(document.createElement("br"));
	question.appendChild(document.createTextNode("Image URL: "));
	var imageInput = document.createElement("input");
	imageInput.type = "text";
	imageInput.name = "imageurl";
	question.appendChild(imageInput);
	question.appendChild(document.createElement("br"));
	question.appendChild(document.createTextNode("Question Text: "));
	var input = document.createElement("input");
	input.type = "text";
	input.name = "question";
	question.appendChild(input);
	question.appendChild(document.createElement("br"));
	question.appendChild(document.createTextNode("Answer Text (delimit multiple answers with ;): "));
	var answerInput = document.createElement("input");
	answerInput.type = "text";
	answerInput.name = 'answer';
	question.appendChild(answerInput);
	question.appendChild(document.createElement("br"));
	return false;
}
