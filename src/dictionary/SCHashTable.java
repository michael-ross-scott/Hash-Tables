package dictionary;
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using sequential chaining.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
public class SCHashTable extends AbstractHashTable{
   protected ChainedEntry[] table;
   
  /**
   * Create an SCHashTable with DEFAULT_SIZE table.
   */ 
   public SCHashTable(){ super(); }
   
  /**
   * Create an SCHashTable with the given default size table.
   */ 
   public SCHashTable(int size){
      this.table = new ChainedEntry[size];
      super.table = this.table;
      super.entries = 0;
      resetProbeCount();
   }
   
   
   public boolean containsWord(String word){
      int index = findIndex(word);
      
      if(table[index]==null){
      	return false;
      }
      
      ChainedEntry prevWord = table[index];
      String next = table[index].getWord();
      while(prevWord!=null){
       
       incProbeCount();
     	 if(prevWord.getWord().equals(word)){
     		 return true;
     	 }
   
     	 prevWord = prevWord.getNext();
     	 
      }   
      return false;
   }
   
   public List<Definition> getDefinitions(String word){
   		incProbeCount();
      if(containsWord(word)==true){
         int index = findIndex(word);
         ChainedEntry next = table[index];

         while(!(next.getWord().equals(word))){
       
         	incProbeCount();
         	next =  next.getNext();
            
         }
         incProbeCount();
         return next.getDefinitions();
      }      
      return null;
   }
   
   public void insert(String word, Definition definition) {

      int index = findIndex(word);
      
      if(table[index]==null){
         table[index] = new ChainedEntry(word);
         table[index].addDefinition(definition);
         entries++;
         return;
      }
      
      if(containsWord(word)==true){
         ChainedEntry prevWord = table[index];
         while(!(prevWord.getWord().equals(word))){
         	prevWord = prevWord.getNext();
         }
         prevWord.addDefinition(definition);
         return;
      }
      
      ChainedEntry prevWord = table[index];
      table[index] = new ChainedEntry(word,prevWord);
      table[index].addDefinition(definition);
      entries++;
      
      incProbeCount();  
   }
   
   protected int findIndex(String word){
      int index = hashFunction(word);
      incProbeCount();
      return index;
   }
}
