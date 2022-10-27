package com.programming;

import java.util.List;

public class FP01Functional {

    public static void main(String args[]){
        List<Integer> numbers = List.of(12,9,13,4,6,2,4,12,15);

        printAllNumbersInFunctional(numbers);
        printEvenNumbersInFunctional(numbers);
    }

    private static void print(int number){
        System.out.println(number);
    }

    private static boolean isEven(int number){
        return number%2==0;
    }

    private static void printAllNumbersInFunctional(List<Integer> integers) {
        integers.stream().forEach(System.out::println);

    }

    private static void printEvenNumbersInFunctional(List<Integer> integers) {
        integers.stream()
                .filter( number -> number%2 == 0 )
                .forEach(System.out::println);

    }


}
