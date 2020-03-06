package chatops.micronaut

import chatops.micronaut.version.endpoint.GasVersionEndpoint
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/version")
class VersionController(private val gasVersionEndpoint: GasVersionEndpoint) {

    @Get(produces = [MediaType.APPLICATION_JSON])
    fun getVersions(): SlackResponse {
        val version = gasVersionEndpoint.fetchVersion().blockingFirst()
        return SlackResponse("in_channel", version.clientVersion);
    }

    /**
     * Consumer MediaType.APPLICATION_FORM_URLENCODED necessary because Slack
     * does a Post request for this media type.
     */
    @Post(produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_FORM_URLENCODED])
    fun postForVersion(command: String, text: String): SlackResponse {
        val version = gasVersionEndpoint.fetchVersion().blockingFirst()
        return SlackResponse("in_channel", "Gas: " + version.clientVersion);
    }
}