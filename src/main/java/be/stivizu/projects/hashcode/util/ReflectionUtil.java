package be.stivizu.projects.hashcode.util;

import be.stivizu.projects.hashcode.algorithm.Algorithm;
import org.reflections.Reflections;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionUtil {

    private static final Reflections REFLECTIONS = new Reflections("be.stivizu.projects.hashcode");

    public static List<Algorithm> getClassReferencesForClassesExtendingGivenBaseClass(final Class clazz) {
        final Set<Class<?>> classes = REFLECTIONS.getSubTypesOf(clazz);
        if (classes.isEmpty()) {
            throw new RuntimeException("Could not find any class extending the given class <" + clazz.getSimpleName() + ">");
        }
        return classes.stream()
                .map(ReflectionUtil::getNewInstanceForClassReference)
                .map(aClass -> (Algorithm) aClass)
                .collect(Collectors.toList());
    }

    private static Object getNewInstanceForClassReference(final Class clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Unable to create a new instance for class <" + clazz.getSimpleName() + ">");
        }
    }

}
