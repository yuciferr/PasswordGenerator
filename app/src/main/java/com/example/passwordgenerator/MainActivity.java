package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Password password = new Password();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = findViewById(R.id.txt);
        TextView size = findViewById(R.id.pSize);
        Switch s1 = findViewById(R.id.switch1);
        Switch s2 = findViewById(R.id.switch2);
        Switch s3 = findViewById(R.id.switch3);
        Switch s4 = findViewById(R.id.switch4);

        s1.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b) password.join(password.getLetters().toCharArray());
            else password.remove(password.getLetters().toCharArray());
        });
        s2.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b) password.join(password.getLetters().toUpperCase(Locale.ROOT).toCharArray());
            else password.remove(password.getLetters().toUpperCase(Locale.ROOT).toCharArray());
        });
        s3.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b) password.join(password.getNumbers().toCharArray());
            else password.remove(password.getNumbers().toCharArray());
        });
        s4.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b) password.join(password.getSymbols().toCharArray());
            else password.remove(password.getSymbols().toCharArray());
        });


        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> {
            if(password.getList().isEmpty()){
                Toast.makeText(getApplicationContext(), "Choose A Symbol",
                        Toast.LENGTH_SHORT).show();
            }else {
            String passWord = password.passwordGenerate(Integer.parseInt(size.getText().toString()),password.getList());
            txt.setText(passWord);
            }

        });

        ClipboardManager cM = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        Button copy = findViewById(R.id.copy);
        copy.setOnClickListener(v -> {
            ClipData cD = ClipData.newPlainText("text", txt.getText().toString());
            cM.setPrimaryClip(cD);

            Toast.makeText(getApplicationContext(), "Text Copied",
                    Toast.LENGTH_SHORT).show();
        });

        Button plus = findViewById(R.id.plus);
        plus.setOnClickListener(v -> {
           int num = Integer.parseInt(size.getText().toString());
           num++;
           size.setText(String.valueOf(num));

        });

        Button minus = findViewById(R.id.minus);
        minus.setOnClickListener(v -> {
            int num = Integer.parseInt(size.getText().toString());
            num--;
            size.setText(String.valueOf(num));

        });
    }

}

class Password{
    private ArrayList<Character> list= new ArrayList<>();

    private final String letters = "qwertyuopğüasdfghjklşizxcvbnmöç";
    private final String numbers = "1234567890";
    private final String symbols = "!'^+%&/()=?_-|*}][{½$#£<>.,:;`";
    private String password;


    Password(){
        password = "";
    }

    public String passwordGenerate(int size, ArrayList<Character> chr){
        password = "";
        for(int i=0; i<size; i++){
            int rnd = new Random().nextInt(chr.size());
            password+=chr.get(rnd);
        }
        return password;
    }

    public ArrayList<Character> join(char[] a){
        for(char x:a){
            list.add(x);
        }
        return list;
    }
    public ArrayList<Character> remove(char[] a){
        for(char x:a){
            if(list.contains(x)){
                list.remove(list.indexOf(x));
            }
        }
        return list;
    }

    public ArrayList<Character> getList(){
        return list;
    }
    public String getLetters(){
        return letters;
    }
    public String getNumbers(){
        return numbers;
    }
    public String getSymbols(){
        return symbols;
    }
}