package com.vortexel.cwdlauncher;

import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ConfigLoader {

    private static final File CONFIG_FILE = new File("config.ini");
    private static final String CONFIG_SECTION = "config";

    private List<LaunchConfig> launchConfigs;
    private Ini iniData;

    public ConfigLoader() {
    }

    public void load() throws IOException {
        launchConfigs = new ArrayList<>();

        iniData = new Ini(CONFIG_FILE);

        // Parse launch configs
        ArrayList<String> commandBuffer = new ArrayList<>();
        for (String sectionName : iniData.keySet()) {
            // if it's not the reserved section then parse it
            if (!sectionName.equals(CONFIG_SECTION)) {
                // grab the section
                Profile.Section section = iniData.get(sectionName);
                // clear the command buffer
                commandBuffer.clear();
                // Add the initial command
                commandBuffer.add(section.get("command"));
                // Arg number counter
                int count = 1;
                while (true) {
                    String key = "arg" + count;
                    if (!section.containsKey(key)) {
                        break;
                    } else {
                        commandBuffer.add(section.get(key));
                    }
                    count++;
                }
                // We're done here
                LaunchConfig conf = new LaunchConfig(sectionName,
                        commandBuffer.toArray(new String[1]));
                launchConfigs.add(conf);
            }
        }
    }

    public List<LaunchConfig> getLaunchConfigs() {
        return launchConfigs;
    }
}
