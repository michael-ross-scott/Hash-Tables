package dictionary;
import java.util.List;
/**
 * Abstract implementation of dictionary using hash table.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
public abstract class AbstractHashTable  extends Monitorable implements Dictionary {
    public final static int DEFAULT_SIZE = 50;
 
    protected Entry[] table;
    protected int entries;
 
    /**
     * Create a table with DEFAULT_SIZE. (For use by sub classes.)
     */
    protected AbstractHashTable() { this(DEFAULT_SIZE); resetProbeCount(); }
    
    /**
     * Create a table with the given default size. (For use by sub classes.) 
     */
    protected AbstractHashTable(final int size) { 
        this.table = new Entry[size];
        this.entries = 0;
        resetProbeCount();
    }
    
    /**
     * Generate a hash code for the given key using algorithm in Weiss. (For use by sub classes.)
     */
    protected int hashFunction(String key) {
        int hashValue = 0;
        int size = table.length;
        int counter = 0;
        
        while(counter != key.length()){
         char character = key.charAt(counter);
         hashValue = hashValue * 37 + character;
         counter++;
        }
        
        hashValue = hashValue % size;
        
        if(0>hashValue){
         hashValue+=size;
        }
        
        return hashValue;
    }
       
    public boolean containsWord(String word) {
    	 
       int index = findIndex(word);
       
       if(index==-1){
         return false;
       }
       
       if(table[index]==null){
         return false;
       }
       
       Entry wordAtEntry = table[index];
       
       if(wordAtEntry.isEntryFor(word)){
         return true;
       }
       return false;
    }
    
    public List<Definition> getDefinitions(String word) {
      if(containsWord(word)==true){
         int index = findIndex(word);
         return table[index].getDefinitions();
      }
      return null;
    }
    
    public void insert(String word, Definition definition) {
                 
        int index = findIndex(word);
        
        if(index==-1){
        	return;
        }else if(table[index]==null){
         table[index] =  new Entry(word);
         table[index].addDefinition(definition);
         entries++;
        } else if(table[index].getWord().equals(word)){
					table[index].addDefinition(definition);
        }
    }
    

    public boolean isEmpty() { return entries == 0; }
    
    public void empty() { this.table = new Entry[this.table.length]; this.entries=0; }
    
    public int size() { return this.entries; }
    
    /* Hash Table Functions */
    
    public double loadFactor() { return entries/(double)table.length; }

    /**
     * Method called by <code>insert()</code> when the table needs enlarging.
     * <p>
     * Sub classes should override as required.
     */
    protected void rebuild() {
          throw new IllegalStateException("Hashtable:insert(): table is full.");
    }
    
    
    /**
     * Find the index for entry: if entry is in the table, then returns its position; 
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     * 
     */
    protected abstract int findIndex(String word);
        
        
    
    /**
     * Prints contents of table to screen. (Method provided to facilitate testing and debugging.) 
     */
    public void dump() {
        Entry[] table = this.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
        System.out.printf("\n#Entries: %d.", this.entries);
    }
    
    /**
     * Obtain a list of the entries in the dictionary. (Method to facilitate testing and debugging.) 
     */
    public java.util.ArrayList<Entry> getWords() {
        java.util.ArrayList<Entry> entries = new java.util.ArrayList<Entry>();
        for (int i=0; i<this.table.length; i++) {
            if (this.table[i]!=null) {
                entries.add(table[i]);
            }
        }
        return entries;
    }
        
}
