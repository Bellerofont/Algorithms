package codesignal.interview;

import java.util.*;

public class GroupingDishes {

    String[][] groupingDishes(String[][] dishes) {
        Map<String, HashSet<String>> map = new HashMap<>();
        for(String[] dish : dishes){
            for(int i = 1; i< dish.length; i++){
                HashSet<String> d = map.getOrDefault(dish[i], new HashSet<>());
                d.add(dish[0]);
                map.put(dish[i], d);
            }
        }
        ArrayList<ArrayList<String>> a = new ArrayList<>();
        for(String key : map.keySet()){
            if(map.get(key).size()>1){
                ArrayList<String> t = new ArrayList<>();
                t.add(key);
                t.addAll(map.get(key));
                a.add(t);
            }
        }
        for (ArrayList<String> b : a) {
            Collections.sort(b.subList(1,b.size()));
        }
        a.sort(Comparator.comparing(o -> o.get(0)));
        String[][] r = new String[a.size()][];
        for (int i = 0; i < a.size(); i++) {
            ArrayList<String> row = a.get(i);
            r[i] = row.toArray(new String[0]);
        }
        return r;
    }

}
