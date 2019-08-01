
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length - 1) {
            int keyAt = indexOf(myText, key, pos);
            if (keyAt == -1 || keyAt >= myText.length - 1 ) {
                break;  // either the key was not found or it was found as the last element in myText and thus has no follower
            }
            String follower = myText[keyAt + 1];
            // System.out.println("Key: " + key + "  found at: " + keyAt + ".  Follow character is: " + follower);
            follows.add(follower);
            pos = keyAt + 1;
        }
        
        return follows;
    }

    private int indexOf(String[] words, String target, int start) {
        for (int i = start; i < words.length; i++) {
            if (words[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    public void testIndexOf () {
        String s = "this is just a test yes this is a simple test";
        String[] text = s.split("\\s+");
        System.out.println(text);
        System.out.println("The training text was " + text.length + " words long.");
        System.out.println("Searching for 'this'   from 0: Expected  0, Result " + indexOf(text, "this", 0));
        System.out.println("Searching for 'this'   from 3: Expected  6, Result " + indexOf(text, "this", 3));
        System.out.println("Searching for 'frog'   from 5: Expected -1, Result " + indexOf(text, "frog", 5));
        System.out.println("Searching for 'simple' from 2: Expected  9, Result " + indexOf(text, "simple", 2));
        System.out.println("Searching for 'test'   from 5: Expected 10, Result " + indexOf(text, "test", 5));
    }
}
