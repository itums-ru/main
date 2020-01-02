package cwc_cons;

import java.util.List;

public class Cluster1C {

    private String clusterName;             //Содержит удобочитаемое имя кластера серверов 1С:Предприятия.
    private String hostName;                //Имя (символический или IP-адрес) компьютера, на котором расположен реестр кластера и процесс главного менеджера кластера.
    private int mainPort;                   //Содержит номер основного IP-порта менеджера кластера.
    private int expirationTimeout;          //Время принудительного завершения
    private int maxMemorySize;              //Определяет объем виртуального адресного пространства (в килобайтах), занятого рабочим процессом, превышение которого приведет к перезапуску рабочего процесса.
    private int lifeTimeLimit;              //Период перезапуска рабочих процессов кластера
    private int errorsCountThreshold;       //Допустимое отклонение количества ошибок сервера
    private int maxMemoryTimeLimit;         //Определяет максимальный период превышения критического объема памяти в секундах.
    private boolean killProblemProcesses;   //Режим принудительного завершения проблемных процессов.
    private int securityLevel;              //Определяет уровень безопасности соединений менеджера кластера.
    private int loadBalancingMode;          //Режим выбора рабочего процесса для соединения.
    private int sessionFaultToleranceLevel; //Определяет уровень отказоустойчивости кластера: количество рабочих серверов кластера, выход из строя которых не приводит к аварийному завершению сеансов.
    public Cluster1C(String clusterName, String hostName, int mainPort){
        this.clusterName=clusterName;
        this.hostName=hostName;
        this.mainPort=mainPort;
    }

    public String getClusterName() {
        return clusterName;
    }

    public String getHostName() {
        return hostName;
    }

    public int getMainPort() {
        return mainPort;
    }

    public int getExpirationTimeout() {
        return expirationTimeout;
    }

    public int getMaxMemorySize() {
        return maxMemorySize;
    }

    public int getLifeTimeLimit() {
        return lifeTimeLimit;
    }

    public int getErrorsCountThreshold() {
        return errorsCountThreshold;
    }

    public int getMaxMemoryTimeLimit() {
        return maxMemoryTimeLimit;
    }

    public boolean isKillProblemProcesses() {
        return killProblemProcesses;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public int getLoadBalancingMode() {
        return loadBalancingMode;
    }

    public int getSessionFaultToleranceLevel() {
        return sessionFaultToleranceLevel;
    }

    public Load_mode getLm() {
        return lm;
    }

    public Adm1C getAdm1c() {
        return adm1c;
    }

    public Session getSession() {
        return session;
    }

    public List<Server1c> getServers() {
        return servers;
    }

    private Load_mode lm;

    /**
     * Администраторы
     */
    private Adm1C adm1c;
    /**
     * Сессии
     */
    private Session session;
    private List<Server1c> servers;

}
