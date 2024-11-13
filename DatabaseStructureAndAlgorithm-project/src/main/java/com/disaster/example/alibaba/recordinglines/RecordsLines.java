package com.disaster.example.alibaba.recordinglines;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 对特定目录下面的JAVA文件进行分析，该目录中会存在子目录和非JAVA文件，要求输出该目录下，JAVA文件的代码行数、注释行数、空行（没有代码注释和代码的行称为空行）。
 * 要求：
 * 1.输入一个文件目录，文件目录下面会有子目录。
 * 2.文件中可能会有空行，统计有效的代码行数，包括有注释的代码。
 */
public class RecordsLines {
    public static void main(StringTest[] args) {
        System.out.print("请输入目录路径：\t");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.next();
        File file = new File(filePath);
        List<File> files = new ArrayList<>();
        getJavaFiles(file, files);
        Map<String, Long> stringLongMap = countRecords(files);
        if (stringLongMap.isEmpty()) System.out.println("此目录下没有java文件!!");
        for (Map.Entry<String, Long> result : stringLongMap.entrySet()) {
            System.out.println(result.getKey() + " = " + result.getValue());
        }
    }

    /**
     * 统计java文件
     *
     * @param file
     * @return
     */
    private static List<File> getJavaFiles(File file, List<File> fileList) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isFile() && file1.toString().endsWith(".java")) {
                    fileList.add(file1);
                }
                if (file1.isDirectory()) {
                    getJavaFiles(file1, fileList);
                }
            }
            return fileList;
        } else {
            System.out.println("file并不是目录！！");
            return null;
        }
    }

    /**
     * 统计代码行数
     *
     * @param files
     * @return
     */
    private static Map<String, Long> countRecords(List<File> files) {
        if (Objects.isNull(files) || files.isEmpty()) return null;
        HashMap<String, Long> resultMap = new HashMap<>();
        AtomicReference<Long> javaNum = new AtomicReference<>(0l);
        AtomicReference<Long> annotationNum = new AtomicReference<>(0l);
        AtomicReference<Long> blackLineNum = new AtomicReference<>(0l);
        files.stream().forEach(file -> {
            try {
                FileReader fr = new FileReader(file.toPath().toString());
                BufferedReader bfr = new BufferedReader(fr);
                bfr.lines().forEach(s -> {
                    int i = verifyString(s);
                    switch (i) {
                        case 1:
                            javaNum.getAndSet(javaNum.get() + 1);
                            break;
                        case 2:
                            annotationNum.getAndSet(annotationNum.get() + 1);
                            break;
                        case 3:
                            blackLineNum.getAndSet(blackLineNum.get() + 1);
                            break;
                    }
                });
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        resultMap.put("java代码", javaNum.get());
        resultMap.put("注释", annotationNum.get());
        resultMap.put("空行", blackLineNum.get());
        return resultMap;
    }

    /**
     * 判断字符串是否是java代码、注释、空行
     *
     * @param str
     * @return 1-java代码 2-注释 3-空行
     */
    private static int verifyString(String str) {
        if(str.startsWith("/*") && str.endsWith("*/")) {
            return 2;
        } else if(str.trim().startsWith("//")) {
            return 2;
        } else if(str.startsWith("/*") && !str.endsWith("*/")) {
            return 2;
        } else if(!str.startsWith("/*") && str.endsWith("*/")) {
            return 2;
        } else if (str.trim().isEmpty()) {
            return 3;
        } else {
            return 1;
        }
    }
}