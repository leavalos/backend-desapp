package ar.edu.unq.desapp.grupom.backenddesappapi

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.junit.ArchUnitRunner
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith

@RunWith(ArchUnitRunner::class)
@AnalyzeClasses(packagesOf = [ArchitectureTest::class])
class ArchitectureTest {
    @ArchTest
    fun serviceOnlyAccessedByController(importedClasses: JavaClasses = ClassFileImporter().importPackages("ar.edu.unq.desapp.grupom.backenddesappapi")) {
        val rule: ArchRule = classes()
                .that().resideInAPackage("..service..")
                .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..")
        rule.check(importedClasses)
    }

    @ArchTest
    fun modelOnlyAccessedByService(importedClasses: JavaClasses = ClassFileImporter().importPackages("ar.edu.unq.desapp.grupom.backenddesappapi")) {
        val importedClasses: JavaClasses = ClassFileImporter().importPackages("ar.edu.unq.desapp.grupom.backenddesappapi")
        val rule: ArchRule = classes()
                .that().resideInAPackage("..model..")
                .should().onlyBeAccessed().byAnyPackage("..service..", "..controller..", "..model..")
        rule.check(importedClasses)
    }

    @ArchTest
    fun repositoryOnlyAccessedByService(importedClasses: JavaClasses = ClassFileImporter().importPackages("ar.edu.unq.desapp.grupom.backenddesappapi")) {

        val rule: ArchRule = classes()
                .that().resideInAPackage("..repository..")
                .should().onlyBeAccessed().byAnyPackage("..service..", "..repository..")
        rule.check(importedClasses)
    }

    @ArchTest
    fun controllerOnlyAccessedBySelf(importedClasses: JavaClasses = ClassFileImporter().importPackages("ar.edu.unq.desapp.grupom.backenddesappapi")) {
        val importedClasses: JavaClasses = ClassFileImporter().importPackages("ar.edu.unq.desapp.grupom.backenddesappapi")
        val rule: ArchRule = classes()
                .that().resideInAPackage("..controller..")
                .should().onlyBeAccessed().byAnyPackage("..controller..")
        rule.check(importedClasses)
    }
}