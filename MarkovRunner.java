
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File;
import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        
        String source = "data/confucius.txt";
        File f = new File(source);
        FileResource fr = new FileResource(f);
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne(); 
        runModel(markovWord, st, 120, 139); 
    } 

    public void runMarkovTwo() { 
        
        String source = "data/confucius.txt";
        File f = new File(source);
        FileResource fr = new FileResource(f);
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovWord = new MarkovWordTwo(); 
        // runModel(IMarkovModel markov, String text, int size, int seed)
        runModel(markovWord, st, 120, 832); 
    } 
    
    public void indexOfTheMinister() {
        String source = "data/confucius.txt";
        File f = new File(source);
        FileResource fr = new FileResource(f);
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        String[] myText = st.split("\\s+");
        System.out.println("Confucius stored as string array of length: " + myText.length);
        
        MarkovWordTwo markovWord = new MarkovWordTwo(); 
        
        // private int indexOf(String[] words, String target1, String target2, int start)
        int i = markovWord.indexOf(myText, "the","minister", 0);
        
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
