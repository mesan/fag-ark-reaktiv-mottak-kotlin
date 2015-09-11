package no.mesan.reaktiv.fengsel.mottak.dto

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Representasjon av en fange fra tjenesten fangeregister.

 * @author Christian Ihle
 */
public class FangeDTO(public val navn: String, public val id: String?) {

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("navn", navn)
                .append("id", id)
                .toString()
    }
}
