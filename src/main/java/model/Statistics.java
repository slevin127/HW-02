package model;

import com.google.gson.annotations.SerializedName;
import enums.StudyProfile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель агрегированной статистики по профилям обучения, рассчитанной на основе данных студентов и университетов.
 */
public class Statistics {

    @SerializedName("study_profile")
    private StudyProfile studyProfile;

    @SerializedName("avg_exam_score")
    private BigDecimal avgExamScore;

    @SerializedName("student_count")
    private int studentCount;

    @SerializedName("university_count")
    private int universityCount;

    @SerializedName("university_names")
    private List<String> universityNames;

    /**
     * Создаёт пустой объект статистики с инициализированным списком университетов.
     */
    public Statistics() {
        this.universityNames = new ArrayList<>();
    }

    /**
     * Создаёт полностью инициализированный объект статистики.
     *
     * @param studyProfile     профиль обучения
     * @param avgExamScore     средний балл по профилю
     * @param studentCount     количество студентов
     * @param universityCount  количество университетов
     * @param universityNames  список названий университетов
     */
    public Statistics(
            StudyProfile studyProfile,
            BigDecimal avgExamScore,
            int studentCount,
            int universityCount,
            List<String> universityNames) {
        this.studyProfile = studyProfile;
        this.avgExamScore = avgExamScore;
        this.studentCount = studentCount;
        this.universityCount = universityCount;
        this.universityNames = universityNames == null ? new ArrayList<>() : new ArrayList<>(universityNames);
    }

    /**
     * Возвращает профиль обучения.
     *
     * @return профиль обучения
     */
    public StudyProfile getStudyProfile() {
        return studyProfile;
    }

    /**
     * Устанавливает профиль обучения.
     *
     * @param studyProfile профиль обучения
     * @return текущий объект статистики
     */
    public Statistics setStudyProfile(StudyProfile studyProfile) {
        this.studyProfile = studyProfile;
        return this;
    }

    /**
     * Возвращает средний балл по профилю обучения.
     *
     * @return средний балл или {@code null}, если он не рассчитан
     */
    public BigDecimal getAvgExamScore() {
        return avgExamScore;
    }

    /**
     * Устанавливает средний балл по профилю обучения.
     *
     * @param avgExamScore средний балл
     * @return текущий объект статистики
     */
    public Statistics setAvgExamScore(BigDecimal avgExamScore) {
        this.avgExamScore = avgExamScore;
        return this;
    }

    /**
     * Возвращает количество студентов, участвующих в расчёте статистики.
     *
     * @return количество студентов
     */
    public int getStudentCount() {
        return studentCount;
    }

    /**
     * Устанавливает количество студентов в статистике.
     *
     * @param studentCount количество студентов
     * @return текущий объект статистики
     */
    public Statistics setStudentCount(int studentCount) {
        this.studentCount = studentCount;
        return this;
    }

    /**
     * Возвращает количество университетов по профилю.
     *
     * @return количество университетов
     */
    public int getUniversityCount() {
        return universityCount;
    }

    /**
     * Устанавливает количество университетов по профилю.
     *
     * @param universityCount количество университетов
     * @return текущий объект статистики
     */
    public Statistics setUniversityCount(int universityCount) {
        this.universityCount = universityCount;
        return this;
    }

    /**
     * Возвращает список названий университетов, представленных в профиле.
     *
     * @return неизменяемая копия списка названий университетов
     */
    public List<String> getUniversityNames() {
        return new ArrayList<>(universityNames);
    }

    /**
     * Устанавливает список названий университетов, представленных в профиле.
     *
     * @param universityNames список названий университетов
     * @return текущий объект статистики
     */
    public Statistics setUniversityNames(List<String> universityNames) {
        this.universityNames = universityNames == null ? new ArrayList<>() : new ArrayList<>(universityNames);
        return this;
    }

    /**
     * Возвращает строковое представление статистики по профилю обучения.
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return String.format(
                "studyProfile = %s, avgExamScore = %s, studentCount = %s, universityCount = %s, universityNames = %s",
                studyProfile != null ? studyProfile.getProfileName() : null,
                avgExamScore,
                studentCount,
                universityCount,
                universityNames);
    }
}
