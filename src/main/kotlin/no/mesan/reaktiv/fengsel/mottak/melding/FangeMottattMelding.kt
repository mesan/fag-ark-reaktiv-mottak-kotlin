package no.mesan.reaktiv.fengsel.mottak.melding

import java.io.Serializable

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Melding for å si at en fange har ankommet fengselet.

 * @author Christian Ihle
 */
public class FangeMottattMelding(public val fangenavn: String) : Serializable {

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("fangenavn", fangenavn)
                .toString()
    }

    companion object {
        public val serialVersionUID: Long = 1
    }
}
