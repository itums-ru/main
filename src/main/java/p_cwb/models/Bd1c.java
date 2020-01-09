package p_cwb.models;

import javax.persistence.*;

import java.util.Date;
@Entity
public class Bd1c {


        @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long sb_id;
    @Column(nullable = false)
    private String sb_name;                     //имя
    private String sb_prop;                     //описание
    @Column(nullable = false)
    private Boolean sb_conn_sec;                 //защищенное соединение
    @Column(nullable = false)
    private String sb_server;                   //сервер бд
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type_bd sb_type_server;             //тип субд
    @Column(nullable = false)
    private String sbd_name;                    //бд(наименование в консоли сервера)
    @Column(nullable = false)
    private String sb_user_name;                //пользователь сервера бд
    @Column(nullable = false)
    private String sb_user_pass;                //пароль сервера бд
    private boolean sb_lic;                     //разрешить выдачу лицензий серверов бд
    private boolean sb_block;                   //блокировка начала сеансов выключена
    private Date sb_date_begin;                 //Начало(дата и время начала блокировки)
    private Date sb_date_end;                   //конец(дата и время окончания блокировки

    @ManyToOne
    @JoinColumn(name = "cluster1c_id")
    private Cluster1c cluster1c;
    public Bd1c(){}

    public Bd1c(String sb_name, Boolean sb_conn_sec, String sb_server, Type_bd sb_type_server, String sbd_name, String sb_user_name, String sb_user_pass) {
        this.sb_name = sb_name;
        this.sb_conn_sec = sb_conn_sec;
        this.sb_server = sb_server;
        this.sb_type_server = sb_type_server;
        this.sbd_name = sbd_name;
        this.sb_user_name = sb_user_name;
        this.sb_user_pass = sb_user_pass;
    }

    public Bd1c(String sb_name, String sb_prop, Boolean sb_conn_sec, String sb_server, Type_bd sb_type_server, String sbd_name, String sb_user_name, String sb_user_pass, boolean sb_lic, boolean sb_block, Date sb_date_begin, Date sb_date_end) {
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
    }

    public String getSb_name() {
        return sb_name;
    }

    public void setSb_name(String sb_name) {
        this.sb_name = sb_name;
    }

    public String getSb_prop() {
        return sb_prop;
    }

    public void setSb_prop(String sb_prop) {
        this.sb_prop = sb_prop;
    }

    public Boolean getSb_conn_sec() {
        return sb_conn_sec;
    }

    public void setSb_conn_sec(Boolean sb_conn_sec) {
        this.sb_conn_sec = sb_conn_sec;
    }

    public String getSb_server() {
        return sb_server;
    }

    public void setSb_server(String sb_server) {
        this.sb_server = sb_server;
    }

    public Type_bd getSb_type_server() {
        return sb_type_server;
    }

    public void setSb_type_server(Type_bd sb_type_server) {
        this.sb_type_server = sb_type_server;
    }

    public String getSbd_name() {
        return sbd_name;
    }

    public void setSbd_name(String sbd_name) {
        this.sbd_name = sbd_name;
    }

    public String getSb_user_name() {
        return sb_user_name;
    }

    public void setSb_user_name(String sb_user_name) {
        this.sb_user_name = sb_user_name;
    }

    public String getSb_user_pass() {
        return sb_user_pass;
    }

    public void setSb_user_pass(String sb_user_pass) {
        this.sb_user_pass = sb_user_pass;
    }

    public boolean isSb_lic() {
        return sb_lic;
    }

    public void setSb_lic(boolean sb_lic) {
        this.sb_lic = sb_lic;
    }

    public boolean isSb_block() {
        return sb_block;
    }

    public void setSb_block(boolean sb_block) {
        this.sb_block = sb_block;
    }

    public Date getSb_date_begin() {
        return sb_date_begin;
    }

    public void setSb_date_begin(Date sb_date_begin) {
        this.sb_date_begin = sb_date_begin;
    }

    public Date getSb_date_end() {
        return sb_date_end;
    }

    public void setSb_date_end(Date sb_date_end) {
        this.sb_date_end = sb_date_end;
    }

    public Cluster1c getCluster1c() {
        return cluster1c;
    }

    protected void setCluster1c(Cluster1c cluster1c) {
        this.cluster1c = cluster1c;
    }
}
