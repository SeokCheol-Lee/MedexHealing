package org.techtown.medexhealing.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.techtown.medexhealing.MySharedPreferences
import org.techtown.medexhealing.R
import org.techtown.medexhealing.databinding.FragmentModfragment2Binding
import org.techtown.medexhealing.databinding.FragmentModfragment3Binding
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
 * Use the [Modfragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class Modfragment3 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentModfragment3Binding? = null
    private val binding get() = _binding!!

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
        var sleepservice = retrofit.create(SleepService::class.java)
        binding.btnSleep.setOnClickListener {
            val btnnum = "sleep"
            sleepservice.sleepcheck(serial, btnnum).enqueue(object : Callback<SleepCheck>{
                override fun onResponse(call: Call<SleepCheck>, response: Response<SleepCheck>) {
                    val rep = response.body()
                    Log.d("???????????? ?????? sleep","msg : "+rep?.msg)
                    Log.d("???????????? ?????? sleep","code : "+rep?.code)
                }

                override fun onFailure(call: Call<SleepCheck>, t: Throwable) {
                    Log.d("???????????? ?????? sleep","${t.localizedMessage}")
                }

            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_modfragment3, container, false)
        _binding = FragmentModfragment3Binding.inflate(inflater, container, false)
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
         * @return A new instance of fragment Modfragment3.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Modfragment3().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}