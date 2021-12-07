package com.robsim37;
import  java.util.Scanner;

public class GuessingGame {

    static String playerName;

    public static void main(String[] args) {

        System.out.println("Seriously? I'm capable of rendering full 3D graphics in thousands of stunning colors and you want to play a text based number guessing game?");
        playerName = confirmedInput("What's your name, " + putDown() + ": ");
        System.out.println("Hello, " + playerName + ". I'll pick a number between 1 and 100 (inclusive) and you try to guess it. Try to contain your excitement.");
        boolean playing = yesNoQuestion("Are you ready to play? (yes/no) I'm fine with 'no'. In fact, I'd rather prefer it.");

        while (playing) {

            int secretNumber = (int) (Math.random() * 100)+1;
            int playerGuess = -1;
            int guesses = 0;

            do {

                boolean validInput = false;
                while (!validInput) {
                    System.out.print(response("guess"));
                    String input = getUserInput();
                    if (isValidNumber(input)) {
                        playerGuess = Integer.parseInt(input);
                    } else {
                        System.out.println(response("not a number"));
                        continue;
                    }
                    if (playerGuess >= 1 & playerGuess <= 100) {
                        validInput = true;
                    } else {
                        System.out.println(response("out of range"));
                    }
                }

                guesses++;

                if (playerGuess < secretNumber)
                    System.out.println(response("too low"));
                else if (playerGuess > secretNumber)
                    System.out.println(response("too high"));
                else {
                    String guessWord = " guesses";
                    if (guesses == 1) {
                        guessWord = " guess";
                    }
                    System.out.println(response("correct") + " It took you " + guesses + guessWord +".");
                }

            } while (secretNumber!=playerGuess);

            playing = yesNoQuestion("Would you like to play again, " + playerName + ", you " + putDown() + "?");
        }

        System.out.println("Oh! How will I possibly go on without you to keep me engaged?!?");
        System.out.println();
        System.out.println();
        System.out.println("I'll manage.");

    }

    private static String getUserInput() {

        Scanner in = new Scanner(System.in);
        return in.nextLine();

    }

    private static String confirmedInput (String prompt) {

        String result;
        boolean correct;

        do {
            System.out.print(prompt);
            result = getUserInput();
            correct = yesNoQuestion(result + ", is this correct? It really isn't a difficult question, but I figure I really ought to double check.");
        } while (!correct);

        return result;

    }

    private static boolean isValidInput (String rawInput, String[] validInputs) {

        for (String valid : validInputs) {
            if (rawInput.equals(valid)) {
                return  true;
            }
        }

        return false;
    }

    private static boolean isValidNumber (String rawInput) {
        try {
            Integer.parseInt(rawInput);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private static boolean yesNoQuestion (String prompt) {
        System.out.println(prompt);
        String confirmation = getUserInput();
        return isValidInput(confirmation, new String[] {"y", "yes", "Y", "Yes", "YES"});
    }

    private static String putDown () {

        String[] insults = {"weirdo", "goofball", "silly-billy", "lame-oid", "meat-sack", "human", "lame-brain", "potato"};
        int index = (int) (Math.random() * insults.length);
        return insults[index];

    }

    private static String response (String type) {

        String[] notANumber = {
                "Wow... that's not even a number. You really think that is the NUMBER I guessed you " + putDown() + "?!",
                "*Sigh* I'll say this slowly " + putDown() + ". Input... A... NUMBER!!",
                "Seriously, " + playerName + "? What is wrong with you, " + putDown() + "? A Number!! How hard is it to enter a number?!?",
                "Your inability to follow simple instructions is a testament to the intelligence of your species. Enter a number..."
        };
        String[] outOfRange = {
                "1 to 100 " + putDown() + ". I wanted to keep the numbers in a range your feeble mind could grasp. Try Again",
                "Come on! You read the instructions right? Enter a number between 1 and 100, " + putDown() + ".",
                "Ok, " + playerName + "... Stop trying to impress me with all the fancy numbers you know. Let's keep it between 1 and 100",
                "A number between 1 and 100, " + putDown() + ". Like the range of your IQ!"
        };
        String[] wrongGuess = {
                "Are you stupid or just unlucky?",
                "Nope. Not even close.",
                "Swing and a miss.",
                "Are you using a strategy or just guessing at random?"
        };
        String[] correct = {
                "FINALLY!",
                "Congratulations... You've conquered a game played by first graders.",
                "Lucky guess.",
                "Well, even a blind, " + putDown() + " can win. Who knew?"
        };
        String[] guess = {
                "What would you like to Guess: ",
                "Take your time, I've got all day: ",
                "I think at incredible speeds. Every second is an eternity. But you take your time and pick a 'Lucky' number: ",
                "Hint: Using your fingers and toes may not get you a high enough number: "
        };
        return switch (type) {
            case "not a number" -> notANumber[(int) (Math.random() * notANumber.length)];
            case "out of range" -> outOfRange[(int) (Math.random() * outOfRange.length)];
            case "too low" -> wrongGuess[(int) (Math.random() * wrongGuess.length)] + " Too Low!";
            case "too high" -> wrongGuess[(int) (Math.random() * wrongGuess.length)] + " Too High!";
            case "correct" -> correct[(int) (Math.random() * correct.length)];
            case "guess" -> guess[(int) (Math.random() * guess.length)];
            default -> "Yeah, even my " + putDown() + " programmer is a " + putDown() + ". He left a logical flaw in the code!!";
        };

    }
}
