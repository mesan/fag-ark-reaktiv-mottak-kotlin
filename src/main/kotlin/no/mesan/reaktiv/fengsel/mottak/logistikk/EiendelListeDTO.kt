package no.mesan.reaktiv.fengsel.mottak.logistikk


import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * DTO med en liste av en fanges eiendeler.

 * @author Christian Ihle
 */
public class EiendelListeDTO(public val eiendel: List<EiendelDTO>) {

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("eiendel", eiendel)
                .toString()
    }
}
