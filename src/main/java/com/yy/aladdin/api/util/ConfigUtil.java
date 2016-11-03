package com.yy.aladdin.api.util;


/**
 * Created by aladdin on 16/11/3.
 * 配置信息
 */
public class ConfigUtil {

    /**
     *测试数据类型
     */
    public final static class UPLOAD_TYPE {
        /**
         * upload file
         **/
        public final static String UPLOAD_IMAGE_TYPE = "image";
        /**
         * upload uri
         */
        public final static String UPLOAD_URI_TYPE = "url";
    }

    /**
     *api  调用地址
     */
    public final static class NET_WORK {

        public final static String API_URI = "http://api.aladdin.yy.com/v2/recognition/";

    }
    /**
     * 文件限制
     */

    public final static class FILE_LIMIT {
        /**
         * 图片最大 800KB 左右
         */
        public final static int MAX_IMAGE_LENGTH = 800 * 1024;

        /**
         * 每个请求最大图片数量
         */
        public final static int MAX_IMAGE_LIST_SIZE = 200;

    }
    
    /**
     * 公用Key文件名
     */
    public final static String PUBLIC_ALADDIN_KEY_PATH="aladdin-open-api.pub";

}
