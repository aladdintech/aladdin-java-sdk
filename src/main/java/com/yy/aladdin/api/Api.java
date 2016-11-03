package com.yy.aladdin.api;

import java.security.PrivateKey;
import java.util.ArrayList;

import net.sf.json.JSONObject;

import com.yy.aladdin.api.httpconnetion.HttpConnectionUtil;
import com.yy.aladdin.api.util.ConfigUtil;
import com.yy.aladdin.api.util.ErrorUtil;
import com.yy.aladdin.api.util.SignatureAndVerifyUtil;

/**
 * 
 * @author soap API
 */
public class Api {
    private String secretId;
    private String url;
    private PrivateKey privateKey;

    /**
     * @param secretId
     *            用户secretId
     * @param pkPath
     *            用户私钥
     */
    public Api(String secretId, String pkPath) {
	this.secretId = secretId;
	this.url = ConfigUtil.NET_WORK.API_URI + secretId;
	this.privateKey = SignatureAndVerifyUtil.readPrivateKey(pkPath);
    }

    /**
     * 
     * @param fileType
     *            传入的数据类型，ConfigUtil.UPLOAD_TYPE.UPLOAD_IMAGE_TYPE为本地文件
     *            ConfigUtil.UPLOAD_TYPE.UPLOAD_URI_TYPE 为图片 Url
     * @param fileLists
     *            文件集合
     * @param tags
     *            [可选] 用于给图片附加额外信息（比如：直播客户可能传房间号，或者主播ID信息）。方便后续根据tag搜索到相关的图片
     * 
     * @return
     */
    public JSONObject doApiTest(String fileType, ArrayList<String> fileLists, String[] tags, String[] taskIds) {
	if (fileLists == null || fileLists.isEmpty()) {
	    return ErrorUtil.getErrorMsg(ErrorUtil.ERROR_CODE_NO_FILE, "");
	}
	String timestamp = Math.round(System.currentTimeMillis() / 1000.0) + "";
	String nonce = Math.random() + "";
	String sign_string = secretId + "," + timestamp + "," + nonce;

	String signature = SignatureAndVerifyUtil.Signature(privateKey, sign_string);

	String result = null;

	long startTime = System.currentTimeMillis();
	long endTime = 0;
	float time = 0;
	try {
	    // 得到签名
	    if (fileType == ConfigUtil.UPLOAD_TYPE.UPLOAD_IMAGE_TYPE) {
		result = HttpConnectionUtil.uploadImage(url + "/file/", secretId, timestamp, nonce, signature,
			fileLists, tags, taskIds);
	    } else if (fileType == ConfigUtil.UPLOAD_TYPE.UPLOAD_URI_TYPE) {
		result = HttpConnectionUtil.uploadUri(url + "/url/", timestamp, nonce, signature, fileLists, tags,
			taskIds);
	    }

	    if (result != null && !result.equals("err")) {
		JSONObject jsonObject = JSONObject.fromObject(result);
		String result_json = jsonObject.getString("json");
		String result_signature = jsonObject.getString("signature");
		// 进行验证
		boolean verify = SignatureAndVerifyUtil.Verify(result_signature, result_json);
		endTime = System.currentTimeMillis();
		time = (float) (endTime - startTime) / (float) 1000;
		if (verify) {
		    System.out.println("ALADDIN API: response verify succeed, total time" + time + "s");
		    return JSONObject.fromObject(result_json);
		} else {
		    System.out.println("ALADDIN API: response verify failed, total time" + time + "s");
		    return ErrorUtil.getErrorMsg(ErrorUtil.ERROR_CODE_RESULT_VERIFY_FAILED, "");
		}
	    } else {
		System.out.println("ALADDIN API: response verify failed, total time" + time + "s");
		return ErrorUtil.getErrorMsg(ErrorUtil.ERROR_CODE_OTHERS, "");
	    }
	} catch (Exception e) {
	    System.out.println("ALADDIN API: response verify failed, total time" + time + "s");
	    return ErrorUtil.getErrorMsg(ErrorUtil.ERROR_CODE_OTHERS, e.getMessage());
	}
    }
}
