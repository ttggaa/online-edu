<template>
	<div>
  		<div class="site-header hidden-xs">
		    <div class="navbar">
		        <!-- brand -->
		        <div class="navbar-header">
		            <a class="navbar-brand  active" >
		                <div class="logo-wrap"></div>
		            </a>
		        </div>
		        
		    </div>
		</div>
		
		<div class="m-site-header visible-xs">
		    <div class="navbar">
		        <!-- brand -->
		        <div class="navbar-header clearfix">
		            <a class="navbar-brand  active">
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
		                <div style="font-size: 25px;line-height: 40px;color:#fff;">
					  		恭喜您已经成功注册完成，请自行记录以下信息，页面关闭后信息将不再保留！
					  		<div>
					  			考务后台管理地址：<a :href="'http://' + rrb.domain + '.adminexam.linghang-tech.com'" target="_blank" style="color: #fff;">http://{{rrb.domain}}.adminexam.linghang-tech.com</a>
					  			<div>
					  				账号：{{rrb.adminLoginName}}
					  				密码：{{rrb.adminPassword}}
					  			</div>
					  		</div>
					  		
					  		<div>
					  			在线考试系统地址：<a :href="'http://' + rrb.domain + '.exam.linghang-tech.com'" target="_blank" style="color: #fff;">http://{{rrb.domain}}.exam.linghang-tech.com</a>
					  		</div>
					    </div>
					    
		            </div>
		        </div>
		
		    </div>
		</div>
		<div class="site-footer clearfix backdif">
    
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
	</div>
</template>

<script>
  export default {
    data () {
      return {
        rrb:{}
      }
    },
    created () {
    	this.rrb = this.$route.params.rrb
    	console.log("rrb=",this.rrb)
    },
    methods: {
      
    }
  }
</script>

<style lang="scss">
  
</style>
  