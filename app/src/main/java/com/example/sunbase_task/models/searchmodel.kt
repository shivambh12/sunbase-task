package com.example.sunbase_task.models

import java.util.ArrayList

data class searchmodel(
    val total:Int,
    val total_pages:Int,
    val results:ArrayList<dataItem>
)