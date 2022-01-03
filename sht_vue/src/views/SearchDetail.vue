<template>
  <nav-bar></nav-bar>
  <el-card class="box-card">
    <post v-for="post in posts" :key="post.id" :post="post" class="divider"></post>
    <el-pagination class="_center"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="pageNum"
                   :page-sizes="[5,10,20]"
                   :page-size="pageSize"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="total"
                   background
    >
    </el-pagination>
  </el-card>
</template>

<script>
import NavBar from "@/components/NavBar";
import {request} from "@/network/request";
import Post from "@/components/Post";
export default {
  name: "SearchDetail",
  components: {NavBar,Post},
  data(){
    return {
      keywords: '',
      postCategory: 0,
      pageNum: 1,
      pageSize: 5,
      total: 0,
      posts: [
        {
          id: 0,
          user: {
            id: '',
            name: "",
            password: "",
            phone: '',
            email: null,
            description: null,
            avatarUrl: null,
            gender: 0,
            likeCount: 0,
            collectedCount: 0,
            followCount: 0,
            followedCount: 0,
            userAddressList: [
              {
                id: 2,
                userId: 2,
                address: "",
                defaultSign: 0
              }
            ]
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
        }
      ],
    }
  },
  methods: {
    handleSizeChange(value) {
      this.pageNum = Math.ceil(value/this.pageSize)
      this.pageSize = value
      request({
        url: '/post/searchPost',
        params: {
          "keywords": this.keywords,
          "postCategory": this.postCategory,
          "pageNum": this.pageNum,
          "pageSize": this.pageSize
        }
      }).then(data => {
        let result = data.data
        console.log(result.data.posts);
        if (result.data.posts.length > 0) {
          this.posts = result.data.posts
        }
      })
    },
    handleCurrentChange(val) {
      this.pageNum = val
      request({
        url: '/post/searchPost',
        params: {
          "keywords": this.keywords,
          "postCategory": this.postCategory,
          "pageNum": this.pageNum,
          "pageSize": this.pageSize
        }
      }).then(data => {
        let result = data.data
        console.log(result.data.posts);
        if (result.data.posts.length > 0) {
          this.posts = result.data.posts
        }
      })
    },
  },
  created() {
    this.keywords = this.$route.params.keywords
    this.postCategory = this.$route.params.postCategory
    request({
      url: '/post/searchPost',
      params: {
        "keywords": this.keywords,
        "postCategory": this.postCategory,
        "pageNum": this.pageNum,
        "pageSize": this.pageSize
      }
    }).then(data => {
      console.log(data.data.data.total);
      this.posts = data.data.data.posts
      this.total = data.data.data.total
    })
  }
}
</script>

<style scoped>
  .box-card {
    width: 90%;
    margin: 0 auto;
  }
  ._center {
    display: flex;
    justify-content: center;
  }
  .divider {
    margin-top: 10px;
  }
</style>