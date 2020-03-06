package chatops.micronaut.version.endpoint

import chatops.micronaut.version.Version
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Flowable

@Client(VersionEndpointConfiguration.GAS_URL)
interface GasVersionEndpoint {

    @Get("/client/version")
    fun fetchVersion(): Flowable<Version>
}
