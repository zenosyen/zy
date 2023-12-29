package equation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection {
    public static void main(String[] args) {
        String className = "project4.equation.AddEquation";

        try {
            // 通过类名字符串获取 Class 对象
            Class<?> clazz = Class.forName(className);

            // 获取类的字段信息
            Field[] fields = clazz.getDeclaredFields();
            System.out.println("Fields:");
            for (Field field : fields) {
                System.out.println(field.getName());
            }

            // 获取类的方法信息
            Method[] methods = clazz.getDeclaredMethods();
            System.out.println("\nMethods:");
            for (Method method : methods) {
                System.out.println(method.getName());
            }

            // 获取类的构造方法信息
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            System.out.println("\nConstructors:");
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor);
            }

            // 使用无参构造方法创建实例
            Object instance = clazz.getDeclaredConstructor().newInstance();
            System.out.println("\nInstance created using default constructor: " + instance);

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
