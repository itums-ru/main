package p_cwb.models;

import javax.persistence.*;

@Entity
public class Server1c {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long server1cId;

    private String name; //Компьютер
    private int ipport; //IP port

    @ManyToOne
    @JoinColumn(name = "cluster1c_id")
    private Cluster1c cluster1c;
    public Server1c(){}

    public Server1c(String name, int ipport) {
        this.name = name;
        this.ipport = ipport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIpport() {
        return ipport;
    }

    public void setIpport(int ipport) {
        this.ipport = ipport;
    }

    public Cluster1c getCluster1c() {
        return cluster1c;
    }

    protected void setCluster1c(Cluster1c cluster1c) {
        this.cluster1c = cluster1c;
    }
}
