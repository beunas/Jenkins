package org.demoWebShop.utilities;

import com.github.javafaker.Faker;

import java.util.Random;

public class RandomDataUtility {

    Faker faker;


    public String getRandomString(String valueType) {
        String randomValue = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        if(valueType.equals("email")){
            randomValue = sb.toString() + "@gmail.com";
        }
        else if(valueType.equals("uname")){
            randomValue = sb.toString() ;
        }
        return randomValue;
    }

    public String  getFirstName(){
        faker=new Faker();
        String fname = faker.name().firstName();
        return fname;
    }
    public String  getLastName(){
        faker=new Faker();
        String lName = faker.name().lastName();
        return lName;
    }
}
