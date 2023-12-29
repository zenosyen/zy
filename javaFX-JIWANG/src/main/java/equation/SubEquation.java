package equation;

public class SubEquation extends AbstractEquation {
    public SubEquation(short operand1, short operand2) {
        super(operand1, operand2, '-');
    }

    @Override
    public int calculate() {
        return getOperand1() - getOperand2();
    }

    @Override
    public int getResult(){ return calculate();}

    public static class SubEquationBuilder {
        private short operand1;
        private short operand2;

        public SubEquationBuilder setOperand1(short operand1) {
            this.operand1 = operand1;
            return this;
        }

        public SubEquationBuilder setOperand2(short operand2) {
            this.operand2 = operand2;
            return this;
        }

        public SubEquation build() {
            return new SubEquation(operand1, operand2);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " = " ;
    }


}
