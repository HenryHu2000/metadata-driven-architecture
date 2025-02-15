package org.softarch.metadriven.spec.parser;

import org.softarch.metadriven.model.entity.Model;
import org.softarch.metadriven.spec.annotation.Metadata;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

public class ModelBuilder {

  private String packageName;

  public ModelBuilder withPackageName(String packageName) {
    this.packageName = packageName;
    return this;
  }
  public Model build() {
    if (packageName == null) {
      throw new IllegalArgumentException("Package name not provided");
    }
    Model model = new Model();
    model.setName(packageName);
    model.setMetadataList(new ArrayList<>());
    if  (packageName.isEmpty()) {
      return model;
    }

    Reflections reflections = new Reflections(packageName, Scanners.SubTypes.filterResultsBy(c -> true));
    Set<Class<?>> allClasses = new HashSet<>(reflections.getSubTypesOf(Object.class));

    allClasses.forEach(clazz -> {
      if (clazz.isAnnotationPresent(Metadata.class)) {
        model.getMetadataList().add(new MetadataBuilder().withClass(clazz).build());
      }
    }
    );
    return model;
  }

  private Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
    if (packageName == null) {
      throw new IllegalArgumentException("Package name not provided");
    }
    InputStream stream = ClassLoader.getSystemClassLoader()
        .getResourceAsStream(packageName.replaceAll("[.]", "/"));
    if (stream == null) {
      return Set.of();
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    return reader.lines()
        .filter(line -> line.endsWith(".class"))
        .map(line -> getClass(line, packageName))
        .collect(Collectors.toSet());
  }

  private Class<?> getClass(String className, String packageName) {
    try {
      return Class.forName(packageName + "."
          + className.substring(0, className.lastIndexOf('.')));
    } catch (ClassNotFoundException e) {
      // handle the exception
    }
    return null;
  }
}
