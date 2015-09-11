package no.mesan.reaktiv.fengsel.mottak.actor

import no.mesan.reaktiv.fengsel.mottak.domene.Fange
import no.mesan.reaktiv.fengsel.mottak.fangeregister.FangeregisterService
import no.mesan.reaktiv.fengsel.mottak.melding.FangeMottattMelding
import no.mesan.reaktiv.fengsel.mottak.melding.NavnOgNrRegistrertMelding

import akka.actor.AbstractActor
import akka.actor.Props
import akka.japi.pf.FI
import akka.japi.pf.ReceiveBuilder

/**
 * Actor for å registrere navn og nummer på en fange.

 * @author Christian Ihle
 */
public class RegistrerNavnOgNrActor : AbstractActor() {

    init {
        val fangeregisterService = FangeregisterService()

        receive(ReceiveBuilder
                .match(javaClass<FangeMottattMelding>(), FI.UnitApply<FangeMottattMelding> { fangeMottatt ->
                    println("RegistrerNavnOgNrActor - Registrerer fange: " + fangeMottatt);

                    val lagretFange = fangeregisterService.lagreFange(fangeMottatt.fangenavn);
                    sender().tell(NavnOgNrRegistrertMelding(lagretFange), self());
                })
                .build());
    }

    companion object {
        public fun props(): Props {
            return Props.create(javaClass<RegistrerNavnOgNrActor>())
        }
    }
}
