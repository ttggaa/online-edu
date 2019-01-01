<template>
  <el-container>
  	<el-header>
  		<el-menu class="el-menu-demo" mode="horizontal">
			  <el-menu-item index="1">{{examname}}</el-menu-item>
			  <el-submenu index="2" style="float: right; font-size: 12px">
			    <template slot="title"><el-tag type="danger" v-if="testFlag=='1'">测试账号</el-tag> {{truename}} [{{idcard}}]</template>
			    <el-menu-item index="2-1" @click.native="logoutHandle()">退 出</el-menu-item>
			  </el-submenu>
			</el-menu>
  	</el-header>
  	<el-main>
			<div class="get">
			  <div class="am-g">
			    <div class="am-u-lg-12">
			      <h1 class="get-title" v-if="!examFlag"><i class="am-icon-spinner am-icon-spin"></i>
			      	&nbsp;距离考试开始还剩:<span id="remainTime" style="color:#FF9900;min-width: 80px;">{{remainTime}}</span>
			      </h1>
			      <div style="text-align: left;padding-left:10px;"></div>
			    </div>
			  </div>
			</div>
			<el-row :gutter="20">
  			<el-col><div class="grid-content bg-purple">考试须知</div></el-col>
			</el-row>
			<el-row :gutter="20">
  			<el-col><div class="grid-content bg-purple" v-html="examIntroduce"></div></el-col>
			</el-row>
			
			<el-table :data="examCourseList" v-loading="loading2">
        <el-table-column prop="courseName" label="考试科目" width="600">
        </el-table-column>
        <el-table-column prop="examSumTime" label="考试时间" width="120">
        </el-table-column>
        <el-table-column prop="score" label="成绩">
        </el-table-column>
        <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
          <template slot-scope="scope">
            <el-button v-if="scope.row.submitFlag == '0' && SysSecond > 0" type="text" size="small">未开始</el-button>
            <el-button v-if="scope.row.submitFlag == '0' && SysSecond == 0 && scope.row.ksBtnFlag == '1'" type="text" size="small" @click="startExam(scope.row.courseId)">进入考试</el-button>
            <el-button v-if="scope.row.submitFlag == '1'" type="text" size="small">已结束</el-button>
          </template>
        </el-table-column>
      </el-table>
		</el-main>
		<div>
			<el-footer style="text-align: center;position:absolute;bottom:0;width:100%;">
			  <p>由大连领航世纪提供技术支持</p>
			</el-footer>
		</div>
	</el-container>
</template>
<script>
    import { clearLoginInfo } from '@/utils'
    export default {
      data () {
        return {
          truename: '',
          idcard: '',
          examname: '',
          SysSecond: -1,
          examCourseList: [],
          examIntroduce: '',
          remainTime: '',
          examFlag: false,
          InterValObj: null,
          loading2: true,
          testFlag: ''
        }
      },
      created () {
        this.$http({
          url: this.$http.adornUrl('/prepare/getExamPrepareData'),
          method: 'post'
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.truename = data.truename
            this.idcard = data.idcard
            this.examname = data.examname
            this.SysSecond = data.diffSecond
            this.examCourseList = data.examCourseList
            this.examIntroduce = data.examIntroduce
            this.testFlag = data.testFlag
            console.log("data.testFlag",data.testFlag)
            this.viewRemainTime()
            this.InterValObj = window.setInterval(this.SetRemainTime, 1000);
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      methods: {
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
            this.examFlag = false
          }else{
          	this.examFlag = true
          	this.loading2 = false
          	window.clearInterval(this.InterValObj)
          }
        },
        SetRemainTime () {
          if (this.SysSecond > 0) {
            this.SysSecond = this.SysSecond - 1
            this.viewRemainTime()
          }
        },
        startExam (courseId) {
          this.$router.replace({ name: 'paper', params: { cid: courseId}})
        },
        logoutHandle () {
          this.$confirm(`确定进行[退出]操作?`, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            clearLoginInfo()
            this.$router.push({ name: 'login' })
          }).catch(() => {})
        }
      }
    }
</script>

<style>
  .mod-home {
    line-height: 1.5;
  }
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
</style>

