package cww_p;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для формирования наборов комманд управления IIS, для исполнения на клиенте
 */
public class cww_comm {
    /**
     * Имя сервера
     */
    String server;

    /**
     * Имя сервера
     *
     * @return Имя сервера
     */
    public String getServer() {
        return server;
    }

    /**
     * Имя сайта 1сpb
     */
    String site;

    /**
     * Имя сайта
     *
     * @return Имя сайта
     */
    public String getSite() {
        return site;
    }

    /**
     * Имя пула
     */
    String pool;

    /**
     * Имя пула
     *
     * @return Имя пула
     */
    public String getPool() {
        return pool;
    }

    /**
     * Путь раположения публикации папки на диске
     */
    String path;

    /**
     * Путь раположения публикации папки на диске
     *
     * @return Путь раположения публикации папки на диске
     */

    public String getPath() {
        return path;
    }

    /**
     * Наименование публикации
     */
    String public_name;

    /**
     * Путь раположения публикации
     *
     * @return Путь раположения публикации
     */
    public String getPublic_name() {
        return public_name;
    }

    /**
     * Путь расположения DLL
     */
    String path_dll;

    /**
     * Путь расположения DLL
     *
     * @return Путь расположения DLL
     */
    public String getPath_dll() {
        return path_dll;
    }

    /**
     * Версия платформы
     */
    private String ver_pl;

    /**
     * Версия платформы
     * @return Версия платформы
     */
    public String getVer_pl() {
        return ver_pl;
    }


    /**
     * Формируем комманду для создания публикации
     *
     * @param server
     * @param site
     * @param pool
     * @param path
     * @param public_name
     * @param path_dll
     */
    public cww_comm(String server, String site, String pool, String path, String public_name, String path_dll) {
        this.server = server;
        this.site = site;
        this.pool = pool;
        this.path = path;
        this.public_name = public_name;
        this.path_dll = path_dll;

    }



    /**
     * Формируем комманду для добавления DLL в IIS
     *
     * @param server
     * @param path_dll
     */
    public cww_comm(String server, String path_dll) {
        this.server = server;
        this.path_dll = path_dll;

    }

    /**
     * Проверяем на наличие публикации с таким наименованием
     *
     * @param public_name
     */
    public cww_comm(String public_name) {
        this.public_name = public_name;
    }

    /**
     * Публикация базы
     *
     * @return сайт, пул, путь
     */
    public Map<String, String> pub_base1C()
    {
        Map<String, String> pub = new HashMap<>();
        pub.put("site", site);
        pub.put("pool", pool);
        pub.put("path", path+pool+"\\"+public_name);
        return pub;

    }

    /**
     * Добавление новой платформы:
     * @return полный путь до dll, версия платформы
     */
    public Map<String, String> add_isapi()
    {
        Map<String, String> isapi = new HashMap<>();
        isapi.put("path_dll", path_dll);
        isapi.put("groupId", "1C_"+ver_pl);
        return isapi;
    }

    /**
     * Удаление публикации:
     * @return пул, путь до приложения
     */
    public Map<String, String> remove_public()
    {
        Map<String, String> remout = new HashMap<>();
        remout.put("pool", pool);
        remout.put("path_app", "\\"+pool+"\\"+public_name);
        return remout;
    }


}