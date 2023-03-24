package org.example.Service;

import org.example.Collection.*;
import org.example.Container.CommandsContainer;
import org.example.Exceptions.CommandInterruptionException;
import org.example.MessageHandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * checks the validity of the input data
 */
public class InputService {
    private final MessageHandler messageHandler;

    public InputService(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
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
     * @throws CommandInterruptionException
     */
    public String inputName() throws NoSuchElementException, CommandInterruptionException {
        for ( ; ; ) {
            try {
                messageHandler.displayToUser("Do not enter a very long name, some parts of it may get lost");
                messageHandler.displayToUser("Enter a name: ");
                String name = scanner.nextLine().trim();
                if(CommandsContainer.contains(name))
                    throw new CommandInterruptionException(name);
                if (name.equals("")) {
                    messageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                return name;
            } catch (InputMismatchException inputMismatchException) {
                messageHandler.displayToUser("This value must be non-empty string.");
                scanner.next();
            }
        }
    }

    /**
     * the method checks the validity of the coordinates and returns them
     * @return
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public Coordinates inputCoordinates() throws NoSuchElementException, CommandInterruptionException {
        messageHandler.displayToUser("adding coordinates..");
        var coor = new Coordinates(inputXLocation(), inputYLocation());
        messageHandler.displayToUser("done with coordinates..");
         return coor;
    }

    /**
     * the method checks the validity of the X coordinate and returns it
     * @return
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public Integer inputXLocation() throws NoSuchElementException, CommandInterruptionException {
        messageHandler.displayToUser("Enter X coordinate of location: ");
        for ( ; ; ) {
            try {

                var str = scanner.nextLine();
                if (str.equals("")) {
                    messageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                if(CommandsContainer.contains(str))
                    throw new CommandInterruptionException(str);
                var val = Integer.parseInt(str);
                return val;
            } catch (NumberFormatException inputMismatchException) {
                messageHandler.displayToUser("This value must be a Integer-type number. Try again. ");
                continue;
            }
        }
    }

    /**
     * the method checks the validity of the Y coordinate and returns it
     * @return
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public Integer inputYLocation()  throws NoSuchElementException, CommandInterruptionException {
        messageHandler.displayToUser("Enter Y coordinate of location: ");
        for ( ; ; ) {
            try {

                var str = scanner.nextLine();
                if (str.equals("")) {
                    messageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                if(CommandsContainer.contains(str))
                    throw new CommandInterruptionException(str);
                var val = Integer.parseInt(str);
                return val;
            } catch (NumberFormatException inputMismatchException) {
                messageHandler.displayToUser("This value must be a Integer-type number. Try again. ");
                continue;
            }
        }
    }

    /**
     * the method checks the validity of the number and returns it
     * @return
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public Integer getInt() throws NoSuchElementException, CommandInterruptionException {
        for ( ; ; ) {
            try {
                var str = scanner.nextLine();
                if (str.equals("")) {
                    messageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                if(CommandsContainer.contains(str))
                    throw new CommandInterruptionException(str);
                return Integer.parseInt(str);
            } catch (NumberFormatException inputMismatchException) {
                messageHandler.displayToUser("This value must be a number. Try again. ");
                continue;
            }
        }
    }

    /**
     * the method display to user enum objects
     * @param enumClass
     * @param <T>
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public <T extends Enum<T>> void inputEnum(Class<T> enumClass) throws NoSuchElementException, CommandInterruptionException {
        var enums = enumClass.getEnumConstants();
        for(int i = 1; i <= enums.length; i++)
            messageHandler.displayToUser(enums[i - 1] + " - " + i);
    }

    /**
     * the method checks the validity of the number of enum object and returns it
     * @param enumClass
     * @return
     * @param <T>
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public <T extends Enum<T>> Enum<T> readEnum(Class<T> enumClass) throws NoSuchElementException, CommandInterruptionException {
        var enums = enumClass.getEnumConstants();
        while (true){
            try {
                var str = scanner.nextLine();
                if (str.equals("")) {
                    messageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                if(CommandsContainer.contains(str))
                    throw new CommandInterruptionException(str);
                var index = Integer.parseInt(str);
                if(1 > index || index > enums.length){
                    messageHandler.displayToUser(String.format("You should enter a number from %s to %s. Try again. ", 1, enums.length));
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
     * @throws CommandInterruptionException
     */
    public float inputArea() throws NoSuchElementException,CommandInterruptionException{
        for ( ; ; ) {
            try {
                messageHandler.displayToUser("Enter area of city..");
                var str = scanner.nextLine();

                if (str.equals("")) {
                    messageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                if(CommandsContainer.contains(str))
                    throw new CommandInterruptionException(str);
                var f = Float.parseFloat(str);
                if (f > 0 && !Float.isInfinite(f)) {
                    return f;
                }
                if (f <= 0){
                    messageHandler.displayToUser("This value less or equal zero. Try again");
                    scanner.next();

                }
                else if (Float.isInfinite(f)){
                    messageHandler.displayToUser("This value is Infinite. Try again");
                    scanner.next();
                }
            } catch (NumberFormatException inputMismatchException) {
                messageHandler.displayToUser("This value must be a float. Try again. ");
                scanner.next();
            }
        }
    }

    /**
     * the method checks the validity of the population and returns it
     * @return
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public Long inputPopulation() throws NoSuchElementException, CommandInterruptionException{
        messageHandler.displayToUser("Enter population of city");
        for ( ; ; ) {
            try {
                var str = scanner.nextLine();
                if (str.equals("")) {
                    messageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                if(CommandsContainer.contains(str))
                    throw new CommandInterruptionException(str);
                var f = Long.parseLong(str);
                if (f > 0 ) {
                    return f;
                }
                else {
                    messageHandler.displayToUser("This value less or equal zero. Try again");

                }
            } catch (NumberFormatException inputMismatchException) {
                messageHandler.displayToUser("This value must be a Long. Try again. ");

            }
        }
    }

    /**
     * the method checks the validity of the metersAboveSeaLevel and returns it
     * @return
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public double getDouble() throws NoSuchElementException, CommandInterruptionException{
        for ( ; ; ) {
            try {
                messageHandler.displayToUser("Enter metersAboveSeaLevel of city");
                var str = scanner.nextLine();
                if (str.equals("")) {
                    messageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                if(CommandsContainer.contains(str))
                    throw new CommandInterruptionException(str);
                return Double.parseDouble(str);
            } catch (NumberFormatException inputMismatchException) {
                messageHandler.displayToUser("This value must be a double. Try again. ");
                scanner.next();
            }
        }
    }

    /**
     * the method checks the validity of the governor birthday and returns it
     * @return
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public Human getPerson() throws NoSuchElementException,CommandInterruptionException{
        for ( ; ; ) {
            try {
                messageHandler.displayToUser("Enter Governor's birthday");
                var str = scanner.nextLine();
                if (str.equals("")) {
                    messageHandler.displayToUser("This value cannot be empty. Try again");
                    continue;
                }
                if(CommandsContainer.contains(str))
                    throw new CommandInterruptionException(str);
                return new Human(LocalDateTime.parse(str));
            } catch (InputMismatchException inputMismatchException) {
                messageHandler.displayToUser("This value must be a string. Try again. ");
                scanner.next();
            }
            catch (DateTimeParseException d){
                messageHandler.displayToUser("invalid dateTimeFormat");
                scanner.next();
            }
        }
    }

    /**
     * the method checks the validity of the climate and returns it
     * @return
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public Climate inputClimate() throws NoSuchElementException, CommandInterruptionException{
        for ( ; ; ) {
            try {
                messageHandler.displayToUser("Choose Climate. Enter the number corresponding to the desired option. ");
                inputEnum(Climate.class);
                var climate = readEnum(Climate.class);
                return (Climate) climate;

            } catch (InputMismatchException inputMismatchException) {
                messageHandler.displayToUser("This value must be a number. Try again. ");
                scanner.next();
            }
        }
    }

    /**
     * the method checks the validity of the government and returns it
     * @return
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public Government inputGovernment() throws NoSuchElementException, CommandInterruptionException{
        for ( ; ; ) {
            try {
                messageHandler.displayToUser("Choose Government. Enter the number corresponding to the desired option. ");
                inputEnum(Government.class);
                var government = readEnum(Government.class);
                return (Government) government;

            } catch (InputMismatchException inputMismatchException) {
                messageHandler.displayToUser("This value must be a number. Try again. ");
                scanner.next();
            }
        }
    }

    /**
     * the method checks the validity of the standardOfLiving and returns it
     * @return
     * @throws NoSuchElementException
     * @throws CommandInterruptionException
     */
    public StandardOfLiving inputStandardOfLiving() throws NoSuchElementException, CommandInterruptionException{
        for ( ; ; ) {
            try {
                messageHandler.displayToUser("Choose StandardOfLiving. Enter the number corresponding to the desired option. ");
                inputEnum(StandardOfLiving.class);
                var standardOfLiving = readEnum(StandardOfLiving.class);
                return (StandardOfLiving) standardOfLiving;

            } catch (InputMismatchException inputMismatchException) {
                messageHandler.displayToUser("This value must be a number. Try again. ");
                scanner.next();
            }
        }
    }
}

