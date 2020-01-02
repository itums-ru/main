package cwbi_p;

import java.util.Random;

public interface ITemplateConfig {
    boolean auto_name=true,
            auto_oib=true,
            auto_id=true,
            auto_tree=true;

    String name_suf="-new";

    static String generate_name(String name){
        return name+name_suf;
    }
    static  String generate_tree(){
        return String.valueOf(new Random().nextInt(65536));
    }
    static  String generate_oib(){
        return String.valueOf(new Random().nextInt(65536));
    }
    static String generate_id(){
        StringBuilder id=new StringBuilder();
        id.ensureCapacity(37);
        id.append(part_id(8));
        id.append('-');
        for(int k=0;k<3;k++){
            id.append(part_id(4));
            id.append('-');
        }
        id.append(part_id(12));
        return id.toString();

    }
    static String part_id(int k){
        StringBuilder s=new StringBuilder();
        s.ensureCapacity(12);
        Random bool_rnd=new Random();
        for(int i=0;i<k;i++)
        {
            if(bool_rnd.nextBoolean())
                s.append((char)(97+(int)(Math.random()*26)));
            else
                s.append((char)(48+(int)(Math.random()*10)));
        }
        return s.toString();
    }


}
