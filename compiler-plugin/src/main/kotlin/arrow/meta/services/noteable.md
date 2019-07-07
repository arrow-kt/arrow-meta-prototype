## A curated list of notable compiler services 
### General Gist
I am an amateur, when it comes to compilers so take this with a grain of salt. 
What we discovered about the compiler internals in kotlin is this:

The compiler process is structured in phases - there called extensions.
In each phase there are different services (non-)available and we can mutate, because the compiler process is written in a more OOP style,
We provide an Arrow Meta Dsl which goes like this: 
Whenever we register a new component to the compiler we start implementing the intercept method in the [MetaComponentRegistrar], which is 
simply speaking nothing else then a list of ExtensionPhases - you see the irony. 
```kotlin
override fun intercept(): List<ExtensionPhase> =
    meta(
        // Let's fuel kotlin with FP idioms and more great tooling
    )
```
For each ExtensionPhase you tap into you're able to access key variables of that Phase - most notably the [StorageComponentContainer].

The small gist is that you intercept any phase of the compiler and register or mutate your services, just the way you want.
```kotlin
override fun intercept(): List<ExtensionPhase> =
    meta(
        storageComponent( 
          // in this Phase the container does not know about depended services
          registerModuleComponents = { container: StorageComponentContainer, platform, moduleDescriptor ->
              // This is the time, where you register most services
              }
        ),
        
    )
```

Well, some would say, why is it important to register your service at such an early stage.
Simply put, if the compiler is aware about our modified service in an earlier stage we get more help throughout 
the subsequent phases and get something like an DI 
- Nice overview how to register basic services in kotlin: kotlin/compiler/frontend/src/org/jetbrains/kotlin/frontend/di/injection.kt

### CallCheckers
- LambdaWithSuspendModifierCallChecker is within the DEFAULT_CALL_CHECKERS List in TargetPlatform

# Other exposed compiler artifacts // [all exposed artifacts](https://dl.bintray.com/kotlin/kotlin-eap/org/jetbrains/kotlin/)
- [Kotlin Compiler Client Embeddable](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-compiler-client-embeddable)
- [Kotlin Compiler Runner](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-compiler-runner)
- [Kotlin Compiler Nostrip](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-compiler-nostrip)
- [Kotlin Frontend Plugin](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-frontend-plugin)


















