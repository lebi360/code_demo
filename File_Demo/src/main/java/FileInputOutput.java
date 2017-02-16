import java.io.*;

/**
 * Created by caihx on 2017/2/16.
 */
public class FileInputOutput {
    public static void main (String[] arg) {
            readFileByLines("File_Demo/src/main/java/resources/aa.txt");
    }
    public static void readFileByLines(String fileName) {
        File file = new File(FileInputOutput.class.getClassLoader().getResource("aa.txt").getFile());
        BufferedReader reader = null;
        try {

            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            FileWriter fileWriter = new FileWriter(new File("bb.txt"), true);
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
                fileWriter.append(tempString+"\n");
            }
            reader.close();
            fileWriter.close(); // 关闭数据流
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }

        }
    }

}
