package com.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.retrofitapp.remote.PokemonEntry
import com.retrofitapp.remote.RetrofitBuilder
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = RetrofitBuilder.create().getPokemonById("27")

        retrofit.enqueue(object: Callback<PokemonEntry> {

            //override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>){
           //     Log.d("retofitresponse","res: ${response.body()}")
           // }
            override fun onResponse(
                call: Call<PokemonEntry>,
                response: retrofit2.Response<PokemonEntry>
            ) {
                val resBody = response.body()
                if(resBody != null){

                    Log.d("retrofitresponse","res: ${resBody}")
                    Log.d("retrofitresponse","name: ${resBody.name}")

                    for(stat in resBody.stats){
                        Log.d("retrofitresponse", "${stat.stat.stat_value}: ${stat.base_stat}")
                    }

                    Log.d("retrofitresponse","type: ${resBody.types[0].type.name}")

                    Log.d("retrofitresponse", "sprite: ${resBody.sprites.sprite_value}")


                }






            }


            override fun onFailure(call: Call<PokemonEntry>, t: Throwable) {
                Log.e("retofitresponse", "error: ${t.message}")
            }


        })
    }
}