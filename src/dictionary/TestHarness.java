package dictionary;
import java.util.List;
import java.util.Scanner;

public class TestHarness{

    public static void main (String[] args){
        System.out.println("Enter table size word and definition");
        Scanner sc = new Scanner(System.in);
        sc = new Scanner(sc.nextLine()).useDelimiter(" ");
        int size = 0;
        String word = "";
        String description = "";
        while(sc.hasNext()){
         size = sc.nextInt();
         word = sc.next();
         description = sc.next();
        }
        System.out.println(size);
        SCHashTable table = new SCHashTable(size);
        //WordType WT = new WordType("n");
        Definition def = new Definition(WordType.NOUN,description);
        //Definition def = new Definition(WordType.NOUN,description);
        table.insert(word,def);
        table.insert("owrd",def);
        table.insert("rdow",def);
        table.insert("see",def);
        table.insert("ms",def);
        table.insert("ms",def);
        table.insert("hello",def);
        table.insert("good",def);
        System.out.println(table.containsWord("ms"));
        System.out.println(table.containsWord("hello"));
        System.out.println(table.getWords());    
        System.out.println(table.getDefinitions("rdow"));
    }
}
