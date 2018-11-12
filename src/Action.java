public class Action {
    public Action(){

    }

    public void printString(String stringToPrint){
        System.out.println(stringToPrint);
    }

    public void errorCommunicate(int index){
        printString("There is an error in symbol number " + index + "\nEnter your action/s once again");
    }

    public boolean isCharADigit(char arg){
        for(int i = 0; i < 10; i++){
            if(Character.getNumericValue(arg) == i){
                return true;
            }
        }

        return false;
    }

    public boolean isCharAnAction(char arg){
        char[] listOfActions = {'+', '-', '/', '*', '^', '!'};

        for(int i = 0; i < listOfActions.length; i++){
            if(listOfActions[i] == arg){
                return true;
            }
        }

        return false;
    }

    public String doFactorial(String argument){
        int lastActionIndex = -1;
        String result = argument;

        for(int i = 0; i < argument.length(); i++){
            if(argument.charAt(i) == '!'){
                result = result.replace(
                        argument.substring(lastActionIndex + 1, i + 1),
                        calculateFactorial(argument.substring(lastActionIndex + 1, i)));
                break;

            }else if(isCharAnAction(argument.charAt(i))){
                lastActionIndex = i;
            }
        }

        return result;
    }

    private String calculateFactorial(String argument){
        int input = Integer.valueOf(argument), i = 1, result = 1;

        while(i <= input){
            result *= i++;
        }

        return String.valueOf(result);
    }

    public String doPower(String argument){
        int lastActionIndex = -1, actionIndex = 0, endOfSecondArgument = argument.length();
        boolean isSecondArgument = false, wasTherePowerSign = false;
        String result = argument;

        for(int i = 0; i < argument.length(); i++){
            if(argument.charAt(i) == '^'){
                actionIndex = i;
                isSecondArgument = true;
                wasTherePowerSign = true;
            }else if(isCharAnAction(argument.charAt(i)) && !isSecondArgument){
                lastActionIndex = i;
            }else if(isCharAnAction(argument.charAt(i))){
                endOfSecondArgument = i;
                break;
            }
        }

        if(wasTherePowerSign) {
            result = result.replace(
                    argument.substring(lastActionIndex + 1, endOfSecondArgument),
                    calculatePower(argument.substring(lastActionIndex + 1, endOfSecondArgument), actionIndex - lastActionIndex - 1));
        }

        return result;
    }

    private String calculatePower(String argument, int actionIndex){
        double firstArgument = Double.valueOf(argument.substring(0, actionIndex));
        double secondArgument = Double.valueOf(argument.substring(actionIndex + 1));

        double result = Math.pow(firstArgument, secondArgument);

        return String.valueOf(result);
    }

    public String doMultiplicationDivision(String argument){
        int lastActionIndex = -1, actionIndex = 0, endOfSecondArgument = argument.length();
        boolean isSecondArgument = false, isMultiplication = false, isDivision = false;
        String result = argument;

        for(int i = 0; i < argument.length(); i++) {
            if (argument.charAt(i) == '*') {
                actionIndex = i;
                isSecondArgument = true;
                isMultiplication = true;
            }else if(argument.charAt(i) == '/'){
                actionIndex = i;
                isSecondArgument = true;
                isMultiplication = false;
                isDivision = true;
            }else if(isCharAnAction(argument.charAt(i)) && !isSecondArgument){
                lastActionIndex = i;
            }else if(isCharAnAction(argument.charAt(i))){
                endOfSecondArgument = i;
                break;
            }
        }
        if(isMultiplication) {
            result = result.replace(
                    argument.substring(lastActionIndex + 1, endOfSecondArgument),
                    calculateMultiplication(argument.substring(lastActionIndex + 1, endOfSecondArgument), actionIndex - lastActionIndex - 1));
        }else if(isDivision){
            result = result.replace(
                    argument.substring(lastActionIndex + 1, endOfSecondArgument),
                    calculateDivision(argument.substring(lastActionIndex + 1, endOfSecondArgument), actionIndex - lastActionIndex - 1));
        }

        return result;
    }

    private String calculateMultiplication(String argument, int actionIndex){
        double firstArgument = Double.valueOf(argument.substring(0, actionIndex));
        double secondArgument = Double.valueOf(argument.substring(actionIndex + 1));

        double result = firstArgument * secondArgument;

        return String.valueOf(result);
    }

    private String calculateDivision(String argument, int actionIndex){
        double firstArgument = Double.valueOf(argument.substring(0, actionIndex));
        double secondArgument = Double.valueOf(argument.substring(actionIndex + 1));

        if(secondArgument == 0){
            throw new ArithmeticException();
        }

        double result = firstArgument / secondArgument;

        return String.valueOf(result);
    }

    public String doAdditionDifference(String argument){
        int lastActionIndex = -1, actionIndex = 0, endOfSecondArgument = argument.length();
        boolean isSecondArgument = false, isAddition = false, isDifference = false;
        String result = argument;

        for(int i = 0; i < argument.length(); i++){
            if(argument.charAt(i) == '+') {
                actionIndex = i;
                isSecondArgument = true;
                isAddition = true;
            }else if(argument.charAt(i) == '-'){
                actionIndex = i;
                isSecondArgument = true;
                isAddition = false;
                isDifference = true;
            }else if(isCharAnAction(argument.charAt(i)) && !isSecondArgument){
                lastActionIndex = i;
            }else if(isCharAnAction(argument.charAt(i))){
                endOfSecondArgument = i;
                break;
            }
        }

        if(isAddition) {
            result = result.replace(
                    argument.substring(lastActionIndex + 1, endOfSecondArgument),
                    calculateAddition(argument.substring(lastActionIndex + 1, endOfSecondArgument), actionIndex - lastActionIndex - 1));
        }else if(isDifference){
            result = result.replace(
                    argument.substring(lastActionIndex + 1, endOfSecondArgument),
                    calculateDifference(argument.substring(lastActionIndex + 1, endOfSecondArgument), actionIndex - lastActionIndex - 1));
        }

        return result;
    }

    private String calculateAddition(String argument, int actionIndex){
        double firstArgument = Double.valueOf(argument.substring(0, actionIndex));
        double secondArgument = Double.valueOf(argument.substring(actionIndex + 1));

        double result = firstArgument + secondArgument;

        return String.valueOf(result);
    }

    private String calculateDifference(String argument, int actionIndex){
        double firstArgument = Double.valueOf(argument.substring(0, actionIndex));
        double secondArgument = Double.valueOf(argument.substring(actionIndex + 1));

        double result = firstArgument - secondArgument;

        return String.valueOf(result);
    }
}