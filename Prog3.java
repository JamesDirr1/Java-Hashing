//James Dirr CSC-464
//Prog 3


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Prog3 {


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Enter File Location: ");
        Scanner input = new Scanner(System.in);
        String fileloc = input.nextLine();
        File data = new File(fileloc);
        hash1(data);
        hash2(data);
        hash3(data);
    }

    public static void hash1(File data) throws FileNotFoundException {
        Scanner read = new Scanner(data);
        int sum = 0;
        int collisions = 0;
        int mod;
        int item_num = read.nextInt();
        read.nextLine();
        LinkedList hashTable1[] = new LinkedList[151];

        String items1[] = new String[item_num];
        //for loop to read each item and add it to an array
        for(int i = 0; i < item_num; i++){
            items1[i] = read.nextLine();
            //for loop to get the ASCII value of each item
            sum = 0; //resets sum to 0
            for(int j = 0; j < items1[i].length(); j++){
                sum += items1[i].charAt(j);
            }
            mod = sum % 151;
            //check if cell is empty and creates a new list if true
            if(hashTable1[mod] == null){
                hashTable1[mod] = new LinkedList();
                hashTable1[mod].add(items1[i]);
            } else {
                //checks if list contains item, if true adds 1 to collisions and adds item to the end
                if(!hashTable1[mod].contains(items1[i])){
                    collisions++;
                    hashTable1[mod].add(items1[i]);
                }
            }
        }
        System.out.println("Hash 1 total collisions = " + collisions);
    }

    public static void hash2(File data) throws FileNotFoundException{
        Scanner read = new Scanner(data);
        long val = 0;
        int collisions = 0;
        int mod;
        int FML;
        int sum;
        char ch;
        boolean upper = false;
        boolean repeat = false;
        String binary = "";
        String reverse= "";
        String list= "";
        ArrayList<Character> chList= new ArrayList<>();
        int item_num = read.nextInt();
        read.nextLine();
        LinkedList hashTable2[] = new LinkedList[151];
        String items2[] = new String[item_num];
        for(int i = 0; i < item_num; i++) {
            items2[i] = read.nextLine();
            //reset values
            binary = "";
            reverse = "";
            list = "";
            upper = false;
            repeat = false;
            FML = 0;
            chList.clear();
            sum = 0;


            //bit 7
                if(items2[i].length() <= 7 ){
                    binary += "1";
                } else {
                    binary += "0";
                }
            //bit 6
                //reverse string, upper case, repeat, and ASCII
                for(int j = 0; j < items2[i].length(); j++ ){
                    ch = items2[i].charAt(j);

                    sum += ch;
                    if (Character.isUpperCase(ch)){
                        upper = true;
                    }
                        list += ch;
                    //System.out.println(list + "----" + ch);
                    if (chList.contains(ch)){
                        //System.out.println("rep");
                        repeat = true;
                    }
                        reverse = ch + reverse;
                    chList.add(ch);
                }
                //System.out.println(items2[i] + " " + reverse);
                if(reverse.compareTo(items2[i]) > 0){
                    binary += "1";
                } else{
                    binary += "0";
                }
            //bit 5
                if(upper){
                    binary += "1";
                } else {
                    binary += "0";
                }
            //bit 4
                FML += items2[i].charAt(0);
                FML += items2[i].charAt(items2[i].length() / 2);
                FML += items2[i].charAt(items2[i].length()-1);
                if(FML % 2 == 0){
                    binary += "0";
                } else {
                    binary += "1";
                }
            //bit 3
                if(repeat){
                    binary += "1";
                } else {
                    binary += "0";
                }
            //bit 2
                if(sum % 2 == 0){
                    binary += "0";
                } else {
                    binary += "1";
                }
            //bit 1
                if("aeiouAEIOU".indexOf(items2[i].charAt(0)) != -1){
                    binary += "1";
                } else{
                    binary += "0";
                }
            //bit 0
                if(items2[i].length() % 2 == 0){
                    binary += "0";
                }else{
                    binary +="1";
                }
                val = Long.parseLong(binary, 2);
                /** \/ prints out string and its binary and pre mod value \/ */
                //System.out.println(items2[i] + " " + binary +  " " +  val);
            mod = (int) val % 151;
            //check if cell is empty and creates a new list if true
            if(hashTable2[mod] == null){
                hashTable2[mod] = new LinkedList();
                hashTable2[mod].add(items2[i]);
            } else {
                //checks if list contains item, if true adds 1 to collisions and adds item to the end
                if (!hashTable2[mod].contains(items2[i])) {
                    collisions++;
                    hashTable2[mod].add(items2[i]);
                }
            }

        }
        System.out.println("Hash 2 total collisions = " + collisions);
    }

    public static void hash3(File data) throws FileNotFoundException {
        Scanner read = new Scanner(data);

        int item_num = read.nextInt();
        read.nextLine();
        LinkedList hashTable3[] = new LinkedList[151];
        String items3[] = new String[item_num];
        long poly = 0;
        int length = 0;
        int collisions = 0;
        long cal;
        int mod;

        for(int i = 0; i < item_num; i++) {
            poly = 0;
            length = 0;
            items3[i] = read.nextLine();
            length = items3[i].length()-1;
            poly = items3[i].charAt(length);
            for (int j = length - 1; j >= 0; j--){
                poly = 31 * poly + items3[i].charAt(j);
            }
            //System.out.println(poly);
            cal = poly % 151;
            //System.out.println(cal);
            mod = (int)cal;
            if(hashTable3[mod] == null){
                hashTable3[mod] = new LinkedList();
                hashTable3[mod].add(items3[i]);
            } else {
                //checks if list contains item, if true adds 1 to collisions and adds item to the end
                if (!hashTable3[mod].contains(items3[i])) {
                    collisions++;
                    //System.out.println("collision");
                    hashTable3[mod].add(items3[i]);
                }
            }
        }
        System.out.println("Hash 3 total collisions = " + collisions);
    }
}