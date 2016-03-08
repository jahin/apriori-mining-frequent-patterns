import java.util.*;
/*
* This is the primitive element of this program. In our Trie Data structure this is the node class.
* It contains several items.
* 1. current item id as currentId
* 2. current level of the node as currentLevel
* 3. support count of this node is also stored here in supportCount
* 4. all the feasible child nodes are also kept in childListOfNodes
* 5. the transactions that contains this node are stored in foundInDocuments
* 6. parents of this node in Trie data structure are also stored in parentSet
* 7. the immediate parent of this node is stored in directParentNode
* */
public class TrieNode {
    Integer currentId;
    Integer currentLevel;
    Integer supportCount;
    List<TrieNode> childListOfNodes;
    Set<Integer> foundInDocuments;
    TreeSet<Integer> parentSet;
    TrieNode directParentNode;
//empty constructor
    public TrieNode(){
        this.supportCount = 0;
        this.childListOfNodes = new ArrayList<>();
        this.currentLevel = 0;
        this.foundInDocuments = new HashSet<>();
        this.parentSet = new TreeSet<>();
    }
//the constructor we generally use in the Apriori Algorithm
    public TrieNode(int currentId){
        this.currentId = currentId;
        this.supportCount = 0;
        this.currentLevel = 0;
        this.foundInDocuments = new HashSet<>();
        this.childListOfNodes = new ArrayList<>();
        this.parentSet = new TreeSet<>();
    }
//this was used while debugging the code
    @Override
    public String toString() {
        String trieNodeDetails = "last id: " + this.currentId+" ";
        trieNodeDetails +=this.parentSet + " supportCount: "+ this.supportCount;
        return trieNodeDetails;
    }
}

