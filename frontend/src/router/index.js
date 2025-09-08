import { createRouter, createWebHistory } from 'vue-router'

// 页面级组件
import Home from '../views/Home.vue'
import Blog from '../views/Blog.vue'
import Profile from '../views/Profile.vue'
import BlogDetail from '../views/BlogDetail.vue'
import AddBlog from '../views/AddBlog.vue'

const routes = [
  // 首页
  { path: '/', name: 'Home', component: Home },

  // 博客列表页
  { path: '/blog', name: 'Blog', component: Blog },

  // 个人资料页
  { path: '/profile', name: 'Profile', component: Profile },

  // 博客详情页
  {
    path: '/blog/:id',
    name: 'BlogDetail',
    component: BlogDetail,
    props: true // ✅ 让组件直接接收 id 参数
    // 建议：
    // 在组件内部可以直接用 props.id 获取文章ID，也可以继续用 useRoute().params.id，两种方式都支持
    // 跳转示例：router.push({ name: 'BlogDetail', params: { id: post.id } })
  },

  // 新建博客页
  {
    path: '/add-blog', // ✅ 建议将路径明确为 /add-blog，避免 /add 太模糊
    name: 'AddBlog',
    component: AddBlog
    // 建议：
    // 这个页面用于创建新文章，和后端 POST /api/posts 对接
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 全局导航守卫（可选）
// 如果后续增加登录认证，可以在这里判断用户是否登录，再决定是否允许访问某些路由

export default router
