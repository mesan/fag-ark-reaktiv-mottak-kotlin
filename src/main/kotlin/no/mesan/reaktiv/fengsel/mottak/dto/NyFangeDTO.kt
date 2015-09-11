package no.mesan.reaktiv.fengsel.mottak.dto

import javax.validation.constraints.NotNull

import org.apache.commons.lang.builder.ToStringBuilder
import org.apache.commons.lang.builder.ToStringStyle
import org.hibernate.validator.constraints.NotBlank

/**
 * Representasjon av en ny fange.
 * Dette er en fange som ikke har fått noen id ennå.

 * @author Svein Melby
 */
public class NyFangeDTO {

    NotBlank
    public var fornavn: String? = null

    NotNull
    public var etternavn: String? = null

    public constructor() {
    }

    public constructor(fornavn: String, etternavn: String) {
        this.fornavn = fornavn
        this.etternavn = etternavn
    }

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("fornavn", fornavn)
                .append("etternavn", etternavn)
                .toString()
    }

    public fun getFulltNavn(): String {
        return this.fornavn + " " + this.etternavn
    }
}
