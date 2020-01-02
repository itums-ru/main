package cwc_cons;


import java.util.List;

public class Services implements Services_1c{
    public String getImagePath() {
        return ImagePath;
    }

    /*
        Описание
         */
    private String Description;
    /*
    Отображаемое имя
     */
    private String DisplayName;
    /*
    Имя службы
     */
    private String ServiceName;
    /*
    Путь запуска исполняемого файла службы
     */
    private String ImagePath;
    /*
    Имя пользователя для запуска службы
     */
    private String ObjectName;
    /*
    Тип запуска
     */
    private int Start;

    @Override
    public String get_dir_install() {
        return null;
    }

    @Override
    public String get_dir_conf() {
        return null;
    }

    @Override
    public boolean get_debug() {
        return false;
    }

    @Override
    public String get_range_port() {
        return null;
    }

    @Override
    public int get_ip_port() {
        return 0;
    }

    @Override
    public int get_main_port() {
        return 0;
    }

    @Override
    public String get_cluster_name() {
        return null;
    }

    @Override
    public List<String> get_servers() {
        return null;
    }

    @Override
    public Cluster1C get_cluster() {
        return null;
    }

    @Override
    public Type_bd gettype() {
        return null;
    }

    @Override
    public String getversion() {
        return null;
    }

    public Services(String imagePath) {
        ImagePath = imagePath;
    }

    public Services() { }

    /**
     *
     * @param description
     * @param displayName
     * @param serviceName
     * @param imagePath
     * @param objectName
     * @param start
     */

    public Services(String description, String displayName, String serviceName, String imagePath, String objectName, int start) {
        Description = description;
        DisplayName = displayName;
        ServiceName = serviceName;
        ImagePath = imagePath;
        ObjectName = objectName;
        Start = start;
    }


}
