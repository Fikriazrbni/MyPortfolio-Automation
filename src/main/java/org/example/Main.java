package org.example;

import org.testng.annotations.Test;

import java.util.*;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.l;

public class Main {
    @Test
    private void testShopee() {
        String text = "ASSALAMUALAIKUM";
        HashMap<String, Integer> karakter = new HashMap<>();

        for (int x = 0; x < text.length(); x++) {
            String s = String.valueOf(text.charAt(x));
            if (karakter.containsKey(s)) {
                karakter.replace(s, karakter.get(s), karakter.get(s) + 1);
            } else {
                karakter.put(s, 1);
            }
        }
        for (int x = 0; x < text.length(); x++) {
            String s = String.valueOf(text.charAt(x));
            if (karakter.get(s) == 1) {
                System.out.println("Char " + s + " Index of : " + x);
                break;
            }
        }
    }

    @Test
    public void testXL() {
        int max = 0, min = 0, sumPositive = 0, sumNegative = 0;
        List<Integer> listOfNumSorted = new ArrayList<>();
        List<Integer> listOfNum = new ArrayList<>();
        listOfNum.add(1);
        listOfNum.add(-3);
        listOfNum.add(6);
        listOfNum.add(8);
        listOfNum.add(-5);
        listOfNum.add(-1);
        listOfNum.add(-4);
        listOfNum.add(4);
        listOfNum.add(4);
        listOfNum.add(7);
        listOfNum.add(3);


        for (int x : listOfNum) {
            if (x < min) {
                min = x;
            }
        }
        for (int x : listOfNum) {
            if (x > max) {
                max = x;
            }
        }

        for (int x : listOfNum) {
            if (x < 0) {
                sumNegative++;
            } else {
                sumPositive++;
            }
        }

        for (int x : listOfNum) {
            try {
                if (x != 0) {

                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        while (true) {
            listOfNumSorted.add(min);
            listOfNum.remove(min);
            for (int x : listOfNum) {
                if (x > min) {


                }
            }
            if (listOfNum.isEmpty()) {
                break;
            }
        }


        System.out.println("Min Num : " + min);
        System.out.println("Max Num : " + max);
        System.out.println("Total Positive : " + sumPositive);
        System.out.println("Total Negative : " + sumNegative);
        System.out.println();
        System.out.println("Sorted Num : " + listOfNum.stream().sorted());
    }

    @Test
    public void testXL2() {
        int[] sampleList = {1, -3, 6, 8, -5, -1, -4, 4, 7, 3, 0};
        int max = sampleList[0];
        int min = sampleList[0];
        int totalPositif = 0;
        int totalNegatif = 0;

        try {
            // Mencari max, min, total positif, total negatif, dan cek ada angka 0
            for (int i = 0; i < sampleList.length; i++) {
                if (sampleList[i] == 0) {
                    throw new ZeroValueException("Error: Ada angka 0 dalam list");
                }
                if (sampleList[i] > max) {
                    max = sampleList[i];
                }
                if (sampleList[i] < min) {
                    min = sampleList[i];
                }
                if (sampleList[i] > 0) {
                    totalPositif++;
                }
                if (sampleList[i] < 0) {
                    totalNegatif++;
                }
            }

            // Mengurutkan angka dari kecil ke besar
            for (int i = 0; i < sampleList.length; i++) {
                for (int j = i + 1; j < sampleList.length; j++) {
                    if (sampleList[i] > sampleList[j]) {
                        int temp = sampleList[i];
                        sampleList[i] = sampleList[j];
                        sampleList[j] = temp;
                    }
                }
            }

            // Menampilkan hasil
            System.out.println("Max: " + max);
            System.out.println("Min: " + min);
            System.out.println("Total angka positif: " + totalPositif);
            System.out.println("Total angka negatif: " + totalNegatif);
            System.out.print("Angka dari kecil ke besar: ");
            for (int num : sampleList) {
                System.out.print(num + " ");
            }
        } catch (ZeroValueException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test() {
        List<Integer> listofNum = new ArrayList<>(Arrays.asList(-1, 2, -3, 4, -5, 6, -7, 8, -9));
        List<String> listString2 = new ArrayList<>(List.of("as", "asd", "cds", "zadqwd", "aaa", "dffff", "bbg", "h5h5f"));
        int[] arrayNumb = {1, -2, -3, 4, -5, 5, 6, -7, 8, -9, 10};
        String[] listString = {"as", "asd", "cds", "zadqwd", "aaa", "dffff", "bbg", "h5h5f"};
        Arrays.sort(arrayNumb);
        Collections.sort(listofNum);
        Collections.sort(listString2);
        Arrays.sort(listString);
        System.out.println(Arrays.toString(arrayNumb));
        System.out.println(listofNum);
        System.out.println(Arrays.toString(listString));
        System.out.println(listString2);
    }

    @Test
    public void test2() {
        List<String> kumpulanNama = new ArrayList<>(List.of("fikri", "katak", "oyo"));
        for (String currentName : kumpulanNama) {
            StringBuilder temp = new StringBuilder();
            for (int x = currentName.length(); x > 0; x--) {
                char c = currentName.charAt(x - 1);
                temp.append(c);
            }
            String result = temp.toString();
            if (currentName.equals(result)) {
                System.out.println(currentName + " adalah palindrom");
            } else {
                System.out.println(currentName + " bukan palindrom");
            }
        }
    }
}


class ZeroValueException extends Exception {
    public ZeroValueException(String message) {
        super(message);
    }
}