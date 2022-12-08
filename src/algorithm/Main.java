package algorithm;
import java.util.ArrayList;
import java.util.List;

import tasks.TSP;
import tasks.BPP;

public class Main {
	public static List<Integer> TSPtype = new ArrayList<>();
	public static List<Integer> dimTask = new ArrayList<>();
	public static List<Task> tasks = new ArrayList<>();
	public static void addDim(Integer newTask) {
	    dimTask.add(newTask);
	}
	public static void addTSPtype() {
	    TSPtype.add(tasks.size());
	    System.out.println(TSPtype);
	}
	
    public static void main(String[] args) {
        tasks.add(new TSP("/Users/ngocminhta/eclipse-workspace/MFEA_TSP_BPP/src/data/tsp/berlin52.tsp"));
        tasks.add(new BPP("/Users/ngocminhta/eclipse-workspace/MFEA_TSP_BPP/src/data/bpp/bpp52.bpp"));
        tasks.add(new TSP("/Users/ngocminhta/eclipse-workspace/MFEA_TSP_BPP/src/data/tsp/eil101.tsp"));
        tasks.add(new BPP("/Users/ngocminhta/eclipse-workspace/MFEA_TSP_BPP/src/data/bpp/bpp101.bpp"));
        tasks.add(new TSP("/Users/ngocminhta/eclipse-workspace/MFEA_TSP_BPP/src/data/tsp/gr229.tsp"));
        tasks.add(new BPP("/Users/ngocminhta/eclipse-workspace/MFEA_TSP_BPP/src/data/bpp/bpp229.bpp"));
        MFEA g = new MFEA(tasks, 100, 0.3, 200);
        g.run(50);
        
    }
    
    public static List<Integer> getTSPtype() {
    	return TSPtype;
    }
}
