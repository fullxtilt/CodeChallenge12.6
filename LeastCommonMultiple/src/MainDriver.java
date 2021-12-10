import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainDriver {
	
	private static final int PRIMES[] = {
								 2, 3, 5, 7, 9, 11, 13, 
								 17, 19, 23, 29, 31, 37, 
								 41, 43, 47, 53, 59, 61,
								 67, 71, 73, 79, 83, 89,
								 97, 101, 103, 107, 109,
								 113, 127, 131, 137, 139,
								 149, 151, 157, 163, 167,
								 173, 179, 181, 191, 193,
								 197, 199, 211, 223, 227,
								 229, 233, 239, 241, 251};
	
	public static void main(String[] args) {
		int arrays[][] = {
				{5, 4, 6, 46, 54, 12, 13, 17},
				{46, 54, 466, 544},
				{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
				{13, 6, 17, 18, 19, 20, 37}
		};

		System.out.println("LCM of arrays");
		System.out.println("------------------");
		
		for (int array[] : arrays) {
			printArray(array);
			System.out.println("LCM: " + lcmOfArray(array));
			
		}
		
	}
	
	/**
	 * Returns the LCM of all integers in the array.
	 */
	public static int lcmOfArray(int array[]) {
		
		int lcm = 1;
		List<Integer> lcmMultipliers = new ArrayList<>();
		
		// Using division method.
		int previousLayer[] = Arrays.copyOf(array, array.length);
		boolean allAreOne = true;
		do {
			// Bring the next layer down
			int currentLayer[] = Arrays.copyOf(previousLayer, array.length);
			
			// Find the smallest prime divisible by at least one element
			for (int prime : PRIMES) {
				boolean divisibleAtLeastOnce = false;
				
				// Check each number to see if it can be divided
				for (int i = 0; i < currentLayer.length; i++) {
					if (currentLayer[i] % prime == 0) {
						currentLayer[i] /= prime;
						divisibleAtLeastOnce = true;
					}
				}
				
				// Stop searching once we find a divisible prime
				if (divisibleAtLeastOnce) {
					lcmMultipliers.add(prime);
					break;
				}
			}
			
			// Check if all numbers in this layer are 1
			allAreOne = true;
			for (int num : currentLayer) {
				if (num != 1) {
					allAreOne = false;
					break;
				}
			}
			
			// Prevent potential infinite loop
			if (Arrays.equals(currentLayer, previousLayer)) {
				System.err.println("Failed to calculate LCM at layer: ");
				for (int num : currentLayer) 
					System.err.print(num + " ");
				return 0;
			}
			
			// Carry layer down
			previousLayer = currentLayer;
			
		} while (!allAreOne);
		
		// LCM is the product of the primes we used to divide each layer
		for (int multiplier : lcmMultipliers) {
			lcm *= multiplier;
		}
		
		return lcm;
	}
	
	static void printArray(int array[]) {
		for (int num : array) 
			System.out.print(num + " ");
	}

}
