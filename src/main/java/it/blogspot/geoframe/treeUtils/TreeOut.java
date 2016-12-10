package it.blogspot.geoframe.treeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import oms3.annotations.Execute;
import oms3.annotations.In;
import oms3.annotations.Initialize;
import oms3.annotations.Out;

import org.jgrasstools.gears.libs.modules.JGTModel;

/**
 *
 * @author sidereus
 * @version 0.3.1, 11/25/16
 */
public class TreeOut extends JGTModel {

	@In
	public Map<Integer, double[]> in;

	@Out
	public static Map<Integer, double[]> outTree;

    @Initialize
	public void init() {
		outTree = new HashMap<>();
	}

	@Execute
	public void process() {
		for (Map.Entry<Integer, double[]> inme : in.entrySet()) {
			Integer key = inme.getKey();
			double[] val = inme.getValue();
			
			if (TreeOut.outTree.containsKey(key)) add(key, val, TreeOut.outTree.get(key));
			else add(key, val);
		}
	}
	
	private void add(final Integer key, final double[] val, final double[] valFromOut) {
		ArrayList<Double> tmp = new ArrayList<>();
		
		for (int i=0; i<valFromOut.length; i++) {
			tmp.add(valFromOut[i]);
		}
		for (int i=0; i<val.length; i++) {
			tmp.add(val[i]);
		}

		double[] ret = new double[tmp.size()];
		
		for (int i=0; i<tmp.size(); i++) ret[i] = tmp.get(i);
		
		TreeOut.outTree.replace(key, ret);
	}
	
	private void add(final Integer key, final double[] val) {
		TreeOut.outTree.put(key, val);
	}
}
