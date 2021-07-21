package main.java.ru.littlebigbro;

public class Start {
    public static void main(String[] args) {
        Runnable app = new Runnable() {
            @Override
            public void run() {
                GUI.init();
            }
        };
        Thread thread = new Thread(app);
        thread.start();
    }
}
