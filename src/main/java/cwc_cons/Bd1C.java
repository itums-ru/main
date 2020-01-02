package cwc_cons;

import java.time.format.DateTimeFormatter;


/**
 * Класс описания сессии Базы
 */
public class Bd1C {
    private String sb_name;                     //имя
    private String sb_prop;                     //описание
    private String sb_conn_sec;                 //защищенное соединение
    private String sb_server;                   //сервер бд
    private Type_bd sb_type_server;             //тип субд
    private String sbd_name;                    //бд(наименование в консоли сервера)
    private String sb_user_name;                //пользователь сервера бд
    private String sb_user_pass;                //пароль сервера бд
    private boolean sb_lic;                     //разрешить выдачу лицензий серверов бд
    private boolean sb_block;                   //блокировка начала сеансов выключена
    private DateTimeFormatter sb_date_begin;    //Начало(дата и время начала блокировки)
    private DateTimeFormatter sb_date_end;      //конец(дата и время окончания блокировки
    private String sb_message;                  //сообщение
    private String sb_code;                     //код разрешения
    private Boolean sb_order;                   //блокировка регламентных заданий включена
    private String sb_external;                 //внешнее управление сеансами
    private boolean sb_necessarily;             //обязательное использование внешнего управления
    private String sb_profile;                  //профиль безопасности
    private String sb_profile_sec;              //профиль безопасности безопасног режима

    /**
     * Обязательный конструктор по умолчанию
     * @param sb_name
     * @param sb_conn_sec
     * @param sb_server
     * @param sb_type_server
     * @param sbd_name
     * @param sb_user_name
     * @param sb_user_pass
     */
    public Bd1C(String sb_name, String sb_conn_sec, String sb_server, Type_bd sb_type_server, String sbd_name, String sb_user_name, String sb_user_pass) {
        this.sb_name = sb_name;
        this.sb_conn_sec = sb_conn_sec;
        this.sb_server = sb_server;
        this.sb_type_server = sb_type_server;
        this.sbd_name = sbd_name;
        this.sb_user_name = sb_user_name;
        this.sb_user_pass = sb_user_pass;
    }

    public Bd1C(String sb_name, String sb_prop, String sb_conn_sec, String sb_server, Type_bd sb_type_server, String sbd_name, String sb_user_name, String sb_user_pass, boolean sb_lic, boolean sb_block, DateTimeFormatter sb_date_begin, DateTimeFormatter sb_date_end, String sb_message, String sb_code, Boolean sb_order, String sb_external, Boolean sb_necessarily, String sb_profile, String sb_profile_sec) {
        this.sb_name = sb_name;
        this.sb_prop = sb_prop;
        this.sb_conn_sec = sb_conn_sec;
        this.sb_server = sb_server;
        this.sb_type_server = sb_type_server;
        this.sbd_name = sbd_name;
        this.sb_user_name = sb_user_name;
        this.sb_user_pass = sb_user_pass;
        this.sb_lic = sb_lic;
        this.sb_block = sb_block;
        this.sb_date_begin = sb_date_begin;
        this.sb_date_end = sb_date_end;
        this.sb_message = sb_message;
        this.sb_code = sb_code;
        this.sb_order = sb_order;
        this.sb_external = sb_external;
        this.sb_necessarily = sb_necessarily;
        this.sb_profile = sb_profile;
        this.sb_profile_sec = sb_profile_sec;
    }

    public Bd1C(String sb_name, String sb_server, Type_bd sb_type_server, String sbd_name, String sb_user_name, boolean sb_lic, boolean sb_block, boolean sb_order) {
        this.sb_name = sb_name;
        this.sb_server = sb_server;
        this.sb_type_server = sb_type_server;
        this.sbd_name = sbd_name;
        this.sb_user_name = sb_user_name;
        this.sb_lic = sb_lic;
        this.sb_block = sb_block;
        this.sb_order = sb_order;
    }
}
