package com.example.sunbase_task.api

import com.example.sunbase_task.api.ApiInterface
import androidx.paging.PagingSource
import com.example.sunbase_task.models.dataItem
import com.example.sunbase_task.models.model
import retrofit2.HttpException
import java.io.IOException

class PagingSource (private val api: ApiInterface, val query:String?
) : PagingSource<Int,dataItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, dataItem> {
        val position = params.key ?: 1
       if(query==null||query=="") {
           return try {
               val response =
                   api.getAllImages(position, "_8IfsW1uHE616ZobsteW7ITjt9V6ZgsZ40RoM44C7KI")
               val photos = response?.body()

               LoadResult.Page(
                   data = photos!!,
                   prevKey = if (position == 1) null else position - 1,
                   nextKey = if (photos.isEmpty()) null else position + 1
               )
           } catch (exception: IOException) {
               LoadResult.Error(exception)
           } catch (exception: HttpException) {
               LoadResult.Error(exception)
           }
       }
       else
       {
           return try {
               val response =
                   api.searchImages(query,position, "_8IfsW1uHE616ZobsteW7ITjt9V6ZgsZ40RoM44C7KI")
               val photos = response?.body()?.results

               LoadResult.Page(
                   data = photos!!,
                   prevKey = if (position == 1) null else position - 1,
                   nextKey = if (photos.isEmpty()) null else position + 1
               )
           } catch (exception: IOException) {
               LoadResult.Error(exception)
           } catch (exception: HttpException) {
               LoadResult.Error(exception)
           }
       }
    }


}