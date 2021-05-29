import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: resolve => require(['../pages/Login.vue'], resolve)
    },
    {
      path: '/SignUp',
      component: resolve => require(['../pages/SignUp.vue'], resolve)
    },
    {
      path: '/Home',
      component: resolve => require(['../components/Home.vue'], resolve),
      children:[
        {
          path: '/Info',
          component: resolve => require(['../pages/InfoPage.vue'], resolve)
        },
       
        {
          path: '/Consumer',
          component: resolve => require(['../pages/ConsumerPage.vue'], resolve)
        },
        {
          path: '/Uploader',
          component: resolve => require(['../pages/UploaderPage.vue'], resolve)
        },
      
        {
          path: '/Reviewer',
          component: resolve => require(['../pages/Reviewer.vue'], resolve)
        },
        {
          path: '/Teminator',
          component: resolve => require(['../pages/Teminator.vue'], resolve)
        }
      ]
    }
   
  ]
})
