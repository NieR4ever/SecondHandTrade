<template>
  <div id="app">
    <router-view name="body"/>
  </div>
  <el-backtop />
</template>

<script>


import store from "@/store";

export default {
  name: 'App',
  store,
}
</script>