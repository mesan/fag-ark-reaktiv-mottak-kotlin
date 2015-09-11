package no.mesan.reaktiv.fengsel.mottak.domene

import java.io.Serializable

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Representasjon av en fange.

 * @author Christian Ihle
 */
public class Fange(public val navn: String, public val id: String) : Serializable {

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("navn", navn)
                .append("id", id)
                .toString()
    }

    companion object {
        public val serialVersionUID: Long = 1
    }
}
