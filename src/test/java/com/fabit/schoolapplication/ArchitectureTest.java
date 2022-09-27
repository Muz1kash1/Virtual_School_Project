package com.fabit.schoolapplication;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Gordeev_Artem
 */
public class ArchitectureTest {

  public static final List<String> ALLOWED_PACKAGES = asList(
    "java..",
    "javax..",
    "org.jetbrains.annotations..",
    "lombok..",
    "org.mapstruct",
    "org.slf4j.."
  );

  private static final JavaClasses APPLICATION_CLASSES = new ClassFileImporter().importPaths(
    findClassFiles("target/classes").toList().toArray(new String[]{})
  );

  @DisplayName("domain package should depends only on approved packages")
  @Test
  public void domainDependencyArchTest() {

    if (APPLICATION_CLASSES.isEmpty()) {
      fail("Classes of school application module were not found");
    }

    contextBusinessRule("com.fabit.schoolapplication.domain..", APPLICATION_CLASSES);
  }

//  @DisplayName("usecase package should depends on domain and approved packages")
//  @Test
//  public void useCaseDependencyArchTest() {
//
//    if (APPLICATION_CLASSES.isEmpty()) {
//      fail("Classes of school application module were not found");
//    }
//
//    ArchRuleDefinition
//      .classes()
//      .that()
//      .resideInAnyPackage("com.fabit.schoolapplication.application.usecase..")
//      .should()
//      .onlyDependOnClassesThat()
//      .resideInAnyPackage(
//        withAllowedPackages(
//          "com.fabit.schoolapplication.application.usecase..", "com.fabit.schoolapplication.domain..",
//          "org.springframework.stereotype.Service"
//        )
//      )
//      .check(APPLICATION_CLASSES);
//  }
//
//  @DisplayName("onion architecture should be followed for school application")
//  @Test
//  public void vehicleSearchArchTest() {
//    if (APPLICATION_CLASSES.isEmpty()) {
//      fail("Classes of school application module were not found");
//    }
//
//    Architectures
//      .onionArchitecture()
//      .domainModels("com.fabit.schoolapplication.domain..")
//      .domainServices("com.fabit.schoolapplication.domain..")
//      .applicationServices("com.fabit.schoolapplication.application..")
//      .adapter("persistence", "com.fabit.schoolapplication.infrastructure.persistence..")
//      .adapter(
//        "messages", "com.fabit.schoolapplication.infrastructure.event..",
//              "com.fabit.schoolapplication.infrastructure.listener.."
//      )
//      .adapter("web", "com.fabit.schoolapplication.infrastructure.controller..")
//      .check(APPLICATION_CLASSES);
//  }

  private String[] withAllowedPackages(final String... packages) {

    final List<String> strings = new ArrayList<>(ALLOWED_PACKAGES);

    strings.addAll(asList(packages));

    return strings.toArray(new String[0]);
  }

  private void contextBusinessRule(final String businessPackage, final JavaClasses javaClasses) {

    ArchRuleDefinition
      .classes()
      .that()
      .resideInAnyPackage(businessPackage)
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage(withAllowedPackages(businessPackage))
      .check(javaClasses);
  }

  private static Stream<String> findClassFiles(final String start) {

    try {

      return Files
        .walk(Path.of(new File(start).getAbsolutePath()))
        .filter(f -> f.toFile().getName().endsWith(".class"))
        .map(f -> f.toFile().getAbsolutePath());

    } catch (NoSuchFileException nfex) {
      return Stream.empty();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
