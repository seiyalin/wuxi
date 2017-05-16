package org.wuxi.fudan.syfw.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 * 封装图像上传操作
 * @author Administrator
 *
 */
public class FileUtils2 {

	/**
	 * 配合struts2 的文件上传操作
	 * @param file
	 * @throws IOException 
	 */
	public static String  saveFile(File file,String fileName) throws IOException{
//		String filePath = ServletActionContext.getServletContext().getRealPath("jsp/syfw/feed/upload/");
		String filePath = "D:/upload";
		String nfileName = UUID.randomUUID().toString().replaceAll("-", "") + fileName.substring(fileName.lastIndexOf("."));
		//复制文件
		FileUtils.copyFile(file, new File(filePath, nfileName));
		//返回文件路径+文件名
//		return filePath + nfileName;
		//返回路径为upload/ +  nfileName
		return "upload/"+nfileName;
//		System.out.println("nfileName--> "+nfileName);
//		return nfileName;
		
	}
}
