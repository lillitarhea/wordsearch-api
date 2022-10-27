package com.programming;

import java.util.List;

public class FP01Structured {

    public static void main(String args[]){
        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);
        printAllNumbersInStructured(numbers);
        printEvenNumbersInStructured(numbers);
    }

    private static void printAllNumbersInStructured(List<Integer> numbers) {
        for(int i: numbers){

            System.out.println(i);
        }
    }

    private static void printEvenNumbersInStructured(List<Integer> numbers) {
        for (int i : numbers) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }


    }
}
