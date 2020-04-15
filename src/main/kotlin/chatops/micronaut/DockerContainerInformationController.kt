package chatops.micronaut

import chatops.micronaut.dockercontainerinformation.DockerContainerInformationService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/listVersions")
class DockerContainerInformationController {

    @Post(produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_FORM_URLENCODED])
    fun postForVersion(command: String, text: String): SlackResponse {

        val version: String
        val params = text.split(" ")

        version = if (params.size > 1) {
            chooseCommand(params[0], params[1])
        } else {
            chooseCommand(params[0])
        }
        return SlackResponse("in_channel", version)
    }

    private fun chooseCommand(command: String, partOfContainerName: String = ""): String {
        val versionService = DockerContainerInformationService()
        return when (command) {
            "listversions" -> versionService.listVersions(partOfContainerName)
            else -> {
                ""
            }
        }
    }
}