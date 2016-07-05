package com.meiben.wechat.common.utils;


import com.meiben.wechat.common.bean.AccessToken;

import java.io.*;

/**
 * Created by joker on 2016/5/19.
 */
public class FileUtil {

    /**
     * @param str  写入文件的内容
     * @param path 文件路径
     */
    public static void writeFile(String str, String path){
        try {
            System.out.println("token path---:"+path);
            File file = new File(path);
            if (!file.exists()){
                file.createNewFile();
                FileOutputStream out=new FileOutputStream(file,false); //如果追加方式用true
                StringBuffer sbf = new StringBuffer();
                sbf.append(str);
                out.write(sbf.toString().getBytes("utf-8"));//注意需要转换对应的字符集
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param file
     * @return  读取本地AccessToken
     */
    public static AccessToken getTokenFromDir(File file){
        AccessToken accessToken = new AccessToken();
        try {
            FileReader in = new FileReader(file);
            LineNumberReader reader = new LineNumberReader(in);
            String s = reader.readLine();
            int lineNum = 0;
            while (s != null) {
                lineNum++;
                if (lineNum == 1){
                    accessToken.setToken(s);
                }
                if (lineNum == 2){
                    accessToken.setExpiresIn(Integer.valueOf(s));
                }
                if (lineNum == 3){
                    accessToken.setFlag(Integer.valueOf(s));
                }
                s = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    /**
     * @param path
     * @param lineNumber
     * @throws IOException
     */
    public static String readPointLine(String path, int lineNumber){
        String s = "";
        try {
            File file = new File(path);
            FileReader in = new FileReader(file);
            LineNumberReader reader = new LineNumberReader(in);
            if (lineNumber < 0 || lineNumber > getTotalLines(file)) {
                System.out.println("不在文件的行数范围之内。");
            }else {
                int lines = 0;
                while (s != null && !"".equals(s)) {
                    lines++;
                    s = reader.readLine();
                    if( lines ==lineNumber ) {
                        break;
                    }
                }
            }
            reader.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * @param file
     * @return 文件行数
     * @throws IOException
     */
    public static int getTotalLines(File file) {
        int lines = 0;
        try {
            FileReader in = new FileReader(file);
            LineNumberReader reader = new LineNumberReader(in);
            String s = reader.readLine();
            while (s != null) {
                lines++;
                s = reader.readLine();
            }
            reader.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
