
import java.util.*;

class Sentence{
        
        Scanner scan = new Scanner(System.in);
        String english;        
        
        String engPrompt(){
                System.out.print("Eng: ");
                english = scan.nextLine();
                return english;
        }
        
        String cleanString(String english){
                String cleanStr = english.replaceAll(",","");
                cleanStr = cleanStr.replace(".","");
                cleanStr = cleanStr.replace("?","");
                cleanStr = cleanStr.replace("!","");
                cleanStr = cleanStr.replace("'","");
                char[] cleanCharArr = cleanStr.toCharArray();
                String cleanestStr = "";
                for (int x = 0; x < cleanCharArr.length; x++){
                        if (!Character.isDigit(cleanCharArr[x])){
                                cleanestStr += cleanCharArr[x];
                        }
                } 
                return cleanestStr;
        }
        String[] strToArr(String cleanStr){
                String[] strArr = cleanStr.split(" ");
                     
                return strArr;
        }

        boolean[] yesIgPay(String[] strArr){
                boolean[] yesUse = new boolean[strArr.length];
                int x = 0;
                for (String word : strArr){
                        if (word.length()<3){
                                yesUse[x] = false;
                        }else if (word.equals("and")||word.equals("the")){
                                yesUse[x] = false;
                        }else{
                                yesUse[x] = true;
                        }
                        x += 1;
                }
                return yesUse;
        }        

        String[] translate(String[] strArr , boolean[] yesUse){
                int x = 0;
                String[] translatedArr = new String[strArr.length];
                for (String word : strArr){
                        char[] wordArr = new char[word.length()];
                        for (int y = 0; y < word.length();y++){
                                wordArr[y] = word.charAt(y);
                        }
                        if (yesUse[x] == false){
                                translatedArr[x] = word;      
                        }else if (wordArr[0]=='a'||wordArr[0]=='e'||wordArr[0]=='i'||wordArr[0]=='o'||wordArr[0]=='u'){
                                translatedArr[x] = word + "way";
                        }else{
                                String startConsonants = "";
                                for (int z = 0;wordArr[z]!='a'&&wordArr[z]!='e'&&wordArr[z]!='i'&&wordArr[z]!='o'&&wordArr[z]!='u';z++){
                                        if (z >= word.length()-1){
                                                break;
                                        }
                                        startConsonants += wordArr[z];
                                }
                                String updatedWord = String.valueOf(wordArr);
                                updatedWord = updatedWord.replace(startConsonants,"");
                                translatedArr[x] =  updatedWord + startConsonants + "ay";
                        }
                        x++;
                }
                return translatedArr;
        }

        String translatedOutput(String[] translatedArr){
                String translatedSentence = "";
                for (String word : translatedArr){
                        translatedSentence = translatedSentence + word + " ";
                }
                translatedSentence = translatedSentence.strip();
                return translatedSentence;
        }

    
}
