public class MyGenericClass<T> {

    private static final int DEFAULT_CAPACITY = 10;// 默认数组容量

    private Object[] array;

    public MyGenericClass() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    public MyGenericClass(T[] array) {
        this.array = array;
    }

    public void setElement(int index, T element) {
        if (index > this.array.length - 1 || index < 0) {
            throw new IndexOutOfBoundsException("数组下标越界异常");
        }
        this.array[index] = element;
    }

    @SuppressWarnings("unchecked")
    public T getElement(int index) {
        if (index > this.array.length - 1 || index < 0) {
            throw new IndexOutOfBoundsException("数组下标越界异常");
        }
        return (T) this.array[index];
    }

    // ----------华---丽---的---分---割---线----------
    // ----------华---丽---的---分---割---线----------
    // ----------华---丽---的---分---割---线----------

    public static void main(String[] args) {
        //
        MyGenericClass<Integer> gen1;
        gen1 = new MyGenericClass<Integer>();// 实际的类型在用的时候指定
        gen1.setElement(1, 123);
        Integer element1 = gen1.getElement(1);
        System.out.println(element1);
        //
        MyGenericClass<String> gen2;
        String[] stringArr = new String[] { "刘备", "孙权", "曹操" };
        gen2 = new MyGenericClass<String>(stringArr);// 实际的类型在用的时候指定
        gen2.setElement(1, "孙坚");
        String element2 = gen2.getElement(1);
        System.out.println(element2);

    }
}