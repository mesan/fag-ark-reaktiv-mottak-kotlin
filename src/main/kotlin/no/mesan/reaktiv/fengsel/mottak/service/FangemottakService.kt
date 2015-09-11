package no.mesan.reaktiv.fengsel.mottak.service

import no.mesan.reaktiv.fengsel.mottak.actor.Actorer.REGISTRERE_EIENDELER
import no.mesan.reaktiv.fengsel.mottak.actor.Actorer.REGISTRERE_NAVN_OG_NR

import no.mesan.reaktiv.fengsel.mottak.actor.FangemottakActor
import no.mesan.reaktiv.fengsel.mottak.actor.RegistrerEiendelerActor
import no.mesan.reaktiv.fengsel.mottak.actor.RegistrerNavnOgNrActor
import no.mesan.reaktiv.fengsel.mottak.melding.FangeMottattMelding
import no.mesan.reaktiv.fengsel.mottak.repository.KontrollerteFangerRepository

import akka.actor.ActorRef
import akka.actor.ActorSystem

/**
 * Service som setter i gang mottaksregistrering av en fange.

 * @author Christian Ihle
 */
public class FangemottakService(kontrollerteFangerRepository: KontrollerteFangerRepository) {

    private val fangemottak: ActorRef

    init {
        val akka = ActorSystem.create("mottak")

        // Initialiserer actor som styrer prosess
        fangemottak = akka.actorOf(FangemottakActor.props(kontrollerteFangerRepository))

        // Initialiserer actorer som utfører arbeidet
        akka.actorOf(RegistrerNavnOgNrActor.props(), REGISTRERE_NAVN_OG_NR.navn())
        akka.actorOf(RegistrerEiendelerActor.props(), REGISTRERE_EIENDELER.navn())
    }

    /**
     * Start her. Denne metoden setter i gang mottak av en fange til fengselet.

     * @param fangenavn Navn på ankommet fange.
     */
    public fun mottaFange(fangenavn: String) {
        println("FangemottakService - Fange har ankommet mottaket: " + fangenavn)
        fangemottak.tell(FangeMottattMelding(fangenavn), ActorRef.noSender())
    }
}
