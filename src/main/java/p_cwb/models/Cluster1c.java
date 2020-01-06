package p_cwb.models;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "cluster1c")
public class Cluster1c {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long clusterId;
    private int mainPort;

    private String clusterName;
    @OneToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany(mappedBy = "cluster1c", fetch = FetchType.LAZY, orphanRemoval = true,cascade =CascadeType.ALL)
    private List<Bd1c> bd1cs;

    @OneToMany(mappedBy = "cluster1c", fetch = FetchType.LAZY, orphanRemoval = true,cascade =CascadeType.ALL)
    private List<Server1c> server1cs;


    public Cluster1c(){}

    public Cluster1c(int mainPort, String clusterName, Service service) {
        this.mainPort = mainPort;
        this.clusterName = clusterName;
        this.service = service;
    }

    public Cluster1c(int mainPort, String clusterName, Service service, List<Bd1c> bd1cs, List<Server1c> server1cs) {
        this.mainPort = mainPort;
        this.clusterName = clusterName;
        this.service = service;
        this.addBd1c(bd1cs);
        this.server1cs = server1cs;
    }

    public void addServer1c(Server1c server1c){
        if(server1cs==null)
            server1cs=new LinkedList<>();
        if(!server1cs.contains(server1c)) {
            server1cs.add(server1c);
            server1c.setCluster1c(this);
        }
    }
    public void addServer1c(List<Server1c> server1cs) {
        for(Server1c server1c:server1cs)
            addServer1c(server1c);
    }

    public void addBd1c(Bd1c bd1c){
        if(bd1cs==null)
           bd1cs=new LinkedList<>();
        if(!bd1cs.contains(bd1c)) {
            bd1cs.add(bd1c);
            bd1c.setCluster1c(this);
        }
    }
    public void addBd1c(List<Bd1c> bd1cs){
        for(Bd1c bd1c:bd1cs)
            addBd1c(bd1c);
    }
    public int getMainPort() {
        return mainPort;
    }

    public void setMainPort(int mainPort) {
        this.mainPort = mainPort;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Service getService() {
        return service;
    }

    protected void setService(Service service) {
        this.service = service;
    }

    public List<Bd1c> getBd1cs() {
        return bd1cs;
    }

    public void setBd1cs(List<Bd1c> bd1cs) {
        this.bd1cs = bd1cs;
    }

    public List<Server1c> getServer1cs() {
        return server1cs;
    }

    public void setServer1cs(List<Server1c> server1cs) {
        this.server1cs = server1cs;
    }

    public long getClusterId() {
        return clusterId;
    }
}
