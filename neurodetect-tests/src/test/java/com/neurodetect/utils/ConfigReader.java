package com.neurodetect.utils;

public class ConfigReader {

    // Default values used if no parameter or system property is provided
    private static final String DEFAULT_BASE_URL  = "http://localhost:5173";
    private static final String DEFAULT_BROWSER   = "chrome";
    private static final String DEFAULT_HEADLESS  = "false";

    // Explicit wait timeout (seconds) — used in WaitUtils
    public static final int EXPLICIT_WAIT_SECONDS = 15;

    // How long to pause after navigation to let React render (milliseconds)
    public static final int RENDER_WAIT_MS = 500;

    private ConfigReader() {}

    public static String getBaseUrl() {
        return System.getProperty("baseUrl", DEFAULT_BASE_URL);
    }

    public static String getBrowser() {
        return System.getProperty("browser", DEFAULT_BROWSER);
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", DEFAULT_HEADLESS));
    }

    public static String getUrl(String route) {
        return getBaseUrl() + route;
    }
}
