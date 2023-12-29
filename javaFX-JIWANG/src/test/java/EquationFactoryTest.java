import equation.EquationFactory;
import equation.IEquation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EquationFactoryTest {
    @Test
    void testAddEquation() {
        // 创建一个EquationFactory对象
        EquationFactory equationFactory = new EquationFactory();
        // 获取一个加法式子，参数为5和3
        IEquation addEquation = equationFactory.getEquation("Add", (short) 5, (short) 3);
        // 断言计算结果为8
        assertEquals(8, addEquation.calculate());
    }

    @Test
    void testSubEquation() {
        // 创建一个EquationFactory对象
        EquationFactory equationFactory = new EquationFactory();
        // 获取一个减法式子，参数为8和2
        IEquation subEquation = equationFactory.getEquation("Sub", (short) 8, (short) 2);
        // 断言计算结果为6
        assertEquals(6, subEquation.calculate());
    }

    @Test
    void testRandomEquation() {
        // 创建一个EquationFactory对象
        EquationFactory equationFactory = new EquationFactory();
        // 获取一个随机式子
        IEquation randomEquation = equationFactory.getEquationRandom();
        // 断言随机式子不为空
        assertNotNull(randomEquation);
        // 打印随机式子
        System.out.println("Random Equation: " + randomEquation.toString());
    }
}
