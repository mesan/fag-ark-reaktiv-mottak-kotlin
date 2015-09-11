package no.mesan.reaktiv.fengsel.mottak.fangeregister

import no.mesan.reaktiv.fengsel.mottak.domene.Fange
import no.mesan.reaktiv.fengsel.mottak.dto.FangeDTO
import no.mesan.reaktiv.fengsel.mottak.service.LogLevelVelgerService
import org.apache.commons.lang3.Validate

import retrofit.RestAdapter

/**
 * Service for å manipulere fanger i fangeregisteret.

 * @author Christian Ihle
 */
public class FangeregisterService {

    private val fangeregisterRestService: FangeregisterRestService

    init {
        val logLevelVelgerService = LogLevelVelgerService()

        // Går mot service fra https://github.com/mesan/fag-ark-persistering-fangeregister
        val restAdapter = RestAdapter.Builder()
                .setEndpoint("http://localhost:49000")
                .setLogLevel(logLevelVelgerService.velgLogLevel())
                .build()

        fangeregisterRestService = restAdapter.create(javaClass<FangeregisterRestService>())
    }

    public fun lagreFange(fangenavn: String): Fange {
        val fangeTilSending = FangeDTO(fangenavn, null)

        println("FangeregisterService - Registrerer fange i fangeregister rest-tjeneste: " + fangeTilSending)
        val opprettetFange = fangeregisterRestService.opprettFange(fangeTilSending)

        if (opprettetFange.id == null) {
            throw IllegalStateException("Mangler id: " + opprettetFange)
        }

        return Fange(opprettetFange.navn, opprettetFange.id)
    }
}
