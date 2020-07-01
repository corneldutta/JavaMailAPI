package com.sendmail.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {

    public static FileInputStream fis;
    public static Properties config=new Properties();

    public Base(){
        try{
            fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\User.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try{
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
