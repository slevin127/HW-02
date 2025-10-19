package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Root;
import model.Statistics;
import model.Student;
import model.University;
import util.JsonUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для записи JSON-файлов из Java-объектов с использованием Gson.
 */
public class JsonWriter {

    private static final Logger logger = Logger.getLogger(JsonWriter.class.getName());
    private static final String OUTPUT_DIR = "jsonReqs";
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Скрывает конструктор утилитного класса.
     */
    private JsonWriter() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Выполняет сериализацию корневого объекта в JSON-файл с временной меткой.
     *
     * @param root корневой объект для сериализации
     * @throws IOException если произошла ошибка при создании директории или записи файла
     */
    public static void writeJson(Root root) throws IOException {
        logger.info("Starting JSON serialization process");

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

        // Генерируем имя файла с временной меткой из processedAt объекта root
        String timestamp;
        if (root.getProcessedAt() != null) {
            timestamp = root.getProcessedAt().format(TIMESTAMP_FORMATTER);
        } else {
            timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        }
        String fileName = String.format("req_%s.json", timestamp);
        File outputFile = new File(outputDir, fileName);

        logger.info("Writing JSON to file: " + outputFile.getAbsolutePath());

        try {
            // Создаём структуру для JSON, аналогичную XML
            Map<String, Object> jsonRoot = new HashMap<>();
            
            // Добавляем информацию о студентах
            if (root.getStudents() != null) {
                jsonRoot.put("studentsInfo", root.getStudents());
                logger.info("Added " + root.getStudents().size() + " students to JSON");
            }
            
            // Добавляем информацию об университетах
            if (root.getUniversities() != null) {
                jsonRoot.put("universitiesInfo", root.getUniversities());
                logger.info("Added " + root.getUniversities().size() + " universities to JSON");
            }
            
            // Добавляем статистическую информацию
            if (root.getStatistics() != null) {
                jsonRoot.put("statisticalInfo", root.getStatistics());
                logger.info("Added " + root.getStatistics().size() + " statistics entries to JSON");
            }
            
            // Добавляем время обработки
            if (root.getProcessedAt() != null) {
                jsonRoot.put("processedAt", root.getProcessedAt().toString());
            }

            // Записываем JSON в файл
            try (FileWriter writer = new FileWriter(outputFile, java.nio.charset.StandardCharsets.UTF_8)) {
                gson.toJson(jsonRoot, writer);
            }

            logger.info("Successfully wrote JSON file: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write JSON file: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Альтернативный метод для записи JSON-файла с использованием long-представления времени.
     *
     * @param root корневой объект для сериализации
     * @param timestamp временная метка в миллисекундах
     * @throws IOException если произошла ошибка при создании директории или записи файла
     */
    public static void writeJson(Root root, long timestamp) throws IOException {
        logger.info("Starting JSON serialization process with timestamp: " + timestamp);

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
        String fileName = String.format("req_%d.json", timestamp);
        File outputFile = new File(outputDir, fileName);

        logger.info("Writing JSON to file: " + outputFile.getAbsolutePath());

        try {
            // Создаём структуру для JSON, аналогичную XML
            Map<String, Object> jsonRoot = new HashMap<>();
            
            // Добавляем информацию о студентах
            if (root.getStudents() != null) {
                jsonRoot.put("studentsInfo", root.getStudents());
                logger.info("Added " + root.getStudents().size() + " students to JSON");
            }
            
            // Добавляем информацию об университетах
            if (root.getUniversities() != null) {
                jsonRoot.put("universitiesInfo", root.getUniversities());
                logger.info("Added " + root.getUniversities().size() + " universities to JSON");
            }
            
            // Добавляем статистическую информацию
            if (root.getStatistics() != null) {
                jsonRoot.put("statisticalInfo", root.getStatistics());
                logger.info("Added " + root.getStatistics().size() + " statistics entries to JSON");
            }
            
            // Добавляем время обработки
            if (root.getProcessedAt() != null) {
                jsonRoot.put("processedAt", root.getProcessedAt().toString());
            }

            // Записываем JSON в файл
            try (FileWriter writer = new FileWriter(outputFile, java.nio.charset.StandardCharsets.UTF_8)) {
                gson.toJson(jsonRoot, writer);
            }

            logger.info("Successfully wrote JSON file: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write JSON file: " + e.getMessage(), e);
            throw e;
        }
    }
}