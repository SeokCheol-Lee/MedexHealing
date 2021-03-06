package org.techtown.medexhealing.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import org.techtown.medexhealing.*
import org.techtown.medexhealing.databinding.FragmentModfragment4Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Modfragment4.newInstance] factory method to
 * create an instance of this fragment.
 */
class Modfragment4 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentModfragment4Binding? = null
    private val binding get() = _binding!!
    var temp: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var serial = MySharedPreferences.getUserSerial(requireContext())

        var retrofit = Retrofit.Builder()
            .baseUrl("http://220.149.244.199:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var controlservice = retrofit.create(ControlService::class.java)
        binding.btnLegup.setOnClickListener {
            val btnnum = "legup"
            controlservice.requestcon(serial,btnnum).enqueue(object: Callback<Modecon> {
                override fun onResponse(call: Call<Modecon>, response: Response<Modecon>) {
                    val rep = response.body()
                    Log.d("???????????? ?????? legup","msg : "+rep?.msg)
                    Log.d("???????????? ?????? legup","code : "+rep?.code)
                }

                override fun onFailure(call: Call<Modecon>, t: Throwable) {
                    Log.d("???????????? ?????? legup","${t.localizedMessage}")
                }

            })

        }
        binding.btnLegdown.setOnClickListener {
            val btnnum = "legdown"
            controlservice.requestcon(serial, btnnum).enqueue(object : Callback<Modecon>{
                override fun onResponse(call: Call<Modecon>, response: Response<Modecon>) {
                    val rep = response.body()
                    Log.d("???????????? ?????? legdown","msg : "+rep?.msg)
                    Log.d("???????????? ?????? legdown","code : "+rep?.code)
                }

                override fun onFailure(call: Call<Modecon>, t: Throwable) {
                    Log.d("???????????? ?????? legdown","${t.localizedMessage}")
                }

            })
        }
        binding.btnHeadup.setOnClickListener {
            val btnnum = "headup"
            controlservice.requestcon(serial, btnnum).enqueue(object  : Callback<Modecon>{
                override fun onResponse(call: Call<Modecon>, response: Response<Modecon>) {
                    val rep = response.body()
                    Log.d("???????????? ?????? headup","msg : "+rep?.msg)
                    Log.d("???????????? ?????? headup","code : "+rep?.code)
                }

                override fun onFailure(call: Call<Modecon>, t: Throwable) {
                    Log.d("???????????? ?????? headup","${t.localizedMessage}")
                }
            })
        }
        binding.btnHeaddown.setOnClickListener {
            val btnnum = "headdown"
            controlservice.requestcon(serial, btnnum).enqueue(object : Callback<Modecon>{
                override fun onResponse(call: Call<Modecon>, response: Response<Modecon>) {
                    val rep = response.body()
                    Log.d("???????????? ?????? headdown","msg : "+rep?.msg)
                    Log.d("???????????? ?????? headdown","code : "+rep?.code)
                }

                override fun onFailure(call: Call<Modecon>, t: Throwable) {
                    Log.d("???????????? ?????? headdown","${t.localizedMessage}")
                }

            })
        }
        var tempservice = retrofit.create(TempService::class.java)
        binding.btnTemup.setOnClickListener {
            val btnnum = "Tempup"
            val mod = ModSelectActivity()
            tempservice.requesttempcon(serial, btnnum).enqueue(object : Callback<Tempcon>{
                override fun onResponse(call: Call<Tempcon>, response: Response<Tempcon>) {
                    val rep = response.body()
                    Log.d("???????????? ?????? tempup", "???????????? :"+rep?.temp)
                    Log.d("???????????? ?????? tempup","msg : "+rep?.msg)
                    Log.d("???????????? ?????? tempup","code : "+rep?.code)
                    temp = rep?.temp
                    mod.changeTextView("$temp??C")
                }

                override fun onFailure(call: Call<Tempcon>, t: Throwable) {
                    Log.d("???????????? ?????? tempup","${t.localizedMessage}")
                }

            })
        }
        binding.btnTemdown.setOnClickListener {
            val btnnum = "Tempdown"
            val mod = ModSelectActivity()
            tempservice.requesttempcon(serial, btnnum).enqueue(object : Callback<Tempcon>{
                override fun onResponse(call: Call<Tempcon>, response: Response<Tempcon>) {
                    val rep = response.body()
                    Log.d("???????????? ?????? tempdown", "???????????? :"+rep?.temp)
                    Log.d("???????????? ?????? tempdown","msg : "+rep?.msg)
                    Log.d("???????????? ?????? tempdown","code : "+rep?.code)
                    temp = rep?.temp
                    mod.changeTextView("$temp??C")
                }

                override fun onFailure(call: Call<Tempcon>, t: Throwable) {
                    Log.d("???????????? ?????? tempdown","${t.localizedMessage}")
                }

            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_modfragment4, container, false)
        _binding = FragmentModfragment4Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Modfragment4.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Modfragment4().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}