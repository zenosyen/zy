
import equation.SubEquation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubEquationTest {
    @Test
    void testSubEquationCalculation() {
        // 创建一个SubEquation对象，参数为8和3
        SubEquation subEquation = new SubEquation((short) 8, (short) 3);
        // 断言计算结果为5
        assertEquals(5, subEquation.calculate());
    }

    @Test
    void testSubEquationBuilder() {
        // 创建一个SubEquation.SubEquationBuilder对象
        SubEquation.SubEquationBuilder builder = new SubEquation.SubEquationBuilder();
        // 设置参数1为10，参数2为4，并创建一个SubEquation对象
        SubEquation subEquation = builder.setOperand1((short) 10)
                .setOperand2((short) 4)
                .build();

        // 断言计算结果为6
        assertEquals(6, subEquation.calculate());
    }

    @Test
    void testSubEquationToString() {
        // 创建一个SubEquation对象，参数为7和2
        SubEquation subEquation = new SubEquation((short) 7, (short) 2);
        // 断言字符串为"7 - 2 = "
        assertEquals("7 - 2 = ", subEquation.toString());
    }

    @Test
    void testSubEquationResult() {
        // 创建一个SubEquation对象，参数为15和5
        SubEquation subEquation = new SubEquation((short) 15, (short) 5);
        // 断言结果为10
        assertEquals(10, subEquation.getResult());
    }

}
