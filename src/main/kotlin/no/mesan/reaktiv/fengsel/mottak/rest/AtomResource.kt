package no.mesan.reaktiv.fengsel.mottak.rest


import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import no.mesan.reaktiv.fengsel.mottak.atom.AtomFeedGenerator
import no.mesan.reaktiv.fengsel.mottak.domene.Fange
import no.mesan.reaktiv.fengsel.mottak.repository.KontrollerteFangerRepository

import com.codahale.metrics.annotation.Timed
import com.rometools.rome.feed.atom.Feed
import com.rometools.rome.io.FeedException

/**
 * Rest-tjeneste for levering av atom feeds.

 * @author Christian Ihle
 */
Path("/atom")
Produces(value = MediaType.APPLICATION_ATOM_XML)
public class AtomResource(private val kontrollerteFangerRepository: KontrollerteFangerRepository) {
    private val atomFeedGenerator: AtomFeedGenerator

    init {
        this.atomFeedGenerator = AtomFeedGenerator()
    }

    GET
    Path("/alt")
    Timed
    throws(FeedException::class)
    public fun hentAlleAtomTing(): String {
        val newFeed = atomFeedGenerator.lagNyFeed("Fangemottak")
        val fanger = kontrollerteFangerRepository.hentAlleFanger()

        for (fange in fanger) {
            atomFeedGenerator.leggTilElement(newFeed, fange.navn, fange.id)
        }

        return atomFeedGenerator.hentFeedSomStreng(newFeed)
    }
}