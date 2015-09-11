package no.mesan.reaktiv.fengsel.mottak.logistikk

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * DTO for en eiendel knyttet til en fange.

 * @author Christian Ihle
 */
public class EiendelDTO(public val navn: String, public val tekniskBeskrivelse: String, public val beskrivelse: String) {

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("navn", navn)
                .append("tekniskBeskrivelse", tekniskBeskrivelse)
                .append("beskrivelse", beskrivelse)
                .toString()
    }
}
