package com.hhb.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 工具类，提供一些静态的功能函数
 */
@SuppressWarnings("unchecked")
public class Utils {
	
	public static int getPageNum(String pageStr) {
		if ("".equals(pageStr) || pageStr == null) {
			pageStr = "1";
		}
		// 当前页码
		return Integer.parseInt(pageStr);
	}

	/**
	 * 把iso8859-1编码的字符串改为utf-8形式，解决中文乱码
	 * 
	 * @param s
	 *            iso8859-1编码的字符串
	 * @return utf-8编码的字符串
	 */
	public static String isoToUtf(String s) {
		try {
			return new String(s.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断入参temp是否满足邮箱格式 xxx@xxx.xxx
	 * @param temp
	 * @return
	 */
	public static boolean isEmail(String temp) {
		String rule = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(rule);
		Matcher matcher = regex.matcher(temp);
		return matcher.matches();
	}
	
	/**
	 * 将字符串进行MD5加密， 主要用于密码部分
	 */
	public static String toMD5(String data) {
		if (data == null)
			return null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 加密转换
			md.update(data.getBytes());
			String result = new BigInteger(1, md.digest()).toString(16);

			return result;

		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}


	/**
	 * 解析request请求， 获取上传的图片文件
	 */
	public static byte[] analysisForm(HttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> items = null;
		byte[] image = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} // 解析request请求
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (item.isFormField()) { // 如果是表单域 ，就是非文件上传元素
				continue;
			} else {
				String fieldName = item.getFieldName(); // 文件域中name属性的值
				if ("fileList".equals(fieldName)) {
					try {
						BufferedImage bi = ImageIO.read(item.getInputStream());
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(bi, "png", baos);
						image = baos.toByteArray();
						baos.close();
						System.out.println("单次上传发现文件：" + image);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return image;
	}

	/**
	 * 通过BufferedImage获取图片的Blob数据
	 * @param image 图片
	 * @return Blob 数据
	 */
	public static final byte[] getDataFromImage(BufferedImage image) throws IOException {
		/** 实训场景006：用户头像上传 - 把BufferedImage对象转换为byte数组【START】 */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpeg", baos);
		baos.flush();
		baos.close();
		return baos.toByteArray();
		/** 实训场景006：用户头像上传 - 把BufferedImage对象转换为byte数组【END】 */
	}

	/**
	 * 将字符串转换成java.sql.Date类型
	 * 
	 * @param ymd
	 *            获取到的表单字符串
	 * @return 返回sql的Date
	 */
	public static final Date stringToDate(String ymd) {
		Date sqlDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			java.util.Date date = sdf.parse(ymd);
			sqlDate = new Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}

	/**
	 * 根据byte数组，生成文件
	 */
	public static void getFile(byte[] bfile, String filePath) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 随机生成图片文件名或者编号
	 * 
	 * @return
	 */
	public static String createName() {
		DateFormat format = new SimpleDateFormat("hhmmss");
		return format.format(new java.util.Date()) + (int) (Math.random() * 89 + 10);
	}

	public static String dateToString(java.util.Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
}
