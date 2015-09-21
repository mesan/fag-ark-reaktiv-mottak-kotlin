package no.mesan.reaktiv.fengsel.mottak.rest

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

import java.nio.file.Files
import java.nio.file.Paths

import no.mesan.reaktiv.fengsel.mottak.dto.NyFangeDTO

import org.junit.Before
import org.junit.Ignore
import org.junit.Test

import retrofit.RestAdapter

/**
 * Integrasjonstest av [MottakRestService].

 * @author Christian Ihle
 */
@Ignore
public class MottakResourceIntegrationTest {

    private var mottakRestService: MottakRestService? = null

    @Before
    public fun setUp() {
        // Går mot service fra https://github.com/mesan/fag-ark-reaktiv-mottak
        val restAdapter = RestAdapter.Builder()
                .setEndpoint("http://localhost:8080")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()

        mottakRestService = restAdapter.create(MottakRestService::class.java)
    }

    @Test
    public fun startFangemottak() {
        val response = mottakRestService!!.startFangemottak(NyFangeDTO("Arne", "Bjarne"))

        // 204: the server has fulfilled the request but does not need to return an entity-body
        assertEquals(204, response.status.toLong())
    }

    @Test
    public fun startFangemottakMedMasseFolk() {
        val alleFornavn = hentAlleNavn("fornavn.txt")
        val alleEtternavn = hentAlleNavn("etternavn.txt")

        for (i in 0..99) {
            val fornavn = velgTilfeldigNavn(alleFornavn)
            val etternavn = velgTilfeldigNavn(alleEtternavn)
            val fange = NyFangeDTO(fornavn, etternavn)

            val response = mottakRestService!!.startFangemottak(fange)
            assertEquals(204, response.status.toLong())
        }
    }

    private fun hentAlleNavn(fil: String): List<String> {
        val resource = javaClass.classLoader.getResource(fil)
        assertNotNull(resource)

        val path = Paths.get(resource.toURI())

        return Files.readAllLines(path)
    }

    private fun velgTilfeldigNavn(navn: List<String>): String {
        return navn.get(velgTilfeldigTall(0, navn.size() - 1))
    }

    private fun velgTilfeldigTall(min: Int, max: Int): Int {
        val range = (max - min) + 1

        return (Math.random() * range).toInt() + min
    }
}
