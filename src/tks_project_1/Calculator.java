package tks_project_1;

/**
 * The Class <b>Calculator</b>.
 * <p>
 * The <b>Calculator</b> class is used to perform default operations on the
 * <i>currentValue</i> attribute, depending on the used operand. Each method is
 * covered by appropriate unit tests and appropriate exceptions, what allows a
 * more comfortable usage.
 * <p>
 * <b>Note:</b> The program was created as a project for the course
 * <i>Testiranje i kvalitet softvera</i> and it's not usable for other purposes.
 * 
 * @author Jovan Vrhovac
 * @version 0.1
 * @since 2020
 * 
 */
public class Calculator {

	/**
	 * The <i>currentValue</i>.
	 * <p>
	 * At the very beginning, the <i>currentValue</i> attribute is initialized to
	 * 0.0. Care should be taken while working with it, as it is a <b>Double</b>
	 * value.
	 */
	private Double currentValue = 0.0;

	/**
	 * Getter method for the <i>currentValue</i>.
	 * 
	 * By calling this method, you will get the value of <i>currentValue</i>.
	 *
	 * @return The value of <i>currentValue</i>.
	 */
	public Double getCurrentValue() {
		return currentValue;
	}

	/**
	 * Setter method for the currentValue.
	 * 
	 * You can set the value of <i>currentValue</i> by calling this method.
	 *
	 * @param currentValue The new value for the attribute <i>currentValue</i>.
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}

	/**
	 * The method <b>calculate</b>.
	 * <p>
	 * The method calculate takes two input parameter <b>Double</b> <i>value</i> and
	 * <b>char</b> <i>operator</i>. By passing the two parameter, you will calculate
	 * the new value and set it for <i>currentValue</i> as:
	 * <p>
	 * <i>currentValue</i> = <i>currentValue</i> <b>operator</b> <i>value</i>
	 *
	 * @param value    The value you want to add, substract, multiply or divide.
	 *                 Take care: it must be a <b>Double</b> value.
	 * @param operator The operator for the operation you want to use ('+', '-', '*'
	 *                 or '/'):
	 * 
	 *                 <br>
	 *                 <table border="1">
	 *                 <caption>&nbsp;</caption> <thead>
	 *                 <tr>
	 *                 <th>+</th>
	 *                 <th>Addition</th>
	 *                 </tr>
	 *                 </thead> <tbody>
	 *                 <tr>
	 *                 <td>-</td>
	 *                 <td><b>Subtraction</b></td>
	 *                 </tr>
	 *                 <tr>
	 *                 <td>*</td>
	 *                 <td><b>Multiplication</b></td>
	 *                 </tr>
	 *                 <tr>
	 *                 <td>/</td>
	 *                 <td><b>Division</b></td>
	 *                 </tr>
	 *                 </tbody>
	 *                 </table>
	 * 
	 * 
	 * @throws DivisionByZeroException If you try to divide with zero (0), the
	 *                                 method will throw the
	 *                                 <i>DivisionByZeroException</i>.
	 * @see DivisionByZeroException
	 * @throws NotSupportedOperationException If the operator is not from the set
	 *                                        ('+', '-', '*', '/'), the method will
	 *                                        throw
	 *                                        <i>NotSupportedOperationException</i>.
	 * @see NotSupportedOperationException
	 */
	public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {

		switch (operator) {
		case '+':
			currentValue += value;
			break;

		case '-':
			currentValue -= value;
			break;

		case '*':
			currentValue *= value;
			break;

		case '/':
			if (value != 0) {
				currentValue /= value;
			} else {
				throw new DivisionByZeroException("Dijeljenje sa nulom!");
			}
			break;

		default:
			throw new NotSupportedOperationException("Nepoznata operacija.");
		}

	}

}
