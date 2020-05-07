package fizzbuzz;

import java.util.Scanner;

public class FizzBuzz {

	public static String fizzBuzzCheck(int number) {

		if(number <= 0) {
			return String.valueOf(number);
		} else if(number % 15 == 0) {
			String result = "FizzBuzz";
			return result;
		} else if(number % 5 == 0) {
			String result = "Buzz";
			return result;
		} else if(number % 3 == 0) {
			String result = "Fizz";
			return result;
		} else {
			return String.valueOf(number);
		}
	}

	public static void main(String[] args) {

        String inputNumber = null;
        Scanner scan = null;
        int i = 0;

        while(i < 1) {
            System.out.println("数字を入力してください");
            scan = new Scanner(System.in);
        	inputNumber = scan.nextLine();
        	if(inputNumber.matches("^[0-9]{1,9}$")) {
        		i ++;
        	} else {
        		System.out.println("整数9桁までの数字を入力してください");
        	}
        }
        scan.close();
        String result = fizzBuzzCheck(Integer.parseInt(inputNumber));
		System.out.println(result);
	}
}

