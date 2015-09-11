package no.mesan.reaktiv.fengsel.mottak.atom

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.UUID

import com.rometools.rome.feed.atom.Entry
import com.rometools.rome.feed.atom.Feed
import com.rometools.rome.io.FeedException
import com.rometools.rome.io.WireFeedOutput

/**
 * Service for å lage atom feeds.
 */
public class AtomFeedGenerator {

    public fun lagNyFeed(tittel: String): Feed {
        val feed = Feed()

        feed.setFeedType("atom_1.0")
        feed.setTitle(tittel)
        feed.setId(genererUnikId())
        feed.setUpdated(lagDato())

        return feed
    }

    public fun leggTilElement(feed: Feed, tittel: String, id: String): Entry {
        val entry = Entry()

        entry.setTitle(tittel)
        entry.setId(id)
        entry.setUpdated(lagDato())

        leggElementIFeed(feed, entry)

        return entry
    }

    public fun hentFeedSomStreng(feed: Feed): String {
        val wfo = WireFeedOutput()

        try {
            return wfo.outputString(feed)
        } catch (e: FeedException) {
            throw RuntimeException(e)
        }

    }

    private fun leggElementIFeed(feed: Feed, entry: Entry) {
        var entries: MutableList<Entry>? = feed.getEntries()

        if (entries == null) {
            entries = ArrayList<Entry>()
        }

        entries.add(entry)
        feed.setEntries(entries)
    }

    private fun genererUnikId(): String {
        return UUID.randomUUID().toString()
    }

    private fun lagDato(): Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        val RFC3339DateString = sdf.format(Date())

        try {
            return sdf.parseObject(RFC3339DateString) as Date
        } catch (e: ParseException) {
            throw RuntimeException(e)
        }

    }
}
