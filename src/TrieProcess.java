/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

*/
/**
 * Created by Rakibul on 11/16/2014.
 *//*

public class TrieProcess {
    public static final String INDEXED_FILE_PATH = "./sample-input.txt";//sample-input the-file-name

    public void findPlaceForNode(TrieNode head, String[] transactionItems, int index, int transactionCount){
        HashMap<Integer, TrieNode> headChild = head.getChildNodes();
        if(index < transactionItems.length) {
            Integer currentItem = Integer.parseInt(transactionItems[index]);

            TrieNode node;
            //2 cases
            //1. already exist 2. doesn't exist and we have to create and insert it
            if (headChild.size() > 0 && null != (node = headChild.get(currentItem))) {
                node.nodeCount += 1;
                node.lastUpdatedTransaction = transactionCount;

                //node.levelOfNode = head.levelOfNode + 1;
                System.out.println("added a node: "+node.currentId+" at level: "+ node.levelOfNode + " current count is: " + node.nodeCount);
            } else {
                // head node without any child
                node = new TrieNode(currentItem);
                node.nodeCount = 1;
                headChild.put(currentItem, node);
                node.levelOfNode = head.levelOfNode + 1;
                //System.out.println("added a node: "+node.currentId+" at level: "+ node.levelOfNode + " current count is: " + node.nodeCount);
            }
            if (index == transactionItems.length - 1){
                node.endOfTransaction = true;
                return;
            } else {
                updateHeadChildList(transactionItems, node,index+ 1);
                findPlaceForNode(node, transactionItems, index + 1, transactionCount);
            }
        }
    }
    public void updateHeadChildList(String[] splittedString, TrieNode head, int index){
        HashMap<Integer, TrieNode> headChild = head.getChildNodes();
        for(int i = index; i< splittedString.length; i++){
            int currentItem = Integer.parseInt(splittedString[i]);
            TrieNode node;
            if(null !=( node = headChild.get(currentItem))){
                if(i != index)node.nodeCount ++;
            }
            else{
                TrieNode trieNode = new TrieNode(currentItem);
                trieNode.levelOfNode = index + 1;
                if(i != index)trieNode.nodeCount = 1;
                head.insertChildNode(trieNode);
            }
        }
    }

    public void insertIntoTrie(TrieNode head, String currentTransaction, int transactionCount){
        TrieNode node;
        String[] splittedString = currentTransaction.split(" ");

        updateHeadChildList(splittedString, head, 0);
        //todo: insert all the transaction elements to the level 1 of Trie
        //todo: iterate over the current child and check for the nodes
        */
/*HashMap<Integer, TrieNode> headChild = head.getChildNodes();
        for(int i = 0; i< splittedString.length; i++){
            int currentItem = Integer.parseInt(splittedString[i]);
            if(null ==(node = headChild.get(currentItem))){
                TrieNode nodeNew = new TrieNode(currentItem);
                nodeNew.nodeCount = 1;
                nodeNew.levelOfNode = 1;
                nodeNew.lastUpdatedTransaction = transactionCount;
                head.insertChildNode(nodeNew);
                //System.out.println("added a new node: " + nodeNew.toString());
                if(nodeNew.currentId == 513) {
                    //System.out.println("added 513 nodeCount: "+ nodeNew.nodeCount+ " in transaction: "+ transactionCount);
                }
            }
            else if(i != 0 || splittedString.length == 1){
                node.nodeCount ++;
                node.lastUpdatedTransaction = transactionCount;
                if(node.currentId == 513) {
                    //System.out.println("added 513 nodeCount: "+node.nodeCount+ " in transaction: "+ transactionCount);
                }
                //System.out.println("updated node: " + node.toString());
            }

        }*//*

        //if(splittedString.length > 1)
            findPlaceForNode(head, splittedString, 0, transactionCount);

    }

    public void traverseTrie(TrieNode head, int level, int minSupportCount) throws Exception{
        Queue<TrieNode> trieNodeQueue = new LinkedList<>();
        //Set<Integer> keysOfHead = head.getChildNodes().keySet();
        trieNodeQueue.addAll(head.getChildNodes().values());
        PrintWriter writer = new PrintWriter("trie-traverse.txt", "UTF-8");
        while(!trieNodeQueue.isEmpty()){
            TrieNode topNode = trieNodeQueue.poll();
            Map<Integer, TrieNode> childNodes = topNode.getChildNodes();
            //test start
            */
/*if(topNode.nodeCount >= minSupportCount) {
                writer.println(topNode.toString());

            }*//*

            writer.println(topNode.toString());
            //test end
            if(topNode.nodeCount >= minSupportCount){
                //System.out.println("found some nodes: "+topNode.nodeCount + " nodeLevel: "+topNode.levelOfNode);
                if(childNodes.size() > 0 && topNode.levelOfNode < level){
                    */
/*Set<Integer> keySet = new HashSet<>(childNodes.keySet());
                    for(Integer key: keySet){
                        trieNodeQueue.add(childNodes.get(key));
                        System.out.println("adding to queue: " + childNodes.get(key).currentId + " with level: " + childNodes.get(key).levelOfNode + " with nodeCount: " + childNodes.get(key).nodeCount);
                    }*//*

                    trieNodeQueue.addAll(childNodes.values());
                }
                //System.out.println("added new childs: "+topNode.getChildNodes().values());
                //System.out.println("Got a node of count: " + topNode.nodeCount + " and level: " + topNode.levelOfNode);
                else if(topNode.levelOfNode == level){
                    System.out.println("got an element: " + topNode.currentId + " count: "+topNode.nodeCount);
                }
                else{
                    System.out.println("Got a result node of count: " + topNode.nodeCount + " and level: " + topNode.levelOfNode);
                }
            }
        }
        writer.close();

    }

    public static void main(String[] args) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(INDEXED_FILE_PATH));
        TrieProcess trieProcess = new TrieProcess();
        TrieNode headNode = new TrieNode(-1);
        headNode.levelOfNode = 0;
        String currentTransaction;
        int transactionCount = 1;
        while ( (currentTransaction = bufferedReader.readLine())!= null){
            trieProcess.insertIntoTrie(headNode, currentTransaction, transactionCount++);
        }
        int minSupportCount = 2;
        int levelCount = 2;
        trieProcess.traverseTrie(headNode, levelCount, minSupportCount);
        */
/*System.out.println(headNode.getChildNodes().get(40955));
        System.out.println(headNode.getChildNodes().get(23237).nodeCount);*//*

    }
}
*/
