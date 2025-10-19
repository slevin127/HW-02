package model;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Модель студента с основными данными, используемыми при агрегации и сериализации.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @SerializedName("student_name")
    @XmlElement(name = "studentName")
    private String fullName;

    @SerializedName("universityId")
    @XmlElement(name = "universityId")
    private String universityId;

    @SerializedName("course_year")
    private int currentCourseNumber;

    @SerializedName("average_score")
    @XmlElement(name = "avgScore")
    private float avgExamScore;

    /**
     * Создаёт пустой объект студента.
     */
    public Student() {
    }

    /**
     * Возвращает полное имя студента.
     *
     * @return полное имя
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Устанавливает полное имя студента.
     *
     * @param fullName полное имя
     * @return текущий объект студента
     */
    public Student setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Возвращает идентификатор университета студента.
     *
     * @return идентификатор университета
     */
    public String getUniversityId() {
        return universityId;
    }

    /**
     * Устанавливает идентификатор университета студента.
     *
     * @param universityId идентификатор университета
     * @return текущий объект студента
     */
    public Student setUniversityId(String universityId) {
        this.universityId = universityId;
        return this;
    }

    /**
     * Возвращает номер курса, на котором обучается студент.
     *
     * @return номер курса
     */
    public int getCurrentCourseNumber() {
        return currentCourseNumber;
    }

    /**
     * Устанавливает номер курса обучения студента.
     *
     * @param currentCourseNumber номер курса
     * @return текущий объект студента
     */
    public Student setCurrentCourseNumber(int currentCourseNumber) {
        this.currentCourseNumber = currentCourseNumber;
        return this;
    }

    /**
     * Возвращает средний балл студента.
     *
     * @return средний балл
     */
    public float getAvgExamScore() {
        return avgExamScore;
    }

    /**
     * Устанавливает средний балл студента.
     *
     * @param avgExamScore средний балл
     * @return текущий объект студента
     */
    public Student setAvgExamScore(float avgExamScore) {
        this.avgExamScore = avgExamScore;
        return this;
    }

    /**
     * Возвращает строковое представление объекта для отладки и логирования.
     *
     * @return строковое представление студента
     */
    @Override
    public String toString() {
        return String.format("fullName = %s, universityId = %s, currentCourseNumber = %s, avgExamScore = %s",
                this.fullName,
                this.universityId,
                this.currentCourseNumber,
                this.avgExamScore);
    }
}
