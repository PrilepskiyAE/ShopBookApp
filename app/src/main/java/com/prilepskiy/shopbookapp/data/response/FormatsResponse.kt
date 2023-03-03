package com.prilepskiy.shopbookapp.data.response

import com.google.gson.annotations.SerializedName
import com.prilepskiy.shopbookapp.domain.model.FormatsModel
import com.prilepskiy.shopbookapp.domain.model.TranslatorModel

//"formats": {
//    "application/x-mobipocket-ebook": "https://www.gutenberg.org/ebooks/1513.kf8.images",
//    "application/epub+zip": "https://www.gutenberg.org/ebooks/1513.epub3.images",
//    "text/html": "https://www.gutenberg.org/ebooks/1513.html.images",
//    "application/octet-stream": "https://www.gutenberg.org/files/1513/1513-0.zip",
//    "image/jpeg": "https://www.gutenberg.org/cache/epub/1513/pg1513.cover.medium.jpg",
//    "text/plain": "https://www.gutenberg.org/ebooks/1513.txt.utf-8",
//    "text/plain; charset=us-ascii": "https://www.gutenberg.org/files/1513/1513-0.txt",
//    "application/rdf+xml": "https://www.gutenberg.org/ebooks/1513.rdf"
//}
data class FormatsResponse(
    @SerializedName(value = "application/x-mobipocket-ebook")
    val ebook: String,
    @SerializedName(value = "image/jpeg")
    val image_jpeg: String,
    @SerializedName(value = "application/rdf+xml")
    val rdf_xml: String
) {
    companion object {
        fun from(data: FormatsModel): FormatsResponse = with(data) {
            FormatsResponse(ebook, image_jpeg, rdf_xml)
        }
    }
}