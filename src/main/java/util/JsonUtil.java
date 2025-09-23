package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Student;
import model.University;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {
    
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    // Private constructor to prevent instantiation
    private JsonUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    // Serialization methods
    
    /**
     * Serialize a single Student object to JSON string
     */
    public static String serializeStudent(Student student) {
        return gson.toJson(student);
    }
    
    /**
     * Serialize a single University object to JSON string
     */
    public static String serializeUniversity(University university) {
        return gson.toJson(university);
    }
    
    /**
     * Serialize a list of Student objects to JSON string
     */
    public static String serializeStudentList(List<Student> students) {
        return gson.toJson(students);
    }
    
    /**
     * Serialize a list of University objects to JSON string
     */
    public static String serializeUniversityList(List<University> universities) {
        return gson.toJson(universities);
    }
    
    // Deserialization methods
    
    /**
     * Deserialize JSON string to a single Student object
     */
    public static Student deserializeStudent(String json) {
        return gson.fromJson(json, Student.class);
    }
    
    /**
     * Deserialize JSON string to a single University object
     */
    public static University deserializeUniversity(String json) {
        return gson.fromJson(json, University.class);
    }
    
    /**
     * Deserialize JSON string to a list of Student objects
     */
    public static List<Student> deserializeStudentList(String json) {
        Type listType = new TypeToken<List<Student>>(){}.getType();
        return gson.fromJson(json, listType);
    }
    
    /**
     * Deserialize JSON string to a list of University objects
     */
    public static List<University> deserializeUniversityList(String json) {
        Type listType = new TypeToken<List<University>>(){}.getType();
        return gson.fromJson(json, listType);
    }
}