package p20200325;

interface Stack{
    boolean isEmpty();
    boolean isFull();
    void push(char item);
    char pop();
    char peek();
    void clear();
}
 
public class ArrayStack implements Stack {
    
    private int top;
    private int stackSize;
    private char stackArr[];
 
    // ������ �����ϴ� ������
    public ArrayStack(int stackSize) {
        top = -1;    // ���� ������ �ʱ�ȭ
        this.stackSize = stackSize;    // ���� ������ ����
        stackArr = new char[this.stackSize];    // ���� �迭 ����
    }
    
    // ������ ����ִ� �������� Ȯ��
    public boolean isEmpty() {
        // ���� �����Ͱ� -1�� ��� �����Ͱ� ���� �����̹Ƿ� true �ƴ� ��� false�� return
        return (top == -1);
    }
    
    // ������ ������ �������� Ȯ��
    public boolean isFull() {
        // ���� �����Ͱ� ������ ������ �ε����� ������ ��� true �ƴ� ��� false�� return
        return (top == this.stackSize-1);
    }
    
    // ���ÿ� �����͸� �߰�
    public void push(char item) {
        if(isFull()) {
            System.out.println("Stack is full!");
        } else {             
            stackArr[++top] = item;    // ���� ���� �����Ͱ� ����Ű�� �ε����� ������ �߰�
            System.out.println("Inserted Item : " + item);
        }
    }
    
    // ������ �ֻ���(������) ������ ���� �� ����
    public char pop() {
        if(isEmpty()) {
            System.out.println("Deleting fail! Stack is empty!");
            return 0;
        } else { 
            System.out.println("Deleted Item : " + stackArr[top]);
            return stackArr[--top];
        }                
    }
    
    // ������ �ֻ���(������) ������ ����
    public char peek() {
        if(isEmpty()) {
            System.out.println("Peeking fail! Stack is empty!");
            return 0;
        } else { 
            System.out.println("Peeked Item : " + stackArr[top]);
            return stackArr[top];
        }
    }
    
    // ���� �ʱ�ȭ
    public void clear() {
        if(isEmpty()) {
            System.out.println("Stack is already empty!");
        } else {
            top = -1;    // ���� ������ �ʱ�ȭ
            stackArr = new char[this.stackSize];    // ���ο� ���� �迭 ����
            System.out.println("Stack is clear!");
        }
    }
    
    // ���ÿ� ����� ��� �����͸� ���
    public void printStack() {
        if(isEmpty()) {
            System.out.println("Stack is empty!");
        } else {
            System.out.print("Stack elements : ");
            for(int i=0; i<=top; i++) {
                System.out.print(stackArr[i] + " ");
            }
            System.out.println();
        }
    }
 
    public static void main(String args[]) {
        int stackSize = 5;
        ArrayStack arrStack = new ArrayStack(stackSize);
        
        arrStack.push('A');
        arrStack.printStack();
        
        arrStack.push('B');
        arrStack.printStack();
        
        arrStack.push('C');
        arrStack.printStack();
        
        arrStack.pop();
        arrStack.printStack();
        
        arrStack.pop();
        arrStack.printStack();
        
        arrStack.peek();
        arrStack.printStack();
        
        arrStack.clear();
        arrStack.printStack();
    }
    
}