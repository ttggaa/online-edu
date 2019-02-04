<template>
  <el-container v-loading="loadingState">
  	<el-container v-if="loadingState && lockSecond > 0 && isFullscreen" style="position:fixed;top:100;float:left;z-index: 2001;margin-left: 20px;margin-top: 20px;">
			<el-tag type="danger" style="font-size: 16px;">警告: 答题过程中请保持在当前页面，如切换到其它的网站页面，系统将会自动锁定，解锁倒计时：{{lockSecond}}</el-tag>
		</el-container>
		<el-container v-if="loadingState && isFullscreen == false" style="position:fixed;top:100;float:left;z-index: 2001;margin-left: 20px;margin-top: 20px;">
			<div class="fullscreen-msg-class">
				警告: <br/>考试过程中请保持全屏状态，禁止切换其它网站页面，否则系统将会自动锁定。<br/>解锁倒计时：{{lockSecond}} <br/>
		  	<el-button type="info" @click="buttoncli" style="height:40px;margin-top: 15px;" v-if="loadingState && lockSecond <= 0">立即全屏解锁考试</el-button>
		  </div>
		</el-container>
  	<el-container v-if="examCourseLoadingState=='0'" style="position:fixed;top:100;float:left;z-index: 2001;margin-left: 20px;margin-top: 20px;">
			<el-tag type="danger" style="font-size: 16px;">抱歉，系统打开试卷失败，请尝试手动刷新页面 或 与管理员联系解决此问题！</el-tag>
		</el-container>
  	<el-header style="position:fixed;top:0;width:100%;background-color:#fff;z-index: 1999;" v-if="examCourseLoadingState=='1'">
  		<el-menu class="el-menu-demo" mode="horizontal">
			  <el-menu-item index="1">{{examname}}</el-menu-item>
			  <div style="float: right;padding-right: 10px;line-height: 60px;">
			  	剩余时间:<el-tag type="success" style="margin-right: 5px;margin-left: 5px;">{{remainTime}}</el-tag>
			    <el-button type="primary" round @click.native="submitHandle()">提交试卷</el-button>
			  </div>
			</el-menu>
  	</el-header>
  	<el-container style="margin-top: 60px; border: 1px solid #eee;" v-if="examCourseLoadingState=='1'">
		  <el-aside style="background-color: rgb(238, 241, 246);position: fixed;top:60px;width:240px;" id="el-aside">
		  	<h3 id="ji-chu-yong-fa" style="margin-left: 10px;"><i class="el-icon-edit" style="margin-right: 4px;"></i>{{truename}}</h3>
		    <el-menu :default-openeds="open_list">
		      <el-submenu :index="itemIndex" v-for="(p, itemIndex) in examCourse.examinationTypeList">
		        <template slot="title"><i class="el-icon-circle-plus"></i>{{p.type.typename}}</template>
		        <el-menu-item-group style="padding-left: 5px;margin-bottom: 10px;">
		        	<el-button size="mini" class="dtk_btn"
		        		v-for="(item, index) in p.paperExaminationList" :key="item.id" 
		        		@click="gotoLocation(item.id)"
		        		:type="[
		        			item.answerState == '1' ? 'success': 'info'
		        		]">{{index+1}}</el-button>
		        </el-menu-item-group>
		      </el-submenu>
		    </el-menu>
		  </el-aside>
		  
		  <el-container style="padding-left: 240px;">
		    <el-main>
		      <el-card class="box-card" v-for="(p, pIndex) in examCourse.examinationTypeList" :key="p.quesCount">
					  <div slot="header" class="clearfix">
					    <span>{{p.type.typename + '(共:' + p.quesCount + '题,每题' + p.score + '分)'}}</span>
					  </div>
					  <div v-for="(item, index) in p.paperExaminationList" :key="item.id" class="text item">
					    <div :id="'question_' + item.id" class="examinationContent" v-html="(index+1) + '. ' + item.examinationContent"></div>
					    <div style="margin-left: 15px;" v-if="p.type.typeCode=='danx'">
					    	<template>
								  <el-radio-group v-model="item.userAnswer" @change="changeUserAnswerHandler(item.typeCode,pIndex,index)">
								  	<div class="option-div">
								      <el-radio :label="'a'" v-if="item.optionA != '' && item.optionA != null">A. {{item.optionA}}</el-radio>
								    </div>
								    <div class="option-div">
								      <el-radio :label="'b'" v-if="item.optionB != '' && item.optionB != null">B. {{item.optionB}}</el-radio>
								    </div>
								    <div class="option-div">
								      <el-radio :label="'c'" v-if="item.optionC != '' && item.optionC != null">C. {{item.optionC}}</el-radio>
								    </div>
								    <div class="option-div">
								      <el-radio :label="'d'" v-if="item.optionD != '' && item.optionD != null">D. {{item.optionD}}</el-radio>
								    </div>
								    <div class="option-div">
								      <el-radio :label="'e'" v-if="item.optionE != '' && item.optionE != null">E. {{item.optionE}}</el-radio>
								    </div>
								    <div class="option-div">
								      <el-radio :label="'f'" v-if="item.optionF != '' && item.optionF != null">F. {{item.optionF}}</el-radio>
								    </div>
								  </el-radio-group>
								</template>
					    </div>
					    <div style="margin-left: 15px;" v-if="p.type.typeCode=='duox'">
					    	<template>
								  <el-checkbox-group v-model="item.userAnswerArr" @change="changeUserAnswerHandler(item.typeCode,pIndex,index)">
								  	<div class="option-div">
								      <el-checkbox :label="'a'" v-if="item.optionA != '' && item.optionA != null">A. {{item.optionA}}</el-checkbox>
								    </div>
								    <div class="option-div">
								      <el-checkbox :label="'b'" v-if="item.optionB != '' && item.optionB != null">B. {{item.optionB}}</el-checkbox>
								    </div>
								    <div class="option-div">
								      <el-checkbox :label="'c'" v-if="item.optionC != '' && item.optionC != null">C. {{item.optionC}}</el-checkbox>
								    </div>
								    <div class="option-div">
								      <el-checkbox :label="'d'" v-if="item.optionD != '' && item.optionD != null">D. {{item.optionD}}</el-checkbox>
								    </div>
								    <div class="option-div">
								      <el-checkbox :label="'e'" v-if="item.optionE != '' && item.optionE != null">E. {{item.optionE}}</el-checkbox>
								    </div>
								    <div class="option-div">
								      <el-checkbox :label="'f'" v-if="item.optionF != '' && item.optionF != null">F. {{item.optionF}}</el-checkbox>
								    </div>
								  </el-checkbox-group>
								</template>
					    </div>
					    <div style="margin-left: 15px;" v-if="p.type.typeCode=='pand'">
					    	<template>
								  <el-radio-group v-model="item.userAnswer" @change="changeUserAnswerHandler(item.typeCode,pIndex,index)">
								  	<div class="option-div">
								      <el-radio :label="'正确'">正确</el-radio>
								      <el-radio :label="'错误'">错误</el-radio>
								   </div>
								  </el-radio-group>
								</template>
					    </div>
					    <div style="margin-left: 15px;" v-if="p.type.typeCode=='tiank'">
					    	<template>
					    		<div v-if="item.optionA != '' && item.optionA != null" class="txt_op_div">
									  <el-input placeholder="请输入(1)答案" v-model="item.userAnswerArr[0]" style="width:600px;" maxlength="60" 
									  	@input.native="inpChangeNative(pIndex,index,0)" @blur="changeUserAnswerHandler(item.typeCode,pIndex,index)">
									    <template slot="prepend">(1)</template>
									  </el-input>
									</div>
									<div v-if="item.optionB != '' && item.optionB != null" class="txt_op_div">
									  <el-input placeholder="请输入(2)答案" v-model="item.userAnswerArr[1]" style="width:600px;" maxlength="60"
									  	@input.native="inpChangeNative(pIndex,index,1)" @blur="changeUserAnswerHandler(item.typeCode,pIndex,index)">
									    <template slot="prepend">(2)</template>
									  </el-input>
									</div>
									<div v-if="item.optionC != '' && item.optionC != null" class="txt_op_div">
									  <el-input placeholder="请输入(3)答案" v-model="item.userAnswerArr[2]" style="width:600px;" maxlength="60">
									    <template slot="prepend">(3)</template>
									  </el-input>
									</div>
									<div v-if="item.optionD != '' && item.optionD != null" class="txt_op_div">
									  <el-input placeholder="请输入(4)答案" v-model="item.userAnswerArr[3]" style="width:600px;" maxlength="60">
									    <template slot="prepend">(4)</template>
									  </el-input>
									</div>
									<div v-if="item.optionE != '' && item.optionE != null" class="txt_op_div">
									  <el-input placeholder="请输入(5)答案" v-model="item.userAnswerArr[4]" style="width:600px;" maxlength="60">
									    <template slot="prepend">(5)</template>
									  </el-input>
									</div>
									<div v-if="item.optionF != '' && item.optionF != null" class="txt_op_div">
									  <el-input placeholder="请输入(6)答案" v-model="item.userAnswerArr[5]" style="width:600px;" maxlength="60">
									    <template slot="prepend">(6)</template>
									  </el-input>
									</div>
								</template>
					    </div>
					    <div style="margin-left: 15px;" v-if="p.type.typeCode=='jiand'">
					    	<el-input
								  type="textarea"
								  :autosize="{ minRows: 6, maxRows: 10}"
								  placeholder="请输入内容"
								  v-model="item.userAnswer" 
								  @input.native="textareaChangeNative(pIndex,index)" @blur="changeUserAnswerHandler(item.typeCode,pIndex,index)">
								</el-input>
					    </div>
					  </div>
					</el-card>
		    </el-main>
		  </el-container>
		</el-container>
	</el-container>
</template>
<script>
	  import screenfull from 'screenfull'
	  import Vue from 'vue'
		var saveQueueMap = new Map()
    export default {
      data () {
        return {
        	cid: 0,
        	examCourse: {},
          truename: '',
          idcard: '',
          examname: '',
          SysSecond: -1,
          remainTime: '',
          InterValObj: null,
          loadingState: true,
          open_list: [0,1,2,3,4],
          timeIntervalDjs: null,
          examCourseLoadingState: '',
          isFullscreen: false,
          pageChange: 0,
          lockSecondBase: 10,
          lockSecond: 0
        }
      },
      created () {
      	//this.buttoncli()
      	if(this.checkFull()){
      		this.isFullscreen = true
          this.loadingState = false
      	}
      	this.initWebSocket();
      	document.addEventListener("visibilitychange", () => { 
			    if(document.hidden) {
			        // 页面被挂起
			        console.log("页面被挂起")
			    }
			    else {
			        // 页面呼出
			        console.log("页面呼出")
			        this.pageChange ++
			        if(this.pageChange > 5){
			        	this.lockSecondBase += 2
			        	this.lockSecond = this.lockSecondBase
			        	this.loadingState = true
			        }
			    }
			  });
      },
      destroyed () {
      	this.websock.close()
      },
      mounted() {
        window.onresize = () => {
          // 全屏下监控是否按键了ESC
          if (!this.checkFull()) {
          	console.log("mounted esc.....")
          	// 全屏下按键esc后要执行的动作
            this.isFullscreen = false
            this.loadingState = true
            this.lockSecondBase += 2
	        	this.lockSecond = this.lockSecondBase
          }else{
          	this.loadingState = false
          	this.isFullscreen = true
          }
        }
      },
      methods: {
        buttoncli () {
          if (!screenfull.enabled) { // 如果不允许进入全屏，发出不允许提示
          	this.isFullscreen = false
            return false
          }
          this.isFullscreen = true
          screenfull.request()
        },
        checkFull() {
          var isFull = document.fullscreenEnabled || window.fullScreen || document.webkitIsFullScreen || document.msFullscreenEnabled;
          if (isFull === undefined) {
            isFull = false;
          }
          return isFull;
        },
        initWebSocket(){ //初始化weosocket
        	var domain = window.location.host
	        const wsuri = "ws://exam-websocket.linghang-tech.com:9989/ws?t=" + Vue.cookie.get('token') + "&r=" + domain
	        this.websock = new WebSocket(wsuri)
	        this.websock.onmessage = this.websocketonmessage
	        this.websock.onopen = this.websocketonopen
	        this.websock.onerror = this.websocketonerror
	        this.websock.onclose = this.websocketclose
	      },
	      websocketonopen(){ //连接建立之后执行send方法发送数据
	        //let actions = {"test":"12345"};        this.websocketsend(JSON.stringify(actions));
	        console.log("ws open....")
	        this.loadPaper();
	      },
	      websocketonerror(){//连接建立失败重连
	        //this.initWebSocket();
	        console.log("ws error....")
	        this.$message.error("服务器连接异常或超出同时在线考生数量 , 请稍候刷新重试！")
	      },
	      websocketonmessage(e){ //数据接收
	        const redata = JSON.parse(e.data);
	        console.log("websocketonmessage=", redata)
	      },
	      websocketsend(Data){//数据发送
	        //this.websock.send(Data);
	      },
	      websocketclose(e){  //关闭
	        console.log('断开连接',e);
	      },
        loadPaper () {
        	let that = this
	      	this.cid = this.$route.params.cid
	        this.$http({
	          url: this.$http.adornUrl('/paper/getExamPaperData'),
	          method: 'post',
	          data: this.$http.adornData({
	            'cid': this.cid
	          })
	        }).then(({data}) => {
	          if (data && data.code === 0) {
	            this.truename = data.truename
	            this.idcard = data.idcard
	            this.examname = data.examname
	            this.SysSecond = data.overTimeSecond
	            this.examCourse = data.examCourse
	            if(data.examCourseLoadingState == 'true'){
	            	this.examCourseLoadingState = '1'
	            	this.viewRemainTime()
	            	this.InterValObj = window.setInterval(this.SetRemainTime, 1000);
	            	this.timeIntervalDjs = window.setInterval(this.reqTimeIntervalDjs, 15000);
	            	window.setTimeout(function exit(){
	            		this.buttoncli()
	              },3000)
	            }else{
	            	this.examCourseLoadingState = '0'
	            	this.$message.error("试卷加载失败,请稍候刷新重试！")
	            }
	          } else {
	            this.$message.error(data.msg)
	            this.$router.push({ name: 'home' })
	          }
	        })
        },
        viewRemainTime () {
          var second = Math.floor(this.SysSecond % 60)
          var minite = Math.floor((this.SysSecond / 60) % 60)
          var hour = Math.floor((this.SysSecond / 3600) % 24)
          var day = Math.floor((this.SysSecond / 3600) / 24)
          var s = ''
          if (day != '0') s += day + '天'
          if (day != '0' || hour != '0') s += hour + '时'
          if (day != '0' || hour != '0' || minite != '0') s += minite + '分'
          if (day != '0' || hour != '0' || minite != '0' || second != '0') s += second + '秒'
          if (this.SysSecond > 0) {
            this.remainTime = s
          }else{
          	this.remainTime = '即将自动提交试卷'
          	window.clearInterval(this.InterValObj)
          	this.autoSubmitPaper()
          }
        },
        SetRemainTime () {
          if (this.SysSecond > 0) {
            this.SysSecond = this.SysSecond - 1
            this.viewRemainTime()
            if(this.lockSecond > 0) {
            	this.lockSecond --
            } else if (this.isFullscreen == false){
            	this.loadingState = true
            } else{
            	this.loadingState = false
            }
          }
        },
        reqTimeIntervalDjs (){
        	if (this.SysSecond > 0) {
            this.$http({
		          url: this.$http.adornChannelUrl('/common/getOverTimeSecond'),
		          method: 'post',
		          data: this.$http.adornData({
		            'cid': this.cid
		          })
		        }).then(({data}) => {
		          if (data && data.code === 0) {
		            this.SysSecond = data.overTimeSecond
		          }
		        })
          }
        },
        changeUserAnswerHandler (typeCode, pIndex, index) {
        	let answer = ""
        	let ques = this.examCourse.examinationTypeList[pIndex].paperExaminationList[index]
        	let qid = ques.id
        	if(typeCode == "danx" || typeCode == "pand" || typeCode == "jiand"){
				    answer = ques.userAnswer
			    }else if(typeCode == "duox"){
				    ques.userAnswerArr.forEach(function (c) {
	            if(c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f') {
	            	answer += c
	            }
	          })
			    }else if(typeCode == "tiank"){
				    let lp = ""
				    console.log("userAnswerArr",ques.userAnswerArr)
				    ques.userAnswerArr.forEach(function (c) {
	            if(c != '') {
	            	answer += lp + c
	            	lp = "@"
	            }
	          })
			    }
			    
			    //空选项，清除答题板
			    if(answer == ""){
						this.examCourse.examinationTypeList[pIndex].paperExaminationList[index].answerState = '0'
			    }else{
			    	if(this.examCourse.examinationTypeList[pIndex].paperExaminationList[index].answerState != '1'){
			    		this.examCourse.examinationTypeList[pIndex].paperExaminationList[index].answerState = '0'
			    	}
			    }
			    
			    var paramTemp = qid + "#" + typeCode + "#" + answer + "#" + pIndex + "#" + index
			    saveQueueMap.set("k_" + qid, paramTemp)
			    console.log("test=",saveQueueMap.get("k_" + qid))
			    this.saveAnswer()
        },
        saveAnswer () {
			    let param = ""
					let lp = ""
					saveQueueMap.forEach(function (item, key, mapObj) {
					    param += lp + item.toString()
					    lp = ","
					});
					let that = this
			    this.$http({
	          url: this.$http.adornChannelUrl('/common/saveQues'),
	          method: 'post',
	          data: this.$http.adornData({
	            'courseId': this.cid,
	            'param': param
	          })
	        }).then(({data}) => {
	        	console.log("data=" , data)
	          if (data && data.code === 0) {
							data.retData.forEach(function (c) {
								let temp = saveQueueMap.get("k_" + c).split('#')
								that.examCourse.examinationTypeList[parseInt(temp[3])].paperExaminationList[parseInt(temp[4])].answerState = '1'
		            saveQueueMap.delete(c)
		          })
	          } else {
	            this.$message.error(data.msg)
	          }
	        })
        },
        submitHandle () {
          this.$confirm(`确定进行 [提交试卷] 操作?`, '【试卷提交】提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.submitPaper()
          }).catch(() => {})
        },
        autoSubmitPaper () {
        	this.submitPaper()
        },
        submitPaper () {
        	this.loadingState = true
        	let param = ""
					let lp = ""
					saveQueueMap.forEach(function (item, key, mapObj) {
					    param += lp + item.toString()
					    lp = ","
					})
        	this.$http({
	          url: this.$http.adornUrl('/common/submitPaper'),
	          method: 'post',
	          data: this.$http.adornData({
	            'courseId': this.cid,
	            'param': param
	          })
	        }).then(({data}) => {
	        	console.log("data=" , data)
	          if (data && data.code === 0) {
							this.$router.push({ name: 'home' })
	          } else {
	            this.$message.error(data.msg)
	          }
	          
	        })
        },
        gotoLocation (id) {
        	document.querySelector("#question_" + id).scrollIntoView(true);
          var anchor = this.$el.querySelector("#question_" + id)
          document.documentElement.scrollTop = anchor.offsetTop - 80
        },
        inpChangeNative(pIndex, index,i) {
          this.$nextTick(() => {
        	  let ques = this.examCourse.examinationTypeList[pIndex].paperExaminationList[index]
            if(ques.userAnswerArr[i] !== null){
            	//ques.userAnswerArr[i] = ques.userAnswerArr[i].replace(/^[\u4e00-\u9fa5a-zA-Z-z0-9]+$]+/,'')
            }
          })
        },
        textareaChangeNative (pIndex, index) {
          this.$nextTick(() => {
        	  let ques = this.examCourse.examinationTypeList[pIndex].paperExaminationList[index]
            if(ques.userAnswer !== null){
            	//ques.userAnswer = ques.userAnswer.replace(/^[A-Za-z0-9\u4e00-\u9fa5]+$/,'')
            }
          })
        }
      }
    }
</script>

<style>
  .mod-home {
    line-height: 1.5;
  }
  .dtk_btn {
  	margin-left: 2px !important;
  	margin-right: 0px;
  	margin-bottom: 4px;
  	padding: 4px 8px;
  	width: 30px;
  }
  .el-menu-item-group__title{
  	padding-top: 0px;
  }
  .box-card {
  	margin-top: 10px;
  }
  .examinationContent {
  	margin-top: 5px;
  	margin-bottom: 10px;
  	font-size: 18px;
  	line-height: 26px;
  }
  .option-div {
  	margin-top: 5px;
  	margin-bottom: 10px;
  }
  .option-div label span {
  	font-size: 17px !important;
  }
  .txt_op_div{
  	margin-top: 10px;
  }
  .fullscreen-msg-class {
  	font-size: 16px;
  	color: brown;
  	line-height: 25px;
  	width:640px;
  }
  
  
  @media screen and (min-width: 960px) and (max-width: 1199px) {
			
	}
		 
		 
	@media screen and (min-width: 768px) and (max-width: 959px) {
			
	}
		 
		 
	@media only screen and (min-width: 480px) and (max-width: 767px){
			
	}
		 
		 
	@media only screen and (max-width: 479px) {
			.fullscreen-msg-class {
		  	font-size: 16px;
		  	color: brown;
		  	line-height: 25px;
		  	width:340px;
		  }
	}
</style>

