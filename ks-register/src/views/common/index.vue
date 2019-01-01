<template>
	<div>
  <div class="site-header hidden-xs">
    <div class="navbar">
        <!-- brand -->
        <div class="navbar-header">
            <a class="navbar-brand  active" href="https://www.kaoshixing.com/">
                <div class="logo-wrap"></div>
            </a>
        </div>
        
    </div>
</div>

<div class="m-site-header visible-xs">
    <div class="navbar">
        <!-- brand -->
        <div class="navbar-header clearfix">
            <a class="navbar-brand  active" href="https://www.kaoshixing.com/">
                <div class="logo-wrap"></div>
            </a>
            <div class="btn-show-navbar">
                <i class="icon icon-m_exam_drag"></i>
            </div>
        </div>
        <div class="right-tab">
            <div class="btn-close"><i class="icon icon-p_personal_cancel"></i></div>
        </div>
    </div>
</div>

<div class="site-main">
    <!-- jumbotron -->
    <div class="jumbotron" id="jumbotron">
        <!-- jumbotron-title/jumbotron-bg -->
        <div class="first-page">
            <div class="container">
                <div class="tryout">
                    <p class="title">最轻量级的在线考试系统 <span class="btn-play-video pc-btn-play-video hidden-xs"><i class="icon icon-m_exam_play"></i></span></p>
                    <span class="hidden-xs">95,000+企业的选择</span>
                    <span class="visible-xs">1分钟组织考试 <span class="btn-play-video m-btn-play-video"><i class="icon icon-m_exam_play"></i></span></span>
                    <div class="register-box">
                        <input  type="text" placeholder="请输入手机号" v-model="telephone">
                        <button @click="dataFormSubmit">免费注册</button>
                    </div>
                    <span class="hidden-xs">想让每一场考试都节约大量成本吗？</span>
                </div>
                <img src="/static/img/home-img-1.png" alt="">
            </div>
        </div>

            <!-- jumbotron-cases -->
        <div class="second-page cases">
            <div class="cases-wrap clearfix">
                <div class="cases-title">3步开始考试，满足不同场景</div>
                <div class="case-box">
                    <div class="case train">
                        <div class="case-icon"></div>
                        <div class="case-intro">
                            <div class="case-title">员工培训考试</div>
                            <div class="case-detail">考后直接出分析，方便快捷</div>
                        </div>
                    </div>
                </div>
                <div class="case-box">
                    <div class="case dealer">
                        <div class="case-icon"></div>
                        <div class="case-intro">
                            <div class="case-title">经销商考试</div>
                            <div class="case-detail">无时间地点约束，考评利器</div>
                        </div>
                    </div>
                </div>
                <div class="case-box">
                    <div class="case recruit">
                        <div class="case-icon"></div>
                        <div class="case-intro">
                            <div class="case-title">招聘考试</div>
                            <div class="case-detail">面试远程考试，高效初筛</div>
                        </div>
                    </div>
                </div>
                <div class="case-box">
                    <div class="case competition">
                        <div class="case-icon"></div>
                        <div class="case-intro">
                            <div class="case-title">知识竞赛</div>
                            <div class="case-detail">移动端完成考评，随时随地</div>
                        </div>
                    </div>
                </div>
                <div class="case-box">
                    <div class="case homework">
                        <div class="case-icon"></div>
                        <div class="case-intro">
                            <div class="case-title">课后作业</div>
                            <div class="case-detail">多题型支持，专业好用</div>
                        </div>
                    </div>
                </div>
                <div class="case-box">
                    <div class="case exercise">
                        <div class="case-icon"></div>
                        <div class="case-intro">
                            <div class="case-title">认证考试</div>
                            <div class="case-detail">开放平台API接口，无缝对接第三方数据</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- procedure -->
    <div class="procedure">
        <div class="step first-step">
            <div class="describe">
                <div class="title">
                    <i class="step-icon icons8-test-partial-passed"></i>
                    第一步：录题
                </div>
                <div class="intro">
                    支持多种题型，一键导入或订购题库
                </div>
                <div class="detail">
                    支持单选、多选、判断题、填空题、问答题、阅读题<br>
                    同时支持excel、word批量上传试题、图片
                </div>
            </div>
            <div class="sketch"></div>
        </div>
        <div class="step second-step">
            <div class="describe">
                <div class="title">
                    <i class="step-icon icons8-combi-ticket"></i>
                    第二步：创建考试
                </div>
                <div class="intro">设置考试名称、时间等内容</div>
                <div class="detail">设定题型分值出题比例</div>
            </div>
            <div class="sketch"></div>
        </div>
        <div class="step third-step">
            <div class="describe">
                <div class="title">
                    <i class="step-icon icons8-save-as"></i>
                    第三步：导入参考人员信息
                </div>
                <div class="intro">支持单个+批量导入考生信息</div>
                <div class="detail">
                    支持创建测试类型考生
                </div>
            </div>
            <div class="sketch"></div>
        </div>
    </div>
</div>
<div class="site-footer clearfix">
    
    <div class="container">
        <div class="footer-nav clearfix">
            <div class="nav-left">                
                <ul class="contact-group">
                    <li><a href="http://www.linghang-tech.com" target="_blank"><i class="icon-w_footer_icon_address"></i> &nbsp;&nbsp;大连领航世纪科技发展有限公司</a></li>
                    <li><a href="http://www.linghang-tech.com" target="_blank"><i class="icon-w_footer_icon_telephone"></i> &nbsp;&nbsp;13614093438</a></li>
                </ul>
            </div>
            <div class="nav-qrcode nav-right">
                <div class="title-qr hidden-xs">微信公众号</div>
                <div class="logo-qr">
                    <img src="http://www.linghang-tech.com/views/IMJ2V2/images/weixin.png">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer-bg"></div>
</div>
</template>

<script>
  export default {
    data () {
      return {
        telephone:''
      }
    },
    created () {},
    methods: {
      // 提交表单
      dataFormSubmit () {
        console.log("123123123")
        this.$http({
          url: this.$http.adornUrl('/openApi/register'),
          method: 'post',
          data: this.$http.adornData({
            'type': '1',
            'name': '',
            'telephone': this.telephone
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$router.replace({ name: 'registerInfo', params: { rrb: data.rrb} })
          } else {
            this.$message.error(data.msg)
          }
        })
      }
    }
  }
</script>

<style lang="scss">
  
</style>
  