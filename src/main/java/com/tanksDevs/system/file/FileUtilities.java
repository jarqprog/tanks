package com.tanksDevs.system.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtilities implements FileTool {

    @Override
    public String read(String path) {
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            bufferedReader = getBufferedReader(path);
            buildText(stringBuilder, bufferedReader);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            closeReader(bufferedReader);
        }
        return stringBuilder.toString();
    }

    private BufferedReader getBufferedReader(String path) {
        File file = new File(path);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new BufferedReader(fileReader);
    }

    private void buildText(StringBuilder stringBuilder, BufferedReader bufferedReader) {
        String text;
        try {
            while ((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeReader(BufferedReader bufferedReader) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
