<template>
  <el-menu mode="horizontal" style="justify-content: center">
    <el-space wrap :size="size">
      <el-menu-item index="1">
        <h2>
          <el-link href="/">二手信息发布平台</el-link>
        </h2>
      </el-menu-item>
      <el-input
          placeholder="搜索关键字"
          clearable
          v-model="keywords"
          :prefix-icon="Search"
      >
        <template #prepend>
          <el-select v-model="postCategory" placeholder="分类" style="width: 100px">
            <el-option label="挂卖帖" value="0"></el-option>
            <el-option label="求购帖" value="1"></el-option>
          </el-select>
        </template>
        <template #append>
          <el-popover
              placement="bottom"
              title="注意"
              :width="200"
              trigger="hover"
              content="你还没有选择分类"
              :disabled="isSelect"
          >
            <template #reference>
              <el-button :icon="Search" type="info" :disabled="!isInput" @click="searchPosts"></el-button>
            </template>
          </el-popover>
        </template>
      </el-input>
      <el-menu-item>
        <el-dropdown>
        <el-button type="text" class="_color">
          发布<el-icon><arrow-down/></el-icon>
        </el-button>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>
                <el-link href="/postAdd?postCategory=0">挂卖帖</el-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-link href="/postAdd?postCategory=1">求购帖</el-link>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-menu-item>
      <el-menu-item index="4" v-if="!isLogin">
        <el-link href="/login" :underline="false">登录</el-link>
      </el-menu-item>
      <el-menu-item index="5" v-if="!isLogin">
        <el-link href="/register" :underline="false">注册</el-link>
      </el-menu-item>
      <el-menu-item index="4" v-if="isLogin">
        <el-dropdown>
        <span class="el-dropdown-link keep-line">
          <el-space>
            <span>{{ username }}</span>
            <el-image style="width: 50px; height: 50px; border-radius: 50%" fit="cover" :src="userAvatarUrl"/>
            <!--            <el-icon><arrow-down/></el-icon>-->
          </el-space>

        </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>
                <el-button type="text" @click="toProfile">个人中心</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button type="text" @click="logout">退出</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-menu-item>
    </el-space>
  </el-menu>

</template>

<script>
import {Search, ArrowDown} from '@element-plus/icons'
import {request} from "@/network/request";

export default {
  name: "nav-bar",
  data() {
    return {
      size: 20,
      keywords: '',
      postCategory: '',
      username: '',
      userId: 0,
      userAvatarUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      Search,
    }
  },
  components: {
    ArrowDown
  },
  methods: {
    toProfile() {
      this.$router.push("/profile/" + this.userId)
    },
    logout() {
      request({
        url: '/user/logout'
      }).then(data => {
        sessionStorage.removeItem("user")
        this.$router.push('/login')
      })
    },
    searchPosts() {
      if (!this.isSelect) {
        return false
      }
      this.$router.push({
        // path:'/search',
        name: 'SearchDetail',
        params: {
          "keywords": this.keywords,
          "postCategory": this.postCategory,
        }
      })
    }
  },
  created() {
    if (sessionStorage.getItem("user") != null) {
      let user = JSON.parse(sessionStorage.getItem("user"))
      this.username = user.name
      this.userId = user.id
      this.userAvatarUrl = user.avatarUrl
    }
  },
  computed: {
    isLogin() {
      return sessionStorage.getItem("user") != null
    },
    isSelect() {
      return this.postCategory.length > 0
    },
    isInput() {
      return this.keywords.trim().length > 0
    }
  }
}
</script>

<style scoped>
.keep-line {
  line-height: 40px;
}
._color {
  color: var(--el-text-color-regular);
}
._color:hover {
  color: var(--el-color-primary);
}
</style>