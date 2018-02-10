package com.forrest.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpConnection;
import org.apache.ibatis.jdbc.Null;

public class ImageUtils {
	private static int count = 0;

	public static String saveToFile(String imageUrl,String path) {
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

			httpURL = (HttpURLConnection) url.openConnection();
			httpURL.connect();
			bis = new BufferedInputStream(httpURL.getInputStream());
			File dir = new File("g://images" +"/" + path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if (imageUrl.indexOf("%") < imageUrl.lastIndexOf("/") + 1)
				imageName = "g:\\images\\" + path + "\\" + imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
			else
				imageName = "g:\\images\\" + path + "\\" + imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.indexOf("%"));

			File file = new File(imageName);
			fileOutputStream = new FileOutputStream(file);
			while ((size = bis.read(buf)) != -1) {
				fileOutputStream.write(buf, 0, size);
			}
			fileOutputStream.flush();

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			count++;
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
		System.out.println("imageName e......" + imageName);

		return imageName;
	}

}
