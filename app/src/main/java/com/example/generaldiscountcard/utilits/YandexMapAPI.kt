package com.example.generaldiscountcard.utilits

import com.fasterxml.jackson.annotation.JsonProperty


class YandexMapAPI(
    @JsonProperty(value = "type") val type: String,
    @JsonProperty(value = "properties") val properties: Properties,
) {
    class Properties(
        @JsonProperty(value = "ResponseMetaData") val responseMetaData: ResponseMetaData,
    ) {
        class ResponseMetaData(
            @JsonProperty(value = "SearchRequest") val searchRequest: SearchRequest,
            @JsonProperty(value = "SearchResponse") val searchResponse: SearchResponse
        ) {
            class SearchRequest(
                @JsonProperty(value = "request") val request: String,
                @JsonProperty(value = "results") val results: Int,
                @JsonProperty(value = "skip") val skip: Int,
                @JsonProperty(value = "boundedBy") val boundedBy: ArrayList<ArrayList<Double>>
            )

            class SearchResponse(
                @JsonProperty(value = "found") val found: Int,
                @JsonProperty(value = "Point") val point: Point,
                @JsonProperty(value = "boundedBy") val boundedBy: ArrayList<ArrayList<Double>>,
                @JsonProperty(value = "display") val display: String
            ) {
                class Point(
                    @JsonProperty(value = "type") val type: String,
                    @JsonProperty(value = "coordinates") val found: ArrayList<Double>
                )
            }
        }
    }
}
