package annotations;

import com.google.common.reflect.ClassPath;
import exception.MyInjectException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@AutoInjectable(packages = {"customers", "annotations", "repository", "sorter", "typeOfContracts", "validators", "wortWithFiles"})
public class Injector {
    private static final Logger LOGGER = Logger.getRootLogger();

    /**
     * Injects dependencies into the fields of the passed object.
     *
     * @param o object in the fields of which you want to inject the dependency
     */
    public static <T> void inject(T o) throws MyInjectException {


        try {
            //получаем все пакеты
            String[] packages = Injector.class.getAnnotation(AutoInjectable.class).packages();
            //проходимся по всем полям переданного класса
            for (Field field : o.getClass().getDeclaredFields()) {
                //находим поля, помеченные аннотацией
                if (field.isAnnotationPresent(MyInject.class)) {
                    //получаем тип класса
                    Class cls = field.getAnnotation(MyInject.class).clazz();
                    field.setAccessible(true);
                    List<Object> list = new ArrayList<>();
                    ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
                    for (String pkg : packages) {
                        Set<ClassPath.ClassInfo> allClasses = classPath.getTopLevelClassesRecursive(pkg);
                        //проходимся по всем классам из пакета
                        for (ClassPath.ClassInfo ci : allClasses) {
                            if (cls.isAssignableFrom(ci.load()) && !ci.load().isInterface()) {
                                list.add(ci.load().newInstance());
                            }
                        }
                    }
                    //если коллекция , то возвращаем лист
                    if (Collection.class.isAssignableFrom(field.getType())) {
                        field.set(o, list);
                    } else {
                        if (list.size() != 1) {
                            try {
                                throw new MyInjectException("Not found or found more than 1 class that can be injected into the field " + field.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            field.set(o, list.get(0));
                        }
                    }
                    //считается, что у нас только 1 поле может быть помеченно такой аннотацией
                    break;
                }
            }
        } catch (IllegalAccessException | IOException | InstantiationException e) {
            LOGGER.error(e.getMessage());
            throw new MyInjectException(e);
        }
    }
}
