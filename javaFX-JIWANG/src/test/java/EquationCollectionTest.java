
import equation.EquationChecker;
import equation.EquationCollection;
import equation.IEquation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquationCollectionTest {
    @Test
    void testEquationCollectionGeneration() {
        // 创建一个EquationCollection对象
        EquationCollection equationCollection = new EquationCollection();
        // 创建一个EquationChecker对象，用于检查Equation
        EquationChecker checker = new EquationChecker() {
            @Override
            public boolean check(IEquation equation) {
                // 在这个例子中，所有的Equation都接受
                return true;
            }
        };

        // 使用提供的检查器生成5个Equation
        equationCollection.generate(5, checker);

        // 确保集合中包含5个Equation
        assertEquals(5, equationCollection.equations.size());
    }

    @Test
    void testEquationCollectionIteration() {
        // 创建一个EquationCollection对象
        EquationCollection equationCollection = new EquationCollection();
        // 创建一个EquationChecker对象，用于检查Equation
        EquationChecker checker = new EquationChecker() {
            @Override
            public boolean check(IEquation equation) {
                // 在这个例子中，所有的Equation都接受
                return true;
            }
        };

        // 使用提供的检查器生成3个Equation
        equationCollection.generate(3, checker);

        // 使用迭代器遍历EquationCollection
        int count = 0;
        for (IEquation equation : equationCollection) {
            // 确保Equation不为空
            assertNotNull(equation);
            count++;
        }

        // 确保迭代器遍历了3个Equation
        assertEquals(3, count);
    }

    @Test
    void testEquationCollectionWithChecker() {
        // 创建一个EquationCollection对象
        EquationCollection equationCollection = new EquationCollection();
        // 创建一个EquationChecker对象，用于检查Equation
        EquationChecker checker = new EquationChecker() {
            @Override
            public boolean check(IEquation equation) {
                // 在这个例子中，只有结果大于0的Equation被接受
                return equation.getResult() > 0;
            }
        };

        // 使用提供的检查器生成5个Equation
        equationCollection.generate(5, checker);

        // 确保集合中的Equation结果大于0
        for (IEquation equation : equationCollection) {
            assertTrue(equation.getResult() > 0);
        }
    }
}
