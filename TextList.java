/**
 * Write a description of class TextList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TextList
{
    private WordNode _head;

    /**
     * Construct a new list(Default constructor)
     * Sets the head of the list to null.
     * Time Complexity O(1)
     * Memory Complexity O(1)
     */
    public TextList (){
        _head= null; 
    }

    /**
     * Construct a new list from a given String
     * arranges the words in the list in an Alphabetical order
     * Time Complexity O(n)
     * Memory Complexity O(n)
     * @param text the string to be turned to a list
     */
    public TextList (String text){
        _head=null;
        while(text.indexOf(" ")!= -1){
            int indexOfSpace=text.indexOf(" ");
            String word=text.substring(0,indexOfSpace);
            addToBeginning(word);
            text=text.substring(indexOfSpace+1);
        }
        if (!(text.equals("")))
            addToBeginning(text);//adds the last word if there is one left
        //sort the array after putting all the words in at N complexity
        mergeSort ();

    }

    /**
     * This method adds a new word to the list in the correct location in an Alphabetical order.
     * Time Complexity O(n)
     * Memory Complexity O(1)
     * @param word the word to be added to the list
     */

    public void addToData (String word){
        WordNode temp= new WordNode(word);//creates a new WordNode with the given word to be placed in the data
        if(_head==null)
            _head=temp;
        else{
            for(WordNode ptr= _head, prev= null ; ptr!=null;prev=ptr, ptr=ptr.getNext()){
                int compare=word.compareTo(ptr.getWord()); //uses the compareTo method and a loop to determine the place for the word
                if (compare==0){//if the same word is found, adds 1 to times in text of the word
                    ptr.addToTimesInText();
                    return; 
                }
                if (compare<0){
                    temp.setNext(ptr);
                    if (prev==null){
                        _head=temp;
                        return;    
                    }
                    prev.setNext(temp);

                }
                if (compare > 0 ){
                    temp.setNext(ptr);
                    if (prev!=null)
                        prev.setNext(temp);
                    if (prev== null)
                        _head=temp;
                }
            }
        }
    }

    /**
     * This method returns a int representation of how many words are in the text
     * Time Complexity O(n)
     * Memory Complexity O(1)
     * @return a int representation of how many words are in the text
     */
    public int howManyWords(){
        int count=0;
        for(WordNode ptr= _head ; ptr!=null; ptr=ptr.getNext())
            count +=ptr.getTimesInText();
        return count;
    }

    /**
     * This method returns a int representation of how many different words are in the text
     * Time Complexity O(n)
     * Memory Complexity O(1)
     * @return a int representation of how many different words are in the text
     */
    public int howManyDifferentWords(){
        int count=0;
        for(WordNode ptr= _head ; ptr!=null; ptr=ptr.getNext())
            count++;
        return count;
    }

    /**
     * This method returns a int representation of how many different words are in the text
     * Time Complexity O(n)
     * Memory Complexity O(1)
     * @return a int representation of how many different words are in the text
     */
    public String mostFrequentWord (){
        int max=0;
        WordNode temp= _head;
        for(WordNode ptr= _head ; ptr!=null; ptr=ptr.getNext()){
            if(ptr.getTimesInText()>max){
                max=ptr.getTimesInText();
                temp= ptr;
            }

        }
        return temp.getWord();
    }

    /**
     * This method returns a int representation of how many different words are in the text
     * Time Complexity O(n)
     * Memory Complexity O(1)
     * @param letter the char to be searched for in the list
     * @return a int representation of how many different words are in the text
     */
    public int howManyStarting(char letter){
        int count=0;
        for(WordNode ptr= _head ; ptr!=null; ptr=ptr.getNext()){
            if (ptr.getWord().charAt(0)==letter)
                count+=ptr.getTimesInText();
        }
        return count;    

    }

    public char mostFrequentStartingLetter (){
        int [] count = new int [26];
        
        
        
    }

    /**
     * This method returns a String representation of the list.
     * Time Complexity O(n)
     * Memory Complexity O(1)
     * @return a String representation of the list.
     */ 
    public String toString(){
        String s="";
        for(WordNode ptr= _head ; ptr!=null; ptr=ptr.getNext()){
            s+= ptr.getWord() + "\t" + ptr.getTimesInText() + "\n";

        }  
        return s;
    }

    private void addToBeginning(String word){//O(1) complexity
        WordNode temp=new WordNode(word);
        temp.setNext(_head);
        _head=temp;
    }

    private void mergeSort () {
        if (_head!=null)
            _head = _head.mergeSort(_head);
    }
}
