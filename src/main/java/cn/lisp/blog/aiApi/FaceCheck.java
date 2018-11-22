package cn.lisp.blog.aiApi;
import cn.lisp.blog.util.Base64Img;
import cn.lisp.blog.util.HttpUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: FaceCheck
 * @Description: 人脸对比
 * @author: lisp
 * @date: 2018/11/22 0022 下午 3:18
 * @version: v1.0
 */
public class FaceCheck {


    public static void main(String[] agrs){
        //人脸对比
        faceCheck();
        System.out.println("===================================================================");
    }

    /**
     * 下载工具包
     * http://open.iot.10086.cn/ai/code/demo/java.zip
     */
    private static void faceCheck() {
        String path = "http://ai.heclouds.com:9090/v1/aiApi/picture/FACE_COMPARE";
        String loginToken = "KEFxfUBxKUV1Kli4KlK2*U-0*Em4KEJAKEFxfUBxKUV1Kli4KlK2*U-0*Em4KEJAU2LLS1W*Um_KQUJAKUV1Kli4K0J$OE1$OlRxKE1yOURz*UV6Oli1QUJxOEK2*EK4OUKAKUF0K0V=";

        //图片信息
        Map<String, Object> params = new HashMap<>();
        //方式一
        String imgURL1 = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1252736001,1291115764&fm=27&gp=0.jpg";
        String imgURL2 = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3563872185,2131706691&fm=27&gp=0.jpg";
        String pic1 = Base64Img.GetImageStrFromUrl(imgURL1);
        String pic2 = Base64Img.GetImageStrFromUrl(imgURL2);
        //方式二
        //String pic1 = Base64Img.GetImageStrFromPath("【本地图片文件】");
        //String pic2 = Base64Img.GetImageStrFromPath("【本地图片文件】");

        String[] value = {pic1,pic2};
        params.put("picture", value);
        try {
            //调用
            String result = HttpUtil.post(path, loginToken, params);
            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


