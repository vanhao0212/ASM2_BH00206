import java.util.Scanner;

class MyQueue<T> {
    private Object[] array;
    private int front, rear, size;
    private static final int DEFAULT = 5;

    public MyQueue() {
        this.array = new Object[DEFAULT];
    }

    public void enqueue(Object item) {
        if (size < DEFAULT) array[(rear++ % DEFAULT)] = item;
        else throw new IllegalStateException("Queue is full");
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        T item = (T) array[(front++ % DEFAULT)];
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}


class MyStack<T> {
    private Object[] array;
    private int size;
    private static final int DEFAULT = 5;

    public MyStack() {
        this.array = new Object[DEFAULT];
        this.size = 0;
    }

    public void push(T item) {
        if (size < DEFAULT) {
            System.out.println(size);
            array[size++] = item;
            System.out.println(size);
        } else {
            throw new IllegalStateException("Stack is full");
        }
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T item = (T) array[--size];
        array[size] = null; // Đặt tham chiếu thành null để tránh rò rỉ bộ nhớ
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}


class MessageSystem {
    private MyQueue<String> messageQueue = new MyQueue<>();
    private MyStack<String> messageStack = new MyStack<>();
    private Scanner scanner = new Scanner(System.in);

    public void sendMessage() {
        System.out.print("Enter a message (type 'exit' to quit): ");
        String message = scanner.nextLine();
        if (message.equalsIgnoreCase("exit")) {
            System.out.println("Exiting the program.");
            System.exit(0);
        }
        if (isValidMessage(message)) {
            messageQueue.enqueue(message);
            System.out.println("tin nhắn đã thêm thành công.");
        } else {
            System.out.println("tin nhắn không hợp lệ hãy nhập lại.");
        }
    }

    private boolean isValidMessage(String message) {
        // Kiểm tra xem tin nhắn có hợp lệ không
        return message.length() >= 3 && message.length() <= 100;
    }

    public void processMessages() {
        while (!messageQueue.isEmpty()) {
            String message = messageQueue.dequeue();
            messageStack.push(message);
            System.out.println("Processing message: " + message);
        }
    }

    public void printProcessedMessages() {
        System.out.println("Processed Messages (in reverse order):");
        while (!messageStack.isEmpty()) {
            String processedMessage = messageStack.pop();
            System.out.println(processedMessage);
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}

public class Main {
    public static void main(String[] args) {
        MessageSystem messageSystem = new MessageSystem();

        for (int i = 0; i < 5; i++) {
            messageSystem.sendMessage();
        }
        messageSystem.processMessages();
        messageSystem.printProcessedMessages();

        // Đóng Scanner khi không cần sử dụng nữa
        messageSystem.closeScanner();
    }
}