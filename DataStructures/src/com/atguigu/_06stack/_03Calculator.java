package com.atguigu._06stack;

/**
 * char和int可以相互转换。只要考虑好是否会有损失
 * 难度中等，都是细节
 *
 * 使用中缀表达式实现：相对比较复杂
 */
public class _03Calculator {
    public static void main(String[] args) {
        String expression = "60+2*30-5/3+1-10*11";
        ArrayStack2 operands = new ArrayStack2(100);//操作数栈
        ArrayStack2 operator = new ArrayStack2(100);//运算符栈，必须保证栈顶的优先级大于下面的。因为最后从上往下算
        int index = 0;//指向表达式的字符
        int num1 = 0;//弹出的第一个数字
        int num2 = 0;//弹出的第二个数字
        char curOperator = ' ';
        while (true) {
            //表达式为空或者index已指向最后一个元素的最后一个位置，结束
            if (expression.length() == 0 || index == expression.length()) {
                break;
            }
            char ch = expression.substring(index, index + 1).charAt(0);
            //判断是否是运算符
            if (operator.isOperator(ch)) {//是运算符
                /*
                    把此运算符加进运算符栈之前先将栈中存在且优先级大于等于该运算符的运算符运算掉
                 */
                while (true) {
                    //判断运算符栈是否为空
                    if (!operator.isEmpty()) {//不为空
                        //判断此运算符的等级是否大于运算符栈栈顶的运算符优先级，大于则直接入栈，小于等于则先将栈顶的运算符用掉
                        if (operator.priority(ch) <= operator.priority((char) operator.peek())) {
                            num1 = operands.pop();
                            num2 = operands.pop();
                            curOperator = (char) operator.pop();
                            operands.push(operator.operate(num1, num2, curOperator));
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                operator.push(ch);
            } else {//是操作数
                String curOperand = ch + "";
                while (true) {
                    //判断index是否指向最后一个字符
                    if (index != expression.length() - 1) {//不指向
                        char chNext = expression.substring(index + 1, index + 2).charAt(0);//下一个字符
                        //判断下一个字符是否仍是数字
                        if (!operator.isOperator(chNext)) {//是数字，拼接成数字
                            curOperand += chNext;
                            index++;
                        } else {//不是数字，添加到操作数栈
                            operands.push(Integer.parseInt(curOperand));
                            break;
                        }
                    } else {//指向最后一个字符，直接添加到操作数栈
                        operands.push(Integer.parseInt(curOperand));
                        break;
                    }
                }
            }
            index++;
        }
        //完成运算
        while (true) {
            if (operator.isEmpty()) {
                break;
            }
            num1 = operands.pop();
            num2 = operands.pop();
            curOperator = (char) operator.pop();
            operands.push(operator.operate(num1, num2, curOperator));
        }

        System.out.println("最终结果是" + operands.pop());
    }
}

class ArrayStack2 {
    private int maxsize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxsize) {
        this.maxsize = maxsize;
        stack = new int[maxsize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxsize - 1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满。。。");
            return;
        }
        stack[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空。。。");
        }
        return stack[top--];
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("栈空。。。");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈空。。。");
        }
        return stack[top];
    }

    public boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public int priority(char ch) {
        int priority;
        switch (ch) {
            case '+':
            case '-':
                priority = 0;
                break;
            case '*':
            case '/':
                priority = 1;
                break;
            default:
                priority = -1;
                break;
        }
        return priority;
    }

    public int operate(int num1, int num2, char ch) {
        int result = 0;
        switch (ch) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                throw new RuntimeException("运算符有误");
        }
        return result;
    }
}

