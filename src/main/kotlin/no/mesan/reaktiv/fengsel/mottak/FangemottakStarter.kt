package no.mesan.reaktiv.fengsel.mottak

import no.mesan.reaktiv.fengsel.mottak.helse.TemplateHealthCheck
import no.mesan.reaktiv.fengsel.mottak.rest.AtomResource
import no.mesan.reaktiv.fengsel.mottak.rest.MottakResource
import no.mesan.reaktiv.fengsel.mottak.service.FangemottakService
import no.mesan.reaktiv.fengsel.mottak.repository.KontrollerteFangerRepository

import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

/**
 * Starts the server.
 *
 * App url: http://localhost:8080/mottak/
 * Atom url: http://localhost:8080/atom/alt
 * Metrics url: http://localhost:8081/
 */
public class FangemottakStarter : Application<FangemottakConfig>() {

    override fun getName(): String {
        return "fangemottak"
    }

    override fun initialize(bootstrap: Bootstrap<FangemottakConfig>) {
        // nothing to do yet
    }

    override fun run(configuration: FangemottakConfig, environment: Environment) {
        val healthCheck = TemplateHealthCheck(configuration.template)

        val kontrollerteFangerRepository = KontrollerteFangerRepository()

        val fangemottakService = FangemottakService(kontrollerteFangerRepository)
        val mottakResource = MottakResource(configuration.template, fangemottakService)

        val atomResource = AtomResource(kontrollerteFangerRepository)

        environment.healthChecks().register("template", healthCheck)
        environment.jersey().register(mottakResource)
        environment.jersey().register(atomResource)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            FangemottakStarter().run(arrayOf("server", "fangemottak.yml"))
        }
    }
}

fun main(args: Array<String>) = FangemottakStarter.main(args)
