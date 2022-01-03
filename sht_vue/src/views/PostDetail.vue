<template>
  <nav-bar></nav-bar>
  <el-row justify="center">
    <el-col :span="20">
      <el-card class="box-card">
        <el-image
          :src="post.coverPictureUrl"
          :preview-src-list="[post.coverPictureUrl]"
          class="image"
          fit="cover"
          hide-on-click-modal
        />
        <h2 class="_title">{{ post.title }}</h2>
        <el-divider></el-divider>
        <p class="_content">{{ post.content }}</p>
      </el-card>
    </el-col>
  </el-row>
  <el-row justify="center">
    <el-button type="primary" round v-if="!isOwner">聊天</el-button>
    <el-button-group v-if="isOwner">
      <el-button type="primary" :icon="Edit" @click="updatePost">修改</el-button>
      <el-popconfirm
          title="你确定要删除吗?"
          confirmButtonText="确认删除"
          cancel-button-text="取消"
          confirmButtonType="danger"
          @confirm="deletePost"
      >
        <template #reference>
          <el-button type="primary" :icon="Delete">删除</el-button>
        </template>
      </el-popconfirm>
    </el-button-group>
  </el-row>

</template>

<script>
import NavBar from '@/components/NavBar'
import Post from '@/components/Post'
import { Edit , Delete } from '@element-plus/icons'
import {request} from "@/network/request";
import {ElMessage} from "element-plus";
export default {
  name: "PostDetail",
  components: {
    NavBar,
    Post
  },
  data() {
    return {
      Edit,
      Delete,
      post: {
        id: 0,
        user: {
          id: 0
        },
        title: '',
        content: '',
        coverPictureUrl: '',
        price: 0,
        category: {
          id: ''
        },
        quality: {
          id: ''
        },
        postCategory: 1,
        keywords: '',
        amount: 0,
        name: ''
      },
    }
  },
  created() {
    this.initPost()
  },
  methods: {
    initPost() {
      console.log(this.$route.path.split("/"));
      let array = this.$route.path.split("/")
      let postId = array[array.length-1]
      request({
        url: '/post/'+postId
      }).then(data => {
        this.post = data.data.data.post
      })
    },
    updatePost() {
      this.$router.push("/postEdit/"+this.post.id)
    },
    deletePost() {
      console.log(111);
      request({
        url: '/post/doPost',
        method: 'delete',
        params: {
          id: this.post.id
        }
      }).then(data => {
        let result = data.data
        if (result.errorCode === 0) {
          ElMessage.success(result.msg)
          this.$router.push("/")
        } else {
          ElMessage.error(result.msg)
        }
      })
    }
  },
  computed: {
    isOwner() {
      if (sessionStorage.getItem("user")) {
        if (JSON.parse(sessionStorage.getItem("user")).id === this.post.user.id) {
          return true
        } else {
          return false
        }
      } else {
        return false
      }
    }
  }
}
</script>

<style scoped>
  .box-card {
    width: 100%;
    margin: 0 auto;
    min-height: 720px;
  }
  .image {
    width: 200px;
    margin: 0 auto;
  }
  ._title {
    text-align: center;
  }
  ._content {
    padding: 20px;
  }
</style>