<template>
  <el-container>
    <el-header>
      <nav-bar></nav-bar>
    </el-header>
    <el-main>
      <index-content ></index-content>
    </el-main>
  </el-container>

</template>

<script>
import NavBar from '@/components/NavBar'
import IndexContent from '@/views/index/IndexContent'
export default {
  name: "index",
  components: {
    NavBar,IndexContent
  },

}
</script>

<style scoped>

</style>