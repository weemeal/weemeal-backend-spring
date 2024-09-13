package de.weemeal.weemealbackendspring.architecture

import com.tngtech.archunit.base.DescribedPredicate.alwaysTrue
import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.Architectures.onionArchitecture
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices
import de.weemeal.backend.WeemealBackendSpringApplication
import jakarta.persistence.Entity
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@AnalyzeClasses(
    packagesOf = [WeemealBackendSpringApplication::class],
    packages = ["de.weemeal.backend"],
    importOptions = [DoNotIncludeTests::class]
)
internal class MainArchitectureTests {
    private val rootPackage = "de.weemeal.backend"
    private val applicationPackage = "$rootPackage.application.."
    private val adapterPackage = "$rootPackage.adapter.."
    private val domainModelPackage = "$rootPackage.domain.model.."
    private val domainServicePackage = "$rootPackage.domain.service.."
    private val useCasePackage = "$rootPackage.domain.usecase.."
    private val portsPackage = "$rootPackage.domain.port.."

    /**
    *** How the onionArchitecture() from ArchUnit works!
    * https://www.archunit.org/userguide/html/000_Index.html#_onion_architecture
    *
    * The domain package is the core of the application. It consists of two parts.
    *   The domainModels packages contain the domain entities.
    *   The packages in domainServices contains services that use the entities in the domainModel packages.
    *
    * The applicationServices packages contain services and configuration to run the application and use cases. It can
    * use the items of the domain package but there must not be any dependency from the domain to the application packages.
    *
    * The adapter package contains logic to connect to external systems and/or infrastructure. No adapter may depend on
    * another adapter. Adapters can use both the items of the domain as well as the application packages. Vice versa,
    * neither the domain nor the application packages must contain dependencies on any adapter package.
    *
    */
    @ArchTest
    val `hexagonal architecture is respected`: ArchRule = onionArchitecture()
        .domainModels(domainModelPackage)
        .domainServices(domainServicePackage) //, portsPackage)
        .applicationServices(applicationPackage)
        .adapter("adapter", adapterPackage)
        .withOptionalLayers(true)
        .ignoreDependency(JavaClass.Predicates.belongToAnyOf(WeemealBackendSpringApplication::class.java), alwaysTrue())

    @ArchTest
    val `first level packages are free of cycles`: ArchRule = slices()
        .matching("$rootPackage.(*)..").should().beFreeOfCycles()


    /**
    * General Naming Conventions
    */
    @ArchTest
    val `no classes should have inbound or outbound in its name`: ArchRule = noClasses()
        .should().haveSimpleNameContaining("Inbound")
        .orShould().haveSimpleNameContaining("Outbound")
        .because("Ports and Adapter should be named for what they doing")

    /**
    * UseCase
    */
    @ArchTest
    val `check usecases`: ArchRule = classes()
        .that().haveSimpleNameEndingWith("UseCase")
        .or().resideInAPackage(useCasePackage)
        .and().areTopLevelClasses()
        .should().haveSimpleNameEndingWith("UseCase")
        .andShould().resideInAPackage(useCasePackage)
        .andShould().beInterfaces()

    /**
     * Ports
     */
    @ArchTest
    val `check ports`: ArchRule = classes()
        .that().haveSimpleNameEndingWith("Port")
        .or().resideInAPackage(portsPackage)
        .and().areTopLevelClasses()
        .should().haveSimpleNameEndingWith("Port")
        .andShould().resideInAPackage(portsPackage)
        .andShould().beInterfaces()

    /**
     * Adapter
     */
    @ArchTest
    val `check adapter`: ArchRule = classes()
        .that().haveSimpleNameEndingWith("Adapter")
        .and().areTopLevelClasses()
        .should().resideInAPackage(adapterPackage)

    /**
    * Services
    */
    @ArchTest
    val `check services by name`: ArchRule = classes()
        .that().haveSimpleNameEndingWith("Service")
        .and().areTopLevelClasses()
        .should().resideInAPackage(domainServicePackage)
        .andShould().beAnnotatedWith(Service::class.java)

    @ArchTest
    val `check services by annotation`: ArchRule = classes()
        .that().areAnnotatedWith(Service::class.java)
        .and().areTopLevelClasses()
        .should().resideInAPackage(domainServicePackage)
        .andShould().haveSimpleNameEndingWith("Service")

    /**
     * Entities
     */
    @ArchTest
    val `check entities by name`: ArchRule = classes()
        .that().haveSimpleNameEndingWith("Entity")
        .and().areTopLevelClasses()
        .should().resideInAPackage("..entity..")
        .andShould().beAnnotatedWith(Entity::class.java)

    @ArchTest
    val `check entities by annotation`: ArchRule = classes()
        .that().areAnnotatedWith(Entity::class.java)
        .and().areTopLevelClasses()
        .should().resideInAPackage("..entity..")
        .andShould().haveSimpleNameEndingWith("Entity")

    /**
     * Repository
     */
    @ArchTest
    val `check repository by name`: ArchRule = classes()
        .that().haveSimpleNameEndingWith("Repository")
        .and().areTopLevelClasses()
        .should().resideInAPackage("..persistence..")
        .andShould().beAnnotatedWith(Repository::class.java)

    @ArchTest
    val `check repository by annotation`: ArchRule = classes()
        .that().areAnnotatedWith(Repository::class.java)
        .and().areTopLevelClasses()
        .should().resideInAPackage("..persistence..")
        .andShould().haveSimpleNameEndingWith("Repository")

    /**
     * DomainModels
     */
    @ArchTest
    val `domain models are no entities`:ArchRule = classes()
        .that().resideInAPackage(domainModelPackage)
        .and().areTopLevelClasses()
        .should().haveSimpleNameNotEndingWith("Entity")
        .andShould().notBeAnnotatedWith(Entity::class.java)

    /**
     * Misc
     */
    @ArchTest
    val `no Rest Entpoints in domain package`: ArchRule = methods()
        .that().areDeclaredInClassesThat().resideInAnyPackage(domainModelPackage, domainServicePackage)
        .should().notBeAnnotatedWith(RequestMapping::class.java)
        .andShould().notBeAnnotatedWith(Mapping::class.java)
        .andShould().notBeAnnotatedWith(PostMapping::class.java)
        .andShould().notBeAnnotatedWith(GetMapping::class.java)
        .andShould().notBeAnnotatedWith(GetMapping::class.java)

    @ArchTest
    val `no Clients in domain package`: ArchRule = classes()
        .that().resideInAnyPackage(domainModelPackage, domainServicePackage)
        .and().areTopLevelClasses()
        .should().notBeAnnotatedWith(RestController::class.java)
        .andShould().haveSimpleNameNotEndingWith("Client")

    /**
    * Mapper
    */
    @ArchTest
    val `check mapper`: ArchRule = classes()
        .that().resideInAPackage("..mapper..")
        .and().areTopLevelClasses()
        .should().haveSimpleNameEndingWith("Mapper")

    /**
    * Scheduler
    */
    @ArchTest
    val `check scheduler`: ArchRule = classes()
        .that().resideInAPackage("..scheduler..")
        .and().areTopLevelClasses()
        .should().haveSimpleNameEndingWith("Scheduler")
}



