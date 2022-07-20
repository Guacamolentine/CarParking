package DesignParking;

/**
 * Класс стека, который определяет метод push элемента, метод pop и метод длины выходного стека;
 */

public class Stack {
    private CarInfo S[];
    private int top;

    public Stack(int maxSize) {
        top = -1;
        S = new CarInfo[maxSize];
    }

    public void push(CarInfo x) throws Exception {
        if (S.length == top + 1) {
            throw new Exception("Stack is full");
        } else {
            top++;
            S[top] = x;
        }
    }

    public CarInfo pop() {
        if (top == -1) {
            return null;
        } else {
            return S[top--];
        }
    }

    public int gettop() {
        return top + 1;
    }
}
