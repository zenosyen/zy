package equation;

public interface IEquation {
    short getOperand1();

    short getOperand2();

    char getOperator();

    void setOperand1(short operand1);

    void setOperand2(short operand2);

    void setOperator(char operator);

    int calculate();

    int getResult();
}
