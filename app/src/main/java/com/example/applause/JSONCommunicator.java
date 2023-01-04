package com.example.applause;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JSONCommunicator {

    Context context;
    JSONObject jsonObject;

    public JSONCommunicator(Context context) {
        this.context = context;
        try {
            jsonObject = new JSONObject(readJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean loginConfirmed(String login, String password) {
        boolean confirmed = false;
        try {
            JSONArray usersArray = jsonObject.getJSONArray("users");
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject user = usersArray.getJSONObject(i);
                if (user.getString("login").equals(login)) {
                    if (user.getString("password").equals(password)) {
                        confirmed = true;
                        break;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return confirmed;
    }

    public void writeJson(String login, String password) {
        try {
            JSONArray usersArray = jsonObject.getJSONArray("users");
            JSONObject object = new JSONObject();
            object.put("login", login);
            object.put("password", password);
            JSONArray shotsArr = new JSONArray();
            object.put("shots", shotsArr);
            usersArray.put(object);
            writeToFile(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String readJson() {
        String json = null;
        File file = new File(context.getFilesDir(), "database.json");
        if(!file.exists()) {
            createBaseJSON();
        }
        try {
            InputStream inputStream = new FileInputStream(file);
            int jsonSize = inputStream.available();
            byte[] buffer = new byte[jsonSize];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    private String getJSONObject() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("data.json");
            int jsonSize = inputStream.available();
            byte[] buffer = new byte[jsonSize];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    private void createBaseJSON() {
        JSONObject object = new JSONObject();
        JSONArray usersArray = new JSONArray();
        try {
            object.put("users", usersArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        writeToFile(object);
    }

    private void writeToFile(JSONObject object) {
        File path = context.getFilesDir();
        String filename = "database.json";
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(path, filename));
            outputStream.write(object.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
