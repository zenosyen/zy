package equation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EquationCollection implements Iterable<IEquation> {
    public Set<IEquation> equations;

    public EquationCollection() {
        equations = new HashSet<>();
    }

    public void generate(int n, EquationChecker checker) {
        // 创建一个EquationFactory对象
        EquationFactory equationFactory = new EquationFactory();
        while (equations.size() < n) {
            IEquation equation = equationFactory.getEquationRandom();

            // 检查算式是否符合约束条件
            if (checker.check(equation)) {
                equations.add(equation);
            }
        }

    }

    @Override
    public Iterator<IEquation> iterator() {
        return equations.iterator();
    }
}
