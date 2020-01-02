package cwc_cons;

public enum Type_launch {

    Отложенный_запуск(1), Автоматически(2), Вручную(3), Отключена(4);
     private int num;
    Type_launch(int num){
        this.num=num;
    }

    public int getNum() {
        return num;
    }
}
