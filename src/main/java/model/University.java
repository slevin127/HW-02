package model;

import com.google.gson.annotations.SerializedName;
import enums.StudyProfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Модель университета с ключевыми атрибутами и основным профилем обучения.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class University {

    @SerializedName("university_id")
    @XmlElement(name = "universityId")
    private String id;

    @SerializedName("full_university_name")
    @XmlElement(name = "universityName")
    private String fullName;

    @SerializedName("abbreviated_name")
    private String shortName;

    @SerializedName("establishment_year")
    private int yearOfFoundation;

    @SerializedName("primary_study_profile")
    @XmlElement(name = "universityProfile")
    private StudyProfile mainProfile;

    /**
     * Создаёт пустой объект университета.
     */
    public University() {
    }

    /**
     * Возвращает идентификатор университета.
     *
     * @return идентификатор
     */
    public String getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор университета.
     *
     * @param id идентификатор
     * @return текущий объект университета
     */
    public University setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Возвращает полное название университета.
     *
     * @return полное название
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Устанавливает полное название университета.
     *
     * @param fullName полное название
     * @return текущий объект университета
     */
    public University setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Возвращает краткое название университета.
     *
     * @return краткое название
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Устанавливает краткое название университета.
     *
     * @param shortName краткое название
     * @return текущий объект университета
     */
    public University setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    /**
     * Возвращает год основания университета.
     *
     * @return год основания
     */
    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    /**
     * Устанавливает год основания университета.
     *
     * @param yearOfFoundation год основания
     * @return текущий объект университета
     */
    public University setYearOfFoundation(int yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
        return this;
    }

    /**
     * Возвращает основной профиль обучения университета.
     *
     * @return профиль обучения
     */
    public StudyProfile getMainProfile() {
        return mainProfile;
    }

    /**
     * Устанавливает основной профиль обучения университета.
     *
     * @param mainProfile профиль обучения
     * @return текущий объект университета
     */
    public University setMainProfile(StudyProfile mainProfile) {
        this.mainProfile = mainProfile;
        return this;
    }

    /**
     * Возвращает строковое представление объекта для логирования и отладки.
     *
     * @return строковое представление университета
     */
    @Override
    public String toString() {
        return String.format("id = %s, fullName = %s, shortName = %s, yearOfFoundation = %s, mainProfile = %s",
                this.id,
                this.fullName,
                this.shortName,
                this.yearOfFoundation,
                this.mainProfile.getProfileName());
    }
}
