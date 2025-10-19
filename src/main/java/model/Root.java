package model;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Корневой класс для JAXB маршаллинга, содержащий все необходимые данные для XML-экспорта.
 */
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {

    @XmlElementWrapper(name = "studentsInfo")
    @XmlElement(name = "studentEntry")
    private List<Student> students;

    @XmlElementWrapper(name = "universitiesInfo")
    @XmlElement(name = "universityEntry")
    private List<University> universities;

    @XmlElementWrapper(name = "statisticalInfo")
    @XmlElement(name = "statisticsEntry")
    private List<Statistics> statistics;

    @XmlElement(name = "processedAt")
    private LocalDateTime processedAt;

    /**
     * Создаёт пустой корневой объект.
     */
    public Root() {
    }

    /**
     * Создаёт корневой объект с заданными данными.
     *
     * @param students список студентов
     * @param universities список университетов
     * @param statistics список статистик
     * @param processedAt время обработки данных
     */
    public Root(List<Student> students, List<University> universities, 
                List<Statistics> statistics, LocalDateTime processedAt) {
        this.students = students;
        this.universities = universities;
        this.statistics = statistics;
        this.processedAt = processedAt;
    }

    /**
     * Возвращает список студентов.
     *
     * @return список студентов
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Устанавливает список студентов.
     *
     * @param students список студентов
     * @return текущий объект корня
     */
    public Root setStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    /**
     * Возвращает список университетов.
     *
     * @return список университетов
     */
    public List<University> getUniversities() {
        return universities;
    }

    /**
     * Устанавливает список университетов.
     *
     * @param universities список университетов
     * @return текущий объект корня
     */
    public Root setUniversities(List<University> universities) {
        this.universities = universities;
        return this;
    }

    /**
     * Возвращает список статистик.
     *
     * @return список статистик
     */
    public List<Statistics> getStatistics() {
        return statistics;
    }

    /**
     * Устанавливает список статистик.
     *
     * @param statistics список статистик
     * @return текущий объект корня
     */
    public Root setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
        return this;
    }

    /**
     * Возвращает время обработки данных.
     *
     * @return время обработки
     */
    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    /**
     * Устанавливает время обработки данных.
     *
     * @param processedAt время обработки
     * @return текущий объект корня
     */
    public Root setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
        return this;
    }
}