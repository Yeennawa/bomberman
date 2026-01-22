package com.bomberman.entity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiClient {

    // สร้าง player
    public static long createPlayer(String name) {
        // (ของเดิมคุณ)
        return 1L;
    }

    // ส่งคะแนน
    public static void sendScore(long playerId, int score) {
        // (ของเดิมคุณ)
    }

    // ✅ ใส่เมธอดนี้ตรงนี้เลย
    public static String getLeaderboard() {
        try {
            URL url = new URL("http://localhost:8080/api/leaderboard");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner sc = new Scanner(conn.getInputStream());
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) sb.append(sc.nextLine());
            sc.close();

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }
}
