package equation;

public class AddEquation extends AbstractEquation {
    public AddEquation() {
        super();
    }

    public AddEquation(short operand1, short operand2) {
        super(operand1, operand2, '+' );
    }

    @Override
    public int calculate() {
        return getOperand1() + getOperand2();
    }

    @Override
    public int getResult() {
        return calculate();
    }

    // 静态内部类
    public static class AddEquationBuilder {
        private short operand1;
        private short operand2;

        public AddEquationBuilder setOperand1(short operand1) {
            this.operand1 = operand1;
            return this;
        }

        public AddEquationBuilder setOperand2(short operand2) {
            this.operand2 = operand2;
            return this;
        }

        public AddEquation build() {
            return new AddEquation(operand1, operand2);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " = " ;
    }
}
