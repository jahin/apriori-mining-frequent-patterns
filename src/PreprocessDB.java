import java.io.*;
import java.util.*;

/**
 * Created by Rakibul on 11/13/2014.
 */
public class PreprocessDB {
/* todo:
    1. First read the transaction Database file and store them in a set to get the exact elements
    2. now using the elements crate an Arraylist
    3. then inverted index Map<String, value>
    4. create a new text file with the new integer list
*/
    Set<String> tokenSet ;
    ArrayList<String> tokenList;
    Map<String, Integer> tokenMap;
    Map<Integer, String> reverseTokenMap;
    public static final String DATABASE_PATH = "./transactionDB.txt";
    public static final String FORMATTED_DB_FILE="formattedDB.txt";

    public void transformDatabaseEntry() throws Exception {
        /*File file = new File("ProcessedEntry.txt");
        if(!file.exists()){
            file.createNewFile();
        }*/
        //FileWriter fw = new FileWriter(file.getAbsoluteFile());
        //BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader databaseReader = getDatabaseReader();
        PrintWriter writer = new PrintWriter(FORMATTED_DB_FILE, "UTF-8");
        String currentTransaction;
        while ( (currentTransaction = databaseReader.readLine())!= null){
            String[] tokens = currentTransaction.split(" ");
            Arrays.sort(tokens);
            for(String token : tokens){
                writer.print(tokenMap.get(token) + " ");
            }
            writer.println("");
        }
        writer.close();
        databaseReader.close();
    }

    public void createMapofTokens(ArrayList<String> tokenList){
        tokenMap = new HashMap<>();
        reverseTokenMap = new HashMap<>();
        for(int index = 0; index < tokenList.size(); index++){
            String item = tokenList.get(index);
            tokenMap.put(item, index + 1);
            reverseTokenMap.put(index+1, item);
        }
    }
    public Map<Integer, String> getReverseTokenMap(){
        return this.reverseTokenMap;
    }
    /*
    *
    * */

    public BufferedReader getDatabaseReader() throws Exception{
        BufferedReader databaseReader = new BufferedReader(new FileReader(DATABASE_PATH));
        return databaseReader;
    }

     public void readDatabase() throws Exception {
        try {

            String currentTransaction;
            BufferedReader databaseReader = getDatabaseReader();
            tokenSet = new HashSet<String>();
            tokenList = new ArrayList<String>();
            while ( (currentTransaction = databaseReader.readLine())!= null){
                //System.out.println(currentTransaction);
                String[] tokens = currentTransaction.split(" ");
                for(String token : tokens){
                    tokenSet.add(token);
                }
            }
            tokenList.addAll(tokenSet);
            Collections.sort(tokenList);
            createMapofTokens(tokenList);
            databaseReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /*public static void main(String[] args) throws Exception {
        PreprocessDB datasetPreprocessor = new PreprocessDB();
        datasetPreprocessor.readDatabase();
        datasetPreprocessor.transformDatabaseEntry();
    }*/
}
