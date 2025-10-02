package enums;

/**
 * Перечисление профилей обучения, используемых в системе для агрегирования статистики.
 */
public enum StudyProfile {
    PHYSICS("���������"),
    COMPUTER_SCIENCE("�?�?�"�?�?�?���'����"),
    MATHEMATICS("�?���'��?���'����"),
    JURISPRUDENCE("�R�?��?���?�?�?��?�Ő�?"),
    MEDICINE("�?��?��Ő�?��"),
    LINGUISTICS("�>��?�?�?��?�'����");

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
