package com.vortexel.cwdlauncher;

import java.io.File;
import java.io.IOException;

/**
 * Created by bindernews on 1/18/2016.
 */
public class LaunchConfig {

    private String name;
    private String[] command;

    public LaunchConfig(String name, String... command) {
        this.name = name;
        this.command = command;
    }

    public void launch(File workingDirectory) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(workingDirectory);
        pb.start();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
