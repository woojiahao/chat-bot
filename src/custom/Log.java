package custom;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

// used to log to console and include the class name
// easier to spot errors
public class Log {
	public static void i (Class className, String message) {
		// substring excludes the word "class" at the start of the string
		System.out.println(className.toString().substring(6) + ": " + message);
	}
}
