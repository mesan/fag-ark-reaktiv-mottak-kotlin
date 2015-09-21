package no.mesan.reaktiv.fengsel.mottak.rest

import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType

import no.mesan.reaktiv.fengsel.mottak.dto.NyFangeDTO
import no.mesan.reaktiv.fengsel.mottak.service.FangemottakService

import com.codahale.metrics.annotation.Timed

/**
 * Rest-tjeneste for mottak av fanger

 * @author Svein Melby
 */
@Path("/mottak/")
@Consumes(value = MediaType.APPLICATION_JSON)
public class MottakResource(private val template: String?, private val fangemottakService: FangemottakService) {

    @POST
    @Timed
    public fun startFangemottak(@Valid fange: NyFangeDTO) {
        fangemottakService.mottaFange(fange.getFulltNavn())
    }
}
