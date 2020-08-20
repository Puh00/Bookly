package com.example.bookly.backend;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class IOHandler {

    private Context context;

    public IOHandler(Context context) {
        this.context = context;
    }

    public boolean fileExists(String name) {
        File file = context.getFileStreamPath(name);
        return file != null && file.exists();
    }

    public boolean createNewFile(String name) {
        boolean r = fileExists(name);
        if (!r) {
            try {
                // this is clearly very safe coding
                r = new File(context.getFilesDir(), name).createNewFile();
            } catch (IOException ignored) {}
        }
        return r;
    }

    public boolean writeTo(String filename, String contents) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(contents.getBytes());
            fos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String readFrom(String filename) throws FileNotFoundException {
        FileInputStream fis = context.openFileInput(filename);
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            String contents = stringBuilder.toString();
        }

        return stringBuilder.toString();
    }


}
