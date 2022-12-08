package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

import util.BPPFileParser;
import util.TSPFileParser;
import tasks.BPP;
import tasks.TSP;
import algorithm.Main;


public class Individual {
    List<Double> gen;
    List<Double> fitnessTask;
    List<Integer> factorial_rank;
    private int skillFactor;
    private double scalarFitness;
    private int dimension;

    Individual(List<Double> gen, List<Double> fitnessTask) {
        super();
        this.gen = gen;
        this.fitnessTask = fitnessTask;
    }

    Integer getMinFactorialRank() {
        Integer min = 10000000;
        for (Integer tmp : factorial_rank) {
            if (min > tmp) min = tmp;
        }
        return min;
    }

    List<Double> getGen() {
        return gen;
    }

    List<Double> getFitnessTask() {
        return fitnessTask;
    }

    void setFitnessTask(List<Double> fitnessTask) {
        this.fitnessTask = fitnessTask;
    }

    int getSkillFactor() {
        return skillFactor;
    }

    void setSkillFactor(int skillFactor) {
        this.skillFactor = skillFactor;
    }

    double getScalarFitness() {
        return scalarFitness;
    }

    void setScalarFitness(double scalarFitness) {
        this.scalarFitness = scalarFitness;
    }

    List<Integer> getFactorialRank() {
        return factorial_rank;
    }

    void setFactorial_rank(List<Integer> factorial_rank) {
        this.factorial_rank = factorial_rank;
    }

    public static void quicksort(List<Double> list, List<Integer> index, int left, int right) {
	    int q;
	    if (right > left) {
	        q = partition(list, index, left, right);
	        // after ‘partition’
	        // list[left..q-1] ≤ list[q] ≤ list[q+1..right]
	        quicksort(list, index, left, q - 1);
	        quicksort(list, index, q + 1, right);
	    }
	}
    
    static int partition(List<Double> list, List<Integer> index, int left, int right) {
	    double P = list.get(left);
	    int i = left;
	    int j = right + 1;
	    for (;;) { // infinite for-loop, break to exit
	        while (list.get(++i) < P)
	            if (i >= right)
	                break;
	        // Now, list[i]≥P
	        while (list.get(--j) > P)
	            if (j <= left)
	                break;
	        // Now, list[j]≤P
	        if (i >= j)
	            break; // break the for-loop
	        else {
	            // swap(list[i],list[j]);
	            Collections.swap(list, i, j);
	        	Collections.swap(index, i, j);
	        }
	    }
	    if (j == left)
	        return j;
	    // swap (list[left],list[j]);
	    Collections.swap(list, left, j);
	    Collections.swap(index, left, j);
	    return j;
	}
    
    int[] x = BPPFileParser.BPPDim;
    //int dimBPP = x.length;
    //int dimTSP = TSPFileParser.graph.length;
    List<Integer> TSPtype = Main.getTSPtype();
    
    @Override
    public String toString() {
    	List<Integer> TSPtmp = new ArrayList<Integer>();

    	for (int i = 1; i <= gen.size(); i++) {
			TSPtmp.add(i);
		}

		List<Double> cloned_TSP = new ArrayList<Double>(gen);
		quicksort(cloned_TSP, TSPtmp, 0, gen.size()-1);

    	if (TSPtype.indexOf(skillFactor) != -1) { // TSP
    		int dimTSP = Main.dimTask.get(skillFactor);
    		
    		List<Integer> TSPres = new ArrayList<Integer>();
    		for (int i = 0; i < TSPtmp.size(); i++) {
    			if (TSPtmp.get(i) <= dimTSP) {
    				TSPres.add(TSPtmp.get(i));
    			}
    		}
    		
    		return "TSP Route = " + TSPres + ", fitnessTask=" + fitnessTask +
    				  ", factorialRank=" + factorial_rank + ", skillFactor=" + skillFactor +
    				  ", scalarFitness=" + scalarFitness;
    	}
    	
    	else { //if (skillFactor % 2 == 1) { // BPP
    		int dimBPP = Main.dimTask.get(skillFactor);
    		List<Integer> xx = BPP.decode(gen);
    		int[] BPPtmp = new int[xx.size()];
    		int[] BPPindx = new int[xx.size()];
    		
    		int c = 1;
    		int capacity = BPPFileParser.getCapacity();
    		int tmp = 0;
    		for (int i = 0; i <= xx.size() - 1; i++) {
    			if (xx.get(i) < dimBPP) {
    				if (tmp + x[xx.get(i)] > capacity) {
        				c += 1;
        				tmp = x[xx.get(i)];
        				BPPtmp[i] = c;
        			}
        			else {
        				tmp += x[xx.get(i)];
        				BPPtmp[i] = c;
        			}
    			}
    		}
    		
    		for (int i = 0; i < xx.size(); i++) {
    			BPPindx[xx.get(i)] = BPPtmp[i];
    		}
    		
    		int[] BPPres = new int[dimBPP];
    		for (int i = 0; i < dimBPP; i++) {
    			BPPres[i] = BPPindx[i];
    		}
			
			return "BPP Bin Order = " + Arrays.toString(BPPres) + ", fitnessTask=" + fitnessTask +
			  ", factorialRank=" + factorial_rank + ", skillFactor=" + skillFactor +
			  ", scalarFitness=" + scalarFitness;
    	}
    	//return null;
    }
    
    
}
