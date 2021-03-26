package tks_project_1;

/**
 * The Class <b>CalculatorAdvanced</b>.
 * <p>
 * The <b>CalculatorAdvanced</b> class is implemented to do more complicated
 * operations than the parent <b>Calculator</b> class. Besides the basic
 * functionality that the parent class provides, the <b>CalculatorAdvanced</b>
 * has the ability to calculate the given power of the <i>currentValue</i>
 * attribute, calculate the factorial of the <i>currentValue</i>, check if the
 * <i>currentValue</i> is a perfect number and to check if the
 * <i>currentValue</i> is an Armstrong number. Every operation is covered with
 * specific test cases and proper Exceptions.
 * 
 * @see Calculator
 * 
 * 
 * @author Jovan Vrhovac
 * @version 0.1
 * @since 2020
 */
public class CalculatorAdvanced extends Calculator {

	/**
	 * The <b>calculateAdvanced</b> method.
	 * 
	 * The method calculateAdvanced takes only one parameter <b>char</b>
	 * <i>action</i> with the intention to do an advanced calculation like
	 * exponentiation and factorial. If the parameter <i>action</i> is an integer
	 * number in the set [0..9], the method will calculate the <i>action</i>-th
	 * power of <i>currentValue</i> like:
	 * <p>
	 * &emsp;(if <i>action</i> &#8714; [0..9]) <i>currentValue</i> =
	 * <i>currentValue</i><sup><i>action</i></sup>
	 * 
	 * <p>
	 * If the parameter is an exclamation mark ('!'), the method will calculate the
	 * factorial of <i>currentValue</i> like:
	 * <p>
	 * 
	 * &emsp;(if <i>action</i> == '!') <i>currentValue</i> = <i>currentValue</i>!
	 * 
	 * <p>
	 * In other cases, based on the specific situation, the method will throw an
	 * Exception.
	 * 
	 *
	 * @param action The action (a natural number with zero or an exclamation mark).
	 *               <p>
	 *               &emsp;(if <i>action</i> &#8712; [0..9]) <i>currentValue</i> =
	 *               <i>currentValue</i><sup><i>action</i></sup>
	 *               <p>
	 *               &emsp;(if <i>action</i> == '!') <i>currentValue</i> =
	 *               <i>currentValue</i>!
	 * 
	 *               <p>
	 *               <b>Note:</b> The operator <i>action</i> is a character type
	 *               (<b>char</b>).
	 * 
	 * @throws NotSupportedOperationException Exception thrown if the operator
	 *                                        <i>action</i>&#8713;[0,9] or
	 *                                        <i>action</i>&#8800;'!'
	 * @see NotSupportedOperationException
	 * @throws NumberNotInAreaException Exception thrown if the <b>Double</b> value
	 *                                  of <i>currentValue</i> is smaller than zero
	 *                                  or greater than 10.
	 * @see NumberNotInAreaException
	 */
	public void calculateAdvanced(char action) throws NotSupportedOperationException, NumberNotInAreaException {

		// Get the currentValue with the Getter method
		Double newCurrentValue = getCurrentValue();

		// Check if the action is in the righ area
		if (action >= '0' && action <= '9') {

			if (action == '0') {
				// Every number ^0 is equal to zero (0)
				setCurrentValue(1.0);

			} else {

				Double powCurrentValue = 1.0;

				// Manually calculate the power of a number
				for (int i = 0; i < Character.getNumericValue(action); i++) {
					powCurrentValue *= newCurrentValue;
				}

				// Set the new value for currentValue with the Setter method
				setCurrentValue(powCurrentValue);
			}

			// If the action is '!', that means the method has to calculate the factorial
		} else if (action == '!') {

			if (newCurrentValue >= 0.0 && newCurrentValue <= 10.0) {

				if (newCurrentValue == 0.0) {
					// 0! = 1
					setCurrentValue(1.0);

				} else {

					// Manually calculating the factorial
					int intNewCurrentValue = newCurrentValue.intValue();
					Integer sum = 1;

					for (int i = 1; i <= intNewCurrentValue; i++) {
						sum *= i;
					}

					// Set the new value for currentValue with the Setter method
					setCurrentValue(sum.doubleValue());
				}

			} else {

				throw new NumberNotInAreaException("Trenutni broj je prevelik.");

			}

		} else {

			throw new NotSupportedOperationException("Nepoznat operator!");

		}

	}

	/**
	 * The <b>isArmstrong</b> method.
	 * <p>
	 * 
	 * The isArmstrong method checks if the <i>value</i> is an Armstrong number.
	 * <p>
	 * 
	 * In number theory, a narcissistic number (also known as an Armstrong number
	 * (after Michael F. Armstrong)) in a given number base <i>b</i> is a number
	 * that is the sum of its own digits each raised to the power of the number of
	 * digits.
	 * <p>
	 * Some od the Armstrong numbers are 0, 1, 2, 3, 130, 131, 203, 223, 313, 332,
	 * 1103, 3303...
	 * 
	 * 
	 * @param value value to prove if it is an Armstrong number.
	 * @return true, if the number is an Armstrong number
	 */
	public boolean isArmstrong(int value) {
		int remainder = 0, powRemainder, result = 0, n = 0;
		int valueCopy = value;

		while (valueCopy != 0) {
			valueCopy /= 10;
			n++;
		}

		valueCopy = value;

		while (valueCopy != 0) {
			remainder = valueCopy % 10;
			powRemainder = 1;

			for (int i = 0; i < n; i++)
				powRemainder *= remainder;

			result += powRemainder;

			valueCopy /= 10;
		}

		return (result == value);
	}

	/**
	 * The <b>isPerfect</b> method.
	 * <p>
	 * 
	 * The isPerfect method checks if the <i>value</i> is a perfect number.
	 * <p>
	 * In number theory, a perfect number is a positive integer that is equal to the
	 * sum of its positive divisors, excluding the number itself. For instance, 6
	 * has divisors 1, 2 and 3 (excluding itself), and 1 + 2 + 3 = 6, so 6 is a
	 * perfect number.
	 *
	 * @param value The value to prove if it is a perfect number
	 * @return true, if the number is perfect
	 */
	public boolean isPerfect(int value) {

		int sum = 0;

		for (int i = 1; i < value; i++) {
			if (value % i == 0) {
				sum += i;
			}
		}

		return (sum == value);
	}

	/**
	 * The <b>hasCharacteristic</b> method.
	 * <p>
	 * 
	 * The method hasCharacteristic uses the helper functions <i>isArmstrong</i> and
	 * <i>isPerfect</i> to check if the integer value <i>currentValue</i> has
	 * specific charaxteristics like being a perfect or Armstrong number. The
	 * <b>char</b> <i>value</i> can be either the letter 'A' (meaning that the
	 * method needs to check if the <i>currentValue</i> is an Armstrong number) or
	 * the letter 'P' (meaning that the method needs to check if the
	 * <i>currentValue</i> is a perfect number). In every other case, and Exception
	 * is thrown.
	 *
	 * @param value The value indicating what characteristic to test ('P' -
	 *              isPerfect; 'A' - isArmstrong)
	 * @return true, if the <i>currentValue</i> has the characteristic
	 * @throws NotSupportedOperationException The NotSupportedOperationException
	 *                                        exception is thrown if <i>value</i>
	 *                                        &#8713; ('P', 'A')
	 * @see NotSupportedOperationException
	 * @throws NumberNotInAreaException The number NumberNotInAreaException
	 *                                  exception is thrown if the
	 *                                  <i>currentValue</i> is smaller or equal than
	 *                                  1
	 * @see NumberNotInAreaException
	 */
	public Boolean hasCharacteristic(char value) throws NotSupportedOperationException, NumberNotInAreaException {

		Double originalValue = getCurrentValue();
		int intOriginalValue = originalValue.intValue();

		if (intOriginalValue > 1) {

			if (value == 'A') {

				return isArmstrong(intOriginalValue);

			} else if (value == 'P') {

				return isPerfect(intOriginalValue);

			} else {

				throw new NotSupportedOperationException("Nepoznata operacija!");

			}

		} else {

			throw new NumberNotInAreaException("Vrijednost je manja od 1.");

		}

	}
}
