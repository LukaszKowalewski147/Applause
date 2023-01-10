package com.example.applause;

import android.content.Context;
import android.widget.Toast;

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

    public JSONCommunicator(Context context) {
        this.context = context;
    }

    public boolean loginConfirmed(String login, String password) {
        try {
            JSONObject jsonObject = new JSONObject(readJson());
            JSONArray usersArray = jsonObject.getJSONArray("users");
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject user = usersArray.getJSONObject(i);
                if (user.getString("login").equals(login)) {
                    if (user.getString("password").equals(password)) {
                        JSONObject settings = user.getJSONObject("settings");
                        Session.privateAccount = settings.getBoolean("privateAccount");
                        Session.soundsEnabled = settings.getBoolean("soundsEnabled");
                        Session.alwaysShowClapInstruction = settings.getBoolean("alwaysShowClapInstruction");
                        Session.alwaysShowProximityInstruction = settings.getBoolean("alwaysShowProximityInstruction");
                        return true;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerAccount(String login, String password) {
        try {
            JSONObject jsonObject = new JSONObject(readJson());
            JSONArray usersArray = jsonObject.getJSONArray("users");

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject user = usersArray.getJSONObject(i);
                if (user.getString("login").equals(login)) {
                    Toast.makeText(context, "Login jest już zajęty" , Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            JSONObject newAccount = new JSONObject();
            JSONObject settings = new JSONObject();
            JSONArray clapsArray = new JSONArray();

            settings.put("privateAccount", false);
            settings.put("soundsEnabled", true);
            settings.put("alwaysShowClapInstruction", true);
            settings.put("alwaysShowProximityInstruction", true);

            newAccount.put("login", login);
            newAccount.put("password", password);
            newAccount.put("settings", settings);
            newAccount.put("claps", clapsArray);

            usersArray.put(newAccount);

            writeToFile(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Nie udało się zarejestrować konta" , Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void deleteAccount() {
        boolean deleted = false;
        try {
            JSONObject jsonObject = new JSONObject(readJson());
            JSONArray usersArray = jsonObject.getJSONArray("users");

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject user = usersArray.getJSONObject(i);
                if (user.getString("login").equals(Session.login)) {
                    usersArray.remove(i);
                    deleted = true;
                    Toast.makeText(context, "Konto " + Session.login + " usunięte" , Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            writeToFile(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Nie udało się usunąć konta" , Toast.LENGTH_SHORT).show();
        }
        if (!deleted)
            Toast.makeText(context, "Nie znaleziono konta do usunięcia" , Toast.LENGTH_SHORT).show();
    }
    
    public void updateSettings() {
        try {
            JSONObject jsonObject = new JSONObject(readJson());
            JSONArray usersArray = jsonObject.getJSONArray("users");
            JSONObject user = null;
            for (int i = 0; i < usersArray.length(); i++) {
                user = usersArray.getJSONObject(i);
                if (user.getString("login").equals(Session.login)) {
                    break;
                }
            }
            if (user == null)
                throw new JSONException("nie znaleziono zalogowanego uzytkownika");
            JSONObject settings = user.getJSONObject("settings");
            settings.put("privateAccount", Session.privateAccount);
            settings.put("soundsEnabled", Session.soundsEnabled);
            settings.put("alwaysShowClapInstruction", Session.alwaysShowClapInstruction);
            settings.put("alwaysShowProximityInstruction", Session.alwaysShowProximityInstruction);

            writeToFile(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Nie udało się zaktualizować ustawień" , Toast.LENGTH_SHORT).show();
        }
    }

    public void updatePassword(String newPassword) {
        try {
            JSONObject jsonObject = new JSONObject(readJson());
            JSONArray usersArray = jsonObject.getJSONArray("users");
            JSONObject user = null;
            for (int i = 0; i < usersArray.length(); i++) {
                user = usersArray.getJSONObject(i);
                if (user.getString("login").equals(Session.login)) {
                    break;
                }
            }
            if (user == null)
                throw new JSONException("nie znaleziono zalogowanego uzytkownika");

            user.put("password", newPassword);

            writeToFile(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Nie udało się zmienić hasła" , Toast.LENGTH_SHORT).show();
        }
    }

    public String getCurrentPassword() {
        String password = null;
        try {
            JSONObject jsonObject = new JSONObject(readJson());
            JSONArray usersArray = jsonObject.getJSONArray("users");
            JSONObject user = null;
            for (int i = 0; i < usersArray.length(); i++) {
                user = usersArray.getJSONObject(i);
                if (user.getString("login").equals(Session.login)) {
                    break;
                }
            }
            if (user == null)
                throw new JSONException("nie znaleziono zalogowanego uzytkownika");
            password = user.getString("password");
        }  catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Nie udało się zaktualizować ustawień" , Toast.LENGTH_SHORT).show();
        }
        return password;
    }

    public void showAccounts() { //debugging only
        try {
            JSONObject jsonObject = new JSONObject(readJson());
            JSONArray usersArray = jsonObject.getJSONArray("users");

            if (usersArray.length() == 0) {
                Toast.makeText(context, "Brak kont do wyświetlenia", Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject user = usersArray.getJSONObject(i);
                String login = user.getString("login");
                String password = user.getString("password");
                Toast.makeText(context, "Login: " + login + "\n" + "Hasło: " + password, Toast.LENGTH_SHORT).show();
            }
            writeToFile(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readJson() {
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
