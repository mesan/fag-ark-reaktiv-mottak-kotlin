package no.mesan.reaktiv.fengsel.mottak.melding

import java.io.Serializable

import no.mesan.reaktiv.fengsel.mottak.domene.Fange

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Melding for å si fra at en fange er registrert med navn og nummer.

 * @author Christian Ihle
 */
public class NavnOgNrRegistrertMelding(public val fange: Fange) : Serializable {

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("fange", fange)
                .toString()
    }

    companion object {
        public val serialVersionUID: Long = 1
    }
}
