<template>
  <nav-bar/>
  <el-card class="box-card">
    <div class="login">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="用户名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-card>
</template>

<script>
import NavBar from '@/components/NavBar'
import {request} from "@/network/request";
import {ElMessage} from "element-plus";
export default {
  name: "login",
  data() {
    return {
      form: {
        name: '',
        password: ''
      },
      isLogin: null,
      rules: {
        name: [
          {
            required: true,
            message: '用户名必填',
            trigger: 'blur',
          },
          {
            min: 1,
            max: 10,
            message: '用户名长度必须在1到10之间',
            trigger: 'blur',
          },
        ],
        password: [
          {
            required: true,
            message: '密码必填',
            trigger: 'blur',
          },
          {
            min: 1,
            max: 10,
            message: '密码长度必须在1到10之间',
            trigger: 'blur',
          }
        ]
      }
    }
  },
  methods: {
    onSubmit() {
      request({
        url: '/user/login',
        method: 'post',
        data: this.form
      }).then(data => {
        console.log(data.data);
        let user = null
        if (data.data.data != null) {
          user = data.data.data.user
        }
        if(user !=null) {
          sessionStorage.setItem("user",JSON.stringify(user))
        }
        if (sessionStorage.getItem("user")!=null) {
          ElMessage.success(data.data.msg)
          this.$router.push("/")
          this.isLogin = true
        } else {
          ElMessage.error(data.data.msg)
          this.isLogin = false
        }
      })
    },
  },
  components: {
    NavBar
  },

}
</script>

<style scoped>
  .box-card {
    width: 480px;
    margin: 50px auto;
  }
</style>