package org.example.xmlUtils;

import org.example.exception.FileLoadingException;
import org.example.exception.NoAccessToFileException;
import org.example.сollection.City;

import javax.xml.bind.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.TreeSet;
import java.util.logging.Logger;
import org.example.Receiver;
import org.example.сollection.Cities;

/**
 * this class implements work with a xml file
 */
public class XmlFileHandler implements Loading.Loadable {


    private final Logger logger;
    private TreeSet<City> cities;
    private ZonedDateTime initializationTime;

    public XmlFileHandler(Logger logger) {
        this.logger = logger;

    }

    private boolean checkPermission(File file){
        if (!file.canRead()){
            System.out.println("File cannot be read from. You should have this permission.");
            return false;
        }
        if (!file.canWrite()){
            System.out.println("File cannot be written to. You should have this permission.");
            return false;
        }
        return true;
    }

    /**
     * the method load data from file
     * @param xmlfile
     * @throws NoAccessToFileException
     * @throws FileLoadingException
     */
    @Override
    public void load() throws NoAccessToFileException, FileLoadingException {
        String filePath = System.getenv("lab6");
        if (filePath == null) {
            logger.info("Program didn't find xml file. " +
                    "Change the file path in the environment variable(FILE_PATH)!");
        }
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            if (!checkPermission(new File(filePath))) {
                throw new NoAccessToFileException(new File(filePath));
            }
            initializationTime = ZonedDateTime.now();
            if (!new File(filePath).exists()) {
                System.out.println("0 cities were downloaded");
                cities = new TreeSet<>();
                return;
            }

            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(bufferedReader);

            JAXBContext context = JAXBContext.newInstance(Cities.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            cities = unmarshaller.unmarshal(xmlEventReader, Cities.class).getValue().getCities();
            System.out.println(cities);

           logger.info("loaded " + " cities: " + cities.size());
        }
        catch (Exception jaxbException) {
            cities = new TreeSet<>();


        }
    }

    /**
     * the method save data to file
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(TreeSet<City> city) throws Exception {
        String filePath = System.getenv("lab6");

        if (filePath == null)
            logger.info("\tProgram can't find xml file. " +
                "Change environmental variable(\"lab6\")!\n\n");
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8")){
            var citiesXml = new Cities();
            citiesXml.setCities(city);
            JAXBContext jaxbContext = JAXBContext.newInstance(Cities.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(citiesXml, outputStreamWriter);
            outputStreamWriter.close();

        }
        catch (JAXBException | IOException jaxbException) {
            logger.info(jaxbException.getMessage());
            return false;
        }
        return true;
    }
    /**
     * the method return initializationTime of collection
     * @return
     */
    @Override
    public ZonedDateTime getInitializationTime() {
        return initializationTime;
    }

    /**
     * the method return collection
     * @return
     */
    @Override
    public TreeSet<City> get() {
        return cities;
    }
}
