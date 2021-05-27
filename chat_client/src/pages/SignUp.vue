<template>
  <div class="login-wrap">
    <div class="ms-title">欢迎注册</div>
    <div class="ms-login">
      <el-form :model="userForm" :rules="regulation" ref="userForm">
        <el-form-item prop="username" label="用户名">
          <el-input v-model="userForm.username" placeholder="用户名" ></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码">
          <el-input type="password" v-model="userForm.password" placeholder="密码"></el-input>
        </el-form-item>
         <el-form-item prop="email" label="邮箱">
          <el-input type="email" v-model="userForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="beforeAddUser(userForm.username)">注册</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import {mixin} from "../mixins/index";
import {ifExist,addUser} from "../api/index";
import 'url-search-params-polyfill';
export default {
  mixins:[mixin],
  data: function(){
    return {
      userForm:{
        username: "",
        password: "",
        email: ""
      },
      regulation:{
        username:[
          {required:true,message:"请输入用户名",trigger:"blur"}
        ],
        password:[
          {required:true,message:"请输入密码",trigger:"blur"}
        ],
        email:[
          {required:true,message:"请输入邮箱",trigger:"blur"}
        ]
      }
    };
  },
  methods:{
      beforeAddUser(userName){         
            ifExist(userName).then(res =>{
                if(res.code==1){
                    this.notify("用户已存在","error");
                }
                else{
                    this.signUp();
                }
            })
        },
      signUp(){
    
            let params=new URLSearchParams();
            params.append('username',this.userForm.username);
            params.append('password',this.userForm.password);
            params.append('email',this.userForm.email);
      
            addUser(params)
            .then(res =>{
                if(res.code == 1)              {
                    this.notify("注册成功","success");
                    this.$router.push("/");
                }else{
                    this.notify("注册失败","error");
                }
            })
            .catch(err => {
                console.log(err);
            });
        },
      
  }
}

</script>
<style scoped>
.login-wrap {
  position: relative;
  background: url("../assets/img/background1.jpeg"  );
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  width: 100%;
  height: 100%;
}
.ms-title {
  position: absolute;
  top: 50%;
  width: 100%;
  margin-top: -230px;
  text-align: center;
  font-size: 30px;
  font-weight: 600;
  color: #fff;
}

.ms-login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 300px;
  height: 350px;
  /* 左移150 */
  margin-left: -190px;
  margin-top: -150px;
  padding: 40px;
  border-radius: 5px;
  background: #fff;
}
.login-btn {
  /* 居中 */
  text-align: center;
}
.login-btn button {
  width: 100%;
  height: 36px;
}
</style>
