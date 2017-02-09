package com.vortexel.cwdlauncher;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    public static final Logger log = Logger.getLogger("CWDLauncher");

    public static void main(String[] args) {
        usePlatformLaF();

        try {
            ConfigLoader cfg = new ConfigLoader();
            cfg.load();

            MainUI ui = new MainUI();
            ui.setLaunchConfigs(cfg.getLaunchConfigs());
            ui.setVisible(true);
        } catch (IOException e) {
            // show the error to the user
            new ErrorFrame(e).setVisible(true);
        }
        System.out.println("Main.main");
    }



    private static void usePlatformLaF() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // It doesn't actually matter if this fails
            log.throwing(Main.class.getCanonicalName(), "usePlatformLaF", e);
        }
    }
}
