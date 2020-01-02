package cwc_cons;

import java.util.List;

/**
 * Методы для получения информации по службе
 */
public interface Services_1c extends Info_services {
    /**
     * Получаем директорию установки
     * @return Получаем директорию установки
     */
    public String get_dir_install();

    /**
     * Получаем директорию конфигурационных файлов
     * @return Получаем директорию конфигурационных файлов
     */
    public String get_dir_conf();

    /**
     * Получаем флаг, установлен ли дебаг -debug
     * @return Получаем флаг, установлен ли дебаг -debug
     */
    public boolean get_debug();

    /**
     * Получаем диапазон рабочих портов
     * @return Получаем диапазон рабочих портов
     */
    public String get_range_port();

    /**
     * Получаем порт подключения
     * @return Получаем порт подключения
     */
    public int get_ip_port();

    /**
     * Получаем порт управления
     * @return Получаем порт управления
     */
    public int get_main_port();

    /**
     * Получаем имя кластера
     * @return Получаем имя кластера
     */
    public String get_cluster_name();

    /**
     * Получим сервера в кластере
     * @return
     */
    public List<String> get_servers();

    /**
     * Получим конфигурацию кластера 1С
     * @return
     */
    public Cluster1C get_cluster();

}
