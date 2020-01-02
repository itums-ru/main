package cwc_cons;

import java.util.List;

public class Server1c {
    public Server1c(String name, int ipport){
        this.name=name;
        this.ipport=ipport;
    }
    /*
    Описание сервера
     */
    private String Description;
    /*
    Компьютер
     */
    private String name;
    /*
    IP port
     */
    private int ipport;
    /*
    Диапазон рабочих портов
     */
    private String rangeip;
    /*

    Максимальный объем памяти рабочих процеесов в байтах
     */
    private int mmcow;
    /*
    Безопасный расход памяти за один вызов в байтах
     */
    private int smupc;
    /*
    Объем памяти рабочих процессов до которого сервер считается производительным в байтах
     */
    private int wm;

    //Параметры рабочих процеесов
    /*
    Количество ИБ на процесс
     */
    private int countib;
    /*
    Количество соединений на процесс
     */
    private int countconnproc;
    /*
    Порт главного менеджера кластера
     */
    private int portmainman;
    //////////////////////////////

    /*
    Менеджер под каждый сервис
     */
    private boolean manserv;
    /*
    Центральный сервер
     */
    private boolean centralser;

    /*
    Менеджеры кластера
     */
    private List<Manager_cluster> mc;

    public String getDescription() {
        return Description;
    }

    public String getName() {
        return name;
    }

    public int getIpport() {
        return ipport;
    }

    public String getRangeip() {
        return rangeip;
    }

    public int getMmcow() {
        return mmcow;
    }

    public int getSmupc() {
        return smupc;
    }

    public int getWm() {
        return wm;
    }

    public int getCountib() {
        return countib;
    }

    public int getCountconnproc() {
        return countconnproc;
    }

    public int getPortmainman() {
        return portmainman;
    }

    public boolean isManserv() {
        return manserv;
    }

    public boolean isCentralser() {
        return centralser;
    }

    public List<Manager_cluster> getMc() {
        return mc;
    }
}
