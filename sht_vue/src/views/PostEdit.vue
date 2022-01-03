<template>
  <nav-bar></nav-bar>
  <el-card class="box-card">
    <el-row justify="center">
      <el-col :span="20">
        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="图片">
            <el-upload
                class="upload-demo"
                drag
                action="http://localhost:8081/upload/coverPicture"
                :on-remove="handleRemove"
                :on-success="handleSuccess"
                :on-exceed="handleExceed"
                list-type="picture"
                :thumbnail-mode="true"
                :limit="1"
                :auto-upload="true"
            >
              <el-icon class="el-icon--upload"><upload-filled /></el-icon>
              <div class="el-upload__text">
                拖拽文件到此处 或 <em>点击上传</em>
              </div>
            </el-upload>
          </el-form-item>
          <el-form-item label="标题">
            <el-input v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="内容">
            <el-input v-model="form.content" type="textarea"></el-input>
          </el-form-item>
          <h3>商品详细信息</h3>
          <el-form-item label="商品名">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="类别">
            <el-select
                v-model="form.category.id"
                filterable
                :multiple-limit="1"
                clearable
                remote
                :remote-method="queryCategory"
                :loading="loading"
                :no-data-text="categoryMsg"
                loading-text="正在加载"
                placeholder="选择分类"
            >
              <el-option
                  v-for="item in categoryOptions"
                  :key="item.id"
                  :label="item.category"
                  :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="关键词">
            <el-input v-model="form.keywords" placeholder="关键词之间用|分割"></el-input>
          </el-form-item>
          <el-form-item label="成色">
            <el-select
                v-model="form.quality.id"
                filterable
                :multiple-limit="1"
                clearable
                remote
                :remote-method="queryQuality"
                :loading="loading"
                :no-data-text="qualityMsg"
                loading-text="正在加载"
                placeholder="选择成色"
            >
              <el-option
                  v-for="item in qualityOptions"
                  :key="item.id"
                  :label="item.quality"
                  :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="数量">
            <el-input-number v-model="form.amount" :min="0" />
          </el-form-item>
          <el-form-item label="价格">
            <el-input v-model="form.price"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="send" v-if="isSave">发布</el-button>
            <el-button @click="send" v-if="!isSave">修改</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
import NavBar from '@/components/NavBar'
import { Plus, ZoomIn, Download, Delete, UploadFilled  } from '@element-plus/icons'
import {ElMessage} from "element-plus";
import {request} from "@/network/request";
export default {
  name: "PostEdit",
  components: {
    Plus,
    ZoomIn,
    Download,
    Delete,
    UploadFilled,
    NavBar
  },
  created() {
    this.initOption()
    this.checkUpdate()
  },
  data() {
    return {
      form: {
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
      categoryOptions: [],
      qualityOptions: [],
      loading: false,
      categoryMsg: '',
      qualityMsg: '',
      isSave: true
    }
  },
  methods: {
    checkUpdate() {
      let array = this.$route.path.split("/");
      if (array[array.length-1].match("^[0-9]+$")) {
        request({
          url: '/post/'+ array[array.length-1],
        }).then(data => {
          this.form = data.data.data.post
          this.isSave = false
        })
      }
    },
    initOption() {
      this.form.user.id = JSON.parse(sessionStorage.getItem("user")).id || 0
      this.form.postCategory = this.$route.query.postCategory
      console.log(this.$route.query.postCategory);
      request({
        url: '/category/findAllCategories'
      }).then(data => {
        this.categoryOptions = data.data.data.categories
      })
      request({
        url: '/quality/findAllQualities'
      }).then(data => {
        this.qualityOptions = data.data.data.qualities
      })
    },
    queryCategory(query) {
      if (query !== '') {
        this.loading = true
        request({
          url: '/category/findCategoriesByName',
          params: {
            "category": query
          }
        }).then(data => {
          console.log(data.data);
          let categories = data.data.data.categories
          this.categoryMsg = data.data.msg
          if (categories != null) {
            this.categoryOptions = categories
          } else {
            this.categoryOptions = []
          }
          this.loading = false
        })
      } else {
        this.loading = true
        request({
          url: '/category/findAllCategories'
        }).then(data => {
          console.log(data.data);
          let categories = data.data.data.categories
          this.categoryMsg = data.data.msg
          if (categories != null) {
            this.categoryOptions = categories
          } else {
            this.categoryOptions = []
          }
          this.loading = false
        })
      }
    },
    queryQuality(query) {
      if (query !== '') {
        this.loading = true
        request({
          url: '/quality/findqualitiesByName',
          params: {
            "quality": query
          }
        }).then(data => {
          console.log(data.data);
          let qualities = data.data.data.qualities
          this.qualityMsg = data.data.msg
          if (qualities != null) {
            this.qualityOptions = qualities
          } else {
            this.qualityOptions = []
          }
          this.loading = false
        })
      } else {
        this.loading = true
        request({
          url: '/quality/findAllQualities'
        }).then(data => {
          console.log(data.data);
          let qualities = data.data.data.qualities
          this.qualityMsg = data.data.msg
          if (qualities != null) {
            this.qualityOptions = qualities
          } else {
            this.qualityOptions = []
          }
          this.loading = false
        })
      }
    },
    handleRemove() {
      this.form.coverPictureUrl = ''
    },

    handleSuccess(res) {
      console.log(res.data.coverPictureUrl);
      this.form.coverPictureUrl = res.data.coverPictureUrl
    },
    handleExceed() {
      ElMessage.error("最多上传一张图片")
    },
    send() {
      console.log(this.form);
      request({
        url: '/post/doPost',
        method: 'post',
        data: this.form
      }).then(data => {
        let msg = data.data.msg
        if (data.data.errorCode === 0){
          ElMessage.success(msg)
          this.$router.push("/")
        } else {
          ElMessage.error(msg)
        }
      })
    },
  }
}
</script>

<style scoped>
  .box-card {
    width: 800px;
    margin: 0 auto;
  }
</style>