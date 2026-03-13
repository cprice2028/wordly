import java.io.*;
import java.util.*;

/***********************************************************************************************
*A Wordguess it a basic tile and maintains information about random, normal, and dictionary 5-letter-words, as well as it's answer.
*A Wordguess knows how to automatically generate 5-letter words, find .txt files for their lists, and return all their lists.
*@author Raiyan, email: 1902549@fcpsschools.edu
*@version 5.11.2025
***********************************************************************************************/

public class Wordguess {
    private final String[] randList = new String[1414];
    private final String[] wordList = new String[12920];
    private final String[] wordleList = new String[1413];
    public final String answer;

    // Load random list
    {
        try (BufferedReader br = new BufferedReader(new FileReader("random.txt"))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < randList.length) {
                randList[index++] = line.trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load full word list, this list is used to check if the word is valid
    {
        try (BufferedReader br = new BufferedReader(new FileReader("Words.txt"))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < wordList.length) {
                wordList[index++] = line.trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load Wordle list, this list is used to choose the answer word
    {
        try (BufferedReader br = new BufferedReader(new FileReader("wordle.txt"))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < wordleList.length) {
                wordleList[index++] = line.trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /***********************************************************************************************
   *constructs a Wordguess and sets its mode to 1 or 2, corresponding to normal or random, respectively.
   *@param mode      if it is random or not
   ***********************************************************************************************/
    public Wordguess(int mode) {
        Random rand = new Random();
        if (mode == 1) {
            // Use Wordle list
            answer = wordleList[rand.nextInt(wordleList.length)];
        } else {
            // Use random list
            answer = randList[rand.nextInt(randList.length)];
        }
    }

   /***********************************************************************************************
   *Returns the answer to the game
   *@return answer
   ***********************************************************************************************/
    public String getAnswer() {
        return answer;
    }

   /***********************************************************************************************
   *Returns the possible words used in the game
   *@return list of possible words
   ***********************************************************************************************/
    public String[] getList() {
        return wordleList;
    }

   /***********************************************************************************************
   *Returns all possible words you can type in the game
   *@return all possible words
   ***********************************************************************************************/
    public String[] getbigList() {
        return wordList;
    }
}

