package chatops.micronaut

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("chatops.micronaut")
                .mainClass(Application.javaClass)
                .start()
    }
}