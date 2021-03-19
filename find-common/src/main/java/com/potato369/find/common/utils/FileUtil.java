package com.potato369.find.common.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil {
	// 使用lastIndexOf()结合subString()获取后缀名
	public static String lastName1(File file) {
		if (file == null) {
			return null;
		}
		String filename = file.getName();
		if (filename.lastIndexOf(".") == -1) {
			return "";// 文件没有后缀名的情况
		}
		// 此时返回的是带有 . 的后缀名，
		return filename.substring(filename.lastIndexOf("."));

		// return filename.subString(filename.lastIndexOf(".")+1);// 这种返回的是没有.的后缀名

		// 下面这种如果对于String类型可能有问题，如 以.结尾的字符串，会报错。但是文件没有以点结尾的
	}

	// split截取后缀名
	public static String lastName2(File file) {
		if (file == null) {
			return null;
		}
		String filename = file.getName();
		// split用的是正则，所以需要用 //. 来做分隔符
		String[] split = filename.split("\\.");
		// 注意判断截取后的数组长度，数组最后一个元素是后缀名
		if (split.length > 1) {
			return split[split.length - 1];
		} else {
			return "";
		}
	}

	/**
	 * java-复制文件及文件夹到另一个文件夹下
	 * 
	 * @param oldPath 老文件或者文件夹
	 * @param newPath 新文件夹
	 * @throws IOException
	 */
	public static void copyDir(String oldPath, String newPath) throws IOException {

		File file = new File(oldPath);

//		System.out.println("oldPath=" + oldPath);
//		System.out.println("newPath=" + newPath);
		String[] filePath = file.list();

		if (!(new File(newPath)).exists()) {
			(new File(newPath)).mkdir();
		}

		for (int i = 0; i < filePath.length; i++) {

			if ((new File(oldPath + File.separator + filePath[i])).isDirectory()) {

				copyDir(oldPath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
			}

			if (new File(oldPath + File.separator + filePath[i]).isFile()) {

				File source = new File(oldPath + File.separator + filePath[i]);

				File dest = new File(newPath + File.separator + filePath[i]);

				if (!(dest.exists())) {

					Files.copy(source.toPath(), dest.toPath());
				}
			}
		}
	}

	public String getSeparator() {
		String _os_name = System.getProperty("os.name");
		System.out.print("os name=" + _os_name);
		if (_os_name.contains("Linux")) {
			return System.getProperty("file.separator");
		}
		if (_os_name.equals("Windows")) {
			return "/";
		}
		if (_os_name.equals("UNIX")) {
			return System.getProperty("file.separator");
		}
		return "/";
	}

	public static void main(String[] args) throws IOException {
		copyDir("C:\\Users\\Administrator\\Desktop\\a", "D:\\资料书籍");
	}
}
