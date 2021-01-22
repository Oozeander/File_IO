package com.oozeander;

import com.oozeander.util.FileUtility;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Launcher {
	public static void main(String[] args) {
		// File/Folder exists ?
		System.out.println(FileUtility.exists("./src/main/java/com/oozeander/Launcher.java") + "\n"
				+ FileUtility.exists("C://Users/bille/Documents/Java/File_IO/src/main/java/com/oozeander/util") + "\n"
				+ FileUtility.exists("./logging.log"));
		// Create Directories
		FileUtility.createDirectories("./", "src/main/test/java");
		FileUtility.createDirectories("./logs", "tests");
		// Create File
		FileUtility.createFile(".", "readme.md");
		// Delete a file/tree
		FileUtility.delete("./files/test", "text.txt.txt");
		FileUtility.delete("./files", StringUtils.EMPTY);
		// Copy file
		FileUtility.copyOrMove("./src/main/java/com/oozeander/Launcher.java", "./Launcher-copy.java", true);
		// Move file
		FileUtility.copyOrMove("./src/main/java/com/oozeander/readm.md", "./readme.md", false);
		// Write and append to File
		FileUtility.createFile(".", "readme.md");
		FileUtility.writeToFile("readme.md", List.of("First line", "Second line"), false);
		FileUtility.writeToFile("readme.md", List.of("Third line", "Etc..."), true);
		// Read from File
		System.out.println(FileUtility.readFromFile("readme.md"));
		// Get File information
		System.out.println(FileUtility.getFileInformation("readme.md"));
	}
}