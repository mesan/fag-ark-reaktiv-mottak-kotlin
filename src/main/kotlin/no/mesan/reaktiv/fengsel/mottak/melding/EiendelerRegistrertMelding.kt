package no.mesan.reaktiv.fengsel.mottak.melding

import java.io.Serializable

import no.mesan.reaktiv.fengsel.mottak.domene.Fange
import no.mesan.reaktiv.fengsel.mottak.logistikk.Eiendel

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Melding for å si fra at en fange har fått eiendelene sine registrert.

 * @author Christian Ihle
 */
public class EiendelerRegistrertMelding(public val fange: Fange, private val eiendeler: List<Eiendel>) : Serializable {

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("fange", fange)
                .append("eiendeler", eiendeler)
                .toString()
    }

    companion object {
        public val serialVersionUID: Long = 1
    }
}
