package io.connorwyatt.airline.shared.ids

import java.util.*
import org.hashids.Hashids

object HashidGenerator {
    private val hashids = Hashids("HASHID_SALT", 16)

    fun generate(): String {
        return hashids.encode(
            *UUID.randomUUID().toString().toByteArray().map { it.toLong() }.toLongArray()
        )
    }
}
