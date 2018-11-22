package cn.lisp.blog.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Base64Img {
    /**
     * @Title: GetImageStrFromUrl
     * @Description: TODO(将一张网络图片转化成Base64字符串)
     * @param imgURL 网络资源位置
     * @return Base64字符串
     */
    public static String GetImageStrFromUrl(String imgURL) {
        byte[] data = null;
        try {
            data = HttpUtil.getByte(imgURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回Base64编码过的字节数组字符串
        return Base64Util.encode(data);
    }

    /**
     * @Title: GetImageStrFromPath
     * @Description: TODO(将一张本地图片转化成Base64字符串)
     * @param imgPath
     * @return
     */
    public static String GetImageStrFromPath(String imgPath) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        // 返回Base64编码过的字节数组字符串
        return Base64Util.encode(data);
    }

}
