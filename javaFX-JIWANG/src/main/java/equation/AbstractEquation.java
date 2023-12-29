package equation;

import java.util.Objects;

abstract class AbstractEquation implements IEquation {
    // 操作数1
    private short operand1;
    // 操作数2
    private short operand2;
    // 运算符
    private char operator;

    private int result;

    public AbstractEquation(short operand1, short operand2, char operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    public AbstractEquation() {
    }

    // 获取操作数1
    @Override
    public short getOperand1() {
        return operand1;
    }

    // 设置操作数1
    @Override
    public void setOperand1(short operand1) {
        this.operand1 = operand1;
    }

    // 获取操作数2
    @Override
    public short getOperand2() {
        return operand2;
    }

    // 设置操作数2
    @Override
    public void setOperand2(short operand2) {
        this.operand2 = operand2;
    }

    // 获取运算符
    @Override
    public char getOperator() {
        return operator;
    }

    // 设置运算符
    @Override
    public void setOperator(char operator) {
        this.operator = operator;
    }

    @Override
    public int getResult() { return result; }



    // 重写equals、hashCode、toString
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IEquation equation = (IEquation) obj;
        return operand1 == equation.getOperand1() &&
                operand2 == equation.getOperand2() &&
                operator == equation.getOperator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand1, operand2, operator);
    }

    @Override
    public String toString() {
        return String.format("%d %c %d", getOperand1(), getOperator(), getOperand2());
    }
}
