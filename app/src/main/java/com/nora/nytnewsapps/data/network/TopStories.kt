package com.nora.nytnewsapps.data.network

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class TopStories(
        @Json(name = "copyright")
    val copyright: String,
        @Json(name = "last_updated")
    val lastUpdated: String,
        @Json(name = "num_results")
    val numResults: Int,
        @Json(name = "results")
    val results: List<Result>,
        @Json(name = "section")
    val section: String,
        @Json(name = "status")
    val status: String
)

@JsonClass(generateAdapter = true)
data class Result(
        @Json(name = "abstract")
    val summary: String,
        @Json(name = "byline")
    val author: String,
        @Json(name = "created_date")
    val createdDate: String,
        @Json(name = "des_facet")
    val desFacet: List<String>,
        @Json(name = "geo_facet")
    val geoFacet: List<String>,
        @Json(name = "item_type")
    val itemType: String,
        @Json(name = "kicker")
    val kicker: String,
        @Json(name = "material_type_facet")
    val materialTypeFacet: String,
        @Json(name = "multimedia")
    val multimedia: List<Multimedia>,
        @Json(name = "org_facet")
    val orgFacet: List<String>,
        @Json(name = "per_facet")
    val perFacet: List<String>,
        @Json(name = "published_date")
    val publishedDate: String,
        @Json(name = "section")
    val section: String,
        @Json(name = "short_url")
    val shortUrl: String,
        @Json(name = "subsection")
    val subsection: String,
        @Json(name = "title")
    val title: String,
        @Json(name = "updated_date")
    val updatedDate: String,
        @Json(name = "uri")
    val uri: String,
        @Json(name = "url")
    val url: String
)

@JsonClass(generateAdapter = true)
data class Multimedia(
    @Json(name = "caption")
    val caption: String,
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "format")
    val format: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "subtype")
    val subtype: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "width")
    val width: Int
)