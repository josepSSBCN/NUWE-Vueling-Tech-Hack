package org.hackVueling;

import org.hackVueling.view.MainViewCls;

public class Main {
    public static void main(String[] args) {
        MainViewCls MainViewCls = new MainViewCls();

        try {
            MainViewCls.WelcomeScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MainViewCls.MainMenu();

        MainViewCls.ExitScreen();

    }
}