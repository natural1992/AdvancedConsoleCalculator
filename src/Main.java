import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main calculator = new Main();
        Action action = new Action();
        String input, result;
        calculator.printInfo();

        programLoop:
        while(true) {
            do {
                input = calculator.getStringFromUser();

                if(input.equals("Exit") || input.equals("exit")){
                    break programLoop;
                }
            } while (calculator.bracketAndErrorChecker(input) != 0);

            input = calculator.spaceDeleter(input);

            result = calculator.mainLoopStarter(input);

            action.printString("The result is \n" + result);
        }
    }

    Scanner sc = new Scanner(System.in);
    Action action = new Action();

    public String getStringFromUser (){
        action.printString("Enter action to compute");
        return sc.nextLine();
    }

    public String spaceDeleter(String argument){
        StringBuilder result = new StringBuilder();
        int substringStartIndex = 0;

        for(int i = 0; i < argument.length(); i++){
            if(argument.charAt(i) == ' '){
                result.append(argument.substring(substringStartIndex, i));
                substringStartIndex = i + 1;
            }
        }

        if(substringStartIndex == 0){
            result.append(argument);
        }

        return result.toString();
    }

    public String mainLoopStarter(String argument){
        String result = argument;
        PrioritizedBrackets ongoingBrackets = prioritizedBracketFinder(result);

        while((ongoingBrackets.getOpening() != 0) || (ongoingBrackets.getClosure() != result.length())){
            result = result.replace(
                    result.substring(ongoingBrackets.getOpening(), ongoingBrackets.getClosure() + 1)
                    , calculateNoBrackets(result.substring(ongoingBrackets.getOpening() + 1, ongoingBrackets.getClosure())));

            ongoingBrackets = prioritizedBracketFinder(result);
        }

        result = result.replace(
                result.substring(ongoingBrackets.getOpening(), ongoingBrackets.getClosure())
                , calculateNoBrackets(result.substring(ongoingBrackets.getOpening(), ongoingBrackets.getClosure())));

        return result;
    }

    public void printInfo(){
        action.printString("You can compute here many actions at once. Use symbols +; -; *; /; ^ for power; ! for factorial. Use also brackets for non standard orders of computation. Type 'Exit' or 'exit' to quit the program.");
    }

    public int bracketAndErrorChecker(String input){
        int numOfUnclosedBrackets = 0;

        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '('){
                numOfUnclosedBrackets++;
            }else if(input.charAt(i) == ')'){
                numOfUnclosedBrackets--;
            }else if(!action.isCharADigit(input.charAt(i)) && !action.isCharAnAction(input.charAt(i)) && input.charAt(i) != ' '){
                action.errorCommunicate(i);
                return 1;
            }

            if(numOfUnclosedBrackets < 0){
                action.printString("There is a bracket missing");
                return 2;
            }
        }

        return 0;
    }

    public PrioritizedBrackets prioritizedBracketFinder(String input){
        PrioritizedBrackets prioritizedBrackets = new PrioritizedBrackets(0, input.length());

        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '('){
                prioritizedBrackets.setOpening(i);
            }else if(input.charAt(i) == ')'){
                prioritizedBrackets.setClosure(i);
                break;
            }
        }

        return prioritizedBrackets;
    }

    public String calculateNoBrackets(String argument){
        String beforeCalculations = "", afterCalculations = argument;

        while(!beforeCalculations.equals(afterCalculations)){
            beforeCalculations = afterCalculations;
            afterCalculations = action.doFactorial(beforeCalculations);
        }
        beforeCalculations = "";

        while(!beforeCalculations.equals(afterCalculations)){
            beforeCalculations = afterCalculations;
            afterCalculations = action.doPower(beforeCalculations);
        }
        beforeCalculations = "";

        while(!beforeCalculations.equals(afterCalculations)){
            beforeCalculations = afterCalculations;
            afterCalculations = action.doMultiplicationDivision(beforeCalculations);
        }
        beforeCalculations = "";

        while(!beforeCalculations.equals(afterCalculations)){
            beforeCalculations = afterCalculations;
            afterCalculations = action.doAdditionDifference(beforeCalculations);
        }

        return afterCalculations;
    }
}
