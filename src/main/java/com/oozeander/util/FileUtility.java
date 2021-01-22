package com.oozeander.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import com.oozeander.exception.PathNotFoundException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class FileUtility {
	/**
	 * Check if file/folder exists
	 *
	 * @param path to file/folder
	 * @return true/false
	 */
	public static Boolean exists(String path) {
		return Files.exists(Paths.get(path));
	}

	/**
	 * Create directory tree
	 *
	 * @param path          to file/folder where to create tree
	 * @param directoryTree tree of directories to create
	 * @return true/false
	 * @throws PathNotFoundException
	 */
	public static void createDirectories(String path, String directoryTree) {
		if (exists(path)) {
			try {
				Files.createDirectories(Paths.get(path).resolve(directoryTree));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			throw new PathNotFoundException(path);
	}

	/**
	 * Create a file
	 *
	 * @param path     directory path
	 * @param filename
	 * @throws IOException
	 * @throws PathNotFoundException
	 */
	public static void createFile(String path, String filename) {
		if (exists(path)) {
			try {
				Files.createFile(Paths.get(path).resolve(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			throw new PathNotFoundException(path);
	}

	/**
	 * Delete a file/folder
	 * if file : require filename, else filename = StringUtils.EMPTY ==> delete folder recursively
	 *
	 * @param path to file
	 * @param filename
	 * @return true/false
	 */
	public static Boolean delete(String path, String filename) {
		return FileUtils.deleteQuietly(filename == StringUtils.EMPTY ? new File(path) : new File(path + "/" + filename));
	}

	/**
	 * Copy/Move file to another location
	 *
	 * @param src file to copy/move
	 * @param dst location for the copy/move
	 * @param copy true == copy && false == move
	 * @throws PathNotFoundException
	 */
	public static void copyOrMove(String src, String dst, boolean copy) {
		if (exists(src)) {
			try {
				if (copy) {
					FileUtils.copyFile(new File(src), new File(dst));
				} else {
					FileUtils.moveFile(new File(src), new File(dst));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			throw new PathNotFoundException(src);
	}

	/**
	 * Write or Append to file
	 *
	 * @param filePath path to file
	 * @param content List.of(lines)
	 * @param append true == append, false == write (override)
	 * @throws PathNotFoundException
	 */
	public static void writeToFile(String filePath, List<String> content, boolean append) {
		if (exists(filePath)) {
			try {
				FileUtils.writeLines(new File(filePath), "utf-8", content, append == true);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		} else
			throw new PathNotFoundException(filePath);
	}

	/**
	 * Read from a file
	 *
	 * @param filePath path to file
	 * @return List.of(lines)
	 */
	public static List<String> readFromFile(String filePath) {
		List<String> lines = new ArrayList<>();
		if (exists(filePath)) {
			try {
				lines = FileUtils.readLines(new File(filePath), Charset.forName("UTF-8"));
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		} return lines;
	}

	/**
	 * Get File Information
	 *
	 * @param filePath path to file
	 * @return List.of(name, path, ext, size, last modified)
	 */
	public static List<String> getFileInformation(String filePath) {
		List<String> information = new ArrayList<>();
		if (exists(filePath)) {
			try {
				information = List.of(FilenameUtils.getBaseName(filePath), new File(filePath).getAbsolutePath(), FilenameUtils.getExtension(filePath),
						String.valueOf(BigDecimal.valueOf(Files.size(Paths.get(filePath))).divide(BigDecimal.valueOf(1024), new MathContext(3, RoundingMode.HALF_UP))) + " Ko",
						Files.getLastModifiedTime(Paths.get(filePath)).toString());
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		} return information;
	}
}