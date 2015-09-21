package no.mesan.reaktiv.fengsel.mottak.fangeregister

import no.mesan.reaktiv.fengsel.mottak.dto.FangeDTO

import retrofit.http.Body
import retrofit.http.POST

/**
 * REST-service for å manipulere fanger i fangeregisteret.

 * @author Christian Ihle
 */
public interface FangeregisterRestService {

    @POST("/fanger")
    public fun opprettFange(@Body fangeDTO: FangeDTO): FangeDTO
}
