package org.example;

import org.example.сollection.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * checks the validity of the input data
 */
public class InputService {


    public InputService() {

    }

    private Scanner scanner = null;

    {
        scanner = new Scanner(System.in);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * the method checks the validity of the name and returns it
     * @return
     * @throws NoSuchElementException
     */
    public String inputName() throws NoSuchElementException {
        for ( ; ; ) {
            try {
                MessageHandler.displayToUser("Do not enter a very long name, some parts of it may get lost");
                MessageHandler.displayToUser("Enter a name: ");
                String name = scanner.nextLine().trim();
                if (name.equals("")) {
                    MessageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                return name;
            } catch (InputMismatchException inputMismatchException) {
                MessageHandler.displayToUser("This value must be non-empty string.");
                scanner.next();
            }
        }
    }

    /**
     * the method checks the validity of the coordinates and returns them
     * @return
     * @throws NoSuchElementException
     *
     */
    public Coordinates inputCoordinates() throws NoSuchElementException {
        MessageHandler.displayToUser("adding coordinates..");
        var coor = new Coordinates(inputXLocation(), inputYLocation());
        MessageHandler.displayToUser("done with coordinates..");
         return coor;
    }

    /**
     * the method checks the validity of the X coordinate and returns it
     * @return
     * @throws NoSuchElementException
     */
    public Integer inputXLocation() throws NoSuchElementException {
        MessageHandler.displayToUser("Enter X coordinate of location: ");
        for ( ; ; ) {
            try {

                var str = scanner.nextLine();
                if (str.equals("")) {
                    MessageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                var val = Integer.parseInt(str);
                return val;
            } catch (NumberFormatException inputMismatchException) {
                MessageHandler.displayToUser("This value must be a Integer-type number. Try again. ");
                continue;
            }
        }
    }

    /**
     * the method checks the validity of the Y coordinate and returns it
     * @return
     * @throws NoSuchElementException
     */
    public Integer inputYLocation()  throws NoSuchElementException {
        MessageHandler.displayToUser("Enter Y coordinate of location: ");
        for ( ; ; ) {
            try {

                var str = scanner.nextLine();
                if (str.equals("")) {
                    MessageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                var val = Integer.parseInt(str);
                return val;
            } catch (NumberFormatException inputMismatchException) {
                MessageHandler.displayToUser("This value must be a Integer-type number. Try again. ");
                continue;
            }
        }
    }

    /**
     * the method checks the validity of the number and returns it
     * @return
     * @throws NoSuchElementException
     */
    public Integer getInt() throws NoSuchElementException{
        for ( ; ; ) {
            try {
                var str = scanner.nextLine();
                if (str.equals("")) {
                    MessageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }

                return Integer.parseInt(str);
            } catch (NumberFormatException inputMismatchException) {
                MessageHandler.displayToUser("This value must be a number. Try again. ");
                continue;
            }
        }
    }

    /**
     * the method display to user enum objects
     * @param enumClass
     * @param <T>
     * @throws NoSuchElementException
     */
    public <T extends Enum<T>> void inputEnum(Class<T> enumClass) throws NoSuchElementException{
        var enums = enumClass.getEnumConstants();
        for(int i = 1; i <= enums.length; i++)
            MessageHandler.displayToUser(enums[i - 1] + " - " + i);
    }

    /**
     * the method checks the validity of the number of enum object and returns it
     * @param enumClass
     * @return
     * @param <T>
     * @throws NoSuchElementException
     */
    public <T extends Enum<T>> Enum<T> readEnum(Class<T> enumClass) throws NoSuchElementException {
        var enums = enumClass.getEnumConstants();
        while (true){
            try {
                var str = scanner.nextLine();
                if (str.equals("")) {
                    MessageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                var index = Integer.parseInt(str);
                if(1 > index || index > enums.length){
                    MessageHandler.displayToUser(String.format("You should enter a number from %s to %s. Try again. ", 1, enums.length));
                    continue;
                }
                return enums[index - 1];
            } catch (InputMismatchException inputMismatchException) {
                scanner.next();
            }

        }
    }

    /**
     * the method checks the validity of the area and returns it
     * @return
     * @throws NoSuchElementException
     */
    public float inputArea() throws NoSuchElementException{
        for ( ; ; ) {
            try {
                MessageHandler.displayToUser("Enter area of city..");
                var str = scanner.nextLine();

                if (str.equals("")) {
                    MessageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                var f = Float.parseFloat(str);
                if (f > 0 && !Float.isInfinite(f)) {
                    return f;
                }
                if (f <= 0){
                    MessageHandler.displayToUser("This value less or equal zero. Try again");
                    scanner.next();

                }
                else if (Float.isInfinite(f)){
                    MessageHandler.displayToUser("This value is Infinite. Try again");
                    scanner.next();
                }
            } catch (NumberFormatException inputMismatchException) {
                MessageHandler.displayToUser("This value must be a float. Try again. ");
                scanner.next();
            }
        }
    }

    /**
     * the method checks the validity of the population and returns it
     * @return
     * @throws NoSuchElementException
     */
    public Long inputPopulation() throws NoSuchElementException{
        MessageHandler.displayToUser("Enter population of city");
        for ( ; ; ) {
            try {
                var str = scanner.nextLine();
                if (str.equals("")) {
                    MessageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                var f = Long.parseLong(str);
                if (f > 0 ) {
                    return f;
                }
                else {
                    MessageHandler.displayToUser("This value less or equal zero. Try again");

                }
            } catch (NumberFormatException inputMismatchException) {
                MessageHandler.displayToUser("This value must be a Long. Try again. ");

            }
        }
    }

    /**
     * the method checks the validity of the metersAboveSeaLevel and returns it
     * @return
     * @throws NoSuchElementException
     */
    public double getDouble() throws NoSuchElementException{
        for ( ; ; ) {
            try {
                MessageHandler.displayToUser("Enter metersAboveSeaLevel of city");
                var str = scanner.nextLine();
                if (str.equals("")) {
                    MessageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                return Double.parseDouble(str);
            } catch (NumberFormatException inputMismatchException) {
                MessageHandler.displayToUser("This value must be a double. Try again. ");
                scanner.next();
            }
        }
    }

    /**
     * the method checks the validity of the governor birthday and returns it
     * @return
     * @throws NoSuchElementException
     */
    public Human getPerson() throws NoSuchElementException{
        for ( ; ; ) {
            try {
                MessageHandler.displayToUser("Enter Governor's birthday");
                var str = scanner.nextLine();
                if (str.equals("")) {
                    MessageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                return new Human(LocalDateTime.parse(str));
            } catch (InputMismatchException inputMismatchException) {
                MessageHandler.displayToUser("This value must be a string. Try again. ");
                scanner.next();
            }
            catch (DateTimeParseException d){
                MessageHandler.displayToUser("invalid dateTimeFormat");
                scanner.next();
            }
        }
    }

    /**
     * the method checks the validity of the climate and returns it
     * @return
     * @throws NoSuchElementException
     */
    public Climate inputClimate() throws NoSuchElementException{
        for ( ; ; ) {
            try {
                MessageHandler.displayToUser("Choose Climate. Enter the number corresponding to the desired option. ");
                inputEnum(Climate.class);
                var climate = readEnum(Climate.class);
                return (Climate) climate;

            } catch (InputMismatchException inputMismatchException) {
                MessageHandler.displayToUser("This value must be a number. Try again. ");
                scanner.next();
            }
        }
    }

    /**
     * the method checks the validity of the government and returns it
     * @return
     * @throws NoSuchElementException
     */
    public Government inputGovernment() throws NoSuchElementException {
        int yesOrNoGov = 0;
        for (; ; ) {
            try {
                MessageHandler.displayToUser("enter a number: ");
                MessageHandler.displayToUser("should we add government? enter the number: 1 - Yes or 2 - No");
                yesOrNoGov = Integer.parseInt(InputClientReader.getInputReader().nextLine().trim());
                if (yesOrNoGov != 1 && yesOrNoGov != 2)
                    continue;
                if (yesOrNoGov == 2)
                    MessageHandler.displayToUser("government will not be defined");
                break;
            } catch (InputMismatchException e) {
                MessageHandler.displayToUser("enter a number: ");
            }
        }
        if (yesOrNoGov == 1) {
            for (; ; ) {
                try {
                    MessageHandler.displayToUser("Choose Government. Enter the number corresponding to the desired option. ");
                    inputEnum(Government.class);
                    var government = readEnum(Government.class);
                    return (Government) government;

                } catch (InputMismatchException inputMismatchException) {
                    MessageHandler.displayToUser("This value must be a number. Try again. ");
                    scanner.next();
                }
            }

        }
        return null;
    }
    /**
     * the method checks the validity of the standardOfLiving and returns it
     * @return
     * @throws NoSuchElementException
     */
    public StandardOfLiving inputStandardOfLiving() throws NoSuchElementException{
        int yesOrNoStandard = 0;
        for( ; ; ) {
            try {
                MessageHandler.displayToUser("enter a number: ");
                MessageHandler.displayToUser("should we add StandardOfLiving? enter the number: 1 - Yes or 2 - No");
                yesOrNoStandard = Integer.parseInt(InputClientReader.getInputReader().nextLine().trim());
                if(yesOrNoStandard != 1 && yesOrNoStandard != 2)
                    continue;
                if(yesOrNoStandard == 2)
                    MessageHandler.displayToUser("StandardOfLiving will not be defined");
                break;
            } catch (InputMismatchException e) {
                MessageHandler.displayToUser("enter a number: ");
            }
        }
        if (yesOrNoStandard == 1){
        for ( ; ; ) {
            try {
                MessageHandler.displayToUser("Choose StandardOfLiving. Enter the number corresponding to the desired option. ");
                inputEnum(StandardOfLiving.class);
                var standardOfLiving = readEnum(StandardOfLiving.class);
                return (StandardOfLiving) standardOfLiving;

            } catch (InputMismatchException inputMismatchException) {
                MessageHandler.displayToUser("This value must be a number. Try again. ");
                scanner.next();
            }
        }
        }
        return null;
    }
}

