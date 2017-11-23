package dictionary;
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using quadratic probing.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
public class QPHashTable extends AbstractHashTable{

  /**
   * Create an QPHashTable with DEFAULT_SIZE table.
   */ 
   public QPHashTable(){ super(); }
   
  /**
   * Create an QPHashTable with the given default size table.
   */ 
   public QPHashTable(int size){ super(size); }
   
  /**
   * Creates a key for given word and loops though the table.
   * If the key lands on an empty slot it returns the index of the slot.
   * If the key contents is equal to the word being inserted it returns the index of that slot.
   * Otherwise returns -1 as the table is full on an insertion
   */
   protected int findIndex(String word){
      int counter = hashFunction(word);
      int quadCount = 0;
      int size = table.length;
      int index = 0;
      //System.out.println(size);
      while(index<size){
      	 //System.out.println(getProbeCount());
      	 incProbeCount();
         if(table[counter]==null){
            return counter;
         }

         String wordEntry = table[counter].getWord();
         
         //word is found in the table (insert)
         
         if(word.equals(wordEntry)){
            return counter;
         }
         
         counter+=((quadCount+1)*(quadCount+1)-(quadCount*quadCount));
         counter=counter%size;
         quadCount++;
         index++;
      }
      //for(int i = 0; i<size ; i++){
      	//incProbeCount();
      //}
      return -1;
   }
}
