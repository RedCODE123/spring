package com.bj.crm.utils;

import java.util.UUID;

public class UUIDUtil {
	
	public static String getUUID(){
		//生成32位的随机字符串
		return UUID.randomUUID().toString().replaceAll("-","");
		
	}
	
}
