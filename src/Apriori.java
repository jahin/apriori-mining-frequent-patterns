import java.io.*;
import java.util.*;

public class Apriori {
    public static final String TEST_INPUT_FILE = "./test-input.txt";
    public List<TrieNode> insertItemsToHead(TrieNode headNode, HashMap<Integer, TreeSet<Integer>> itemLevelOne, int minSupport){
        Set<Integer> itemsIdSet = (Set<Integer>) itemLevelOne.keySet();
        List<TrieNode> candidate = new ArrayList<>();
        for(Integer currentId : itemsIdSet){
            TreeSet<Integer> foundInTransactions = itemLevelOne.get(currentId);
            if(foundInTransactions.size() >= minSupport){
                TrieNode node = new TrieNode(currentId);
                node.currentLevel = 1;
                node.foundInDocuments = foundInTransactions;
                node.supportCount = node.foundInDocuments.size();
                node.directParentNode = headNode;
                node.parentSet.add(node.currentId);
                headNode.childListOfNodes.add(node);
                candidate.add(node);
            }
        }
        return candidate;
    }
    public List<TrieNode> candidateGenerate(TrieNode headNode, List<TrieNode> candidate, int minSupport){
        List<TrieNode> candidateNewLevel = new ArrayList<>();
        int sizeOfCandidateList = candidate.size();
        for(int i = 0; (i + 1)< sizeOfCandidateList; i++){
            TrieNode currentNode = candidate.get(i);
            List<TrieNode> siblingList = currentNode.directParentNode.childListOfNodes;
            for(int j = 0; j< siblingList.size(); j++){
                TrieNode siblingNode = siblingList.get(j);
                if(currentNode.currentId < siblingNode.currentId){
                    TrieNode node = new TrieNode(siblingNode.currentId);
                    node.currentLevel = currentNode.currentLevel + 1;
                    node.parentSet.addAll(currentNode.parentSet);
                    node.parentSet.add(siblingNode.currentId);
                    node.foundInDocuments = PrintAllSizeKSubset.intersection(currentNode.foundInDocuments, siblingNode.foundInDocuments);
                    node.supportCount = node.foundInDocuments.size();
                    //System.out.println("found in documents: "+node.supportCount);
                    node.directParentNode = currentNode;
                    if(node.supportCount< minSupport){
                        continue;
                    }
                    if(node.parentSet.toArray().length >3){
                        List<Integer> arrayList = new ArrayList<>(node.parentSet);
                        boolean[] used = new boolean[node.parentSet.toArray().length];
                        PrintAllSizeKSubset.allKSubset.clear();
                        PrintAllSizeKSubset.PrintSubsets(arrayList,used,0,0,used.length - 1);
                        HashSet<HashSet<Integer>> allKSubset = PrintAllSizeKSubset.allKSubset;

                        int matchCount = 0;
                        boolean matched = false;
                        for(HashSet<Integer> set: allKSubset){
                            for(int k = 0; k< candidate.size(); k++){
                                if (set.equals(candidate.get(k).parentSet)){
                                    matchCount++;
                                    matched = true;
                                }
                                /*if(!matched) break;
                                matched = false;*/
                            }
                        }
                        if(matchCount == arrayList.size())//matched
                            candidateNewLevel.add(node);
                        matchCount = 0;
                    }
                    else {
                        candidateNewLevel.add(node);
                    }

                }
            }
        }
        return candidateNewLevel;
    }

    public static void main(String[] args) throws Exception{
        /*preprocess the transaction database generated an integer for each transaction item.
        * then we have created a new file names formattedDB.txt to store the integer representation for the transactions
        * with items.
        * */
        long startTime = System.currentTimeMillis();
        PreprocessDB datasetPreprocessor = new PreprocessDB();
        datasetPreprocessor.readDatabase();
        datasetPreprocessor.transformDatabaseEntry();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(PreprocessDB.FORMATTED_DB_FILE));
        Apriori apriori = new Apriori();
        TrieNode headNode = new TrieNode(-1);
        headNode.currentLevel = 0;
        String currentTransaction;
        String currentInput;
        int transactionCount = 1;
        int minSupport = -1;
        int K = -1;
        int count = 0;
        HashMap<Integer, TreeSet<Integer>> itemLevelOne = new HashMap<>();
        List<TrieNode> candidate;
        List<TrieNode> finalResult = new ArrayList<>();
        while ( (currentTransaction = bufferedReader.readLine())!= null){
            String[] splittedString = currentTransaction.split(" ");
            for(String token: splittedString){
                int currentItem = Integer.parseInt(token);
                TreeSet<Integer> transactionSet = itemLevelOne.get(currentItem);
                if( transactionSet!=null){
                    transactionSet.add(transactionCount);
                }
                else {
                    transactionSet = new TreeSet<>();
                    transactionSet.add(transactionCount);
                    itemLevelOne.put(currentItem, transactionSet);
                }
            }
            transactionCount++;
        }
        bufferedReader.close();
        bufferedReader = new BufferedReader(new FileReader(TEST_INPUT_FILE));
        while ( (currentInput = bufferedReader.readLine())!= null){
            String[] splittedInput = currentInput.split(" ");
            if(splittedInput.length >= 2){
                minSupport = Integer.parseInt(splittedInput[0]);
                K = Integer.parseInt(splittedInput[1]);
            }
            candidate = apriori.insertItemsToHead(headNode, itemLevelOne, minSupport);
            count++;
            while(candidate.size() !=0){
                if(count >= K){
                    finalResult.addAll(candidate);
                }
                candidate = apriori.candidateGenerate(headNode,candidate,minSupport);
                count++;
                apriori.computeSupport(candidate, minSupport);
            }
            apriori.printCandidateList(finalResult, datasetPreprocessor.getReverseTokenMap(), K, minSupport);
            headNode = new TrieNode(-1);
            finalResult.clear();
            count = 0;
        }
        bufferedReader.close();
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time taken: "+totalTime);
    }

    public void computeSupport(List<TrieNode> candidate, int minSupport) {
        for(int i = 0; i< candidate.size(); ){
            TrieNode trieNode = candidate.get(i);
            if(trieNode.supportCount < minSupport){
                candidate.remove(i);
            }
            else{
                TrieNode parentNode = trieNode.directParentNode;
                parentNode.childListOfNodes.add(trieNode);
                trieNode.currentLevel = parentNode.currentLevel + 1;
                i++;
            }
        }
    }

    public void printCandidateList(List<TrieNode> candidate, Map<Integer, String> reverseMap, int K, int minSupport) throws FileNotFoundException, UnsupportedEncodingException {
        String fileName = "out_s="+minSupport+"_k="+K+"+.txt";
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        System.out.println("Output written for this min_support="+minSupport+" and k="+K+"to "+fileName);
        int listSize = candidate.size();
        for(int i = 0; i< listSize; i++){
            TrieNode currentNode = candidate.get(i);
            for(Integer item: currentNode.parentSet){
                writer.print(reverseMap.get(item) + " ");
            }
            writer.println("( " + currentNode.supportCount + " )");
        }
        writer.close();
    }
}
