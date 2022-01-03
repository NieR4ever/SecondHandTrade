<template>
  <nav-bar/>
  <el-card class="box-card">
    <div class="login">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="用户名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input
              v-model="form.password"
              type="password"
              autocomplete="off"
              show-password
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPassword">
          <el-input
              v-model="form.checkPassword"
              type="password"
              autocomplete="off"
              show-password
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">注册</el-button>
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
  name: "register",

  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('密码不能为空'))
      } else {
        if (this.form.checkPassword !== '') {
          this.$refs.ruleForm.validateField('checkPassword')
        }
        callback()
      }
    }
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.form.password) {
        callback(new Error("两次输入的密码不一致"))
      } else {
        callback()
      }
    }
    return {
      form: {
        name: '',
        password: '',
        checkPassword: ''
      },
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
            { required: true, validator: validatePass, trigger: 'blur' }
        ],
        checkPassword: [
            { required: true, validator: validatePass2, trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    onSubmit() {
      request({
        url: '/user/register',
        method: 'post',
        data: this.form
      }).then(data =>{
        let result = data.data
        console.log(result);
        if (result.data != null && result.data.userOperation != null) {
          ElMessage.success(result.msg)
          this.$router.push("/login")
        } else {
          ElMessage.error(result.msg)
        }
      })
    },
  },
  components: {
    NavBar
  }

}
</script>

<style scoped>
  .box-card {
    width: 480px;
    margin: 50px auto;
  }
</style>