import equation.AddEquation;
import equation.EquationCheckerOfRange;
import equation.SubEquation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EquationCheckerOfRangeTest {

    @Test
    void testCheckWithinRange() {
        // 创建一个检查范围为0-100的EquationCheckerOfRange对象
        EquationCheckerOfRange checker = new EquationCheckerOfRange(0, 100);
        // 检查20+30是否在范围内
        assertTrue(checker.check(new AddEquation((short) 20, (short) 30)), "Equation within range should pass");
        // 检查50-20是否在范围内
        assertTrue(checker.check(new SubEquation((short) 50, (short) 20)), "Equation within range should pass");
    }

    @Test
    void testCheckOutsideRange() {
        // 创建一个检查范围为0-100的EquationCheckerOfRange对象
        EquationCheckerOfRange checker = new EquationCheckerOfRange(0, 100);
        // 检查120+30是否在范围内
        assertFalse(checker.check(new AddEquation((short) 120, (short) 30)), "Equation outside range should fail");
        // 检查10-80是否在范围内
        assertFalse(checker.check(new SubEquation((short) 10, (short) 80)), "Equation outside range should fail");
    }

    @Test
    void testCheckAtBoundary() {
        // 创建一个检查范围为0-100的EquationCheckerOfRange对象
        EquationCheckerOfRange checker = new EquationCheckerOfRange(0, 100);
        // 检查0+100是否在范围内
        assertTrue(checker.check(new AddEquation((short) 0, (short) 100)), "Equation at boundary should pass");
        // 检查100-101是否在范围内
        assertFalse(checker.check(new SubEquation((short) 100, (short) 101)), "Equation at boundary should fail");
    }

    @Test
    void testCheckNegativeRange() {
        // 创建一个检查范围为-50-50的EquationCheckerOfRange对象
        EquationCheckerOfRange checker = new EquationCheckerOfRange(-50, 50);
        // 检查-20+30是否在范围内
        assertTrue(checker.check(new AddEquation((short) -20, (short) 30)), "Equation within negative range should pass");
        // 检查-60-30是否在范围内
        assertFalse(checker.check(new SubEquation((short) -60, (short) -30)), "Equation outside negative range should fail");
    }


}
