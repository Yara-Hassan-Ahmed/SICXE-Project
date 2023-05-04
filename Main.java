package projectSICXE;

import java.util.*;
import java.io.*;

public class Main {
    //create array list type string to create tables that holds the input data from inSICXE.txt
    static ArrayList<String> locctr = new ArrayList<>();//holds the location counter
    static ArrayList<String> label = new ArrayList<>();//holds the labels
    static ArrayList<String> instr = new ArrayList<>();//holds the instruction
    static ArrayList<String> refr = new ArrayList<>();//holds the reference
    static ArrayList<String> objCode = new ArrayList<>();//holds the object code
    static ArrayList<String> first4opcode= new ArrayList<>();//holds the first 4 opcodes
    static ArrayList<String> Binopcode = new ArrayList<>();//holds the binary opcodes
    static ArrayList<String> second2opcode = new ArrayList<>();//holds the second 2 opcodes
    static ArrayList<String> opNI = new ArrayList<>();//holds the opcode N & I
    static ArrayList<ArrayList<String>> Registers = new ArrayList<>();//holds another array list that holds the register with their numbers
    static ArrayList<ArrayList<String>> lit = new ArrayList<>();
    static ArrayList<String> litRow = new ArrayList<>();
    //initializing static variables
    static String opcode;
    static String temp1;
    static String temp2;
    static String Register1;
    static String Register2 = null;//it will be zero if there's no ","
  //Function Format 2 (Opcode, register 1, register 2)
    public static void FormatTwo(int i ){
    	//to see if it matches the format in the opcode table
        for (int j = 0 ; j <OPTAB.length; j++){
            String reg2 = null;
            String reg1 = null;
            String registers;
            if (instr.get(i).equals (OPTAB[j][0])){
                if(OPTAB[j][1].equals("2")){
                    opcode = OPTAB[j][2];
                  //if it contains "," which means we 2 registers it will get the values from the opcodeFormat2
                    String [] arr = refr.get(i).split(",");
                    if (refr.get(i).contains(",")){

                        Register1 = arr[0];
                        Register2 = arr[1];
                        for(int k = 0 ; k<Registers.size(); k++){
                            if (Register1.equals(Registers.get(k).get(0))){
                                reg1 = Registers.get(k).get(1);}

                            if (Register2.equals(Registers.get(k).get(0))){
                                reg2 = Registers.get(k).get(1);
                            }

                        }

                    }
                  //if we only have 1 register
                    else{
                        Register1 = arr[0];
                        for(int k = 0 ; k<Registers.size(); k++){
                            if (Register1.equals(Registers.get(k).get(0))){
                                reg1 = Registers.get(k).get(1);}}
                        reg2="0";
                    }
                    registers = reg1 + reg2;                    
                    String objFormat2 = opcode + registers;
                    //save it in the arr list objCode 
                    objCode.add(objFormat2);
                }
            }
        }

    }//end of function format 2
    private static final String[][] OPTAB = new String[59][3];//private string of array that holds a part of a memory
    public static void main(String[] args) {
        ArrayList<String> RegistersRow1 = new ArrayList<>();
        ArrayList<String> RegistersRow2 = new ArrayList<>();
        ArrayList<String> RegistersRow3 = new ArrayList<>();
        ArrayList<String> RegistersRow4 = new ArrayList<>();
        ArrayList<String> RegistersRow5 = new ArrayList<>();
        ArrayList<String> RegistersRow6 = new ArrayList<>();
        ArrayList<String> RegistersRow7 = new ArrayList<>();
        ArrayList<String> RegistersRow8 = new ArrayList<>();
        ArrayList<String> RegistersRow9 = new ArrayList<>();
        ArrayList<String> RegistersRow10 = new ArrayList<>();
        ArrayList<String> RegistersRow11= new ArrayList<>();
        RegistersRow1.add("A");
        RegistersRow1.add("0");
        Registers.add(RegistersRow1);
        RegistersRow2.add("X");
        RegistersRow2.add("1");
        Registers.add(RegistersRow2);
        RegistersRow3.add("L");
        RegistersRow3.add("2");
        Registers.add(RegistersRow3);
        RegistersRow4.add("B");
        RegistersRow4.add("4");
        Registers.add(RegistersRow4);
        RegistersRow5.add("S");
        RegistersRow5.add("5");
        Registers.add(RegistersRow5);
        RegistersRow6.add("T");
        RegistersRow6.add("6");
        Registers.add(RegistersRow6);
        RegistersRow7.add("F");
        RegistersRow7.add("7");
        Registers.add(RegistersRow7);
        RegistersRow8.add("PC");
        RegistersRow8.add("8");
        Registers.add(RegistersRow8);
        RegistersRow9.add("SW");
        RegistersRow9.add("9");
        Registers.add(RegistersRow9);
        //Initializing variables
        String line;
        boolean end = false;// the boolean variable is used to check if the reader reached the end or not
        int counter = 3;
        int locationCounter;
        String length;

        try {
            //FileReader is meant to read streams of characters
            FileReader file = new FileReader("inSICXE.txt");
            //BufferedReader will buffer the input from the specified file
            BufferedReader br = new BufferedReader(file);
            //readLine() reads the line from the file txt
            //while loop to check the end of the line
            while ((line = br.readLine()) != null) {
                line = line.trim();//it checks the whole line, it returns the string and until it goes to the end
                String[] values = line.split("\\s+");//when it reaches the end of the line it split it to the next line
                //for loop to check the values length of the line
                for (int i = 0; i < values.length; i++) {
                    //if the arr[0] is end that means the end of reading the file
                    if (values[0].equals("end")) {
                        end = true;
                        break;
                    }
                }
                //if it didn't reach the end of the program then it will go in if conditions
                if (!end) {
                    //if the length of the line is equal 3 then it will add every read line to its place in the arr
                    if (values.length == 3) {// check if the array arr contain 3 columns or not
                        label.add(values[0]);// adding the first element in the array arr to the arraylist label
                        instr.add(values[1]);// adding the second element in the array arr to the arraylist instruction
                        refr.add(values[2]);// adding the second element in the array arr to the arraylist reference
                    }
                    //if the length of the line is equal 2 then it will add every read line to its place in the arr and * in the empty label part
                    else if (values.length == 2) {//check if the array arr contain only 2 columns
                        label.add("***");// adding * to the label arraylist
                        instr.add(values[0]);// adding the first element in the array arr to the arraylist instruction
                        refr.add(values[1]);// adding the second element in the array arr to the arraylist reference
                    }
                    //if the length of the line is equal RSUB
                    //then it will add every read line to its place in the arr and * in the empty label part and refrence part
                    else if(values[0].equals("RSUB")) {
                        label.add("***");// adding * to the label arraylist
                        instr.add(values[0]);// adding the first element in the array arr to the arraylist instruction
                        refr.add("***");// adding * to the reference arraylist
                    }
                    else if(values[0].equals("LTORG")) {
                        label.add("***");// adding * to the label arraylist
                        instr.add(values[0]);// adding the first element in the array arr to the arraylist instruction
                        refr.add("***");// adding * to the reference arraylist
                    }

                }
                //else if there's nothing in the refrence part will add * in the empty part in the arr
                else {
                    label.add(values[0]);// adding the first element in the array arr to the arraylist label
                    instr.add(values[1]);// adding the second element in the array arr to the arraylist instruction
                    refr.add("***");// adding * to the reference arraylist
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //print the file in the output
        System.out.println("*******Program 1*******" +"\n");
        System.out.println("Label " + "Instructions" + " Reference");
        for (int i = 0; i < label.size(); i++) {
            System.out.println(label.get(i) + " ---> " + instr.get(i) + " ---> "
                    + refr.get(i) + "\n");
        }
        // adding the first reference in the program to the location counter
        // filling the first two location counter
        locctr.add(refr.get(0));
        locctr.add(refr.get(0));
//////////////////////End of task 1//////////////////////
        
//////////////////////Pass 1//////////////////////
        //Format 2
        for (int i = 1; i < instr.size()-1; i++) {//for loop to return the number of elements in the list
            if(instr.get(i).matches("[a-zA-Z]+R")){
                //Returns the element at the specified position in this list and Compares it to the specified object
                int x = Integer.parseInt(locctr.get(i), 16);//convert from integer to hex
                locationCounter = x + 2;//format 2 (add 2 on the prev locctr)
                //to check if the locctr is 4 digits, first we convert the int number to str to count the digits
                temp1 = Integer.toHexString(locationCounter);
                String number = "";
                //to check if the length of the string is less than 4 then go into the for loop
                if (temp1.length() < 4) {
                    for (int z = 4; z > temp1.length(); z--) {
                        number += "0";
                    }
                }
                //increment the temp +1 until it completes the 4 digits
                number += temp1;
                //save it in the arr list locctr
                locctr.add(number);
            }

            //Returns the element at the specified position in this list and Compares it to the specified object
            //if it's base then there's no calculation and will be like the prev locctr
            else if(instr.get(i).equals("BASE")){
                locctr.add(locctr.get(i)); 
            }
          /*  else if (instr.get(i).equals("LTORG")) {
            	int locLtorg = Integer.parseInt(locctr.get(i));
            	int newlocltorg = locLtorg + 3;
            	String newltorg = Integer.toHexString(newlocltorg);
            	locctr.add(newltorg);
            }
            else if (instr.get(i).startsWith("=")) {
            	
        		String litInst = instr.get(i);
        		String litLocctr= locctr.get(i);
        			litRow.add(litInst);
        			litRow.add(litLocctr);
        			lit.add(litRow);
        			System.out.println(lit);
        		
        	
        	
        }*/

            //Format 4
            else if(instr.get(i).contains("+")) {
                //Returns the element at the specified position in this list and Compares it to the specified object
                int x = Integer.parseInt(locctr.get(i), 16);//convert from integer to hex
                locationCounter = x + 4;//format 4 (add 4 on the prev locctr)
                //to check if the locctr is 4 digits, first we convert the int number to str to count the digits
                temp1 = Integer.toHexString(locationCounter);
                String number = "";
                //to check if the length of the string is less than 4 then go into the for loop
                if (temp1.length() < 4) {
                    for (int z = 4; z > temp1.length(); z--) {
                        number += "0";
                    }
                }
                //increment the temp +1 until it completes the 4 digits
                number += temp1;
                //save it in the arr list locctr
                locctr.add(number);
            }
          
          //locctr format 5  
          /*  else if(instr.get(i).startsWith("&")) {
                //Returns the element at the specified position in this list and Compares it to the specified object
                int x = Integer.parseInt(locctr.get(i), 16);//convert from integer to hex
                locationCounter = x + 5;//format 4 (add 5 on the prev locctr)
                //to check if the locctr is 4 digits, first we convert the int number to str to count the digits
                temp1 = Integer.toHexString(locationCounter);
                String number = "";
                //to check if the length of the string is less than 4 then go into the for loop
                if (temp1.length() < 4) {
                    for (int z = 4; z > temp1.length(); z--) {
                        number += "0";
                    }
                }
                //increment the temp +1 until it completes the 4 digits
                number += temp1;
                //save it in the arr list locctr
                locctr.add(number);
            }*/

            else {
                //Returns the element at the specified position in this list and Compares it to the specified object
                //it will check if it's RESW
                if (instr.get(i).equals("RESW")) { //value*3 -> hex + prev add
                    int locctrNumber = Integer.parseInt(locctr.get(i), 16);//convert from integer to hex
                    int refNumber = (Integer.parseInt(refr.get(i))) * 3;//gets the reference number and multiply by 3
                    // adding the new ref to the location counter
                    locationCounter = locctrNumber + refNumber;//to calculate the locctr of RESW ((ref*3)& convert to hex then add)
                    //to check if the locctr is 4 digits, first we convert the int number to str to count the digits
                    temp1 = Integer.toHexString(locationCounter);
                    String number = "";
                    //to check if the length of the string is less than 4 then go into the for loop
                    if(temp1.length() < 4){
                        //if it's less than 4 digits then fill it with "0"
                        for(int y=4; y>temp1.length(); y--){
                            number += "0";
                        }
                    }
                    //increment the temp +1 until it completes the 4 digits
                    number += temp1;
                    //save it in the arr list locctr
                    locctr.add(number);
                }
                //it will check if it's RESB
                else if (instr.get(i).equals("RESB")) {
                    int locctrNumber = Integer.parseInt(locctr.get(i), 16);//convert from integer to hex
                    int refNumber = (Integer.parseInt(refr.get(i)));//gets the reference number and convert to hex
                    //add the new ref to the location counter
                    locationCounter = locctrNumber + refNumber;//to calculate the locctr of RESW (ref & convert to hex then add)
                    //to check if the locctr is 4 digits, first we convert the int number to str to count the digits
                    temp1 = Integer.toHexString(locationCounter);
                    String number = "";
                    //to check if the length of the string is less than 4 then go into the for loop
                    if(temp1.length() < 4){
                        //if it's less than 4 digits then fill it with "0"
                        for(int y=4; y>temp1.length(); y--){
                            number += "0";
                        }
                    }
                    //increment the temp +1 until it completes the 4 digits
                    number += temp1;
                    //save it in the arr list locctr
                    locctr.add(number);
                }
                //it will check if it's BYTE
                else if (instr.get(i).equals("BYTE")) {
                    String type = refr.get(i);//convert the reference to string
                    char character = type.charAt(0);//take the first character in the string type and save it in the varaible x
                    if (character == 'C'){//if the first character is c
                        String number = type.substring(2,type.length()-1);//take the characters in the string type from the third character to the end - 1
                        int refNumber = number.length();// calculate the length of the string number
                        int locctrNumber = Integer.parseInt(locctr.get(i),16);//convert the location counter to hexadecimal
                        int locationCounter1 = refNumber + locctrNumber;//add the new ref to the location counter
                        temp1 = Integer.toHexString(locationCounter1);
                        String num = "";
                        //to check if the length of the string is less than 4 then go into the for loop
                        if(temp1.length() < 4){
                            //if it's less than 4 digits then fill it with "0"
                            for(int y=4; y>temp1.length(); y--){
                                num += "0";
                            }
                        }
                        //increment the temp +1 until it completes the 4 digits
                        num += temp1;
                        //save it in the arr list locctr
                        locctr.add(num);
                    }
                    else {//if the charcter isnot c
                        String number = type.substring(2,type.length()-1);// take the characters in the string type from the third character to the end -1
                        int refToNum;
                        if (number.length()%2 ==0)// to check if the number is odd or even
                        {
                            int newnumber = number.length()/2;//if the number is even than it is divided by 2
                            refToNum = newnumber;// and added to the reference
                        }
                        else{
                            int newnumber = (number.length()+1)/2;// if the number is odd than add 1 and divide it by 2
                            refToNum = newnumber;//than add it to the reference
                        }
                        int ctrToHexNum = Integer.parseInt (locctr.get(i),16);//convert the location to hexadecimal
                        int locationCounter2 = refToNum + ctrToHexNum;//add the reference to the location
                        temp1 = Integer.toHexString(locationCounter2);
                        String num = "";
                        //to check if the length of the string is less than 4 then go into the for loop
                        if(temp1.length() < 4){
                            //if it's less than 4 digits then fill it with "0"
                            for(int y=4; y>temp1.length(); y--){
                                num += "0";
                            }
                        }
                        //increment the temp +1 until it completes the 4 digits
                        num += temp1;
                        //save it in the arr list locctr
                        locctr.add(num);
                    }
                }
                //else if it's smth else than the previous conditions if the instruction is word or any other type
                else {
                    int locctrNumber = Integer.parseInt(locctr.get(i), 16);//convert from integer to hex
                    locationCounter = locctrNumber + counter;//to calculate the locctr of a WORD or any other type
                    //to check if the locctr is 4 digits, first we convert the int number to str to count the digits
                    temp1 = Integer.toHexString(locationCounter);
                    String number = "";
                    //to check if the length of the string is less than 4 then go into the for loop
                    if(temp1.length() < 4){
                        //if it's less than 4 digits then fill it with "0"
                        for(int y=4; y>temp1.length(); y--){
                            number += "0";
                        }
                    }
                    //increment the temp +1 until it completes the 4 digits
                    number += temp1;
                    //save it in the arr list locctr
                    locctr.add(number);
                }
            }
        }
        //to print out the location counter after doing the calculation
        System.out.println("*******Location Counter*******" +"\n");
        for (int i=0; i<locctr.size(); i++){
            System.out.println(locctr.get(i));
        }
//////////////////////Program Length//////////////////////
//to get the length of the program by calculating (End - Start)
        int sizeOfLocctr = locctr.size(); //to get the size of the locctr
        int startOfProg = Integer.parseInt(locctr.get(0), 16);//to get the start locctr
        int endOfProg = Integer.parseInt(locctr.get(sizeOfLocctr-1), 16);//to get the end locctr

        locationCounter = endOfProg - startOfProg;//Length of the program = End - Start
        length = Integer.toHexString(locationCounter);//Returns a string representation of the integer argument as hexa


        //to print out the length of the program
        System.out.println("*******Length Of Program*******" +"\n");
        System.out.println("Length = " + length +"\n");

//////////////////////Symbol Table//////////////////////
//to print out the Symbol table
        System.out.println("*******Symbol Table*******"+"\n");
        System.out.println("Locctr " + " Label");
        for (int i=1; i<locctr.size(); i++) {
            if (label.get(i) != "**  ") {
                System.out.println(locctr.get(i) + "--->" + label.get(i));
            }
        }
        System.out.println("\n"+"\n");
//////////////////////End of pass 1//////////////////////
        
//////////////////////Pass 2//////////////////////
        String N = "", I = "", X = "",B="",P="",E = "";//Initializing the flag bits
        initialize();
        objCode.add("***");// adding empty row in the arraylist object code
//in pass 2 we don't calculate the object code for the "Start, RESW, RESB, End, BASE"
        for (int i = 1; i < instr.size(); i++) {
            //by going by the list and checking if this word are similar
            if (instr.get(i).equals("RESW") || instr.get(i).equals("RESB")
                    || instr.get(i).equals("END") || instr.get(i).equals("BASE")) {
                objCode.add("***");
            }
            //if it was WORD then we get the reference number and save it in an int number to convert it from decimal to hexa
            else if (instr.get(i).equals("WORD")) {
                temp1 = "";
                int number = Integer.parseInt(refr.get(i));

                String word = Integer.toHexString(number);
//checking if it's 6 digits after converting it to hexa number, if not we will add "0" until it's 6 digits
                for (int digits = 6; digits > word.length(); digits--) {
                    temp1 += "0";
                }
                //increment the temp +word until it completes the 6 digits
                temp1 += word;
                //save it in the arr list objCode
                objCode.add(temp1);
            }
            //if it was RSUB then we will use OPTAB to normally calculate objCode
            else if (instr.get(i).equals("RSUB")) {
                temp1 = "";
                //for loop to check the opcode length to start calculating every object code
                for (int j = 0; j < OPTAB.length; j++) {
                    if (instr.get(i).equals(OPTAB[j][0])) {
                        opcode = OPTAB[j][2];
                    }
                }
                temp1 = opcode;
                //checking if it's 6 digits after converting it to hexa number, if not we will add "0" until it's 6 digits
                for (int digits = 6; digits > opcode.length(); digits--) {
                    //increment the temp by 1 until it completes the 6 digits by adding "0"
                    temp1 += "0";
                }
                //save it in the arr list objCode
                objCode.add(temp1);
            }
       /*     else if (instr.get(i).startsWith("=")) {
            	temp1 = "";
                //the byte digits may be C or X
                String[] values = refr.get(i).split("’|'|,");
                //if it's C
                if (values[0].matches("c|C")) {
                    temp1 = values[1];
                }
                //else if it's X
                else
                    temp1 = values[1];
                //then add temp in the arraylist objCode
                objCode.add(temp1);}*/
 
            //if it was BYTE then we get the reference number and save it in a string temp
            else if (instr.get(i).equals("BYTE")) {
                temp1 = "";
                //the byte digits may be C or X
                String[] values = refr.get(i).split("’|'|,");
                //if it's C
                if (values[0].matches("c|C")) {
                    temp1 = values[1];
                }
                //else if it's X
                else
                    temp1 = values[1];
                //then add temp in the arraylist objCode
                objCode.add(temp1);
            } else {
                // format 1
                for (int j = 0; j < OPTAB.length; j++) {
                	//if it matches the second column "1" as in format 1
                    if (instr.get(i).equals(OPTAB[j][0])) {
                        if (OPTAB[j][1].equals("1")) {
                        	//it will take the value in third column as it's the opcode value  
                            opcode = OPTAB[j][2];
                            //then add opcode in the arraylist objCode
                            objCode.add(opcode);
                            break;
                        }
                    }
                }
                //calling for function format 2 to do what in it
                FormatTwo(i);
                
                //format 4
                if (instr.get(i).startsWith("+")) {
                	//Initializing the flag bits with the format 4 values
                    N = "1";
                    I = "1";
                    X = "0";
                    B = "0";
                    P = "0";
                    E = "1";
                    String instrOPTAB = instr.get(i).substring(1);//to detach the "+" from the label
                    String opCode="";
                    String address ="";
                    String reference;
                    //if it begin with a certain symbol it will change specific flag values
                    if (refr.get(i).startsWith("#")) {
                        N = "0";
                    } else if (refr.get(i).startsWith("@")) {
                        I = "0";
                    } else if (refr.get(i).contains(",X")) {
                        X = "1";
                    }
                    //to get the value of opcode from OPTAB
                    for (int j = 0; j < OPTAB.length; j++) {
                        if (instrOPTAB.equalsIgnoreCase(OPTAB[j][0])) {
                            opCode = OPTAB[j][2];
                            int num = Integer.parseInt(opCode, 16);//converting the opcode to hexa
                            String binary = Integer.toBinaryString(num);//then converting it to binary
                            //checks the length of the binary number
                            while (binary.length() < 8) {
                                binary = 0 + binary;
                            }
                            
                            String opccode = binary.substring(0, 6); // holds the first 6 digits in the opcode 
                            Binopcode.add(opccode);//array holds the opcode 
                            String opcode1st4 = binary.substring(0, 4);// hold the first four digits in the opcode to convert them to hexadecimal number
                            String opcode2nd2 = binary.substring(4, 6);// holds the second 2 digits in the opcode to concatenate them with n,i flags
                            
                            //if the reference got any of these symbols then it will add the value in the reference
                            if (refr.get(i).contains("#") || refr.get(i).contains("@")) {
                                String[] values = refr.get(i).split("#|@");
                                reference = values[1];
                            } else //if not then it will get it from the refr arraylist
                                reference = refr.get(i);
                            //if this reference matches the A-Z
                            if (reference.matches("[a-zA-Z]+")) {
                            	//to loop over the size of the refr arraylist
                                for (int k = 0; k < refr.size(); k++) {
                                	//to see if it's equal to the label
                                    if (reference.equals(label.get(k))) {
                                    	//to get the address of the reference
                                        address = "0" + locctr.get(k);
                                    }
                                }
                            } else {
                                int number = Integer.parseInt(reference);//converting the reference to hexa
                                String word = Integer.toHexString(number);//converting the number to string
                                //checks the address contains 5 digits or not
                                for (int w = 5; w > word.length(); w--) {
                                    address += "0";
                                }
                              //increment the address +word until it completes the 6 digits
                                address += word;
                            }
                            //getting the opcode and the flag converting it from binary to hexa
                            int opCodeAndFlagBinary = Integer.parseInt(opcode1st4 +opcode2nd2 + N + I + X + B + P + E, 2);
                            String opCodeAndFlagHexa = Integer.toString(opCodeAndFlagBinary, 16);
                            //save it in the arr list objCode
                            objCode.add(opCodeAndFlagHexa + address);
                            break;
                        }
                      }
                   }
               /* if (instr.get(i).startsWith("&")) {
                
				         //Initializing the flag bits with the format 3 values     
				                    N = "1";
				                    I = "1";
				                    X = "0";
				                    B = "0";
				                    P = "0";
				                    E = "0";
				                  //Initializing some values
				                    String address = null;
				                    String disp = "";
				                    String ref;
				                    //if it begin with a certain symbol it will change specific flag values
				                    if (refr.get(i).startsWith("#")) {
				                        N = "0";
				                    } else if (refr.get(i).startsWith("@")) {
				                        I = "0";
				                    } else if (refr.get(i).contains(",b")){
				                        B ="1";
				                    }
				                    //to get the value of opcode from OPTAB
				                    for (int j = 0; j < OPTAB.length; j++) {
				                        if (instr.get(i).equals(OPTAB[j][0])) {
				                            opcode = OPTAB[j][2];
				                        }
				                    }
		                            //if the reference got any of these symbols then it will add the value in the reference
				                    if (refr.get(i).contains("#") || refr.get(i).contains("@")) {
				                        String[] values = refr.get(i).split("#|@");
				                        ref = values[1];
				                    }
		                            //if the reference got "," then it will add the value in the reference
				                    else if (refr.get(i).contains(",b")) {
				                        String[] values = refr.get(i).split(",b");
				                        ref = values[0];
				                    }
				                  //if not then it will get it from the refr arraylist
				                    else
				                        ref = refr.get(i);
				                    //to get the displacement value
				                  //Initializing some values
				                    int Disp =0;
				                    int TA=0;
				                    int PC=0;
				                    if(ref.matches("[a-zA-Z]+")){
				                        for (int j = 0; j < refr.size(); j++) {
				                            if (ref.equals(label.get(j))) {
				                                address = locctr.get(j);
				                                TA = Integer.parseInt(address,16);//converting the address to hexa
				                                PC = Integer.parseInt(locctr.get(i+1), 16);//converting the previous location counter to hexa
				                                Disp = TA-PC;//getting the displacement if it's PC
				                                break;
				                            }
				                        }
				                        //checking the conditions of the value we got from the displacement
				                        //if the value is correct then it will change the flag bit P
				                        if(Disp >= -2048 && Disp <= 2047){
				                            P = "1";
				                        }
				                        //if it's not correct then it will change the flag bit B
				                        else {
				                            String pos = "";
				                            String base = "";
				                            B = "1";
				                            //to get the position that has the instruction BASE
				                            for (int j = 0; j < instr.size(); j++) {
				                                if (instr.get(j).equals("BASE")) {
				                                    pos = refr.get(j);
				                                }
				                            }
				                            //if the label = the pos then we will get the location counter of the base
				                            for (int j = 0; j < label.size(); j++) {
				                                if (label.get(j).equals(pos)) {
				                                    base = locctr.get(j);
				                                }
				                            }
				                            int BASE = Integer.parseInt(base, 16);//converting the base to hexa
				                            Disp = TA-BASE;//calculating the displacement if it's base
				                        }
				                        String displacement = Integer.toHexString(Disp);//converting the disp to string
				                        //if the displacement length is less than 3 then we will add "0"
				                        if(displacement.length()>3){
				                            displacement = displacement.substring(displacement.length()-3, displacement.length());
				                        }
				                        //increment the disp by 1 until it completes the 3 digits by adding "0"
				                        for(int w = 3; w>displacement.length(); w--){
				                            disp += "0";
				                        }
				                        disp += displacement;
				                    }
				
				                    else {	
				                        int numb = Integer.parseInt(ref);//converting ref to hexa
				                        String wor = Integer.toHexString(numb);//converting numb to string
				                        //checking if it's 3 digits, if not we will add "0" until it's 3 digits
				                        for(int w = 3; w>wor.length(); w--){
				                            //increment the disp by 1 until it completes the 3 digits by adding "0"
				                            disp += "0";
				                        }
				                        disp += wor;
				                    }
				                    
				                    String op = "" + opcode.charAt(0);//getting the 1st opcode
				                    String opcodeBinary = hexToBin(op);//converting from hexa to binary using the function hexToBin
				                    op = "" +opcode.charAt(1);//getting the 2nd opcode
				                    opcodeBinary += hexToBin(op);
				                    opcodeBinary = opcodeBinary.substring(0, opcodeBinary.length() - 2);
		                            //getting the opcode and the flag converting it from binary to hexa
				                    int opCodeAndFlagBinary = Integer.parseInt(opcodeBinary + N + I + X + B + P + E, 2);
				                    String opCodeAndFlagHexa = Integer.toString(opCodeAndFlagBinary, 16);
				                    String opCodeAndFlag = "";
	                                //checks the address contains 3 digits or not
				                    for(int w = 3; w>opCodeAndFlagHexa.length(); w--){
				                        opCodeAndFlag += "0";
				                    }
				                    opCodeAndFlag += opCodeAndFlagHexa;
				                    //save it in the arr list objCode
				                    objCode.add(opCodeAndFlag + disp);
				              }
				           */
				        
                
              //format 3
				else {
					//to check from the optab if it matches the second column as it's format 3
				    for (int k = 0 ; k <OPTAB.length; k++){
				                if (instr.get(i).equals (OPTAB[k][0])){
				                    if(OPTAB[k][1].equals("3")){
				                        opcode = OPTAB[k][2];
				         //Initializing the flag bits with the format 3 values     
				                    N = "1";
				                    I = "1";
				                    X = "0";
				                    B = "0";
				                    P = "0";
				                    E = "0";
				                  //Initializing some values
				                    String address = null;
				                    String disp = "";
				                    String ref;
				                    //if it begin with a certain symbol it will change specific flag values
				                    if (refr.get(i).startsWith("#")) {
				                        N = "0";
				                    } else if (refr.get(i).startsWith("@")) {
				                        I = "0";
				                    } else if (refr.get(i).contains(",X")){
				                        X ="1";
				                    }
				                    //to get the value of opcode from OPTAB
				                    for (int j = 0; j < OPTAB.length; j++) {
				                        if (instr.get(i).equals(OPTAB[j][0])) {
				                            opcode = OPTAB[j][2];
				                        }
				                    }
				                    if (refr.get(i).contains("=")) {
				                    	String [] values = refr.get(i).split("=|c|x|'");
				                    	ref= values[2];
				                    }
		                            //if the reference got any of these symbols then it will add the value in the reference
				                    if (refr.get(i).contains("#") || refr.get(i).contains("@")) {
				                        String[] values = refr.get(i).split("#|@");
				                        ref = values[1];
				                    }
		                            //if the reference got ",X" then it will add the value in the reference
				                    else if (refr.get(i).contains(",X")) {
				                        String[] values = refr.get(i).split(",X");
				                        ref = values[0];
				                    }
				                  //if not then it will get it from the refr arraylist
				                    else
				                        ref = refr.get(i);
				                    //to get the displacement value
				                  //Initializing some values
				                    int Disp =0;
				                    int TA=0;
				                    int PC=0;
				                    if(ref.matches("[a-zA-Z]+")){
				                        for (int j = 0; j < refr.size(); j++) {
				                            if (ref.equals(label.get(j))) {
				                                address = locctr.get(j);
				                                TA = Integer.parseInt(address,16);//converting the address to hexa
				                                PC = Integer.parseInt(locctr.get(i+1), 16);//converting the previous location counter to hexa
				                                Disp = TA-PC;//getting the displacement if it's PC
				                                break;
				                            }
				                        }
				                        //checking the conditions of the value we got from the displacement
				                        //if the value is correct then it will change the flag bit P
				                        if(Disp >= -2048 && Disp <= 2047){
				                            P = "1";
				                        }
				                        //if it's not correct then it will change the flag bit B
				                        else {
				                            String pos = "";
				                            String base = "";
				                            B = "1";
				                            //to get the position that has the instruction BASE
				                            for (int j = 0; j < instr.size(); j++) {
				                                if (instr.get(j).equals("BASE")) {
				                                    pos = refr.get(j);
				                                }
				                            }
				                            //if the label = the pos then we will get the location counter of the base
				                            for (int j = 0; j < label.size(); j++) {
				                                if (label.get(j).equals(pos)) {
				                                    base = locctr.get(j);
				                                }
				                            }
				                            int BASE = Integer.parseInt(base, 16);//converting the base to hexa
				                            Disp = TA-BASE;//calculating the displacement if it's base
				                        }
				                        String displacement = Integer.toHexString(Disp);//converting the disp to string
				                        //if the displacement length is less than 3 then we will add "0"
				                        if(displacement.length()>3){
				                            displacement = displacement.substring(displacement.length()-3, displacement.length());
				                        }
				                        //increment the disp by 1 until it completes the 3 digits by adding "0"
				                        for(int w = 3; w>displacement.length(); w--){
				                            disp += "0";
				                        }
				                        disp += displacement;
				                    }
				
				                    else {	
				                        int numb = Integer.parseInt(ref);//converting ref to hexa
				                        String wor = Integer.toHexString(numb);//converting numb to string
				                        //checking if it's 3 digits, if not we will add "0" until it's 3 digits
				                        for(int w = 3; w>wor.length(); w--){
				                            //increment the disp by 1 until it completes the 3 digits by adding "0"
				                            disp += "0";
				                        }
				                        disp += wor;
				                    }
				                    
				                    String op = "" + opcode.charAt(0);//getting the 1st opcode
				                    String opcodeBinary = hexToBin(op);//converting from hexa to binary using the function hexToBin
				                    op = "" +opcode.charAt(1);//getting the 2nd opcode
				                    opcodeBinary += hexToBin(op);
				                    opcodeBinary = opcodeBinary.substring(0, opcodeBinary.length() - 2);
		                            //getting the opcode and the flag converting it from binary to hexa
				                    int opCodeAndFlagBinary = Integer.parseInt(opcodeBinary + N + I + X + B + P + E, 2);
				                    String opCodeAndFlagHexa = Integer.toString(opCodeAndFlagBinary, 16);
				                    String opCodeAndFlag = "";
	                                //checks the address contains 3 digits or not
				                    for(int w = 3; w>opCodeAndFlagHexa.length(); w--){
				                        opCodeAndFlag += "0";
				                    }
				                    opCodeAndFlag += opCodeAndFlagHexa;
				                    //save it in the arr list objCode
				                    objCode.add(opCodeAndFlag + disp);
				              }
				           }
				        }
				    }
                }
            }
//to print out the Object Code after doing the calculation
        System.out.println("\n" + "****Obj Code****");
        System.out.println("****Pass 2****"+"\n");
        for (int i=0; i<objCode.size(); i++){
            System.out.println(objCode.get(i));
        }
//////////////////////End of pass 2 Object code//////////////////////
        
//////////////////////HTE Record//////////////////////
        System.out.println("\n"+"****HTE record****" +"\n");
//////////////////////H record//////////////////////
        System.out.println("H^"+label.get(0)+"^00"+locctr.get(1)+"^000"+length);
//////////////////////T record//////////////////////
        int lengthObjCode = 0;
        String tRecord="";
        int counter1;//counter to get the T record size
        temp1 = locctr.get(0);//start point
        int position = 0;
        int counter2 = 0;
        int counter3 = 0;
//to get the first 10 instructions
        for(counter1=1; counter1 < objCode.size()-1 ; counter1++) {
//checks if it's not RESW or RESB or BASE
            if(!instr.get(counter1).equals("RESW") && !instr.get(counter1).equals("RESB")
                    && !instr.get(counter1).equals("BASE")) {
                tRecord+= objCode.get(counter1) + " ";
                counter2 = counter1;
                counter3++;//counter that increments to count the elements
            }
//stop before RESW or RESB and get the size
            if(counter3 == 10 || instr.get(counter1 + 1).equals("RESW") ||
                    instr.get(counter1 + 1).equals("RESB")){
//it will reset the counter to begin the next T record
                if(instr.get(counter1).equals("RESW") || instr.get(counter1).equals("RESB")
                        || instr.get(counter1).equals("WORD")
                        || instr.get(counter1).equals("BASE")){
                    counter3 = 0;
                    continue;
                }
//length of this text record = "end" (counter2+1) - "start" current position
                lengthObjCode=Integer.parseInt(locctr.get(counter2 + 1), 16)-
                        Integer.parseInt(locctr.get(position), 16);

                String lengthOfProg = Integer.toHexString(lengthObjCode);
//if it less than 2 bits then add "0"
                if(lengthOfProg.length() < 2){
                    lengthOfProg = "0" + lengthOfProg;
                }
//print out T record
                System.out.println("T^00" + temp1 + "^" + lengthOfProg + "^" + tRecord);
//go to the next T record position
                position = counter1 + 1;
                temp1 = locctr.get(counter1 + 1);
                tRecord = "";
                counter3 = 0;
            }
        }
//if the counter 3 is more than 1
        if(counter3 > 1) {
//length of this text record = "end" (counter2+1) - "start" current position
            lengthObjCode=Integer.parseInt(locctr.get(counter2 + 1), 16)-
                    Integer.parseInt(locctr.get(position), 16);

            String lengthOfProg = Integer.toHexString(lengthObjCode);
//if it less than 2 bits then add "0"
            if(lengthOfProg.length() < 2){
                lengthOfProg = "0" + lengthOfProg;
            }
//print out T record
            System.out.println("T^00" + temp1 + "^" + lengthOfProg + "^" + tRecord);
        }
        
       											/*EXTRA PART*/
  /*       
//////////////////////Modification record//////////////////////
//to loop on the instruction size to check for the ones that has "+" (format 4) to do the modification record
        for(counter1=0; counter1< instr.size(); counter1++){
            if(instr.get(counter1).contains("+") && !refr.get(counter1).contains("#")) {
                tRecord = "";
                //convert from hexa to integer
                int location = Integer.parseInt(locctr.get(counter1), 16);
                location += 1;//increment the location by 1 to get all the instruction that has +
                String lengthOfProg = Integer.toHexString(location);
                //if it less than 2 bits then add "0"
                for(int counter4 = 4; counter4>lengthOfProg.length(); counter4--){
                    tRecord += "0";
                }
                //increment the tRecord by 1  until the it's 4 digits
                tRecord += lengthOfProg;
                //print out M record
                System.out.println("M^"+tRecord+"^05^+"+label.get(0));
            }
        }*/
//////////////////////E record//////////////////////
        System.out.println("E^00"+locctr.get(0));
//////////////////////End of HTE record//////////////////////
        
//////////////////////The whole SICXE project//////////////////////
        System.out.println("\n" + "*********SIC Project*********");
        System.out.println("Locctr " + "   Label " + " Instructions " + " Reference " + " Object Code");
        for (int i=0; i<label.size(); i++){
            System.out.println(locctr.get(i) + " ---> " +label.get(i) + " ---> " + instr.get(i) + " ---> "
                    + refr.get(i)+ " " + " ---> " + objCode.get(i));
        }
    }
//function to use to convert from hexadecimal to binary
    public static String hexToBin(String hex){
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }
    
    public static void initialize () {
        OPTAB[0] = new String[] {"FIX", "1", "C4"};
        OPTAB[1] = new String[] {"FLOAT", "1", "C0"};
        OPTAB[2] = new String[] {"HIO", "1", "F4"};
        OPTAB[3] = new String[] {"NORM", "1", "C8"};
        OPTAB[4] = new String[] {"SIO", "1", "F0"};
        OPTAB[5] = new String[] {"TIO", "1", "F8"};
        OPTAB[6] = new String[] {"ADDR", "2", "90"};
        OPTAB[7] = new String[] {"CLEAR", "2", "B4"};
        OPTAB[8] = new String[] {"COMPR", "2", "A0"};
        OPTAB[9] = new String[] {"DIVR", "2", "9C"};
        OPTAB[10] = new String[] {"MULR", "2", "98"};
        OPTAB[11] = new String[] {"RMO", "2", "AC"};
        OPTAB[12] = new String[] {"SHIFTL", "2", "A4"};
        OPTAB[13] = new String[] {"SHIFTR", "2", "A8"};
        OPTAB[14] = new String[] {"SUBR", "2", "94"};
        OPTAB[15] = new String[] {"SVC", "2", "B0"};
        OPTAB[16] = new String[] {"TIXR", "2", "B8"};
        OPTAB[17] = new String[] {"ADD", "3", "18"};
        OPTAB[18] = new String[] {"ADDF", "3", "58"};
        OPTAB[19] = new String[] {"AND", "3", "40"};
        OPTAB[20] = new String[] {"COMP", "3", "28"};
        OPTAB[21] = new String[] {"COMPF", "3", "88"};
        OPTAB[22] = new String[] {"DIV", "3", "24"};
        OPTAB[23] = new String[] {"DIVF", "3", "64"};
        OPTAB[24] = new String[] {"J", "3", "3C"};
        OPTAB[25] = new String[] {"JEQ", "3", "30"};
        OPTAB[26] = new String[] {"JGT", "3", "34"};
        OPTAB[27] = new String[] {"JLT", "3", "38"};
        OPTAB[28] = new String[] {"JSUB", "3", "48"};
        OPTAB[29] = new String[] {"LDA", "3", "00"};
        OPTAB[30] = new String[] {"LDB", "3", "68"};
        OPTAB[31] = new String[] {"LDCH", "3", "50"};
        OPTAB[32] = new String[] {"LDF", "3", "70"};
        OPTAB[33] = new String[] {"LDL", "3", "08"};
        OPTAB[34] = new String[] {"LDS", "3", "6C"};
        OPTAB[35] = new String[] {"LDT", "3", "74"};
        OPTAB[36] = new String[] {"LDX", "3", "04"};
        OPTAB[37] = new String[] {"LPS", "3", "D0"};
        OPTAB[38] = new String[] {"MUL", "3", "20"};
        OPTAB[39] = new String[] {"MULF", "3", "60"};
        OPTAB[40] = new String[] {"OR", "3", "44"};
        OPTAB[41] = new String[] {"RD", "3", "D8"};
        OPTAB[42] = new String[] {"RSUB", "3", "4C"};
        OPTAB[43] = new String[] {"SSK", "3", "EC"};
        OPTAB[44] = new String[] {"STA", "3", "0C"};
        OPTAB[45] = new String[] {"STB", "3", "78"};
        OPTAB[46] = new String[] {"STCH", "3", "54"};
        OPTAB[47] = new String[] {"STF", "3", "80"};
        OPTAB[48] = new String[] {"STI", "3", "D4"};
        OPTAB[49] = new String[] {"STL", "3", "14"};
        OPTAB[50] = new String[] {"STS", "3", "7C"};
        OPTAB[51] = new String[] {"STSW", "3", "E8"};
        OPTAB[52] = new String[] {"STT", "3", "84"};
        OPTAB[53] = new String[] {"STX", "3", "10"};
        OPTAB[54] = new String[] {"SUB", "3", "1C"};
        OPTAB[55] = new String[] {"SUBF", "3", "5C"};
        OPTAB[56] = new String[] {"TD", "3", "E0"};
        OPTAB[57] = new String[] {"TIX", "3", "2C"};
        OPTAB[58] = new String[] {"WD", "3", "DC"};
    }
}