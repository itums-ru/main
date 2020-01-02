package cww_p;


import cwbi_p.ILogging;

public class Cww_cons implements ILogging {

    private String public_path;
    private String public_name;
    private String pool;
    private String bit;
    private String platform_path;
    private String guid;
    private Template_cww tc;
    public Cww_cons(){}

    /**
     * Конструктор принимает все параметры
     * @param public_path - путь публикуемой базы, пример: C:\\public, для *.vrd в ib
     * @param public_name - имя публикуемой базы, пример: /buh, для *.vrd в base
     * @param pool - имя пула, пример: 1C_8.3.3.3, для *.config
     * @param bit - разрядность, пример: x32, для *.config
     * @param platform_path - путь к dll платформы, пример: C:\Program Files (x86)\1cv8\8.3.13.1222\bin\wsisapi.dll
     * @param guid - уникальный идентификатор операции, пример: dgd67dh6e5vctcy65usc4, для *.vrd и *.config
     * @param tc - класс-шаблон содержащий параметры автозаполнения, для *.vrd и *.config, пример: form_name - bool - формировать наименование публикации из пути публикации, для *.vrd
     *                                                                       pool - имя пула, 1C_8.3.13.1222, для *.config
     *                                                                       bit - разрядность, пример: x32 , для *.config
     *                                                                       platform_path - путь к dll платформы, пример: C:\Program Files (x86)\1cv8\8.3.13.1222\bin\wsisapi.dll , для *.config
     *
     */
    public Cww_cons(String public_path, String public_name, String pool, String bit, String platform_path, String guid, Template_cww tc) {
        this.public_path = public_path;
        this.public_name = public_name;
        this.pool = pool;
        this.bit = bit;
        this.platform_path = platform_path;
        this.guid = guid;
        this.tc = tc;
    }

    /**
     *
     * @param public_path
     * @param pool
     * @param bit
     * @param guid
     * @param tc
     */
    public Cww_cons(String public_path,  String pool, String bit,String platform_path ,String guid, Template_cww tc) {
        this.public_path = public_path;
        this.platform_path = platform_path;
        this.pool = pool;
        this.bit = bit;
        this.guid = guid;
        this.tc = tc;
    }

    /**
     *
     * @param public_path
     * @param pool
     * @param bit
     * @param guid
     * @param tc
     */
    public Cww_cons(String public_path, String pool, String bit, String guid, Template_cww tc) {
        this.public_path = public_path;
        this.pool = pool;
        this.bit = bit;
        this.guid = guid;
        this.tc = tc;
    }

    public String getPublic_path() {
        return public_path;
    }

    public void setPublic_path(String public_path) {
        this.public_path = public_path;
    }

    public String getPublic_name() {
        return public_name;
    }

    public void setPublic_name(String public_name) {
        this.public_name = public_name;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getBit() {
        return bit;
    }

    public void setBit(String bit) {
        this.bit = bit;
    }

    public String getPlatform_path() {
        return platform_path;
    }

    public void setPlatform_path(String platform_path) {
        this.platform_path = platform_path;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Template_cww getTc() {
        return tc;
    }


    public void setTc(Template_cww tc) {
        this.tc = tc;
    }
    private boolean checkAllFields() {
        boolean check=true;
        if(public_path==null) {
            LOGGER.error("Отсутствует путь");
            check=false;
        }
        if(public_name==null) {
            if(tc.getForm_name()&&public_path!=null){
                LOGGER.info("Отсутствует имя публикации");
                String subStr=public_path.substring(0, public_path.lastIndexOf("&quot;;", public_path.length()-1));//отсекаем справа подстроку "&quot;;"
                int intSlash=-1, intTcz=-1;
                try {
                    intSlash = subStr.lastIndexOf("\\", subStr.length() - 1);//для файлового пути
                    intTcz = subStr.lastIndexOf(";", subStr.length() - 1);//для серверного пути
                }
                catch (StringIndexOutOfBoundsException e)
                {
                    public_name=subStr.substring(subStr.lastIndexOf(";", subStr.length() - 1)+1);
                }
                finally {
                    if(public_name==null){//если нашлось \, и ;
                        if(intSlash>intTcz)
                            public_name=subStr.substring(subStr.lastIndexOf("\\", subStr.length() - 1)+1);
                        if(intSlash<intTcz)
                            public_name=subStr.substring(subStr.lastIndexOf(";", subStr.length() - 1)+1);
                    }
                    //                    LOGGER.info("Имя публикации сформировано из пути публикации");
                }
            }
            else{
                LOGGER.error("Отсутствует разрешение для формирование имени публикации, либо отсутствует путь");
                check=false;
            }

        }
        if(pool==null)
            if(tc.getPool()==null){
                LOGGER.error("Отсутствует название пула в шаблоне");
                check=false;
            }
            else
                pool=tc.getPool();

        if(bit==null)
            if(tc.getBit()==null){
                LOGGER.error("Отсутствует разрядность платформы в шаблоне");
                check=false;
            }
            else
                bit=tc.getBit();

        if(platform_path==null)
            if(tc.getPlatform_path()==null){
                LOGGER.error("Отсутствует путь платформы в шаблоне");
                check=false;
            }
            else
                platform_path=tc.getPlatform_path();
        if (guid==null) {
            LOGGER.error("Отсутствует GUID");
            check = false;
        }
        return check;

    }



   /* public List<Map<String, String>> files = new ArrayList<>();

    public void addFile(String path, String content, String type) {
        Map<String, String> file = new HashMap<>();
        file.put("Path", path);
        file.put("Content", content);
        file.put("Type", type);
        files.add(file);
    }

    public static String readContent(String path) {
        StringBuilder s = new StringBuilder();
        BufferedReader cfgReader = null;
        try {
            int c = -1;
            s.ensureCapacity(200);
            cfgReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            while ((c = cfgReader.read()) != -1) {
                s.append((char) c);
            }
        } catch (Exception e) {
            LOGGER.error(e.toString());
        } finally {
            try {
                cfgReader.close();
            } catch (Exception e) {
                LOGGER.error(e.toString());
            }
        }
        return s.toString();
    }

    public String deleteFiles() {

        String paths = "";
        for (Map<String, String> mp : files)
            paths += mp.get("Path") + "\n";
        return paths;
    }

    public void changePathToBase(String path) {
        change(path, "ib");
    }
    public void changePubName(String name){
        change(name, "base");
    }
    private void change(String value, String changeField) {
        String default_vrd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "\r\n<point xmlns=\"http://v8.1c.ru/8.2/virtual-resource-system\"" +
                "\r\nxmlns:xs=\"http://www.w3.org/2001/XMLSchema\"" +
                "\r\nxmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
        for (Map<String, String> mp : files)
            if (mp.get("Type").equals("vrd")) {
                FileOutputStream fos = null;
                try {

                    fos = new FileOutputStream(mp.get("Path"), false);
                    if (value.equals("base"))
                        default_vrd += "\r\nbase=\"" + value + "\"" +
                                "\r\nib=" + extractValue(mp.get("Content"), "ib", "\n");
                    else
                        default_vrd += "\r\nbase=\"" + extractValue(mp.get("Content"), "base", "\n") + "\"" +
                                "\r\nib=" + value;

                } catch (FileNotFoundException | StringIndexOutOfBoundsException ex) {
                    try {
                        File file = new File(mp.get("Path"));
                        if (file.createNewFile())
                            fos = new FileOutputStream(mp.get("Path"), false);
                        default_vrd += "<ws enable=\"false\"/>";

                    } catch (IOException e) {
                        LOGGER.error(e.toString());
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex.toString() + " changeBase()");
                } finally {
                    try {
                        default_vrd += "\r\n<ws enable=\"false\"/>";
                        byte[] buffer = default_vrd.getBytes();
                        fos.write(buffer, 0, buffer.length);
                        LOGGER.info("The file " + mp.get("Path") + " was written");
                        fos.close();
                    } catch (Exception e) {
                        LOGGER.error(e.toString());
                    }
                }
            }
    }*/

    public StringBuilder createWeb_config() {
        StringBuilder web_config = new StringBuilder();
        if(checkAllFields())
            web_config.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "\r\n<configuration>" +
                "\r\n\t<system.webServer>" +
                "\r\n\t\t<handlers>"+
                "\r\n\t\t\t<add name=\"" + pool + "\" path=\"*\" verb=\"*\" modules=\"*\" scriptProcessor=\""+platform_path+"\" resourceType=\"Unspecified\""+ " requireAccess=" + "\"" + "None" + "\"" +"/>"+
                "\r\n\t\t</handlers>" +
                "\r\n\t</system.webServer>" +
                "\r\n</configuration>");
        else LOGGER.error("Файл web.config не сформирован");
        return web_config;
    }
    public StringBuilder createDefault_vrd(){
        StringBuilder default_vrd=new StringBuilder();
        if(checkAllFields())
            default_vrd.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "\r\n<point xmlns=\"http://v8.1c.ru/8.2/virtual-resource-system\"" +
                        "\r\nxmlns:xs=\"http://www.w3.org/2001/XMLSchema\"" +
                        "\r\nxmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+
                        "\r\nbase=\"\\"+public_name+"\""+
                        "\r\nib=\""+public_path+"\">");
        else LOGGER.error("Файл default.vrd не сформирован");
        return  default_vrd;
    }


}
