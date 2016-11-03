package com.yy.aladdin.example;

import java.util.ArrayList;

import net.sf.json.JSONObject;

import com.yy.aladdin.api.Api;
import com.yy.aladdin.api.util.ConfigUtil;

public class APITest {

    public static void main(String[] args) {
	// secret id
	String secretId = "74ffeffb337b10e842c588ff84e13705";
	// private KEY path
	String privateKey = "E:\\work\\aladdin\\aladdin-java-sdk\\src\\main\\resources\\rsatest";
	// fileList imageFile or url
	ArrayList<String> fileList = new ArrayList<String>();
	// tags
	String tags[] = { "tag1", "tag2" };

	fileList.add("E:\\work\\aladdin\\aladdin-java-sdk\\src\\main\\resources\\test1.png");
	fileList.add("E:\\work\\aladdin\\aladdin-java-sdk\\src\\main\\resources\\test2.png");

	String taskIds[] = { "54ecfc6c329af61034f7c2fc", "54ecfc6c329af61034f7c2fc" };

	Api api = new Api(secretId, privateKey);

	JSONObject result = api.doApiTest(ConfigUtil.UPLOAD_TYPE.UPLOAD_IMAGE_TYPE, fileList, tags, taskIds);

	System.out.println(result);

	fileList.clear();
	fileList.add("http://yysnapshot.bs2monitor2.yy.com/4e6f4af407e12fb94d09b4c61bc578acbe555743");
	fileList.add("http://yysnapshot.bs2monitor2.yy.com/4e6f4af407e12fb94d09b4c61bc578acbe555743");

	result = api.doApiTest(ConfigUtil.UPLOAD_TYPE.UPLOAD_URI_TYPE, fileList, tags, taskIds);

	System.out.println(result);
    }
}
