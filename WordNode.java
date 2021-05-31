public class WordNode
{
    private String _word;
    private WordNode _next;
    private int _timesInText;    

    /**
     * Construct a new WordNode with a given word
     * Sets the next word to null;
     * Time Complexity O(1)
     * Memory Complexity O(1)
     * @param word the word to be in the value of the new WordNode
     */
    public WordNode(String word)
    {
        _word = word;
        _next = null;
        _timesInText=1;
    }

    /**
     * Construct a new WordNode with a given word
     * Sets the next word to null;
     * Time Complexity O(1)
     * Memory Complexity O(1)
     * @param word the word to be in the value of the new WordNode
     */
    public WordNode(String word, WordNode next)
    {
        _word = word;
        _next = next;
        _timesInText=1;
    }

    /**
     * This method returns a String representation of the word value in WordNode
     * Time Complexity O(1)
     * Memory Complexity O(1)
     * @return String representation of the word value in WordNode
     */
    public String getWord() {
        return _word;
    }

    /**
     * This method Sets a String to the word value in WordNode
     * Time Complexity O(1)
     * Memory Complexity O(1)
     * @param word the string value to be places in the WordNode
     */
    public void setWord(String word) {
        _word = word;
    }

    /**
     * This method returns the next WordNode to the WordNode that the method was applied on
     * @return next WordNode
     */
    public WordNode getNext( ) {
        return _next;
    }

    /**
     * This method sets the next WordNode to the WordNode that the method was applied on
     * @param node the next WordNode
     */
    public void setNext(WordNode node) {
        _next = node;
    }

    /**
     * This method adds to the timesInText value of WordNode
     * will be used if the word being added is already on the list
     */
    public void addToTimesInText(){
        _timesInText++;   
    }

    /**
     * This method returns a integer representing the amount of time a word is in the text
     * @return integer representing the amount of time a word is in the text
     */
    public int getTimesInText(){
        return _timesInText;    
    }

    /**
     * This method is part of the mergeSort method 
     * which sorts the array in time complexity O(nlogn)
     */
    private WordNode merge(WordNode list1, WordNode list2) {
        if (list1 == null) 
            return list2;
        if (list2 == null) 
            return list1;
        if (list1.getWord().compareTo(list2.getWord()) == 0) {//same word
            list1._timesInText = list1._timesInText+list2._timesInText;//adds to the count of the word the count of the 2 words combined
            list1.setNext(merge (list1.getNext(), list2.getNext()));//next recursive call
            return list1;
        } // end if
        if (list1.getWord().compareTo(list2.getWord()) < 0) {//list 1 smaller
            list1.setNext(merge (list1.getNext(), list2));//next recursive call
            return list1;
        } // end if
        else {
            list2.setNext(merge (list1, list2.getNext()));//next recursive call
            return list2;
        } // end else
    }

    /**
     * This method is part of the mergeSort method 
     * which sorts the array in time complexity O(nlogn)
     */
    private WordNode split(WordNode node) 
    {
        if (node == null || node.getNext() == null) 
            return null;
        WordNode list2 = node.getNext();
        node.setNext(list2.getNext());
        list2.setNext(split(list2.getNext()));
        return list2;
    }

    /**
     * This method is part of the mergeSort method 
     * which sorts the array in time complexity O(nlogn)
     */
    protected WordNode mergeSort (WordNode node) 
    {
        if (node == null || node.getNext() == null)
            return node; // checks for empty or single list
        WordNode list2 = split (node);
        node = mergeSort (node);
        list2 = mergeSort (list2);
        return merge (node, list2);
    } // end merge_sort

}
