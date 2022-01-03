<template>
  <div>
    <el-tabs type="border-card" :stretch="true">
      <el-tab-pane label="挂卖帖">
        <post v-for="post in sellPosts" :key="post.id" :post="post" class="divider"></post>
        <el-pagination class="_center"
                       @size-change="handleSizeChange1"
                       @current-change="handleCurrentChange1"
                       :current-page="pageNum1"
                       :page-sizes="[5,10,20]"
                       :page-size="pageSize1"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="total1"
                       background
        >
        </el-pagination>
      </el-tab-pane>
      <el-tab-pane label="求购帖">
        <post v-for="post in buyPosts" :key="post.id" :post="post"></post>
        <el-pagination class="_center"
                       @size-change="handleSizeChange2"
                       @current-change="handleCurrentChange2"
                       :current-page="pageNum2"
                       :page-sizes="[5,10,20]"
                       :page-size="pageSize2"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="total2"
                       popper-class="每页"
                       background
        >
        </el-pagination>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Post from '@/components/Post'
import {request} from '@/network/request'

export default {
  name: "index-content",
  components: {
    Post
  },
  data() {
    return {
      sellPosts: [
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
      buyPosts: [
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
      pageNum1: 1,
      pageSize1: 5,
      total1: 0,
      pageNum2: 1,
      pageSize2: 5,
      total2: 0
    }
  },
  methods: {
    handleSizeChange1(value) {
      this.pageNum1 = Math.ceil(value/this.pageSize1)
      this.pageSize1 = value
      request({
        url: '/post/getPostByPostCategory',
        params: {
          "postCategory": 0,
          "pageNum": this.pageNum1,
          "pageSize": this.pageSize1
        }
      }).then(data => {
        let result = data.data
        console.log(result.data.posts);
        if (result.data.posts.length > 0) {
          this.sellPosts = result.data.posts
        }
      })
    },
    handleCurrentChange1(val) {
      this.pageNum1 = val
      request({
        url: '/post/getPostByPostCategory',
        params: {
          "postCategory": 0,
          "pageNum": this.pageNum1,
          "pageSize": this.pageSize1
        }
      }).then(data => {
        let result = data.data
        console.log(result.data.posts);
        if (result.data.posts.length > 0) {
          this.sellPosts = result.data.posts
        }
      })
    },
    handleSizeChange2(value) {
      this.pageSize2 = value
    },
    handleCurrentChange2(val) {
      this.pageNum2 = val
      request({
        url: '/post/getPostByPostCategory',
        params: {
          "postCategory": 1,
          "pageNum": this.pageNum2,
          "pageSize": this.pageSize2
        }
      }).then(data => {
        let result = data.data
        console.log(result.data.posts);
        if (result.data.posts.length > 0) {
          this.buyPosts = result.data.posts
        }
      })
    },
  },
  created() {
    request({
      url: '/post/getPostByPostCategory',
      params: {
        "postCategory": 0,
        "pageNum": this.pageNum1,
        "pageSize": this.pageSize1
      }
    }).then(data => {
      console.log(data.data.data.total);
      this.sellPosts = data.data.data.posts
      this.total1 = data.data.data.total
    })
    request({
      url: '/post/getPostByPostCategory',
      params: {
        "postCategory": 1,
        "pageNum": this.pageNum2,
        "pageSize": this.pageSize2
      }
    }).then(data => {
      this.buyPosts = data.data.data.posts
      this.total2 = data.data.data.total
    })
  }
}
</script>

<style scoped>
._center {
  display: flex;
  justify-content: center;
}
.divider {
  margin-top: 10px;
}
</style>