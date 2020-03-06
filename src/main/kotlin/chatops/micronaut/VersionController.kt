package chatops.micronaut

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/version")
class VersionController {

    @Get(produces = [MediaType.TEXT_PLAIN])
    fun index(): String {
        return "Hello World"
    }

    @Get(produces = [MediaType.APPLICATION_JSON])
    fun getVersions(): List<String> {
        return listOf("1.2.3");
    }
}