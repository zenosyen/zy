import equation.AddEquation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddEquationTest {

    @Test
    void testAddEquationCalculation() {
        // 创建一个加法计算实例
        AddEquation addEquation = new AddEquation((short) 5, (short) 7);
        // 断言计算结果是否正确
        assertEquals(12, addEquation.calculate(), "Addition calculation is incorrect");
    }

    @Test
    void testAddEquationToString() {
        // 创建一个加法计算实例
        AddEquation addEquation = new AddEquation((short) 3, (short) 4);
        // 断言字符串是否正确
        assertEquals("3 + 4 = ", addEquation.toString(), "Addition toString method is incorrect");
    }

    @Test
    void testAddEquationBuilder() {
        // 创建一个加法计算实例的构建器
        AddEquation.AddEquationBuilder builder = new AddEquation.AddEquationBuilder();
        // 设置加法计算实例的参数
        AddEquation addEquation = builder.setOperand1((short) 2).setOperand2((short) 3).build();
        // 断言计算结果是否正确
        assertEquals(5, addEquation.calculate(), "AddEquationBuilder does not build correctly");
    }

    @Test
    void testAddEquationEquality() {
        // 创建三个加法计算实例
        AddEquation addEquation1 = new AddEquation((short) 5, (short) 7);
        AddEquation addEquation2 = new AddEquation((short) 5, (short) 7);
        AddEquation addEquation3 = new AddEquation((short) 3, (short) 4);

        // 断言三个加法计算实例是否相等
        assertEquals(addEquation1, addEquation2, "equation.AddEquation equality check fails for equal equations");
        assertNotEquals(addEquation1, addEquation3, "equation.AddEquation equality check fails for different equations");
    }

    @Test
    void testAddEquationHashCode() {
        // 创建三个加法计算实例
        AddEquation addEquation1 = new AddEquation((short) 5, (short) 7);
        AddEquation addEquation2 = new AddEquation((short) 5, (short) 7);
        AddEquation addEquation3 = new AddEquation((short) 3, (short) 4);

        // 断言三个加法计算实例的hashCode是否一致
        assertEquals(addEquation1.hashCode(), addEquation2.hashCode(), "equation.AddEquation hashCode is not consistent for equal equations");
        assertNotEquals(addEquation1.hashCode(), addEquation3.hashCode(), "equation.AddEquation hashCode is not consistent for different equations");
    }
}