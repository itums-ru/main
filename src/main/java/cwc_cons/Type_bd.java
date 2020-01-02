package cwc_cons;

public enum Type_bd {
    MSSQLServer,
    Postgres,
    DB2,
    Oracle;

    private String bd;

    Type_bd(String bd) {
        this.bd = bd;
    }


    Type_bd() {

    }

    public String getBd() {
        return bd;
    }
}
