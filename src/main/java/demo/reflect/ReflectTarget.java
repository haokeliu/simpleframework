package demo.reflect;

public class ReflectTarget {
    //--------构造函数--------
    //（默认带参构造函数）
    ReflectTarget(String str){
        System.out.println("默认构造函数s= " + str);
    }
    //（无参构造函数）
    public ReflectTarget(){
        System.out.println("调用了共有的无参构造函数");
    }
    // 有一个参数的构造函数
    public ReflectTarget(char name){
        System.out.println("调用有一个参数的构造方法，参数为 c= " +name);
    }
    // 有多个参数的构造函数
    public ReflectTarget(String name, int index){
        System.out.println("调用了带有多个参数的构造方法，参数为 " + name + " 【序号】 " + index);
    }
    // 受保护的构造函数
    protected ReflectTarget(boolean n){
        System.out.println("受保护的构造方法n " + n);
    }
    // 私有的构造函数
    private ReflectTarget(int index){
        System.out.println("私有构造函数 序号n " + index);
    }

    // ---------------------字段---------------------
    public String name;
    protected int index;
    char type;
    private String targetInfo;

    @Override
    public String toString() {
        return "ReflectTarget{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", type=" + type +
                ", targetInfo='" + targetInfo + '\'' +
                '}';
    }

    // ----------------成员方法----------------
    public void show1(String s){
        System.out.println("调用共有的，String参数的show1（） s = " +s);
    }
    protected  void  show2(){
        System.out.println("调用受保护的，无参的show2（）");
    }
    void show3(){
        System.out.println("调用默认的，无参的show3（）");
    }
    private String show4(int index){
        System.out.println("调用私有的，有返回值的，int参数的show4（）index = " +index);
        return "show4result";
    }
    public static void main(String[] args) throws ClassNotFoundException {
        // 1.
        ReflectTarget reflectTarget = new ReflectTarget();
        Class reflectClass1 = reflectTarget.getClass();
        System.out.println("1st:" + reflectClass1.getName());
        // 2.
        Class reflectClass2 = ReflectTarget.class;
        System.out.println("2bd" + reflectClass2.getName());
        // 判断二者获取的class对象是否一致
        System.out.println(reflectClass1 == reflectClass2);
        // 3.
        Class reflectClass3 = Class.forName("demo.reflect.ReflectTarget");
        System.out.println("3bd" + reflectClass3.getName());
        System.out.println(reflectClass2 == reflectClass3);
        
    }
}
