package cwc_cons;

/**
 * Класс описания сексии Администраторы
 */
public class Adm1C {
    /*
    Имя
     */
    private String name;
    /*
    Описание
     */
    private String desciption;
    /*
    Аутентификация паролем
     */
    private Boolean authpass;
    /*
    pass
     */
    private String pass;
    /*
    pass2
     */
    private String pass2;
    /*
    Аутентификация ОС
     */
    private Boolean aos;
    /*
    Пользователь
     */
    private String user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Boolean getAuthpass() {
        return authpass;
    }

    public void setAuthpass(Boolean authpass) {
        this.authpass = authpass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public Boolean getAos() {
        return aos;
    }

    public void setAos(Boolean aos) {
        this.aos = aos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Конструктор по умолчанию для установки авторизации по логину\паролю
     * @param name
     * @param authpass
     * @param pass
     * @param pass2
     */
    public Adm1C(String name, Boolean authpass, String pass, String pass2) {
        this.name = name;
        this.authpass = authpass;
        this.pass = pass;
        this.pass2 = pass2;
    }

    /**
     * Устанавливаем все поля
     * @param name
     * @param desciption
     * @param authpass
     * @param pass
     * @param pass2
     * @param aos
     * @param user
     */
    public Adm1C(String name, String desciption, Boolean authpass, String pass, String pass2, Boolean aos, String user) {
        this.name = name;
        this.desciption = desciption;
        this.authpass = authpass;
        this.pass = pass;
        this.pass2 = pass2;
        this.aos = aos;
        this.user = user;
    }

    /**
     * Устанавливаем афторизацию ОС
     * @param name
     * @param aos
     * @param user
     */
    public Adm1C(String name, Boolean aos, String user) {
        this.name = name;
        this.aos = aos;
        this.user = user;
    }
}
