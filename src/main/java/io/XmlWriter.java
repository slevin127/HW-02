package io;

import model.Root;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Класс для записи XML-файлов из Java-объектов с использованием JAXB маршаллинга.
 */
public class XmlWriter {

    private static final Logger logger = Logger.getLogger(XmlWriter.class.getName());
    private static final String OUTPUT_DIR = "xmlReqs";
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    /**
     * Скрывает конструктор утилитного класса.
     */
    private XmlWriter() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Выполняет маршаллинг корневого объекта в XML-файл с временной меткой.
     *
     * @param root корневой объект для сериализации
     * @throws IOException если произошла ошибка при создании директории или записи файла
     * @throws JAXBException если произошла ошибка при маршаллинге
     */
    public static void writeXml(Root root) throws IOException, JAXBException {
        logger.info("Starting XML marshalling process");
        
        // Создаём директорию, если она не существует
        File outputDir = new File(OUTPUT_DIR);
        if (!outputDir.exists()) {
            boolean created = outputDir.mkdirs();
            if (created) {
                logger.info("Created output directory: " + OUTPUT_DIR);
            } else {
                logger.warning("Failed to create output directory: " + OUTPUT_DIR);
            }
        }

        // Генерируем имя файла с временной меткой
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        String fileName = String.format("req_%s.xml", timestamp);
        File outputFile = new File(outputDir, fileName);

        logger.info("Writing XML to file: " + outputFile.getAbsolutePath());

        try {
            // Создаём JAXB контекст и маршаллер
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            
            // Настраиваем форматирование для читаемого вывода
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            // Выполняем маршаллинг
            marshaller.marshal(root, outputFile);
            
            logger.info("Successfully wrote XML file: " + outputFile.getAbsolutePath());
            
        } catch (JAXBException e) {
            logger.log(Level.SEVERE, "Failed to marshal XML: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Альтернативный метод для записи XML-файла с использованием long-представления времени.
     *
     * @param root корневой объект для сериализации
     * @param timestamp временная метка в миллисекундах
     * @throws IOException если произошла ошибка при создании директории или записи файла
     * @throws JAXBException если произошла ошибка при маршаллинге
     */
    public static void writeXml(Root root, long timestamp) throws IOException, JAXBException {
        logger.info("Starting XML marshalling process with timestamp: " + timestamp);
        
        // Создаём директорию, если она не существует
        File outputDir = new File(OUTPUT_DIR);
        if (!outputDir.exists()) {
            boolean created = outputDir.mkdirs();
            if (created) {
                logger.info("Created output directory: " + OUTPUT_DIR);
            } else {
                logger.warning("Failed to create output directory: " + OUTPUT_DIR);
            }
        }

        // Генерируем имя файла с long-представлением времени
        String fileName = String.format("req_%d.xml", timestamp);
        File outputFile = new File(outputDir, fileName);

        logger.info("Writing XML to file: " + outputFile.getAbsolutePath());

        try {
            // Создаём JAXB контекст и маршаллер
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            
            // Настраиваем форматирование для читаемого вывода
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            // Выполняем маршаллинг
            marshaller.marshal(root, outputFile);
            
            logger.info("Successfully wrote XML file: " + outputFile.getAbsolutePath());
            
        } catch (JAXBException e) {
            logger.log(Level.SEVERE, "Failed to marshal XML: " + e.getMessage(), e);
            throw e;
        }
    }
}