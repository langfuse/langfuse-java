package com.langfuse.api.spi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceLoader;

/**
 * Utility for loading SPI implementations via {@link ServiceLoader}.
 *
 * <p>Attempts multiple classloader strategies to ensure discovery works across
 * different deployment environments (application servers, OSGi, modular classloaders).
 *
 * @author Eric Deandrea
 */
public final class ServiceLoaderHelper {

    private ServiceLoaderHelper() {
    }

    /**
     * Loads a single factory of the given type, if available.
     *
     * @param <T> the factory type
     * @param clazz the factory class to load
     * @return an {@link Optional} containing the factory, or empty if none found
     */
    public static <T> Optional<T> loadFactory(Class<T> clazz) {
        var factories = loadFactories(clazz, null);
        return factories.isEmpty() ? Optional.empty() : Optional.ofNullable(factories.iterator().next());
    }

    /**
     * Loads all factories of the given type using the thread context classloader.
     *
     * @param <T> the factory type
     * @param clazz the factory class to load
     * @return a collection of discovered factories (may be empty)
     */
    public static <T> Collection<T> loadFactories(Class<T> clazz) {
        return loadFactories(clazz, null);
    }

    /**
     * Loads all factories of the given type using the specified classloader.
     * Falls back to this class's classloader if the initial lookup finds nothing.
     *
     * @param <T> the factory type
     * @param clazz the factory class to load
     * @param classLoader the classloader to use, or {@code null} for the thread context classloader
     * @return a collection of discovered factories (may be empty)
     */
    public static <T> Collection<T> loadFactories(Class<T> clazz, ClassLoader classLoader) {
        var factories = (classLoader != null)
                ? loadAll(ServiceLoader.load(clazz, classLoader))
                : loadAll(ServiceLoader.load(clazz));

        if (factories.isEmpty()) {
            factories = loadAll(ServiceLoader.load(clazz, ServiceLoaderHelper.class.getClassLoader()));
        }

        return factories.stream()
                        .filter(Objects::nonNull)
                        .toList();
    }

    private static <T> List<T> loadAll(ServiceLoader<T> loader) {
        List<T> list = new ArrayList<>();
        loader.iterator().forEachRemaining(list::add);
        return list;
    }
}
