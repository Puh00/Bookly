package com.example.bookly.backend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
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


    // returns true when file already exists or created successfully
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

    public boolean writeBitmapTo(String filename, Bitmap bitmap, Bitmap.CompressFormat format, int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteArray = stream.toByteArray();

        return writeTo(filename, byteArray);
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

    public boolean writeTo(String filename, byte[] contents) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(contents);
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
        }

        return stringBuilder.toString();
    }

    public Bitmap readAsBitmap(String filename) {
        try {
            byte[] byteArr = readAsByteArray(filename);
            return BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public byte[] readAsByteArray(String filename) {
        try {
            File file = new File(context.getFilesDir(), filename);

            byte[] bytes = new byte[(int) file.length()];
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(bytes);
            return bytes;
        } catch (IOException e) {
            return null;
        }
    }

}
