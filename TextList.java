
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
        while(text.indexOf(" ")!= -1){//if a space exsits
            int indexOfSpace=text.indexOf(" ");//finds the next space that seperates between words
            String word=text.substring(0,indexOfSpace);//cuts the string from start until the space
            addToBeginning(word);//adds the word to beginning in O(1) complexity
            text=text.substring(indexOfSpace+1);//changes the string text to start from the spaceindex plus 1 until the end of the string
        }
        if (!(text.equals(""))){// if the string is empty
            addToBeginning(text);//adds the last word if there is one left
            //sort the array in Nlogn complexity after putting all the words in at N complexity
            mergeSort ();}

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
            _head=temp;//if the list is empty adds to the beginning
        else{
            for(WordNode ptr= _head, prev= null ; ptr!=null;prev=ptr, ptr=ptr.getNext()){
                int compare=word.compareTo(ptr.getWord()); //uses the compareTo method and a loop to determine the place for the word
                if (compare==0){//if the same word is found, adds 1 to times in text of the word
                    ptr.addToTimesInText();//adds to times in text variable of the node
                    return; 
                }
                if (compare<0){//if the string is smaller then the given string
                    temp.setNext(ptr);
                    if (prev==null){//if the place to enter the new string is the first in the list
                        _head=temp;
                        return;    
                    }
                    prev.setNext(temp);
                }
                if (compare > 0 ){//if the string is bigger then the given string
                    temp.setNext(ptr);
                    if (prev!=null)
                        prev.setNext(temp);
                    if (prev== null)//if the place to enter the new string is the first in the list
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
            count +=ptr.getTimesInText();//gets the amount of time the word is in the text by using the timesInText instance variable
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
            count++;//counts word that appears more then once only 1 time
        return count;
    }

    /**
     * This method returns a int representation of how many different words are in the text
     * Time Complexity O(n)
     * Memory Complexity O(1)
     * @return a int representation of how many different words are in the text
     */
    public String mostFrequentWord (){
        if (_head==null || _head.getWord().length()==0)
            return "";
        int max=0;
        WordNode temp= _head;//will run with this temp WordNode on the list in the for loop next
        for(WordNode ptr= _head ; ptr!=null; ptr=ptr.getNext()){
            if(ptr.getTimesInText()>max){
                max=ptr.getTimesInText();//sets max to the new max
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
                count+=ptr.getTimesInText();//gets the amount of time the word is in the text by using the timesInText instance variable
        }
        return count;    

    }

    /**
     * This Method finds the most frequent starting letter in the list by using recursion.
     * @return char The most frequent starting letter in the list.
     */
    public char mostFrequentStartingLetter (){
        if (_head==null)
            return ' ';
        int [] count = new int [26];//a new int array in the size of a-z (26).
        return mostFrequentStartingLetter( count, _head);//calls the recursive overloading method

    }

    private char mostFrequentStartingLetter( int [] count, WordNode node){
        if (node!=null){
            char firstChar=node.getWord().charAt(0);//char at the beginning of the word
            count[firstChar-97] += node.getTimesInText();//adds the amount of time word is in the text to the location representing the char in the array
            mostFrequentStartingLetter(count, node.getNext());//recursive call
        }

        return maxInArray(count,0,0);//calls another recursive method to return the Maximum char in the array (most frequent)
    }

    private char maxInArray(int [] count, int i, int maxIndex){
        if (i==count.length)//if the method reached the end of the array
            return (char)(maxIndex+97);//returns the char that is most frequent
        if (count[i] > count[maxIndex]){
            maxIndex=i;//variable contains the coordinate of the current most frequent letter
        }
        return maxInArray(count,i+1,maxIndex); //recursive call

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

    /**
     * This method adds to the beginning of the list
     * Time Complexity O(1)
     * Memory Complexity O(1) 
     */
    private void addToBeginning(String word){//O(1) complexity
        WordNode temp=new WordNode(word);
        temp.setNext(_head);
        _head=temp;
    }

    /**
     * This method Sorts the array
     * Time Complexity O(nlogn)
     * Memory Complexity O(1) 
     */
    private void mergeSort () {
        if (_head!=null)
            _head = _head.mergeSort(_head);//calls the mergeSort method from WordNode Class
    }
}