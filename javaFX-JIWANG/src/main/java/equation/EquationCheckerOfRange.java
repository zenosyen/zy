package equation;

// 添加中文注释后
public class EquationCheckerOfRange implements EquationChecker {
    private int min;
    private int max;

    public EquationCheckerOfRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean check(IEquation equation) {
        int result = equation.calculate();
        // 检查操作数1和2的取值范围，以及结果的取值范围
        return equation.getOperand1() >= min && equation.getOperand1() <= max &&
                equation.getOperand2() >= min && equation.getOperand2() <= max &&
                result >= min && result <= max;
    }
}
