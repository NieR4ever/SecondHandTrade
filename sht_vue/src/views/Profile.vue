<template>
  <nav-bar v-if="!this.isChange"></nav-bar>
  <el-card class="box-card">
    <el-row>
      <div class="_center">
        <el-space direction="vertical">
          <div class="block"
               @mouseenter="changeAvatarHover"
               @mouseleave="changeAvatarHover"
          >
            <el-image
                style="width: 100px; height: 100px; border-radius: 50%"
                fit="cover"
                :src="user.avatarUrl"
            >
            </el-image>
            <el-upload
                class="mask"
                action="http://localhost:8081/upload/avatar"
                :on-success="changeAvatar"
                :show-file-list="false"
                v-show="this.avatarHover"
            >
              <el-button type="text" class="text">更换头像</el-button>
            </el-upload>
          </div>
          <el-space :size="20" spacer="|">
            <span>{{ user.name }}</span>
            <el-button type="text" @click="this.centerDialogVisible=true">修改信息</el-button>
          </el-space>
          <div>{{ user.description }}</div>
        </el-space>
        <el-dialog
            v-model="centerDialogVisible"
            title="修改个人信息"
            width="60%"
            :destroy-on-close="true"
            center
        >
          <template #default>
            <el-form ref="form" v-model="form" label-width="120px">
              <el-form-item label="电话号码">
                <el-input type="number" v-model="form.phone"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input type="email" v-model="form.email"></el-input>
              </el-form-item>
              <el-form-item label="个性签名" >
                <el-input type="textarea" v-model="form.description"></el-input>
              </el-form-item>
            </el-form>
          </template>
          <template #footer>
            <span>
              <el-button @click="centerDialogVisible = false">取消</el-button>
              <el-button type="primary" @click="updateUser">修改</el-button>
            </span>
          </template>
        </el-dialog>
      </div>

    </el-row>
    <el-card>
      <el-timeline>
        <el-timeline-item v-for="post in posts" :timestamp="post.createDate" placement="top">
          <post :key="post.id" :post="post"></post>
        </el-timeline-item>
      </el-timeline>
      <div style="width: 200px;margin: 0 auto" v-if="posts.length===0">你还没有发过帖子哦</div>
    </el-card>
  </el-card>
</template>

<script>
import NavBar from "@/components/NavBar";
import Post from "@/components/Post";
import {request} from "@/network/request";
import {ElMessage} from "element-plus";

export default {
  name: "profile",
  components: {
    NavBar,
    Post,
  },
  data() {
    return {
      centerDialogVisible: false,
      avatarHover: false,
      isChange: false,

      user: {
        id: 0,
        name: "",
        phone: 0,
        email: null,
        description: null,
        avatarUrl: '',
        gender: 0,
        userAddressList: []
      },
      form: {
        id: '',
        phone: '',
        email: '',
        description: ''
      },
      posts: [
        {
          user: {
            id: 0
          },
          createDate: "",
          title: "",
          content: "",
          scanCount: 0,
          sold: 0,
          coverPictureUrl: "",
          price: 0,
          category: {
            id: 0,
            category: ""
          },
          quality: {
            id: 0,
            quality: ""
          },
          postCategory: 0,
          keywords: "",
          amount: 0,
          name: ""
        },
      ]
    }
  },
  created() {
    this.initProFile()
  },
  methods: {
    updateUser() {
      this.form.id = this.user.id
      request({
        url: '/user/'+ this.user.id,
        method: 'put',
        data: this.form
      }).then(data => {
        let result = data.data
        console.log(result);
        if (result.errorCode === 0) {
          ElMessage.success("修改成功")
          this.user.phone = this.form.phone
          this.user.email = this.form.email
          this.user.description = this.form.description
          this.centerDialogVisible = false
        } else {
          ElMessage.error("修改失败")
        }
      })
    },
    changeAvatar(res) {
      request({
        url: '/user/' + this.user.id,
        method: 'put',
        data: {
          "id": this.user.id,
          "avatarUrl": res.data.avatarUrl
        }
      }).then(data => {
        this.isChange = true
        return request({
          url: '/user/' + this.user.id,
        })
      }).then(data => {
        this.isChange = false
        let result = data.data
        if (result.errorCode === 0) {
          console.log(111);
          this.user = result.data.user
          sessionStorage.setItem("user", JSON.stringify(this.user))
        }
      })
    },
    changeAvatarHover() {
      this.avatarHover = !this.avatarHover
    },
    initProFile() {
      let userId = this.$route.params.id
      request({
        url: '/user/' + userId
      }).then(data => {
        let result = data.data
        if (result.errorCode === 0) {
          sessionStorage.setItem("user", JSON.stringify(result.data.user))
          this.user = JSON.parse(sessionStorage.getItem("user"))
          console.log(this.user.avatarUrl);
          this.posts = result.data.posts
          this.form.phone = this.user.phone
          this.form.email = this.user.email
          this.form.description = this.user.description
        }
      })
    }
  },
  computed: {}
}
</script>

<style scoped>
.text {
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  width: 100px;
  height: 100px;
}

.block {
  position: relative;
  width: 100px;
  height: 100px;
  border-radius: 50%;
}

.box-card {
  width: 90%;
  margin: 0 auto;
}

.mask {
  display: block;
  position: absolute;
  border-radius: 50%;
  text-align: center;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: gray;
  z-index: 1000;
  opacity: 0.6;
}

._center {
  margin: 0 auto;
}
</style>