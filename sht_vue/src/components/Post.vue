<template>
  <el-row>
    <el-card class="box-card">
      <el-row>
        <el-col :span="4">
          <div class="grid-content">
            <el-image
                :src="post.coverPictureUrl"
                :preview-src-list="[post.coverPictureUrl]"
                class="image"
                fit="cover"
                hide-on-click-modal
            />
          </div>
        </el-col>
          <el-col :span="20">
            <el-link class="_link" :underline="false" :href="'/postDetail/'+post.id">
              <el-row class="row-padding">
              <el-col :span="24">
                <el-row>
                  <el-col :span="20">
                    <div class="div-height center">
                        <h2>{{ post.name }}</h2>
                    </div>
                    <div class="div-height title">
                      <span>{{ post.title }}</span>
                    </div>
                    <div class="div-height content">
                      <span>{{ post.content }}</span>
                    </div>
                  </el-col>
                  <el-col :span="4">
                    <div class="number-height">
                      <span>x{{ post.amount }}</span>
                    </div>
                  </el-col>
                </el-row>
                <el-row style="height: 53px">
                  <el-col :span="8">
                    <div class="col-height">
                      <span>{{ post.quality.quality }}</span>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="col-height">
                      <span>{{ post.category.category }}</span>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <div class="col-height">
                      <span>{{ post.price }} 元</span>
                    </div>
                  </el-col>
                </el-row>
              </el-col>
          </el-row>
            </el-link>
        </el-col>
      </el-row>
    </el-card>

  </el-row>
</template>

<script>
export default {
  name: "post",
  props: {
    post: {
      type: Object,
      default: () => ({
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
      }),
    }
  }
}
</script>

<style >
  .image {
    height: 100%;
    display: block;
  }
  ._link {
    display: block;
    width: 100%;
  }
  .box-card {
    width: 100%;
  }
  .row-padding {
    padding-left: 20px;
  }
  .grid-content {
    border-radius: 4px;
    width: 100%;
    height: 100%;
  }
  .div-height {
    height: 60px;
    border-bottom: #d3dce6 1px solid;
  }
  .col-height {
    height: 100%;
    line-height: 100%;
  }
  .number-height {
    height: 180px;
    line-height: 180px;
    text-align: center;
  }
  .center {
    text-align: center;
    line-height: 60px;
  }
  .title {
    font-size: 18px;
    overflow: hidden;
  }
  .content {
    font-size: 16px;
    overflow: hidden;
  }
</style>