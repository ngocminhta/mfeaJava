package tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algorithm.Task;
import algorithm.Main;
import util.TSPException;
import util.TSPFileParser;

public class TSP extends Task {
	private int[][] graph;

	public TSP(String fileName) {
		super();

		TSPFileParser parser;

		try {
			parser = new TSPFileParser(fileName);
			this.graph = parser.getGraph();
			if (dimension < this.graph.length) {
				dimension = this.graph.length;
			}
			Main.addDim(this.graph.length);
			Main.addType(true);
		} catch (TSPException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private List<Integer> decode(List<Double> tx) {
		List<Double> x = new ArrayList<>();
		List<Double> tmp_tx = new ArrayList<>(tx);

		if (tmp_tx.size() > dimension) {
			double window[] = { Math.random(), Math.random() };
			int stride = getStride(tx, tmp_tx, window);
			for (int i = 0; i < tmp_tx.size(); i += stride) {
				double tmp_x = 0;
				for (int j = 0; j < window.length; j++) {
					tmp_x += tmp_tx.get(i + j) * window[j];
				}
				x.add(tmp_x);
			}
		} else {
			x = tmp_tx;
		}

		List<Integer> lA = new ArrayList<>();
		Double ts[] = new Double[dimension];
		for (int i = 0; i < dimension; i++) {
			ts[i] = x.get(i);
		}

		Arrays.sort(ts);
		for (int i = 0; i < dimension; i++) {
			lA.add(x.indexOf(ts[i]));
		}
		
		return lA;
	}

	@Override
	public Double computeFitness(List<Double> ind) {
		List<Integer> tmp = decode(ind);
		double c = 0;
		int TSPDim = this.graph.length;
		
		List<Integer> xx = new ArrayList<Integer>();
		if (TSPDim < tmp.size()) {
			for (int i = 0; i < tmp.size() - 1; i++) {
				if (tmp.get(i) < TSPDim) {
					xx.add(tmp.get(i));
				}
			}
		}
		else {
			xx = tmp;
		}

		for (int i = 0; i < xx.size() - 1; i++) {
			if (xx.get(i) < TSPDim-1) {
				c += this.graph[xx.get(i)][xx.get(i + 1)];
			}
		}
		return c;
	}

	@Override
	public void makeIndividualVail(List<Double> ind) {
	}

	@Override
	public boolean checkIndividualVail(List<Double> ind) {
		return false;
	}

	@Override
	public int getLenGen() {
		return dimension;
	}
	
	
}
