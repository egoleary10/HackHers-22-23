package org.firstinspires.ftc.teamcode;

public class PaulMath {
    public static float[] omniCalc(float verticalPower, float horizontalPower, float rotationalPower) {

        float[] vertical = {verticalPower, verticalPower, verticalPower, verticalPower};//changed 1 and 3
        float[] horizontal = {-horizontalPower, horizontalPower, horizontalPower, -horizontalPower}; //changed 0 and 3
        float[] rotational = {-rotationalPower, rotationalPower, -rotationalPower, rotationalPower}; //changed 1 and 3
        //the first vertical power

        float[] sum = new float[4];
        for (int i = 0; i < 4; i++) {
            sum[i] = vertical[i] + horizontal[i] + rotational[i];
        }
        float greatest = -100000;
        for (int i = 0; i < 4; i++) {
            if (Math.abs(sum[i]) > greatest) {
                greatest = Math.abs(sum[i]);
            }
        }

        if(greatest>1){
            for(int i = 0; i<4; i++){
                sum[i] = sum[i]/greatest;
            }
        }
        return sum;
    }
}