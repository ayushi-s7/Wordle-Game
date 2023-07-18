import java.util.*;

import javax.lang.model.util.ElementScanner14;

import java.nio.file.Files;
import java.nio.file.Paths;


class parentwordle{
    Scanner sc=new Scanner(System.in);
    String yellow = "\u001B[33m";
    String green = "\u001B[32m";
    String reset = "\u001B[0m";
    String black="\033[0;30m";
    String key="QWERTYUIOPASDFGHJKLZCVBNM";
    
    String rword;
    String[] words;
    String List() {
        try {
            words = new String(Files.readAllBytes(Paths.get("words.txt"))).split(" ");
            int i = (int) (Math.random() * words.length);
            String word = "NANNY";
            return word;
        } 
        catch (Exception e) {
            System.out.println("Error 404: File \"words.txt\" not found");
            System.exit(404);
            return null;
        }
    }  
}

class wordle_dipti extends parentwordle {
    String uword;
    int att=1;
    boolean won=false;
    String[][] gb=new String[6][7];
    void run(){
        rword=List();
        rword=rword.toUpperCase();
        for(int i=0;i<=gb.length-1;i++){
            gb[i][0]="|";
            gb[i][6]="|";
            for(int j=1;j<=gb[0].length-2;j++){
                gb[i][j]="-";
            }
        }
        table();
        input();
    }
    int flagk[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    
    
    public static void main(String args[]) {
        System.out.println("Welcome to Wordle! ");
        //to put credits and instructions
        wordle a=new wordle();
        a.run();
    }

    void table(){
        for(int i=0;i<gb.length;i++){
            for(int j=0;j<gb[0].length;j++)
            System.out.print(gb[i][j]+ " ");
            System.out.println();
        }
    }

    void input(){
        String choice;
        boolean flag=false;
        att=1;
        won=false;
        while(!won && att<=6){
            System.out.println("Please enter a word:");
            while(!flag){
                uword=sc.nextLine();
                if(uword.length()==5){
                    for(String w: words){
                        if(w.equalsIgnoreCase(uword))
                        flag=true;
                    }
                    if(flag==false)
                    System.out.println("Enter a valid word.");
                }
                else
                System.out.println("Enter a word of length 5");
            }
            enter();
            att++;
            flag=false;
        }
        if(won==false){
        System.out.println("Oops! Better luck next time.\n");
        System.out.println("Correct word was: "+rword);
        }
        else
        System.out.println("Congratulations! You guessed the correct word.\n");
        System.out.print("Would you like to play again? Y/N : ");
        do{
            choice=sc.nextLine();
            System.out.println();
            if(choice.equalsIgnoreCase("y"))
            run();
            else if(choice.equalsIgnoreCase("n"))
            flag=false;
            else
            System.out.println("Please enter Y/N : ");
        }while(flag);
    }

    void enter(){
        int i,j;
        
        String letter="";
        int flag[]=new int[5];
        int flagy[]=new int[5];
        
        for(i=0;i<5;i++){
            flag[i]=0;
            flagy[i]=0;
        
        }
        
//green=2
//yellow=1
    
        uword=uword.toUpperCase();
        for(i=0;i<5;i++){
            letter=String.valueOf(uword.charAt(i));
           int a = key.indexOf(uword.charAt(i));
           System.out.println(a);
            if(uword.charAt(i)==rword.charAt(i)){
                flag[i]=1;
                flagk[a]=2;
                letter=green+letter+reset;
                //System.out.println(out);
            }
            keyboard(letter);

            gb[att-1][i+1]=letter;
            
        }
        for(i=0;i<5;i++){
            while(flag[i]!=0 && i<4)
            i++;
            letter=String.valueOf(uword.charAt(i));
            for(j=0;j<5;j++){
                while(flag[j]!=0 && j<4)
                j++;
                if(uword.charAt(i)==rword.charAt(j) && (i!=4 || j!=4) && (flag[i]!=1 && flag[j]!=1) && flagy[j]!=1){
                    int a = key.indexOf(uword.charAt(i));
                    letter=yellow+letter+reset;
                    flagy[j]=1;
                    flagk[a]=1;
                    //String out1=keyboard(letter);
                    gb[att-1][i+1]=letter;
                    break;
                }
            }
            }
                
        table();

        if(uword.equals(rword))
        won=true;
        
    }
    void keyboard(String letter){
        key=key+black+reset;
        System.out.println(flagk[24]);
        for (int i = 0; i < 26; i++) {
            System.out.println(flagk[i]);

            if(flagk[i]==2){
                
                key=key.replace(String.valueOf(key.charAt(i)), letter);
              System.out.println(key);
             // break;
            }
            
        }
       
        
//return key;
    }
}