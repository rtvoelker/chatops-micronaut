package chatops.micronaut.version.endpoint

import chatops.micronaut.version.endpoint.VersionEndpointConfiguration.Companion.PREFIX
import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties(PREFIX)
class VersionEndpointConfiguration {
    companion object {
        const val PREFIX = "bintray"

        const val GAS_URL = "https://pegasusgas.uniper-digital.de"
    }
}

