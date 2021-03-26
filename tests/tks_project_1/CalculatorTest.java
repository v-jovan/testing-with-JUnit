package tks_project_1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Testing the Calculator class.")
class CalculatorTest {
		
	private static Calculator calculator;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		calculator = new Calculator();
		
	}
			
	@DisplayName("Testing if the initial value is zero.")
	@Order(1)
	@Test
	void testGetCurrentValue() {
		assertThat(calculator.getCurrentValue(), is(0.0));
	}
	
	@Order(2)
	@DisplayName("Testing the setter and getter of the Calculator class.")
	@ParameterizedTest
    @MethodSource("calculatorTestSetGetCurrentValue")
	public void testSetGetCurrentValue(Double testValue) {
		calculator.setCurrentValue(testValue);
		assertThat(calculator.getCurrentValue(), is(testValue));
		
	}
	
	private static Stream<Arguments> calculatorTestSetGetCurrentValue()
    {
        return Stream.of(
                Arguments.of(1.2),
                Arguments.of(0.0),
                Arguments.of(-17.3),
                Arguments.of(8888.888)
        );
    }
	
	@Order(3)
	@DisplayName("Testing the calculate method of the Calculator class (without exceptions).")
	@ParameterizedTest
    @MethodSource("calculatorTestCalculateNoException")
	public void testCalculateWithoutException(Double currentValue, char operator, Double value, Double result) throws DivisionByZeroException, NotSupportedOperationException {
		calculator.setCurrentValue(currentValue);
		calculator.calculate(value, operator);
		assertThat(calculator.getCurrentValue(), is(result));
	}
	
	private static Stream<Arguments> calculatorTestCalculateNoException()
    {
        return Stream.of(
                Arguments.of(0.0, '+', 1.2, 1.2),
                Arguments.of(1.2, '+', 1.2, 2.4),
                Arguments.of(2.4, '/', 2.0, 1.2),
                Arguments.of(1.2, '*', 0.0, 0.0),
                Arguments.of(0.0, '-', (-17.3), 17.3),
                Arguments.of(17.3, '*', 2.0, 34.6)
                
        );
    }

	@Order(4)
	@DisplayName("Testing the calculate method of the Calculator class (DivisionByZeroException).")
	@ParameterizedTest
    @MethodSource("calculatorTestCalculateException1")
	public void testCalculateWithDBZException(Double value, char operator) throws DivisionByZeroException {
		Exception exception = assertThrows(
				DivisionByZeroException.class, 
				() -> calculator.calculate(value, operator));
		assertThat(exception, is(instanceOf(DivisionByZeroException.class)));
	}
	
	private static Stream<Arguments> calculatorTestCalculateException1()
    {
        return Stream.of(
                Arguments.of(0.0, '/')
        );
    }
	
	@Order(5)
	@DisplayName("Testing the calculate method of the Calculator class (NotSupportedOperationException).")
	@ParameterizedTest
    @MethodSource("calculatorTestCalculateException2")
	public void testCalculateWithNSOException(Double value, char operator) throws NotSupportedOperationException{
		Exception exception = assertThrows(
        		NotSupportedOperationException.class, 
	            () -> calculator.calculate(value, operator));
        assertThat(exception, is(instanceOf(NotSupportedOperationException.class)));
	}
	
	private static Stream<Arguments> calculatorTestCalculateException2()
    {
        return Stream.of(
                Arguments.of(1.1, '%'),
                Arguments.of(0.0, '%'),
                Arguments.of(3.3, '"'),
                Arguments.of(1111.999, 'p')
        );
    }
}
