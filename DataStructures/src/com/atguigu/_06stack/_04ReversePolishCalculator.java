package com.atguigu._06stack;

import netscape.security.UserTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式计算过程：
 *      ①取元素
 *      ②判断为操作数还是运算符
 *      ③为操作数，直接存进栈
 *      ④为运算符，取出两个操作数做运算
 */
public class _04ReversePolishCalculator {
    public static void main(String[] args) {
        /*String rpn = "3 4 + 5 * 6 - 7 /";
        //将逆波兰表达式的每一个元素转成字符串存进ArrayList集合
        List<String> rpnElements = rpnToArrayList(rpn);
        System.out.println(rpnElements);
        //计算出该逆波兰表达式的值
        int result = calculate(rpnElements);
        System.out.println("逆波兰表达式的结果为" + result);*/


        String expression = "(30+4)*5-6+7/4+3-2/1";
        List<String> rpnElements = expressionToArrayList(expression);
        System.out.println(rpnElements);
        int res = calculate(rpnElements);
        System.out.println("结果为" + res);
    }

    /**
     * 将中缀表达式转换为后缀表达式，并用ArrayList装起来
     * @param expression
     * @return
     */
    private static List<String> expressionToArrayList(String expression) {
        //指向每一个字符的指针
        int index = 0;
        String str = "";
        //用来存放逆波兰表达式的元素
        List<String> list = new ArrayList<>();
        //暂存运算符的栈
        Stack<String> operators = new Stack<>();
        //遍历中缀字符串的每一个字符
        while (index < expression.length()) {
            //取出一个字符
            str = expression.substring(index, index + 1);
            if (str.matches("\\d+")) {  //字符是数字，若后面还是数字拼装成多位数，填到集合里
                //遍历之后的元素，如果是多位数，组建成多位数
                while (index < (expression.length()-1)) {
                    String next = expression.substring(index + 1, index + 2);
                    if (next.matches("\\d+")) {
                        str += next;
                        index++;
                    } else {
                        break;
                    }
                }
                list.add(str);
            } else if ("(".equals(str)) {   //字符是左括号，直接入栈
                operators.push("(");
            } else if (")".equals(str)) {   //字符是右括号，一直出栈将运算符添加到集合里，直至弹出的是左括号结束
                while (true) {
                    String pop = operators.pop();
                    if ("(".equals(pop)) {
                        break;
                    }
                    list.add(pop);
                }
            } else {    //字符是运算符
                //若运算符栈不为空、栈顶元素不是左括号、且此字符优先级小于等于栈顶运算符的优先级，将栈运算符出栈添加到集合中，以保证先运算左边的优先级高及同级的运算符
                while (!operators.isEmpty() && !"(".equals(operators.peek()) && OperatorUtils.priority(str) <= OperatorUtils.priority(operators.peek())) {
                    String pop = operators.pop();
                    list.add(pop);
                }
                //保证了上述条件的反条件后，将运算符入栈
                operators.push(str);
            }
            //扫描下一个字符
            index++;
        }
        //中缀表达式扫描完毕，将栈中剩余的运算符依次加入集合
        while (!operators.isEmpty()) {
            String pop = operators.pop();
            list.add(pop);
        }
        //正确的后缀表达式对应的集合
        return list;
    }

    /**
     * 逆波兰计算器
     * @param rpnElements
     * @return
     */
    private static int calculate(List<String> rpnElements) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        //遍历逆波兰的集合
        for (String rpnElement : rpnElements) {
            if (rpnElement.matches("\\d+")) {
                //是数字，直接入栈
                stack.push(Integer.parseInt(rpnElement));
            } else {
                //是运算符，弹出两个数，运算
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                if ("+".equals(rpnElement)) {
                    result = num2 + num1;
                } else if ("-".equals(rpnElement)) {
                    result = num2 - num1;
                } else if ("*".equals(rpnElement)) {
                    result = num2 * num1;
                } else if ("/".equals(rpnElement)) {
                    result = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //此轮运算结束，将结果入栈
                stack.push(result);
            }
        }
        return result;
    }

    /**
     * 将一个逆波兰表达式字符串的每一个元素装进ArrayList中
     * @param rpn
     * @return
     */
    private static List<String> rpnToArrayList(String rpn) {
        List<String> list = new ArrayList<>();
        String[] elements = rpn.split(" ");
        for (String element : elements) {
            list.add(element);
        }
        return list;
    }
}

/**
 * 运算符优先级工具类
 */
class OperatorUtils {
    public static int priority(String str) {
        switch (str) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return -1;
        }
    }
}