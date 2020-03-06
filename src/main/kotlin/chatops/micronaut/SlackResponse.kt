package chatops.micronaut

data class SlackResponse(
        var responseType: String,
        var text: String
)