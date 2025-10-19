package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Student;
import model.University;
import model.Statistics;

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
     * Выполняет сериализацию объекта любого типа в строку JSON.
     *
     * @param object объект для сериализации
     * @param <T> тип объекта
     * @return JSON-представление объекта
     */
    public static <T> String serialize(T object) {
        return gson.toJson(object);
    }
    
    /**
     * Выполняет сериализацию списка объектов любого типа в строку JSON.
     *
     * @param list список объектов для сериализации
     * @param <T> тип элементов списка
     * @return JSON-массив с объектами
     */
    public static <T> String serializeList(List<T> list) {
        return gson.toJson(list);
    }
    
    /**
     * Выполняет сериализацию отдельного студента в строку JSON.
     * @deprecated Используйте {@link #serialize(Object)} вместо этого метода
     *
     * @param student объект студента
     * @return JSON-представление студента
     */
    @Deprecated
    public static String serializeStudent(Student student) {
        return serialize(student);
    }
    
    /**
     * Выполняет сериализацию отдельного университета в строку JSON.
     * @deprecated Используйте {@link #serialize(Object)} вместо этого метода
     *
     * @param university объект университета
     * @return JSON-представление университета
     */
    @Deprecated
    public static String serializeUniversity(University university) {
        return serialize(university);
    }
    
    /**
     * Выполняет сериализацию списка студентов.
     * @deprecated Используйте {@link #serializeList(List)} вместо этого метода
     *
     * @param students список студентов
     * @return JSON-массив со студентами
     */
    @Deprecated
    public static String serializeStudentList(List<Student> students) {
        return serializeList(students);
    }
    
    /**
     * Выполняет сериализацию списка университетов.
     * @deprecated Используйте {@link #serializeList(List)} вместо этого метода
     *
     * @param universities список университетов
     * @return JSON-массив с университетами
     */
    @Deprecated
    public static String serializeUniversityList(List<University> universities) {
        return serializeList(universities);
    }
    
    /**
     * Восстанавливает объект заданного типа из JSON-строки.
     *
     * @param json исходная строка JSON
     * @param clazz класс объекта
     * @param <T> тип объекта
     * @return объект заданного типа
     */
    public static <T> T deserialize(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
    
    /**
     * Восстанавливает список объектов заданного типа из JSON-строки.
     *
     * @param json исходная строка JSON
     * @param type тип списка (используйте TypeToken)
     * @param <T> тип элементов списка
     * @return список объектов заданного типа
     */
    public static <T> T deserializeList(String json, Type type) {
        return gson.fromJson(json, type);
    }
    
    /**
     * Восстанавливает объект студента из JSON-строки.
     * @deprecated Используйте {@link #deserialize(String, Class)} вместо этого метода
     *
     * @param json исходная строка JSON
     * @return объект студента
     */
    @Deprecated
    public static Student deserializeStudent(String json) {
        return deserialize(json, Student.class);
    }
    
    /**
     * Восстанавливает объект университета из JSON-строки.
     * @deprecated Используйте {@link #deserialize(String, Class)} вместо этого метода
     *
     * @param json исходная строка JSON
     * @return объект университета
     */
    @Deprecated
    public static University deserializeUniversity(String json) {
        return deserialize(json, University.class);
    }
    
    /**
     * Восстанавливает список студентов из JSON-строки.
     * @deprecated Используйте {@link #deserializeList(String, Type)} вместо этого метода
     *
     * @param json исходная строка JSON
     * @return список студентов
     */
    @Deprecated
    public static List<Student> deserializeStudentList(String json) {
        Type listType = new TypeToken<List<Student>>(){}.getType();
        return deserializeList(json, listType);
    }
    
    /**
     * Восстанавливает список университетов из JSON-строки.
     * @deprecated Используйте {@link #deserializeList(String, Type)} вместо этого метода
     *
     * @param json исходная строка JSON
     * @return список университетов
     */
    @Deprecated
    public static List<University> deserializeUniversityList(String json) {
        Type listType = new TypeToken<List<University>>(){}.getType();
        return deserializeList(json, listType);
    }
}
