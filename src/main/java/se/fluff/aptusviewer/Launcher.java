package se.fluff.aptusviewer;

import java.security.Security;

public class Launcher {

    public static void main(String[] args) {
        Security.setProperty("jdk.tls.disabledAlgorithms", "");
        AptusViewerApplication.main(args);
    }

}
