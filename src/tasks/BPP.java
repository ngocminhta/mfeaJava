package tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algorithm.Main;
import algorithm.Task;
import util.BPPException;
import util.BPPFileParser;

public class BPP extends Task {
	private int[] objects;
	
	public BPP(String fileName) {
		super();

		BPPFileParser parser;

		try {
			parser = new BPPFileParser(fileName);
			this.objects = parser.getDim();
			if (dimension < this.objects.length) {
				dimension = this.objects.length;
			}
			Main.addDim(this.objects.length);
			Main.addType(false);
		} catch (BPPException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static List<Integer> decode(List<Double> tx) {
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
		// chia so bin theo capacity
		List<Integer> xx = decode(ind);
		double c = 1;
		int capacity = BPPFileParser.getCapacity();
		int tmp = 0;
		for (int i = 0; i <= xx.size() - 1; i++) {
			if (xx.get(i) < objects.length) {
				if (tmp + objects[xx.get(i)] > capacity) {
					c += 1;
					tmp = objects[xx.get(i)];
				}
				else {
					tmp += objects[xx.get(i)];
				}
			}
		}
		if (tmp > capacity) {
			c += 1;
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