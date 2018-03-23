package com.forrest.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpConnection;
import org.apache.ibatis.jdbc.Null;

public class ImageUtils {

	public static String getRootPath(ImageType type) 
	{
		if (type == ImageType.RESTO) {
			return "g:\\images\\";
		}
		else {
			return "g:\\members\\";
		}
	}
	public static String saveToFile(String imageUrl,String path, ImageType type) throws MalformedURLException {
		FileOutputStream fileOutputStream = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpURL = null;
		HttpsURLConnection httpsURL = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		String imageName = "";
		try {

			url = new URL(imageUrl);
//			System.out.println("Í¼Æ¬µØÖ·Îª£º" + url);

			if (url.getProtocol().equals("http")) {
				httpURL = (HttpURLConnection) url.openConnection();
				httpURL.connect();
				bis = new BufferedInputStream(httpURL.getInputStream());
			}else if (url.getProtocol().equals("https")) {
				httpsURL = (HttpsURLConnection)url.openConnection();
				httpsURL.connect();
				bis = new BufferedInputStream(httpsURL.getInputStream());
			}
			String rootPath = "";
			if (type == ImageType.RESTO) {
				rootPath = "g://images";
			}
			else {
				rootPath = "g://members";
			}
			File dir = new File(rootPath +"/" + path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if (imageUrl.indexOf("%") < imageUrl.lastIndexOf("/") + 1)
				imageName = getRootPath(type) + path + "\\" + imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
			else
				imageName = getRootPath(type) + path + "\\" + imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.indexOf("%"));

			File file = new File(imageName);
			if (file.exists()) {
				return imageName;
			}
			fileOutputStream = new FileOutputStream(file);
			while ((size = bis.read(buf)) != -1) {
				fileOutputStream.write(buf, 0, size);
			}
			fileOutputStream.flush();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
				if (bis != null) {
					bis.close();
				}
				if (httpsURL != null) {
					httpsURL.disconnect();
				}
				if (httpURL != null) {
					httpURL.disconnect();

				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
//		System.out.println("imageName database:" + imageName);

		return imageName;
	}

}
