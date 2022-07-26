package com.tuyenhoang.appdoctruyenjsoupv2

import java.io.Serializable

data class ItemStory(
    var linkHtml:String,
    var linkImage:String,
    var title:String,
    var content:String
):Serializable {
}