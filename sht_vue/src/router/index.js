import { createRouter, createWebHistory } from 'vue-router'
const Index = () => import('@/views/Index');
const Login = () => import('@/views/Login');
const Register = () => import('@/views/Register');
const PostDetail = () => import('@/views/PostDetail');
const PostEdit = () => import('@/views/PostEdit');
const Profile = () => import('@/views/Profile');
const SearchDetail = () => import('@/views/SearchDetail');
const routes = [
  {
    path: '/',
    name: 'Index',
    components: {
      "body": Index
    }
  },
  {
    path: '/login',
    name: 'Login',
    components: {
      "body": Login
    }
  },
  {
    path: '/register',
    name: 'Register',
    components: {
      "body": Register
    }
  },
  {
    path: '/postDetail/:id',
    name: 'PostDetail',
    components: {
      "body": PostDetail
    }
  },
  {
    path: '/postEdit/:id',
    name: 'PostEdit',
    components: {
      "body": PostEdit
    }
  },
  {
    path: '/postAdd',
    name: 'PostAdd',
    components: {
      "body": PostEdit
    }
  },
  {
    path: '/search',
    name: 'SearchDetail',
    components: {
      "body": SearchDetail
    }
  },
  {
    path: '/profile/:id',
    name: 'Profile',
    components: {
      "body": Profile
    }
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

router.beforeEach((to, from, next) => {
  // to: Route: 即将要进入的目标 路由对象
  // from: Route: 当前导航正要离开的路由
  // next: Function: 一定要调用该方法来 resolve 这个钩子。执行效果依赖 next 方法的调用参数。

  const nextRoute = ['PostEdit', 'PostAdd', 'Profile']
  let isLogin = sessionStorage.getItem("user") != null  // 是否登录
  // 未登录状态；当路由到nextRoute指定页时，跳转至login
  if (nextRoute.indexOf(to.name) >= 0) {
    if (!isLogin) {
      router.push('/login')
    }
  }
  // 已登录状态；当路由到login时，跳转至home
  if (to.name === 'login') {
    if (isLogin) {
      router.push({ name: 'index' })
    }
  }
  next();
});

export default router
