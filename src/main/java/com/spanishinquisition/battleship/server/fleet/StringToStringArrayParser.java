package com.spanishinquisition.battleship.server.fleet;

public class StringToStringArrayParser {
    public static String[] parse(String str) {
        return str
                .replaceAll(";", "")
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll("\\s", "")
                .split(",");
    }
}
