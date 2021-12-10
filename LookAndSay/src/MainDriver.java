import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MainDriver {

	public static void main(String[] args) {
		
		long nums[] = {3132, 1213200012171979L, 54544666L, 
				95, 1213141516171819L, 120520, 231};
		
		for (long num : nums) {
			lookAndSay(num);
		}
		
	}
	
	/**
	 * Prints and returns a number based on the rules below:
	 * <p>
	 * -Split the number into groups of two digit numbers. If the number has an odd number of digits,
	 * return -1. <p>
	 * -For each group, concatenate the last digit to a new string the same number of times as the
	 * value of the first digit. <p>
	 * -Return the result as an integer.
	 * @param num 	The number to be grouped into pairs of digits
	 * @return		A number calculated from the above rules			
	 */
	public static long lookAndSay(long num) {
		
		// Divide num into individual digits
		Queue<Integer> digits = new ArrayDeque<>();
		long dividedNum = num;
		while (dividedNum > 0) {
			digits.add((int)(dividedNum % 10));
			dividedNum /= 10;
		}
		
		// Look and Say
		System.out.print("lookAndSay(" + num + ") -> ");
		long lookAndSay = 0;
		int tensPlace = 0;
		while(!digits.isEmpty()) {
			int times, say;
			
			try {
				say = digits.remove();
				times = digits.remove();
			} catch (NoSuchElementException e) {
				// If we run out of digits to remove, the given number was odd
				System.out.println(-1);
				return -1;
			}
			
			// Add [say] as a digit for each [times]
			for (int i = 0; i < times; i++) {
				lookAndSay += (long)say * Math.pow(10, tensPlace);
				tensPlace++;
			}
		}
		
		System.out.println(lookAndSay);
		
		return lookAndSay;
	}
}
