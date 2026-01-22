package com.bomberman.entity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.*;



public class ApiClient {

    public static void sendScore(String playerName, int score) {
        try {
            if (playerName == null || playerName.isBlank()) {
                playerName = "unknown"; // กัน null
            }

            URL url = new URL("http://localhost:8080/api/scores");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            String json = String.format(
                    "{\"playerName\":\"%s\",\"score\":%d}",
                    playerName, score
            );

            // ส่งข้อมูล
            try (var os = conn.getOutputStream()) {
                os.write(json.getBytes("UTF-8"));
            }

            // trigger request
            int responseCode = conn.getResponseCode();
            System.out.println("sendScore response = " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static List<String[]> getLeaderboard() {
        List<String[]> list = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/api/leaderboard");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner sc = new Scanner(conn.getInputStream());
            StringBuilder json = new StringBuilder();
            while (sc.hasNext()) json.append(sc.nextLine());

            JsonArray arr = JsonParser.parseString(json.toString()).getAsJsonArray();

            for (JsonElement el : arr) {
                JsonObject o = el.getAsJsonObject();

                String name = o.has("playerName") && !o.get("playerName").isJsonNull()
                        ? o.get("playerName").getAsString()
                        : "Unknown";

                String score = o.has("score") && !o.get("score").isJsonNull()
                        ? o.get("score").getAsString()
                        : "0";

                list.add(new String[]{ name, score });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
