package com.mrboring.burpenps;
import java.security.SecureRandom;
public class Utils {
    public static Password gen(int length){
        Password finalPass = new Password();
        SecureRandom sRandom = new SecureRandom();
        byte[] sRandomArray = new byte[length];
        sRandom.nextBytes(sRandomArray);
        String asciiCapital_Letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String asciiLetters = "abcdefghijklmnopqrstuvwxyz";
        String ascii_Simbols ="!@#$%¨&*()_+-=/?[]{}^~";
        String ascii_Numbers = "0123456789";
        StringBuilder _password = new StringBuilder();
        for (int y = 0; y < sRandomArray.length; y++){
            if (sRandomArray[y] < 0){
                sRandomArray[y] = (byte) (sRandomArray[y]*(byte)-1);
            }
        }
        for (int x = 0; x < length; x++){
            double _defineChar = Math.random();
            if (_defineChar <= 0.25d){
                _password.append(
                        asciiLetters.charAt(
                                (sRandomArray[x] % asciiLetters.length()))
                );
            }
            else if (_defineChar > 0.25d && _defineChar <= 0.5d){
                _password.append(
                        asciiCapital_Letters.charAt(
                                (sRandomArray[x] % asciiCapital_Letters.length()))
                );
            }
            else if (_defineChar > 0.5d && _defineChar <= 0.75d){
                _password.append(
                        ascii_Simbols.charAt(
                                (sRandomArray[x] % ascii_Simbols.length()))
                );
            }
            else{
                _password.append(
                        ascii_Numbers.charAt(
                                (sRandomArray[x] % ascii_Numbers.length()))
                );
            }
        }
        finalPass.setText(_password.toString());
        return finalPass;
    }
}

