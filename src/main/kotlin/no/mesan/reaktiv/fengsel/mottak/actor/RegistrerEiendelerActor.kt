package no.mesan.reaktiv.fengsel.mottak.actor

import java.util.Arrays

import no.mesan.reaktiv.fengsel.mottak.domene.Fange
import no.mesan.reaktiv.fengsel.mottak.logistikk.Eiendel
import no.mesan.reaktiv.fengsel.mottak.logistikk.LogistikkService
import no.mesan.reaktiv.fengsel.mottak.melding.EiendelerRegistrertMelding
import no.mesan.reaktiv.fengsel.mottak.melding.NavnOgNrRegistrertMelding

import akka.actor.AbstractActor
import akka.actor.Props
import akka.japi.pf.FI
import akka.japi.pf.ReceiveBuilder
import no.mesan.reaktiv.fengsel.mottak.melding.FangeMottattMelding

/**
 * Actor for å registrere en fanges eiendeler.

 * @author Christian Ihle
 */
public class RegistrerEiendelerActor : AbstractActor() {

    init {
        val logistikkService = LogistikkService()

        receive(ReceiveBuilder.match(javaClass<NavnOgNrRegistrertMelding>(), FI.UnitApply<NavnOgNrRegistrertMelding> { fangeRegistrert ->
            println("RegistrerEiendelerActor - Registrerer fange: " + fangeRegistrert);

            val fange = fangeRegistrert.fange;
            val eiendeler = genererEiendeler();

            logistikkService.registrerEiendeler(fange, eiendeler);
            sender().tell(EiendelerRegistrertMelding(fange, eiendeler), self());
        }).build());
    }

    // TODO kunne vært litt mer spennende og tilfeldig
    private fun genererEiendeler(): List<Eiendel> {
        return Arrays.asList(
                Eiendel("Rambokniv", "Våpen", "Knivstikkeenhet"),
                Eiendel("Desert Eagle", "Våpen", "Skytevåpen"))
    }

    companion object {
        public fun props(): Props {
            return Props.create(javaClass<RegistrerEiendelerActor>())
        }
    }
}
