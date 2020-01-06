package p_cwb.models;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long service_id;
    private String description;
    /*
    Отображаемое имя
     */
    private String displayName;
    /*
    Имя службы
     */
    private String serviceName;
    /*
    Путь запуска исполняемого файла службы
     */
    private String imagePath;
    /*
    Имя пользователя для запуска службы
     */
    private String objectName;
    /*
    Тип запуска
     */
    private int start;
    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "cluster_id")
    private Cluster1c cluster1c;

    public Service(){}


    public Service(String description, String displayName, String serviceName, String imagePath, String objectName, int start, Cluster1c cluster1c) {
        this.description = description;
        this.displayName = displayName;
        this.serviceName = serviceName;
        this.imagePath = imagePath;
        this.objectName = objectName;
        this.start = start;
        this.setCluster1c(cluster1c);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public Server getServer() {
        return server;
    }

    protected void setServer(Server server) {
        this.server = server;
    }

    public Cluster1c getCluster1c() {
        return cluster1c;
    }

    public void setCluster1c(Cluster1c cluster1c) {
        this.cluster1c = cluster1c;
        cluster1c.setService(this);
    }
}
