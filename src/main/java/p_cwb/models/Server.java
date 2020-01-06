package p_cwb.models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "servers")
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//используется встроенный в бд механизм генерации последовательных значений(oracle и postgresql)
    private long serverId;
    @Column(nullable = false)
    private String server;
    private String ip;
    @OneToMany(mappedBy = "server",//имя атрибута ассоциации на стороне владельца
            fetch = FetchType.LAZY,//загружать в память только по требованию
            orphanRemoval = true,//удаление сирот
            cascade =CascadeType.ALL)//подгружение ассоциированных объектов-сущностей
    private List<Service> services;

    public Server(){}
    public Server(String server, String ip) {
        this.server = server;
        this.ip = ip;
    }
    public Server(String server, String ip, Service service) {
        this.server = server;
        this.ip = ip;
        addService(service);
    }
    public Server(String server, String ip, List<Service> services) {
        this.server = server;
        this.ip = ip;
        addService(services);
    }
    public void addService(List<Service> services){
        for(Service service:services)
            addService(service);
    }
    public void addService(Service service){
        if(services == null)
            services = new LinkedList<>();
        if(!services.contains(service)) {
            services.add(service);
            service.setServer(this);
        }

    }
    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<Service> getServices() {
        return services;
    }

    public long getServerId() {
        return serverId;
    }
}
