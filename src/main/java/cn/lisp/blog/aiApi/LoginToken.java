package cn.lisp.blog.aiApi;

import cn.lisp.blog.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: LoginToken
 * @Description: 用户鉴权
 * @author: lisp
 * @date: 2018/11/22 0022 下午 3:37
 * @version: v1.0
 */
public class LoginToken {


        public static void main(String[] agrs){
            //获取loginToken：
            loginToken();
            System.out.println("===================================================================");

        }

        /**
         * 下载工具包
         * http://open.iot.10086.cn/ai/code/demo/java.zip
         */
        private static void loginToken() {
            //服务地址
            String path = "http://ai.heclouds.com:9090/v1/user/simpleLogin";
            //注册邮箱/登录密码
            String email = "lisp@redoornetwork.com";
            String pwd = "lishuiping92";
            try {
                //调用
                Map<String, Object> params = new HashMap<>();
                params.put("email",email);
                String value = HttpUtil.byteToHexString(pwd.getBytes());
                params.put("password", value);
                String result = HttpUtil.post(path, null,params);
                System.out.println("result:" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


