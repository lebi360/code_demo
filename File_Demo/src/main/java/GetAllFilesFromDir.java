import java.io.*;
public class GetAllFilesFromDir {
        public static void main(String[] args) {
            String fileName="F:"+File.separator+"sony";
            File f=new File(fileName);
            print(f);
        }
        public static void print(File f){
            if(f!=null){
                if(f.isDirectory()){
                    File[] fileArray=f.listFiles();
                    if(fileArray!=null){
                        //递归调用
                        for (int i = 0; i < fileArray.length; i++) {
                            print(fileArray[i]);
                        }
                    }
                }
                else{
                    System.out.println(f);
                }
            }
        }
    }

