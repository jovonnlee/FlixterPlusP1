package com.example.flixterplusp1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MovieFragment : Fragment(), OnListFragmentInteractionsListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_view_list, container, false)
        val recyclerView = view.findViewById<View>(R.id.movieRv) as RecyclerView
        val context = view.context

        recyclerView.layoutManager = LinearLayoutManager(context)
        updateAdapter(recyclerView)
        return view
    }
    private fun updateAdapter(recyclerView: RecyclerView) {
        val client = AsyncHttpClient()
        val params = RequestParams()

        params["api-key"] = API_KEY


        client [
                "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US&page=1",
                params,

                object : JsonHttpResponseHandler()


        {
             override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
             ) {
                        val arrayMovieType = object : TypeToken<List<Movies>>() {}.type
                        val resultsJSON = json.jsonObject.getJSONArray("results")
                        val movsRawJSON: String = resultsJSON.toString()
                        val gson = Gson()
                        val models: List<Movies> = gson.fromJson(movsRawJSON, arrayMovieType)
                        recyclerView.adapter = MovieRecyclerViewAdapter(models, this@MovieFragment)

                        Log.d("MovieFragment", "response successful")
             }

             override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?

             ) {
                  t?.message?.let {
                        Log.e("MovieFragment", errorResponse) }
             }
        }]
    }

    override fun onItemClick(item: Movies) {
            Toast.makeText(context, "test: " + item.movieTitle, Toast.LENGTH_LONG).show()
    }
}