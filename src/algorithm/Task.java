package algorithm;

import java.util.List;

abstract public class Task {
    protected static int dimension;
	protected int capacity;

    protected static int getStride(List<Double> tx, List<Double> tmp_tx, double[] window) {
        int stride;
        if ((tmp_tx.size() - window.length) % (dimension - 1) == 0) {
            stride = (tmp_tx.size() - window.length) / (dimension - 1);
        } else {
            stride = (tmp_tx.size() - window.length) / (dimension - 1) + 1;
            int zero_padding = (dimension - 1) * stride + window.length - tx.size();
            for (int i = 0; i < zero_padding; i++) {
                tmp_tx.add(0.0);
            }
        }
        return stride;
    }

    public abstract void makeIndividualVail(List<Double> ind);

    public abstract boolean checkIndividualVail(List<Double> ind);

    public abstract Double computeFitness(List<Double> ind);

    public abstract int getLenGen();
}
