package enums;

/**
 * Перечисление профилей обучения, используемых в системе для агрегирования статистики.
 */
public enum StudyProfile {
    PHYSICS("Физика"),
    COMPUTER_SCIENCE("Информатика"),
    MATHEMATICS("Математика"),
    JURISPRUDENCE("Юриспруденция"),
    MEDICINE("Медицина"),
    LINGUISTICS("Лингвистика");

    private final String profileName;

    /**
     * Создаёт элемент перечисления с локализованным отображаемым именем.
     *
     * @param profileName отображаемое название профиля
     */
    private StudyProfile(String profileName) {
        this.profileName = profileName;
    }

    /**
     * Возвращает читабельное название профиля обучения.
     *
     * @return отображаемое название
     */
    public String getProfileName() {
        return this.profileName;
    }
}
