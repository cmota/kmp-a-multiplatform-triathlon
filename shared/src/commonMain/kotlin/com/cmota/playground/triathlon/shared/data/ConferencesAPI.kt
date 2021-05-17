package com.cmota.playground.triathlon.shared.data

import com.cmota.playground.triathlon.shared.data.common.HttpClientLogger
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

private const val BASE_URL = "https://gist.githubusercontent.com/cmota/"
private const val ENDPOINT = "99626b9d3f3837679d42970f8df5160c/raw/a702fb9b5e00b7d1b608de5d9b6fda780ff3ba4b/conferences-2.json"

class ConferencesAPI {

    private val client = HttpClient{
        install(Logging) {
            logger = HttpClientLogger
            level = LogLevel.ALL
        }
    }
    /**
     * If the response was an application/json we would need to install [JsonFeature] on [HttpClient]
     * something similar to:
     *
     * private val jsonParser = Json { ignoreUnknownKeys = true; isLenient = true }
     *
     * private val client = HttpClient() {
     *   install(JsonFeature) {
     *     serializer = KotlinxSerializer(jsonParser)
     *   }
     * }
     *
     *
     * However, on the latest version of Ktor (1.4.1) there's an issue that would trigger an
     * InvalidMutabilityException` when running on iOS. If on your use case you want to parse a
     * json file automatically, I advise you to rollback to ktor 1.4.0
     */

    /** This is a request to GitHub Gists that returns application/json as text/plain which is not
     *  not currently supported. If it was, instead of `String` it should be `List<Conference>`.
     *
     *  With this, we'll get the `List<Conference>` by decoding the response on
     *  [com.cmota.playground.triathlon.shared.domain.GetConferences]
     */
    suspend fun fetchConferences() = client.get<String>("$BASE_URL$ENDPOINT")
}