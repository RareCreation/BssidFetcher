package com.rare.wifivendor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BssidFetcher {

    private static final Logger logger = Logger.getLogger(BssidFetcher.class.getName());

    private static final Pattern BSSID_PATTERN = Pattern.compile("(?:BSSID\\s+:|Connected to)\\s+([0-9a-fA-F:]{17})");

    private static final Map<String, String> OS_COMMANDS = createOsCommands();

    private static Map<String, String> createOsCommands() {
        Map<String, String> map = new HashMap<>();
        map.put("win", "netsh wlan show interfaces");
        map.put("linux", "iw dev wlan0 link");
        return map;
    }

    public static String getBssid() {
        String os = System.getProperty("os.name").toLowerCase();

        String command = null;
        for (Map.Entry<String, String> entry : OS_COMMANDS.entrySet()) {
            if (os.contains(entry.getKey())) {
                command = entry.getValue();
                break;
            }
        }

        if (command == null) {
            logger.warning("Unsupported OS: " + os);
            return null;
        }

        try {
            return parseBssid(Runtime.getRuntime().exec(command));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error getting BSSID", e);
            return null;
        }
    }

    private static String parseBssid(Process process) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = BSSID_PATTERN.matcher(line);
                if (matcher.find()) {
                    return matcher.group(1).toLowerCase();
                }
            }
        }
        return null;
    }
}
