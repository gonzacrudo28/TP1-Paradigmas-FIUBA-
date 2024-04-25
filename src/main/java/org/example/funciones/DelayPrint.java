package org.example.funciones;

public class DelayPrint {
        public static void delayPrint(String text, int delayMillis) {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("");
        }

}
