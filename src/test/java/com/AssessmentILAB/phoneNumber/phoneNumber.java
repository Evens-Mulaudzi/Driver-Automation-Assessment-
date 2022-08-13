package com.AssessmentILAB.phoneNumber;

import java.util.Random;

public class phoneNumber {

    public String number(){


        Random phoneN = new Random();


        int firstNum = 0;
        int[] carrierId = {6,7,8};
        int afterCarrierId = phoneN.nextInt(10);
        int randomIndexForCarrierId = phoneN.nextInt(carrierId.length);
        String first3 = firstNum + "" + carrierId[randomIndexForCarrierId] + "" + afterCarrierId;


        int upperBoundForSecond3 = 1000;
        int second3 =  phoneN.nextInt(upperBoundForSecond3);

        int upperBoundForLast4 = 10000;
        int last4 = phoneN.nextInt(upperBoundForLast4);


        String phoneNum = first3 + " " + second3 + " " + last4;


        return phoneNum;

    }

}
