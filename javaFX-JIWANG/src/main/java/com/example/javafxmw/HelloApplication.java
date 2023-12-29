package com.example.javafxmw;

import SQL.MySQLDemo;
import equation.Generate;
import equation.IEquation;
import javafx.application.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static SQL.MySQLDemo.*;

public class HelloApplication extends Application {

    // 设置算式数量
    static final int NUMBER_OF_QUESTIONS = 10;


    private VBox equationBox;
    private Button checkAllButton;
    // 答案框
    private List<TextField> answerFields = new ArrayList<>();
    // 正确答案存放
    public static int[] correctAnswers = new int[NUMBER_OF_QUESTIONS];

    private Label scoreLabel = new Label("Your score: ");
    // 问题存放
    public static String[] Equations = new String[NUMBER_OF_QUESTIONS];
    // 正确答案数量
    static int corNumber;


    //连接数据库
    MySQLDemo mySQLDemo = new MySQLDemo();



    @Override
    public void start(Stage stage) throws IOException {



        Menu fileMenu = new Menu("点这里！！");
        //
        //
        //
        //

        MenuItem generateMenuItem = new MenuItem("生成习题");
        MenuItem saveMenuItem = new MenuItem("保存到题库");
        MenuItem readMenuItem = new MenuItem("读取题库");
        MenuItem checkMenuItem = new MenuItem("检查习题");
        MenuItem helpMenuItem = new MenuItem("帮助");
        MenuItem exitMenuItem = new MenuItem("退出");

        // 设置菜单栏
        fileMenu.getItems().addAll(generateMenuItem, saveMenuItem, readMenuItem, checkMenuItem, helpMenuItem, exitMenuItem);


        // 触发事件
        generateMenuItem.setOnAction(new EventHandler<ActionEvent>()
         {
            @Override
            public void handle(ActionEvent event) {
                // Handle generate menu item action

                System.out.println("Generate menu item clicked");
                generateAndDisplayEquations();
            }
        });

        saveMenuItem.setOnAction(new EventHandler<ActionEvent>()
         {
            @Override
            public void handle(ActionEvent event) {
                boolean confirmed = showConfirmationDialog("保存", "是否要保存?");
                if(confirmed) {
                    save();
                    System.out.println("Save menu item clicked");
                }
            }
        });

        readMenuItem.setOnAction(new EventHandler<ActionEvent>()
         {
            @Override
            public void handle(ActionEvent event) {
                // Handle read menu item action

                queryDatabase();
                System.out.println("Read menu item clicked");
            }
        });

        checkMenuItem.setOnAction(new EventHandler<ActionEvent>()
         {
            @Override
            public void handle(ActionEvent event) {
                checkEquationResult();
                showCheckDialog("Check");
                System.out.println("Check menu item clicked");
            }
        });

        helpMenuItem.setOnAction(new EventHandler<ActionEvent>()
         {
            @Override
            public void handle(ActionEvent event) {
                // Handle help menu item action
                System.out.println("Help menu item clicked");
                showInformationDialog("帮助", "查询帮助请联系 Zeno syne 2630520224@qq.com.");
            }
        });

        exitMenuItem.setOnAction(new EventHandler<ActionEvent>()
         {
            @Override
            public void handle(ActionEvent event) {
                // Handle exit menu item action
                System.out.println("Exit menu item clicked");
                boolean confirmed = showConfirmationDialog("退出", "是否要退出程序?");
                if (confirmed) {
                    closeConnection();
                    System.exit(0);
                }
            }
        });

        // 可以添加更多事件
        //
        //
        //
        //
        //
        //

        // 关闭窗口设置
        stage.setOnCloseRequest(event -> {
            // 在窗口关闭请求时处理关闭事件
            System.out.println("Window closed");
            closeConnection();
            Platform.exit(); // 停止应用程序运行
        });

        // 创建菜单栏
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);

        // 创建一个垂直布局的容器来放置方程
        equationBox = new VBox();
        equationBox.setSpacing(10);

        // 设置菜单栏和内容显示
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);// 菜单栏置顶
        borderPane.setCenter(equationBox);// 内容居中

        // 场景和舞台设置
        Scene scene = new Scene(borderPane, 1000, 800);
        stage.setTitle("口算习题薄");
        stage.setScene(scene);
        stage.show();

    }


    // 保存算式
    private static void save() {
        try {
            addEquation();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Save menu item clicked");
    }

    // 生成算式
    private void generateAndDisplayEquations() {

        //清空equationBox
        equationBox.getChildren().clear();

        //生成Equations
        List<IEquation> equations = Generate.extracted();

        //flag用于记录当前Equation的索引
        int flag = 0;

        //遍历equations，生成EquationLabel和AnswerField
        for (IEquation equation : equations) {
            //将Equation的计算结果赋值给correctAnswers
            correctAnswers[flag] = equation.getResult();
            //将Equation的字符串赋值给Equations
            Equations[flag] = equation.toString();
            //生成EquationLabel
            Label equationLabel = new Label(equation.getOperand1()+ " " + equation.getOperator() + " " + equation.getOperand2() + " = ");
            //生成AnswerField
            TextField answerField = new TextField();

            // int finalI = flag;

            //为AnswerField添加事件监听器，当按下键盘上的键时触发(只能输入数字)
            answerField.setOnKeyTyped(e -> handleAnswerFieldKeyPress(answerField));
            //将AnswerField添加到answerFields中
            answerFields.add(answerField);

            //将EquationLabel和AnswerField添加到equationBox中
            equationBox.getChildren().addAll(equationLabel, answerField);
            //flag自增
            flag++;
        }
        //将scoreLabel添加到equationBox中
        scoreLabel.setText("Your score: ");
        equationBox.getChildren().addAll(scoreLabel);
    }

    // 处理答案字段的按键事件(只能输入数字)
    private void handleAnswerFieldKeyPress(TextField answerField) {
        //如果答案字段的文本不匹配[0-9]*
        if (!answerField.getText().matches("[0-9]*")) {
            //删除上一个字符(只能输入数字)
            answerField.deletePreviousChar();
        }
    }

    // 获取用户答案
    private int getUserAnswer(int questionIndex) {
        //获取指定问题索引的答案输入框
        TextField answerField = answerFields.get(questionIndex);
        //获取答案输入框中的文本
        String answerText = answerField.getText();
        //如果答案文本为空，返回-1，否则将答案文本转换为整数并返回
        return answerText.isEmpty() ? -1 : Integer.parseInt(answerText);
    }
    // 检查用户答案
    private void checkEquationResult() {
        int correctCount = 0;
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
            int userAnswer = getUserAnswer(i);
            if (userAnswer == correctAnswers[i]) {
                correctCount++;
            }
        }
        // 直接使用 correctCount 作为分数
        scoreLabel.setText("Your score: " + correctCount + " / " + NUMBER_OF_QUESTIONS);
        corNumber = correctCount;
    }
    // 查询数据库
    private void queryDatabase() {
        Stage stage = new Stage();
        VBox dataVBox = new VBox();
        dataVBox.setSpacing(10);

        // 创建一个滚动面板
        ScrollPane scrollPane = new ScrollPane();

        try {
            // 从数据库中查询所有等式
            List<Map<String, Object>> equationsList = mySQLDemo.selectEquations();
            // 遍历查询到的等式
            for (Map<String, Object> equationMap : equationsList) {
                // 获取等式的id
                int id = (int) equationMap.get("id");
                // 获取等式的表达式
                String equation = (String) equationMap.get("equation");
                // 获取等式的结果
                String result = (String) equationMap.get("result");

                // 创建一个标签，显示等式的id、表达式和结果
                Label label = new Label("ID: " + id + ", Equation: " + equation + result);
                // 将标签添加到VBox中
                dataVBox.getChildren().add(label);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 设置ScrollPane的内容为VBox
        scrollPane.setContent(dataVBox);
        // 创建一个新的场景，将VBox作为场景的内容
        Scene dataScene = new Scene(scrollPane, 400, 300);
        // 设置场景为stage的场景
        stage.setScene(dataScene);
        stage.show();
    }

    // 设置Dialog弹窗
    // 需要确认类(是否保存，是否退出等)
    private boolean showConfirmationDialog(String title, String contentText) {
        // 创建一个确认框
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // 设置标题
        alert.setTitle(title);
        // 设置头部文本
        alert.setHeaderText(null);
        // 设置内容文本
        alert.setContentText(contentText);

        // 创建一个按钮类型，设置按钮文字和按钮数据
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);

        // 添加按钮
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // 显示确认框，并等待用户点击
        Optional<ButtonType> result = alert.showAndWait();
        // 返回用户点击的按钮类型，如果点击的是Yes按钮，返回true，否则返回false
        return result.orElse(ButtonType.NO) == buttonTypeYes;
    }
    // 显示信息类(提示，帮助等)
    private void showInformationDialog(String title, String contentText) {
        // 创建一个Alert对象
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // 设置标题
        alert.setTitle(title);
        // 设置头部文本
        alert.setHeaderText(null);
        // 设置内容文本
        alert.setContentText(contentText);
        // 显示并等待
        alert.showAndWait();
    }
    // 特殊类(显示一个图标)
    private void showCheckDialog(String title) {
        // 创建一个对话框
        Dialog<Void> checkDialog = new Dialog<>();
        // 设置对话框的标题
        checkDialog.setTitle(title);
        checkDialog.setHeaderText("检查结果：" + corNumber + " / " + NUMBER_OF_QUESTIONS);

        // 创建一个按钮，并设置按钮的类型为取消和关闭
        ButtonType closeButton = new ButtonType("关闭", ButtonBar.ButtonData.CANCEL_CLOSE);
        // 将按钮添加到对话框的按钮栏中
        checkDialog.getDialogPane().getButtonTypes().add(closeButton);

        // 创建一个可观察列表，用于存储饼图的数据
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("正确", corNumber),
                new PieChart.Data("错误", NUMBER_OF_QUESTIONS-corNumber)
        );
        // 创建一个饼图，并传入可观察列表
        PieChart pieChart = new PieChart(pieChartData);

        // 创建一个垂直布局，用于存放饼图
        VBox chartBox = new VBox(pieChart);
        // 设置布局中的间距
        chartBox.setSpacing(10);

        // 将布局设置为对话框的内容
        checkDialog.getDialogPane().setContent(chartBox);

        // 显示对话框，并等待用户点击按钮
        checkDialog.showAndWait();
    }



    public static void main(String[] args) {
        launch();
    }
}