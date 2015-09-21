package no.mesan.reaktiv.fengsel.mottak.logistikk

import no.mesan.reaktiv.fengsel.mottak.domene.Fange
import no.mesan.reaktiv.fengsel.mottak.service.LogLevelVelgerService

import retrofit.RestAdapter

/**
 * Service for å jobbe med en fanges eiendeler.

 * @author Christian Ihle
 */
public class LogistikkService {

    private val logistikkRestService: LogistikkRestService

    init {
        val logLevelVelgerService = LogLevelVelgerService()

        // Går mot service fra https://github.com/mesan/fag-ark-reaktiv-logistikk
        val restAdapter = RestAdapter.Builder().setEndpoint("http://localhost:9999")
                .setLogLevel(logLevelVelgerService.velgLogLevel())
                .build()

        logistikkRestService = restAdapter.create(LogistikkRestService::class.java)
    }

    public fun registrerEiendeler(fange: Fange, eiendeler: List<Eiendel>) {
        val eiendelDTOer = eiendeler.map{eiendel ->
            EiendelDTO(eiendel.navn,
                       eiendel.tekniskBeskrivelse,
                       eiendel.beskrivelse)
        }

        val eiendelListe = EiendelListeDTO(eiendelDTOer)

        println(java.lang.String.format("LogistikkService - Sender %s sine eiendeler til logistikk rest-tjeneste: %s",
                fange, eiendelListe))
        logistikkRestService.leggTilEiendeler(fange.id, eiendelListe)
    }
}
