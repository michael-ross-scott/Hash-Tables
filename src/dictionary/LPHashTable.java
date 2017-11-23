package dictionary;
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using linear probing.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
public class LPHashTable extends AbstractHashTable {

    /**
     * Create an LPHashTable with DEFAULT_SIZE table.
     */ 
    public LPHashTable() { super(); }
    
    /**
     * Create an LPHashTable with the given default size table.
     */
    public LPHashTable(int size) { super(size); }    
    

  /**
   * Creates a key for given word and loops though the table.
   * If the key lands on an empty slot it returns the index of the slot.
   * If the key contents is equal to the word being inserted it returns the index of that slot.
   * Otherwise returns -1 as the table is full on an insertion
   */    
	protected int findIndex(String word) {
      int counter = hashFunction(word);
      int size = table.length;
      int index = 0;
      //resetProbeCount();
      
      while(index<size){
      	 incProbeCount();
         if(table[counter]==null){
            return counter;
         }
         
         String wordEntry = table[counter].getWord();
         
         //word is found in the table or a null entry is reached (insert)
         if(word.equals(wordEntry)){
            return counter;
         }
         
         counter++;
         index++;
         
         //the iterator gets to the end of the table and has to loop back to the begining.
         if(counter==size){
            counter = 0;
         }
      }
      return -1;
   }
}
