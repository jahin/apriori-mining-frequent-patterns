import java.util.*;
/*
* This class is kind of Util Class, which contains the utility methods for the Apriori.
* 1. PrintSubsets : is used to generate the k-1 sized subsets of the current new node with k elements. The we check against
*    this list to check the acceptance of this new child node.
* 2. intersection : this one is from the earlier assignment . To generate the documents that contains the item X and Y
*    we used this method.
* */
public class PrintAllSizeKSubset {
    static HashSet<HashSet<Integer>> allKSubset = new HashSet<>();
    static void PrintSubsets(List<Integer> array, boolean[] used, int startIndex,
                             int currentSize, int k){
        if(currentSize == k){
            HashSet<Integer> set = new HashSet<>();
            for(int i =0; i< array.size(); i++){
                if(used[i]) {
                    set.add(array.get(i));
                }
            }
            allKSubset.add(set);
            return;
        }
        if(startIndex == array.size()){
            return;
        }
        used[startIndex] = true;
        PrintSubsets(array, used, startIndex+1, currentSize+1, k);
        used[startIndex] = false;
        PrintSubsets(array, used, startIndex+1, currentSize, k);

    }

    static Set<Integer> intersection(Set<Integer> a, Set<Integer> b){
		/*
		First convert the posting lists from sorted set to something we
		can iterate easily using an index. I choose to use ArrayList<Integer>.
		Once can also use other enumerable.
		 */
        ArrayList<Integer> PostingList_a = new ArrayList<Integer>(a);
        ArrayList<Integer> PostingList_b = new ArrayList<Integer>(b);
        TreeSet result = new TreeSet();

        //Set indices to iterate two lists. I use i, j
        int i = 0;
        int j = 0;

        while(i!=PostingList_a.size() && j!=PostingList_b.size()){

            //TO-DO: Implement the intersection algorithm here
            Integer fromA = PostingList_a.get(i);
            Integer fromB = PostingList_b.get(j);
            if(fromA.intValue() == fromB.intValue()){
                result.add(PostingList_a.get(i));
                i++;j++;
            }
            else if (PostingList_a.get(i) < PostingList_b.get(j)){
                i++;
            }
            else if (PostingList_a.get(i) > PostingList_b.get(j)){
                j++;
            }
        }
        return result;
    }
}
