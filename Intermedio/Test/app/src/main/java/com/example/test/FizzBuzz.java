package com.example.test;

public class FizzBuzz {

    // Mensaje para los números múltiplos de 3
    public static final String FIZZ = "FIZZ";

    // Mensaje para los números múltiplos de 5
    public static final String BUZZ = "BUZZ";


    public String execute(int number) {
        String message = "";

        if (multiple3(number)) {
            message += FIZZ;
        }

        if (multiple5(number)) {
            message += BUZZ;
        }
        if (message.isEmpty()) {
            message = "" + number;
        }
        return message;

    }

    private boolean multiple5(int number) {
        return (number % 5 == 0);
    }

    private boolean multiple3(int number) {
        return (number % 3 == 0);
    }

}
