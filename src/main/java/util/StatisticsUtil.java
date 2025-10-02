package util;

import enums.StudyProfile;
import model.Statistics;
import model.Student;
import model.University;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Набор утилит для построения агрегированной статистики по студентам и университетам.
 * Обрабатывает исходные коллекции и формирует объекты {@link Statistics} с использованием Stream API.
 */
public final class StatisticsUtil {

    /**
     * Прячет конструктор утилитного класса и предотвращает создание экземпляров.
     */
    private StatisticsUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Формирует список статистик для каждого профиля обучения, по которому найден хотя бы один университет.
     * <p>
     * Коллекции студентов и университетов фильтруются от пустых значений, университеты группируются по профилю,
     * далее для каждого профиля подбираются связанные студенты. Средний балл считается только при наличии студентов
     * и округляется математически до двух знаков после запятой.
     * </p>
     *
     * @param students     исходный список студентов; {@code null} трактуется как пустая коллекция
     * @param universities исходный список университетов; {@code null} или пустой список возвращает пустой результат
     * @return список статистик, упорядоченный согласно порядку появления профилей в исходной коллекции университетов
     */
    public static List<Statistics> calculateStatistics(List<Student> students, List<University> universities) {
        if (universities == null || universities.isEmpty()) {
            return Collections.emptyList();
        }

        Map<StudyProfile, List<University>> universitiesByProfile = universities.stream()
                .filter(Objects::nonNull)
                .filter(university -> university.getMainProfile() != null)
                .collect(Collectors.groupingBy(
                        University::getMainProfile,
                        LinkedHashMap::new,
                        Collectors.toCollection(ArrayList::new)));

        if (universitiesByProfile.isEmpty()) {
            return Collections.emptyList();
        }

        Map<String, List<Student>> studentsByUniversity = students == null
                ? Collections.emptyMap()
                : students.stream()
                .filter(Objects::nonNull)
                .filter(student -> student.getUniversityId() != null)
                .collect(Collectors.groupingBy(Student::getUniversityId));

        return universitiesByProfile.entrySet().stream()
                .map(entry -> buildStatistics(entry.getKey(), entry.getValue(), studentsByUniversity))
                .collect(Collectors.toList());
    }

    /**
     * Создаёт объект {@link Statistics} для конкретного профиля обучения на основе списка университетов и студентов.
     * Собирает уникальные названия университетов, подсчитывает студентов и вычисляет средний балл при наличии данных.
     *
     * @param profile              профиль обучения, для которого строится статистика
     * @param profileUniversities  университеты, относящиеся к данному профилю
     * @param studentsByUniversity отображение студентов, сгруппированных по идентификатору университета
     * @return полностью заполненный объект статистики; средний балл может отсутствовать (значение {@code null})
     */
    private static Statistics buildStatistics(
            StudyProfile profile,
            List<University> profileUniversities,
            Map<String, List<Student>> studentsByUniversity) {

        List<String> universityNames = profileUniversities.stream()
                .map(University::getFullName)
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(LinkedHashSet::new),
                        ArrayList::new));

        List<Student> profileStudents = profileUniversities.stream()
                .map(University::getId)
                .filter(Objects::nonNull)
                .map(studentsByUniversity::get)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        OptionalDouble averageScoreOptional = profileStudents.stream()
                .mapToDouble(Student::getAvgExamScore)
                .average();

        BigDecimal roundedAverage = averageScoreOptional.isPresent()
                ? BigDecimal.valueOf(averageScoreOptional.getAsDouble()).setScale(2, RoundingMode.HALF_UP)
                : null;

        return new Statistics()
                .setStudyProfile(profile)
                .setAvgExamScore(roundedAverage)
                .setStudentCount(profileStudents.size())
                .setUniversityCount(profileUniversities.size())
                .setUniversityNames(universityNames);
    }
}
