package cwc_cons;
/**
 * Методы для получения типа службы: 1c, MS SQL, Postgres
 * Методы для получения версии
 */
public interface Info_services {
    /**
     * Получаем тип службы
     * @return Получаем тип службы
     */
    public Type_bd gettype();

    /**
     * Получаем версию службы
     * @return
     */
    public String getversion();
}
