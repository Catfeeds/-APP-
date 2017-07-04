package com.hcb.xigou.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CopyFile {

	public static File copyFile(File dest,String url){
		//如果目标文件不存在，创建文件
		if( !dest.exists() ){
			try {
				dest.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		InputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = SimpleGetObjectSample.getUrlStream(url);
			fos = new FileOutputStream(dest,true);
			byte[] bs = new byte[1024 * 8];
			int hasRead = 0;
			long start = System.currentTimeMillis();
			//进行拷贝
			while( (hasRead = fis.read(bs) ) !=-1){
				fos.write(bs,0,hasRead);
				fos.flush();
			}
			long end = System.currentTimeMillis();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return  dest;
	}
}
