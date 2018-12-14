   function showModal(title,kind){
	   $('#re-prompt').modal('close');
	   $("#modalDivTitle").html(title);
	   $('#my-modal-loading').modal(kind);
   }
   function changQues(qNum){
	   $.ajax({
          url: apiBasePath + "findQues?qnum="+qNum+"&pid="+pId,
          dataType: "jsonp",jsonp: "callback",jsonpCallback:"callback",type: "get",
          success: function(json){
              var o = eval(json);
              if(o.result == 'SUCCESS'){
            	  $("#board_" + qIndex).removeClass("am-badge-primary");
    			  $("#board_" + qNum).addClass("am-badge-primary");
            	  $("#qcontentSpan").html(o.message.qcontent);
    			  qIndex = qNum;
    			  quesUserAnswer = o.message.userAnswer;
            	  showOption(o.message.typeCode , o.message.userAnswer , o.message.optionCount);
    			  if(qIndex > 1) priNum = parseInt(qIndex) - 1;
    			  if(qIndex < quesCount) nextNum = parseInt(qIndex) + 1;
    		 	  $("#quesTypeSpan").html(o.message.typeName);
    			  updatePage();
              }
          },error: function(){alert('数据请求失败，请稍候重试！');}
      });
   }
   function priQues(){
	   if(parseInt(qIndex) == 1) return ;
	   changQues(priNum);
   }
   function nextQues(){
	   if(parseInt(qIndex) >= quesCount) {
		   return ;  
	   }
	   changQues(nextNum);
   }
   function uncertainQues(){
	   var uncertain = "1";
	   if($("#board_" + qIndex).hasClass("am-badge-warning")) uncertain = "0";
	   $.ajax({
          url: apiBasePath + "uncertainQues?qnum="+qIndex+"&pid="+pId+"&uncertain=" + uncertain,
          dataType: "jsonp",jsonp: "callback",jsonpCallback:"callback",type: "get",
          success: function(json){
              var o = eval(json);
              if(o.result == 'SUCCESS'){
            	  if($("#board_" + qIndex).hasClass("am-badge-warning")){
           		      $("#board_" + qIndex).removeClass("am-badge-warning");  
	           	  }else{
	           		  $("#board_" + qIndex).addClass("am-badge-warning");  
	           	  }
	           	  updatePage();
              }
          },error: function(){alert('数据请求失败，请稍候重试！');}
      });
   }
   function checkTime(){
	   $.ajax({
          url: apiBasePath + "checkTime?pid="+pId,
          dataType: "jsonp",jsonp: "callback",jsonpCallback:"callback",type: "get",
          success: function(json){
              var o = eval(json);
              if(o.result == 'SUCCESS'){
            	  deadlineTimeMinute = o.message;
            	  $("#timeMinSpan").html(deadlineTimeMinute);
            	  if(deadlineTimeMinute <= 0){
            		  window.clearInterval(InterValObj); 
            		  autoSubmitPaper();
            	  }
              }
          },error: function(){}
      });
   }
   function submitPaper(basePath){
	   if(confirm("您确定要提交试卷吗？")){
		   showModal("正在为您提交试卷，请稍候...","open");
		   setTimeout(function () { 
			   submitPaperAjax(basePath);
		   }, 1000);
	   }
   }
   function submitPaperAjax(basePath){
	   $.ajax({
          url: apiBasePath + "submitPaper?pid="+pId,
          dataType: "jsonp",jsonp: "callback",jsonpCallback:"callback",type: "get",
          success: function(json){
              var o = eval(json);
              if(o.result == 'SUCCESS'){
            	  showModal("交卷成功，正在为您跳转成绩显示页面，请稍候...","open");
            	  window.location = basePath + "submitPaperExam.action?cid=" + cid;
              }
          },error: function(){
        	  if(errorCount > 3){
        		  showModal("","close");
        		  $('#re-prompt').modal('open');
        	  }else{
        		  submitPaperAjax();
        		  errorCount ++;
        	  }
          }
      });
   }
   function autoSubmitPaper(basePath){
	   showModal("交卷时间已到，系统自动提交试卷，请稍候...","open");
	   setTimeout(function () { 
		   submitPaperAjax(basePath);
	   }, 2000);
   }
   
   function haveAnswer(obj,typeCode,opName){
	   var userAnswer = "";
	   if(typeCode == "danx" || typeCode == "pand"){
		   userAnswer = obj.value;
	   }else if(typeCode == "duox"){
		   var lp = "";
		   $("input[name='"+opName+"']:checkbox:checked").each(function(){ 
			   userAnswer+= lp + $(this).val();
			   lp = ";";
		   }); 
	   }
	   //已答状态 且 答案未改变不调用AJAX
	   if($("#board_" + qIndex).hasClass("am-badge-success") && userAnswer == quesUserAnswer) return ;
	   $.ajax({
          url: apiBasePath + "haveAnswer?qnum="+qIndex+"&pid="+pId+"&userAnswer=" + userAnswer,
          dataType: "jsonp",jsonp: "callback",jsonpCallback:"callback",type: "get",
          success: function(json){
              var o = eval(json);
              if(o.result == 'SUCCESS'){
            	  $("#board_" + qIndex).addClass("am-badge-success"); 
            	  quesUserAnswer = userAnswer;
           	   	  updatePage();
              }
          },error: function(){alert('数据请求失败，请稍候重试！');}
       });
   }
   
   function showOption(typeCode,userAnswer,optionCount){
	  $("#singleTypeDiv").hide();
 	  $("#multipleTypeDiv").hide();
 	  $("#judgeTypeDiv").hide();
 	  if(optionCount == "" || optionCount == null) optionCount = "4";
 	  $("#singleTypeDiv").html("");
 	  $("#multipleTypeDiv").html("");
 	  $("#judgeTypeDiv").html("");
 	  if(typeCode == "danx"){
 		  //单选
 		  var opStr = "";
 		  for(var i=0;i<parseInt(optionCount);i++){
 			  var opTxt = String.fromCharCode(65+i);
 			  var selFlag = "";
 			  if(userAnswer == opTxt) {
 				 selFlag = "checked='checked'";
 				 $("#board_" + qIndex).addClass("am-badge-success"); 
 			  }
 		  	  opStr = "<label class='am-radio-inline'>";
 	 	  	  opStr += "<input type='radio' value='" + opTxt + "' name='option_1' onclick='haveAnswer(this,\"" + typeCode + "\",\"option_1\");' " + selFlag + " /> ";
 	 	  	  opStr += String.fromCharCode(65+i);
 	     	  opStr += "</label>";
 		  	  $("#singleTypeDiv").append(opStr);		  
 		  }
 		  $("#singleTypeDiv").show();
 	  }else if(typeCode == "duox"){
 		  //多选
 		  var opStr = "";
		  for(var i=0;i<parseInt(optionCount);i++){
		  	 opStr = "<label class='am-radio-inline'>";
	 	  	 opStr += "<input type='checkbox' value='" + String.fromCharCode(65+i) + "' name='option_2' onclick='haveAnswer(this,\"" + typeCode + "\",\"option_2\");' /> ";
	 	  	 opStr += String.fromCharCode(65+i);
	     	 opStr += "</label>";
		  	 $("#multipleTypeDiv").append(opStr);		  
		  }
 		  $("#multipleTypeDiv").show();
 	  }else if(typeCode == "pand"){
 		  //判断
 		  opStr = "<label class='am-radio-inline'>";
 	  	  opStr += "<input type='radio' value='true' name='option_3' onclick='haveAnswer(this,\"" + typeCode + "\",\"option_3\");' /> 正确";
 	  	  opStr += "</label>";
 	  	  opStr += "<label class='am-radio-inline'>";
 	  	  opStr += "<input type='radio' value='false' name='option_3' onclick='haveAnswer(this,\"" + typeCode + "\",\"option_3\");' /> 错误";
     	  opStr += "</label>";
     	  $("#judgeTypeDiv").append(opStr);
 		  $("#judgeTypeDiv").show();
 	  }
   }
   
   function updatePage(){
	   if($("#board_" + qIndex).hasClass("am-badge-warning")){
		   $("#uncertainSpan").html("取消疑难");
	   }else{
		   $("#uncertainSpan").html("疑难标记");
	   }
	   $("#quesNumSpan").html(qIndex);
	   
	   if($("#board_" + qIndex).hasClass("am-badge-success")){
		   $("#haveAnswerFlagSpan").show();
	   }else{
		   $("#haveAnswerFlagSpan").hide();
	   }
   }
   function fontSizeChange(type){
	   var thisEle = $("#qcontentSpan").css("font-size"); 
	   var textFontSize = parseFloat(thisEle , 10);
	   var unit = thisEle.slice(-2);
       if(type == "bigger"){
           textFontSize += 2;
       }else if(type == "smaller"){
           textFontSize -= 2;
       }
       $("#qcontentSpan").css("font-size",  textFontSize + unit );
   }