package com.example.observer.chatservicemessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.observer.chatservicemessage.manager.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_username_or_email, edt_password;
    private static String emailorusername, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_username_or_email = (EditText) findViewById(R.id.edt_username_or_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
    }

    public void onClick(View v) {
//        encryptedPassword();
        password = edt_password.getText().toString().trim();
        emailorusername = edt_username_or_email.getText().toString().trim();
        switch(v.getId()) {
            case R.id.btn_login :
                SessionManager.login(emailorusername, password);
                break;
            case R.id.btn_signup :
                SessionManager.signup(emailorusername, password);
                break;
            default:
                break;
        }

    }

//    private void login(){
//        AppController.getInstance().getRoot().authWithPassword(email, password, new Firebase.AuthResultHandler() {
//            @Override
//            public void onAuthenticated(AuthData authData) {
//                Toast.makeText(getApplicationContext(),
//                        "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(),
//                        Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onAuthenticationError(FirebaseError firebaseError) {
//                Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void signup(){
//        AppController.getInstance().getRoot().createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
//            @Override
//            public void onSuccess(Map<String, Object> result) {
//                Toast.makeText(getApplicationContext(), "Successfully created user account with uid: " + result.get("uid")
//                        + "and password: " + result.get("password"), Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onError(FirebaseError firebaseError) {
//                Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void encryptedPassword() {
        try {
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            String text = edt_password.getText().toString();  // password for encrypt

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(text);
            byte [] indexes = cipher.doFinal(bos.toByteArray());
            password = new String(indexes);

            Log.d(MainActivity.class.getSimpleName() + ": text encrypted", text);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (NoSuchPaddingException ex1) {
            ex1.printStackTrace();
        } catch (InvalidKeyException ex2) {
            ex2.printStackTrace();
        } catch (IllegalBlockSizeException ex3) {
            ex3.printStackTrace();
        } catch (IOException ex4) {
            ex4.printStackTrace();
        } catch (BadPaddingException ex5) {
            ex5.printStackTrace();
        }
    }
}
