import java.io.*;

/**
 * Created by caihx on 2017/2/17.
 */
public class SourceCodeManager {


    private int annotationCount;

    private int sourceCodeCount;

    private int blankCount;

    public int getBlankCount() {
        return blankCount;
    }

    public int getAnnotationCount() {
        return annotationCount;
    }

    public int processFiles() {
        return sourceCodeCount;
    }


    public static void main(String[] args) {
        String fileName = "C:\\Users\\caihx\\IdeaProjects\\code_demo\\interviewTest\\";
        SourceCodeManager sourceCodeManager = new SourceCodeManager();
        sourceCodeManager.processFiles(new File(fileName));
        System.out.println("sourceCodeCount:" + sourceCodeManager.processFiles());
        System.out.println("blankCount:" + sourceCodeManager.getBlankCount());
        System.out.println("annotationCount:" + sourceCodeManager.getAnnotationCount());
    }

//    private Boolean isBinaryFile(String extName) {
//        return extName.equalsIgnoreCase(".class")
//                || extName.equalsIgnoreCase(".jar")
//                || extName.equalsIgnoreCase(".war");
//    }

    private Boolean isSourceFile(String fileName) {
        return getExt(fileName).equalsIgnoreCase("java")
                || getExt(fileName).equalsIgnoreCase("css")
                ||getExt(fileName).equalsIgnoreCase("js");
    }

    private String getExt(String fileName) {
        return fileName == null ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private int getLineSourceCount(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ';') {
                count++;
            }
        }
        return count;
    }

    //

    /*
    jjkjkjkjk
    kkllllll
     */
    private void readSourceFile(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                if (tempString == null || tempString.trim().equals("")) {
                    blankCount++;
                } else if (tempString.trim().startsWith("//")
                        || tempString.trim().startsWith("/*") || tempString.trim().startsWith("*") || tempString.trim().endsWith("*/")) {
                    System.out.println(tempString);
                    annotationCount++;
                } else {
                    System.out.println("line " + sourceCodeCount + ": " + tempString);
                    sourceCodeCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }

    private void processFiles(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        processFiles(files[i]);
                    }
                }
            } else if (isSourceFile(file.getName())) {
                readSourceFile(file);
            }
        }
    }
}
