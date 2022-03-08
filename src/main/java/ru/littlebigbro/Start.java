package main.java.ru.littlebigbro;

import main.java.ru.littlebigbro.GUI.GUI;

public class Start {
    public static void main(String[] args) {
        Runnable app = GUI::init;
        Thread thread = new Thread(app);
        thread.start();
    }
}
