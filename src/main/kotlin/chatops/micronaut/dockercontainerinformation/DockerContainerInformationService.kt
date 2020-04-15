package chatops.micronaut.dockercontainerinformation

import java.util.*

class DockerContainerInformationService {

    fun listVersions(partOfContainerName: String = ""): String {
        var result = ""
        var containerInformation: List<String>
        /**
         * Get Name Id Label from all Docker Containers
         */
        val dockerTable = executeCommand(arrayOf("/bin/sh", "-c",
                "docker ps --format 'table {{.ID}}\\t{{.Names}}\\t{{.Image}}\\t{{.Labels}}\\t' -f name=$partOfContainerName -a"))
        val linesFromDockerTable = dockerTable.split("\n")
        /**
         * Adds "Implementation Version" to the table header
         */
        result = result.plus(linesFromDockerTable[0].plus("IMPLEMENTATION VERSION\t\n"))

        for (line in linesFromDockerTable.stream().skip(1)) {
            containerInformation = line.split(" ")
            result = result.plus(line)
            /**
             * Gets the Implementation-Version from the MANIFEST.MF
             */
            result = result.plus(executeCommand(arrayOf("/bin/sh", "-c",
                    "docker exec ${containerInformation[0]} unzip -p chatops-micronaut.jar META-INF/MANIFEST.MF | grep Implementation-Version"))
                    .replace("Implementation-Version: ", ""))
            result = result.plus("\n")
        }
        println(result)
        return result
    }

    /**
     * Executes given Command on th CommandLine and returns a String with all Lines
     */
    private fun executeCommand(command: Array<String>): String {
        val executedCommand = Runtime.getRuntime().exec(command)
        var result = ""

        Scanner(executedCommand.inputStream).use {
            while (it.hasNextLine()) {
                result = result.plus("${it.nextLine()}\n")
            }
        }
        return result
    }

}