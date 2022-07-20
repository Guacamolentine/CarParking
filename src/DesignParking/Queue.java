package DesignParking;

/**
 * Класс очереди, который определяет метод постановки в очередь, снятия с очереди
 * и вывода количества элементов в команде;
 * @param <CarInfo>
 */

public class Queue<CarInfo> {
    private static final int QUEUE_CAPACITY = 1000;
    private Object[] elementData;
    private int rear;
    private int front;

    public Queue() {
        this.elementData = new Object[QUEUE_CAPACITY];
        this.rear = -1;
        this.front = -1;
    }

    public void push(CarInfo data) {
        if (this.rear + 1 >= QUEUE_CAPACITY) {
            throw new RuntimeException("Queue full, registration failed!");
        }
        ++rear;
        this.elementData[rear] = data;
    }

    public CarInfo pop() {
        if (isEmpty()) {
            throw new RuntimeException("The queue is empty");
        }
        ++front;
        return (CarInfo) this.elementData[front];
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public int length() {
        return rear - front;
    }
}
