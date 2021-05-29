import Axios from "axios";
import {get,post} from "./http";

//====================登录注册相关=======================
export const ifExist = (userName) => get(`/user/ifExist?userName=${userName}`);
export const addUser= (params) => post(`/user/signUp`,params);
export const sendEmail = (params) => get(`/sendSimpleMail`,params);
export const Login = (params) => post(`/user/login`,params);

//=====================用户相关==================================
// 下载音乐
export const download = (url) => Axios({
    method: 'get',
    url:url,
    responseType: 'blob'
});
