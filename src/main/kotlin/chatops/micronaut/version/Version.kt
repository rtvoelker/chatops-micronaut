package chatops.micronaut.version

data class Version(
        var clientVersion: String,
        var jreVersions: Map<String, String>
)