package org.example;


import java.util.HashMap;

public class Main {

    private void testShopee(){
        String text = "ASSALAMUALAIKUM";
        HashMap<String, Integer> karakter = new HashMap<>();

        for (int x=0; x<text.length(); x++){
            String s = String.valueOf(text.charAt(x));
            if (karakter.containsKey(s)){
                karakter.replace(s, karakter.get(s), karakter.get(s) + 1);
            }else {
                karakter.put(s, 1);
            }
        }
        for (int x=0; x<text.length(); x++) {
            String s = String.valueOf(text.charAt(x));
            if (karakter.get(s)==1){
                System.out.println("Char "+s+ " Index of : "+x);
                break;
            }
        }
    }
}