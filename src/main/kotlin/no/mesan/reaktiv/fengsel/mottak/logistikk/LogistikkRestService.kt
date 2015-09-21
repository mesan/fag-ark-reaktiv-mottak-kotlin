package no.mesan.reaktiv.fengsel.mottak.logistikk

import retrofit.client.Response
import retrofit.http.Body
import retrofit.http.PUT
import retrofit.http.Path

/**
 * REST-service for å jobbe med en fanges eiendeler.

 * @author Christian Ihle
 */
public interface LogistikkRestService {

    @PUT("/logistikk/eier/{fangeId}")
    public fun leggTilEiendeler(@Path("fangeId") fangeId: String, @Body eiendelListe: EiendelListeDTO): Response
}
