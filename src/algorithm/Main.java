package algorithm;
import java.util.ArrayList;
import java.util.List;

import tasks.TSP;
import tasks.BPP;

public class Main {
	public static List<Integer> TSPtype = new ArrayList<>();
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new TSP("/Users/ngocminhta/eclipse-workspace/MFEA_TSP_BPP/src/data/tsp/berlin52.tsp"));
        TSPtype.add(tasks.size()-1);
        tasks.add(new BPP("/Users/ngocminhta/eclipse-workspace/MFEA_TSP_BPP/src/data/bpp/bpp101.bpp"));
        MFEA g = new MFEA(tasks, 100, 0.3, 200);
        g.run(50);
    }
    
    public static List<Integer> getTSPtype() {
    	return TSPtype;
    }
}
