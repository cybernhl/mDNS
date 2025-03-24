package com.deepseek.chat.developer.android

import android.content.Context
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import com.deepseek.chat.developer.android.databinding.FragmentMainBinding
import kotlin.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.cybernhl.getProtocolOverTansportLayerLocal

//class MainPage : Fragment() {
//    private var _binding: FragmentMainBinding? = null
//    private val binding get() = _binding!!
//    private val viewmodel: MainViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentMainBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.buttonFirst.setOnClickListener {
//            viewmodel.discoverServices("_nsdchat._tcp.local.")
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}

@Composable
fun MainPage(viewmodel: MainViewModel = viewModel()) {
    var serviceName by remember { mutableStateOf("MyService") }
    var serviceType by remember { mutableStateOf(getProtocolOverTansportLayerLocal()) }
    Scaffold() { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val context = LocalContext.current
            (context.getSystemService(Context.WIFI_SERVICE) as WifiManager).let { wifi->
                wifi.createMulticastLock("my_multicastLock")?.apply {
                    setReferenceCounted(true)
                    acquire()
                }
            }
            OutlinedTextField(
                value = serviceName,
                onValueChange = { serviceName = it },
                label = { Text("Service Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = serviceType,
                onValueChange = { serviceType = it },
                label = { Text("Service Type") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    viewmodel.discoverServices(serviceType = serviceType)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go")
            }
        }
    }
}