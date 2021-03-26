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
@DisplayName("Testing the CalculatorAdvanced class.")
class CalculatorAdvancedTest {
	
	private static CalculatorAdvanced calculatorAdvanced;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		calculatorAdvanced = new CalculatorAdvanced();
	}
	
	@DisplayName("Testing if the initial value is zero.")
	@Order(1)
	@Test
	void testGetCurrentValue() {
		assertThat(calculatorAdvanced.getCurrentValue(), is(0.0));
	}
	
	@DisplayName("Testing the calculateAdvanced method of the CalculatorAdvanced class (without exceptions).")
	@Order(2)
	@ParameterizedTest
    @MethodSource("calculatorAdvancedTestCalculatAdvanced1")
	public void calculatorAdvancedTestCalculatAdvancedNoException(Double currentValue, char operator, Double value, char action, Double result) throws Exception {
		calculatorAdvanced.setCurrentValue(currentValue);
		calculatorAdvanced.calculate(value, operator);
		calculatorAdvanced.calculateAdvanced(action);
		assertThat(calculatorAdvanced.getCurrentValue(), is(result));
		
	}
	
	private static Stream<Arguments> calculatorAdvancedTestCalculatAdvanced1()
    {
        return Stream.of(
                Arguments.of(0.0, '+', 5.0, '2', 25.0),
                Arguments.of(25.0, '*', 0.0, '5', 0.0),
                Arguments.of(0.0, '+', 10.0, '3', 1000.0),
                Arguments.of(1000.0, '*', 0.0, '0', 1.0),
                Arguments.of(1.0 , '-', 5.0, '2', 16.0),
                Arguments.of(16.0 , '/', 2.0, '2', 64.0),
                Arguments.of(64.0, '*', 0.0, '9', 0.0),
                Arguments.of(0.0, '+', 5.0, '1', 5.0),
                Arguments.of(5.0, '+', 0.0, '!', 120.0),
                Arguments.of(120.0, '-', 120.0, '1', 0.0),
                Arguments.of(0.0, '+', 0.0, '!', 1.0),
                Arguments.of(1.0, '+', 7.0, '1', 8.0),
                Arguments.of(8.0, '+', 0.0, '!', 40320.0),
                Arguments.of(0.0, '+', 10.0, '!', 3628800.0)
                
        );
    }
	
	@DisplayName("Testing the calculateAdvanced method of the CalculatorAdvanced class (with NotSupportedOperationException).")
	@Order(3)
	@ParameterizedTest
    @MethodSource("calculatorAdvancedTestCalculatAdvanced2")
	public void calculatorAdvancedTestCalculatAdvancedNSOException(char action) throws Exception {
		 Exception exception = assertThrows(
	        		NotSupportedOperationException.class, 
		            () -> calculatorAdvanced.calculateAdvanced(action));
	     assertThat(exception, is(instanceOf(NotSupportedOperationException.class)));
	}
	
	private static Stream<Arguments> calculatorAdvancedTestCalculatAdvanced2()
    {
        return Stream.of(
                Arguments.of('J'),
                Arguments.of('o'),
                Arguments.of('v'),
                Arguments.of('a'),
                Arguments.of('n'),
                Arguments.of(':'),
                Arguments.of(')')
               
        );
    }
	
	@DisplayName("Testing the calculateAdvanced method of the CalculatorAdvanced class (with NumberNotInAreaException).")
	@Order(4)
	@ParameterizedTest
    @MethodSource("calculatorAdvancedTestCalculatAdvanced3")
	public void calculatorAdvancedTestCalculatAdvancedNNIAException(Double value, char action) throws Exception {
		calculatorAdvanced.setCurrentValue(value);
		Exception exception = assertThrows(
				NumberNotInAreaException.class, 
	            () -> calculatorAdvanced.calculateAdvanced(action));
		assertThat(exception, is(instanceOf(NumberNotInAreaException.class)));
	}
	
	private static Stream<Arguments> calculatorAdvancedTestCalculatAdvanced3()
    {
        return Stream.of(
                Arguments.of(-1.0, '!'),
                Arguments.of(11.0, '!'),
                Arguments.of(100.0, '!'),
                Arguments.of(10.1, '!'),
                Arguments.of(-100.0, '!')
               
        );
    }
	
	@DisplayName("Testing the hasCharacteristic method of the CalculatorAdvanced class (without exceptions).")
	@Order(5)
	@ParameterizedTest
    @MethodSource("calculatorAdvancedTestHasCharacteristic1")
	public void calculatorAdvancedTestHasCharacteristicNoException(Double currentValue, char value, boolean expected) throws Exception {
		
		calculatorAdvanced.setCurrentValue(currentValue);
		assertThat(calculatorAdvanced.hasCharacteristic(value), is(expected));
	}
	
	private static Stream<Arguments> calculatorAdvancedTestHasCharacteristic1()
    {
        return Stream.of(
                Arguments.of(153.0, 'A', true),
                Arguments.of(11.11, 'A', false),
                Arguments.of(100.1, 'A', false),
                Arguments.of(370.0, 'A', true),
                Arguments.of(371.0, 'A', true),
                
                Arguments.of(6.0, 'P', true),
                Arguments.of(7.0, 'P', false),
                Arguments.of(99.0, 'P', false),
                Arguments.of(28.0, 'P', true),
                Arguments.of(496.0, 'P', true)
               
        );
    }
	
	@DisplayName("Testing the hasCharacteristic method of the CalculatorAdvanced class (with NumberNotInAreaException).")
	@Order(6)
	@ParameterizedTest
    @MethodSource("calculatorAdvancedTestHasCharacteristic2")
	public void calculatorAdvancedTestHasCharacteristicNNIAException(Double currentValue, char value) throws Exception {
		calculatorAdvanced.setCurrentValue(currentValue);
		Exception exception = assertThrows(
				NumberNotInAreaException.class, 
	            () -> calculatorAdvanced.hasCharacteristic(value));
		assertThat(exception, is(instanceOf(NumberNotInAreaException.class)));
	}
	
	private static Stream<Arguments> calculatorAdvancedTestHasCharacteristic2()
    {
        return Stream.of(
                Arguments.of(0.0, 'A'),
                Arguments.of(0.2, 'A'),
                Arguments.of(-50.0, 'A'),
                Arguments.of(0.001, 'A'),
                Arguments.of(1.0, 'A'),
                
                Arguments.of(0.0, 'P'),
                Arguments.of(0.003, 'P'),
                Arguments.of(1.1, 'P'),
                Arguments.of(-28.0, 'P'),
                Arguments.of(0.496, 'P')
               
        );
    }
	
	@DisplayName("Testing the hasCharacteristic method of the CalculatorAdvanced class (with NotSupportedOperationException).")
	@Order(7)
	@ParameterizedTest
    @MethodSource("calculatorAdvancedTestHasCharacteristic3")
	public void calculatorAdvancedTestHasCharacteristicNSOException(Double currentValue, char value) throws Exception {
		
		calculatorAdvanced.setCurrentValue(currentValue);
		Exception exception = assertThrows(
				NotSupportedOperationException.class, 
	            () -> calculatorAdvanced.hasCharacteristic(value));
		assertThat(exception, is(instanceOf(NotSupportedOperationException.class)));
	}
	
	private static Stream<Arguments> calculatorAdvancedTestHasCharacteristic3()
    {
        return Stream.of(
                Arguments.of(10.0, 'J'),
                Arguments.of(10.2, 'o'),
                Arguments.of(11.0, 'v'),
                Arguments.of(30.001, 'a'),
                Arguments.of(331.0, 'n')
               
        );
    }


}
