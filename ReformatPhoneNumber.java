public class ReformatPhoneNumber {
    public static void main(String[] args) {
        String number1 = "1-23-45 6";
        showResults(number1); // expect "123-456"

        String number2 = "123 4-567";
        showResults(number2); // expect "123-45-67"

        String number3 = "123 4-5678";
        showResults(number3); // expect "123-456-78"

        String number4 = "12";
        showResults(number4); // expect "12"

        String number5 = "--17-5 229 35-39475 ";
        showResults(number5); // expect "175-229-353-94-75"
    }

    private static void showResults(String number) {
        System.out.println("\t----ShowResults----");
        System.out.printf("INPUT NUMBER: %s\n", number);
        String output = reformatNumber(number);
        System.out.printf("OUTPUT NUMBER: %s\n", output);
    }

    // Time: O(n), space: O(n)
    public static String reformatNumber(String number) {
        if(number == null || number.length() == 0) return "";

        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < number.length(); ++i) {
            char c = number.charAt(i);
            if(c == '-' || Character.isSpaceChar(c)) continue;
            input.append(c);
        }

        int numDigitsLeft = input.length(), i = 0;
        while(numDigitsLeft >= 2) {
            if(numDigitsLeft > 4) {
                output.append(input.substring(i, i+3) + "-");
                i += 3;
                numDigitsLeft -= 3;
            } else if(numDigitsLeft == 4) {
                output.append(input.substring(i, i+2) + "-");
                i += 2;
                output.append(input.substring(i, i+2));
                i += 2;
                numDigitsLeft -= 4;
            } else if(numDigitsLeft == 3) {
                output.append(input.substring(i, i+3));
                i += 3;
                numDigitsLeft -= 3;
            } else {
                output.append(input.substring(i, i+2));
                i += 2;
                numDigitsLeft -= 2;
            }
        }

        return output.toString();
    }
}
