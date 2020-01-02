package cww_p;

public class Template_cww {
    private Boolean form_name;
    private String pool;
    private String bit;
    private String platform_path;

    /**
    *@param form_name - разрешение на формирование имени публикуемой базы из пути публикуемой базы
     *@param pool - имя пула, пример: 1C_8.3.3.3, для *.config
     *@param bit - разрядность, пример: x32, для *.config
     *@param platform_path - путь к dll платформы, пример: C:\Program Files (x86)\1cv8\8.3.13.1222\bin\wsisapi.dll
     */
    public Template_cww(boolean form_name, String pool, String bit, String platform_path)
    {
        this.form_name=form_name;
        this.pool=pool;
        this.bit=bit;
        this.platform_path=platform_path;
    }

    public Boolean getForm_name() {
        return form_name;
    }

    public String getBit() {
        return bit;
    }

    public String getPlatform_path() {
        return platform_path;
    }

    public String getPool() {
        return pool;
    }

    public void setBit(String bit) {
        this.bit = bit;
    }

    public void setForm_name(Boolean form_name) {
        this.form_name = form_name;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public void setPlatform_path(String platform_path) {
        this.platform_path = platform_path;
    }
}
