<template>
	<div>
  		恭喜您已经成功注册完成，请自行记录以下信息，页面关闭后信息将不再保留！
  		<div>
  			考务后台管理地址：<a :href="'http://' + rrb.domain + '.adminexam.linghang-tech.com'" target="_blank">http://{{rrb.domain}}.adminexam.linghang-tech.com</a>
  			<div>
  				账号：{{rrb.adminLoginName}}
  				密码：{{rrb.adminPassword}}
  			</div>
  		</div>
  		
  		<div>
  			在线考试系统地址：<a :href="'http://' + rrb.domain + '.exam.linghang-tech.com'" target="_blank">http://{{rrb.domain}}.exam.linghang-tech.com</a>
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
  