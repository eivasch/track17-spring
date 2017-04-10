package track.container;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import track.container.config.Bean;
import track.container.config.Property;
import track.container.config.ValueType;

/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class Container {
    private List<Bean> beans;
    private Map<String, Object> objByName;
    private Map<String, Object> objByClassName;


    public Container(List<Bean> beans) {
        objByName = new HashMap<>();
        objByClassName = new HashMap<>();
        this.beans = beans;
    }

    private String getSetMethodName(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private void setUsingMethod(String name, Class clazz, Object value, Object newObject) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        String methodName = getSetMethodName(name);
        Method method = clazz.getDeclaredMethod(methodName, field.getType());
        method.invoke(newObject, value);
    }d main(String[] args) throws Exception {


    private Object createObject(Bean bean) throws Exception {
        Class clazz = Class.forName(bean.getClassName());
        Object newObject = clazz.newInstance();
        Map<String, Property> properties = bean.getProperties();
        for (Property property: properties.values()) {
            if (property.getType().equals(ValueType.VAL)) {
                setUsingMethod(property.getName(), clazz, Integer.parseInt(property.getValue()), newObject);
            } else {
                Object refObject = getById(property.getValue());
                setUsingMethod(property.getName(), clazz, refObject, newObject);
            }
        }
        objByClassName.put(bean.getClassName(), newObject);
        objByName.put(bean.getId(), newObject);
        return newObject;
    }

    /**
     *  Вернуть объект по имени бина из конфига
     *  Например, Car car = (Car) container.getById("carBean")
     */
    public Object getById(String id) throws Exception {
        if (objByName.containsKey(id)) {
            return objByName.get(id);
        } else {
            for (Bean bean : beans) {
                if (bean.getId().equals(id)) {
                    return createObject(bean);
                }
            }
        }
        return null;
    }

    /**
     * Вернуть объект по имени класса
     * Например, Car car = (Car) container.getByClass("track.container.beans.Car")
     */
    public Object getByClass(String className) throws Exception {
        if (objByClassName.containsKey(className)) {
            return objByClassName.get(className);
        } else {
            for (Bean bean : beans) {
                if (bean.getClassName().equals(className)) {
                    return createObject(bean);
                }
            }
        }
        return null;
    }
}
