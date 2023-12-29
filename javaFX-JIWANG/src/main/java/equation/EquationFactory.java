package equation;

import java.util.Random;

public class EquationFactory {
    private Random random = new Random();

    //获取随机等式
    public IEquation getEquation(String type, short operand1, short operand2) {
        if ("Add".equals(type)) {
            return new AddEquation.AddEquationBuilder()
                    .setOperand1(operand1)
                    .setOperand2(operand2)
                    .build();
        } else if ("Sub".equals(type)) {
            return new SubEquation.SubEquationBuilder()
                    .setOperand1(operand1)
                    .setOperand2(operand2)
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid equation type");
        }
    }

    public IEquation getEquationRandom() {
        // 随机生成两个 short 类型的数字
        short operand1 = (short) random.nextInt(101);
        short operand2 = (short) random.nextInt(101);

        // 随机生成一个字符，可以是 + 或者 -
        char operator = random.nextBoolean() ? '+' : '-';

        // 随机选择 equation.AddEquation 或 equation.SubEquation
        String type = random.nextBoolean() ? "Add" : "Sub";

        // 根据随机生成的参数创建相应类型的算式
        return getEquation(type, operand1, operand2);
    }
}
