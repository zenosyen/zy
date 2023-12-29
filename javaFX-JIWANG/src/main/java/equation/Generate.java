package equation;

import java.util.ArrayList;
import java.util.List;

public class Generate {

    //习题生成
    public static List<IEquation> extracted() {

        // 读入数目n
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("输入算式的数量 (n): ");
//        int n = scanner.nextInt();

        List<IEquation> equations = new ArrayList<>();
        // 创建 equation.EquationCheckerOfRange 实例，限定操作数及结果在0到100之间
        EquationChecker checker = new EquationCheckerOfRange(0, 100);

        // 创建 equation.EquationCollection 实例，生成 n 个满足条件的算式
        EquationCollection equationCollection = new EquationCollection();
        equationCollection.generate(10, checker);

        // 遍历算式并在终端输出
        System.out.println("\n生成的算式:");
        for (IEquation equation : equationCollection) {
            equations.add(equation);
            //打印算式
            System.out.println(equation);
        }
        return equations;
    }


}
