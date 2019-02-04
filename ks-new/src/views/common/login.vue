<template>
  <div class="site-wrapper site-page--login">
    <div class="site-content__wrapper login_back" v-bind:style="{'background-image': background}">
      <div class="site-content">
        <div class="brand-info">
          <h2 class="brand-info__text">{{proName}}</h2>
          <p class="brand-info__intro">{{summary}}</p>
        </div>
        <div class="login-main">
          <h3 class="login-title">考生登录</h3>
          <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon>
            <el-form-item prop="idcard">
              <el-input v-model="dataForm.idcard" placeholder="准考证号"></el-input>
            </el-form-item>
            <el-form-item prop="truename">
              <el-input v-model.trim="dataForm.truename" type="text" placeholder="姓名"></el-input>
            </el-form-item>            
            <el-form-item>
              <el-button class="login-btn-submit" type="primary" @click="dataFormSubmit()">登录</el-button>
            </el-form-item>
            <el-form-item v-if="footerViewFlag==1">
              <p class="brand-info__intro" style="text-align: center;">
              	<a href="http://www.linghang-tech.com" target="_blank" style="color:#0055AA;">大连领航世纪科技发展有限公司 技术支持</a>
              </p>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
	import screenfull from 'screenfull'
  export default {
    data () {
      return {
      	proName: '',
        summary: '',
        background: '',
        footerViewFlag: '',
        isFullscreen: false,
        dataForm: {
          idcard: '',
          truename: ''
        },
        dataRule: {
          idcard: [
            { required: true, message: '准考证号不能为空', trigger: 'blur' }
          ],
          truename: [
            { required: true, message: '姓名不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    created () {
    	this.$http({
          url: this.$http.adornUrl('/login/reqLoginInfo'),
          method: 'post',
          data: this.$http.adornData({
            'd': 1
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.proName = data.lcb.proName
            this.summary = data.lcb.summary
            this.background = 'url(' + data.lcb.background + ')'
            this.footerViewFlag = data.lcb.footerViewFlag
          } else {
            this.$message.error(data.msg)
          }
        })
    },
    methods: {
      // 提交表单
      dataFormSubmit () {
      	this.buttoncli()
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl('/login/login'),
              method: 'post',
              data: this.$http.adornData({
                'idcard': this.dataForm.idcard,
                'truename': this.dataForm.truename
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$cookie.set('token', data.token)
                this.$router.replace({ name: 'home' })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      },
      buttoncli () {
        if (!screenfull.enabled) { // 如果不允许进入全屏，发出不允许提示
      	  this.isFullscreen = false
          return false
        }
        this.isFullscreen = true
        screenfull.request()
      }
    }
  }
</script>

<style lang="scss">
  .site-wrapper.site-page--login {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-color: rgba(38, 50, 56, .6);
    overflow: hidden;
    .login_back{
    	position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      content: "";
      background-size: cover;
      background-size: 78% 100%;
    }
    .site-content__wrapper {
      position: absolute;
      top: 0;
      right: 0;
      bottom: 0;
      left: 0;
      padding: 0;
      margin: 0;
      overflow-x: hidden;
      overflow-y: auto;
      background-color: transparent;
    }
    .site-content {
      min-height: 100%;
      padding: 30px 500px 30px 30px;
    }
    .brand-info {
      margin: 220px 100px 0 90px;
      color: #fff;
    }
    .brand-info__text {
      margin:  0 0 22px 0;
      font-size: 48px;
      font-weight: 400;
      text-transform : uppercase;
    }
    .brand-info__intro {
      margin: 10px 0;
      font-size: 16px;
      line-height: 1.58;
      opacity: .6;
    }
    .login-main {
      position: absolute;
      top: 0;
      right: 0;
      padding: 150px 60px 180px;
      width: 470px;
      min-height: 100%;
      background-color: #fff;
    }
    .login-title {
      font-size: 16px;
    }
    .login-btn-submit {
      width: 100%;
      margin-top: 38px;
    }
    
 
		@media screen and (min-width: 960px) and (max-width: 1199px) {
			.el-input__inner{
				height: 69px;
			}
			.login-title {
				font-size: 30px;
			}
		}
		 
		 
		@media screen and (min-width: 768px) and (max-width: 959px) {
			.el-input__inner{
				height: 69px;
			}
			.login-title {
				font-size: 30px;
			}
		}
		 
		 
		@media only screen and (min-width: 480px) and (max-width: 767px){
			.el-input__inner{
				height: 69px;
			}
			.login-title {
				font-size: 30px;
			}
		}
		 
		 
		@media only screen and (max-width: 479px) {
			.brand-info__text {
				font-size: 58px;
			}
			.el-input__inner{
				height: 69px;
			}
			.login-title {
				font-size: 30px;
			}
			.el-input--medium {
				font-size: 24px;
			}
			.login-btn-submit{
				font-size: 30px;
			}
			.brand-info__intro {
				font-size: 27px;
			}
		}
  }
</style>
