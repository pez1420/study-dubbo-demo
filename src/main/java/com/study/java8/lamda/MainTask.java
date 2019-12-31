package com.study.java8.lamda;

/**
 * @author pez1420@gmail.com
 * @version $Id: MainTask.java v 0.1 2019/8/27 4:41 PM pez1420 Exp $$
 */
public class MainTask {


    public static void main(String[] args) {
        MainTask mainTask = new MainTask();
        mainTask.printA();
        mainTask.printB();

    }


    void printB() {
        printData(() ->
                System.out.println("printB")
        );
    }

    void printA() {
        printData(() ->
                System.out.println("printA")
        );
    }

    public void printData(PrintTask printTask) {
        printTask.print();
        // 其他核心逻辑从各个业务函数中抽析，使业务代码更清晰更易维护
        // 避免重复性代码多次编写，精简重复函数越多收益越大
    }


    public interface PrintTask {
        void print();
    }


}
