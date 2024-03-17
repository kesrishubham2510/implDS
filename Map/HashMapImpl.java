import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/*
    "Array of LinkedList" type implementation
 */
public class HashMapImpl {

    static class HashMap<K,V>{

        private int n; // no of data nodes
        private int N; // number of buckets
        private final double occupancyThreshold;
        private LinkedList<Node<K,V>> buckets[];


        public HashMap(){
            this.n = 0;
            this.N = 10;
            this.occupancyThreshold = 2;
            this.buckets = new LinkedList[N];

            // initialise all the buckets with empty LinkedList
            for(int i=0;i<N;i++)
                buckets[i] = new LinkedList<>();
        }

        public void put(K key, V value){

            IndexWrapper indexWrapper = new IndexWrapper();
            boolean doesExist = containsKey(key, indexWrapper);

            if(!doesExist){
                // simply add the dataNode at the head position
                buckets[indexWrapper.bucketIndex].add(new Node<>(key, value));
                n++;
            }else{
                // update the value of the dataNode
                buckets[indexWrapper.bucketIndex].get(indexWrapper.dataIndex).setValue(value);
            }

            double occupancy = (double) n/N;

            // check occupancy for rehashing
            if(occupancy>=occupancyThreshold){
                rehash();
            }
        }

        public boolean containsKey(K key){
            IndexWrapper indexWrapper = new IndexWrapper();
            return containsKey(key, indexWrapper);
        }

        public V get(K key){
            IndexWrapper indexWrapper = new IndexWrapper();

            if(!containsKey(key, indexWrapper)){
                return null;
            }

            return buckets[indexWrapper.bucketIndex].get(indexWrapper.dataIndex).getValue();
        }

        public V remove(K key){
            IndexWrapper indexWrapper = new IndexWrapper();

            if(!containsKey(key, indexWrapper)){
                return null;
            }

            n--;
            return buckets[indexWrapper.bucketIndex].remove(indexWrapper.dataIndex).getValue();
        }

        public boolean isEmpty(){
            return n==0;
        }

        public ArrayList<K> keySet(){
            
            ArrayList<K> keySet = new ArrayList<>();
            
            // can be done with help of Collectors
            for(int i=0; i< N; i++){

                for(int j=0; j<buckets[i].size(); j++){
 
                    keySet.add(buckets[i].get(j).getKey());
                }

            }

            return keySet;
        }

        private boolean containsKey(K key, final IndexWrapper indexWrapper){
            indexWrapper.bucketIndex = getBucketIndex(key);
            indexWrapper.dataIndex = searchInBucket(key, indexWrapper.bucketIndex);
            return indexWrapper.dataIndex!=-1;
        }

        private int getBucketIndex(K key){
            int bucketIndex = key.hashCode();

            return Math.abs(bucketIndex)%N;
        }

        private int searchInBucket(K key,int bucketIndex){

            LinkedList<Node<K,V>> bucket = buckets[bucketIndex];

            for(int i=0; i<bucket.size(); i++){
                if(bucket.get(i).getKey().equals(key))
                    return i;
            }

            return -1;
        }

        private void rehash(){
            // double the buckets
            int newSize = 2*N;

            LinkedList<Node<K,V>>[] temporayBucket = buckets;

            buckets = new LinkedList[newSize];

            for(int i=0; i<newSize; i++){
               buckets[i] = new LinkedList<>();
            }

            for(int i=0; i < N; i++ ){

                for(int j=0; j < temporayBucket[i].size(); j++){
                    put(temporayBucket[i].get(j).getKey(),temporayBucket[i].get(j).getValue());
                }
            }
        }
    }

    private static class IndexWrapper {
        int bucketIndex;
        int dataIndex;

        public IndexWrapper() {
            bucketIndex = -1;
            dataIndex = -1;
        }
    }
}
