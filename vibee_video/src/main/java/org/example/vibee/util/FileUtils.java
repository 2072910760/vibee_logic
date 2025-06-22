package com.neuedu.xinxin.util;

import cn.hutool.core.lang.UUID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class FileUtils {
    public final static int copyFolder(File srcFolder, String copyFolderPath,
                                       int fileCount) throws IOException {
        File copyFolder = new File(copyFolderPath);
        if (!copyFolder.isDirectory()) {
            if (!copyFolder.mkdirs()) {
                String message = "can not make folder path:"
                        + srcFolder.getPath();
                throw new IllegalArgumentException(message);
            }
        }
        if (!srcFolder.isDirectory()) {
            String message = "error resource path:" + srcFolder.getPath();
            throw new IllegalArgumentException(message);
        }

        File files[] = srcFolder.listFiles();
        int length = files.length;
        for (int index = 0; index < length; index++) {
            File file = files[index];
            String copyFilePath = copyFolderPath + "\\" + file.getName();
            if (file.isFile()) {
                if (copyFile(file, copyFilePath)) {
                    fileCount++;
                }
            } else if (file.isDirectory()) {
                fileCount += copyFolder(file, copyFilePath, 0);
            } else {
                String message = "error copy file path:" + srcFolder.getPath();
                throw new IllegalArgumentException(message);
            }
        }
        return fileCount;
    }

    public final static File createFolder(String dirPath) {
        File folder = new File(dirPath);
        if (!folder.isDirectory()) {
            if (!folder.mkdirs()) {
                String message = "can not make folder path:" + dirPath;
                throw new IllegalArgumentException(message);
            }
        }
        return folder;
    }

    public final static File createFile(String filePath) throws IOException {

        File file = new File(filePath);
        if (!file.isFile()) {
            String folderPath = filePath.substring(0,
                    filePath.lastIndexOf("\\"));
            createFolder(folderPath);
            if (!file.createNewFile()) {
                String message = "can not make file  path:" + filePath;
                throw new IllegalArgumentException(message);
            }
        }
        return file;
    }

    public final static boolean copyFile(File srcFile, String copyFilePath)
            throws IOException {
        File copyFile = new File(copyFilePath);
        if (copyFile.isFile()) {
            return false;
        } else {
            copyFile = createFile(copyFilePath);
            InputStream in = new FileInputStream(srcFile);
            OutputStream out = new FileOutputStream(copyFile);
            byte bytes[] = new byte[1024];
            while (-1 != in.read(bytes)) {
                out.write(bytes);
            }
            out.close();
            in.close();
        }
        return true;
    }

    public final static void deleteSubFiles(String dirPath) {
        File file = new File(dirPath);
        if (file.isDirectory()) {
            File[] subfiles = file.listFiles();
            int length = subfiles.length;
            for (int index = 0; index < length; index++) {
                if (subfiles[index].isFile()) {
                    subfiles[index].delete();
                } else {
                    deleteSubFiles(subfiles[index].getPath());
                    subfiles[index].delete();
                }
            }
        }
    }

    public final static boolean isValidXMLFile(String xmlFilePath) {
        File xmlFile = new File(xmlFilePath);
        boolean isValid = xmlFile.isFile();
        if (isValid) {
            xmlFilePath = xmlFilePath.toLowerCase();
            isValid = xmlFilePath.endsWith(".xml");
        }
        return isValid;
    }

    /**
     * @param srcImage
     * @param destFile
     * @param destWidth
     * @param destHeight void
     * @describe
     * @method zoomImage
     */
    public final static void zoomImage(BufferedImage srcImage, File destFile,
                                       int destWidth, int destHeight) {
        try {
            int imgWidth = destWidth;
            int imgHeight = destHeight;
            int srcWidth = srcImage.getWidth();
            int srcHeight = srcImage.getHeight();
            if (srcWidth > imgWidth || srcHeight > imgHeight) {
                if (srcHeight >= srcWidth) {
                    imgWidth = (int) Math
                            .round(((destHeight * 1.0 / srcHeight) * srcWidth));
                } else {
                    imgHeight = (int) Math
                            .round(((destWidth * 1.0 / srcWidth) * srcHeight));
                }
            } else {
                imgWidth = srcWidth;
                imgHeight = srcHeight;
            }
            BufferedImage destBufferedImage = new BufferedImage(imgWidth,
                    imgHeight, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphics2D = destBufferedImage.createGraphics();
            graphics2D.setBackground(Color.WHITE);
            graphics2D.clearRect(0, 0, imgWidth, imgHeight);
            graphics2D.drawImage(srcImage.getScaledInstance(imgWidth,
                    imgHeight, Image.SCALE_SMOOTH), 0, 0, null);
            graphics2D.dispose();
            ImageIO.write(destBufferedImage, "JPEG", destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final static boolean checkImgStyle(String imgName) {
        imgName = imgName.toLowerCase();
        Pattern p = Pattern
                .compile("[^\\/\\.<>|\":]+\\.(jpg|gif|png|bmp|jpeg)$");
        return p.matcher(imgName).matches();
    }

    public final static boolean checkVideoStyle(String imgName) {
        imgName = imgName.toLowerCase();
        Pattern p = Pattern
                .compile("[^\\/\\.<>|\":]+\\.(mp4|ogg|m4v|3gpp|mpeg|mov|mkv)$");
        return p.matcher(imgName).matches();
    }

    public final static List<String> getRawImgNames(String rawImgDirPath) {
        List<String> imgNameList = new ArrayList<String>();
        File rawImgDir = new File(rawImgDirPath);
        if (rawImgDir.isDirectory()) {
            File[] imgFiles = rawImgDir.listFiles();
            for (File file : imgFiles) {
                String fileName = file.getName();
                if (checkImgStyle(fileName)) {
                    fileName = fileName.substring(0, fileName.lastIndexOf('.'));
                    imgNameList.add(fileName);
                }
            }
        }
        return imgNameList;
    }

    /**
     * 判断是否为图片
     *
     * @param imgName
     * @return
     */
    public final static boolean isImgFile(String imgName) {
        imgName = imgName.toLowerCase();
        Pattern p = Pattern
                .compile("[^\\/\\.<>|\":]+\\.(jpg|gif|png|bmp|jpeg)$");
        return p.matcher(imgName).matches();
    }

    /**
     * 判断是否为视频文件
     *
     * @param videoFileName
     * @return
     */
    public static boolean isVideoFile(String videoFileName) {
        videoFileName = videoFileName.toLowerCase();
        Pattern p = Pattern
                .compile("[^\\/\\.<>|\":]+\\.(mp4|ogg|m4v|3gpp|mpeg|mov|mkv)$");
        return p.matcher(videoFileName).matches();
    }

    /**
     * 判断文件是否为简历文件
     *
     * @param resumeFileName
     * @return
     */
    public static boolean isResumeFile(String resumeFileName) {
        resumeFileName = resumeFileName.toLowerCase();
        Pattern p = Pattern
                .compile("[^\\/\\.<>|\":]+\\.(doc|docx|pdf)$");
        return p.matcher(resumeFileName).matches();
    }

    /**
     * 获取上传后文件的名字
     *
     * @param prefix           前缀
     * @param originalFilename
     * @return
     */
    public static String getUploadFileName(char prefix, String originalFilename) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(prefix);
        strBuf.append('_');
        strBuf.append(UUID.randomUUID().toString());
        int i = originalFilename.lastIndexOf('.');
        String ext = originalFilename.substring(i);//扩展名
        strBuf.append(ext);
        return strBuf.toString();
    }
}