import Axios from "axios";
import {get,post} from "./http";

//判断管理员是否登录成功
export const getLoginStatus = (params) => post(`admin/login/status`,params);
export const getUserOfLevel = (privilegeLevel) => get(`/admin/level?privilegeLevel=${privilegeLevel}`);
export const getAllAdmin = () => get(`/admin/all`);
//添加管理员
export const addAdmin= (params) => post(`admin/add`,params);
export const updateAdmin= (params) => post(`admin/update`,params);
//删除管理员
export const deleteAdmin = (id) => get(`admin/delete?id=${id}`);
export const adminOfName = (name) => get(`/admin/verifyExist?name=${name}`);

//====================注册相关=======================
export const ifExist = (userName) => get(`/user/ifExist?userName=${userName}`);
export const addUser= (params) => post(`/user/signUp`,params);
//====================上传者相关=======================

//查询歌手
export const getAllUploader = () => get(`uploader/allUploader`);

//添加歌手
export const setUploader= (params) => post(`uploader/add`,params);

//修改歌手信息
export const updateUploader = (params) => post(`uploader/update`,params);

//删除歌手
export const deleteUploader = (id) => get(`uploader/delete?id=${id}`);

export const uploaderOfStudyNumber = (studyNumber) => get(`uploader/uploaderOfStudyNumber?studyNumber=${studyNumber}`);



//=====================文件相关=======================
export const getAllPublicFile = () =>get(`publicFile/allPublicFile`);

export const publicFileOfUploaderId=(id) => get(`publicFile/uploader/detail?uploaderId=${id}`);

export const updatePublicFile= (params) =>post(`publicFile/update`,params);

export const delPublicFile= (id) =>get(`publicFile/delete?id=${id}`);

//根据歌曲id查询歌曲对象
export const publicFileOfPublicFileId= (id) =>get(`publicFile/detail?publicFileId=${id}`);

export const publicFileOfStatus= (uploaderId,status) =>get(`publicFile/publicFileOfStatus?uploaderId=${uploaderId}&&status=${status}`);

//更新歌曲状态
export const updatePublicFileStatus= (params) =>post(`publicFile/updateStatus`,params);

//保存一级反馈信息
export const updateFirstReason= (params) =>post(`publicFile/updateFirstReason`,params);


export const updateSecondReason= (params) =>post(`publicFile/updateSecondReason`,params);
//=====================用户相关==================================
//查询所有用户
export const getAllConsumer = () => get(`consumer/allConsumer`);
//添加歌手
export const setConsumer= (params) => post(`consumer/add`,params);

//修改歌手信息
export const updateConsumer = (params) => post(`consumer/update`,params);

//删除歌手
export const deleteConsumer = (id) => get(`consumer/delete?id=${id}`);

//=====================用户相关==================================
// 下载音乐
export const download = (url) => Axios({
    method: 'get',
    url:url,
    responseType: 'blob'
});
