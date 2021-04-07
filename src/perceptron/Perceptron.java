/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;

/**
 *
 * @author bdwoo
 */
public class Perceptron {
	int[] classes;
	int[] weights1;
	int[] weights2;
	int a1;
	int a2;
	int limit;	//Limit to the number of attempts

	public Perceptron(int[] classes, int[] weights1, int[] weights2) {
		if (classes.length != weights1.length && classes.length != weights2.length) {
			System.out.println("Array of weights and classes must be the same length");
			throw new RuntimeException();
		}
		this.classes = classes;
		this.weights1 = weights1;
		this.weights2 = weights2;
		this.a1 = 0;
		this.a2 = 0;
		this.limit = 100;
	}
	
	void run() {
		System.out.println("Instance\ta1\ta2\tPredicted class");
		int correct = 0;
		int predicted;
		
		for (int i=0 ; correct < classes.length && i<limit ; i++) {
			//i keeps track of total iterations, j keeps track of index
			int j = i%classes.length;
			
			//If predicted is greater than zero, predict class 1, else class2
			predicted = a1*weights1[j] + a2*weights2[j];
			predicted = (predicted > 0 ? 1 : -1);
			
			if (predicted != classes[j]) {
				if (classes[j] == 1) {
					a1 += weights1[j];
					a2 += weights2[j];
				}
				else {
					a1 -= weights1[j];
					a2 -= weights2[j];
				}
				correct = 0;	//Reset counter of correct instances
			}
			else {	//Instance classified correctly
				correct++;
			}
			
			System.out.println(j + "\t\t" + a1 + "\t" + a2 + "\t" + predicted);
		}
		
		
	}

	
	public static void main(String[] args) {
		//Test set 1
		int[] classes = {1, -1, 1, -1};
		int[] weights1 = {0, 0, 4, 4};
		int[] weights2 = {4, 0, 4, 0};
		
		//Test set 2
//		int[] classes = {1, -1, 1, -1};
//		int[] weights1 = {1, 1, 2, 2};
//		int[] weights2 = {3, 1, 4, 2};

		Perceptron p = new Perceptron(classes, weights1, weights2);
		
		p.run();
	}
	
}
