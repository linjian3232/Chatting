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
          <el-button type="mini" @click="SendMail()">获取验证码</el-button>
        </el-form-item>
        <el-form-item prop="verifyCode" label="验证码">
          <el-input v-model="userForm.verifyCode" placeholder="验证码"></el-input>
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
import {ifExist,addUser,sendEmail} from "../api/index";
import 'url-search-params-polyfill';
export default {
  mixins:[mixin],
  data: function(){
    return {
      userForm:{
        username: "",
        password: "",
        email: "",
        verifyCode: ""
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
        ],
        verifyCode:[
          {required:true,message:"请输入验证码",trigger:"blur"}
        ]
      }
    };
  },
  methods:{
      beforeAddUser(userName){  
        console.log("邮箱"+this.userForm.email);  
        if(this.userForm.username==""||this.userForm.password==""||this.userForm.email==""||this.userForm.verifyCode=="") {
          this.notify("请填写完整信息","error");
        }    
        else{
            ifExist(userName).then(res =>{
                if(res.code==1){
                    this.notify("用户已存在","error");
                }
                else{
                    
                    this.signUp();
                }
            })
        }
        },
      SendMail(){
         if(this.userForm.username==""||this.userForm.password==""||this.userForm.email=="") {
          this.notify("请填写完整信息","error");
        }    
        else{
          let params=new URLSearchParams();
          params.append('username',this.userForm.username);
          params.append('email',this.userForm.email);
          sendEmail(params).then(res =>{
                if(res.code==0){
                    this.notify("邮件发送失败","error");
                }
                else{
                    this.notify("邮件已发送","success");
                }
            })
        }

      },
      signUp(){
    
            let params=new URLSearchParams();
            params.append('username',this.userForm.username);
            params.append('password',this.userForm.password);
            params.append('email',this.userForm.email);
            params.append('verifyCode',this.userForm.verifyCode);

            addUser(params)
            .then(res =>{
                if(res.code==-1){
                  this.notify(res.msg,"error");
                }
                else if(res.code == 1)              {
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
  top: 40%;
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
  top: 40%;
  width: 350px;
  height: 470px;
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
  width: 70%;
  height: 36px;
}
</style>
