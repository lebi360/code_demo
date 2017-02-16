import java.io.*;
import java.util.HashMap;

/**
 * Created by caihx on 2017/2/16.
 */
public class BGFileProcess {
    public static File[] splitFiles = new File[1000];
    public static void createBigDate() {
        for(int  i =1; i<1000;i++) {
            FileInputOutput.readFileByLines("File_Demo/src/main/java/resources/aa.txt");
        }
    }

    public static void main (String[] arg) {
        splitDataToSplitedFile();
    }

    public static void splitDataToSplitedFile() {
        File file = new File("bb.txt");
        BufferedReader reader = null;
        try {

            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束

            while ((tempString = reader.readLine()) != null) {
                System.out.println(tempString.hashCode());
                int index = Math.abs(tempString.hashCode()) % 1000;
                System.out.println(index);
                if (splitFiles[index] == null)
                    splitFiles[index] = new File("bb"+index+".txt");
                FileWriter fileWriter = new FileWriter(splitFiles[index], true);
                fileWriter.append(tempString+"\n");
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
                fileWriter.close(); // 关闭数据流
            }
            reader.close();
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

