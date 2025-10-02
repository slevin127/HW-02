package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Student;
import model.University;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Утилита для сериализации и десериализации моделей проекта в формат JSON с использованием Gson.
 */
public class JsonUtil {
    
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    /**
     * Прячет конструктор утилитного класса.
     */
    private JsonUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Выполняет сериализацию отдельного студента в строку JSON.
     *
     * @param student объект студента
     * @return JSON-представление студента
     */
    public static String serializeStudent(Student student) {
        return gson.toJson(student);
    }
    
    /**
     * Выполняет сериализацию отдельного университета в строку JSON.
     *
     * @param university объект университета
     * @return JSON-представление университета
     */
    public static String serializeUniversity(University university) {
        return gson.toJson(university);
    }
    
    /**
     * Выполняет сериализацию списка студентов.
     *
     * @param students список студентов
     * @return JSON-массив со студентами
     */
    public static String serializeStudentList(List<Student> students) {
        return gson.toJson(students);
    }
    
    /**
     * Выполняет сериализацию списка университетов.
     *
     * @param universities список университетов
     * @return JSON-массив с университетами
     */
    public static String serializeUniversityList(List<University> universities) {
        return gson.toJson(universities);
    }
    
    /**
     * Восстанавливает объект студента из JSON-строки.
     *
     * @param json исходная строка JSON
     * @return объект студента
     */
    public static Student deserializeStudent(String json) {
        return gson.fromJson(json, Student.class);
    }
    
    /**
     * Восстанавливает объект университета из JSON-строки.
     *
     * @param json исходная строка JSON
     * @return объект университета
     */
    public static University deserializeUniversity(String json) {
        return gson.fromJson(json, University.class);
    }
    
    /**
     * Восстанавливает список студентов из JSON-строки.
     *
     * @param json исходная строка JSON
     * @return список студентов
     */
    public static List<Student> deserializeStudentList(String json) {
        Type listType = new TypeToken<List<Student>>(){}.getType();
        return gson.fromJson(json, listType);
    }
    
    /**
     * Восстанавливает список университетов из JSON-строки.
     *
     * @param json исходная строка JSON
     * @return список университетов
     */
    public static List<University> deserializeUniversityList(String json) {
        Type listType = new TypeToken<List<University>>(){}.getType();
        return gson.fromJson(json, listType);
    }
}
