package no.mesan.reaktiv.fengsel.mottak.rest


import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import no.mesan.reaktiv.fengsel.mottak.atom.AtomFeedGenerator
import no.mesan.reaktiv.fengsel.mottak.repository.KontrollerteFangerRepository

import com.codahale.metrics.annotation.Timed

/**
 * Rest-tjeneste for levering av atom feeds.

 * @author Christian Ihle
 */
@Path("/atom")
@Produces(value = MediaType.APPLICATION_ATOM_XML)
public class AtomResource(private val kontrollerteFangerRepository: KontrollerteFangerRepository) {

    private val atomFeedGenerator: AtomFeedGenerator

    init {
        this.atomFeedGenerator = AtomFeedGenerator()
    }

    @GET
    @Path("/alt")
    @Timed
    public fun hentAlleAtomTing(): String {
        val nyFeed = atomFeedGenerator.lagNyFeed("Fangemottak")
        val fanger = kontrollerteFangerRepository.hentAlleFanger()

        for (fange in fanger) {
            atomFeedGenerator.leggTilElement(nyFeed, fange.navn, fange.id)
        }

        return atomFeedGenerator.hentFeedSomStreng(nyFeed)
    }
}
