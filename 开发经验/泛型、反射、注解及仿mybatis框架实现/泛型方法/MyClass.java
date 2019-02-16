public class MyClass {

    // 通用的遍历List集合的方法
    public static <T> void print(List<T> list) {// <T>表示声明一个泛型类型T，必须在方法声明的返回值之前，方法修饰符之后声明，具体类型在使用的时候确定
        for (T element : list) {
            System.out.println(element);
        }
    }

    // 通用的打印对象类型方法
    public static <T> void printType(T t) {// <T>表示声明一个泛型类型T，必须在方法声明的返回值之前，方法修饰符之后声明，具体类型在使用的时候确定
        String type = "未知类型";
        if (t instanceof String) {
            type = "String类型";
        } else if (t instanceof Integer) {
            type = "Integer类型";
        } else if (t instanceof Double) {
            type = "Double类型";
        }
        System.out.println(type);
    }

    // 通用的实例化对象方法
    public static <T> T instantiateObj(Class<T> clazz) throws InstantiationException, IllegalAccessException {// <T>表示声明一个泛型类型T，必须在方法声明的返回值之前，方法修饰符之后声明，具体类型在使用的时候确定
        T obj = clazz.newInstance();
        return obj;
    }

    // ----------华---丽---的---分---割---线----------
	// ----------华---丽---的---分---割---线----------
	// ----------华---丽---的---分---割---线----------

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        //
        List<String> list = new ArrayList<String>();
        list.add("刘备");
        list.add("孙权");
        list.add("曹操");
        MyClass.print(list);
        //
        MyClass.printType("你好");
        MyClass.printType(12);
        MyClass.printType(12.22);
        //
        String str = MyClass.instantiateObj(String.class);
        System.out.println(str instanceof String);
    }
}