package utlity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import enums.LocalPaths;
import jakarta.servlet.http.Part;

public class Utlity {
	public static String createSaveFileAndDirectory(String filePurpose, String userId, Part inputFilePart,
			Boolean saveFile, Boolean returnDirectory) {
		Path realPath = null;
		try {
			Path relativePath = Paths.get(LocalPaths.UPLOAD_PATH.getValue()); // UPLOAD_PATH
			realPath = relativePath.toRealPath(); // with "\"
			String restPath = ("/uploadedFiles/" + filePurpose + "/" + userId).replace(" ", "");
			File fileDirectoryPath = new File(realPath + restPath);

			if (fileDirectoryPath.exists()) {
//				System.out.println("Folder exists");
			} else if (fileDirectoryPath.mkdirs()) {
//				System.out.println("Folder created");
			}

			if (saveFile == true) {
				return saveFileInDrive(inputFilePart, restPath, fileDirectoryPath.toString());
			} else {
				if (returnDirectory) {
					return fileDirectoryPath.toString();
				} else {
					return null;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String saveFileInDrive(final Part inputFilePart, String newDire, String directoryPath) {
		try {
			String uploadingFile = null;
			if (inputFilePart.getSize() > 0) {
				try {
					OutputStream out = null;
					InputStream filecontent = null;
					int read = 0;

					String originalFileName = getFileName(inputFilePart).replace(" ", "");
					uploadingFile = directoryPath + File.separator + originalFileName;
					out = new FileOutputStream(new File(uploadingFile));
					filecontent = inputFilePart.getInputStream();
					final byte[] readFile = new byte[1024]; // 1MB buffer
					while ((read = filecontent.read(readFile)) != -1) {
						out.write(readFile, 0, read);
					}
					return LocalPaths.CURRENTHOST.getValue() + newDire + "/" + originalFileName;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return uploadingFile;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : partHeader.split(";")) {
			if (content.trim().startsWith("filename")) {
				return getCurrentDateTimeFormatted("yyyy_MM_dd_HH_mm_ss")
						.concat(content.substring(content.indexOf('=') + 1).trim().replace("\"", ""));
			}
		}
		return null;
	}

	public static String getCurrentDateTimeFormatted(String format) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return now.format(formatter);
	}
}