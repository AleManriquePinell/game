
class Translator {
               
                

        public static void main(String args[]){
                Sentence sentence = new Sentence();
                String english = sentence.engPrompt();
                String cleanStr = sentence.cleanString(english);
                String[] strArr = sentence.strToArr(cleanStr);
                boolean[] yesUse = sentence.yesIgPay(strArr);
                String[] translatedArr = sentence.translate(strArr , yesUse);
                String translatedSentence = sentence.translatedOutput(translatedArr);
                System.out.println("Pig: " + translatedSentence);               
        }
        

}
