package com.treegrowth.service.iml.qiniudemo.download;

import com.qiniu.util.Auth;

public class GenerateDownLoadFilePath {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "Access_Key";
    String SECRET_KEY = "Secret_Key";
    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //构造私有空间需要生成的下载的链接
    String URL = "http://bucketdomain/key";

    public void download() {
        //调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
        String downloadRUL = auth.privateDownloadUrl(URL, 3600);
        System.out.println(downloadRUL);
    }

    public static void main(String args[]) {
        new GenerateDownLoadFilePath().download();
    }

}
