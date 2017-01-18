package kom_ev.wordcount;

import java.util.*;

/**
 * Created by EKomarov on 18.01.2017.
 */

class HashMapToSortedList<K,V> extends HashMap<K,V>{

    List<Map.Entry<K,V>> mapSort(Comparator<Map.Entry<K, V>> customComparator){
        ArrayList<Map.Entry<K,V>> arrayList = new ArrayList<>(entrySet());
        arrayList.sort(customComparator);
        return arrayList;
    }

}
