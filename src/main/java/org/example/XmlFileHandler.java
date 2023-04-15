package org.example;

import org.example.сollection.City;
import org.example.exceptions.FileLoadingException;
import org.example.exceptions.NoAccessToFileException;
import org.example.interfaces.Loadable;

import javax.xml.bind.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.TreeSet;

import org.example.сollection.Cities;

/**
 * this class implements work with a xml file
 */
public class XmlFileHandler implements Loadable {
    private TreeSet<City> cities;
    private ZonedDateTime initializationTime;

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
    public void load(File xmlfile) throws NoAccessToFileException, FileLoadingException {


        try {
            if (!checkPermission(xmlfile)) {
                throw new NoAccessToFileException(xmlfile);
            }
            initializationTime = ZonedDateTime.now();
            if (!xmlfile.exists()) {
                System.out.println("0 groups were downloaded");
                cities = new TreeSet<>();
                return;
            }

            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            var bufferedReader = new BufferedReader(new FileReader(xmlfile));

            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(bufferedReader);

            JAXBContext context = JAXBContext.newInstance(Cities.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            cities = unmarshaller.unmarshal(xmlEventReader, Cities.class).getValue().getCities();
            System.out.println(cities);

            System.out.println("loaded " + " cities: " + cities.size());
        } catch (Exception jaxbException) {
            cities = new TreeSet<>();
        }
    }

    /**
     * the method save data to file
     * @param city
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(TreeSet<City> city, File file) throws Exception {
        try {
            if (city.size() == 0){
                new OutputStreamWriter(new FileOutputStream(file),"UTF-8").close();
                return true;
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            var citiesXml = new Cities();
            citiesXml.setCities(city);
            JAXBContext jaxbContext = JAXBContext.newInstance(Cities.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(citiesXml, outputStreamWriter);
            outputStreamWriter.close();

        }
        catch (JAXBException | IOException jaxbException) {
            System.err.print(jaxbException.getMessage());
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
