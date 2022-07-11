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
 * Use the [Modfragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Modfragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentModfragment2Binding? = null
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

        var controlservice = retrofit.create(ControlService::class.java)
        binding.btnFlat.setOnClickListener {
            val btnnum = "flat"
            controlservice.requestcon(serial, btnnum).enqueue(object : Callback<Modecon> {
                override fun onResponse(call: Call<Modecon>, response: Response<Modecon>) {
                    val rep = response.body()
                    Log.d("베드조작 성공 flat","msg : "+rep?.msg)
                    Log.d("베드조작 성공 flat","code : "+rep?.code)
                }

                override fun onFailure(call: Call<Modecon>, t: Throwable) {
                    Log.d("베드조작 실패 flat","${t.localizedMessage}")
                }

            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_modfragment2, container, false)
        _binding = FragmentModfragment2Binding.inflate(inflater, container, false)
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
         * @return A new instance of fragment Modfragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Modfragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}