package tests.math;

import java.util.List;
import java.util.Random;

public class AdditionHelp {
    public Integer generateInt() {
        int randomInt = new Random().nextInt(99);
        System.out.println(randomInt);
        return randomInt+1;
    }

    public Integer addNumbers(int num1, int num2) {
        int total = num1 + num2;
        return total;
    }
}
