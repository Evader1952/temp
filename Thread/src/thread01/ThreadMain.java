package thread01;

public class ThreadMain {
    public static void main(String[] args) {


        for (int i = 0; i <20 ; i++) {

            System.out.println("主线程"+i);
        }
        MyThread myThread = new MyThread();
        myThread.start();

    }
}
