
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        // myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
        // int val = seed;
        System.out.println("myRandom seed : " + seed);
        /*
        System.out.println("Word at that index: " + myText[val]);
        
        val = myRandom.nextInt(myText.length);
        System.out.println("myRandom value: " + val);   
        System.out.println("Word at that index: " + myText[val]);
        
        val = myRandom.nextInt(myText.length);
        System.out.println("myRandom value: " + val);   
        System.out.println("Word at that index: " + myText[val]);
        
        val = myRandom.nextInt(myText.length);
        System.out.println("myRandom value: " + val);   
        System.out.println("Word at that index: " + myText[val]);
        */
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords) {  // Modified from markovWordOne to consider the previous TWO words.
        StringBuilder sb = new StringBuilder();
        
        int index = myRandom.nextInt(myText.length);  // random word to start with 
        System.out.println("index set using myRandom.nextInt() to : " + index);
        // String word1 = myText[index];
        // String word2 = myText[index+1];
        String word1 = "daring";
        String word2 = "becomes";
        
        sb.append(word1);
        sb.append(" ");

        sb.append(word2);
        sb.append(" ");
        
        System.out.println("*** Random word1 from myText: " + word1);
        System.out.println("*** Random word2 from myText: " + word2);
        System.out.println("*** Random before word1 from myText: " +  myText[index -1]);
        
        ArrayList<String> testFollows = getFollows("the", "minister");
        System.out.println(Arrays.toString(testFollows.toArray()));
        
        testFollows = getFollows("minister", "know");
        System.out.println(Arrays.toString(testFollows.toArray()));
        
        testFollows = getFollows("know", "me?");
        System.out.println(Arrays.toString(testFollows.toArray()));
        
        System.out.println("*** Word at 549 from myText: " +  myText[549]);
        
        for(int k=0; k < numWords; k++){
            ArrayList<String> follows = getFollows(word1, word2);
            if (follows.size() == 0) {
                k--;
                System.out.println("*** getFollows generated list of length zero ***");
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            word1 = word2;
            word2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length - 2) {
            int keyAt = indexOf(myText, key1, key2, pos);
            if (keyAt == -1 || keyAt > myText.length - 2) {
                break;  
            }    
            String follower = myText[keyAt + 2];  // Changed from keyAt+1 in original indexOf method.
            // System.out.println("Key: " + key + "  found at: " + keyAt + ".  Follow character is: " + follower);
            follows.add(follower);
            pos = keyAt + 1;  // the pos to search from is only increased by one in case k+1 = k+2 = k+3
        }
        return follows;
    }

    public int indexOf(String[] words, String target1, String target2, int start) {
        for (int i = start; i < words.length - 1; i++) {  // only search where i < words.length - 2 to leave room for target2 not a follows word.  
            if (words[i].equals(target1)) {               // if the first word is a match
                if (words[i + 1].equals(target2)) {       // if the next word is also a match
                    return i;   // return the index where the first match occured. The actual 'follows' word is at i+2
                }
            }
        }
        return -1;
    }
    
    
    
    public void testIndexOf () {
        String s = "this is just a test yes this is a simple test";
        String[] text = s.split("\\s+");
        System.out.println(text);
        System.out.println("The training text was " + text.length + " words long.");
        System.out.println("Searching for 'this' 'is'       from 0: Expected  0, Result " + indexOf(text, "this","is", 0));
        System.out.println("Searching for 'this' 'just'     from 0: Expected -1, Result " + indexOf(text, "this", "just", 0));
        System.out.println("Searching for 'this' 'is'       from 3: Expected  6, Result " + indexOf(text, "this", "is", 3));
        System.out.println("Searching for 'simple' 'test'   from 2: Expected  9, Result " + indexOf(text, "simple", "test", 2));
        System.out.println("Searching for 'test' 'yes'      from 5: Expected -1, Result " + indexOf(text, "test", "yes", 5));
    }
}
