package no.mesan.reaktiv.fengsel.mottak.actor

import no.mesan.reaktiv.fengsel.mottak.actor.Actorer.*

import no.mesan.reaktiv.fengsel.mottak.melding.EiendelerRegistrertMelding
import no.mesan.reaktiv.fengsel.mottak.melding.NavnOgNrRegistrertMelding
import no.mesan.reaktiv.fengsel.mottak.melding.FangeMottattMelding
import no.mesan.reaktiv.fengsel.mottak.repository.KontrollerteFangerRepository

import akka.actor.AbstractActor
import akka.actor.ActorSelection
import akka.actor.Props
import akka.japi.pf.FI
import akka.japi.pf.ReceiveBuilder

/**
 * Actor som har ansvar for å styre hele prosessen rundt fangemottak.

 * @author Christian Ihle
 */
public class FangemottakActor(kontrollerteFangerRepository: KontrollerteFangerRepository) : AbstractActor() {

    init {
        val registrerNavnOgNrActor = context().actorSelection(REGISTRERE_NAVN_OG_NR.adresse)
        val registrerEiendelerActor = context().actorSelection(REGISTRERE_EIENDELER.adresse)

        receive(ReceiveBuilder
                // Steg 1: registrere navn og nummer
                .match(javaClass<FangeMottattMelding>(), FI.UnitApply<FangeMottattMelding> { fangeMottattMelding ->
                    println("FangemottakActor - " + fangeMottattMelding)
                    registrerNavnOgNrActor.tell(fangeMottattMelding, self())
                })
                // Steg 2: registrere eiendeler
                .match(javaClass<NavnOgNrRegistrertMelding>(), FI.UnitApply<NavnOgNrRegistrertMelding> { navnOgNrRegistrertMelding ->
                    println("FangemottakActor - " + navnOgNrRegistrertMelding);
                    registrerEiendelerActor.tell(navnOgNrRegistrertMelding, self());
                })
                // Steg 3: gå til metalldetektor
                .match(javaClass<EiendelerRegistrertMelding>(), FI.UnitApply<EiendelerRegistrertMelding> { eiendelerRegistrertMelding ->
                    println("FangemottakActor - " + eiendelerRegistrertMelding);

                    // TODO midlertidig løsning for å teste atom+rest. Skal gjøres av actor for visitering
                    kontrollerteFangerRepository.leggTilFange(eiendelerRegistrertMelding.fange);
                })
                .build())
    }

    companion object {
        public fun props(kontrollerteFangerRepository: KontrollerteFangerRepository): Props {
            return Props.create(javaClass<FangemottakActor>(), kontrollerteFangerRepository)
        }
    }
}
