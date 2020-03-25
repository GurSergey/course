package com.company.session;

import java.util.HashMap;
import java.util.HashSet;

public class AdminSessionStorage {

        private static final ThreadLocal<HashSet<String>> adminSession =
                new ThreadLocal<HashSet<String>>();
        public static void setSession(String sessionId) {
            adminSession.get().add(sessionId);
        }

        public static boolean sessionIsActive(String sessionId) {
            return adminSession.get().contains(sessionId);
        }

        public static void deleteSession(String sessionId) {
            adminSession.get().remove(sessionId);
        }

}
