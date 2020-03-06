package chatops.micronaut

import chatops.micronaut.version.endpoint.GasVersionEndpoint
import chatops.micronaut.version.Version
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Flowable

@Controller("/version")
class VersionController(private val gasVersionEndpoint: GasVersionEndpoint) {

    @Get(produces = [MediaType.APPLICATION_JSON])
    fun getVersions(): Flowable<Version> {
        return gasVersionEndpoint.fetchVersion();
    }
}